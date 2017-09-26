/**
 * @author      John Robert Tan
 * @version
 */

package com.safeway.cgct.ui;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.LayoutManager;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.io.File;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

import javax.swing.BorderFactory;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JSplitPane;
import javax.swing.JTextField;
import javax.swing.JTextPane;
import javax.swing.JTree;
import javax.swing.ToolTipManager;
import javax.swing.UIDefaults;
import javax.swing.UIManager;
import javax.swing.border.Border;
import javax.swing.border.EtchedBorder;
import javax.swing.border.TitledBorder;
import javax.swing.event.TreeExpansionEvent;
import javax.swing.event.TreeExpansionListener;
import javax.swing.event.TreeSelectionEvent;
import javax.swing.event.TreeSelectionListener;
import javax.swing.plaf.ColorUIResource;
import javax.swing.text.BadLocationException;
import javax.swing.text.Style;
import javax.swing.text.StyleConstants;
import javax.swing.text.StyledDocument;
import javax.swing.tree.DefaultMutableTreeNode;
import javax.swing.tree.TreePath;
import javax.swing.tree.TreeSelectionModel;

import com.safeway.cgct.objects.Calcgroup;
import com.safeway.cgct.objects.Condition;
import com.safeway.cgct.objects.Conditionset;
import com.safeway.cgct.objects.Parameter;
import com.safeway.cgct.objects.Rule;

public class ComparatorView extends JFrame {
	JPanel cPanel;
	//Calcgroup cgaDisplay;
	//Calcgroup cgbDisplay;
	JTree jTreeA;
	JTree jTreeB;
	//JLabel attributeLabelA;
	//JLabel attributeLabelB;
	JTextPane attribTextPaneA;
	JTextPane attribTextPaneB;
	JPanel attribPanelA;
    JPanel attribPanelB;
    JSplitPane leftView;
    JSplitPane rightView;
    JSplitPane compareView;
    JSplitPane fileNameSplitPane;
    JButton expandButton;
    JButton collapseButton;
    JButton backButton;
    JButton exitButton;
    JButton nextButton;
    JButton previousButton;
    JFrame fcFrame;
    String currentTree = "none";
	
