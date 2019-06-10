/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package webclients;

import client.ComplexNumber;

/**
 *
 * @author daniel
 */
public class WebCalculatorClient {

    private static ComplexNumber add(client.ComplexNumber a, client.ComplexNumber b) {
        client.Calculator_Service service = new client.Calculator_Service();
        client.Calculator port = service.getCalculatorPort();
        return port.add(a, b);
    }
    
    public static void main(String[] args) {
        ComplexNumber a = new ComplexNumber();
        ComplexNumber b = new ComplexNumber();
        a.setI(1);
        b.setI(2);
        
        a.setJ(4);
        b.setJ(3);
        
        ComplexNumber c = add(a, b);
        System.out.println(c.getI() + ", " + c.getJ());
    }
}
