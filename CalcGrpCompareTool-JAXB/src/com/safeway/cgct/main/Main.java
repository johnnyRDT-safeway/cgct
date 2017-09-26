/**
 * @author      John Robert Tan
 * @version
 */

package com.safeway.cgct.main;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import javax.xml.bind.Unmarshaller;

import com.safeway.cgct.ui.FileChooserView;

import javax.swing.*;
import javax.swing.UIManager.LookAndFeelInfo;

public class Main{
	public static void main(String[] args) throws UnsupportedLookAndFeelException {
		
		/* File file = new File("C:/Users/JTAN010/Desktop/DRAFT/017-PHNX.xml");
		JAXBContext jaxbContext = JAXBContext.newInstance(Calcgroup.class);
	    Unmarshaller jaxbUnmarshaller = jaxbContext.createUnmarshaller();
	    Calcgroup calcGroupA = (Calcgroup) jaxbUnmarshaller.unmarshal(file);
	    
	    Marshaller jaxbMarshaller = jaxbContext.createMarshaller();
		
		// output pretty printed
		jaxbMarshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);

		jaxbMarshaller.marshal(calcGroupA, file);
		jaxbMarshaller.marshal(calcGroupA, System.out); */
		
		try {
		    for (LookAndFeelInfo info : UIManager.getInstalledLookAndFeels()) {
		        if ("Nimbus".equals(info.getName())) {
		            UIManager.setLookAndFeel(info.getClassName());
		            break;
		        }
		    }
		} catch (Exception e) {
		    // If Nimbus is not available, fall back to cross-platform
		    try {
		        UIManager.setLookAndFeel(UIManager.getCrossPlatformLookAndFeelClassName());
		    } catch (Exception ex) {
		        // not worth my time
		    }
		}
		
        FileChooserView fcFrame = new FileChooserView();
        fcFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
 
        fcFrame.pack();
        fcFrame.setLocationRelativeTo(null);
        fcFrame.setVisible(true);
        
	}
	
}
