package com.example.zerobasemission1.mission.servlet.controller.impl_controller;

import com.example.zerobasemission1.mission.servlet.entity.Favorite;
import com.example.zerobasemission1.mission.servlet.entity.Member;
import com.example.zerobasemission1.mission.servlet.repository.WifiInfoRepository;
import com.example.zerobasemission1.mission.servlet.controller.Controller;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

public class WifiFavoriteController implements Controller {
    private static final WifiInfoRepository wifiInfoRepository = WifiInfoRepository.getInstance();
    @Override
    public void process(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        System.out.println("Favorite List  호출");

        Member member = Member.getInstance();
        List<Favorite> favoriteList = wifiInfoRepository.findMyFavoriteList(member.getId());
        String viewPath = "/WEB-INF/favorite.jsp";
        request.setAttribute("favoriteList", favoriteList);
        request.getRequestDispatcher(viewPath).forward(request, response);
    }
}
