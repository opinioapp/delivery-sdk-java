package com.opinioapp.sdk.delivery;

import com.opinioapp.sdk.delivery.auth.Signer;
import com.opinioapp.sdk.delivery.exceptions.*;
import com.opinioapp.sdk.delivery.reponses.*;
import com.opinioapp.sdk.delivery.requests.CancellationRequest;
import com.opinioapp.sdk.delivery.requests.DeliveryRequest;
import com.opinioapp.sdk.delivery.requests.MerchantRegistrationRequest;
import com.opinioapp.sdk.delivery.utils.HttpAgent;
import com.opinioapp.sdk.delivery.utils.JSON;
import org.apache.http.HttpStatus;
import org.apache.http.util.TextUtils;

import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.Validator;
import javax.validation.ValidatorFactory;
import java.io.IOException;
import java.security.SignatureException;
import java.text.SimpleDateFormat;
import java.util.*;

/**
 * Created by lokesh on 4/11/15.
 *
 * Default implementation of opinio apiclient
 * {@link ApiClient}
 */
public class ApiClientImpl implements ApiClient {
    private boolean isInitialized = false;

    private String host;
    private String hostHeader;
    private Credentials credentials;

    private Validator validator;

    public void init(EnvironmentEnum environment, Credentials credentials) throws KeysNotFoundException {
        if (environment == null) {
            throw new IllegalArgumentException("environment cannot be null");
        }

        if (credentials == null ||
                TextUtils.isEmpty(credentials.getAccessKeyId()) ||
                TextUtils.isEmpty(credentials.getSecretKey())) {
            throw new KeysNotFoundException();
        }


        ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
        validator = factory.getValidator();

        this.credentials = credentials;
        this.host = environment.getValue();
        this.hostHeader = this.host.substring(this.host.indexOf("://") + 3);
        this.isInitialized = true;
    }

    @Override
    public RegisteredMerchant registerMerchant(MerchantRegistrationRequest request) throws
            PluginNotSetupException, MerchantAlreadyRegisteredException, IOException, ApiCallException {
        if (!isInitialized) {
            throw new PluginNotSetupException();
        }

        Set<ConstraintViolation<MerchantRegistrationRequest>> violations = validator.validate(request);
        if (violations.size() > 0) {
            throw new IllegalArgumentException(violations.toString());
        }

        BaseApiResponse response = makeCall(request, HttpAgent.HTTPMethod.POST, "/api/v1/merchants");

        if (!response.isOk()) {
            ApiError apiError = JSON.parse(response.getJsonBody(), ApiError.class);

            if (response.getStatusCode() == HttpStatus.SC_UNAUTHORIZED) {
                throw new PluginNotSetupException("AccessKey and Secret not setup properly");
            }

            if (response.getStatusCode() == HttpStatus.SC_UNPROCESSABLE_ENTITY) {
                throw new MerchantAlreadyRegisteredException();
            }

            throw new ApiCallException(apiError);
        }

        return JSON.parse(response.getJsonBody(), RegisteredMerchant.class);
    }

    @Override
    public Serviceability checkServiceability(String merchantId) throws PluginNotSetupException, IOException, ApiCallException {
        if (!isInitialized) {
            throw new PluginNotSetupException();
        }

        Map<String, String> qs = new HashMap<>();
        qs.put("merchant_id", merchantId);

        BaseApiResponse response = makeCall(qs, HttpAgent.HTTPMethod.GET, "/api/v1/serviceability");

        if (!response.isOk()) {
            onUnAuthorized(response);
        }

        return JSON.parse(response.getJsonBody(), Serviceability.class);
    }

    @Override
    public SupportedLocalities getSupportedLocalities(String merchantId) throws PluginNotSetupException, IOException, ApiCallException {
        if (!isInitialized) {
            throw new PluginNotSetupException();
        }

        BaseApiResponse response = makeCall(new Object(), HttpAgent.HTTPMethod.GET, "/api/v1/localities/" + merchantId);

        if (!response.isOk()) {
            onUnAuthorized(response);
        }

        return JSON.parse(response.getJsonBody(), SupportedLocalities.class);
    }

    @Override
    public DeliveryOrder requestDelivery(DeliveryRequest request) throws
            PluginNotSetupException, IOException, ApiCallException, NoPilotAvailableException {

        if (!isInitialized) {
            throw new PluginNotSetupException();
        }

        Set<ConstraintViolation<DeliveryRequest>> violations = validator.validate(request);
        if (violations.size() > 0) {
            throw new IllegalArgumentException(violations.toString());
        }

        BaseApiResponse response = makeCall(request, HttpAgent.HTTPMethod.POST, "/api/v1/orders");

        if (!response.isOk()) {
            ApiError apiError = JSON.parse(response.getJsonBody(), ApiError.class);

            if (response.getStatusCode() == HttpStatus.SC_UNAUTHORIZED) {
                throw new PluginNotSetupException("AccessKey and Secret not setup properly");
            }

            if (response.getStatusCode() == HttpStatus.SC_GONE) {
                throw new NoPilotAvailableException();
            }

            throw new ApiCallException(apiError);
        }

        return JSON.parse(response.getJsonBody(), DeliveryOrder.class);
    }

