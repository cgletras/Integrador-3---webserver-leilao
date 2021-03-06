package com.leilaodequadrinhos.api.model.task.product;

import com.leilaodequadrinhos.api.model.dao.ProductDao;
import com.leilaodequadrinhos.api.model.dao.ProductStatusDao;
import com.leilaodequadrinhos.api.model.dao.UserDao;
import com.leilaodequadrinhos.api.model.dao.impl.jdbc.ProductDAO;
import com.leilaodequadrinhos.api.model.dao.impl.jdbc.ProductStatusDAO;
import com.leilaodequadrinhos.api.model.dao.impl.jdbc.UserDAO;
import com.leilaodequadrinhos.api.model.entities.Product;
import com.leilaodequadrinhos.api.model.task.Task;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class InsertProduct extends BaseProductTask implements Task {

    private static final long ACTIVE = 1;

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) {
        ProductDao productDao = new ProductDAO();
        ProductStatusDao productStatusDao = new ProductStatusDAO();
        UserDao userDao = new UserDAO();
        Product product = null;
        try {
            product = buildProduct(request, userDao);
        } catch (IOException | ServletException e) {
            e.printStackTrace();
            return "Product not inserted";
        }
        product.setProductStatus(productStatusDao.findById(ACTIVE));
        productDao.insert(product);
        return "Product inserted successfully";
    }
}