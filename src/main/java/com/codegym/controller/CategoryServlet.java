package com.codegym.controller;

import com.codegym.dao.category.CategoryDao;
import com.codegym.dao.stone.StoneDao;
import com.codegym.model.Category;
import com.codegym.model.Stone;
import com.codegym.service.category.CategoryService;
import com.codegym.service.category.ICategoryService;
import com.codegym.service.stone.IStoneService;
import com.codegym.service.stone.StoneService;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@WebServlet(name = "CategoryServlet", value = "/category")
public class CategoryServlet extends HttpServlet {

    private ICategoryService categoryService;
    private IStoneService stoneService;

    public CategoryServlet() {
        this.categoryService = new CategoryService(new CategoryDao());
        this.stoneService = new StoneService((new StoneDao()));
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String action = request.getParameter("action");
        if (action == null) {
            action = "";
        }
        switch (action) {
            case "create": {
                RequestDispatcher dispatcher = request.getRequestDispatcher("/category/create.jsp");
                dispatcher.forward(request, response);
                break;
            }
            case "view": {
                int category_Id = Integer.parseInt(request.getParameter("id"));
                List<Stone> stones = stoneService.findAllByCategory(category_Id);
                request.setAttribute("stones", stones);
                RequestDispatcher dispatcher = request.getRequestDispatcher("/category/view.jsp");
                dispatcher.forward(request, response);
                break;
            }
            default: {
                List<Category> categories = categoryService.findAll();
                request.setAttribute("categories", categories);
                RequestDispatcher dispatcher = request.getRequestDispatcher("/category/list.jsp");
                dispatcher.forward(request, response);
                break;
            }
        }

    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String action = request.getParameter("action");
        if (action == null) {
            action = "";
        }

        switch (action) {
            case "create": {
                String name = request.getParameter("name");
                Category category = new Category(name);
                categoryService.create(category);
                response.sendRedirect("/category");
                break;
            }
//            case "delete": {
//                deleteCustomer(request, response);
//                break;
//            }
//            case "edit": {
//                editCustomer(request, response);
//                break;
//            }
        }
    }

}
