package shopping;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.util.*;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;


@WebServlet("/deleteItem")
public class DeleteFromCart extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	Connection con;
	
	@Override
	public void init(ServletConfig config) throws ServletException {
		super.init(config);
		con=(Connection)config.getServletContext().getAttribute("jdbccon");
	}


	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doPost(request,response);
	}

	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		PrintWriter out=response.getWriter();
		
		int spid=Integer.parseInt(request.getParameter("pid"));
		
		HttpSession session=request.getSession();
		List<Integer> products=(List<Integer>)session.getAttribute("cart");
		if(products==null)
		{
			products=new ArrayList<>();
		}
		products.remove(Integer.valueOf(spid));
		session.setAttribute("cart", products);
		
		response.sendRedirect("/Shoppingapp/viewcart");
		
		
		
		
	}

}
