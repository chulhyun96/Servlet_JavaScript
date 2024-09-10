package com.example.zerobasemission1.mission.servlet.controller;

import com.example.zerobasemission1.mission.servlet.Bookmark;
import com.example.zerobasemission1.mission.servlet.Member;
import com.example.zerobasemission1.mission.servlet.WifiInfo;
import com.example.zerobasemission1.mission.servlet.WifiInfoRepository;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

public class WifiDetailController implements Controller {
    private final WifiInfoRepository wifiInfoRepository = WifiInfoRepository.getInstance();
    @Override
    public void process(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String mgrNo = request.getParameter("mgrNo");
        WifiInfo findWifi = wifiInfoRepository.findById(mgrNo);
        System.out.println("조회 완료 ID = " + findWifi.getId());
        System.out.println("mgrNo = " + mgrNo);

        Member member = Member.getInstance();
        List<Bookmark> bookMarkInfo = wifiInfoRepository.findBookMarkInfo(member.getId());
        System.out.println("Bookmark INFO SIZE  = " + bookMarkInfo.size());

        request.setAttribute("bookMarkInfo", bookMarkInfo);
        request.setAttribute("findWifi", findWifi);

        String viewPath = "/WEB-INF/detail.jsp";
        request.getRequestDispatcher(viewPath).forward(request, response);
    }
}
