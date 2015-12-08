package com.opinioapp.sdk.delivery;

import com.opinioapp.sdk.delivery.exceptions.*;
import com.opinioapp.sdk.delivery.reponses.DeliveryOrder;
import com.opinioapp.sdk.delivery.reponses.RegisteredMerchant;
import com.opinioapp.sdk.delivery.reponses.Serviceability;
import com.opinioapp.sdk.delivery.reponses.SupportedLocalities;
import com.opinioapp.sdk.delivery.requests.CancellationRequest;
import com.opinioapp.sdk.delivery.requests.DeliveryRequest;
import com.opinioapp.sdk.delivery.requests.MerchantRegistrationRequest;

import java.io.IOException;
import java.util.Date;
import java.util.List;

/**
 * Created by lokesh on 4/11/15.
 *
 * Client for Opinio delivery api
 */
public interface ApiClient {

    void init(EnvironmentEnum environment, Credentials credentials) throws KeysNotFoundException;

    RegisteredMerchant registerMerchant(MerchantRegistrationRequest request) throws PluginNotSetupException, MerchantAlreadyRegisteredException, IOException, ApiCallException;

    Serviceability checkServiceability(String merchantId) throws PluginNotSetupException, IOException, ApiCallException;

    SupportedLocalities getSupportedLocalities(String merchantId) throws PluginNotSetupException, IOException, ApiCallException;

    DeliveryOrder requestDelivery(DeliveryRequest request) throws PluginNotSetupException, IOException, ApiCallException, NoPilotAvailableException;

    DeliveryOrder getDeliveryStatus(String orderCode) throws PluginNotSetupException, IOException, ApiCallException;

    DeliveryOrder updateDelivery(DeliveryRequest request) throws PluginNotSetupException, IOException, ApiCallException;

    DeliveryOrder cancelDelivery(CancellationRequest request) throws PluginNotSetupException, ApiCallException, IOException;

    List<DeliveryOrder> getAllDeliveries(String merchantId, Date startDate, Date endDate) throws PluginNotSetupException, IOException, ApiCallException;
}
