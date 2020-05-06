package com;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;

import javax.servlet.http.HttpServlet;

public class DoctorRegister extends HttpServlet {
	private Connection connect() {
		Connection con = null;
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");

			// Provide the correct details: DBServer/DBName, username, password
			con = DriverManager.getConnection("jdbc:mysql://127.0.0.1:3308/paf", "root", "");
		} catch (Exception e) {
			e.printStackTrace();
		}
		return con;
	}

	public String insertDoctor(String D_Name, String D_PhoneNumber, String D_Fee , String D_Email , String D_Specialization,String D_NIC ,
			String D_Hospitals ) {
		String output = "";
		try {
			Connection con = connect();
			if (con == null) {
				return "Error while connecting to the database for inserting.";
			}
			// create a prepared statement
			String query = " insert into doctor_reg_req (`D_ID`,`D_Name`,`D_PhoneNumber`,`D_Fee`,`D_Email`,`D_Specialization`,`D_NIC`,`D_Hospitals`)"
					+ " values (?, ?, ?, ?, ?,?,?,?)";
			PreparedStatement preparedStmt = con.prepareStatement(query);
			// binding values
			preparedStmt.setInt(1, 0);
			preparedStmt.setString(2, D_Name);
			preparedStmt.setString(3, D_PhoneNumber);
			preparedStmt.setString(4, D_Fee);
			preparedStmt.setString(5, D_Email);
			preparedStmt.setString(6, D_Specialization);
			preparedStmt.setString(7, D_NIC);
			preparedStmt.setString(8, D_Hospitals);

			// execute the statement
			preparedStmt.execute();
			con.close();

			String newDoctors = readDoctors();
			output = "{\"status\":\"success\", \"data\": \"" + newDoctors + "\"}";

			// output = "Inserted successfully";
		} catch (Exception e) {
			output = "{\"status\":\"error\", \"data\":\"Error while inserting the doctor.\"}";
			// output = "Error while inserting the doctor.";
			System.err.println(e.getMessage());
		}
		return output;
	}

	public String readDoctors() {
		String output = "";

		try {
			Connection con = connect();

			if (con == null) {
				return "Error while connecting the database for reading";
			}

			output = "<table border='1'><tr><th>Doctor name</th><th>Phone number</th><th>Doctor Fee</th><th>Email</th><th>Specialization</th><th>NIC</th><th>Hospital</th></tr>";

			String query = "select * from doctor_reg_req";
			Statement stmt = con.createStatement();
			ResultSet rs = stmt.executeQuery(query);

			while (rs.next()) {
				String D_ID = Integer.toString(rs.getInt("D_ID"));
				String D_Name = rs.getString("D_Name");
				String D_PhoneNumber = rs.getString("D_PhoneNumber");
				String D_Fee = rs.getString("D_Fee");
				String D_Email = rs.getString("D_Email");
				String D_Specialization = rs.getString("D_Specialization");
				String D_NIC = rs.getString("D_NIC");
				String D_Hospitals = rs.getString("D_Hospitals");
				

				// Add into the html table
				output += "<tr><td><input id='hidDoctorIDUpdate' name ='hidDoctorIDUpdate' type='hidden' value='"
						+ D_ID + "'>" + D_Name + "</td>";

				output += "<td>" + D_PhoneNumber + "</td>";
				output += "<td>" + D_Fee + "</td>";
				output += "<td>" + D_Email + "</td>";
				output += "<td>" + D_Specialization + "</td>";
				output += "<td>" + D_NIC + "</td>";
				output += "<td>" + D_Hospitals + "</td>";

				// buttons
				output += "<td><input name='btnUpdate' type='button' value='Update' class='btnUpdate btn btn-secondary'></td><td><input name='btnRemove' type='button' value='Remove' class='btnRemove btn btn-danger' data-doctorid='"
						+ D_ID + "'>" + "</td></tr>";

			}
			con.close();
			// Complete the html table
			output += "</table>";
		} catch (Exception e) {
			output = "Error while reading doctors";
			System.err.println(e.getMessage());
		}
		return output;
	}

	public String updateDoctor(String D_ID, String D_Name, String D_PhoneNumber, String D_Fee, String D_Email,
			String D_Specialization, String D_NIC,String D_Hospitals) {
		String output = "";
		try {
			Connection con = connect();
			if (con == null) {
				return "Error while connecting to the database for updating.";
			}
			// create a prepared statement
			String query = "UPDATE doctor_reg_req SET D_Name=?,D_PhoneNumber=?,D_Fee=?,D_Email=?,D_Specialization=?,D_NIC=?,D_Hospitals=?  WHERE D_ID=?";

			PreparedStatement preparedStmt = con.prepareStatement(query);
			// binding values
			preparedStmt.setString(1, D_Name);
			preparedStmt.setString(2, D_PhoneNumber);
			preparedStmt.setString(3, D_Fee);
			preparedStmt.setString(4, D_Email);
			preparedStmt.setString(5, D_Specialization);
			preparedStmt.setString(6, D_NIC);
			preparedStmt.setString(7, D_Hospitals);
			preparedStmt.setInt(8, Integer.parseInt(D_ID));
			// execute the statement
			preparedStmt.execute();
			con.close();

			// output = "Updated successfully";
			String newDoctors = readDoctors();
			output = "{\"status\":\"success\", \"data\": \"" + newDoctors + "\"}";

			// output = "Updated successfully";
		} catch (Exception e) {
			output = "{\"status\":\"error\", \"data\":\"Error while updating the doctor.\"}";
			// output = "Error while updating the doctor.";
			System.err.println(e.getMessage());
		}
		return output;
	}

	public String deleteDoctor(String D_ID) {
		String output = "";
		try {
			Connection con = connect();
			if (con == null) {
				return "Error while connecting to the database for deleting.";
			}
			// create a prepared statement
			String query = "delete from doctor_reg_req where D_ID=?";
			PreparedStatement preparedStmt = con.prepareStatement(query);
			// binding values
			preparedStmt.setInt(1, Integer.parseInt(D_ID));
			// execute the statement
			preparedStmt.execute();
			con.close();

			// output = "Deleted successfully";
			String newDoctors = readDoctors();
			output = "{\"status\":\"success\", \"data\": \"" + newDoctors + "\"}";

			// output = "Deleted successfully";
		} catch (Exception e) {
			output = "{\"status\":\"error\", \"data\":\"Error while deleting the doctor.\"}";
			// output = "Error while deleting the doctor.";
			System.err.println(e.getMessage());
		}
		return output;
	}
}
