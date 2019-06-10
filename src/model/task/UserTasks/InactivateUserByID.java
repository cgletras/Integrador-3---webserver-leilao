package model.task.UserTasks;

import model.dao.DaoFactory;
import model.dao.UserDao;
import model.task.Task;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class InactivateUserByID implements Task {

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) {
        UserDao userDao = DaoFactory.createUsuarioDao();
        Integer id = Integer.parseInt(request.getParameter("userID"));
        userDao.inactivate(id);
        return "Usuário desativado";
    }
}