package com.javanbeyond.client;

import org.junit.Ignore;
import org.junit.Test;

import javax.xml.ws.WebServiceException;

import static org.junit.Assert.assertEquals;
/*
    To run integration tests:
    1) run 'mvn clean package'
    2) copy QuickSOAPOverviewMetro/target/QuickSOAPOverviewMetro.war in ${TOMCAT_HOME}/webapps/
    3) start/restart Tomcat
    4) remove @Ignore above tests
*/
public class BinaryAdditionServiceClientTest {

    @Ignore
    @Test
    public void testBinaryAdditionWithOutRetry() throws Exception {
        final String wsdlLocation = "http://localhost:8080/QuickSOAPOverviewMetro/BinaryAdditionService";
        final BinaryAdditionServiceClient soapClient = new BinaryAdditionServiceClient(wsdlLocation, 0,
                3000, 10000);
        final String result = soapClient.addWithRetry("1011", "1000");
        System.out.println(result);
        assertEquals("10011", result);
    }

    @Ignore
    @Test(expected = WebServiceException.class)
    public void shouldRetryThreeTimesBeforeWebServiceExceptionIsThrown() throws Exception {
        final String wsdlLocation = "http://localhost:8080/QuickSOAPOverviewMetro/BinaryAdditionService";
        final BinaryAdditionServiceClient soapClient = new BinaryAdditionServiceClient(wsdlLocation, 3,
                3000, 3000);
        final String result = soapClient.addWithRetry("1011", "1000");
    }


}
