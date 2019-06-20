package com.leilaodequadrinhos.api.model.task.user;

import com.leilaodequadrinhos.api.model.dao.DAO;
import com.leilaodequadrinhos.api.model.dao.UserDao;
import com.leilaodequadrinhos.api.model.dao.impl.jdbc.UserDAO;

import com.leilaodequadrinhos.api.model.entities.Produto;

import com.leilaodequadrinhos.api.model.entities.User;
import com.leilaodequadrinhos.api.model.task.Task;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.text.ParseException;
import java.text.SimpleDateFormat;

public class InsertNewUser extends CommonUserTasks implements Task {

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
        DAO userDao = new UserDAO();
        User user = getUser(request);
        userDao.insert(user);
        return "Usuário cadastrado";
    }
}