	public ComparatorView(JFrame fcFrame, Calcgroup cga, Calcgroup cgb, File fileA, File fileB) {
		this.fcFrame=fcFrame;
		cPanel = new JPanel();
		add(cPanel);
        setTitle("Calculation Group Compare Tool v1.0");
        setSize(900, 725);
        setJMenuBar(createMenuBar());
        
        //setDefaultLookAndFeelDecorated(true);
        
		//cPanel.setBackground(Color.DARK_GRAY);
		CalculationGroupTreeBuilder cgtb = new CalculationGroupTreeBuilder();
		cgtb.compareAndCreateCalcGroupTrees(cga, cgb);
		//cgaDisplay=cgtb.getRootNodeA();
		//cgbDisplay=cgtb.getRootNodeB();
		jTreeA = new JTree(cgtb.getRootNodeA());
		jTreeB = new JTree(cgtb.getRootNodeB());
		
		jTreeA.getSelectionModel().setSelectionMode(TreeSelectionModel.SINGLE_TREE_SELECTION);
		jTreeB.getSelectionModel().setSelectionMode(TreeSelectionModel.SINGLE_TREE_SELECTION);
		jTreeA.setCellRenderer(new CalculationGroupTreeCellRenderer());
		jTreeB.setCellRenderer(new CalculationGroupTreeCellRenderer());
		jTreeA.setScrollsOnExpand(true);
		jTreeB.setScrollsOnExpand(true);
		
		javax.swing.ToolTipManager.sharedInstance().registerComponent(jTreeA);
		javax.swing.ToolTipManager.sharedInstance().registerComponent(jTreeB);
		UIManager.put("ToolTip.background", new ColorUIResource(255, 247, 200));
		Border border = BorderFactory.createLineBorder(new Color(76,79,83));    //#4c4f53
		UIManager.put("ToolTip.border", border);
		ToolTipManager.sharedInstance().setDismissDelay(15000);
		
		JScrollPane treeViewA = new JScrollPane(jTreeA);
		JScrollPane treeViewB = new JScrollPane(jTreeB);
		
//		JSplitPane treeSplitPane = new JSplitPane(JSplitPane.HORIZONTAL_SPLIT);
//      treeSplitPane.setLeftComponent(treeViewA);
//      treeSplitPane.setRightComponent(treeViewB);
		
		//JPanel filePanel = new JPanel();
		Border loweredetched = BorderFactory.createEtchedBorder(EtchedBorder.LOWERED);
		Border raised = BorderFactory.createRaisedBevelBorder();
		Border raisedEtched = BorderFactory.createEtchedBorder(EtchedBorder.RAISED);
//		Border whiteline = BorderFactory.createLineBorder(Color.WHITE);
//		Border blackline = BorderFactory.createLineBorder(Color.DARK_GRAY);
		TitledBorder titledBorderA = BorderFactory.createTitledBorder(raised, "CALC GROUP A");
		TitledBorder titledBorderB = BorderFactory.createTitledBorder(raised, "CALC GROUP B");
		titledBorderA.setTitleJustification(TitledBorder.LEFT);
		titledBorderB.setTitleJustification(TitledBorder.LEFT);
		JTextField fileTextFieldA = new JTextField(fileA.getName());
		JTextField fileTextFieldB = new JTextField(fileB.getName());
		fileTextFieldA.setEditable(false);
		fileTextFieldB.setEditable(false);
//		fileTextFieldA.setBorder(titledBorderA);
//		fileTextFieldB.setBorder(titledBorderB);
		fileTextFieldA.setPreferredSize(new Dimension(400,30));
		fileTextFieldB.setPreferredSize(new Dimension(400,30));
		fileTextFieldA.setHorizontalAlignment(JTextField.CENTER);
		fileTextFieldB.setHorizontalAlignment(JTextField.CENTER);
		
		SimpleDateFormat sdf = new SimpleDateFormat("MM/dd/yyyy HH:mm:ss");
		fileTextFieldA.setToolTipText("File last modified: " + sdf.format(fileA.lastModified()));
		fileTextFieldB.setToolTipText("File last modified: " + sdf.format(fileB.lastModified()));

		JPanel leftFilePanel = new JPanel();
		JPanel rightFilePanel=new JPanel();
		leftFilePanel.setBorder(titledBorderA);
		rightFilePanel.setBorder(titledBorderB);
//		leftFilePanel.setLayout(new FlowLayout(FlowLayout.LEFT));
//		rightFilePanel.setLayout(new FlowLayout(FlowLayout.LEFT));
//		leftFilePanel.add(leftFileLabel);
//		rightFilePanel.add(rightFileLabel);
//		leftFilePanel.setAlignmentX(Component.LEFT_ALIGNMENT);
//		rightFilePanel.setAlignmentX(Component.LEFT_ALIGNMENT);
//		leftFilePanel.setPreferredSize(new Dimension(450,45));
//		rightFilePanel.setPreferredSize(new Dimension(450,45));
		leftFilePanel.add(fileTextFieldA);
		rightFilePanel.add(fileTextFieldB);

		fileNameSplitPane = new JSplitPane(JSplitPane.HORIZONTAL_SPLIT);
		//fileNameSplitPane.setLayout(new FlowLayout(FlowLayout.LEFT));
		fileNameSplitPane.setLeftComponent(leftFilePanel);
		fileNameSplitPane.setRightComponent(rightFilePanel);
		fileNameSplitPane.setDividerLocation(0.5);
		fileNameSplitPane.setResizeWeight(0.5);
		//fileNameSplitPane.setAlignmentX(Component.LEFT_ALIGNMENT);
		
		//filePanel.add(fileNameSplitPane);
		cPanel.add(fileNameSplitPane);
		fileNameSplitPane.setPreferredSize(new Dimension(900, 65));
		fileNameSplitPane.setAlignmentX(Component.CENTER_ALIGNMENT);
		
		cPanel.add(fileNameSplitPane);
		
        Dimension treeViewMinimumSize = new Dimension(375, 325);
        treeViewA.setMinimumSize(treeViewMinimumSize);
        treeViewB.setMinimumSize(treeViewMinimumSize);
//        treeSplitPane.setDividerLocation(340); 
//        treeSplitPane.setPreferredSize(new Dimension(600, 500));
        
        
        attribPanelA = new JPanel();
        attribPanelB = new JPanel();
        attribPanelA.setLayout(new FlowLayout(FlowLayout.LEFT));
        attribPanelB.setLayout(new FlowLayout(FlowLayout.LEFT));
        attribPanelA.setBackground(Color.WHITE);
        attribPanelB.setBackground(Color.WHITE);
//        JSplitPane attributeSplitPane = new JSplitPane(JSplitPane.HORIZONTAL_SPLIT);
//        attributeSplitPane.setLeftComponent(attribPanelA);
//        attributeSplitPane.setRightComponent(attribPanelB);
//        attributeSplitPane.setDividerLocation(340); 
        Dimension attribMinSize = new Dimension(375, 120);
        attribPanelA.setPreferredSize(attribMinSize);
        attribPanelB.setPreferredSize(attribMinSize);
//        attribPanelA.setBorder(BorderFactory.createMatteBorder(1, 5, 1, 1, Color.BLACK));
//        attribPanelB.setBorder(BorderFactory.createMatteBorder(1, 5, 1, 1, Color.BLACK));
        //attribPanelA.setBorder(BorderFactory.createLoweredBevelBorder());
        //attributeSplitPane.setPreferredSize(new Dimension(600, 300));
        
    	attribTextPaneA = new JTextPane();
    	attribTextPaneA.setSize(450,120);
        attribPanelA.add(attribTextPaneA);
        attribTextPaneB = new JTextPane();
        attribTextPaneB.setSize(450,120);
        attribPanelB.add(attribTextPaneB);
        
    	
		leftView = new JSplitPane(JSplitPane.VERTICAL_SPLIT);
		rightView = new JSplitPane(JSplitPane.VERTICAL_SPLIT);
		leftView.setTopComponent(treeViewA);
		leftView.setBottomComponent(attribPanelA);
		rightView.setTopComponent(treeViewB);
		rightView.setBottomComponent(attribPanelB);
		compareView = new JSplitPane(JSplitPane.HORIZONTAL_SPLIT);
        compareView.setDividerLocation(0.5);
		compareView.setLeftComponent(leftView);
		compareView.setRightComponent(rightView);
		compareView.setResizeWeight(0.5);
		compareView.setPreferredSize(new Dimension(900, 500));
		
		cPanel.add(compareView);
		
		JPanel buttonPanel = new JPanel(new BorderLayout());
		JPanel ctrButtonPanel = new JPanel(new GridLayout(2,2));
		nextButton=new JButton("NEXT DIFF >>");
		previousButton=new JButton("<< PREV DIFF");
        collapseButton=new JButton("Collapse All");
        expandButton=new JButton("Expand All");
        backButton=new JButton("Select New Calc Groups");
        exitButton=new JButton("EXIT");
        ctrButtonPanel.add(expandButton, 0);
        ctrButtonPanel.add(collapseButton, 1);
        ctrButtonPanel.add(backButton, 2);
        ctrButtonPanel.add(exitButton, 3);
        ctrButtonPanel.setPreferredSize(new Dimension(700, 75));
        ctrButtonPanel.setAlignmentX(Component.CENTER_ALIGNMENT);
        //ctrButtonPanel.setBackground(Color.DARK_GRAY);
        
        backButton.setEnabled(false);
        //nextButton.setEnabled(false);
        previousButton.setEnabled(false);
        
		buttonPanel.add(ctrButtonPanel, BorderLayout.CENTER);
		buttonPanel.add(nextButton, BorderLayout.LINE_END);
		buttonPanel.add(previousButton, BorderLayout.LINE_START);
		//buttonPanel.setBorder(BorderFactory.createMatteBorder(
        //        5, 5, 5, 5, Color.DARK_GRAY));
		//buttonPanel.setBackground(Color.DARK_GRAY);
		buttonPanel.setBorder(raisedEtched);
		//cPanel.add(attributeSplitPane);
		cPanel.add(buttonPanel);
		
        addDividerListeners();
        addTreeSelectionListeners();
        addTreeExpansionListeners();
        addButtonListeners();
	}
	
