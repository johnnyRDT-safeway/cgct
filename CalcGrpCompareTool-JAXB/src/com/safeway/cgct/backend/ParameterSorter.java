/**
 * @author      John Robert Tan
 * @version
 */

package com.safeway.cgct.backend;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

import com.safeway.cgct.objects.Parameter;

public class ParameterSorter {
	class ParameterComparator implements Comparator<Parameter> {
		public int compare(Parameter p1, Parameter p2) {
			/*Return Value
			The value 0 if the argument is a string lexicographically 
			equal to this string; a value less than 0 if the argument is 
			a string lexicographically greater than this string; and a value
			greater than 0 if the argument is a string lexicographically 
			less than this string. */
			return p1.getName().compareToIgnoreCase(p2.getName());
		}
	}
	public List<Parameter> sortParameters(List<Parameter> params) {
		List<Parameter> parameters = params;
		if (!parameters.isEmpty())
			Collections.sort(parameters, new ParameterComparator());
		return parameters;
	}
	
	/*public List<Parameter> sortParameters(List<Parameter> params) {
		List<Parameter> unsortedParams = params;
		List<Parameter> sortedParams = new ArrayList<Parameter>();
		
		while (!unsortedParams.isEmpty()){
			int currParamIndex = 0;
			if (unsortedParams.size()>1) {
				
				for (int j=1;j<unsortedParams.size();j++) {
					if((j!=currParamIndex)&& (unsortedParams.get(currParamIndex).getName().compareTo(unsortedParams.get(j).getName())>0)) {
						currParamIndex = j;
					}
				}
			}
			sortedParams.add(unsortedParams.get(currParamIndex));
			unsortedParams.remove(currParamIndex);
		}
		return sortedParams;
	}*/
	
}
