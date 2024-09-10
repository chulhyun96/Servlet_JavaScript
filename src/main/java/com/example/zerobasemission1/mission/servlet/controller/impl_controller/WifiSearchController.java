package com.example.zerobasemission1.mission.servlet.controller.impl_controller;

import com.example.zerobasemission1.mission.servlet.entity.Member;
import com.example.zerobasemission1.mission.servlet.entity.WifiInfo;
import com.example.zerobasemission1.mission.servlet.repository.WifiInfoRepository;
import com.example.zerobasemission1.mission.servlet.controller.Controller;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

public class WifiSearchController implements Controller {
    private final WifiInfoRepository wifiInfoRepository = WifiInfoRepository.getInstance();

    @Override
    public void process(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        //37.4811992, 126.8955428
        System.out.println("Search Controller 호출");
        double lat = Double.parseDouble(request.getParameter("lat"));
        double lnt = Double.parseDouble(request.getParameter("lnt"));
        List<WifiInfo> searchList = wifiInfoRepository.findSearchResult(lat, lnt);

        Long id = Member.getInstance().getId(); // findById
        boolean result = wifiInfoRepository.saveMyHistory(id,lat, lnt,true);
        System.out.println(result);

        request.setAttribute("lat",lat);
        request.setAttribute("lnt",lnt);

        String viewPath;
        if (searchList.isEmpty()) {
            viewPath = "/WEB-INF/fail-load-data.jsp";
            String notService = "서비스 가능한 지역이 아닙니다.";
            request.setAttribute("notService",notService);
            request.getRequestDispatcher(viewPath).forward(request, response);
        }  else {
            viewPath = "/WEB-INF/success-load-data.jsp";
            request.setAttribute("searchList", searchList);
            request.getRequestDispatcher(viewPath).forward(request, response);
        }
    }
}
