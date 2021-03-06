package com.leilaodequadrinhos.api.model.task.writer;

import com.leilaodequadrinhos.api.model.dao.WriterDao;
import com.leilaodequadrinhos.api.model.dao.impl.jdbc.WriterDAO;
import com.leilaodequadrinhos.api.model.entities.Writer;
import com.leilaodequadrinhos.api.model.task.Task;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class FindWriterById implements Task {

    @Override
    public Object execute(HttpServletRequest request, HttpServletResponse response) {
        Long writerID = Long.parseLong(request.getParameter("writerID"));
        WriterDao writerDao = new WriterDAO();
        Writer writer = (Writer) writerDao.findById(writerID);
        request.setAttribute("writer", writer);
        return writer;
    }
}