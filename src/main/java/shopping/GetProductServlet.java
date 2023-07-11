package shopping;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


@WebServlet("/getProducts")
public class GetProductServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	Connection con;
	
	@Override
	public void init(ServletConfig config) throws ServletException {
		
		con=(Connection)config.getServletContext().getAttribute("jdbccon");
	}


	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		PrintWriter out = response.getWriter();
		PreparedStatement ps=null;
		ResultSet rs=null;
		
		try
		{
			ps =con.prepareStatement("select * from product where cat_id=?");
			int cid=Integer.parseInt(request.getParameter("cid"));
			ps.setInt(1, cid);
			rs=ps.executeQuery();
			out.println("<form action='addtocart' method='get'>");
			out.println("Select Product");
			out.println("<select name=category>");
			while (rs.next())
			{
				out.println("<option value="+rs.getInt(1)+">"+rs.getString(2)+"</option>");
			}
			out.println("</select>");
			out.println("<br/><input type='submit' value='Add to Cart'/>");
			out.println("</form>");
			response.setContentType("text/html");
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
		finally
		{
			try
			{
				rs.close();
				ps.close();
			}
			catch(Exception e)
			{
				e.printStackTrace();
			}
		}
		
	}

	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		doGet(request,response);
		
	}

}
