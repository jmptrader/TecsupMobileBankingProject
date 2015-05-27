package BankServlets;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import BankLogic.LoginLogic;
import BankServices.excepcion.DAOExcepcion;
import BankServices.modelo.Customer;

/**
 * Servlet implementation class Login
 */
@WebServlet("/Login")
public class Login extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public Login() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		System.out.println("login request");
		String username = (String) request.getParameter("username");
		String password = (String) request.getParameter("password");
		String securityQuestionIDStr = request.getParameter("securityQuestionID");
		Integer securityQuestionID = null;
		if (securityQuestionIDStr != null) 
		{
			securityQuestionID = Integer.parseInt( securityQuestionIDStr);
		}
		String securityAnswer = (String) request.getParameter("securityAnswer");
		
		request.setAttribute("securityQuestion",null);
		Customer customer = null;
		
		LoginLogic l = new LoginLogic();
		
		try {
			
			customer = l.doLogin(username, password, securityQuestionID, securityAnswer);
		} 
		catch (DAOExcepcion e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		System.out.println("login request");
		
		if (customer == null)
		{
			request.setAttribute("RESUL", "Incorrect Username or Password");
			RequestDispatcher rd = request.getRequestDispatcher("login.jsp");
			rd.forward(request, response);
			
			
		}
		//Provide security question if customer not null
		else if (customer.loggedIn == false)
		{
			request.setAttribute("RESUL", "Please Provide Security Question");
			//System.out.println("Response - " + securityQuestionID.toString() + securityAnswer);
			request.setAttribute("user",customer);
			RequestDispatcher rd = request.getRequestDispatcher("login.jsp");
			rd.forward(request, response);
			
			
		}
		
		else
		{
			
		request.setAttribute("RESUL", "Logged In!");
		//Continue
		request.setAttribute("userID",customer.getIdUser());
		request.setAttribute("user",customer);
		RequestDispatcher rd = request.getRequestDispatcher("TransactionReports.jsp");
		rd.forward(request, response);
		}
	}

}
