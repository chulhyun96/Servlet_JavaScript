package com.example.zerobasemission1.mission.servlet;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/delete-history")
public class WifiInfoDeleteHistory extends HttpServlet {
    private final WifiInfoRepository wifiInfoRepository = new WifiInfoRepository();

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        System.out.println("DeleteButton 히스토리 삭제 요청");
        String id = request.getParameter("id");
        System.out.println(id);
        int resultOfHistory = wifiInfoRepository.updateWifiStatus(Long.parseLong(id));
        System.out.println(resultOfHistory);
        response.sendRedirect("history");
    }
}
