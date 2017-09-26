/**
 * @author      John Robert Tan
 * @version
 */

package com.safeway.cgct.ui;

import java.awt.Color;
import java.awt.Component;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JTree;
import javax.swing.tree.TreeCellRenderer;
import javax.swing.tree.DefaultMutableTreeNode;
import javax.swing.tree.DefaultTreeCellRenderer;

import com.safeway.cgct.objects.Calcgroup;
import com.safeway.cgct.objects.Condition;
import com.safeway.cgct.objects.Conditionset;
import com.safeway.cgct.objects.Parameter;
import com.safeway.cgct.objects.Rule;

import javax.swing.plaf.synth.*;

public class CalculationGroupTreeCellRenderer extends DefaultTreeCellRenderer {
	ImageIcon defaultCalcGroupIcon;
	ImageIcon greenCalcGroupIcon;
	ImageIcon redCalcGroupIcon;
	ImageIcon defaultRuleIcon;
	ImageIcon greenRuleIcon;
	ImageIcon redRuleIcon;
	ImageIcon defaultConditionSetIcon;
	ImageIcon greenConditionSetIcon;
	ImageIcon redConditionSetIcon;
	ImageIcon defaultConditionIcon;
	ImageIcon greenConditionIcon;
	ImageIcon redConditionIcon;
	ImageIcon defaultParameterIcon;
	ImageIcon greenParameterIcon;
	ImageIcon redParameterIcon;

	public CalculationGroupTreeCellRenderer() {
		
		defaultCalcGroupIcon = new ImageIcon(getClass().getClassLoader().getResource("DEFAULTCG.png"));
		greenCalcGroupIcon = new ImageIcon(getClass().getClassLoader().getResource("GREENCG.png"));
		redCalcGroupIcon = new ImageIcon(getClass().getClassLoader().getResource("REDCG.png"));
		defaultRuleIcon = new ImageIcon(getClass().getClassLoader().getResource("DEFAULTR.png"));
		greenRuleIcon = new ImageIcon(getClass().getClassLoader().getResource("GREENR.png"));
		redRuleIcon = new ImageIcon(getClass().getClassLoader().getResource("REDR.png"));
		defaultConditionSetIcon = new ImageIcon(getClass().getClassLoader().getResource("DEFAULTCS.png"));
		greenConditionSetIcon = new ImageIcon(getClass().getClassLoader().getResource("GREENCS.png"));
		redConditionSetIcon = new ImageIcon(getClass().getClassLoader().getResource("REDCS.png"));
		defaultConditionIcon = new ImageIcon(getClass().getClassLoader().getResource("DEFAULTC.png"));
		greenConditionIcon = new ImageIcon(getClass().getClassLoader().getResource("GREENC.png"));
		redConditionIcon = new ImageIcon(getClass().getClassLoader().getResource("REDC.png"));
		defaultParameterIcon = new ImageIcon(getClass().getClassLoader().getResource("DEFAULTP.png"));
		greenParameterIcon = new ImageIcon(getClass().getClassLoader().getResource("GREENP.png"));
		redParameterIcon = new ImageIcon(getClass().getClassLoader().getResource("REDP.png"));
		
		//setOpaque(true);
			
	}
	public Component getTreeCellRendererComponent(JTree tree, Object value, boolean selected, boolean expanded,
			boolean leaf, int row, boolean hasFocus) {
		// TODO Auto-generated method stub
		super.getTreeCellRendererComponent(tree, value, selected, expanded, leaf, row, hasFocus);
		if (value instanceof Calcgroup) {
			String compareStatus = ((Calcgroup)value).getCompareStatus();
			switch (compareStatus) {
				case "SAME":
					setIcon(greenCalcGroupIcon);
					break;
				case "NOT SAME":
					setIcon(redCalcGroupIcon);
					break;
				default:
					setIcon(defaultCalcGroupIcon);		
			}
			setText("CALCULATION GROUP - " + ((Calcgroup)value).getName());
			setToolTipText("No. of rules in this calculation group: " + ((Calcgroup)value).getRule().size());
        } else if (value instanceof Rule) {
        	String compareStatus = ((Rule)value).getCompareStatus();
        	switch (compareStatus) {
				case "SAME":
					setIcon(greenRuleIcon);
					break;
				case "NOT SAME":
					setIcon(redRuleIcon);
					break;
				default:
					setIcon(defaultRuleIcon);		
        	}
        	if (((Rule)value).getIsActive().equalsIgnoreCase("false")) {
        		setText("<html><span bgcolor=\"gray\">RULE " + 
        				(((Rule)value).getIndex()+1) + " - " + 
        				((Rule)value).getName()+"</span></html>");
        	} else { 
        		setText("RULE " + (((Rule)value).getIndex()+1) + 
        				" - " + ((Rule)value).getName());
        	}
        	
        	//setToolTipText("No. of condition sets in this rule: " + ((Rule)value).getConditionset().size());
        	setToolTipText(((Rule)value).getClazz());
        } else if (value instanceof Conditionset) {
        	String compareStatus = ((Conditionset)value).getCompareStatus();
        	switch (compareStatus) {
				case "SAME":
					setIcon(greenConditionSetIcon);
					break;
				case "NOT SAME":
					setIcon(redConditionSetIcon);
					break;
				default:
					setIcon(defaultConditionSetIcon);		
        	}
        	setText("CONDITION SET " + (((Conditionset)value).getIndex()+1) + " - " + ((Conditionset)value).getDescription());
        	setToolTipText(//"<html>No. of parameters in this condition set: " + ((Conditionset)value).getParameter().size() +"<br>" + 
        				   "No. of conditions in this condition set: " + ((Conditionset)value).getCondition().size());
        } else if (value instanceof Condition) {
        	String compareStatus = ((Condition)value).getCompareStatus();
        	switch (compareStatus) {
				case "SAME":
					setIcon(greenConditionIcon);
					break;
				case "NOT SAME":
					setIcon(redConditionIcon);
					break;
				default:
					setIcon(defaultConditionIcon);	
        	}
        	setText("CONDITION " + (((Condition)value).getIndex()+1) + " - " + ((Condition)value).getName());
        	//setToolTipText("No. of parameters in this condition: " + ((Condition)value).getParameter().size());
        	setToolTipText(((Condition)value).getClazz());
        } else if (value instanceof Parameter) {
        	String compareStatus = ((Parameter)value).getCompareStatus();
        	switch (compareStatus) {
				case "SAME":
					setIcon(greenParameterIcon);
					break;
				case "NOT SAME":
					setIcon(redParameterIcon);
					break;
				default:
					setIcon(defaultParameterIcon);		
        	}
        	//setText("PARAMETER " + (((Parameter)value).getIndex()+1) + " - " + ((Parameter)value).getName() + ": " + ((Parameter)value).getValue());
        	setText("PARAMETER - " + ((Parameter)value).getName() + ": " + ((Parameter)value).getValue());
        	if (((Parameter)value).getValue()==null||((Parameter)value).getValue().isEmpty()) {
        		setToolTipText("This parameter contains no value");}
        	else {setToolTipText(((Parameter)value).getName()+": "+((Parameter)value).getValue());}
        }
        else {
            setIcon(null);
            //setText("" + value);
        }
		//setText(value.toString());
		return this;
	}
}
