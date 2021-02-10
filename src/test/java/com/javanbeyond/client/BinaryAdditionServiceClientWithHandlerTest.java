package com.javanbeyond.client;

import static org.junit.Assert.assertEquals;

import javax.xml.ws.WebServiceException;

import org.junit.Ignore;
import org.junit.Test;

/*
    To run integration tests:
    1) run 'mvn clean package'
    2) copy QuickSOAPOverviewMetro/target/QuickSOAPOverviewMetro.war in ${TOMCAT_HOME}/webapps/
    3) start/restart Tomcat
    4) remove @Ignore above tests
*/
public class BinaryAdditionServiceClientWithHandlerTest {

	// @Ignore
	@Test
	public void testBinaryAdditionWithOutRetryAndLogRequest() throws Exception {
		final String wsdlLocation = "http://localhost/QuickSOAPOverviewMetro/BinaryAdditionService";
		final BinaryAdditionServiceClient soapClient = new BinaryAdditionServiceClient(wsdlLocation, 0, 3000, 10000);
		final String result = soapClient.addWithRetryAndLogRequest("1011", "1000");
		System.out.println(result);
		assertEquals("10011", result);
	}

	@Ignore
	@Test(expected = WebServiceException.class)
	public void shouldThrowWebServiceExceptionIfNumberOfRetriesIsZeroAndRemoteServerConnectionTimesOut() throws Exception {
		final String wsdlLocation = "http://localhost/QuickSOAPOverviewMetro/BinaryAdditionService";
		final BinaryAdditionServiceClient soapClient = new BinaryAdditionServiceClient(wsdlLocation, 0, 3000, 3000);
		soapClient.addWithRetryAndLogRequest("1011", "1000");
	}

	@Test(expected = WebServiceException.class)
	public void shouldRetryThreeTimesBeforeWebServiceExceptionIsThrown() throws Exception {
		final String wsdlLocation = "http://localhost/QuickSOAPOverviewMetro/BinaryAdditionService";
		final BinaryAdditionServiceClient soapClient = new BinaryAdditionServiceClient(wsdlLocation, 3, 3000, 3000);
		soapClient.addWithRetryAndLogRequest("1011", "1000");
	}

}
