package com.vd.sqlite;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class DBConnection {

	String url = "jdbc:sqlite:finwizz.db";

	/**
	 * Create a new table in the finwizz database
	 *
	 */
	public void createNewTable(String sql) {
		try {
			Class.forName("org.sqlite.JDBC");

			try (Connection conn = DriverManager.getConnection(url); Statement stmt = conn.createStatement()) {
				// create a new table
				stmt.execute(sql);
			} catch (SQLException e) {
				System.out.println(e.getMessage());
			}
		} catch (ClassNotFoundException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
	}

	/**
	 * Insert a new row into table
	 */
	public void insert(String sql, Map<String, String> colValue) {
		try {
			Class.forName("org.sqlite.JDBC");

			try (Connection conn = DriverManager.getConnection(url);
					PreparedStatement pstmt = conn.prepareStatement(sql)) {
				// I have to generalize this method.
				// Iteratig not giving me th=in the exact order
				// for (Map.Entry<String, String> entry : colValue.entrySet()) {
				pstmt.setString(1, colValue.get("name"));
				pstmt.setString(2, colValue.get("mobile"));
				pstmt.setString(3, colValue.get("email"));
				pstmt.setString(4, colValue.get("password"));
				pstmt.setString(5, colValue.get("auth"));
				// }
				pstmt.executeUpdate();
			} catch (SQLException e) {
				System.out.println(e.getMessage());
			}
		} catch (ClassNotFoundException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
	}

	/**
	 * Insert a new row into table I hae to remove this., making insert generic
	 */
	public void insertExecutive(String sql, Map<String, String> colValue) {
		try {
			Class.forName("org.sqlite.JDBC");

			try (Connection conn = DriverManager.getConnection(url);
					PreparedStatement pstmt = conn.prepareStatement(sql)) {
				// I have to generalize this method.
				// Iteratig not giving me th=in the exact order
				// for (Map.Entry<String, String> entry : colValue.entrySet()) {
				pstmt.setInt(1, Integer.parseInt(colValue.get("adminId")));
				pstmt.setString(2, colValue.get("name"));
				pstmt.setString(3, colValue.get("mobile"));
				pstmt.setString(4, colValue.get("email"));
				// }
				pstmt.executeUpdate();
			} catch (SQLException e) {
				System.out.println(e.getMessage());
			}
		} catch (ClassNotFoundException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
	}

	/**
	 * select all rows in the Finwizzs table
	 */
	public void selectAll() {
		String sql = "SELECT id, name  FROM AdminDetails ";
		String url = "jdbc:sqlite:finwizz.db";
		try {
			Class.forName("org.sqlite.JDBC");

			try (Connection conn = DriverManager.getConnection(url);
					Statement stmt = conn.createStatement();
					ResultSet rs = stmt.executeQuery(sql)) {

				// loop through the result set
				while (rs.next()) {
					System.out.println(rs.getInt("id") + "\t" + rs.getString("name"));
				}
			} catch (SQLException e) {
				System.out.println(e.getMessage());
			}
		} catch (ClassNotFoundException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
	}

	/**
	 * select all rows in the Finwizzs table
	 */
	public boolean CheckValidAdmin(String query) {
		String url = "jdbc:sqlite:finwizz.db";
		boolean isValid = false;
		try {
			Class.forName("org.sqlite.JDBC");

			try (Connection conn = DriverManager.getConnection(url);
					Statement stmt = conn.createStatement();
					ResultSet rs = stmt.executeQuery(query)) {

				// loop through the result set
				if (rs.next()) {
					isValid = true;
				}
			} catch (SQLException e) {
				System.out.println(e.getMessage());
			}
		} catch (ClassNotFoundException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		return isValid;
	}

	/**
	 * select all rows in the Finwizzs table
	 */
	public List<Integer> selectQuery(String query) {
		String url = "jdbc:sqlite:finwizz.db";
		List<Integer> result = new ArrayList<>();
		try {
			Class.forName("org.sqlite.JDBC");

			try (Connection conn = DriverManager.getConnection(url);
					Statement stmt = conn.createStatement();
					ResultSet rs = stmt.executeQuery(query)) {
				// loop through the result set
				// I have generalize this
				while (rs.next()) {
					System.out.println(rs.getInt("id"));
					result.add(rs.getInt("id"));
				}
			} catch (SQLException e) {
				System.out.println(e.getMessage());
			}
		} catch (ClassNotFoundException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		return result;
	}

	/**
	 * select all rows in the Finwizzs table
	 */
	public Map<String, String> selectQueryForExecutivesSms(String query) {
		String url = "jdbc:sqlite:finwizz.db";
		Map<String, String> result = new HashMap<String, String>();
		try {
			Class.forName("org.sqlite.JDBC");

			try (Connection conn = DriverManager.getConnection(url);
					Statement stmt = conn.createStatement();
					ResultSet rs = stmt.executeQuery(query)) {

				// loop through the result set
				// I have generalize this
				while (rs.next()) {
					result.put(rs.getString("mobile"), rs.getString("name"));
				}
			} catch (SQLException e) {
				System.out.println(e.getMessage());
			}
		} catch (ClassNotFoundException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		return result;
	}

	/**
	 * select all rows in the Finwizzs table
	 */
	public Map<String, String> selectQueryForExecutivesEmail(String query) {
		String url = "jdbc:sqlite:finwizz.db";
		Map<String, String> result = new HashMap<String, String>();
		try {
			Class.forName("org.sqlite.JDBC");

			try (Connection conn = DriverManager.getConnection(url);
					Statement stmt = conn.createStatement();
					ResultSet rs = stmt.executeQuery(query)) {

				// loop through the result set
				// I have generalize this
				while (rs.next()) {
					result.put(rs.getString("email"), rs.getString("name"));
				}
			} catch (SQLException e) {
				System.out.println(e.getMessage());
			}
		} catch (ClassNotFoundException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		return result;
	}

	/**
	 * Delete a Finwizz specified by the id
	 *
	 * @param id
	 */
	public void delete() {
		String sql = "DELETE FROM AdminDetails";
		try {
			Class.forName("org.sqlite.JDBC");

			try (Connection conn = DriverManager.getConnection(url); Statement stmt = conn.createStatement()) {

				// set the corresponding param
				// execute the delete statement
				stmt.executeUpdate(sql);

			} catch (SQLException e) {
				System.out.println(e.getMessage());
			}
		} catch (ClassNotFoundException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
	}

	public void addToDb(String tbname, List<String> name) {
		try {
			String url = "jdbc:sqlite:finwizz.db";
			Class.forName("org.sqlite.JDBC");
			try (Connection conn = DriverManager.getConnection(url); Statement stmt = conn.createStatement();) {
				stmt.execute("CREATE TABLE IF NOT EXISTS " + tbname + "(id integer PRIMARY KEY AUTOINCREMENT,"
						+ "name text NOT NULL)");

				name.forEach((s) -> {
					try {
						stmt.execute("INSERT INTO " + tbname + "(name) VALUES('" + s + "')");
					} catch (SQLException e) {
						e.printStackTrace();
					}
				});
				System.out.println("Finished");
			} catch (SQLException e) {
				e.printStackTrace();
			}
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
	}

	public static void main(String[] args) {
		DBConnection dbConnection = new DBConnection();
		// List val = new ArrayList<String>();
		// val.add("x");
		//
		// dbConnection.addToDb("test",val);
		dbConnection.selectQuery("SELECT id, name from AdminDetails where name = 'Div' ");
		// dbConnection.delete();
		dbConnection.selectAll();
	}
}
