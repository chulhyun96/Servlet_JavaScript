package com.example.zerobasemission1.mission.servlet;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.time.LocalDateTime;

@WebServlet("/update-bookmark")
public class WifiUpdateBookmark extends HttpServlet {
    private static final WifiInfoRepository wifiInfoRepository = new WifiInfoRepository();

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("utf-8");
        String bookmarkName = request.getParameter("bookmarkName");
        String orders = request.getParameter("orders");
        String bookmarkId = request.getParameter("bookmarkId");


        System.out.println("bookmarkId = " + bookmarkId);
        System.out.println("bookmarkName = " + bookmarkName);
        System.out.println("orders = " + orders);

        int row = wifiInfoRepository.updateWifiInfo(Long.parseLong(bookmarkId), bookmarkName, Long.parseLong(orders), LocalDateTime.now());
        System.out.println("update =  " + row);
        response.sendRedirect("bookmark-group");
    }
}
