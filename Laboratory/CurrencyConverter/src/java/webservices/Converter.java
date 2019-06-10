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

/**
 *
 * @author daniel
 */
@WebService(serviceName = "Converter")
@Stateless()
public class Converter {

    /**
     * Web service operation
     */
    @WebMethod(operationName = "convert")
    public String convert(@WebParam(name = "currency") double currency, @WebParam(name = "from") String from, @WebParam(name = "to") String to) {
        String output = "Something went wrong";
        if (from.equals(to))
            output = currency + "";
        else if (from.equals("USD")) {
            if (to.equals("MXN"))
                output = currency * 20.01 + "";
        }
        else if (from.equals("MXN")) {
            if (to.equals("USD"))
                output = currency / 20.01 + "";
        }
        return output;
    }
}
