package com.radostin.controller;

import java.io.IOException;
import java.sql.SQLException;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.radostin.dao.UserDao;
import com.radostin.model.User;

/**
 * Servlet implementation class ControllerServlet
 */
public class ControllerServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private UserDao userDao;

	public void init() {
		String jdbcURL = getServletContext().getInitParameter("jdbcURL");
		String jdbcUsername = getServletContext().getInitParameter("jdbcUsername");
		String jdbcPassword = getServletContext().getInitParameter("jdbcPassword");

		userDao = new UserDao(jdbcURL, jdbcUsername, jdbcPassword);

	}

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public ControllerServlet() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String action = request.getServletPath();

		try {
			switch (action) {
			case "/new":
				showNewForm(request, response);
				break;
			case "/insert":
				insertUser(request, response);
				break;
			case "/delete":
				deleteUserData(request, response);
				break;
			case "/edit":
				showEditForm(request, response);
				break;
			case "/update":
				updateUserData(request, response);
				break;
			case "/sortByLastName":
				sortByLastName(request, response);
				break;
			case "/sortByBirthDate":
				sortByBirthDate(request, response);
				break;
			default:
				listUser(request, response);
				break;
			}
		} catch (SQLException ex) {
			throw new ServletException(ex);
		}
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

	private void listUser(HttpServletRequest request, HttpServletResponse response)
			throws SQLException, IOException, ServletException {
		List<User> listUser = userDao.listAllUsers();
		request.setAttribute("listUser", listUser);
		RequestDispatcher dispatcher = request.getRequestDispatcher("UserList.jsp");
		dispatcher.forward(request, response);
	}

	private void sortByLastName(HttpServletRequest request, HttpServletResponse response)
			throws SQLException, ServletException, IOException {
		List<User> listUser = userDao.listAllUsers();

		Collections.sort(listUser, new Comparator<User>() {
			@Override
			public int compare(User o1, User o2) {
				return o1.getLastName().compareToIgnoreCase(o2.getLastName());
			}
		});

		request.setAttribute("listUser", listUser);
		RequestDispatcher dispatcher = request.getRequestDispatcher("UserList.jsp");
		dispatcher.forward(request, response);

	}

	private void sortByBirthDate(HttpServletRequest request, HttpServletResponse response)
			throws SQLException, ServletException, IOException {
		List<User> listUser = userDao.listAllUsers();
		Collections.sort(listUser, new Comparator<User>() {
			@Override
			public int compare(User o1, User o2) {
				return o1.getBirthdate().compareTo(o2.getBirthdate());
			}
		});

		request.setAttribute("listUser", listUser);
		RequestDispatcher dispatcher = request.getRequestDispatcher("UserList.jsp");
		dispatcher.forward(request, response);

	}

	private void showNewForm(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		RequestDispatcher dispatcher = request.getRequestDispatcher("UserForm.jsp");
		dispatcher.forward(request, response);
	}

	private void showEditForm(HttpServletRequest request, HttpServletResponse response)
			throws SQLException, ServletException, IOException {
		int id = Integer.parseInt(request.getParameter("id"));
		User existingUser = userDao.getUser(id);
		RequestDispatcher dispatcher = request.getRequestDispatcher("UserForm.jsp");
		request.setAttribute("user", existingUser);
		dispatcher.forward(request, response);

	}

	private void insertUser(HttpServletRequest request, HttpServletResponse response) throws SQLException, IOException {
		String firstName = request.getParameter("firstName");
		String lastName = request.getParameter("lastName");
		java.sql.Date birthdate = java.sql.Date.valueOf(request.getParameter("birthDate"));
		String phoneNumber = request.getParameter("phonenumber");
		String email = request.getParameter("email");

		User newUser = new User(firstName, lastName, birthdate, phoneNumber, email);
		userDao.insertUser(newUser);
		response.sendRedirect("list");
	}

	private void updateUserData(HttpServletRequest request, HttpServletResponse response)
			throws SQLException, IOException {
		int id = Integer.parseInt(request.getParameter("id"));
		String firstName = request.getParameter("firstName");
		String lastName = request.getParameter("lastName");
		java.sql.Date birthdate = java.sql.Date.valueOf(request.getParameter("birthDate"));
		String phoneNumber = request.getParameter("phonenumber");
		String email = request.getParameter("email");

		User user = new User(id, firstName, lastName, birthdate, phoneNumber, email);
		userDao.updateUser(user);
		response.sendRedirect("list");
	}

	private void deleteUserData(HttpServletRequest request, HttpServletResponse response)
			throws SQLException, IOException {
		int id = Integer.parseInt(request.getParameter("id"));

		User user = new User(id);
		userDao.deleteUser(user);
		response.sendRedirect("list");

	}

}
