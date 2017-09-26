/**
 * @author      John Robert Tan
 * @version
 */

package com.safeway.cgct.backend;

import com.safeway.cgct.objects.Calcgroup;

public class CalculationGroupInspector {
	Calcgroup calcgrpA;
	Calcgroup calcgrpB;
	boolean same;
	public CalculationGroupInspector(Calcgroup cga, Calcgroup cgb) {
		calcgrpA = cga;
		calcgrpB = cgb;
	}
	public boolean areSameCalcGroups(){
		same = true;
		if (calcgrpA.getName().equals(calcgrpB.getName())) {
			AttributeInspector ai=new AttributeInspector();
			RuleInspector ri = new RuleInspector(calcgrpA.getRule(), calcgrpB.getRule());
			boolean hasSameCGAttribs=ai.hasSameCalcGroupAttributes(calcgrpA, calcgrpB);
			calcgrpA.setHasSameAttributes(hasSameCGAttribs);
			calcgrpB.setHasSameAttributes(hasSameCGAttribs);
			boolean hasSameRules=ri.hasSameRules();
			if (hasSameCGAttribs&&hasSameRules){
				calcgrpA.setCompareStatus("SAME");
				calcgrpB.setCompareStatus("SAME");
			}else {
				calcgrpA.setCompareStatus("NOT SAME");
				calcgrpB.setCompareStatus("NOT SAME");
				same=false;
			}
			calcgrpA.setRule(ri.getRulesA());
			calcgrpB.setRule(ri.getRulesB());
			calcgrpA.setSameAttributes(ai.getSameAttributes());
			calcgrpB.setSameAttributes(ai.getSameAttributes());
			calcgrpA.setHasSameAttributes(ai.isSame());
			calcgrpB.setHasSameAttributes(ai.isSame());
		} else {
			//showMessageDialog("ERROR: unable to compare different calcgroups");
			same=false;
			System.out.println("ERROR: unable to compare different calcgroups");
		}
		return same;
	}
	public Calcgroup getCalcgrpA(){
		return calcgrpA;
	}
	public Calcgroup getCalcgrpB(){
		return calcgrpB;
	}
}
