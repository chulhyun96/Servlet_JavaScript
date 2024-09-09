package com.example.zerobasemission1.mission.servlet;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/my-favorite-list")
public class WifiFavoriteList extends HttpServlet {
    private static final WifiInfoRepository wifiInfoRepository = new WifiInfoRepository();

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        Member member = Member.getInstance();
        wifiInfoRepository.findMyFavoriteList(member.getId());
    }
}
