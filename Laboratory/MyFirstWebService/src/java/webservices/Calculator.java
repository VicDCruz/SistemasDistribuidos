/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package webservices;

import javax.jws.WebService;
import javax.jws.WebMethod;
import javax.jws.WebParam;
import javax.ejb.Stateless;
import objetos.ComplexNumber;

/**
 *
 * @author daniel
 */
@WebService(serviceName = "Calculator")
@Stateless()
public class Calculator {

    /**
     * Web service operation
     */
    @WebMethod(operationName = "add")
    public ComplexNumber add(@WebParam(name = "a") ComplexNumber a, @WebParam(name = "b") ComplexNumber b) {
        ComplexNumber cn = new ComplexNumber();
        cn.setI(a.getI() + b.getI());
        cn.setJ(a.getJ() + b.getJ());
        return null;
    }
}
