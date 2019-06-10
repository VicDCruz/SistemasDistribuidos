
package client;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Clase Java para add complex type.
 * 
 * <p>El siguiente fragmento de esquema especifica el contenido que se espera que haya en esta clase.
 * 
 * <pre>
 * &lt;complexType name="add">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="a" type="{http://webservices/}complexNumber" minOccurs="0"/>
 *         &lt;element name="b" type="{http://webservices/}complexNumber" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "add", propOrder = {
    "a",
    "b"
})
public class Add {

    protected ComplexNumber a;
    protected ComplexNumber b;

    /**
     * Obtiene el valor de la propiedad a.
     * 
     * @return
     *     possible object is
     *     {@link ComplexNumber }
     *     
     */
    public ComplexNumber getA() {
        return a;
    }

    /**
     * Define el valor de la propiedad a.
     * 
     * @param value
     *     allowed object is
     *     {@link ComplexNumber }
     *     
     */
    public void setA(ComplexNumber value) {
        this.a = value;
    }

    /**
     * Obtiene el valor de la propiedad b.
     * 
     * @return
     *     possible object is
     *     {@link ComplexNumber }
     *     
     */
    public ComplexNumber getB() {
        return b;
    }

    /**
     * Define el valor de la propiedad b.
     * 
     * @param value
     *     allowed object is
     *     {@link ComplexNumber }
     *     
     */
    public void setB(ComplexNumber value) {
        this.b = value;
    }

}
