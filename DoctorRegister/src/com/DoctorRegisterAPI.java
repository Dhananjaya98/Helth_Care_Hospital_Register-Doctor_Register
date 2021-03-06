package com;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/DoctorRegisterAPI")
public class DoctorRegisterAPI extends HttpServlet {

	private static final long serialVersionUID = 1L;
	DoctorRegister doctorObj = new DoctorRegister();

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public DoctorRegisterAPI() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		// response.getWriter().append("Served at: ").append(request.getContextPath());
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
		String output = doctorObj.insertDoctor(request.getParameter("D_Name"), request.getParameter("D_PhoneNumber"),
				request.getParameter("D_Fee"), request.getParameter("D_Email"), request.getParameter("D_Specialization"),
				request.getParameter("D_NIC"),request.getParameter("D_Hospitals"));
		response.getWriter().write(output);
	}

	/**
	 * @see HttpServlet#doPut(HttpServletRequest, HttpServletResponse)
	 */
	protected void doPut(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub

		Map paras = getParasMap(request);
		String output = doctorObj.updateDoctor(paras.get("hidDoctorIDSave").toString(),
				paras.get("D_Name").toString().replace("+", " "), paras.get("D_PhoneNumber").toString().replace("+", " "),
				paras.get("D_Fee").toString().replace("+", " "),
				paras.get("D_Email").toString().replace("+", " ")
				.replace("%2C", ",").replace("%3A", ":").replace("%40", "@").replace("%2F", "/"), paras.get("D_Specialization").toString().replace("+", " "),
				paras.get("D_NIC").toString().replace("+", " "),paras.get("D_Hospitals").toString().replace("+", " "));
		response.getWriter().write(output);
	}

	/**
	 * @see HttpServlet#doDelete(HttpServletRequest, HttpServletResponse)
	 */
	protected void doDelete(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		Map paras = getParasMap(request);
		String output = doctorObj.deleteDoctor(paras.get("D_ID").toString());
		response.getWriter().write(output);

	}

	private static Map getParasMap(HttpServletRequest request) {
		Map<String, String> map = new HashMap<String, String>();
		try {
			Scanner scanner = new Scanner(request.getInputStream(), "UTF-8");
			String queryString = scanner.hasNext() ? scanner.useDelimiter("\\A").next() : "";
			scanner.close();
			String[] params = queryString.split("&");
			for (String param : params) {
				String[] p = param.split("=");
				map.put(p[0], p[1]);
			}
		} catch (Exception e) {
		}
		return map;
	}
}
