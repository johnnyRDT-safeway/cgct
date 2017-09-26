/**
 * @author      John Robert Tan
 * @version
 */

package com.safeway.cgct.backend;

import java.util.List;

import javax.swing.tree.TreePath;

import com.safeway.cgct.objects.Condition;

public class ConditionInspector {
	List<Condition> conditionsA;
	List<Condition> conditionsB;
	boolean same;
	
	public ConditionInspector(List<Condition> condsA, List<Condition> condsB) {
		conditionsA=condsA;
		conditionsB=condsB;
	}
	
	public boolean hasSameConditions() {
		same=true;
		Condition cA;
		Condition cB;
		if (conditionsA.size()!=conditionsB.size())
			same=false;
		for (int i=0;i<conditionsA.size();i++){
			cA=conditionsA.get(i);
			for (int j=0;j<conditionsB.size();j++) {
				cB=conditionsB.get(j);
				if ((!cA.isChecked())&&(cA.getName().equals(cB.getName()))&&(cA.getClazz().equals(cB.getClazz()))) {
					cA.setIndexOfCounterpart(j);
					cB.setIndexOfCounterpart(i);
					//cB.setIndex(j);
					cA.setTreePathOfCounterpart(new TreePath(cB.getPath()));
					cB.setTreePathOfCounterpart(new TreePath(cA.getPath()));
					cB.setChecked(true);
					AttributeInspector ai=new AttributeInspector();
					ParameterInspector pi = new ParameterInspector(cA.getParameter(), cB.getParameter());
					boolean hasSameConditionAttribs = ai.hasSameConditionAttributes(cA, cB);
					boolean hasSameParams = pi.hasSameParameters();
					cA.setHasSameAttributes(hasSameConditionAttribs);
//					if (!(i==j)) {
//						cA.setSamePosition(false);
//						cB.setSamePosition(false);
//						same=false;
//					}
					if (hasSameConditionAttribs&&hasSameParams) {
						cA.setCompareStatus("SAME");
						cB.setCompareStatus("SAME");
					} else {
						cA.setCompareStatus("NOT SAME");
						cB.setCompareStatus("NOT SAME");
						same=false;
					}
					cA.setParameter(pi.getParametersA());
					cB.setParameter(pi.getParametersB());
					cA.setSameAttributes(ai.getSameAttributes());
					cB.setSameAttributes(ai.getSameAttributes());
					cA.setHasSameAttributes(ai.isSame());
					cB.setHasSameAttributes(ai.isSame());
					conditionsB.set(j, cB);
					break;
				}
			}
			//cA.setIndex(i);
			cA.setChecked(true);
			conditionsA.set(i,cA);
		}
		return same;
	}
	public List<Condition> getConditionsA(){
		return conditionsA;
	}
	public List<Condition> getConditionsB(){
		return conditionsB;
	}
}
