package com.example.zerobasemission1.mission.servlet;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/favorite-add")
public class WifiBookmarkFavorite extends HttpServlet {
    private static final WifiInfoRepository wifiInfoRepository = new WifiInfoRepository();

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("text/html;charset=utf-8");
        System.out.println("즐겨찾기 추가 호출");
        Member member = Member.getInstance();
        String groupId = request.getParameter("group");
        wifiInfoRepository.saveFavoriteList(Long.parseLong(groupId), member.getId());
        request.getRequestDispatcher("my-favorite-list").forward(request, response);
    }
}
