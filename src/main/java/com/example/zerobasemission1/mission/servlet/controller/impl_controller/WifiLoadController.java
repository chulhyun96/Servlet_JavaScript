package com.example.zerobasemission1.mission.servlet.controller.impl_controller;

import com.example.zerobasemission1.mission.servlet.repository.WifiInfoRepository;
import com.example.zerobasemission1.mission.servlet.controller.Controller;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class WifiLoadController implements Controller {
    private final WifiInfoRepository wifiInfoRepository = WifiInfoRepository.getInstance();
    @Override
    public void process(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        int loadDataCount = wifiInfoRepository.loadData();
        String result = loadDataCount + "개의 WIFI 정보를 정상적으로 저장하였습니다";

        String viewPath ="/WEB-INF/load-wifi-view.jsp";
        request.setAttribute("result", result);
        request.getRequestDispatcher(viewPath).forward(request, response);
    }
}
