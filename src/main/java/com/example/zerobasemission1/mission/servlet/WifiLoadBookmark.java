package com.example.zerobasemission1.mission.servlet;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

@WebServlet("/bookmark-group")
public class WifiLoadBookmark extends HttpServlet {
    private static final WifiInfoRepository wifiInfoRepository = new WifiInfoRepository();

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        Member member = Member.getInstance();
        List<Bookmark> bookmarkList = wifiInfoRepository.findBookMarkList(member.getId());
        System.out.println("bookmark List size = " + bookmarkList.size());
        request.setAttribute("bookmarkList", bookmarkList);
        request.getRequestDispatcher("bookmark-group.jsp").forward(request, response);
    }
}
