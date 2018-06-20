package a1.controller;

import java.io.IOException;
import java.util.*;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import a1.POJO.Movie;
import a1.dao.DAO;

/**
 * Servlet implementation class HomeController
 */
@WebServlet("/HomeController")
public class HomeController extends HttpServlet {
	private static final long serialVersionUID = 1L;
	DAO dao = new DAO();
	List<Movie> list = new ArrayList<>();
	String search = "";

    /**
     * Default constructor. 
     */
    public HomeController() {
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		search = request.getParameter("search");
		String filter = request.getParameter("filter");
		
		if (filter == null)
			filter = "";
		
		if (filter.equals("name"))
			list = dao.queryByName(search);
		else if (filter.equals("rating"))
			list = dao.queryByRating(Integer.parseInt(search));
		else if (filter.equals("director"))
			list = dao.queryByDirector(search);
		else
			list = dao.queryBySearch(search);
		request.setAttribute("movies", list);
		response.getWriter().append("Served at: ").append(request.getContextPath());
		request.getRequestDispatcher("index.jsp").forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		if (list != null) {
			String movieName = request.getParameter("movie"),
					comment = request.getParameter("comment"),
					submitReview = request.getParameter("submitReview");
			
			if (submitReview != null && submitReview.equals("Add Review")) {
				//if () {
					list = dao.queryBySearch(movieName);
					Movie movie = list.get(0);
					System.out.println("submitted");
					movie.setReview(comment);
					dao.queryDelete(movie.getName());
					dao.transaction(movie);
					search = movie.getName();
				//}
			}
		}
		doGet(request, response);
	}

}
