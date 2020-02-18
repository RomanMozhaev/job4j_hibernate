package ru.job4j.todolist.servlets;

import com.fasterxml.jackson.databind.ObjectMapper;
import ru.job4j.todolist.logic.Service;
import ru.job4j.todolist.logic.ServiceInterface;
import ru.job4j.todolist.models.Task;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.BufferedReader;
import java.io.IOException;
import java.util.HashMap;

/**
 * the servlet processes the status changing for the task.
 */
public class ChangeStatusServlet extends HttpServlet {

    /**
     * the instance of the Service class.
     */
    private final ServiceInterface service = Service.getInstance();

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        BufferedReader reader = req.getReader();
        StringBuilder sb = new StringBuilder();
        reader.lines().forEach(sb::append);
        ObjectMapper mapper = new ObjectMapper();
        String json = sb.toString();
        HashMap map = mapper.readValue(json, HashMap.class);
        int id = Integer.parseInt((String) map.get("id"));
        Boolean done = (Boolean) map.get("done");
        Task task = new Task();
        task.setId(id);
        task.setDone(done);
        this.service.changeStatus(task);
    }
}
