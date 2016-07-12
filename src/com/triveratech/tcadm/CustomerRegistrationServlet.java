package com.triveratech.tcadm;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class CustomerRegistrationServlet
 */
@WebServlet("/CustomerRegistrationServlet")
public class CustomerRegistrationServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public CustomerRegistrationServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.getWriter().append("Served at: ").append(request.getContextPath());
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setContentType("text/html"); // Mime type of the response we're creating
		PrintWriter out = response.getWriter(); // Stream going back to the browser
		String firstName = request.getParameter("firstName");
		String lastName = request.getParameter("lastName");
		String phoneNumber = request.getParameter("phoneNumber");
		String email = request.getParameter("email");
		
		CustomerDAO dao = new CustomerJPADAO();
		Customer customer = new Customer(firstName, lastName, phoneNumber, email);
		dao.insert(customer);
		
		out.println("<h1>Hello, " + firstName + "!</h1>");
		out.println("<br/> CID: " + customer.getCustomerId());
		out.println("<br/> FN: " + customer.getFirstName());
		out.println("<br/> LN: " + customer.getLastName());
		out.println("<br/> PN: " + customer.getPhoneNumber());
		out.println("<br/> EM: " + customer.getEmail());
		out.flush();
		out.close();
	}

}
