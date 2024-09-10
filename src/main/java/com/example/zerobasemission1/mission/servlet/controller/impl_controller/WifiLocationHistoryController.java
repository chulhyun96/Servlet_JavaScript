package com.example.zerobasemission1.mission.servlet.controller.impl_controller;

import com.example.zerobasemission1.mission.servlet.dto.History;
import com.example.zerobasemission1.mission.servlet.dto.Member;
import com.example.zerobasemission1.mission.servlet.repository.WifiInfoRepository;
import com.example.zerobasemission1.mission.servlet.controller.Controller;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

public class WifiLocationHistoryController implements Controller {
    private final WifiInfoRepository wifiInfoRepository = WifiInfoRepository.getInstance();
    @Override
    public void process(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        Member member = Member.getInstance();
        List<History> historyList = wifiInfoRepository.findMyHistoryList(member.getId());

        String viewPath = "/WEB-INF/location-history.jsp";
        request.setAttribute("historyList", historyList);
        request.getRequestDispatcher(viewPath).forward(request, response);
    }
}
