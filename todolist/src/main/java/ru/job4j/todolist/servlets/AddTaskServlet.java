package ru.job4j.todolist.servlets;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.json.JSONArray;
import org.json.JSONObject;
import ru.job4j.todolist.logic.Service;
import ru.job4j.todolist.logic.ServiceInterface;
import ru.job4j.todolist.models.Task;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Date;
import java.util.HashMap;

/**
 * the servlet processes adding the new task to the data base.
 */
public class AddTaskServlet extends HttpServlet {

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
        String name = (String) map.get("name");
        String desc = (String) map.get("desc");
        Task task = new Task();
        task.setName(name);
        task.setDesc(desc);
        Task addedTask = this.service.addTask(task);
        resp.setContentType("application/json");
        resp.setCharacterEncoding("UTF-8");
        PrintWriter writer = new PrintWriter(resp.getOutputStream());
        JSONArray newRow = new JSONArray();
        JSONObject row = new JSONObject();
        row.put("id", addedTask.getId());
        row.put("name", addedTask.getName());
        row.put("desc", addedTask.getDesc());
        row.put("created", new Date(addedTask.getCreated().getTimeInMillis()));
        row.put("done", addedTask.isDone());
        newRow.put(row);
        writer.append(newRow.toString());
        writer.flush();
    }
}