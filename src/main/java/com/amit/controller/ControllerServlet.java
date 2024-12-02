package com.amit.controller;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

import com.amit.dto.Student;
import com.amit.service.IStudentService;
import com.amit.servicefactory.StudentServiceFactory;

@WebServlet("/controller/*")
public class ControllerServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException 
	{
		doProcess(request,response);
	}
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException 
	{
		doProcess(request,response);
	}
	
	protected void doProcess(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException
	{
		IStudentService stdService=StudentServiceFactory.getStudentService();
		
		System.out.println("Request URI is "+request.getRequestURI());
		System.out.println("Path Info is "+request.getPathInfo());
		
		//addform
		if(request.getRequestURI().endsWith("addform"))
		{
			Student std=new Student();
			std.setSid(Integer.parseInt(request.getParameter("sid")));
			std.setSname(request.getParameter("sname"));
			std.setSage(Integer.parseInt(request.getParameter("sage")));
			std.setAddress(request.getParameter("saddress"));
			String status=stdService.addStudent(std);
			PrintWriter out=response.getWriter();
			response.setContentType("text/html");
			RequestDispatcher requestDispatcher=null;
			if(status.equals("success"))
			{
				requestDispatcher=request.getRequestDispatcher("../sucess.html");
				requestDispatcher.forward(request, response);
			}
			else {
				requestDispatcher=request.getRequestDispatcher("../failure.html");
				requestDispatcher.forward(request, response);
			}
			out.close();
		}
		
		// Update Fetch
        if (request.getRequestURI().endsWith("fetchupdate")) {
        	PrintWriter out=response.getWriter();
			response.setContentType("text/html");
            Integer sid = Integer.parseInt(request.getParameter("sid"));
            Student std = stdService.searchStudent(sid);

            if (std != null) {
                out.println("<form action='./updateform' method='post' style='text-align:center;'>");
                out.println("<h2>Update Student Data</h2>");
                out.println("<label>Student ID: </label>");
                out.println("<input type='text' name='sid' value='" + std.getSid() + "' readonly><br><br>");
                out.println("<label>Name: </label>");
                out.println("<input type='text' name='sname' value='" + std.getSname() + "'><br><br>");
                out.println("<label>Age: </label>");
                out.println("<input type='number' name='sage' value='" + std.getSage() + "'><br><br>");
                out.println("<label>Address: </label>");
                out.println("<input type='text' name='saddress' value='" + std.getAddress() + "'><br><br>");
                out.println("<button type='submit'>Update</button>");
                out.println("</form>");
            } else {
                out.println("<h1 style='color:red;text-align:center;'>Student Record Not Found</h1>");
            }
            out.close();
        }

        // Update Form
        if (request.getRequestURI().endsWith("updateform")) {
        	PrintWriter out=response.getWriter();
			response.setContentType("text/html");
            Student std = new Student();
            std.setSid(Integer.parseInt(request.getParameter("sid")));
            std.setSname(request.getParameter("sname"));
            std.setSage(Integer.parseInt(request.getParameter("sage")));
            std.setAddress(request.getParameter("saddress"));
            String status = stdService.updateStudent(std);
            RequestDispatcher requestDispatcher=null;
            if (status.equals("success")) {
            	requestDispatcher=request.getRequestDispatcher("../updatesucess.html");
				requestDispatcher.forward(request, response);
				}
            else {
            	requestDispatcher=request.getRequestDispatcher("../updatefailure.html");
				requestDispatcher.forward(request, response);            }
            out.close();
        }
	
		
		//deleteform
		if(request.getRequestURI().endsWith("deleteform"))
		{
			Integer sid=(Integer.parseInt(request.getParameter("sid")));
			String status=stdService.deleteStudent(sid);
			PrintWriter out=response.getWriter();
			response.setContentType("text/html");
			RequestDispatcher requestDispatcher=null;
			if(status.equals("success"))
			{
				requestDispatcher=request.getRequestDispatcher("../deletesucess.html");
				requestDispatcher.forward(request, response);			}
			else if (status.equals("failure")) 
			{
				requestDispatcher=request.getRequestDispatcher("../deletefailure.html");
				requestDispatcher.forward(request, response);
			}
			else {
				requestDispatcher=request.getRequestDispatcher("../deletenotfound.html");
				requestDispatcher.forward(request, response);
			}
			out.close();
		}
		
		//searchform
		if(request.getRequestURI().endsWith("searchform"))
		{
			Integer sid=(Integer.parseInt(request.getParameter("sid")));
			Student std=stdService.searchStudent(sid);
			PrintWriter out=response.getWriter();
			response.setContentType("text/html");
			if(std!=null)
			{
				out.println("<table border='1' style='margin:auto;text-align:left;border-collapse:collapse;'>");
			    out.println("<tr><th style='padding:10px;'>Field</th><th style='padding:10px;'>Value</th></tr>");
			    out.println("<tr><td style='padding:10px;'>Id</td><td style='padding:10px;'>" + std.getSid() + "</td></tr>");
			    out.println("<tr><td style='padding:10px;'>Name</td><td style='padding:10px;'>" + std.getSname() + "</td></tr>");
			    out.println("<tr><td style='padding:10px;'>Age</td><td style='padding:10px;'>" + std.getSage() + "</td></tr>");
			    out.println("<tr><td style='padding:10px;'>Address</td><td style='padding:10px;'>" + std.getAddress() + "</td></tr>");
			    out.println("</table>");			}
			else {
				out.println("<h1 style='color:red;text-align:center;'>Record Not Found for Student Id "+sid+"</h1>");

			}
			out.close();
		}
		
		
	}

}
