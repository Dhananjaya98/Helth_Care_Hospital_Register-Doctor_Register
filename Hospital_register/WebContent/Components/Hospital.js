$(document).ready(function() {
	if ($("#alertSuccess").text().trim() == "") {
		$("#alertSuccess").hide();
	}
	$("#alertError").hide();
});
// SAVE ============================================
$(document).on("click", "#btnSave", function(event) {
	// Clear alerts---------------------
	$("#alertSuccess").text("");
	$("#alertSuccess").hide();
	$("#alertError").text("");
	$("#alertError").hide();

	// Form validation-------------------
	var status = validateHospitalForm();
	if (status != true) {
		$("#alertError").text(status);
		$("#alertError").show();
		return;
	}
	// If valid------------------------
	var type = ($("#hidHospitalIDSave").val() == "") ? "POST" : "PUT";

	$.ajax({
		url : "HospitalsAPI",
		type : type,
		data : $("#formHospital").serialize(),
		dataType : "text",
		complete : function(response, status) {
			onHospitalSaveComplete(response.responseText, status);
		}
	});
});

//UPDATE==========================================
function onHospitalSaveComplete(response, status) {
	if (status == "success") {
		var resultSet = JSON.parse(response);
		if (resultSet.status.trim() == "success") {
			$("#alertSuccess").text("Successfully saved.");
			$("#alertSuccess").show();
			$("divHospitalGrid").html(resultSet.data);
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
	$("#hidHospitalIDSave").val("");
	$("#formHospital")[0].reset();
}

$(document).on("click", ".btnRemove", function(event) {
	$.ajax({
		url : "HospitalsAPI",
		type : "DELETE",
		data : "RegNumber=" + $(this).data("hospid"),
		dataType : "text",
		complete : function(response, status) {
			onHospitalDeleteComplete(response.responseText, status);
		}
	});
});

//DELETE==========================================
function onHospitalDeleteComplete(response, status) {
	if (status == "success") {
		var resultSet = JSON.parse(response);
		if (resultSet.status.trim() == "success") {
			$("#alertSuccess").text("Successfully deleted.");
			$("#alertSuccess").show();
			$("#divHospitalGrid").html(resultSet.data);
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
			$("#hidHospitalIDSave").val(
					$(this).closest("tr").find('#hidHosIDUpdate').val());
			$("#Hospital_Name").val($(this).closest("tr").find('td:eq(0)').text());
			$("#Address").val($(this).closest("tr").find('td:eq(1)').text());
			$("#PhoneNumber").val($(this).closest("tr").find('td:eq(2)').text());
			$("#Email").val($(this).closest("tr").find('td:eq(3)').text());
		});

// CLIENTMODEL=========================================================================
function validateHospitalForm() {
	// CODE
	if ($("#Hospital_Name").val().trim() == "") {
		return "Insert Hospital Name.";
	}
	// NAME
	if ($("#Address").val().trim() == "") {
		return "Insert Address.";
	}
	// PRICE-------------------------------
	if ($("#PhoneNumber").val().trim() == "") {
		return "Insert Phone Number.";
	}
	// is numerical value
	var tmpNumber = $("#PhoneNumber").val().trim();
	if (!$.isNumeric(tmpNumber)) {
		return "Insert a numerical value for Phone Number.";
	}
	// DESCRIPTION------------------------
	if ($("#Email").val().trim() == "") {
		return "Insert Email.";
	}
	return true;
}