	public boolean hasSameAttributes(DefaultMutableTreeNode node) {
		boolean same = true;
		if (node instanceof Calcgroup) {
			for (int i=0; i<((Calcgroup)node).getSameAttributes().size();i++) {
				if (!((Calcgroup)node).getSameAttributes().get(i)) {
					same=false;
					break;
				}
			}
		} else if (node instanceof Rule) {
			System.out.println(((Rule)node).getSameAttributes().size());
			for (int i=0; i<((Rule)node).getSameAttributes().size();i++) {
				System.out.println(((Rule)node).getSameAttributes().get(i));
				if (!((Rule)node).getSameAttributes().get(i)) {
					same=false;
					break;
				}
			}
		} else if (node instanceof Conditionset) {
			System.out.println(((Conditionset)node).getSameAttributes().size());
			for (int i=0; i<((Conditionset)node).getSameAttributes().size();i++) {
				if (!((Conditionset)node).getSameAttributes().get(i)) {
					same=false;
					break;
				}
			}
		} else if (node instanceof Condition) {
			System.out.println(((Condition)node).getSameAttributes().size());
			for (int i=0; i<((Condition)node).getSameAttributes().size();i++) {
				if (!((Condition)node).getSameAttributes().get(i)) {
					same=false;
					break;
				}
			}
		}
		//System.out.println(same);
		return same;
	}
	
	public void addDividerListeners() {
		leftView.addPropertyChangeListener(JSplitPane.DIVIDER_LOCATION_PROPERTY, new PropertyChangeListener() {
			@Override
			public void propertyChange(PropertyChangeEvent evt) {
				// TODO Auto-generated method stub
				rightView.setDividerLocation(leftView.getDividerLocation());
			}
		});
		rightView.addPropertyChangeListener(JSplitPane.DIVIDER_LOCATION_PROPERTY, new PropertyChangeListener() {
			@Override
			public void propertyChange(PropertyChangeEvent evt) {
				// TODO Auto-generated method stub
				leftView.setDividerLocation(rightView.getDividerLocation());
			}
		});	
		fileNameSplitPane.addPropertyChangeListener(JSplitPane.DIVIDER_LOCATION_PROPERTY, new PropertyChangeListener() {
			@Override
			public void propertyChange(PropertyChangeEvent evt) {
				// TODO Auto-generated method stub
				compareView.setDividerLocation(fileNameSplitPane.getDividerLocation());
			}
		});	
		compareView.addPropertyChangeListener(JSplitPane.DIVIDER_LOCATION_PROPERTY, new PropertyChangeListener() {
			@Override
			public void propertyChange(PropertyChangeEvent evt) {
				// TODO Auto-generated method stub
				fileNameSplitPane.setDividerLocation(compareView.getDividerLocation());
			}
		});	
	}
	
	public void addButtonListeners(){
		expandButton.addActionListener(new ActionListener(){
			@Override
			public void actionPerformed(ActionEvent e) {
				for (int i=1;i<jTreeA.getRowCount();i++) {jTreeA.expandRow(i);}
				for (int i=1;i<jTreeB.getRowCount();i++) {jTreeB.expandRow(i);}
			}
		});
		collapseButton.addActionListener(new ActionListener(){
			@Override
			public void actionPerformed(ActionEvent e) {
				for (int i=1;i<jTreeA.getRowCount();i++) {jTreeA.collapseRow(i);}
				for (int i=1;i<jTreeB.getRowCount();i++) {jTreeB.collapseRow(i);}
			}
		});
		backButton.addActionListener(new ActionListener(){
			@Override
			public void actionPerformed(ActionEvent e) {
				ComparatorView.this.setVisible(false);
				fcFrame.setVisible(true);
			}
		});
		exitButton.addActionListener(new ActionListener(){
			@Override
			public void actionPerformed(ActionEvent e) {System.exit(0);}
		});
		nextButton.addActionListener(new ActionListener(){
			@Override
			public void actionPerformed(ActionEvent e) {
//				DefaultMutableTreeNode node = ((DefaultMutableTreeNode) jTreeA.getLastSelectedPathComponent()).getNextNode();
//				jTreeA.setSelectionPath(new TreePath (node.getPath()));
				boolean foundNext=false;
				DefaultMutableTreeNode nextNode;
				System.out.println(currentTree);
				if (currentTree.equals("A")) {
					nextNode = (DefaultMutableTreeNode)jTreeA.getLastSelectedPathComponent();
					//DefaultMutableTreeNode currentNode = (DefaultMutableTreeNode)jTreeA.getLastSelectedPathComponent();
					while ((!foundNext)) { 
						if ((nextNode instanceof Rule)&&(((Rule)nextNode).getCompareStatus().equals("DEFAULT"))
							&&(nextNode.getNextSibling()!=null)) {nextNode = nextNode.getNextSibling();
							System.out.println("next rule");} 
						else if ((nextNode instanceof Conditionset)&&(((Conditionset)nextNode).getCompareStatus().equals("DEFAULT"))
								 &&(nextNode.getNextSibling()!=null)) {nextNode = nextNode.getNextSibling();} 
						else if ((nextNode instanceof Condition)&&(((Condition)nextNode).getCompareStatus().equals("DEFAULT"))
								 &&(nextNode.getNextSibling()!=null)) {nextNode = nextNode.getNextSibling();} 
						else if (nextNode.getNextNode()!=null) {nextNode = nextNode.getNextNode();
							System.out.println("next");
						} else if (nextNode.getNextNode()==null) {
							System.out.println("no next");
							foundNext=true;
						}
						
						if (nextNode instanceof Rule) {
							if (((Rule)nextNode).getCompareStatus().equals("DEFAULT")||!hasSameAttributes((Rule)nextNode)) {
								jTreeA.setSelectionPath(new TreePath(nextNode.getPath()));
								//jTreeB.setSelectionPath(((Rule) nextNode).getTreePathOfCounterpart());
								foundNext=true;
							} else if (((Rule)nextNode).getCompareStatus().equals("NOT SAME")) {
								jTreeA.setSelectionPath(new TreePath(nextNode.getPath()));
								//jTreeB.clearSelection();
								foundNext=true;
							}
						}
						else if (nextNode instanceof Conditionset) {
							//System.out.println("found conditionset");
							if (((Conditionset)nextNode).getCompareStatus().equals("DEFAULT")) {
								jTreeA.setSelectionPath(new TreePath(nextNode.getPath()));
								//jTreeB.clearSelection();
								foundNext=true;
							}
							else if (!hasSameAttributes(nextNode)) {
								jTreeA.setSelectionPath(new TreePath(nextNode.getPath()));
								//jTreeB.setSelectionPath(((Conditionset) nextNode).getTreePathOfCounterpart());
								foundNext=true;
							}  
						}
						else if (nextNode instanceof Condition) {
							//System.out.println("found condition");
							if (((Condition)nextNode).getCompareStatus().equals("DEFAULT")) {
								jTreeA.setSelectionPath(new TreePath(nextNode.getPath()));
								//jTreeB.clearSelection();
								foundNext=true;
							}
							else if (!hasSameAttributes(nextNode)) {
								jTreeA.setSelectionPath(new TreePath(nextNode.getPath()));
								//jTreeB.setSelectionPath(((Condition) nextNode).getTreePathOfCounterpart());
								foundNext=true;
							} 
						}
						else if (nextNode instanceof Parameter) {
							//System.out.println("found param");
							if (((Parameter)nextNode).getCompareStatus().equals("DEFAULT")) {
								jTreeA.setSelectionPath(new TreePath(nextNode.getPath()));
								//jTreeB.clearSelection();
								foundNext=true;
							}
							else if (((Parameter)nextNode).getCompareStatus().equals("NOT SAME")) {
								jTreeA.setSelectionPath(new TreePath(nextNode.getPath()));
								//jTreeB.setSelectionPath(((Parameter) nextNode).getTreePathOfCounterpart());
								foundNext=true;
							} 
						}
					}
				} else if (currentTree.equals("B")) {
					nextNode = (DefaultMutableTreeNode)jTreeB.getLastSelectedPathComponent();
				} 

			}
		});
	}
	
