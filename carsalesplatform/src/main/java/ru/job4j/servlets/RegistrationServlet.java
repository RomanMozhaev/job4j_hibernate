package ru.job4j.servlets;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.json.JSONObject;
import ru.job4j.logic.Service;
import ru.job4j.logic.ServiceInterface;
import ru.job4j.models.User;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.HashMap;

/**
 * the servlet for adding the new user.
 */
public class RegistrationServlet extends HttpServlet {

    private ServiceInterface service = Service.getInstance();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        this.getServletContext().getRequestDispatcher("/WEB-INF/registration.jsp").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        BufferedReader reader = req.getReader();
        StringBuilder sb = new StringBuilder();
        reader.lines().forEach(sb::append);
        ObjectMapper mapper = new ObjectMapper();
        String json = sb.toString();
        HashMap map = mapper.readValue(json, HashMap.class);
        String name = (String) map.get("name");
        String password = (String) map.get("pass");
        int id = this.service.addUser(new User(name, password));
        resp.setContentType("application/json");
        resp.setCharacterEncoding("UTF-8");
        PrintWriter writer = new PrintWriter(resp.getOutputStream());
        JSONObject status = new JSONObject();
        if (id != -1) {
            HttpSession session = req.getSession();
            synchronized (session) {
                session.setAttribute("name", name);
                session.setAttribute("id", id);
            }
            status.put("status", "valid");
        } else {
            status.put("status", "invalid");
        }
        writer.append(status.toString());
        writer.flush();
    }
}
