

import java.io.IOException;
import java.util.Base64;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class Login
 */
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
		response.getWriter().append("Served at: ").append(request.getContextPath());
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String uname = request.getParameter("uname");
		String password = request.getParameter("password");
		
		LoginBean loginBean = new LoginBean();
		loginBean.setUname(uname);
		loginBean.setPassword(password);
	
		LoginDao loginDao = new LoginDao();
				
		//encrypt
		//Encoder encoder = Base64.getEncoder();
		String encodedString = Base64.getEncoder().encodeToString(password.getBytes());
	    loginBean.setPassword(encodedString);
		
		if(loginDao.validate(loginBean))
				{
			response.sendRedirect("Loginsuccess.jsp");
				}
		else
		{
			response.sendRedirect("Loginfail.jsp");
		}
		
	}
}


