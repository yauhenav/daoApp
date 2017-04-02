package les11.web;

import java.io.*;
import java.util.*;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.sql.*;

import les11.logic.controller.*;
import les11.logic.dao.*;
import les11.logic.dto.*;
import les11.logic.exception.*;
import les11.logic.mysql.*;

public class daoAppServlet extends HttpServlet {
	public void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		try {
			Service mngob = new Service();
			resp.setContentType("text/html;charset=utf-8");
			PrintWriter pw = resp.getWriter();
			
			if (req.getParameter("show_all_students") != null) {
				pw.println("<B>Here's a list of all students in the DB</B>");
				pw.println("<table border=1>");
				List<Student> lst = mngob.displayAllStudents();
				Iterator<Student> itrstud0 = lst.iterator();
				while (itrstud0.hasNext()) {
					pw.println("<tr>");
					Student element = itrstud0.next();
					pw.println("<td>" + element.toString() + "</td>");
					pw.println("</tr>");
				}
			} 
					
			else if (req.getParameter("show_student_ID") != null) {
				pw.println("<B>Here goes one student from selected key</B>");
				pw.println("<table border=1>");
				String paramVal = req.getParameter("show_student_ID");
				int key = Integer.parseInt(paramVal);
				Student element = mngob.displayOneStudent(key);
				pw.println("<tr>");
				pw.println("<td>" + element + "</td>");
				//pw.println(element.toString());
				pw.println("</tr>");
				pw.println("</table>");
			}
			
			else if (req.getParameter("new_student_ID") != null) {
				pw.println("<B>Addition of new student</B>");
				pw.println("Go to home page and press button in Show all students section to check if student was added");
				String idValue = req.getParameter("new_student_ID");
				int id = Integer.parseInt(idValue);
				String nameValue = req.getParameter("new_student_name");
				String surnameValue = req.getParameter("new_student_surname");
				mngob.addStudent(id, nameValue, surnameValue);				
			}
			
			else if(req.getParameter("update_student_ID") != null) {
				pw.println("<B>Update of existing student</B>");
				pw.println("Go to home page and press button in Show all students section to check if student was updated");
				String idValue = req.getParameter("update_student_ID");
				int id = Integer.parseInt(idValue);
				String nameValue = req.getParameter("update_student_name");
				String surnameValue = req.getParameter("update_student_surname");
				mngob.updateStudent(id, nameValue, surnameValue);				
			}
			
			else if(req.getParameter("delete_student_ID") != null) {
				pw.println("<B>Deletion of existing student</B>");
				pw.println("Go to home page and press button in Show all students section to check if student was deleted");
				String idValue = req.getParameter("delete_student_ID");
				int id = Integer.parseInt(idValue);
				mngob.deleteStudent(id);
			}

			else if (req.getParameter("show_subject_ID") != null) {
				pw.println("<B>Here goes the subject from selected key</B>");
				pw.println("<table border=1>");
				String paramVal = req.getParameter("show_subject_ID");
				int key = Integer.parseInt(paramVal);
				Subject element = mngob.displayOneSubject(key);
				pw.println("<tr>");
				pw.println("<td>" + element + "</td>");
				//pw.println(element.toString());
				pw.println("</tr>");
				pw.println("</table>");
			}
			
			else if (req.getParameter("show_all_subjects") != null) {
				pw.println("<B>Here's a list of all subjects in the DB</B>");
				pw.println("<table border=1>");
				List<Subject> lst = mngob.displayAllSubjects();
				Iterator<Subject> itrsub0 = lst.iterator();
				while (itrsub0.hasNext()) {
					pw.println("<tr>");
					Subject element = itrsub0.next();
					pw.println("<td>" + element.toString() + "</td>");
					pw.println("</tr>");
				}
			}
			
			else if (req.getParameter("new_subject_ID") != null) {
				pw.println("<B>Addition of new subject</B>");
				pw.println("Go to home page and press button in Show all subjects section to check if subject was added");
				String idValue = req.getParameter("new_subject_ID");
				int id = Integer.parseInt(idValue);
				String descriptionValue = req.getParameter("new_subject_description");
				mngob.addSubject(id, descriptionValue);				
			}
			else if(req.getParameter("update_subject_ID") != null) {
				pw.println("<B>Update of existing subject</B>");
				pw.println("Go to home page and press button in Show all subjects section to check if subject was updated");
				String idValue = req.getParameter("update_subject_ID");
				int id = Integer.parseInt(idValue);
				String descriptionValue = req.getParameter("update_subject_description");
				mngob.updateSubject(id, descriptionValue);				
			}
			else if(req.getParameter("delete_subject_ID") != null) {
				pw.println("<B>Deletion of existing subject</B>");
				pw.println("Go to home page and press button in Show all subjects section to check if subject was deleted");
				String idValue = req.getParameter("delete_subject_ID");
				int id = Integer.parseInt(idValue);
				mngob.deleteSubject(id);
			}
			else if (req.getParameter("show_mark_ID") != null) {
				pw.println("<B>Here goes the mark from selected key</B>");
				pw.println("<table border=1>");
				String paramVal = req.getParameter("show_mark_ID");
				int key = Integer.parseInt(paramVal);
				Mark element = mngob.displayOneMark(key);
				pw.println("<tr>");
				pw.println("<td>" + element + "</td>");
				pw.println("</tr>");
				pw.println("</table>");
			}
			else if (req.getParameter("close_everything") != null) {
				pw.println("<B>All prepared statements and connections are closed</B>");
				mngob.closeEverything();
			}
			
			
			
			
		} catch (DaoException exc) {
				exc.printStackTrace();
		}				
    }
}
