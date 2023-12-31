package shopping;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import entity.*;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;


@WebServlet("/confirmcart")
public class ConfirmCartServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	Connection con;
	
	@Override
	public void init(ServletConfig config) throws ServletException {
		
		super.init(config);
		con = (Connection)config.getServletContext().getAttribute("jdbccon");
	}
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		PrintWriter out=response.getWriter();
		
		HttpSession session = request.getSession();
		User u=(User)session.getAttribute("loggedinuser");
		
		String uid =u.getUid();
		out.print(uid);
		java.sql.Timestamp ts=new java.sql.Timestamp(new java.util.Date().getTime());
		out.print(ts);
		float tprice=(Float)session.getAttribute("tprice");
		out.println(tprice);
		
		
		out.print("<p>Order is Confirmed</p>");
		out.print("<p>U will be emailed at "+u.getEmail()+"</p>");
		out.print("<p>U will receive message on "+u.getContact()+"before order delievery</p>");
		
		out.print("<br/><br/><a href='logout'>LogOut</a>");
		out.print("<br/><br/><a href='home'>Want to set new order?</a>");
		
		
	}

	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
	}

}
