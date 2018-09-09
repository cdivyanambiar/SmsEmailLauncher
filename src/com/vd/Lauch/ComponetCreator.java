package com.vd.Lauch;

import java.awt.Color;
import java.awt.Font;
import java.awt.Rectangle;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.border.BevelBorder;

public class ComponetCreator {
	
	final static Font fontInput = new Font("sans-serif", Font.BOLD, 20);
	final static Font fontLabel = new Font("Comic Sans MS", Font.BOLD, 17);
	
	public static JPanel createPanel(Color color, BevelBorder border) {
		JPanel panel = new JPanel();
		panel.setBackground(color);
		panel.setLayout(null);
		panel.setBorder(border);
		return panel;
    }
	public static JButton createButton(String text, Font butFont,Rectangle r,Color background) {
		JButton button = new JButton();
		button.setText(text);
		button.setFont(butFont);
		button.setBounds(r);
		button.setBackground(background);
		return button;
    }
	public static JLabel createLabel(String text, Color foreground, Color background, Font font, Rectangle r) {
		JLabel label = new JLabel(text);
		label.setForeground(foreground);
		label.setBackground(background);
		label.setLayout(null);
		label.setFont(font);
		label.setBounds(r);
		return label;				
    }
	public static JTextField createTextField(Font font, Rectangle r) {
		JTextField textField = new JTextField();
		textField.setBounds(r);				
		textField.setFont(font);
		return textField;
	}

	public static JTextArea createTextArea(Font font, Rectangle r) {
		JTextArea textArea = new JTextArea();
		textArea.setBounds(r);				
		textArea.setFont(font);
		textArea.setLineWrap(true);
		return textArea;
	}

}
