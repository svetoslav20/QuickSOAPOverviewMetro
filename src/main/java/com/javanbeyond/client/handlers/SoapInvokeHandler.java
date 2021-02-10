package com.javanbeyond.client.handlers;

import java.util.function.Function;

public abstract class SoapInvokeHandler {
	private SoapInvokeHandler chain;

	/*
	 * This method is used to create a chain
	 */
	public SoapInvokeHandler chainWith(SoapInvokeHandler handler) {
		this.chain = handler;
		return chain;
	}

	/**
	 * This method has to be implementation for the operation
	 */
	public abstract <Rs, Rq> Rs execute(Function<Rq, Rs> operation, Rq request, Rs response);

	/*
	 * Trigger the next operation defined in the chain
	 */
	protected <Rs, Rq> Rs executeNext(Function<Rq, Rs> operation, Rq request, Rs response) {
		if(chain == null) {
			return response;
		}
		return chain.execute(operation, request, response);
	}

}
