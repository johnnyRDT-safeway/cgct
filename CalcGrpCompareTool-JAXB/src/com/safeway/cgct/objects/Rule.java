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
 *         &lt;element name="conditionset">
 *           &lt;complexType>
 *             &lt;complexContent>
 *               &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *                 &lt;sequence>
 *                   &lt;element name="parameter" maxOccurs="unbounded">
 *                     &lt;complexType>
 *                       &lt;complexContent>
 *                         &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *                           &lt;sequence>
 *                             &lt;element name="name" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *                             &lt;element name="value" type="{http://www.w3.org/2001/XMLSchema}int"/>
 *                           &lt;/sequence>
 *                         &lt;/restriction>
 *                       &lt;/complexContent>
 *                     &lt;/complexType>
 *                   &lt;/element>
 *                   &lt;element name="condition">
 *                     &lt;complexType>
 *                       &lt;complexContent>
 *                         &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *                           &lt;attribute name="class" type="{http://www.w3.org/2001/XMLSchema}string" />
 *                           &lt;attribute name="name" type="{http://www.w3.org/2001/XMLSchema}string" />
 *                           &lt;attribute name="negate" type="{http://www.w3.org/2001/XMLSchema}string" />
 *                         &lt;/restriction>
 *                       &lt;/complexContent>
 *                     &lt;/complexType>
 *                   &lt;/element>
 *                 &lt;/sequence>
 *                 &lt;attribute name="description" type="{http://www.w3.org/2001/XMLSchema}string" />
 *               &lt;/restriction>
 *             &lt;/complexContent>
 *           &lt;/complexType>
 *         &lt;/element>
 *       &lt;/sequence>
 *       &lt;attribute name="class" type="{http://www.w3.org/2001/XMLSchema}string" />
 *       &lt;attribute name="executionPoint" type="{http://www.w3.org/2001/XMLSchema}int" />
 *       &lt;attribute name="isActive" type="{http://www.w3.org/2001/XMLSchema}string" />
 *       &lt;attribute name="name" type="{http://www.w3.org/2001/XMLSchema}string" />
 *       &lt;attribute name="overriddenConditionSetExecIsME" type="{http://www.w3.org/2001/XMLSchema}string" />
 *       &lt;attribute name="overrideConditionSetExecIsME" type="{http://www.w3.org/2001/XMLSchema}string" />
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */

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


@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "", propOrder = {
    "conditionset"
})
public class Rule extends DefaultMutableTreeNode {

	/*index of element if it is present in the other calcgroup*/
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
    protected List<Conditionset> conditionset;
    @XmlAttribute(name = "class")
    protected String clazz;
    @XmlAttribute(name = "description")
    protected String description;
    @XmlAttribute(name = "executionPoint")
    protected Integer executionPoint;
    @XmlAttribute(name = "isActive")
    protected String isActive;
    @XmlAttribute(name = "name")
    protected String name;
    @XmlAttribute(name = "overriddenConditionSetExecIsME")
    protected String overriddenConditionSetExecIsME;
    @XmlAttribute(name = "overrideConditionSetExecIsME")
    protected String overrideConditionSetExecIsME;
    
    
    public Rule() {
    	
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
     * Gets the value of the conditionset property.
     * 
     * @return
     *     possible object is
     *     {@link Calcgroup.Rule.Conditionset }
     *     
     */
    public List<Conditionset> getConditionset() {
    	if (conditionset == null) {
    		conditionset = new ArrayList<Conditionset>();
        }
        return this.conditionset;
    }

    /**
     * Sets the value of the conditionset property.
     * 
     * @param value
     *     allowed object is
     *     {@link Calcgroup.Rule.Conditionset }
     *     
     */
    public void setConditionset(List<Conditionset> value) {
        this.conditionset = value;
    }

    /**
     * Gets the value of the clazz property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getClazz() {
        return clazz;
    }

    /**
     * Sets the value of the clazz property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setClazz(String value) {
        this.clazz = value;
    }
    
    public String getDescription() {
    	if(description != null && !description.isEmpty())
    		return description;
    	else
    		return "";
    }

    public void setDescription(String value) {
        this.description = value;
    }

    /**
     * Gets the value of the executionPoint property.
     * 
     * @return
     *     possible object is
     *     {@link Integer }
     *     
     */
    public Integer getExecutionPoint() {
        return executionPoint;
    }

    /**
     * Sets the value of the executionPoint property.
     * 
     * @param value
     *     allowed object is
     *     {@link Integer }
     *     
     */
    public void setExecutionPoint(Integer value) {
        this.executionPoint = value;
    }

    /**
     * Gets the value of the isActive property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getIsActive() {
        return isActive;
    }

    /**
     * Sets the value of the isActive property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setIsActive(String value) {
        this.isActive = value;
    }

    /**
     * Gets the value of the name property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getName() {
        return name;
    }

    /**
     * Sets the value of the name property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setName(String value) {
        this.name = value;
    }

    /**
     * Gets the value of the overriddenConditionSetExecIsME property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getOverriddenConditionSetExecIsME() {
        return overriddenConditionSetExecIsME;
    }

    /**
     * Sets the value of the overriddenConditionSetExecIsME property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setOverriddenConditionSetExecIsME(String value) {
        this.overriddenConditionSetExecIsME = value;
    }

    /**
     * Gets the value of the overrideConditionSetExecIsME property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getOverrideConditionSetExecIsME() {
        return overrideConditionSetExecIsME;
    }

    /**
     * Sets the value of the overrideConditionSetExecIsME property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setOverrideConditionSetExecIsME(String value) {
        this.overrideConditionSetExecIsME = value;
    }

    /*public String toString() {
    	return ("RULE: " +name);
    }*/
}