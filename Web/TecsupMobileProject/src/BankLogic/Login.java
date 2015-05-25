package BankLogic;

import java.io.IOException;
import java.util.Collection;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.sf.json.JSONObject;

import com.sun.org.apache.xpath.internal.operations.Bool;

import BankServices.modelo.*;
import BankServices.excepcion.*;
import BankServices.dao.*;

public class Login {

	public boolean checkLogin(String username, String password) throws DAOExcepcion
	{
		
		if (username == "" || username == null)
		{
			return false;
		}
		
		else if (password == "" || password == null)
		{
			return false;
		}
		
		
		CustomerDAO dao = new CustomerDAO();
		Customer customer = dao.getCustomerById(username);
		SecurityQuestionDAO SQdao = new SecurityQuestionDAO();
		
		SQdao.getQuestionByCustomerId(customer);
		
		
		if (!password.equals(customer.getPassword()))
		{
			return false;
		}
		
		return true;
		
		
	}
	
	public boolean getStudent()
	

}