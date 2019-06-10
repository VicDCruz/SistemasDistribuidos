package checking;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author daniel
 */
public class CheckSum {

    private static int add(int a, int b) {
        webserviceclients.MyWebService_Service service = new webserviceclients.MyWebService_Service();
        webserviceclients.MyWebService port = service.getMyWebServicePort();
        return port.add(a, b);
    }
    
    public static void main(String[] args) {
        for (int i = 0; i < 10; i++) {
            int r1 = (int) (Math.random() * i);
            int r2 = (int) (Math.random() * i);
            System.out.println(r1 + " + " + r2 + " = " + add(r1, r2));
        }
    }
}
