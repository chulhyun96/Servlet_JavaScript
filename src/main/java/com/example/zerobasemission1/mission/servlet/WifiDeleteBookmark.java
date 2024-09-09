package com.example.zerobasemission1.mission.servlet;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/delete-bookmark")
public class WifiDeleteBookmark extends HttpServlet {
    private static final WifiInfoRepository wifiInfoRepository = new WifiInfoRepository();
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String bookmarkId = request.getParameter("bookmarkId");
        wifiInfoRepository.updateBookmarkStatus(Long.parseLong(bookmarkId));
        response.sendRedirect("bookmark-group");
    }
}

