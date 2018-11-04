package com.dasn.javawebapp;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.regex.Pattern;
import javax.inject.Inject;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.sql.DataSource;
import org.apache.commons.lang3.StringEscapeUtils;

@WebServlet("/notes_corregido/*")
public class NotesCorregidoServlet extends HttpServlet {

    @Inject
    private DataSource ds;
    
    private static final Logger LOG= Logger.getLogger(NotesCorregidoServlet.class.getName());

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        processRequest(request, response);

        String action = (request.getPathInfo() != null ? request.getPathInfo() : "");

        switch (action) {
            case ("/xssdb"):
				xssDB();
                response.sendRedirect(".");
                break;
            default:
                getNotes(request, response);
        }
    }

    protected void getNotes(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        String query="SELECT * FROM notes";
        String id = request.getParameter("id");
        if ( id!=null ) {
            query+=" WHERE id=? ";
        }
        
        List<Note> notes = new ArrayList<>();

		try (Connection dbc = ds.getConnection();
			PreparedStatement ps = dbc.prepareStatement(query)) {
			if (id!=null) {
				if (!Pattern.matches("^[0-9]*$",id)) { 
					throw new SQLException("parametro id incorrecto");
				}
				ps.setInt(1, Integer.parseInt(id));
			}
			try (ResultSet rs = ps.executeQuery()) {
				while (rs.next()) {
					Note n = new Note();
					n.setId(rs.getInt("id"));
					n.setTitle(rs.getString("title"));
					n.setText(rs.getString("text"));
					n.setHidden(rs.getBoolean("hidden"));
					notes.add(n);
				}
			}
		} catch (SQLException| NumberFormatException e) {
			LOG.log(Level.SEVERE, e.getMessage(), e);
		}
        request.setAttribute("notes", notes);
        RequestDispatcher rd = request.getRequestDispatcher("/WEB-INF/jsp/notes_corregido.jsp");
        rd.forward(request, response);
    }

    public void xssDB() {
        String query;
        try (Connection dbc = ds.getConnection();
                Statement st = dbc.createStatement();) {
            query = "INSERT INTO notes (title,text,hidden) VALUES"
                    + "('<script>alert(\"mensaje\")</script>','mensaje con xss',false)";
            st.execute(query);
        } catch (SQLException e) {
            LOG.log(Level.SEVERE, e.getMessage(), e);
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);

        boolean result=false;

        String query="INSERT INTO notes (title,text,hidden) VALUES ('%s','%s',%s)";
        query=String.format(query,
                StringEscapeUtils.escapeHtml4(request.getParameter("title")),
                StringEscapeUtils.escapeHtml4(request.getParameter("text")),
                "false");                
                
        try (Connection conn=ds.getConnection();
            Statement st=conn.createStatement();
        ){
            st.executeUpdate(query);
        } catch (SQLException ex) {
            LOG.log(Level.SEVERE, ex.getMessage(), ex);
        }
        response.sendRedirect(".");
    }
}
