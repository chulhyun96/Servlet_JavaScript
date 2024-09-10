package com.example.zerobasemission1.mission.servlet.controller.impl_controller;

import com.example.zerobasemission1.mission.servlet.repository.WifiInfoRepository;
import com.example.zerobasemission1.mission.servlet.controller.Controller;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.time.LocalDateTime;

public class WifiBookmarkUpdateController implements Controller {
    private final WifiInfoRepository wifiInfoRepository = WifiInfoRepository.getInstance();
    @Override
    public void process(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("utf-8");
        String bookmarkName = request.getParameter("bookmarkName");
        String orders = request.getParameter("orders");
        String bookmarkId = request.getParameter("bookmarkId");

        int row = wifiInfoRepository.updateWifiInfo(Long.parseLong(bookmarkId), bookmarkName, Long.parseLong(orders), LocalDateTime.now());
        System.out.println("update =  " + row);
        response.sendRedirect("bookmark-group");
    }
}