	public void addTreeExpansionListeners() {
		jTreeA.addTreeExpansionListener(new TreeExpansionListener(){
			@Override
			public void treeExpanded(TreeExpansionEvent event) {
				// TODO Auto-generated method stub
				DefaultMutableTreeNode node = (DefaultMutableTreeNode) event.getPath().getLastPathComponent();
				if (node instanceof Calcgroup){
					if (((Calcgroup)node).getCompareStatus().equals("SAME")||((Calcgroup)node).getCompareStatus().equals("NOT SAME"))
						jTreeB.expandPath(new TreePath(((DefaultMutableTreeNode)jTreeB.getModel().getRoot()).getPath()));
				} else if (node instanceof Rule) {
					if (((Rule)node).getCompareStatus().equals("SAME")||((Rule)node).getCompareStatus().equals("NOT SAME"))
						jTreeB.expandPath(((Rule)node).getTreePathOfCounterpart());
				} else if (node instanceof Conditionset) {
					if (((Conditionset)node).getCompareStatus().equals("SAME")||((Conditionset)node).getCompareStatus().equals("NOT SAME"))
						jTreeB.expandPath(((Conditionset)node).getTreePathOfCounterpart());
				} else if (node instanceof Condition) {
					if (((Condition)node).getCompareStatus().equals("SAME")||((Condition)node).getCompareStatus().equals("NOT SAME"))
						jTreeB.expandPath(((Condition)node).getTreePathOfCounterpart());
				} 
			}
			@Override
			public void treeCollapsed(TreeExpansionEvent event) {
				// TODO Auto-generated method stub
				DefaultMutableTreeNode node = (DefaultMutableTreeNode) event.getPath().getLastPathComponent();
				if (node instanceof Calcgroup){
					if (((Calcgroup)node).getCompareStatus().equals("SAME")||((Calcgroup)node).getCompareStatus().equals("NOT SAME"))
						jTreeB.collapsePath(new TreePath(((DefaultMutableTreeNode)jTreeB.getModel().getRoot()).getPath()));
				} else if (node instanceof Rule) {
					if (((Rule)node).getCompareStatus().equals("SAME")||((Rule)node).getCompareStatus().equals("NOT SAME"))
						jTreeB.collapsePath(((Rule)node).getTreePathOfCounterpart());
				} else if (node instanceof Conditionset) {
					if (((Conditionset)node).getCompareStatus().equals("SAME")||((Conditionset)node).getCompareStatus().equals("NOT SAME"))
						jTreeB.collapsePath(((Conditionset)node).getTreePathOfCounterpart());
				} else if (node instanceof Condition) {
					if (((Condition)node).getCompareStatus().equals("SAME")||((Condition)node).getCompareStatus().equals("NOT SAME"))
						jTreeB.collapsePath(((Condition)node).getTreePathOfCounterpart());
				} 
			} 
		});
		jTreeB.addTreeExpansionListener(new TreeExpansionListener(){
			@Override
			public void treeExpanded(TreeExpansionEvent event) {
				// TODO Auto-generated method stub
				DefaultMutableTreeNode node = (DefaultMutableTreeNode) event.getPath().getLastPathComponent();
				if (node instanceof Calcgroup){
					if (((Calcgroup)node).getCompareStatus().equals("SAME")||((Calcgroup)node).getCompareStatus().equals("NOT SAME"))
						jTreeA.expandPath(new TreePath(((DefaultMutableTreeNode)jTreeA.getModel().getRoot()).getPath()));
				} else if (node instanceof Rule) {
					if (((Rule)node).getCompareStatus().equals("SAME")||((Rule)node).getCompareStatus().equals("NOT SAME"))
						jTreeA.expandPath(((Rule)node).getTreePathOfCounterpart());
				} else if (node instanceof Conditionset) {
					if (((Conditionset)node).getCompareStatus().equals("SAME")||((Conditionset)node).getCompareStatus().equals("NOT SAME"))
						jTreeA.expandPath(((Conditionset)node).getTreePathOfCounterpart());
				} else if (node instanceof Condition) {
					if (((Condition)node).getCompareStatus().equals("SAME")||((Condition)node).getCompareStatus().equals("NOT SAME"))
						jTreeA.expandPath(((Condition)node).getTreePathOfCounterpart());
				} 
			}
			@Override
			public void treeCollapsed(TreeExpansionEvent event) {
				// TODO Auto-generated method stub
				DefaultMutableTreeNode node = (DefaultMutableTreeNode) event.getPath().getLastPathComponent();
				if (node instanceof Calcgroup){
					if (((Calcgroup)node).getCompareStatus().equals("SAME")||((Calcgroup)node).getCompareStatus().equals("NOT SAME"))
						jTreeA.collapsePath(new TreePath(((DefaultMutableTreeNode)jTreeA.getModel().getRoot()).getPath()));
				} else if (node instanceof Rule) {
					if (((Rule)node).getCompareStatus().equals("SAME")||((Rule)node).getCompareStatus().equals("NOT SAME"))
						jTreeA.collapsePath(((Rule)node).getTreePathOfCounterpart());
				} else if (node instanceof Conditionset) {
					if (((Conditionset)node).getCompareStatus().equals("SAME")||((Conditionset)node).getCompareStatus().equals("NOT SAME"))
						jTreeA.collapsePath(((Conditionset)node).getTreePathOfCounterpart());
				} else if (node instanceof Condition) {
					if (((Condition)node).getCompareStatus().equals("SAME")||((Condition)node).getCompareStatus().equals("NOT SAME"))
						jTreeA.collapsePath(((Condition)node).getTreePathOfCounterpart());
				}
			}
		});
	}
	
