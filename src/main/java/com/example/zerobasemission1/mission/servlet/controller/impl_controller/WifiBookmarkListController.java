package com.example.zerobasemission1.mission.servlet.controller.impl_controller;

import com.example.zerobasemission1.mission.servlet.dto.Bookmark;
import com.example.zerobasemission1.mission.servlet.dto.Member;
import com.example.zerobasemission1.mission.servlet.repository.WifiInfoRepository;
import com.example.zerobasemission1.mission.servlet.controller.Controller;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

public class WifiBookmarkListController implements Controller {
    public final WifiInfoRepository wifiInfoRepository = WifiInfoRepository.getInstance();
    @Override
    public void process(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        Member member = Member.getInstance();
        List<Bookmark> bookmarkList = wifiInfoRepository.findBookMarkList(member.getId());

        String viewPath = "/WEB-INF/bookmark-group.jsp";
        request.setAttribute("bookmarkList", bookmarkList);
        request.getRequestDispatcher(viewPath).forward(request, response);
    }
}
