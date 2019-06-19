package com.leilaodequadrinhos.api.model.task.user;

import com.leilaodequadrinhos.api.model.dao.UserDao;
import com.leilaodequadrinhos.api.model.dao.impl.jdbc.UserDAO;

import com.leilaodequadrinhos.api.model.entities.Produto;

import com.leilaodequadrinhos.api.model.entities.User;
import com.leilaodequadrinhos.api.model.task.Task;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.text.SimpleDateFormat;

public class InsertNewUser implements Task {

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
        UserDao userDao = new UserDAO();
        User user = new User();
        user.setName(request.getParameter("name"));
        user.setStatus(true);
        user.setEmail(request.getParameter("email"));
        user.setState(request.getParameter("state"));
        user.setCity(request.getParameter("city"));
        String format = "dd/MM/yyyy";
        user.setDateOfBirth(new SimpleDateFormat(format).parse(request.getParameter("dateOfBirth")));
        user.setPassword(request.getParameter("password"));
        userDao.insert(user);
        return "Usuário cadastrado";
    }
}