	public void addTreeSelectionListeners() {
		jTreeA.addTreeSelectionListener(new TreeSelectionListener() {
			public void valueChanged(TreeSelectionEvent e) {
			    DefaultMutableTreeNode node = (DefaultMutableTreeNode)jTreeA.getLastSelectedPathComponent();
			    //if nothing is selected  
			    if (node == null) {
			    	
				} else {
			    // retrieve the node that was selected 
			    //Object selectedNode = node.getUserObject();
			    //    ...
			    	if (node instanceof Calcgroup) {
			    		Calcgroup cga=(Calcgroup) node;
			    		Calcgroup cgb=(Calcgroup) jTreeB.getModel().getRoot();
			    		if(cga.getCompareStatus().equals("SAME")||cga.getCompareStatus().equals("NOT SAME")) {
			    			TreePath pathB = new TreePath(((DefaultMutableTreeNode)jTreeB.getModel().getRoot()).getPath());
			    			
			    			jTreeB.setSelectionPath(pathB);
			    			jTreeB.scrollPathToVisible(pathB);
			    			List<String> attribsA = new ArrayList<String>();
			    			attribsA.add("autoRecalc: "+cga.getAutoRecalc());
			    			attribsA.add("calcPeriod: "+cga.getCalcPeriod());
			    			attribsA.add("description: "+cga.getDescription());
			    			attribsA.add("name: "+cga.getName());
			    			setAttributesA(attribsA, cga.getSameAttributes());
			    			
			    			List<String> attribsB = new ArrayList<String>();
			    			attribsB.add("autoRecalc: "+cgb.getAutoRecalc());
			    			attribsB.add("calcPeriod: "+cgb.getCalcPeriod());
			    			attribsB.add("description: "+cgb.getDescription());
			    			attribsB.add("name: "+cgb.getName());
			    			setAttributesB(attribsB, cgb.getSameAttributes());
			    		}
			    		else { 
			    			jTreeB.clearSelection();
			    			clearAttributeTextB();
			    			List<String> attribsA = new ArrayList<String>();
			    			attribsA.add("autoRecalc: "+cga.getAutoRecalc());
			    			attribsA.add("calcPeriod: "+cga.getCalcPeriod());
			    			attribsA.add("description: "+cga.getDescription());
			    			attribsA.add("name: "+cga.getName());
			    			setAttributesA(attribsA, cga.getSameAttributes());
			    		}
			    	} 
			    	else if (node instanceof Rule) { 
			    		Rule ra = (Rule) node;
			    		if(((Rule) node).getCompareStatus().equals("SAME")||((Rule) node).getCompareStatus().equals("NOT SAME")) {
			    			Rule rb = (Rule) ra.getTreePathOfCounterpart().getLastPathComponent();
			    			TreePath pathB = ((Rule) node).getTreePathOfCounterpart();
			    			jTreeB.setSelectionPath(pathB);
			    			jTreeB.scrollPathToVisible(pathB);
			    			//jTreeA.setSelectionPath(new TreePath(node.getPath()));
			    			
			    			List<String> attribsA = new ArrayList<String>();
			    			attribsA.add("class: "+ra.getClazz());
			    			attribsA.add("description: "+ra.getDescription());
			    			attribsA.add("executionPoint: "+ra.getExecutionPoint());
			    			attribsA.add("isActive: "+ra.getIsActive());
			    			attribsA.add("name: "+ra.getName());
			    			attribsA.add("overriddenConditionSetExecIsME: "+ra.getOverriddenConditionSetExecIsME());
			    			attribsA.add("overrideConditionSetExecIsME: "+ra.getOverrideConditionSetExecIsME());
			    			setAttributesA(attribsA, ra.getSameAttributes());
			    			
			    			List<String> attribsB = new ArrayList<String>();
			    			attribsB.add("class: "+rb.getClazz());
			    			attribsB.add("description: "+rb.getDescription());
			    			attribsB.add("executionPoint: "+rb.getExecutionPoint());
			    			attribsB.add("isActive: "+rb.getIsActive());
			    			attribsB.add("name: "+rb.getName());
			    			attribsB.add("overriddenConditionSetExecIsME: "+rb.getOverriddenConditionSetExecIsME());
			    			attribsB.add("overrideConditionSetExecIsME: "+rb.getOverrideConditionSetExecIsME());
			    			setAttributesB(attribsB, rb.getSameAttributes());
			    		}
			    		else { jTreeB.clearSelection();
			    			clearAttributeTextB();
				    		List<String> attribsA = new ArrayList<String>();
			    			attribsA.add("class: "+ra.getClazz());
			    			attribsA.add("description: "+ra.getDescription());
			    			attribsA.add("executionPoint: "+ra.getExecutionPoint());
			    			attribsA.add("isActive: "+ra.getIsActive());
			    			attribsA.add("name: "+ra.getName());
			    			attribsA.add("overriddenConditionSetExecIsME: "+ra.getOverriddenConditionSetExecIsME());
			    			attribsA.add("overrideConditionSetExecIsME: "+ra.getOverrideConditionSetExecIsME());
			    			setAttributesA(attribsA, ra.getSameAttributes());
			    		}
			    	}
			    	else if (node instanceof Conditionset) {
			    		Conditionset csa = (Conditionset) node;
			    		if(csa.getCompareStatus().equals("SAME")||csa.getCompareStatus().equals("NOT SAME")) {
			    			Conditionset csb = (Conditionset) csa.getTreePathOfCounterpart().getLastPathComponent();
			    			TreePath pathB = csa.getTreePathOfCounterpart();
			    			jTreeB.setSelectionPath(pathB);
			    			jTreeB.scrollPathToVisible(pathB);
			    		
			    			List<String> attribsA = new ArrayList<String>();
			    			attribsA.add("description: "+csa.getDescription());
			    			attribsA.add("negate: "+csa.getNegate());
			    			setAttributesA(attribsA, csa.getSameAttributes());
			    			
			    			List<String> attribsB = new ArrayList<String>();
			    			attribsB.add("description: "+csb.getDescription());
			    			attribsB.add("negate: "+csb.getNegate());
			    			setAttributesB(attribsB, csb.getSameAttributes());
			    		}
			    		else { jTreeB.clearSelection(); 
			    			clearAttributeTextB();
			    			List<String> attribsA = new ArrayList<String>();
			    			attribsA.add("description: "+csa.getDescription());
			    			attribsA.add("negate: "+csa.getNegate());
			    			setAttributesA(attribsA, csa.getSameAttributes());
			    		}
			    	}
			    	else if (node instanceof Condition) {
			    		Condition ca = (Condition) node;
			    		if(ca.getCompareStatus().equals("SAME")||ca.getCompareStatus().equals("NOT SAME")) {
			    			Condition cb = ((Condition)ca.getTreePathOfCounterpart().getLastPathComponent());
			    			TreePath pathB = ((Condition) node).getTreePathOfCounterpart();
			    			jTreeB.setSelectionPath(pathB);
			    			jTreeB.scrollPathToVisible(pathB);
			    			List<String> attribsA = new ArrayList<String>();
			    			attribsA.add("class: "+ca.getClazz());
			    			attribsA.add("description: "+ca.getDescription());
			    			attribsA.add("name: "+ca.getName());
			    			attribsA.add("negate: "+ca.getNegate());
			    			setAttributesA(attribsA, ca.getSameAttributes());
			    			
			    			List<String> attribsB = new ArrayList<String>();
			    			attribsB.add("class: "+cb.getClazz());
			    			attribsB.add("description: "+cb.getDescription());
			    			attribsB.add("name: "+cb.getName());
			    			attribsB.add("negate: "+cb.getNegate());
			    			setAttributesB(attribsB, cb.getSameAttributes());
			    		}
			    		else { jTreeB.clearSelection(); 
			    			clearAttributeTextB();
				    		List<String> attribsA = new ArrayList<String>();
			    			attribsA.add("class: "+ca.getClazz());
			    			attribsA.add("description: "+ca.getDescription());
			    			attribsA.add("name: "+ca.getName());
			    			attribsA.add("negate: "+ca.getNegate());
			    			setAttributesA(attribsA, ca.getSameAttributes());
			    		}
			    	}
			    	else if (node instanceof Parameter) {
			    		Parameter pa = (Parameter) node;
			    		if(((Parameter) node).getCompareStatus().equals("SAME")||((Parameter) node).getCompareStatus().equals("NOT SAME")) {
			    			Parameter pb = ((Parameter)pa.getTreePathOfCounterpart().getLastPathComponent());
			    			TreePath pathB = ((Parameter) node).getTreePathOfCounterpart();
			    			jTreeB.setSelectionPath(pathB);
			    			jTreeB.scrollPathToVisible(pathB);
			    			List<Boolean> sameAttribsA = new ArrayList<Boolean>();
			    			List<String> attribsA = new ArrayList<String>();
			    			attribsA.add("name: "+pa.getName());
			    			attribsA.add("value: "+pa.getValue());
			    			if (pa.getCompareStatus().equals("SAME")) {
			    				sameAttribsA.add(true); 
			    				sameAttribsA.add(true); 
			    			} else if (pa.getCompareStatus().equals("NOT SAME")) {
			    				sameAttribsA.add(true); 
			    				sameAttribsA.add(false);
			    			}
			    			setAttributesA(attribsA, sameAttribsA);
			    			
			    			List<Boolean> sameAttribsB = new ArrayList<Boolean>();
			    			List<String> attribsB = new ArrayList<String>();
			    			attribsB.add("name: "+pb.getName());
			    			attribsB.add("value: "+pb.getValue());
			    			if (pb.getCompareStatus().equals("SAME")) {
			    				sameAttribsB.add(true); 
			    				sameAttribsB.add(true); 
			    			} else if (pb.getCompareStatus().equals("NOT SAME")) {
			    				sameAttribsB.add(true); 
			    				sameAttribsB.add(false);
			    			}
			    			setAttributesB(attribsB, sameAttribsB);
			    		}
			    		else { jTreeB.clearSelection(); 
			    			clearAttributeTextB();
				    		List<String> attribsA = new ArrayList<String>();
			    			attribsA.add("name: "+pa.getName());
			    			attribsA.add("value: "+pa.getValue());
			    			setAttributesA(attribsA, new ArrayList<Boolean>());
			    		}
			    	}
			        
			    }
			    currentTree="A";
			}
		});
		 
	        
        jTreeB.addTreeSelectionListener(new TreeSelectionListener() {
    		public void valueChanged(TreeSelectionEvent e) {
    		    DefaultMutableTreeNode node = (DefaultMutableTreeNode)jTreeB.getLastSelectedPathComponent();
    		    
    		    //if nothing is selected  
    		    if (node == null) {
    		    	
    			} else {
    		    // retrieve the node that was selected 
    		    //Object selectedNode = node.getUserObject();
    		    //    ...
    		    	if (node instanceof Calcgroup) {
    		    		Calcgroup cgb=(Calcgroup) node;
    		    		Calcgroup cga=(Calcgroup) jTreeA.getModel().getRoot();
    		    		if(cgb.getCompareStatus().equals("SAME")||cgb.getCompareStatus().equals("NOT SAME")) {
    		    			TreePath pathA = new TreePath(((DefaultMutableTreeNode)jTreeA.getModel().getRoot()).getPath());
    		    			
    		    			jTreeA.setSelectionPath(pathA);
    		    			jTreeA.scrollPathToVisible(pathA);
    		    			List<String> attribsB = new ArrayList<String>();
    		    			attribsB.add("autoRecalc: "+cgb.getAutoRecalc());
    		    			attribsB.add("calcPeriod: "+cgb.getCalcPeriod());
    		    			attribsB.add("description: "+cgb.getDescription());
    		    			attribsB.add("name: "+cgb.getName());
    		    			setAttributesB(attribsB, cgb.getSameAttributes());
    		    			
    		    			List<String> attribsA = new ArrayList<String>();
    		    			attribsA.add("autoRecalc: "+cga.getAutoRecalc());
    		    			attribsA.add("calcPeriod: "+cga.getCalcPeriod());
    		    			attribsA.add("description: "+cga.getDescription());
    		    			attribsA.add("name: "+cga.getName());
    		    			setAttributesA(attribsA, cga.getSameAttributes());
    		    		}
    		    		else { 
    		    			jTreeA.clearSelection();
    		    			clearAttributeTextA();
    		    			List<String> attribsB = new ArrayList<String>();
    		    			attribsB.add("autoRecalc: "+cgb.getAutoRecalc());
    		    			attribsB.add("calcPeriod: "+cgb.getCalcPeriod());
    		    			attribsB.add("description: "+cgb.getDescription());
    		    			attribsB.add("name: "+cgb.getName());
    		    			setAttributesB(attribsB, cgb.getSameAttributes());
    		    		}
    		    	} 
    		    	else if (node instanceof Rule) { 
    		    		Rule rb = (Rule) node;
    		    		if(((Rule) node).getCompareStatus().equals("SAME")||((Rule) node).getCompareStatus().equals("NOT SAME")) {
    		    			Rule ra = (Rule) rb.getTreePathOfCounterpart().getLastPathComponent();
    		    			TreePath pathA = ((Rule) node).getTreePathOfCounterpart();
    		    			jTreeA.setSelectionPath(pathA);
    		    			jTreeA.scrollPathToVisible(pathA);
    		    			
    		    			List<String> attribsB = new ArrayList<String>();
    		    			attribsB.add("class: "+rb.getClazz());
    		    			attribsB.add("description: "+rb.getDescription());
    		    			attribsB.add("executionPoint: "+rb.getExecutionPoint());
    		    			attribsB.add("isActive: "+rb.getIsActive());
    		    			attribsB.add("name: "+rb.getName());
    		    			attribsB.add("overriddenConditionSetExecIsME: "+rb.getOverriddenConditionSetExecIsME());
    		    			attribsB.add("overrideConditionSetExecIsME: "+rb.getOverrideConditionSetExecIsME());
    		    			setAttributesB(attribsB, rb.getSameAttributes());
    		    			
    		    			List<String> attribsA = new ArrayList<String>();
    		    			attribsA.add("class: "+ra.getClazz());
    		    			attribsA.add("description: "+ra.getDescription());
    		    			attribsA.add("executionPoint: "+ra.getExecutionPoint());
    		    			attribsA.add("isActive: "+ra.getIsActive());
    		    			attribsA.add("name: "+ra.getName());
    		    			attribsA.add("overriddenConditionSetExecIsME: "+ra.getOverriddenConditionSetExecIsME());
    		    			attribsA.add("overrideConditionSetExecIsME: "+ra.getOverrideConditionSetExecIsME());
    		    			setAttributesA(attribsA, ra.getSameAttributes());
    		    			
    		    		}
    		    		else { jTreeA.clearSelection();
    		    			clearAttributeTextA();
    			    		List<String> attribsB = new ArrayList<String>();
    		    			attribsB.add("class: "+rb.getClazz());
    		    			attribsB.add("description: "+rb.getDescription());
    		    			attribsB.add("executionPoint: "+rb.getExecutionPoint());
    		    			attribsB.add("isActive: "+rb.getIsActive());
    		    			attribsB.add("name: "+rb.getName());
    		    			attribsB.add("overriddenConditionSetExecIsME: "+rb.getOverriddenConditionSetExecIsME());
    		    			attribsB.add("overrideConditionSetExecIsME: "+rb.getOverrideConditionSetExecIsME());
    		    			setAttributesB(attribsB, rb.getSameAttributes());
    		    		}
    		    	}
    		    	else if (node instanceof Conditionset) {
    		    		Conditionset csb = (Conditionset) node;
    		    		if(csb.getCompareStatus().equals("SAME")||csb.getCompareStatus().equals("NOT SAME")) {
    		    			Conditionset csa = (Conditionset) csb.getTreePathOfCounterpart().getLastPathComponent();
    		    			TreePath pathA = csb.getTreePathOfCounterpart();
    		    			jTreeA.setSelectionPath(pathA);
    		    			jTreeA.scrollPathToVisible(pathA);
    		    			List<String> attribsB = new ArrayList<String>();
			    			attribsB.add("description: "+csb.getDescription());
			    			attribsB.add("negate: "+csb.getNegate());
			    			setAttributesB(attribsB, csb.getSameAttributes());
			    			
			    			List<String> attribsA = new ArrayList<String>();
			    			attribsA.add("description: "+csa.getDescription());
			    			attribsA.add("negate: "+csa.getNegate());
			    			setAttributesA(attribsA, csa.getSameAttributes());
			    		}
			    		else { jTreeA.clearSelection(); 
			    			clearAttributeTextA();
			    			List<String> attribsB = new ArrayList<String>();
			    			attribsB.add("description: "+csb.getDescription());
			    			attribsB.add("negate: "+csb.getNegate());
			    			setAttributesB(attribsB, csb.getSameAttributes());
			    		}
    		    	}
    		    	else if (node instanceof Condition) {
    		    		Condition cb = (Condition) node;
    		    		if(cb.getCompareStatus().equals("SAME")||cb.getCompareStatus().equals("NOT SAME")) {
        		    		Condition ca = ((Condition)cb.getTreePathOfCounterpart().getLastPathComponent());
    		    			TreePath pathA = cb.getTreePathOfCounterpart();
    		    			
    		    			jTreeA.setSelectionPath(pathA);
    		    			jTreeA.scrollPathToVisible(pathA);
    		    			List<String> attribsB = new ArrayList<String>();
    		    			attribsB.add("class: "+cb.getClazz());
    		    			attribsB.add("description: "+cb.getDescription());
    		    			attribsB.add("name: "+cb.getName());
    		    			attribsB.add("negate: "+cb.getNegate());
    		    			setAttributesB(attribsB, cb.getSameAttributes());
    		    			
    		    			List<String> attribsA = new ArrayList<String>();
    		    			attribsA.add("class: "+ca.getClazz());
    		    			attribsA.add("description: "+ca.getDescription());
    		    			attribsA.add("name: "+ca.getName());
    		    			attribsA.add("negate: "+ca.getNegate());
    		    			setAttributesA(attribsA, ca.getSameAttributes());
    		    		}
    		    		else { jTreeA.clearSelection(); 
    		    			clearAttributeTextA();
    			    		List<String> attribsB = new ArrayList<String>();
    		    			attribsB.add("class: "+cb.getClazz());
    		    			attribsB.add("description: "+cb.getDescription());
    		    			attribsB.add("name: "+cb.getName());
    		    			attribsB.add("negate: "+cb.getNegate());
    		    			setAttributesB(attribsB, cb.getSameAttributes());
    		    		}
    		    	}
    		    	else if (node instanceof Parameter) {
    		    		Parameter pb = (Parameter) node;
    		    		if(pb.getCompareStatus().equals("SAME")||pb.getCompareStatus().equals("NOT SAME")) {
    		    			Parameter pa = ((Parameter)pb.getTreePathOfCounterpart().getLastPathComponent());
    		    			TreePath pathA = ((Parameter) node).getTreePathOfCounterpart();
    		    			jTreeA.setSelectionPath(pathA);
    		    			jTreeA.scrollPathToVisible(pathA);
    		    			List<Boolean> sameAttribsB = new ArrayList<Boolean>();
    		    			List<String> attribsB = new ArrayList<String>();
    		    			attribsB.add("name: "+pb.getName());
    		    			attribsB.add("value: "+pb.getValue());
    		    			if (pb.getCompareStatus().equals("SAME")) {
    		    				sameAttribsB.add(true); 
    		    				sameAttribsB.add(true); 
    		    			} else if (pb.getCompareStatus().equals("NOT SAME")) {
    		    				sameAttribsB.add(true); 
    		    				sameAttribsB.add(false);
    		    			}
    		    			setAttributesB(attribsB, sameAttribsB);
    		    			
    		    			List<Boolean> sameAttribsA = new ArrayList<Boolean>();
    		    			List<String> attribsA = new ArrayList<String>();
    		    			attribsA.add("name: "+pa.getName());
    		    			attribsA.add("value: "+pa.getValue());
    		    			if (pa.getCompareStatus().equals("SAME")) {
    		    				sameAttribsA.add(true); 
    		    				sameAttribsA.add(true); 
    		    			} else if (pa.getCompareStatus().equals("NOT SAME")) {
    		    				sameAttribsA.add(true); 
    		    				sameAttribsA.add(false);
    		    			}
    		    			setAttributesA(attribsA, sameAttribsA);
    		    		}
    		    		else { jTreeA.clearSelection(); 
    		    			clearAttributeTextA();
    			    		List<String> attribsB = new ArrayList<String>();
    		    			attribsB.add("name: "+pb.getName());
    		    			attribsB.add("value: "+pb.getValue());
    		    			setAttributesB(attribsB, new ArrayList<Boolean>());
    		    		}
    		    	}
    		        
    		    }
    		    currentTree="B";
    		}
    	});
	}
	
