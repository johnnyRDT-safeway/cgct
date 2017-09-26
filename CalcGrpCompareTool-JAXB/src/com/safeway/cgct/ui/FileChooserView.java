/**
 * @author      John Robert Tan
 * @version
 */

package com.safeway.cgct.ui;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;

import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import javax.xml.bind.Unmarshaller;

import com.safeway.cgct.objects.Calcgroup;

public class FileChooserView extends JFrame {
	JPanel fcPanel;
	Calcgroup calcGroupA;
	Calcgroup calcGroupB;
	JTextField fileATxtFld;
	JTextField fileBTxtFld;
	JFileChooser jfc;
	JButton browseButtonA;
	JButton browseButtonB;
	JButton compareButton;
	File fileA;
	File fileB;
	
	public FileChooserView() {
		this.setTitle("Please select files");
		//setDefaultLookAndFeelDecorated(true);
		
		fcPanel = new JPanel();
		fcPanel.setLayout(new BoxLayout(fcPanel, BoxLayout.Y_AXIS));
		
		fileATxtFld = new JTextField();
		fileATxtFld.setColumns(20);
		browseButtonA = new JButton("Browse...");
		browseButtonA.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				jfc = new JFileChooser();
		        //jfca.setMultiSelectionEnabled(true);
		        jfc.setFileSelectionMode(JFileChooser.FILES_ONLY);
		        int result = jfc.showDialog(FileChooserView.this,"Open/Save");
		        if(result == JFileChooser.APPROVE_OPTION)
		        {
		            fileA = jfc.getSelectedFile();
		            fileATxtFld.setText(fileA.getAbsolutePath());
		         }
			}
		});
		
		fileBTxtFld = new JTextField();
		fileBTxtFld.setColumns(20);
		browseButtonB = new JButton("Browse...");
		browseButtonB.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				jfc = new JFileChooser();
		        //jfca.setMultiSelectionEnabled(true);
		        jfc.setFileSelectionMode(JFileChooser.FILES_ONLY);
		        int result = jfc.showDialog(FileChooserView.this,"Open/Save");
		        if(result == JFileChooser.APPROVE_OPTION)
		        {
		            fileB = jfc.getSelectedFile();
		            fileBTxtFld.setText(fileB.getAbsolutePath());
		         }
			}
		});
		
		compareButton = new JButton("Compare Calc Groups");
		compareButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				fileA = new File(fileATxtFld.getText());
				fileB = new File(fileBTxtFld.getText());
//				String fileNameA = fileA.getName();
//				String fileNameB = fileB.getName();
				try {
					JAXBContext jaxbContext = JAXBContext.newInstance(Calcgroup.class);
					Unmarshaller jaxbUnmarshaller = jaxbContext.createUnmarshaller();
				    calcGroupA = (Calcgroup) jaxbUnmarshaller.unmarshal(fileA);
				    calcGroupB = (Calcgroup) jaxbUnmarshaller.unmarshal(fileB);
				    
				    /*Marshaller jaxbMarshaller = jaxbContext.createMarshaller();
					// output pretty printed
					jaxbMarshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);
					jaxbMarshaller.marshal(calcGroupA, fileA);
					jaxbMarshaller.marshal(calcGroupA, System.out);*/
					
					ComparatorView cFrame = new ComparatorView(FileChooserView.this, calcGroupA,calcGroupB, fileA, fileB);
					cFrame.setVisible(true);
					FileChooserView.this.setVisible(false);
				} catch (JAXBException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			    
			}
		});
		
		fcPanel.add(browseButtonA);
		fcPanel.add(fileATxtFld);
		fcPanel.add(browseButtonB);
		fcPanel.add(fileBTxtFld);
		fcPanel.add(compareButton);
		
		add(fcPanel);
		setJMenuBar(createMenuBar());
		
	}
	
	public JMenuBar createMenuBar() {
		JMenuBar menuBar = new JMenuBar();
		JMenu fileMenu = new JMenu("File");
		JMenu helpMenu = new JMenu("Help");
		JMenuItem closeItem = new JMenuItem("Exit");
		JMenuItem aboutItem = new JMenuItem("About");
		
		closeItem.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				System.exit(0);
			}
		});
		aboutItem.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				JOptionPane.showMessageDialog(FileChooserView.this, "Author: John Tan", "About", JOptionPane.INFORMATION_MESSAGE);
			}
		});
		fileMenu.add(closeItem);
		menuBar.add(fileMenu);
		helpMenu.add(aboutItem);
		menuBar.add(helpMenu);
		
		return menuBar;
	}
}
