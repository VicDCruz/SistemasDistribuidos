/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package restfulservices;

import javax.ws.rs.core.Context;
import javax.ws.rs.core.UriInfo;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PUT;
import javax.ws.rs.QueryParam;

/**
 * REST Web Service
 *
 * @author daniel
 */
@Path("converterRestful")
public class ConverterRestfulResource {

    @Context
    private UriInfo context;

    /**
     * Creates a new instance of ConverterRestfulResource
     */
    public ConverterRestfulResource() {
    }

    /**
     * Retrieves representation of an instance of restfulservices.ConverterRestfulResource
     * @param currency
     * @return an instance of java.lang.String
     */
    @GET
    @Produces("text/html")
    public String getHtml(@QueryParam("currency") double currency,
            @QueryParam("from") String from,
            @QueryParam("to") String to) {
        return convert(currency, from, to);
    }

    /**
     * PUT method for updating or creating an instance of ConverterRestfulResource
     * @param content representation for the resource
     * @return an HTTP response with content of the updated or created resource.
     */
    @PUT
    @Consumes("text/html")
    public void putHtml(String content) {
    }

    private static String convert(double currency, java.lang.String from, java.lang.String to) {
        webserviceclients.Converter_Service service = new webserviceclients.Converter_Service();
        webserviceclients.Converter port = service.getConverterPort();
        return port.convert(currency, from, to);
    }
}