	public void setAttributesA(List<String> attribs, List<Boolean> sameAttribs){
		attribTextPaneA.setText("");
		StyledDocument sd = attribTextPaneA.getStyledDocument();
		Style style = attribTextPaneA.addStyle("Style", null);
		for (int i=0;i<attribs.size();i++) {
			if(!attribs.isEmpty()&&!sameAttribs.isEmpty()) {
				if (sameAttribs.get(i)) {
					StyleConstants.setForeground(style, Color.GREEN);
			        try { sd.insertString(sd.getLength(),attribs.get(i) + "\n",style); }
			        catch (BadLocationException e){}
				} else { StyleConstants.setForeground(style, Color.RED);
			        try { sd.insertString(sd.getLength(),attribs.get(i) + "\n",style); }
			        catch (BadLocationException e){} }
			} else { StyleConstants.setForeground(style, Color.BLACK);
		        try { sd.insertString(sd.getLength(),attribs.get(i) + "\n",style); }
		        catch (BadLocationException e){} }
		}
	}
	public void clearAttributeTextA(){
		attribTextPaneA.setText("");
	}
	public void setAttributesB(List<String> attribs, List<Boolean> sameAttribs){
		attribTextPaneB.setText("");
		StyledDocument sd = attribTextPaneB.getStyledDocument();
		Style style = attribTextPaneB.addStyle("Style", null);
		for (int i=0;i<attribs.size();i++) {
			if(!attribs.isEmpty()&&!sameAttribs.isEmpty()) {
				if (sameAttribs.get(i)) {
					StyleConstants.setForeground(style, Color.GREEN);
			        try { sd.insertString(sd.getLength(),attribs.get(i) + "\n",style); }
			        catch (BadLocationException e){}
				} else { StyleConstants.setForeground(style, Color.RED);
			        try { sd.insertString(sd.getLength(),attribs.get(i) + "\n",style); }
			        catch (BadLocationException e){} }
			} else { StyleConstants.setForeground(style, Color.BLACK);
		        try { sd.insertString(sd.getLength(),attribs.get(i) + "\n",style); }
		        catch (BadLocationException e){} }
		}
	}
	public void clearAttributeTextB(){
		attribTextPaneB.setText("");
	}
	
