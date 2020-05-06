<%@ page import="com.Hospital"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>


<!DOCTYPE htmlt>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Hospital Management</title>
<link rel="stylesheet" href="Views/bootstrap.min.css">
<script src="Components/jquery-3.4.0.min.js"></script>
<script src="Components/Hospital.js"></script>
</head>
<body>

	<div class="container">
		<div class="row">
			<div class="col-6">


				<h1>Hospital Register System</h1>

				<form id="formHospital" name="formHospital" method="post"
					action="Hospitals.jsp">
					Hospital Name : <input id="Hospital_Name" name="Hospital_Name"
						type="text" class="form-control form-control-sm"> <br>
					Address : <input id="Address" name="Address" type="text"
						class="form-control form-control-sm"> <br> Phone
					Number : <input id="PhoneNumber" name="PhoneNumber" type="text"
						class="form-control form-control-sm"> <br> Email : <input
						id="Email" name="Email" type="text"
						class="form-control form-control-sm"> <br> <input
						id="btnSave" name="btnSave" type="button" value="Save"
						class="btn btn-primary"> <input type="hidden"
						id="hidHospitalIDSave" name="hidHospitalIDSave" value="">
				</form>

				<div id="alertSuccess" class="alert alert-success"></div>
				<div id="alertError" class="alert alert-danger"></div>
				<br>
				<div id="divHospitalsGrid">
					<%
						Hospital HosObj = new Hospital();
					out.print(HosObj.readHospital());
					%>
				</div>
			</div>
		</div>
	</div>
</body>
</html>