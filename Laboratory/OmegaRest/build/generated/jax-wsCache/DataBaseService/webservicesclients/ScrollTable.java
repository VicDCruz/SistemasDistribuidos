
package webservicesclients;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Clase Java para scrollTable complex type.
 * 
 * <p>El siguiente fragmento de esquema especifica el contenido que se espera que haya en esta clase.
 * 
 * <pre>
 * &lt;complexType name="scrollTable">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="database" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="table" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="start" type="{http://www.w3.org/2001/XMLSchema}int"/>
 *         &lt;element name="length" type="{http://www.w3.org/2001/XMLSchema}int"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "scrollTable", propOrder = {
    "database",
    "table",
    "start",
    "length"
})
public class ScrollTable {

    protected String database;
    protected String table;
    protected int start;
    protected int length;

    /**
     * Obtiene el valor de la propiedad database.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getDatabase() {
        return database;
    }

    /**
     * Define el valor de la propiedad database.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setDatabase(String value) {
        this.database = value;
    }

    /**
     * Obtiene el valor de la propiedad table.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getTable() {
        return table;
    }

    /**
     * Define el valor de la propiedad table.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setTable(String value) {
        this.table = value;
    }

    /**
     * Obtiene el valor de la propiedad start.
     * 
     */
    public int getStart() {
        return start;
    }

    /**
     * Define el valor de la propiedad start.
     * 
     */
    public void setStart(int value) {
        this.start = value;
    }

    /**
     * Obtiene el valor de la propiedad length.
     * 
     */
    public int getLength() {
        return length;
    }

    /**
     * Define el valor de la propiedad length.
     * 
     */
    public void setLength(int value) {
        this.length = value;
    }

}
