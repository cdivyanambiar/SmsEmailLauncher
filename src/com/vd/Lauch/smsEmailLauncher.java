package com.vd.Lauch;

import java.awt.Color;
import java.awt.Component;
import java.awt.Container;
import java.awt.Font;
import java.awt.Rectangle;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.swing.BorderFactory;
import javax.swing.DefaultListModel;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JScrollPane;
import javax.swing.JTabbedPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.ListCellRenderer;
import javax.swing.ListModel;
import javax.swing.ListSelectionModel;
import javax.swing.SpringLayout;
import javax.swing.SwingConstants;
import javax.swing.UIManager;
import javax.swing.border.BevelBorder;
import javax.swing.border.Border;
import javax.swing.border.EmptyBorder;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;

import com.vd.sqlite.DBConnection;

public class smsEmailLauncher extends JFrame {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	String sessionName = null;
	String adminid = "0";
	static smsEmailLauncher mw;
	final JFrame frame = new JFrame("Finwizz Login");
	JButton login;
	JButton signup;
	JLabel image;
	JTabbedPane tab1;
	JPanel startPanel;
	JPanel addExecutives;
	JPanel smsPanel;
	JPanel emailPanel;
	Container pane;
	DBConnection dbConnection;

	final DefaultListModel<JCheckBox> model;
	final JCheckBoxList numeberListSms;
	final JCheckBoxList emailListEmail;

