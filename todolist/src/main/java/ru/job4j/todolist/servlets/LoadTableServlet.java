package ru.job4j.todolist.servlets;

import org.json.JSONObject;
import org.json.JSONArray;
import ru.job4j.todolist.logic.Service;
import ru.job4j.todolist.logic.ServiceInterface;
import ru.job4j.todolist.models.Task;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Date;
import java.util.List;

/**
 * the servlet processes the loading tasks to the table on the index page.
 */
public class LoadTableServlet extends HttpServlet {

    /**
     * the instance of the Service class.
     */
    private final ServiceInterface service = Service.getInstance();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        List<Task> tasks = this.service.getAll();
        resp.setContentType("application/json");
        resp.setCharacterEncoding("UTF-8");
        PrintWriter writer = new PrintWriter(resp.getOutputStream());
        JSONArray todoTable = new JSONArray();
        for (Task task : tasks) {
            JSONObject json = new JSONObject();
            json.put("id", task.getId());
            json.put("name", task.getName());
            json.put("desc", task.getDesc());
            json.put("created", new Date(task.getCreated().getTimeInMillis()));
            json.put("done", task.isDone());
            todoTable.put(json);
        }
        writer.append(todoTable.toString());
        writer.flush();
    }
}
