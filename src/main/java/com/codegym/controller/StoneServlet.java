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
import java.util.List;

@WebServlet(name = "StoneServlet", value = "/stones")
public class StoneServlet extends HttpServlet {

    private IStoneService stoneService;
    private ICategoryService categoryService;

    public StoneServlet() {
        this.stoneService = new StoneService(new StoneDao());
        this.categoryService = new CategoryService((new CategoryDao()));
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String action = request.getParameter("action");
        if (action == null) {
            action = "";
        }
        switch (action) {
            case "create": {
                List<Category> categories = categoryService.findAll();
                request.setAttribute("categories", categories);
                RequestDispatcher dispatcher = request.getRequestDispatcher("/stone/create.jsp");
                dispatcher.forward(request, response);
            }
            case "view": {
                int id = Integer.parseInt(request.getParameter("id"));
                Stone stone = stoneService.findById(id);
                request.setAttribute("stone", stone);
                RequestDispatcher dispatcher = request.getRequestDispatcher("/stone/view.jsp");
                dispatcher.forward(request, response);
            }
            default: {
                List<Stone> stones = stoneService.findAll();
                request.setAttribute("stones", stones);
                RequestDispatcher dispatcher = request.getRequestDispatcher("/stone/list.jsp");
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
                double price = Double.parseDouble(request.getParameter("price"));
                String description = request.getParameter("description");
                String image = request.getParameter("image");
                int category_id = Integer.parseInt(request.getParameter("category_id"));
                Stone stone = new Stone(name, price, description, image,category_id);
                stoneService.create(stone);
                response.sendRedirect("/stones");
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
