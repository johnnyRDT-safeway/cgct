/**
 * @author      John Robert Tan
 * @version
 */

package com.safeway.cgct.backend;

import java.util.List;

import javax.swing.tree.TreePath;

import com.safeway.cgct.objects.Rule;

public class RuleInspector {
	List<Rule> rulesA;
	List<Rule> rulesB;
	boolean same;
	public RuleInspector(List<Rule> rlsA, List<Rule> rlsB) {
		rulesA = rlsA;
		rulesB = rlsB;
	}
	public boolean hasSameRules() {
		same=true;
		Rule rA;
		Rule rB;
		if (rulesA.size()!=rulesB.size()) 
			same=false;
		for (int i=0;i<rulesA.size();i++) {
			rA=rulesA.get(i);
			for (int j=0;j<rulesB.size();j++) {
				rB=rulesB.get(j);
				if (!rB.isChecked()) {
					if ((rA.getName().equals(rB.getName()))&&(rA.getClazz().equals(rB.getClazz()))) {
						rB.setChecked(true);
						rA.setIndexOfCounterpart(j);
						rB.setIndexOfCounterpart(i);
						//rB.setIndex(j);
						rA.setTreePathOfCounterpart(new TreePath(rB.getPath()));
						rB.setTreePathOfCounterpart(new TreePath(rA.getPath()));
						AttributeInspector ai = new AttributeInspector();
						ConditionSetInspector csi = new ConditionSetInspector(rA.getConditionset(), rB.getConditionset());
						boolean hasSameCondSets = csi.hasSameConditionSets();
						boolean hasSameRuleAttribs = ai.hasSameRuleAttributes(rA, rB);
						rA.setHasSameAttributes(hasSameRuleAttribs);
						rB.setHasSameAttributes(hasSameRuleAttribs);
						
//						if (!(i==j)) {
//							rA.setSamePosition(false);
//							rB.setSamePosition(false);
//							same=false;
//						}
						if (hasSameCondSets && hasSameRuleAttribs){
							rA.setCompareStatus("SAME");
							rB.setCompareStatus("SAME"); 
						} else {
							rA.setCompareStatus("NOT SAME");
							rB.setCompareStatus("NOT SAME");
							same=false;
						}
						
						rA.setConditionset(csi.getConditionSetsA());
						rB.setConditionset(csi.getConditionSetsB());
						rA.setSameAttributes(ai.getSameAttributes());
						rB.setSameAttributes(ai.getSameAttributes());
						rA.setHasSameAttributes(ai.isSame());
						rB.setHasSameAttributes(ai.isSame());
						rulesB.set(j, rB);
						break;
					}
				}
			}
			//rA.setIndex(i);
			rA.setChecked(true);
			rulesA.set(i, rA);
		}
		return same;
	}
	public List<Rule> getRulesA() {
		return rulesA;
	}
	public List<Rule> getRulesB() {
		return rulesB;
	}
	public boolean isSame(){
		return same;
	}
}
