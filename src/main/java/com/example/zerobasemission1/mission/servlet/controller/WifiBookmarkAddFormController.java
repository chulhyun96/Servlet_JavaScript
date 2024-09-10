package com.example.zerobasemission1.mission.servlet.controller;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class WifiBookmarkAddFormController implements Controller {
    @Override
    public void process(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String viewPath = "/WEB-INF/bookmark-add.jsp";
        request.getRequestDispatcher(viewPath).forward(request, response);
    }
}
