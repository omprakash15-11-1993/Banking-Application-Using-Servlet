package abc.com.model;

import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import com.sun.jdi.connect.spi.Connection;

public class Model {
	private static final String pwd = null;
	private static final String ACCNO = null;
	private String name;
	private String custid;
	private int accno;
	private String password;
	private int balance;
	private String email;
	private int raccno;

	private java.sql.Connection con;
	private PreparedStatement pstmt;
	private ResultSet res;
	private int bal;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getCustid() {
		return custid;
	}

	public void setCustid(String custid) {
		this.custid = custid;
	}

	public int getAccno() {
		return accno;
	}

	public void setAccno(int accno) {
		this.accno = accno;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public int getBalance() {
		return balance;
	}

	public void setBalance(int balance) {
		this.balance = balance;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public int getRaccno() {
		return raccno;
	}

	public void setRaccno(int raccno) {
		this.raccno = raccno;
	}

	public Model() throws Exception {
		Class.forName("com.mysql.jdbc.Driver");
		con = DriverManager.getConnection("jdbc:mysql://localhost:3306/BankApplication", "root", "root");
		System.out.println("Load the Driver and Established the Connection");

	}
	
	
	
	//REGISTER METHOD START FROM HERE

	public boolean register() throws SQLException {
		String s = "insert into abcbank values (?,?,?,?,?,?)";
		pstmt = con.prepareStatement(s);
		pstmt.setString(1, name);
		pstmt.setString(2, custid);
		pstmt.setInt(3, accno);
		pstmt.setString(4, password);
		pstmt.setInt(5, balance);
		pstmt.setString(6, email);

		int x = pstmt.executeUpdate();

		if (x > 0) {
			return true;
		}

		return false;
	}
	
	
	
	//Login method start from here

	public boolean login(String custId, String pass) throws SQLException {
		String s = "select * from ABCBank  where custid = ? and password = ?";
		System.out.println(custId + " " + pass);
		pstmt = con.prepareStatement(s);
		pstmt.setString(1, custId);
		 pstmt.setString(2, pass);

		ResultSet res = null;
		try {
			res = pstmt.executeQuery();
		} catch (SQLException e) {
			System.out.println("User & password is wrong");
		}
		
		while (res!=null && res.next() == true) {
			System.out.println("1");
			return true;
		}
		System.out.println("User & password is wrong");
		return false;

	}
	
	
	//CHECK BALANCE
	public boolean checkbalance() throws SQLException {
		String s = "select balance from ABCBank where accno = ?";
		pstmt = con.prepareStatement(s);
		pstmt.setInt(1, accno);
		ResultSet res = pstmt.executeQuery();
		
		while (res.next() == true) {
	    bal=res.getInt("BALANCE");
			System.out.println("1");
			return true;
		}

		return false;

	}

	
	
	
	
	// CHANGE PASSWORD START FROM HERE
	

	public boolean changePwd() throws SQLException {

		String s = "update ABCBank set pwd=? where accno=?";
		pstmt = con.prepareStatement(s);
		pstmt.setString(1, pwd);
		pstmt.setInt(2, accno);
		int x = pstmt.executeUpdate();

		if (x>0) {
			return true;
		}
		else {
		return false;
		}
	}
	
	
	
	
	//TRANSFER METHOD START FROM HERE
	
	
	

	public boolean transfer() throws SQLException {

		String s1 = "select * from ABCBank where accno=?";
		pstmt = con.prepareStatement(s1);
		pstmt.setInt(1, raccno);
		res = pstmt.executeQuery();
		while (res.next() == true) {
			System.out.println("while loop no-1");

			String s2 = "update ABCBank set balance=balance-? where accno=? ";
			pstmt = con.prepareStatement(s2);
			pstmt.setInt(1, bal);
			pstmt.setInt(2, accno);
			int y1 = pstmt.executeUpdate();
			if (y1 > 0) {
				System.out.println("while loop no-2");
				int x = res.getInt("BALANCE");
				if (x > 0) {

					String s3 = "update ABCBank set balance=balance+? where accno=? ";
					pstmt = con.prepareStatement(s2);
					pstmt.setInt(1, bal);
					pstmt.setInt(2, accno);
					int y2 = pstmt.executeUpdate();
					if (y2 > 0) {
						System.out.println("inside if no-1");

						String s4 = "Insert into GetStatement value(?,?,?)";
						pstmt = con.prepareStatement(s4);
						pstmt.setInt(1, accno);
						pstmt.setInt(2, raccno);
						pstmt.setInt(3, bal);
						int y = pstmt.executeUpdate();
						if (y > 0) {
							return true;
						} else {
							return false;
						}
					}
				} else {

				}
			}
		}

		return false;
	}

	
	
}
