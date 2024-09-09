package com.example.zerobasemission1.mission.servlet;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/bookmark-group-add-submit")
public class WifiBookmarkAdd extends HttpServlet {
    public static final WifiInfoRepository wifiInfoRepository = new WifiInfoRepository();

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("UTF-8");
        Member member = Member.getInstance();
        String bookMarkName = request.getParameter("bookmarkName");
        String orders = request.getParameter("orders");
        wifiInfoRepository.saveBookmark(member.getId(), bookMarkName, orders);
        response.sendRedirect("bookmark-group");
    }
}
