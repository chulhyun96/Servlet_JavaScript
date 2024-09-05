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
        System.out.println("lat = " + lat);
        double lnt = Double.parseDouble(request.getParameter("lnt"));
        System.out.println("lnt = " + lnt);
        List<WifiInfo> searchList = wifiInfoRepository.getSearchList(lat, lnt);
        System.out.println("searchList = " + searchList.size());
    }
}
