
package org.imie;

import javax.xml.bind.JAXBElement;
import javax.xml.bind.annotation.XmlElementDecl;
import javax.xml.bind.annotation.XmlRegistry;
import javax.xml.namespace.QName;


/**
 * This object contains factory methods for each 
 * Java content interface and Java element interface 
 * generated in the org.imie package. 
 * <p>An ObjectFactory allows you to programatically 
 * construct new instances of the Java representation 
 * for XML content. The Java representation of XML 
 * content can consist of schema derived interfaces 
 * and classes representing the binding of schema 
 * type definitions, element declarations and model 
 * groups.  Factory methods for each of these are 
 * provided in this class.
 * 
 */
@XmlRegistry
public class ObjectFactory {

    private final static QName _ExecuteService_QNAME = new QName("http://imie.org/", "executeService");
    private final static QName _ExecuteServiceResponse_QNAME = new QName("http://imie.org/", "executeServiceResponse");

    /**
     * Create a new ObjectFactory that can be used to create new instances of schema derived classes for package: org.imie
     * 
     */
    public ObjectFactory() {
    }

    /**
     * Create an instance of {@link ExecuteService }
     * 
     */
    public ExecuteService createExecuteService() {
        return new ExecuteService();
    }

    /**
     * Create an instance of {@link ExecuteServiceResponse }
     * 
     */
    public ExecuteServiceResponse createExecuteServiceResponse() {
        return new ExecuteServiceResponse();
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link ExecuteService }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://imie.org/", name = "executeService")
    public JAXBElement<ExecuteService> createExecuteService(ExecuteService value) {
        return new JAXBElement<ExecuteService>(_ExecuteService_QNAME, ExecuteService.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link ExecuteServiceResponse }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://imie.org/", name = "executeServiceResponse")
    public JAXBElement<ExecuteServiceResponse> createExecuteServiceResponse(ExecuteServiceResponse value) {
        return new JAXBElement<ExecuteServiceResponse>(_ExecuteServiceResponse_QNAME, ExecuteServiceResponse.class, null, value);
    }

}
