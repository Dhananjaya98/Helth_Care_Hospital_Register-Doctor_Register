$(document).ready(function () {
	$("#alertSuccess").hide(); 
	$("#alertError").hide();
	
});
// SAVE ============================================
$(document).on("click", "#btnSave", function (event) {
	// Clear alerts---------------------
	$("#alertSuccess").text("");
	$("#alertSuccess").hide();
	$("#alertError").text("");
	$("#alertError").hide();

	// Form validation-------------------
	var status = validateDoctorForm();
	if (status != true) {
		$("#alertError").text(status);
		$("#alertError").show();
		return;
	}
	// If valid------------------------
	var type = ($("#hidDoctorIDSave").val() == "") ? "POST" : "PUT";

	$.ajax({
		url : "DoctorRegisterAPI",
		type : type,
		data : $("#formRegisterDoctor").serialize(),
		dataType : "text",
		complete : function(response, status) {
			onDoctorSaveComplete(response.responseText, status);
		}
	});
});

function onDoctorSaveComplete(response, status) {
	if (status == "success") {
		var resultSet = JSON.parse(response);
		if (resultSet.status.trim() == "success") {
			$("#alertSuccess").text("Successfully saved.");
			$("#alertSuccess").show();
			$("#divDoctorsGrid").html(resultSet.data);
		} else if (resultSet.status.trim() == "error") {
			$("#alertError").text(resultSet.data);
			$("#alertError").show();
		}
	} else if (status == "error") {
		$("#alertError").text("Error while saving.");
		$("#alertError").show();
	} else {
		$("#alertError").text("Unknown error while saving..");
		$("#alertError").show();
	}
	$("#hidDoctorIDSave").val("");
	$("#formRegisterDoctor")[0].reset();
}
//DELETE
$(document).on("click", ".btnRemove", function(event) {
	$.ajax({
		url : "DoctorRegisterAPI",
		type : "DELETE",
		data : "D_ID=" + $(this).data("doctorid"),
		dataType : "text",
		complete : function(response, status) {
			onDoctorDeleteComplete(response.responseText, status);
		}
	});
});


//DELETE==========================================
function onDoctorDeleteComplete(response, status) {
	if (status == "success") {
		var resultSet = JSON.parse(response);
		if (resultSet.status.trim() == "success") {
			$("#alertSuccess").text("Successfully deleted.");
			$("#alertSuccess").show();
			$("#divDoctorsGrid").html(resultSet.data);
		} else if (resultSet.status.trim() == "error") {
			$("#alertError").text(resultSet.data);
			$("#alertError").show();
		}
	} else if (status == "error") {
		$("#alertError").text("Error while deleting.");
		$("#alertError").show();
	} else {
		$("#alertError").text("Unknown error while deleting..");
		$("#alertError").show();
	}
}

// UPDATE==========================================
$(document).on(
		"click",
		".btnUpdate",
		function(event) {
			$("#hidDoctorIDSave").val(
					$(this).closest("tr").find('#hidDoctorIDUpdate').val());
			$("#D_Name").val($(this).closest("tr").find('td:eq(0)').text());
			$("#D_PhoneNumber").val($(this).closest("tr").find('td:eq(1)').text());
			$("#D_Fee").val($(this).closest("tr").find('td:eq(2)').text());
			$("#D_Email").val($(this).closest("tr").find('td:eq(3)').text());
			$("#D_Specialization").val($(this).closest("tr").find('td:eq(4)').text());
			$("#D_NIC").val($(this).closest("tr").find('td:eq(5)').text());
			$("#D_Hospitals").val($(this).closest("tr").find('td:eq(6)').text());
		});


// CLIENTMODEL=========================================================================
function validateDoctorForm() {
	// name
	if ($("#D_Name").val().trim() == "") {
		return "Insert Doctor Name.";
	}
	// NIC
	if ($("#D_PhoneNumber").val().trim() == "") {
		return "Insert Phone Number.";
	}
	
	// spec-------------------------------
	if ($("#D_Fee").val().trim() == "") {
		return "Insert Fee.";
	}
	/*// is numerical value
	var tmpPrice = $("#itemPrice").val().trim();
	if (!$.isNumeric(tmpPrice)) {
		return "Insert a numerical value for Item Price.";
	}
	// convert to decimal price
	$("#itemPrice").val(parseFloat(tmpPrice).toFixed(2));*/

	// hospital------------------------
	if ($("#D_Email").val().trim() == "") {
		return "Insert Email.";
	}
		// email
	if ($("#D_Specialization").val().trim() == "") {
		return "Insert Specialization.";
	}
	
	// mobile-------------------------------
	if ($("#D_NIC").val().trim() == "") {
		return "Insert NIC.";
	}
	//hospital-----------------
	if ($("#D_Hospitals").val().trim() == "") {
		return "Insert Hospital.";
	}
	return true;
}