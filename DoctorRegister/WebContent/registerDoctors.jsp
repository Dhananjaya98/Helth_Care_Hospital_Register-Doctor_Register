<%@ page import="com.DoctorRegister"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>


<!DOCTYPE htmlt>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Doctor request Management</title>
<link rel="stylesheet" href="Views/bootstrap.min.css">
<script src="Components/jquery-3.4.0.min.js"></script>
<script src="Components/registerDoctors.js"></script>
</head>
<body>

	<div class="container">
		<div class="row">
			<div class="col-6">


				<h1>Doctor request Management</h1>

				<form id="formRegisterDoctor" name="formRegisterDoctor"
					method="post" action="registerDoctors.jsp">
					Doctor Name : <input id="D_Name" name="D_Name" type="text"
						class="form-control form-control-sm"> <br>
					Doctor Phone Number : <input id="D_PhoneNumber" name="D_PhoneNumber" type="text"
						class="form-control form-control-sm"> <br>
					Doctor Fee : <input id="D_Fee" name="D_Fee"
						type="text" class="form-control form-control-sm"> <br>
					Doctor Email : <input id="D_Email" name="D_Email" type="text"
						class="form-control form-control-sm"> <br> 
					Doctor Specialization : <input id="D_Specialization" name="D_Specialization" type="text"
						class="form-control form-control-sm"> <br>
					Doctor NIC : <input id="D_NIC"
						name="D_NIC" type="text"
						class="form-control form-control-sm"> <br> 
					Doctor Hospital : <input id="D_Hospitals" name="D_Hospitals"
						type="text" class="form-control form-control-sm"> <br>
					 <input
						id="btnSave" name="btnSave" type="button" value="Save"
						class="btn btn-primary"> <input type="hidden"
						id="hidDoctorIDSave" name="hidDoctorIDSave" value="">
				</form>

				<div id="alertSuccess" class="alert alert-success"></div>
				<div id="alertError" class="alert alert-danger"></div>
				<br>
				<div id="divDoctorsGrid">
					<%
						DoctorRegister doctorObj = new DoctorRegister();
					out.print(doctorObj.readDoctors());
					%>
				</div>
			</div>
		</div>
	</div>
</body>
</html>