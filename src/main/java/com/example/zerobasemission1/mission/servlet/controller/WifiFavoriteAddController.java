package com.example.zerobasemission1.mission.servlet.controller;

import com.example.zerobasemission1.mission.servlet.Member;
import com.example.zerobasemission1.mission.servlet.WifiInfoRepository;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class WifiFavoriteAddController implements Controller{
    public final WifiInfoRepository wifiInfoRepository = WifiInfoRepository.getInstance();
    @Override
    public void process(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("text/html;charset=utf-8");
        System.out.println("즐겨찾기 추가 호출");

        Member member = Member.getInstance();
        String groupId = request.getParameter("group");
        String id = request.getParameter("wifiId");

        wifiInfoRepository.saveFavoriteList(Long.parseLong(groupId), member.getId(),id);
        response.sendRedirect("my-favorite-list");
    }
}
