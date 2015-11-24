package com.opinioapp.sdk.delivery;

import com.opinioapp.sdk.delivery.reponses.*;
import com.opinioapp.sdk.delivery.requests.CancellationRequest;
import com.opinioapp.sdk.delivery.requests.DeliveryRequest;
import com.opinioapp.sdk.delivery.requests.MerchantRegistrationRequest;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.net.URL;
import java.text.SimpleDateFormat;
import java.util.List;

/**
 * Created by lokesh on 4/11/15.
 *
 * ApiClient Test Suite
 */
public class ApiClientImplTest {

    private ApiClient apiClient;

    private static int merchantId = 9;
    private static String orderCode;

    @Before
    public void setUp() throws Exception {
        apiClient = new ApiClientImpl();
        apiClient.init(EnvironmentEnum.LOCALHOST, new Credentials("12345678", "1234567890"));
    }

    @Test
    public void testRegisterMerchant() throws Exception {
        MerchantRegistrationRequest registrationRequest = new MerchantRegistrationRequest();
        registrationRequest.setName("test merchant");
        registrationRequest.setAddress("penthouse, bommanahalli");
        registrationRequest.setEmail("lokeshj@outlook.com");
        registrationRequest.setLatitude(12.500);
        registrationRequest.setLongitude(71.99);
        registrationRequest.setPhone("9916989452");

        RegisteredMerchant response = apiClient.registerMerchant(registrationRequest);
        System.out.println(response);
        Assert.assertNotNull(response);

        merchantId = response.getId();
    }

    @Test
    public void testCheckServiceability() throws Exception {
        Serviceability serviceability = apiClient.checkServiceability(merchantId);
        System.out.println(serviceability);
        Assert.assertNotNull(serviceability);
    }

    @Test
    public void testGetSupportedLocalities() throws Exception {
        SupportedLocalities localities = apiClient.getSupportedLocalities(merchantId);
        System.out.println(localities);
        Assert.assertNotNull(localities);
    }

    @Test
    public void testRequestDelivery() throws Exception {
        DeliveryRequest deliveryRequest = new DeliveryRequest();

        deliveryRequest.setMerchantId(merchantId +"");
        deliveryRequest.setAmount(300);
        deliveryRequest.setAmountToPay(300);
        deliveryRequest.setCustomerPhone("9916989452");
        deliveryRequest.setCustomerAddress("221B Baker Street");
        deliveryRequest.setCustomerLocality("London");
        deliveryRequest.setCallbackUrl(new URL("https://opinioapp.com"));

        DeliveryOrder deliveryOrder = apiClient.requestDelivery(deliveryRequest);
        orderCode = deliveryOrder.getOrderCode();
        System.out.println(deliveryOrder);
    }

    @Test
    public void testUpdateDelivery() throws Exception {
        DeliveryRequest updatedRequest = new DeliveryRequest();
        updatedRequest.setMerchantId(merchantId + "");
        updatedRequest.setOrderCode(orderCode);
        updatedRequest.setAmount(400);
        updatedRequest.setAmountToPay(300);
        updatedRequest.setCustomerPhone("9916989452");
        updatedRequest.setCustomerAddress("221B Baker Street");
        updatedRequest.setCustomerLocality("London");

        DeliveryOrder updatedDelivery = apiClient.updateDelivery(updatedRequest);
        System.out.println(updatedDelivery);
        Assert.assertNotNull(updatedDelivery);
        Assert.assertEquals(updatedDelivery.getOrderCode(), orderCode);
    }

    @Test
    public void testCancelDelivery() throws Exception {
        CancellationRequest request = new CancellationRequest();
        request.setOrderCode(orderCode);
        request.setReasonForCancellation("just testing");
        DeliveryOrder cancelledDelivery = apiClient.cancelDelivery(request);
        System.out.println(cancelledDelivery);
        Assert.assertNotNull(cancelledDelivery);
        Assert.assertEquals(cancelledDelivery.getOrderState(), DeliveryStateEnum.CANCELLED.getValue());
    }

    @Test
    public void testGetDeliveryStatus() throws Exception {
        DeliveryOrder request = apiClient.getDeliveryStatus(orderCode);
        System.out.println(request);
        Assert.assertNotNull(request);
    }

    @Test
    public void testGetAllDeliveries() throws Exception {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-mm-dd");
        List<DeliveryOrder> history = apiClient.getAllDeliveries(merchantId + "", sdf.parse("2015-10-01"), sdf.parse("2015-12-01"));
        System.out.println(history);
        Assert.assertNotNull(history);
    }
}