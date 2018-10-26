package com.dasn.web;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.inject.Inject;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.sql.DataSource;

import com.dasn.dominio.Note;

@WebServlet("/notes/*")
public class EjemploServlet extends HttpServlet {

    private static final long serialVersionUID = 1L;

    @Inject
    private DataSource ds;

    private static final Logger Log = Logger.getLogger(EjemploServlet.class.getName());

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
        case ("/initdb"):
            initDB();
            response.sendRedirect("..");
            break;
        default:
            getNotes(request, response);
        }
    }

    protected void getNotes(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        String query = "SELECT * FROM notes";

        if (request.getParameter("id") != null) {
            query += " WHERE id=" + request.getParameter("id");
        }

        List<Note> notes = new ArrayList<>();

        try (Connection dbc = ds.getConnection();
                Statement st = dbc.createStatement();
                ResultSet rs = st.executeQuery(query);) {
            while (rs.next()) {
                Note n = new Note();
                n.setId(rs.getInt("id"));
                n.setTitle(rs.getString("title"));
                n.setText(rs.getString("text"));
                n.setHidden(rs.getBoolean("hidden"));
                notes.add(n);
            }
        } catch (SQLException e) {
            Log.log(Level.SEVERE, e.getMessage(), e);
        }
        request.setAttribute("notes", notes);
        RequestDispatcher rd = request.getRequestDispatcher("/WEB-INF/jsp/notes.jsp");
        rd.forward(request, response);
    }

    public void initDB() {
        String query;
        try (Connection dbc = ds.getConnection(); Statement st = dbc.createStatement();) {
            query = "CREATE TABLE  notes (" + "id INTEGER GENERATED ALWAYS AS IDENTITY (START WITH 1, INCREMENT BY 1),"
                    + "title VARCHAR(100)," + "text VARCHAR(255)," + "hidden BOOLEAN)";
            st.execute(query);
        } catch (SQLException e) {
            Log.log(Level.SEVERE, e.getMessage(), e);
        }
        try (Connection dbc = ds.getConnection(); Statement st = dbc.createStatement();) {
            query = "INSERT INTO notes (title,text,hidden) VALUES" + "('first','Hello world',false),"
                    + "('second','Hidden',true)";
            st.execute(query);
        } catch (SQLException e) {
            Log.log(Level.SEVERE, e.getMessage(), e);
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);

        boolean result = false;

        String query = "INSERT INTO notes (title,text,hidden) VALUES ('%s','%s',%s)";
        query = String.format(query, request.getParameter("title"), request.getParameter("text"), "false");

        try (Connection conn = ds.getConnection(); Statement st = conn.createStatement();) {
            st.executeUpdate(query);
        } catch (SQLException ex) {
            Log.log(Level.SEVERE, ex.getMessage(), ex);
        }

        response.sendRedirect(".");

    }

}