package com.example.zerobasemission1.mission.servlet;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

@WebServlet("/detail")
public class WIfiInfoDetail extends HttpServlet {
    private final WifiInfoRepository wifiInfoRepository = new WifiInfoRepository();

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String mgrNo = request.getParameter("mgrNo");
        WifiInfo findWifi = wifiInfoRepository.findById(mgrNo);
        System.out.println("조회 완료 ID = " + findWifi.getId());
        System.out.println("mgrNo = " + mgrNo);

        Member member = Member.getInstance();
        List<Bookmark> bookMarkInfo = wifiInfoRepository.findBookMarkInfo(member.getId());
        System.out.println("Bookmark INFO SIZE  = " + bookMarkInfo.size());
        request.setAttribute("bookMarkInfo", bookMarkInfo);
        request.setAttribute("findWifi", findWifi);
        request.getRequestDispatcher("wifi-detail.jsp").forward(request, response);
    }
}
