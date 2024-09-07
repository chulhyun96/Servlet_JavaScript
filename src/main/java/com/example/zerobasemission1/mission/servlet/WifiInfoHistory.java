package com.example.zerobasemission1.mission.servlet;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

@WebServlet("/history")
public class WifiInfoHistory extends HttpServlet {
    private static final WifiInfoRepository wifiInfoRepository = new WifiInfoRepository();
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        Member member = Member.getInstance();
        List<History> historyList = wifiInfoRepository.findMyHistoryList(member.getId());
        System.out.println(historyList.size());
        request.setAttribute("historyList", historyList);
        request.getRequestDispatcher("my-history.jsp").forward(request, response);
    }
}
