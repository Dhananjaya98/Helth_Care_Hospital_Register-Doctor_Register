package com;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import com.mysql.cj.xdevapi.Statement;

public class Hospital {

	public Connection connect() {
		Connection con = null;
		try {
			Class.forName("com.mysql.jdbc.Driver");

			// Provide the correct details: DBServer/DBName, username, password
			con = DriverManager.getConnection("jdbc:mysql://127.0.0.1:3308/paf", "root", "");

			System.out.print("Successfully connected");
		} catch (Exception e) {
			e.printStackTrace();
		}
		return con;
	}

	public String insertHospital(String Hname, String Address, String Pnum, String Email) {
		String output = "";
		try {
			Connection con = connect();
			if (con == null) {
				return "Error while connecting to the database for inserting.";
			}
			// create a prepared statement
			String query = " insert into hospitalrreq(`RegNumber`,`Hospital_Name`,`Address`,`PhoneNumber`,`Email`)"
					+ " values (?, ?, ?, ?, ?)";
			PreparedStatement preparedStmt = con.prepareStatement(query);
			// binding values
			preparedStmt.setInt(1,0);
			preparedStmt.setString(2, Hname);
			preparedStmt.setString(3, Address);
			preparedStmt.setString(4, Pnum);
			preparedStmt.setString(5, Email);
//execute the statement
			preparedStmt.execute();
			con.close();
			
			String newHospital = readHospital();
			 output = "{\"status\":\"success\", \"data\": \"" +newHospital + "\"}";
			 
			//output = "Inserted successfully";
		} catch (Exception e) {
			output = "{\"status\":\"error\", \"data\":\"Error while inserting the item.\"}";
			//output = "Error while inserting the item.";
			System.err.println(e.getMessage());
		}
		return output;
	}

	public String readHospital() {
		String output = "";
		try {
			Connection con = connect();
			if (con == null) {
				return "Error while connecting to the database for reading.";
			}
// Prepare the html table to be displayed
			output = "<table border=\'1\'><tr><th>Hospital Name</th><th>Hoapital Address</th><th>Phone Number</th><th>Email</th><th>Update</th><th>Remove</th></tr>";
			String query = "select * from hospitalrreq";
			java.sql.Statement stmt = con.createStatement();
			ResultSet rs = stmt.executeQuery(query);
// iterate through the rows in the result set
			while (rs.next()) {
				String RegNumber = Integer.toString(rs.getInt("RegNumber"));
				String Hospital_Name = rs.getString("Hospital_Name");
				String Address = rs.getString("Address");
				String PhoneNumber = rs.getString("PhoneNumber");
				String Email = rs.getString("Email");
// Add into the html table
				
			    output += "<tr><td><input id='hidHosIDUpdate' name='hidHosIDUpdate' type='hidden' value='" + RegNumber + "'>" + Hospital_Name + "</td>";
				
//				output += "<tr><td>" + RegNumber + "</td>";
//				output += "<td>" + Hospital_Name + "</td>";
				output += "<td>" + Address + "</td>";
				output += "<td>" + PhoneNumber + "</td>";
				output += "<td>" + Email + "</td>";
// buttons
				
				output += "<td><input name='btnUpdate' type='button' value='Update' class='btnUpdate btn btn-secondary'></td><td><input name='btnRemove' type='button' value='Remove' class='btnRemove btn btn-danger' data-hospid='"
						 + RegNumber + "'>" + "</td></tr>";
//				output += "<td><input name=\"btnUpdate\" type=\"button\"value=\"Update\" class=\"btn btn-secondary\"></td>"
//						+ "<td><form method=\"post\" action=\"items.jsp\">"
//						+ "<input name=\"btnRemove\" type=\"submit\" value=\"Remove\"class=\"btn btn-danger\">"
//						+ "<input name=\"RegNumber\" type=\"hidden\" value=\"" + RegNumber + "\">"
//						+ "</form></td></tr>";
			}
			con.close();
// Complete the html table
			output += "</table>";
		} catch (Exception e) {
			output = "Error while reading the Hospital.";
			System.err.println(e.getMessage());
		}
		return output;
	}

	public String updateHospital(String Rnum, String Hname, String Address, String Pnum, String Email) {
		String output = "";
		try {
			Connection con = connect();
			if (con == null) {
				return "Error while connecting to the database for updating.";
			}
// create a prepared statement
			String query = "UPDATE hospitalrreq SET Hospital_Name=?,Address=?,PhoneNumber=?,Email=?WHERE RegNumber=?";
			PreparedStatement preparedStmt = con.prepareStatement(query);
// binding values
//			preparedStmt.setString(1, Rnum);
			preparedStmt.setString(1, Hname);
			preparedStmt.setString(2, Address);
			preparedStmt.setString(3, Pnum);
			preparedStmt.setString(4, Email);
			preparedStmt.setInt(5, Integer.parseInt(Rnum));
// execute the statement
			preparedStmt.execute();
			con.close();
			
			String newHospital = readHospital();
			 output = "{\"status\":\"success\", \"data\": \"" +newHospital + "\"}";
			
//			output = "Updated successfully";
		} catch (Exception e) {
			output = "{\"status\":\"error\", \"data\":\"Error while updating the Hospital.\"}";
//			output = "Error while updating the item.";
			System.err.println(e.getMessage());
		}
		return output;
	}

	public String deleteHospital(String RegNumber) {
		String output = "";
		try {
			Connection con = connect();
			if (con == null) {
				return "Error while connecting to the database for deleting.";
			}
//create a prepared statement
			String query = "delete from hospitalrreq where RegNumber=?";
			PreparedStatement preparedStmt = con.prepareStatement(query);
//binding values
			preparedStmt.setInt(1, Integer.parseInt(RegNumber));
//execute the statement
			preparedStmt.execute();
			con.close();
			
			String newHospital = readHospital();
			 output = "{\"status\":\"success\", \"data\": \"" +newHospital + "\"}";
//			output = "Deleted Hospital details successfully";
		} catch (Exception e) {
			output = "{\"status\":\"error\", \"data\":\"Error while deleting the Hospital.\"}";
//			output = "Error while deleting the Hospital details.";
			System.err.println(e.getMessage());
		}
		return output;
	}

}
