package com.example.zerobasemission1.mission.servlet;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

@WebServlet("/search")
public class WifiInfoSearch extends HttpServlet {
    private final WifiInfoRepository wifiInfoRepository = new WifiInfoRepository();
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        //37.4811992, 126.8955428
        double lat = Double.parseDouble(request.getParameter("lat"));
        double lnt = Double.parseDouble(request.getParameter("lnt"));
        List<WifiInfo> searchList = wifiInfoRepository.getSearchList(lat, lnt);

        System.out.println("lat = " + lat);
        System.out.println("lnt = " + lnt);
        System.out.println("searchList = " + searchList.size());

        request.setAttribute("lat",lat);
        request.setAttribute("lnt",lnt);
        if (searchList.isEmpty()) {
            String notService = "서비스 가능한 지역이 아닙니다.";
            request.setAttribute("notService",notService);
            request.getRequestDispatcher("fail-load-data.jsp").forward(request, response);
        }  else {
            request.setAttribute("searchList", searchList);
            request.getRequestDispatcher("success-load-data.jsp").forward(request, response);
        }
    }
}
