/**
 * @author      John Robert Tan
 * @version
 */

package com.safeway.cgct.backend;

import java.util.ArrayList;
import java.util.List;

import com.safeway.cgct.objects.Calcgroup;
import com.safeway.cgct.objects.Condition;
import com.safeway.cgct.objects.Conditionset;
import com.safeway.cgct.objects.Rule;

public class AttributeInspector {
	protected List<String> attributesA;
	protected List<String> attributesB;
	protected List<Boolean> sameAttributes=new ArrayList<Boolean>();
	protected boolean same;
	
	public boolean hasSameCalcGroupAttributes(Calcgroup cga, Calcgroup cgb) {
		sameAttributes.clear();
		same = true;
		//System.out.println(cga.getAutoRecalc() + " & " + cgb.getAutoRecalc());
		if(cga.getAutoRecalc().equals(cgb.getAutoRecalc())) {
			sameAttributes.add(true);} 
		else { sameAttributes.add(false);
			same=false; }
		if (cga.getCalcPeriod().equals(cgb.getCalcPeriod())) {
			sameAttributes.add(true); } 
		else { sameAttributes.add(false);
			same=false; }
		if (cga.getDescription().equals(cgb.getDescription())){
			sameAttributes.add(true); } 
		else {sameAttributes.add(false);
			same=false; }
		if (cga.getName().equals(cgb.getName())){
			sameAttributes.add(true); } 
		else { sameAttributes.add(false);
			same=false; }
		return same;
	}
	
	public boolean hasSameRuleAttributes(Rule ra, Rule rb) {
		sameAttributes.clear();
		same = true;
		if(ra.getClazz().equals(rb.getClazz())) {
			sameAttributes.add(true);} 
		else { sameAttributes.add(false);
			same=false; }
		if (ra.getDescription().equals(rb.getDescription())){
			sameAttributes.add(true);} 
		else {sameAttributes.add(false);
			same=false; }
		if(ra.getExecutionPoint().equals(rb.getExecutionPoint())) {
			sameAttributes.add(true);}  
		else { sameAttributes.add(false);
			same=false; }
		if(ra.getIsActive().equals(rb.getIsActive())) {
			sameAttributes.add(true);}  
		else { sameAttributes.add(false);
			same=false; }
		if(ra.getName().equals(rb.getName())) {
			sameAttributes.add(true);}  
		else { sameAttributes.add(false);
			same=false; }
		if(ra.getOverriddenConditionSetExecIsME().equals(rb.getOverriddenConditionSetExecIsME())) {
			sameAttributes.add(true);}  
		else { sameAttributes.add(false);
			same=false; }
		if(ra.getOverrideConditionSetExecIsME().equals(rb.getOverrideConditionSetExecIsME())) {
			sameAttributes.add(true);}  
		else { sameAttributes.add(false);
			same=false; }
		return same;
	}
	
	public boolean hasSameConditionSetAttributes(Conditionset csa, Conditionset csb) {
		same = true;
		sameAttributes.clear();
		if(csa.getDescription().equals(csb.getDescription())) {
			sameAttributes.add(true);} 
		else { sameAttributes.add(false);
			same=false; }
		if(csa.getNegate().equals(csb.getNegate())) {
			sameAttributes.add(true);} 
		else { sameAttributes.add(false);
			same=false; }
		return same;
	}
	
	public boolean hasSameConditionAttributes(Condition ca, Condition cb) {
		sameAttributes.clear();
		same = true;
		if(ca.getClazz().equals(cb.getClazz())) {
			sameAttributes.add(true);} 
		else { sameAttributes.add(false);
			same=false; }
		if (ca.getDescription().equals(cb.getDescription())){
			sameAttributes.add(true); } 
		else {sameAttributes.add(false);
			same=false; }
		if(ca.getName().equals(cb.getName())) {
			sameAttributes.add(true);}  
		else { sameAttributes.add(false);
			same=false; }
		if(ca.getNegate().equals(cb.getNegate())) {
			sameAttributes.add(true);} 
		else { sameAttributes.add(false);
			same=false; }
		return same;
	}
	
	public List<Boolean> getSameAttributes() {
		return sameAttributes;
	}
	
	public boolean isSame() {
		return same;
	}
	
}
