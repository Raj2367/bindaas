//
// This file was generated by the JavaTM Architecture for XML Binding(JAXB) Reference Implementation, vJAXB 2.1.10 in JDK 6 
// See <a href="http://java.sun.com/xml/jaxb">http://java.sun.com/xml/jaxb</a> 
// Any modifications to this file will be lost upon recompilation of the source schema. 
// Generated on: 2013.05.08 at 12:16:10 PM EDT 
//


package edu.emory.cci.bindaas.datasource.provider.aime4.jaxb;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlSeeAlso;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for CharacteristicQuantification complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="CharacteristicQuantification">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="annotatorConfidence" type="{uri:iso.org:21090}REAL" minOccurs="0"/>
 *         &lt;element name="characteristicQuantificationIndex" type="{uri:iso.org:21090}INT" minOccurs="0"/>
 *         &lt;element name="label" type="{uri:iso.org:21090}ST"/>
 *         &lt;element name="valueLabel" type="{uri:iso.org:21090}ST" minOccurs="0"/>
 *         &lt;element name="valueDescription" type="{uri:iso.org:21090}ST" minOccurs="0"/>
 *         &lt;element name="comment" type="{uri:iso.org:21090}ST" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "CharacteristicQuantification", namespace = "gme://caCORE.caCORE/4.4/edu.northwestern.radiology.AIM", propOrder = {
    "annotatorConfidence",
    "characteristicQuantificationIndex",
    "label",
    "valueLabel",
    "valueDescription",
    "comment"
})
@XmlSeeAlso({
    Numerical.class,
    NonQuantifiable.class,
    Quantile.class,
    Interval.class,
    Scale.class
})
public abstract class CharacteristicQuantification {

    protected REAL annotatorConfidence;
    protected INT characteristicQuantificationIndex;
    @XmlElement(required = true)
    protected ST label;
    protected ST valueLabel;
    protected ST valueDescription;
    protected ST comment;

    /**
     * Gets the value of the annotatorConfidence property.
     * 
     * @return
     *     possible object is
     *     {@link REAL }
     *     
     */
    public REAL getAnnotatorConfidence() {
        return annotatorConfidence;
    }

    /**
     * Sets the value of the annotatorConfidence property.
     * 
     * @param value
     *     allowed object is
     *     {@link REAL }
     *     
     */
    public void setAnnotatorConfidence(REAL value) {
        this.annotatorConfidence = value;
    }

    /**
     * Gets the value of the characteristicQuantificationIndex property.
     * 
     * @return
     *     possible object is
     *     {@link INT }
     *     
     */
    public INT getCharacteristicQuantificationIndex() {
        return characteristicQuantificationIndex;
    }

    /**
     * Sets the value of the characteristicQuantificationIndex property.
     * 
     * @param value
     *     allowed object is
     *     {@link INT }
     *     
     */
    public void setCharacteristicQuantificationIndex(INT value) {
        this.characteristicQuantificationIndex = value;
    }

    /**
     * Gets the value of the label property.
     * 
     * @return
     *     possible object is
     *     {@link ST }
     *     
     */
    public ST getLabel() {
        return label;
    }

    /**
     * Sets the value of the label property.
     * 
     * @param value
     *     allowed object is
     *     {@link ST }
     *     
     */
    public void setLabel(ST value) {
        this.label = value;
    }

    /**
     * Gets the value of the valueLabel property.
     * 
     * @return
     *     possible object is
     *     {@link ST }
     *     
     */
    public ST getValueLabel() {
        return valueLabel;
    }

    /**
     * Sets the value of the valueLabel property.
     * 
     * @param value
     *     allowed object is
     *     {@link ST }
     *     
     */
    public void setValueLabel(ST value) {
        this.valueLabel = value;
    }

    /**
     * Gets the value of the valueDescription property.
     * 
     * @return
     *     possible object is
     *     {@link ST }
     *     
     */
    public ST getValueDescription() {
        return valueDescription;
    }

    /**
     * Sets the value of the valueDescription property.
     * 
     * @param value
     *     allowed object is
     *     {@link ST }
     *     
     */
    public void setValueDescription(ST value) {
        this.valueDescription = value;
    }

    /**
     * Gets the value of the comment property.
     * 
     * @return
     *     possible object is
     *     {@link ST }
     *     
     */
    public ST getComment() {
        return comment;
    }

    /**
     * Sets the value of the comment property.
     * 
     * @param value
     *     allowed object is
     *     {@link ST }
     *     
     */
    public void setComment(ST value) {
        this.comment = value;
    }

}