	public smsEmailLauncher() {

		dbConnection = new DBConnection();
		/* For tabbed pane */
		Font tpaneFont = new Font("Serif", Font.BOLD, 18);
		tab1 = new JTabbedPane();
		tab1.setBounds(50, 50, 1300, 450);
		tab1.setBorder(BorderFactory.createMatteBorder(24, 6, 12, 6, Color.blue));
		tab1.setFont(tpaneFont);
		tab1.setBorder(BorderFactory.createTitledBorder("Welcome...!!!"));

		/* home page */
		login = ComponetCreator.createButton("LOGIN", new Font("Serif", Font.BOLD, 18),
				new Rectangle(1000, 300, 100, 50), Color.white);
		signup = ComponetCreator.createButton("SIGNUP", new Font("Serif", Font.BOLD, 18),
				new Rectangle(1150, 300, 110, 50), Color.white);
		startPanel = ComponetCreator.createPanel(Color.gray,
				new BevelBorder(BevelBorder.RAISED, null, null, null, null));
		startPanel.add(login);
		startPanel.add(signup);

		image = new JLabel();
		image.setBounds(50, 150, 800, 350);
		image.setBackground(Color.white);
		image.setIcon(new ImageIcon("C:/Python/FW/welcome.png"));
		startPanel.add(image);

		pane = getContentPane();
		pane.add(startPanel);

		/* Add executive section */
		addExecutives = ComponetCreator.createPanel(Color.gray,
				new BevelBorder(BevelBorder.RAISED, null, null, null, null));
		tab1.add(" ADD EXECUTIVES ", addExecutives);

		JLabel name = ComponetCreator.createLabel("Name : ", Color.WHITE, Color.WHITE, ComponetCreator.fontLabel,
				new Rectangle(400, 150, 150, 30));
		addExecutives.add(name);

		JTextField nameInput = ComponetCreator.createTextField(ComponetCreator.fontInput,
				new Rectangle(555, 150, 300, 30));
		nameInput.setColumns(10);
		addExecutives.add(nameInput);

		JLabel mobileNo = ComponetCreator.createLabel("Mobile Number : ", Color.WHITE, Color.WHITE,
				ComponetCreator.fontLabel, new Rectangle(400, 190, 150, 30));
		addExecutives.add(mobileNo);

		JTextField mobileNoInput = ComponetCreator.createTextField(ComponetCreator.fontInput,
				new Rectangle(555, 190, 300, 30));
		mobileNoInput.setColumns(10);
		addExecutives.add(mobileNoInput);

		JLabel emailaddress = ComponetCreator.createLabel("Email : ", Color.WHITE, Color.WHITE,
				ComponetCreator.fontLabel, new Rectangle(400, 230, 150, 30));
		addExecutives.add(emailaddress);

		JTextField emailaddressInput = ComponetCreator.createTextField(ComponetCreator.fontInput,
				new Rectangle(555, 230, 300, 30));
		emailaddressInput.setColumns(10);
		addExecutives.add(emailaddressInput);

		JButton addDetails = ComponetCreator.createButton("Add Details", ComponetCreator.fontInput,
				new Rectangle(650, 300, 150, 50), Color.white);
		addExecutives.add(addDetails);

		addDetails.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				System.out.println("adminid = " + adminid);
				if (nameInput.getText().isEmpty() || mobileNoInput.getText().isEmpty()
						|| emailaddressInput.getText().isEmpty()) {
					JOptionPane.showMessageDialog(mw,
							"Please fill all the fields");
				} else {
					// i have to make reference of parent table
					String sql = "CREATE TABLE IF NOT EXISTS ExecutiveDeatils ("
							+ "	id integer PRIMARY KEY AUTOINCREMENT," + "	adminId integer NOT NULL,"
							+ "	name text NOT NULL," + "	mobile text NOT NULL," + "	email text NOT NULL" + ");";
					dbConnection.createNewTable(sql);

					String insertSql = "INSERT INTO ExecutiveDeatils(adminId,name,mobile,email) values(?,?,?,?)";

					Map<String, String> colValue = new HashMap<String, String>();
					colValue.put("adminId", adminid);
					colValue.put("name", nameInput.getText());
					colValue.put("mobile", mobileNoInput.getText());
					colValue.put("email", emailaddressInput.getText());
					dbConnection.insertExecutive(insertSql, colValue);
					dbConnection.selectAll();
					JOptionPane.showMessageDialog(mw, "successfully added new Executive : " + nameInput.getText());
					nameInput.setText("");
					mobileNoInput.setText("");
					emailaddressInput.setText("");
				}

			}
		});

		/* SMS Section */
		smsPanel = ComponetCreator.createPanel(Color.gray, new BevelBorder(BevelBorder.RAISED, null, null, null, null));
		tab1.add(" SMS ", smsPanel);

		JLabel mobile = ComponetCreator.createLabel("Mobile Number : ", Color.WHITE, Color.WHITE,
				ComponetCreator.fontLabel, new Rectangle(120, 20, 150, 30));
		smsPanel.add(mobile);

		// JTextField mobileInput =
		// ComponetCreator.createTextField(ComponetCreator.fontInput,
		// new Rectangle(655, 50, 300, 30));
		// mobileInput.setColumns(10);
		// smsPanel.add(mobileInput);

		JLabel message = ComponetCreator.createLabel("Message : ", Color.WHITE, Color.WHITE, ComponetCreator.fontLabel,
				new Rectangle(500, 50, 150, 30));
		smsPanel.add(message);

		JTextArea messageInput = ComponetCreator.createTextArea(ComponetCreator.fontInput,
				new Rectangle(655, 50, 300, 150));
		messageInput.setColumns(10);
		smsPanel.add(messageInput);

		JButton sendSms = ComponetCreator.createButton("Send SMS", ComponetCreator.fontInput,
				new Rectangle(725, 210, 150, 50), Color.white);
		smsPanel.add(sendSms);

		JLabel wordCount = new JLabel("", SwingConstants.RIGHT);
		wordCount.setBounds(960, 100, 30, 10);
		wordCount.setForeground(Color.WHITE);
		wordCount.setHorizontalTextPosition(SwingConstants.RIGHT);
		smsPanel.add(wordCount);
		JLabel wordCountLimit = new JLabel("/ 160");
		wordCountLimit.setBounds(990, 100, 30, 10);
		wordCountLimit.setForeground(Color.WHITE);
		smsPanel.add(wordCountLimit);

		model = new DefaultListModel<JCheckBox>();
		numeberListSms = new JCheckBoxList(model);
		JScrollPane scrollPaneSms = new JScrollPane(numeberListSms);
		smsPanel.add(scrollPaneSms);

		scrollPaneSms.setBounds(120, 50, 300, 600);

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
		emailPanel = new JPanel();
		emailPanel.setLayout(null);
		emailPanel.setBackground(Color.gray);

		tab1.add(" EMAIL ", emailPanel);

		JLabel email = ComponetCreator.createLabel("To : ", Color.WHITE, Color.WHITE, ComponetCreator.fontLabel,
				new Rectangle(120, 20, 150, 30));
		emailPanel.add(email);

		// JTextField emailInput =
		// ComponetCreator.createTextField(ComponetCreator.fontInput,
		// new Rectangle(655, 50, 350, 30));
		// emailInput.setColumns(10);
		// emailPanel.add(emailInput);

		JLabel emailSubject = new JLabel("Subject : ");
		emailSubject.setForeground(Color.WHITE);
		emailSubject.setBackground(Color.WHITE);
		emailSubject.setFont(new Font("Comic Sans MS", Font.BOLD, 17));
		emailSubject.setBounds(500, 50, 150, 30);
		emailPanel.add(emailSubject);

		JTextField emailSubjectInput = ComponetCreator.createTextField(ComponetCreator.fontInput,
				new Rectangle(655, 50, 800, 50));
		emailSubjectInput.setColumns(10);
		emailPanel.add(emailSubjectInput);

		JLabel emailContent = ComponetCreator.createLabel("Email : ", Color.WHITE, Color.WHITE,
				ComponetCreator.fontLabel, new Rectangle(500, 110, 150, 30));
		emailPanel.add(emailContent);

		JTextArea emailContentInput = ComponetCreator.createTextArea(ComponetCreator.fontInput,
				new Rectangle(655, 110, 800, 400));
		emailContentInput.setColumns(10);
		emailPanel.add(emailContentInput);

		JButton sendEmail = ComponetCreator.createButton("Send Email", ComponetCreator.fontInput,
				new Rectangle(950, 520, 150, 50), Color.white);
		emailPanel.add(sendEmail);

