/**
 * @author      John Robert Tan
 * @version
 */

package com.safeway.cgct.backend;

import java.util.List;

import javax.swing.tree.TreePath;

import com.safeway.cgct.objects.Parameter;

public class ParameterInspector {
	List<Parameter> parametersA;
	List<Parameter> parametersB;
	boolean same;
	
	public ParameterInspector(List<Parameter> paramsA, List<Parameter> paramsB){
		/*ParameterSorter ps = new ParameterSorter();
		parametersA=ps.sortParameters(paramsA);
		parametersB=ps.sortParameters(paramsB);*/
		parametersA=paramsA;
		parametersB=paramsB;
	}
	public boolean hasSameParameters() {
		same=true;
		//boolean samePosition=true;
		Parameter pA;
		Parameter pB;
		if (parametersA.size()!=parametersB.size())
			same=false;
		for (int i=0;i<parametersA.size();i++) {
			pA=parametersA.get(i);
			for (int j=0;j<parametersB.size();j++) {
				pB=parametersB.get(j);
				if ((!pA.isChecked())&&(pA.getName().equals(pB.getName()))) {
					if(pA.getValue().equals(pB.getValue())) {
						pA.setCompareStatus("SAME");
						pB.setCompareStatus("SAME");
					} else {
						pA.setCompareStatus("NOT SAME");
						pB.setCompareStatus("NOT SAME");
						same=false;
					}
					pB.setChecked(true);
					pA.setIndexOfCounterpart(j);
					pB.setIndexOfCounterpart(i);
					pA.setTreePathOfCounterpart(new TreePath(pB.getPath()));
					pB.setTreePathOfCounterpart(new TreePath(pA.getPath()));
					parametersB.set(j,pB);
					break;
				}
			}
			pA.setChecked(true);
			parametersA.set(i,pA);
		}
		return same;
	}
	
	public boolean checkParamsWithoutTagging() {
		boolean same = true;
		return same;
	}
	public List<Parameter> getParametersA() {
		return parametersA;
	}
	public List<Parameter> getParametersB() {
		return parametersB;
	}
}
