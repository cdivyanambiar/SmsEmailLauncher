package com.vd.Lauch;

import java.awt.Color;
import java.awt.Rectangle;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import javax.swing.SpringLayout;
import javax.swing.border.BevelBorder;

public class SignUpAdmin2 extends JFrame {
	static SignUpAdmin2 mw;
	JPanel registerForm ;

	int x = 40 ;  
	int y = 30;
	int width = 150; 
	int height = 30; 
	int xIncrease = 115;
	int yIncrease  = height+5; 

	public SignUpAdmin2() {
		registerForm = ComponetCreator.createPanel(Color.gray, new BevelBorder(BevelBorder.RAISED, null, null, null, null));
		
		/* name */ 
		JLabel name = ComponetCreator.createLabel("Name : ", Color.WHITE,Color.WHITE,ComponetCreator.fontLabel,new Rectangle(x, y, width,height));		
		registerForm.add(name);

		JTextField nameInput = ComponetCreator.createTextField(ComponetCreator.fontInput, new Rectangle(x+xIncrease, y, width,height));
		nameInput.setColumns(10);
		registerForm.add(nameInput);
		
		/* Mobile */
		JLabel mobileNo = ComponetCreator.createLabel("Mobile : ", Color.WHITE,Color.WHITE,ComponetCreator.fontLabel,new Rectangle(x, y+(1*yIncrease), width,height));		
		registerForm.add(mobileNo);

		JTextField mobileNoInput = ComponetCreator.createTextField(ComponetCreator.fontInput, new Rectangle((x+xIncrease), (y+(1*yIncrease)), width,height));
		mobileNoInput.setColumns(10);
		registerForm.add(mobileNoInput);
		
		/* email */
		JLabel emailaddress = ComponetCreator.createLabel("Email : ", Color.WHITE,Color.WHITE,ComponetCreator.fontLabel,new Rectangle(x, (y+(2*yIncrease)), width,height));		
		registerForm.add(emailaddress);

		JTextField emailaddressInput = ComponetCreator.createTextField(ComponetCreator.fontInput, new Rectangle((x+xIncrease), (y+(2*yIncrease)), width,height));
		emailaddressInput.setColumns(10);
		registerForm.add(emailaddressInput);
		
		/* password */
		JLabel lblPassword = new JLabel("Password:");
		lblPassword.setBounds(new Rectangle(x, y+(3*yIncrease), width,height));
		registerForm.add(lblPassword);
		
        final JPasswordField pfPassword = new JPasswordField(20);
        pfPassword.setBounds(new Rectangle((x+xIncrease), (y+(3*yIncrease)), width,height));
        registerForm.add(pfPassword);
        
        /* retype password */
		JLabel lblRetypePassword = new JLabel("Retype Password:");
		lblRetypePassword.setBounds(new Rectangle(x, y+(4*yIncrease), width,height));
		registerForm.add(lblRetypePassword);
		
        final JPasswordField pfRetypePassword = new JPasswordField(20);
        pfRetypePassword.setBounds(new Rectangle((x+xIncrease), (y+(4*yIncrease)), width,height));
        registerForm.add(pfRetypePassword);
        
        /* retype password */
		JLabel authKey = new JLabel("Auth Key: ");
		authKey.setBounds(new Rectangle(x, y+(5*yIncrease), width,height));
		registerForm.add(lblRetypePassword);
		
        final JPasswordField authKeyInput = new JPasswordField(20);
        authKeyInput.setBounds(new Rectangle((x+xIncrease), (y+(5*yIncrease)), width,height));
        registerForm.add(authKeyInput);
        
		
		JButton addDetails = ComponetCreator.createButton("Add Details", ComponetCreator.fontInput,new Rectangle((x+xIncrease), (y+(6*yIncrease)), width,height),Color.white);		
		registerForm.add(addDetails);
		
		ComponetCreator.createPanel(Color.gray, new BevelBorder(BevelBorder.RAISED, null, null, null, null));
		this.add(registerForm);
	}
	public static void main(String[] args) {
		mw = new SignUpAdmin2();
		mw.setTitle("Finwizz SMS/EMAIL Launcher");
		mw.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		Rectangle registerAdmin_rect  = new Rectangle(300, 140, 500, 400);
		mw.setBounds(registerAdmin_rect);
		mw.setUndecorated(false);
		mw.setVisible(true);
		mw.setIconImage(new ImageIcon("logo.png").getImage());

	}
}