	public JMenuBar createMenuBar() {
		JMenuBar menuBar = new JMenuBar();
		JMenu fileMenu = new JMenu("File");
		JMenu helpMenu = new JMenu("Help");
		JMenuItem closeItem = new JMenuItem("Exit");
		JMenuItem tutorialItem = new JMenuItem("Tutorial");
		JMenuItem aboutItem = new JMenuItem("About");
		
		closeItem.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				System.exit(0);
			}
		});
		tutorialItem.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				JOptionPane.showMessageDialog(ComparatorView.this, "\nICONS:\nRed icon - both elements from CALC GROUP A and CALC GROUP B and/or their sub-elements contain differences"
																	+ "\nGreen icon - both elements have no differences"
																	+ "\nWhite icon - the other CALC GROUP does not contain an instance of this element"
																	+ "\n\nNOTE: PARAMETERS are sorted and displayed automatically in alphabetical order",
																	"Help", JOptionPane.INFORMATION_MESSAGE);
			}
		});
		aboutItem.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				JOptionPane.showMessageDialog(ComparatorView.this, "Author: John Tan", "About", JOptionPane.INFORMATION_MESSAGE);
			}
		});
		fileMenu.add(closeItem);
		menuBar.add(fileMenu);
		helpMenu.add(tutorialItem);
		helpMenu.add(aboutItem);
		menuBar.add(helpMenu);
		
		return menuBar;
	}
}
