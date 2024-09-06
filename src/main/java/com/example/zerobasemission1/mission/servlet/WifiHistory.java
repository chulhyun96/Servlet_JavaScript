package com.example.zerobasemission1.mission.servlet;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

@WebServlet("/my-history")
public class WifiHistory extends HttpServlet {
    private final WifiInfoRepository wifiInfoRepository = new WifiInfoRepository();

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        Member member = Member.getInstance();
        List<WifiInfo> myHistoryList = wifiInfoRepository.loadMyHistory(member.getId());
        request.setAttribute("myHistoryList", myHistoryList);
        request.getRequestDispatcher("my-history.jsp").forward(request, response);

    }
}
