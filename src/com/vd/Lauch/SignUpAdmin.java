package com.vd.Lauch;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowEvent;
import java.util.HashMap;
import java.util.Map;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import javax.swing.SpringLayout;

import com.vd.sqlite.DBConnection;

public class SignUpAdmin extends JFrame {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	static SignUpAdmin mw;
	JPanel registerForm;
	DBConnection dbConnection = new DBConnection();
	JFrame frame;

	public void SignUp() {
		registerForm = new JPanel();
		registerForm.setLayout(new SpringLayout());

		/* name */
		JLabel name = new JLabel("Name : ");
		registerForm.add(name);

		JTextField nameInput = new JTextField();
		nameInput.setColumns(10);
		registerForm.add(nameInput);

		/* Mobile */
		JLabel mobileNo = new JLabel("Mobile : ");
		registerForm.add(mobileNo);

		JTextField mobileNoInput = new JTextField();
		mobileNoInput.setColumns(10);
		registerForm.add(mobileNoInput);

		/* email */
		JLabel emailaddress = new JLabel("Email : ");
		registerForm.add(emailaddress);

		JTextField emailaddressInput = new JTextField();
		emailaddressInput.setColumns(10);
		registerForm.add(emailaddressInput);

		/* password */
		JLabel lblPassword = new JLabel("Password:");
		registerForm.add(lblPassword);

		final JPasswordField passwordInput = new JPasswordField(10);
		registerForm.add(passwordInput);

		/* retype password */
		JLabel lblRetypePassword = new JLabel("Retype Password:");
		registerForm.add(lblRetypePassword);

		final JPasswordField passwordRetypeInput = new JPasswordField(10);
		registerForm.add(passwordRetypeInput);

		/* retype password */
		JLabel authKey = new JLabel("Auth Key: ");
		registerForm.add(authKey);

		final JPasswordField authKeyInput = new JPasswordField(10);
		registerForm.add(authKeyInput);

		JButton clear = new JButton(" Clear ");
		registerForm.add(clear);

		clear.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				nameInput.setText("");
				mobileNoInput.setText("");
				passwordInput.setText("");
				authKeyInput.setText("");
				passwordRetypeInput.setText("");
				emailaddressInput.setText("");
			}
		});

		JButton addDetails = new JButton(" Sign Up ");
		registerForm.add(addDetails);
		addDetails.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {

				// SQL statement for creating a new table
				String sql = "CREATE TABLE IF NOT EXISTS AdminDetails (" + "	id integer PRIMARY KEY AUTOINCREMENT,"
						+ "	name text NOT NULL," + "	mobile text NOT NULL," + "	email text NOT NULL,"
						+ "	password text NOT NULL," + "	auth text" + ");";
				dbConnection.createNewTable(sql);

				String insertSql = "INSERT INTO AdminDetails(name,mobile,email,password,auth) values(?,?,?,?,?)";

				Map<String, String> colValue = new HashMap<String, String>();
				colValue.put("name", nameInput.getText());
				colValue.put("mobile", mobileNoInput.getText());
				colValue.put("email", emailaddressInput.getText());
				colValue.put("password", passwordInput.getText());
				colValue.put("auth", authKeyInput.getText());
				dbConnection.insert(insertSql, colValue);
				dbConnection.selectAll();
				frame.dispatchEvent(new WindowEvent(frame, WindowEvent.WINDOW_CLOSING));
			}
		});

		SpringUtilities.makeCompactGrid(registerForm, 7, 2, // rows, cols
				6, 6, // initX, initY
				6, 6); // xPad, yPad
		frame = new JFrame("Finwizz Login");
		frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		frame.setSize(400, 250);
		frame.setLocation(500, 500);
		frame.getContentPane().add(registerForm);
		frame.setVisible(true);

	}

}