//		JLabel statusLabel = ComponetCreator.createLabel("@copyright V&D Solutions", Color.WHITE, Color.WHITE,
//				ComponetCreator.fontLabel, new Rectangle(1400, 600, 300, 300));
//		statusLabel.setHorizontalAlignment(SwingConstants.LEFT);
//		startPanel.add(statusLabel);

		emailListEmail = new JCheckBoxList(model);
		JScrollPane scrollPaneEmail = new JScrollPane(emailListEmail);
		emailPanel.add(scrollPaneEmail);
		scrollPaneEmail.setBounds(120, 50, 300, 600);

		login.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				doLogin();
			}
		});

		signup.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				SignUpAdmin signUpAdmin = new SignUpAdmin();
				signUpAdmin.setLocation(800, 500);
				signUpAdmin.SignUp();
			}
		});

		tab1.addMouseListener(new MouseListener() {

			@Override
			public void mouseReleased(MouseEvent e) {
				// TODO Auto-generated method stub
				JTabbedPane pane = (JTabbedPane) e.getComponent();
				int tabIndex = pane.getSelectedIndex();
				System.out.println("tab index" + tabIndex);
				if (tabIndex == 1) {
					/* have to do this when sms tabbed pane clicked */
					numeberListSms.modelSelected.removeAll(numeberListSms.modelSelected);
					Map<String, String> executiveSmsDetails = dbConnection
							.selectQueryForExecutivesSms("Select * from ExecutiveDeatils where adminId = " + adminid);
					model.clear();
					for (Map.Entry<String, String> entry : executiveSmsDetails.entrySet())
						model.addElement(new JCheckBox(entry.getValue() + " : " + entry.getKey()));
				} else if (tabIndex == 2) {
					/* have to do this when sms tabbed pane clicked */
					emailListEmail.modelSelected.removeAll(emailListEmail.modelSelected);
					Map<String, String> executiveSmsDetails = dbConnection
							.selectQueryForExecutivesEmail("Select * from ExecutiveDeatils where adminId = " + adminid);
					model.clear();
					for (Map.Entry<String, String> entry : executiveSmsDetails.entrySet())
						model.addElement(new JCheckBox(entry.getValue() + " : " + entry.getKey()));
				}

			}

			@Override
			public void mousePressed(MouseEvent e) {
				// TODO Auto-generated method stub

			}

			@Override
			public void mouseExited(MouseEvent e) {
				// TODO Auto-generated method stub

			}

			@Override
			public void mouseEntered(MouseEvent e) {
				// TODO Auto-generated method stub

			}

			@Override
			public void mouseClicked(MouseEvent e) {
				// TODO Auto-generated method stub

			}
		});
		sendSms.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				StringBuffer numberList = new StringBuffer();
				numeberListSms.modelSelected
						.forEach(item -> numberList.append(item.getText().split(":")[1].trim() + ";"));
				String numbers = numberList != null && numberList.length() > 10? numberList.substring(0, numberList.length() - 1) : ""; 
				String message = messageInput.getText();
				if (numbers.isEmpty() || message.isEmpty()) {
					JOptionPane.showMessageDialog(mw,
							"Please select atleast one Mobile number and Enter text in Message box ");
				} else {
					String command = "C:/Python/python.exe C:/Python/FW/fast2sms.py" + " -n \"" + numbers + "\" -m \""
							+ message + "\"";
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
					messageInput.setText("");
				}
			}
		});

		sendEmail.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				StringBuffer emailList = new StringBuffer();
				emailListEmail.modelSelected
						.forEach(item -> emailList.append(item.getText().split(":")[1].trim() + ";"));
				String to = emailList != null && emailList.length() > 3 ? emailList.substring(0, emailList.length() - 1) : ""; 
				String subject = emailSubjectInput.getText();
				String emailContent = emailContentInput.getText();
				if (to.isEmpty() || subject.isEmpty() || emailContent.isEmpty()) {
					JOptionPane.showMessageDialog(mw,
							"Please select atleast one Email Id and Enter text in Subject and Email section ");
				} else {
					String command = "C:/Python/python.exe C:/Python/FW/fastGmail.py" + " -t \"" + to + "\" -s \""
							+ subject + "\" -b \"" + emailContent + "\"";
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
					emailSubjectInput.setText("");
					emailContentInput.setText("");
				}
			}
		});

	}

	public void doLogin() {
		JLabel lblUserNamer = new JLabel("User Name:");
		JTextField userName = new JTextField(20);
		lblUserNamer.setLabelFor(userName);

		JLabel lblPassword = new JLabel("Password:");
		final JPasswordField password = new JPasswordField(20);
		lblPassword.setLabelFor(password);
		JButton clearGet = new JButton(" Clear ");

		JButton btnLogin = new JButton(" Login ");
		btnLogin.addActionListener(new ActionListener() {

			public void actionPerformed(ActionEvent e) {
				String sql = "SELECT name,password FROM AdminDetails where name='" + userName.getText().trim()
						+ "' AND password = '" + password.getText().trim() + "'";
				System.out.println(sql);

				String queryForSelectid = "SELECT id from AdminDetails where name = '" + userName.getText().trim()
						+ "'";
				System.out.println(queryForSelectid);
				List<Integer> ids = dbConnection.selectQuery(queryForSelectid);
				boolean isValid = dbConnection.CheckValidAdmin(sql);
				System.out.println(isValid);
				if (isValid) {
					sessionName = userName.getText().trim();
					adminid = ids.get(0).toString();
					pane.removeAll();
					pane.add(tab1);
					pane.revalidate();
					pane.repaint();
					frame.dispose();
				}
			}
		});

		JPanel panel = new JPanel();
		panel.setLayout(new SpringLayout());

		panel.add(lblUserNamer);
		panel.add(userName);
		panel.add(lblPassword);
		panel.add(password);
		panel.add(clearGet);
		panel.add(btnLogin);

		SpringUtilities.makeCompactGrid(panel, 3, 2, // rows, cols
				6, 6, // initX, initY
				6, 6); // xPad, yPad

		frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		frame.setSize(400, 150);
		frame.setLocation(500, 500);
		frame.getContentPane().add(panel);
		frame.setVisible(true);
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

@SuppressWarnings("serial")
class JCheckBoxList extends JList<JCheckBox> {
	protected static Border noFocusBorder = new EmptyBorder(1, 1, 1, 1);
	public final List<JCheckBox> modelSelected = new ArrayList<>();

	public JCheckBoxList() {
		setCellRenderer(new CellRenderer());
		addMouseListener(new MouseAdapter() {
			public void mousePressed(MouseEvent e) {
				int index = locationToIndex(e.getPoint());
				if (index != -1) {
					JCheckBox checkbox = (JCheckBox) getModel().getElementAt(index);
					if (!checkbox.isSelected()) {
						modelSelected.add(checkbox);
					} else {
						modelSelected.remove(checkbox);
					}
					checkbox.setSelected(!checkbox.isSelected());
					repaint();
				}
			}
		});
		setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
	}

	public JCheckBoxList(ListModel<JCheckBox> model) {
		this();
		setModel(model);

	}

	protected class CellRenderer implements ListCellRenderer<JCheckBox> {
		public Component getListCellRendererComponent(JList<? extends JCheckBox> list, JCheckBox value, int index,
				boolean isSelected, boolean cellHasFocus) {
			JCheckBox checkbox = value;

			// Drawing checkbox, change the appearance here
			checkbox.setBackground(isSelected ? getSelectionBackground() : getBackground());
			checkbox.setForeground(isSelected ? getSelectionForeground() : getForeground());
			checkbox.setEnabled(isEnabled());
			checkbox.setFont(getFont());
			checkbox.setFocusPainted(false);
			checkbox.setBorderPainted(true);
			checkbox.setBorder(isSelected ? UIManager.getBorder("List.focusCellHighlightBorder") : noFocusBorder);
			return checkbox;
		}
	}

}