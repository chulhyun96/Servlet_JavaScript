package com.example.zerobasemission1.mission.servlet.controller;

import com.example.zerobasemission1.mission.servlet.WifiInfoRepository;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class WifiHistoryDeleteController implements Controller {
    private final WifiInfoRepository wifiInfoRepository = WifiInfoRepository.getInstance();
    @Override
    public void process(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        System.out.println("DeleteButton 히스토리 삭제 요청");
        String id = request.getParameter("id");
        wifiInfoRepository.updateWifiStatus(Long.parseLong(id));
        response.sendRedirect("history");
    }
}
