/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package clientejavaappws;

/**
 *
 * @author daniel
 */
public class ClienteJavaAppWS {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        System.out.println(add(5, 9));
    }

    private static int add(int a, int b) {
        webserviceclient1.MyWebService_Service service = new webserviceclient1.MyWebService_Service();
        webserviceclient1.MyWebService port = service.getMyWebServicePort();
        return port.add(a, b);
    }
    
}
