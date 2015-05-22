package BankServices.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collection;

import BankServices.modelo.Customer;
import BankServices.excepcion.*;
import BankServices.util.*;

public class CustomerDAO extends BaseDAO {

	public Customer getCustomerById(String idCustomer) throws DAOExcepcion {
		Customer customer = new Customer();
		Connection con = null;
		PreparedStatement stmt = null;
		ResultSet rs = null;
		try {
			String query = "select * from Customer where userID=?";
			con = ConexionBD.obtenerConexion();
			stmt = con.prepareStatement(query);
			stmt.setString(1, idCustomer);
		

			rs = stmt.executeQuery();
			if (rs.next()) {
				customer.setIdUser(rs.getString(1));
				customer.setPassword(rs.getString(2));
				customer.setName(rs.getString(3) + rs.getString(rs.getString(5)));
				
				
			}
		} catch (SQLException e) {
			System.err.println(e.getMessage());
			throw new DAOExcepcion(e.getMessage());
		} finally {
			this.cerrarResultSet(rs);
			this.cerrarStatement(stmt);
			this.cerrarConexion(con);
		}
		return customer;
	}


}
