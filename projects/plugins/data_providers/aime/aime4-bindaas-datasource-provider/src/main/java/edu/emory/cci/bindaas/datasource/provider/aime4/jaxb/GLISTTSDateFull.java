//
// This file was generated by the JavaTM Architecture for XML Binding(JAXB) Reference Implementation, vJAXB 2.1.10 in JDK 6 
// See <a href="http://java.sun.com/xml/jaxb">http://java.sun.com/xml/jaxb</a> 
// Any modifications to this file will be lost upon recompilation of the source schema. 
// Generated on: 2013.05.08 at 12:16:10 PM EDT 
//


package edu.emory.cci.bindaas.datasource.provider.aime4.jaxb;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for GLIST_TS.Date.Full complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="GLIST_TS.Date.Full">
 *   &lt;complexContent>
 *     &lt;extension base="{uri:iso.org:21090}ANY">
 *       &lt;sequence>
 *         &lt;element name="head" type="{uri:iso.org:21090}TS.Date.Full" minOccurs="0"/>
 *         &lt;element name="increment" type="{uri:iso.org:21090}QTY" minOccurs="0"/>
 *       &lt;/sequence>
 *       &lt;attribute name="denominator" type="{http://www.w3.org/2001/XMLSchema}int" />
 *       &lt;attribute name="period" type="{http://www.w3.org/2001/XMLSchema}int" />
 *     &lt;/extension>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "GLIST_TS.Date.Full", propOrder = {
    "head",
    "increment"
})
public class GLISTTSDateFull
    extends ANY
{

    protected TSDateFull head;
    protected QTY increment;
    @XmlAttribute
    protected Integer denominator;
    @XmlAttribute
    protected Integer period;

    /**
     * Gets the value of the head property.
     * 
     * @return
     *     possible object is
     *     {@link TSDateFull }
     *     
     */
    public TSDateFull getHead() {
        return head;
    }

    /**
     * Sets the value of the head property.
     * 
     * @param value
     *     allowed object is
     *     {@link TSDateFull }
     *     
     */
    public void setHead(TSDateFull value) {
        this.head = value;
    }

    /**
     * Gets the value of the increment property.
     * 
     * @return
     *     possible object is
     *     {@link QTY }
     *     
     */
    public QTY getIncrement() {
        return increment;
    }

    /**
     * Sets the value of the increment property.
     * 
     * @param value
     *     allowed object is
     *     {@link QTY }
     *     
     */
    public void setIncrement(QTY value) {
        this.increment = value;
    }

    /**
     * Gets the value of the denominator property.
     * 
     * @return
     *     possible object is
     *     {@link Integer }
     *     
     */
    public Integer getDenominator() {
        return denominator;
    }

    /**
     * Sets the value of the denominator property.
     * 
     * @param value
     *     allowed object is
     *     {@link Integer }
     *     
     */
    public void setDenominator(Integer value) {
        this.denominator = value;
    }

    /**
     * Gets the value of the period property.
     * 
     * @return
     *     possible object is
     *     {@link Integer }
     *     
     */
    public Integer getPeriod() {
        return period;
    }

    /**
     * Sets the value of the period property.
     * 
     * @param value
     *     allowed object is
     *     {@link Integer }
     *     
     */
    public void setPeriod(Integer value) {
        this.period = value;
    }

}
