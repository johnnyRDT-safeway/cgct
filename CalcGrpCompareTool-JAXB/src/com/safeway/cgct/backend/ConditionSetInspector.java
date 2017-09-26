/**
 * @author      John Robert Tan
 * @version
 */

package com.safeway.cgct.backend;

import java.util.List;

import javax.swing.tree.TreePath;

import com.safeway.cgct.objects.Conditionset;
import com.safeway.cgct.objects.Rule;

public class ConditionSetInspector {
	List<Conditionset> conditionSetsA;
	List<Conditionset> conditionSetsB;
	boolean same;
	
	public ConditionSetInspector(List<Conditionset> condsetsA, List<Conditionset> condsetsB) {
		conditionSetsA = condsetsA;
		conditionSetsB = condsetsB;
	}
	public boolean hasSameConditionSets() {
		same=true;
		Conditionset csA;
		Conditionset csB;
		if (conditionSetsA.size()!=conditionSetsB.size())
			same=false;
		for (int i=0;i<conditionSetsA.size();i++) {
			csA=conditionSetsA.get(i);
			for(int j=0;j<conditionSetsB.size();j++) {
				csB=conditionSetsB.get(j);
				
				//if ((!csB.isChecked())&&(csA.getDescription().equals(csB.getDescription()))){
				if ((!csB.isChecked())&&(matchingConditionSets(csA, csB))){
					csB.setChecked(true);
					csA.setIndexOfCounterpart(j);
					csB.setIndexOfCounterpart(i);
					//csB.setIndex(j);
					csA.setTreePathOfCounterpart(new TreePath(csB.getPath()));
					csB.setTreePathOfCounterpart(new TreePath(csA.getPath()));
					//no need to check attributes
					AttributeInspector ai=new AttributeInspector();
					ParameterInspector pi = new ParameterInspector(csA.getParameter(), csB.getParameter());
					ConditionInspector ci = new ConditionInspector(csA.getCondition(), csB.getCondition());
					boolean hasSameCSAttribs = ai.hasSameConditionSetAttributes(csA, csB);
					boolean hasSameParams = pi.hasSameParameters();
					boolean hasSameConditions = ci.hasSameConditions();
					csA.setHasSameAttributes(hasSameCSAttribs);
					csB.setHasSameAttributes(hasSameCSAttribs);
//					if ((i!=j)) {
//						csA.setSamePosition(false);
//						csB.setSamePosition(false);
//						same=false;
//					}
					if (hasSameCSAttribs&&hasSameParams&&hasSameConditions) {
						csA.setCompareStatus("SAME");
						csB.setCompareStatus("SAME");
					} else {
						csA.setCompareStatus("NOT SAME");
						csB.setCompareStatus("NOT SAME");
						same=false;
					}
					csA.setSameAttributes(ai.getSameAttributes());
					csB.setSameAttributes(ai.getSameAttributes());
					csA.setHasSameAttributes(ai.isSame());
					csB.setHasSameAttributes(ai.isSame());
					csA.setParameter(pi.getParametersA());
					csB.setParameter(pi.getParametersB());
					csA.setCondition(ci.getConditionsA());
					csB.setCondition(ci.getConditionsB());
					conditionSetsB.set(j,csB);
					break;
				}
			}
			//csA.setIndex(i);
			csA.setChecked(true);
			conditionSetsA.set(i,csA);
		}
		return same;
	}
	
	private boolean matchingConditionSets(Conditionset condsetA, Conditionset condsetB) {
		Conditionset csa;
		Conditionset csb;
		if (condsetA.getCondition().size()<=condsetB.getCondition().size()) {
			csa = condsetA;
			csb = condsetB;
		} else { csa = condsetB;
				 csb = condsetA; }
		boolean match = true;
		for (int i=0;i<csa.getCondition().size();i++) {
			for (int j=0;j<csb.getCondition().size();j++) {
				if ((csb.getCondition().get(j).getIndexOfCounterpart()==-1)
					&&(csa.getCondition().get(i).getName().equals(csb.getCondition().get(j).getName()))
					&&(csa.getCondition().get(i).getClazz().equals(csb.getCondition().get(j).getClazz()))) {
					csa.getCondition().get(i).setIndexOfCounterpart(j);
					csb.getCondition().get(j).setIndexOfCounterpart(i);
					break;
				}
			}
		}
		for (int i=0;i<csa.getCondition().size();i++) {
			if (csa.getCondition().get(i).getIndexOfCounterpart()==-1){
				match=false;
				break;}
		}
		return match;
	}
	
	/**
	 *
	 * Returns string similarity ratio using the Levenshtein distance algorithm
	 * 
	 */
	private double stringSimilarityRatio(String s1, String s2){
	     int edits[][]=new int[s1.length()+1][s2.length()+1];
	     for(int i=0;i<=s1.length();i++)
	         edits[i][0]=i;
	     for(int j=1;j<=s2.length();j++)
	         edits[0][j]=j;
	     for(int i=1;i<=s1.length();i++){
	         for(int j=1;j<=s2.length();j++){
	             int u=(s1.charAt(i-1)==s2.charAt(j-1)?0:1);
	             edits[i][j]=Math.min(edits[i-1][j]+1,Math.min(edits[i][j-1]+1,edits[i-1][j-1]+u));
	         }
	     }
	     int distance = edits[s1.length()][s2.length()];
	     if (distance==0){
	    	 return 100;
	     }
	     return 100*(1-(((double) distance) / (Math.max(s1.length(), s2.length()))));
	}
	
	private double conditionsSimilarityRatio(Conditionset csA, Conditionset csB) {
		int matches = 0;
		int total = (csA.getCondition().size()<=csB.getCondition().size())? csA.getCondition().size():csB.getCondition().size();
		
		
		
		return ((double)matches/(double)total);
	}
	
	public List<Conditionset> getConditionSetsA() {
		return conditionSetsA;
	}
	public List<Conditionset> getConditionSetsB() {
		return conditionSetsB;
	}
	public boolean isSame(){
		return same;
	}
}
