package com.vd.Lauch;

import java.awt.*;
import java.awt.event.*;
import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStreamReader;

import javax.swing.*;
import javax.swing.border.BevelBorder;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;

public class smsEmailLauncher extends JFrame {
	static smsEmailLauncher mw;
	JButton start;
	JLabel image;
	JTabbedPane tab1;
	JPanel panel;
	JPanel panel1;
	JPanel panel2;
	Container pane;

	public smsEmailLauncher() {
		panel = new JPanel();
		panel.setBackground(Color.gray);
		panel.setLayout(null);
		panel.setBorder(new BevelBorder(BevelBorder.RAISED, null, null, null, null));
		start = new JButton();
		start.setText("START");
		Font myFont = new Font("Serif", Font.BOLD, 18);
		start.setFont(myFont);
		panel.setLayout(null);
		panel.add(start);

		start.setBounds(1000, 300, 200, 80);
		start.setBackground(Color.white);
		image = new JLabel();
		image.setBounds(50, 150, 800, 350);
		image.setBackground(Color.white);
		image.setIcon(new ImageIcon("welcome.png"));
		panel.add(image);
		panel1 = new JPanel();
		panel1.setLayout(null);
		panel1.setBackground(Color.gray);

		panel2 = new JPanel();
		panel2.setLayout(null);
		panel2.setBackground(Color.gray);

		Font myFont1 = new Font("Serif", Font.BOLD, 18);
		tab1 = new JTabbedPane();
		tab1.setBounds(50, 50, 1300, 450);
		tab1.setBorder(BorderFactory.createMatteBorder(24, 6, 12, 6, Color.blue));
		tab1.setFont(myFont1);
		tab1.setBorder(BorderFactory.createTitledBorder("Welcome...!!!"));
		tab1.add(" SMS ", panel1);
		tab1.add(" EMAIL ", panel2);

		pane = getContentPane();
		pane.add(panel);

		/* SMS Section */
		Font fontSms = new Font("sans-serif", Font.BOLD, 20);

		JLabel mobile = new JLabel("Mobile Number : ");
		mobile.setForeground(Color.WHITE);
		mobile.setBackground(Color.WHITE);
		mobile.setFont(new Font("Comic Sans MS", Font.BOLD, 17));
		mobile.setBounds(400, 150, 150, 30);
		panel1.add(mobile);

		JTextField mobileInput = new JTextField();
		mobileInput.setBounds(555, 150, 300, 30);
		panel1.add(mobileInput);
		mobileInput.setColumns(10);
		mobileInput.setFont(fontSms);

		JLabel message = new JLabel("Message : ");
		message.setForeground(Color.WHITE);
		message.setBackground(Color.WHITE);
		message.setFont(new Font("Comic Sans MS", Font.BOLD, 17));
		message.setBounds(400, 190, 150, 30);
		panel1.add(message);

		JTextArea messageInput = new JTextArea();
		messageInput.setBounds(555, 190, 300, 150);
		panel1.add(messageInput);
		messageInput.setColumns(10);
		messageInput.setFont(fontSms);
		messageInput.setLineWrap(true);

		JButton sendSms = new JButton();
		sendSms.setText("Send SMS");
		sendSms.setBounds(625, 350, 150, 50);
		sendSms.setBackground(Color.white);
		sendSms.setFont(fontSms);
		panel1.add(sendSms);

		JLabel wordCount = new JLabel("", SwingConstants.RIGHT);
		wordCount.setBounds(860, 200, 30, 10);
		wordCount.setForeground(Color.WHITE);
		wordCount.setHorizontalTextPosition(SwingConstants.RIGHT);
		panel1.add(wordCount);
		JLabel wordCountLimit = new JLabel("/ 160");
		wordCountLimit.setBounds(890, 200, 30, 10);
		wordCountLimit.setForeground(Color.WHITE);
		panel1.add(wordCountLimit);

		messageInput.getDocument().addDocumentListener(new DocumentListener() {

			@Override
			public void removeUpdate(DocumentEvent e) {
				wordCount.setText("" + messageInput.getText().length());
			}

			@Override
			public void insertUpdate(DocumentEvent e) {
				wordCount.setText("" + messageInput.getText().length());

			}

			@Override
			public void changedUpdate(DocumentEvent arg0) {
				wordCount.setText("" + messageInput.getText().length());
			}
		});
		/* EMAIL Section */
		Font fontEmail = new Font("sans-serif", Font.BOLD, 20);

		JLabel email = new JLabel("To : ");
		email.setForeground(Color.WHITE);
		email.setBackground(Color.WHITE);
		email.setFont(new Font("Comic Sans MS", Font.BOLD, 17));
		email.setBounds(400, 150, 150, 30);
		panel2.add(email);

		JTextField emailInput = new JTextField();
		emailInput.setBounds(555, 150, 350, 30);
		panel2.add(emailInput);
		emailInput.setColumns(10);
		emailInput.setFont(fontEmail);

		JLabel emailSubject = new JLabel("Subject : ");
		emailSubject.setForeground(Color.WHITE);
		emailSubject.setBackground(Color.WHITE);
		emailSubject.setFont(new Font("Comic Sans MS", Font.BOLD, 17));
		emailSubject.setBounds(400, 190, 150, 30);
		panel2.add(emailSubject);

		JTextField emailSubjectInput = new JTextField();
		emailSubjectInput.setBounds(555, 190, 800, 50);
		panel2.add(emailSubjectInput);
		emailSubjectInput.setColumns(10);
		emailSubjectInput.setFont(fontEmail);

		JLabel emailContent = new JLabel("Email : ");
		emailContent.setForeground(Color.WHITE);
		emailContent.setBackground(Color.WHITE);
		emailContent.setFont(new Font("Comic Sans MS", Font.BOLD, 17));
		emailContent.setBounds(400, 250, 150, 30);
		panel2.add(emailContent);

		JTextArea emailContentInput = new JTextArea();
		emailContentInput.setBounds(555, 250, 800, 400);
		panel2.add(emailContentInput);
		emailContentInput.setColumns(10);
		emailContentInput.setFont(fontEmail);
		emailContentInput.setLineWrap(true);

		JButton sendEmail = new JButton();
		sendEmail.setText("Send Email");
		sendEmail.setBounds(850, 660, 150, 50);
		sendEmail.setBackground(Color.white);
		sendEmail.setFont(fontEmail);
		panel2.add(sendEmail);

		JLabel statusLabel = new JLabel("@copyright V&D Solutions");
		statusLabel.setForeground(Color.white);
		statusLabel.setFont(new Font("Comic Sans MS", Font.BOLD, 17));
		statusLabel.setBounds(1300, 700, 300, 300);
		statusLabel.setHorizontalAlignment(SwingConstants.LEFT);
		panel.add(statusLabel);

		start.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				pane.removeAll();
				pane.add(tab1);
				pane.revalidate();
				pane.repaint();

			}
		});

		sendSms.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				String numbers = mobileInput.getText();
				String message = messageInput.getText();
				String command = "C:/Users/dhimate/AppData/Local/Programs/Python/Python37-32/python.exe fast2sms.py"
						+ " -n \"" + numbers + "\" -m \"" + message + "\"";
				System.out.println(command);
				StringBuffer output = new StringBuffer();
				try {
					Process p = Runtime.getRuntime().exec(command);
					BufferedReader stdInput = new BufferedReader(new InputStreamReader(p.getInputStream()));

					BufferedReader stdError = new BufferedReader(new InputStreamReader(p.getErrorStream()));

					// read the output from the command
					System.out.println("Here is the standard output of the command:\n");
					String s = null;
					while ((s = stdInput.readLine()) != null) {
						output.append(s);
					}

					// read any errors from the attempted command
					System.out.println("Here is the standard error of the command (if any):\n");
					while ((s = stdError.readLine()) != null) {
						output.append(s);
					}
				} catch (IOException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
					JOptionPane.showMessageDialog(mw, "Failed to send SMS : " + output);
				}
				JOptionPane.showMessageDialog(mw, "successfully sent SMS : " + output);
			}
		});

		sendEmail.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				String to = emailInput.getText();
				String subject = emailSubjectInput.getText();
				String emailContent = emailContentInput.getText();
				String command = "C:/Users/dhimate/AppData/Local/programs/Python/Python37-32/python.exe fastGmail.py"
						+ " -t \"" + to + "\" -s \"" + subject + "\" -b \"" + emailContent + "\"";
				System.out.println(command);
				StringBuffer output = new StringBuffer();
				try {
					Process p = Runtime.getRuntime().exec(command);
					BufferedReader stdInput = new BufferedReader(new InputStreamReader(p.getInputStream()));

					BufferedReader stdError = new BufferedReader(new InputStreamReader(p.getErrorStream()));

					// read the output from the command
					System.out.println("Here is the standard output of the command:\n");
					String s = null;
					while ((s = stdInput.readLine()) != null) {
						output.append(s);
					}

					// read any errors from the attempted command
					System.out.println("Here is the standard error of the command (if any):\n");
					while ((s = stdError.readLine()) != null) {
						output.append(s);
					}
				} catch (IOException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
					JOptionPane.showMessageDialog(mw, "Failed to send EMAIL : " + output);
				}
				JOptionPane.showMessageDialog(mw, "successfully sent EMAIL : " + output);
			}
		});
	}

	public static void main(String[] args) {
		mw = new smsEmailLauncher();
		mw.setTitle("Finwizz SMS/EMAIL Launcher");
		mw.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		mw.setExtendedState(JFrame.MAXIMIZED_BOTH);
		mw.setUndecorated(false);
		mw.setVisible(true);
		mw.setIconImage(new ImageIcon("logo.png").getImage());

	}
}