package com.javanbeyond.client.handlers;

import java.util.function.Function;

public class LoggerHandler extends SoapInvokeHandler {

	@Override
	public <Rs, Rq> Rs execute(Function<Rq, Rs> operation, Rq request, Rs response) {
		// do its operation
		System.out.println("I am a Logger Handler :: " + request);
		return executeNext(operation, request, response);
	}
}
