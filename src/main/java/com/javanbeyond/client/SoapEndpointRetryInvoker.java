package com.javanbeyond.client;

import javax.xml.ws.WebServiceException;
import java.util.function.Function;

public class SoapEndpointRetryInvoker {

    private int numberOfRetries;

    public SoapEndpointRetryInvoker(int numberOfRetries) {
        this.numberOfRetries = numberOfRetries;
    }

    public <Rs, Rq> Rs invoke(Function<Rq, Rs> operation, Rq request) {
        Rs response;
        try {
            response = operation.apply(request);
        } catch (WebServiceException ex) {
            if (isTimeOutException(ex)) {
                if (numberOfRetries > 0) {
                    System.out.println("Retrying numberOfRetries=" + numberOfRetries);
                    sleep();
                    numberOfRetries--;
                    response = invoke(operation, request);
                } else {
                    throw ex;
                }
            } else {
                throw ex;
            }
        }
        return response;
    }

    private boolean isTimeOutException(WebServiceException ex) {
        return ex.getMessage().contains("java.net.SocketTimeoutException: Read timed out");
    }

    private void sleep() {
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public int getRemainingNumberOfRetries() {
        return numberOfRetries;
    }
}