    @Override
    public DeliveryOrder updateDelivery(DeliveryRequest updatedDeliveryRequest) throws PluginNotSetupException, IOException, ApiCallException {
        if (!isInitialized) {
            throw new PluginNotSetupException();
        }

        Set<ConstraintViolation<DeliveryRequest>> violations = validator.validate(updatedDeliveryRequest);
        if (violations.size() > 0) {
            throw new IllegalArgumentException(violations.toString());
        }

        if (TextUtils.isEmpty(updatedDeliveryRequest.getOrderCode())) {
            throw new IllegalArgumentException("Order Code cannot be blank/null if you want to update the order");
        }

        BaseApiResponse response = makeCall(updatedDeliveryRequest, HttpAgent.HTTPMethod.PUT,
                "/api/v1/orders/" + updatedDeliveryRequest.getOrderCode());

        if (!response.isOk()) {
            onUnAuthorized(response);
        }

        return JSON.parse(response.getJsonBody(), DeliveryOrder.class);

    }

    @Override
    public DeliveryOrder cancelDelivery(CancellationRequest request) throws PluginNotSetupException, ApiCallException, IOException {
        if (!isInitialized) {
            throw new PluginNotSetupException();
        }

        Set<ConstraintViolation<CancellationRequest>> violations = validator.validate(request);
        if (violations.size() > 0) {
            throw new IllegalArgumentException(violations.toString());
        }

        Map<String, String> qs = new HashMap<>();
        qs.put("is_cancelled", "1");
        qs.put("cancellation_reason", request.getReasonForCancellation());

        BaseApiResponse response = makeCall(qs, HttpAgent.HTTPMethod.PUT, "/api/v1/orders/" + request.getOrderCode());

        if (!response.isOk()) {
            onUnAuthorized(response);
        }

        return JSON.parse(response.getJsonBody(), DeliveryOrder.class);
    }

    @Override
    public DeliveryOrder getDeliveryStatus(String orderCode) throws PluginNotSetupException, IOException, ApiCallException {
        if (!isInitialized) {
            throw new PluginNotSetupException();
        }

        BaseApiResponse response = makeCall(new Object(), HttpAgent.HTTPMethod.PUT, "/api/v1/orders/" + orderCode);

        if (!response.isOk()) {
            onUnAuthorized(response);
        }

        return JSON.parse(response.getJsonBody(), DeliveryOrder.class);
    }

    @Override
    public List<DeliveryOrder> getAllDeliveries(String merchantId, Date startDate, Date endDate) throws PluginNotSetupException, IOException, ApiCallException {
        if (!isInitialized) {
            throw new PluginNotSetupException();
        }

        SimpleDateFormat format = new SimpleDateFormat("yyyy-mm-dd");

        Map<String, String> qs = new HashMap<>();
        if (startDate != null) {
            qs.put("start_date", format.format(startDate));
        }
        if (endDate != null) {
            qs.put("end_date", format.format(endDate));
        }
        if (merchantId != null) {
            qs.put("merchant_id", merchantId);
        }

        BaseApiResponse response = makeCall(qs, HttpAgent.HTTPMethod.GET, "/api/v1/orders");

        if (!response.isOk()) {
            onUnAuthorized(response);
        }

        DeliveryHistory history = JSON.parse(response.getJsonBody(), DeliveryHistory.class);
        if (history == null) {
            return new ArrayList<>();
        }

        return history.getDeliveries();
    }

    @SuppressWarnings("unchecked")
    private BaseApiResponse makeCall(Object body, HttpAgent.HTTPMethod method, String uri) throws IOException {
        Map paramMap = JSON.convert(body, Map.class);
        SortedMap<String, String> sortedParamMap = new TreeMap<String, String>(paramMap);
        String canonicalQS = Signer.canonicalize(sortedParamMap);

        String authorizationHeader = null;
        try {
            authorizationHeader = Signer.getSignedHeader(method, hostHeader, uri, credentials, canonicalQS);
        } catch (SignatureException e) {
            e.printStackTrace();
        }

        return HttpAgent.request(method, host + uri, canonicalQS, authorizationHeader);
    }

    private void onUnAuthorized(BaseApiResponse response) throws PluginNotSetupException, ApiCallException {
        ApiError apiError = JSON.parse(response.getJsonBody(), ApiError.class);

        if (response.getStatusCode() == HttpStatus.SC_UNAUTHORIZED) {
            throw new PluginNotSetupException("AccessKey and Secret not setup properly");
        }

        throw new ApiCallException(apiError);
    }
}
