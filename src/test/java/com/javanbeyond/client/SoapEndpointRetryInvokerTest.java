package com.javanbeyond.client;

import org.junit.Test;

import javax.xml.ws.WebServiceException;
import java.util.function.Function;

import static org.junit.Assert.assertEquals;

public class SoapEndpointRetryInvokerTest {

    @Test
    public void testWithZeroRetries() {
        final SoapEndpointRetryInvoker invoker = new SoapEndpointRetryInvoker(0);
        final Function<String, Integer> operation = x -> x.length();
        final int response = invoker.invoke(operation, "test");
        assertEquals(4, response);
    }

    @Test
    public void shouldNotRetryIfNoTimeOutExceptionIsThrown() {
        final int numberOfRetries = 3;
        final SoapEndpointRetryInvoker invoker = new SoapEndpointRetryInvoker(numberOfRetries);
        final Function<String, Integer> operation = x -> { throw new WebServiceException("Some exception"); };
        try {
            invoker.invoke(operation, "test");
        } catch (WebServiceException ex) {
            assertEquals(numberOfRetries, invoker.getRemainingNumberOfRetries());
        }
    }

    @Test
    public void shouldRetryIfTimeOutExceptionIsThrown() {
        final int numberOfRetries = 3;
        final SoapEndpointRetryInvoker invoker = new SoapEndpointRetryInvoker(numberOfRetries);
        final Function<String, Integer> operation = x -> {
            throw new WebServiceException("java.net.SocketTimeoutException: Read timed out");
        };

        try {
            invoker.invoke(operation, "test");
        } catch (WebServiceException ex) {
            assertEquals(0, invoker.getRemainingNumberOfRetries());
        }
    }
}
