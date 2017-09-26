/**
 * @author      John Robert Tan
 * @version
 */

package com.safeway.cgct.ui;

import java.util.List;

import javax.swing.tree.DefaultMutableTreeNode;

import com.safeway.cgct.backend.CalculationGroupInspector;
import com.safeway.cgct.backend.ParameterSorter;
import com.safeway.cgct.objects.Calcgroup;
import com.safeway.cgct.objects.Condition;
import com.safeway.cgct.objects.Conditionset;
import com.safeway.cgct.objects.Parameter;
import com.safeway.cgct.objects.Rule;

public class CalculationGroupTreeBuilder {
	Calcgroup rootA;
	Calcgroup rootB;
	
	public void compareAndCreateCalcGroupTrees(Calcgroup cga, Calcgroup cgb) {
		
		CalculationGroupInspector cgi = new CalculationGroupInspector(populateNodes(cga), populateNodes(cgb));
		if (cgi.areSameCalcGroups()) {
			System.out.println("Calculation groups are identical.");
		} else {
			System.out.println("WARNING: Changes have been found between the 2 calculation groups");
		}
		rootA = cgi.getCalcgrpA();
		rootB = cgi.getCalcgrpB();
		
	}
	
	private Calcgroup populateNodes(Calcgroup cg) {
		//DefaultMutableTreeNode root = new DefaultMutableTreeNode(cg);
		for (int i=0;i<cg.getRule().size();i++) {
			if (!cg.getRule().get(i).getConditionset().isEmpty()) {
				for (int j=0;j<cg.getRule().get(i).getConditionset().size();j++) {
					if (!cg.getRule().get(i).getConditionset().get(j).getParameter().isEmpty()) {
						ParameterSorter ps=new ParameterSorter();
						//List<Parameter> sortedParams=ps.sortParameters(cg.getRule().get(i).getConditionset().get(j).getParameter());
						cg.getRule().get(i).getConditionset().get(j).setParameter(ps.sortParameters(cg.getRule().get(i).getConditionset().get(j).getParameter()));
						for (int k=0;k<cg.getRule().get(i).getConditionset().get(j).getParameter().size();k++) {
							//cg.getRule().get(i).getConditionset().get(j).getParameter().set(k, sortedParams.get(k));
							cg.getRule().get(i).getConditionset().get(j).getParameter().get(k).setIndex(k);
							cg.getRule().get(i).getConditionset().get(j).add(cg.getRule().get(i).getConditionset().get(j).getParameter().get(k));
						}
					}
					if (!cg.getRule().get(i).getConditionset().get(j).getCondition().isEmpty()) {
						for (int k=0;k<cg.getRule().get(i).getConditionset().get(j).getCondition().size();k++) {
							if (!cg.getRule().get(i).getConditionset().get(j).getCondition().get(k).getParameter().isEmpty()) {
								ParameterSorter ps=new ParameterSorter();
								List<Parameter> sortedParams=ps.sortParameters(cg.getRule().get(i).getConditionset().get(j).getCondition().get(k).getParameter());
								//cg.getRule().get(i).getConditionset().get(j).getCondition().get(k).setParameter(ps.sortParameters(cg.getRule().get(i).getConditionset().get(j).getCondition().get(k).getParameter()));
								for (int l=0;l<cg.getRule().get(i).getConditionset().get(j).getCondition().get(k).getParameter().size();l++) {
									cg.getRule().get(i).getConditionset().get(j).getCondition().get(k).getParameter().set(l, sortedParams.get(l));
									cg.getRule().get(i).getConditionset().get(j).getCondition().get(k).getParameter().get(l).setIndex(l);
									cg.getRule().get(i).getConditionset().get(j).getCondition().get(k).add(cg.getRule().get(i).getConditionset().get(j).getCondition().get(k).getParameter().get(l));
								}
							}
							cg.getRule().get(i).getConditionset().get(j).getCondition().get(k).setIndex(k);
							cg.getRule().get(i).getConditionset().get(j).add(cg.getRule().get(i).getConditionset().get(j).getCondition().get(k));
						}
					}
					cg.getRule().get(i).getConditionset().get(j).setIndex(j);
					cg.getRule().get(i).add(cg.getRule().get(i).getConditionset().get(j));
				}
			}
			cg.getRule().get(i).setIndex(i);
			cg.add(cg.getRule().get(i));
		}
		return cg;
	}
	
	public Calcgroup getRootNodeA() {
		return rootA;
	}
	
	public Calcgroup getRootNodeB() {
		return rootB;
	}
	
	/*for (Rule currentRule: cg.getRule()) {
		if (!currentRule.getConditionset().isEmpty()) {
			for (Conditionset currentConditionSet: currentRule.getConditionset()) {
				if (!currentConditionSet.getParameter().isEmpty()) {
					ParameterSorter ps=new ParameterSorter();
					currentConditionSet.setParameter(ps.sortParameters(currentConditionSet.getParameter()));
					for (Parameter currentParameter: currentConditionSet.getParameter()) {
						currentConditionSet.add(currentParameter);
					}
				}
				if (!currentConditionSet.getCondition().isEmpty()) {
					for (Condition currentCondition: currentConditionSet.getCondition()) {
						if (!currentCondition.getParameter().isEmpty()) {
							for (Parameter currentParameter: currentCondition.getParameter()) {
								currentCondition.add(currentParameter);
							}
						}
						currentConditionSet.add(currentCondition);
					}
					currentRule.add(currentConditionSet);
				}
			}
			cg.add(currentRule);
		}
	}*/
}
