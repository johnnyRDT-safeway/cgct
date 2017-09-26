package com.safeway.cgct.objects;

import java.util.ArrayList;
import java.util.List;

import javax.swing.tree.DefaultMutableTreeNode;
import javax.swing.tree.TreePath;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlTransient;
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
 *         &lt;element name="parameter" maxOccurs="unbounded">
 *           &lt;complexType>
 *             &lt;complexContent>
 *               &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *                 &lt;sequence>
 *                   &lt;element name="name" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *                   &lt;element name="value" type="{http://www.w3.org/2001/XMLSchema}int"/>
 *                 &lt;/sequence>
 *               &lt;/restriction>
 *             &lt;/complexContent>
 *           &lt;/complexType>
 *         &lt;/element>
 *         &lt;element name="condition">
 *           &lt;complexType>
 *             &lt;complexContent>
 *               &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *                 &lt;attribute name="class" type="{http://www.w3.org/2001/XMLSchema}string" />
 *                 &lt;attribute name="name" type="{http://www.w3.org/2001/XMLSchema}string" />
 *                 &lt;attribute name="negate" type="{http://www.w3.org/2001/XMLSchema}string" />
 *               &lt;/restriction>
 *             &lt;/complexContent>
 *           &lt;/complexType>
 *         &lt;/element>
 *       &lt;/sequence>
 *       &lt;attribute name="description" type="{http://www.w3.org/2001/XMLSchema}string" />
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "", propOrder = {
    "parameter",
    "condition"
})
public class Conditionset extends DefaultMutableTreeNode {

	@XmlTransient
	protected int index;
	@XmlTransient
	protected int indexOfCounterpart = -1;
	@XmlTransient
	protected TreePath treePathOfCounterpart;
	@XmlTransient
	protected boolean checked = false;
	@XmlTransient 
	protected List<Boolean> sameAttributes;
	@XmlTransient
	protected boolean hasSameAttributes;
	@XmlTransient
	protected String compareStatus = "DEFAULT";
	
    @XmlElement(required = true)
    protected List<Parameter> parameter;
    @XmlElement(required = true)
    protected List<Condition> condition;
    @XmlAttribute(name = "description")
    protected String description;
    @XmlAttribute(name = "negate")
    protected String negate;
   
    public Conditionset() {
    	
    }
    
    public int getIndex(){
    	return index;
    }
    
    public void setIndex(int i){
    	index=i;
    }
    
    public int getIndexOfCounterpart() {
		return indexOfCounterpart;
	}


	public void setIndexOfCounterpart(int indexOfCounterpart) {
		this.indexOfCounterpart = indexOfCounterpart;
	}
    
    public TreePath getTreePathOfCounterpart() {
    	return treePathOfCounterpart;
    }
    
    public void setTreePathOfCounterpart(TreePath tp) {
    	this.treePathOfCounterpart=tp;
    }

	public boolean isChecked() {
		return checked;
	}


	public void setChecked(boolean checked) {
		this.checked = checked;
	}
	
	public List<Boolean> getSameAttributes() {
		if (sameAttributes==null) 
			sameAttributes = new ArrayList<Boolean>();
		return sameAttributes;
	}	
	
	public void setSameAttributes(List<Boolean> sameAttribs) {
		sameAttributes=sameAttribs;
	}
	
	public boolean hasSameAttributes() {
    	return hasSameAttributes;
    }
    
    public void setHasSameAttributes(boolean hsa) {
    	hasSameAttributes = hsa;
    }
	
	public String getCompareStatus() {
		return compareStatus;
	}
	
	public void setCompareStatus(String compstatus) {
		compareStatus=compstatus;
	}
    
    /**
     * Gets the value of the parameter property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the parameter property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getParameter().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link Calcgroup.Rule.Conditionset.Parameter }
     * 
     * 
     */
    public List<Parameter> getParameter() {
        if (parameter == null) {
            parameter = new ArrayList<Parameter>();
        }
        return this.parameter;
    }
    
    public void setParameter(List<Parameter> params) {
        parameter = params;
    }

    /**
     * Gets the value of the condition property.
     * 
     * @return
     *     possible object is
     *     {@link Calcgroup.Rule.Conditionset.Condition }
     *     
     */
    public List<Condition> getCondition() {
    	if (condition == null) {
            condition = new ArrayList<Condition>();
        }
        return condition;
    }

    /**
     * Sets the value of the condition property.
     * 
     * @param value
     *     allowed object is
     *     {@link Calcgroup.Rule.Conditionset.Condition }
     *     
     */
    public void setCondition(List<Condition> value) {
        this.condition = value;
    }

    /**
     * Gets the value of the description property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getDescription() {
    	if(description != null && !description.isEmpty())
    		return description;
    	else
    		return "";
    }

    /**
     * Sets the value of the description property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setDescription(String value) {
        this.description = value;
    }
    
    /**
     * Gets the value of the negate property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getNegate() {
    	if(negate != null && !negate.isEmpty())
    		return negate;
    	else
    		return "";
    }

    /**
     * Sets the value of the negate property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setNegate(String value) {
        this.negate = value;
    }

    //@override
    /*public String toString() {
    	return ("CONDITION SET: " + description);
    }*/
}
