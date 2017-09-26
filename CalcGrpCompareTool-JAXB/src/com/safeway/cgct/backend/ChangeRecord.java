package com.safeway.cgct.backend;

import java.util.Comparator;
import java.util.List;

import javax.swing.tree.DefaultMutableTreeNode;

import com.safeway.cgct.objects.Calcgroup;

public class ChangeRecord {
	public List<DefaultMutableTreeNode> changes;
	
	class NodePositionComparator implements Comparator<DefaultMutableTreeNode> {
		@Override
		public int compare(DefaultMutableTreeNode o1, DefaultMutableTreeNode o2) {
			// TODO Auto-generated method stub
			return ((DefaultMutableTreeNode)o1).getPath().length - ((DefaultMutableTreeNode)o2).getPath().length;
		}
	}
	public ChangeRecord(Calcgroup cga, Calcgroup cgb) {
		/*for (Rule ra: cga.getRule()) {
			if (ra.getCompareStatus().equals("DEFAULT")||)
			if (!ra.getConditionset().isEmpty()) {	
				for (Conditionset csa: ra.getConditionset()) {
					if (!csa.getParameter().isEmpty()) {
						csa.setParameter(csa.getParameter());
						for (Parameter currentParameter: csa.getParameter()) {
							csa.add(currentParameter);
						}
					}
					if (!csa.getCondition().isEmpty()) {
						for (Condition ca: csa.getCondition()) {
							if (!ca.getParameter().isEmpty()) {
								for (Parameter pa: ca.getParameter()) {
									ca.add(pa);
								}
							}
							csa.add(ca);
						}
						ra.add(csa);
					}
				}
				cg.add(currentRule);
			}
		}*/
		
//		class Change {
//			TreePath pathA;
//			TreePath pathB;
//			String changeType; //add/remove, modified 
//			getPathA 
//			getPathB
//			setPathA
//			setPathB
//		}
		//create inner class difference/change
		
	}
	
	
	
}
