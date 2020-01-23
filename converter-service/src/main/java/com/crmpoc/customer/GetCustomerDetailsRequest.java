
package com.crmpoc.customer;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for anonymous complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType>
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="tckn" type="{http://www.w3.org/2001/XMLSchema}long"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "", propOrder = {
    "tckn"
})
@XmlRootElement(name = "GetCustomerDetailsRequest")
public class GetCustomerDetailsRequest {

    protected long tckn;

    /**
     * Gets the value of the tckn property.
     * 
     */
    public long getTckn() {
        return tckn;
    }

    /**
     * Sets the value of the tckn property.
     * 
     */
    public void setTckn(long value) {
        this.tckn = value;
    }

}
