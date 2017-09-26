package com.safeway.cgct.objects;

import javax.swing.tree.DefaultMutableTreeNode;
import javax.swing.tree.TreePath;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
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
 *         &lt;element name="name" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="value" type="{http://www.w3.org/2001/XMLSchema}int"/>
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
    "name",
    "value"
})
public class Parameter extends DefaultMutableTreeNode {
	@XmlTransient
	int index;
	@XmlTransient
	public String compareStatus = "DEFAULT";
	@XmlTransient
	protected int indexOfCounterpart = -1;
	@XmlTransient
	protected TreePath treePathOfCounterpart;
	@XmlTransient
	protected boolean checked = false;
	

    @XmlElement(required = true)
    protected String name;
    @XmlElement(required = true)
    protected String value;

    /**
     * Gets the value of the name property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public Parameter() {
    	
    }
    
    public int getIndex(){
    	return index;
    }
    
    public void setIndex(int i){
    	index=i;
    }
    
    public String getCompareStatus() {
    	return compareStatus;
    }
    
    public void setCompareStatus(String compstatus) {
    	compareStatus=compstatus;
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
     * Gets the value of the value property.
     * 
     */
    public String getValue() {
        return value;
    }

    /**
     * Sets the value of the value property.
     * 
     */
    public void setValue(String value) {
        this.value = value;
    }
    
    /*public String toString() {
    	return ("PARAMETER: " + name);
    }*/

}