package com.example.zerobasemission1.mission.servlet.controller;

import com.example.zerobasemission1.mission.servlet.Member;
import com.example.zerobasemission1.mission.servlet.WifiInfoRepository;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class WifiBookmarkGroupAddController implements Controller {
    public final WifiInfoRepository wifiInfoRepository = WifiInfoRepository.getInstance();
    @Override
    public void process(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("UTF-8");
        Member member = Member.getInstance();
        String bookMarkName = request.getParameter("bookmarkName");
        String orders = request.getParameter("orders");
        wifiInfoRepository.saveBookmark(member.getId(), bookMarkName, orders);

        String viewPath = "bookmark-group";
        System.out.println("viewPath = " + viewPath);
        response.sendRedirect(viewPath);
    }
}
