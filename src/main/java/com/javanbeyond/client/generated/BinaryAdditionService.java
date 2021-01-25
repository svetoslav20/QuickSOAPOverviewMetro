
package com.javanbeyond.client.generated;

import javax.jws.WebMethod;
import javax.jws.WebParam;
import javax.jws.WebResult;
import javax.jws.WebService;
import javax.jws.soap.SOAPBinding;
import javax.xml.bind.annotation.XmlSeeAlso;


/**
 * This class was generated by the JAX-WS RI.
 * JAX-WS RI 2.3.2
 * Generated source version: 2.2
 * 
 */
@WebService(name = "BinaryAdditionService", targetNamespace = "http://www.javanbeyond.com/BinaryAdditionService/")
@SOAPBinding(parameterStyle = SOAPBinding.ParameterStyle.BARE)
@XmlSeeAlso({
    ObjectFactory.class
})
public interface BinaryAdditionService {


    /**
     * 
     * @param parameters
     * @return
     *     returns com.javanbeyond.client.generated.BinaryAddResponse
     */
    @WebMethod(action = "http://www.javanbeyond.com/BinaryAdditionService/binaryAdd")
    @WebResult(name = "binaryAddResponse", targetNamespace = "http://www.javanbeyond.com/BinaryAdditionService/", partName = "parameters")
    public BinaryAddResponse binaryAdd(
        @WebParam(name = "binaryAddRequest", targetNamespace = "http://www.javanbeyond.com/BinaryAdditionService/", partName = "parameters")
        BinaryAddRequest parameters);

}
