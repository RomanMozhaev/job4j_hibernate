package ru.job4j.servlets;

import ru.job4j.logic.Service;
import ru.job4j.logic.ServiceInterface;
import ru.job4j.models.Car;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.List;

/**
 * the servlet for loading the main table for the main page.
 */
public class StartPageServlet extends HttpServlet {

    private ServiceInterface service = new Service();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        HttpSession session = req.getSession();
        String name = (String) session.getAttribute("name");
        if (name == null) {
            name = "Log In";
        }
        List<Car> list = this.service.loadTable();
        req.setAttribute("list", list);
        req.setAttribute("name", name);
        this.getServletContext().getRequestDispatcher("/WEB-INF/table.jsp").forward(req, resp);
    }
}
