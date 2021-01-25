package com.javanbeyond.client;

import com.javanbeyond.client.generated.BinaryAddRequest;
import com.javanbeyond.client.generated.BinaryAddResponse;
import com.javanbeyond.client.generated.BinaryAdditionService;
import com.javanbeyond.client.generated.BinaryAdditionService_Service;
import com.sun.xml.ws.client.BindingProviderProperties;

import javax.xml.ws.BindingProvider;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.Map;
import java.util.function.Function;

public class BinaryAdditionServiceClient {

    private String wsdlLocation;
    private int numberOfRetries;
    private int connectionTimeOutMs;
    private int requestTimeOutMs;

    public BinaryAdditionServiceClient(String wsdlLocation) {
        this(wsdlLocation, 0);
    }

    public BinaryAdditionServiceClient(String wsdlLocation, int numberOfRetries) {
        this(wsdlLocation, numberOfRetries, 1000, 3000);
    }

    public BinaryAdditionServiceClient(String wsdlLocation, int numberOfRetries, int connectionTimeOutMs,
                                       int requestTimeOutMs) {
        this.wsdlLocation = wsdlLocation;
        this.numberOfRetries = numberOfRetries;
        this.connectionTimeOutMs = connectionTimeOutMs;
        this.requestTimeOutMs = requestTimeOutMs;
    }

    public String addWithRetry(String a, String b) throws MalformedURLException {
        final URL url = new URL(wsdlLocation);
        final BinaryAdditionService_Service service = new BinaryAdditionService_Service(url);
        final BinaryAdditionService proxy = service.getBinaryAdditionServiceSOAP();
        setConnectionTimeouts(proxy);

        final Function<BinaryAddRequest, BinaryAddResponse> operation = (request) -> proxy.binaryAdd(request);

        final BinaryAddRequest request = createBinaryAddRequest(a, b);
        final SoapEndpointRetryInvoker retryInvoker = new SoapEndpointRetryInvoker(numberOfRetries);
        final BinaryAddResponse response = retryInvoker.invoke(operation, request);
        return response.getOut();
    }

    private BinaryAddRequest createBinaryAddRequest(String a, String b) {
        final BinaryAddRequest request = new BinaryAddRequest();
        request.setA(a);
        request.setB(b);
        return request;
    }

    private void setConnectionTimeouts(BinaryAdditionService proxy) {
        Map<String, Object> requestContext = ((BindingProvider)proxy).getRequestContext();
        requestContext.put(BindingProviderProperties.REQUEST_TIMEOUT, requestTimeOutMs);
        requestContext.put(BindingProviderProperties.CONNECT_TIMEOUT, connectionTimeOutMs);
    }

}
