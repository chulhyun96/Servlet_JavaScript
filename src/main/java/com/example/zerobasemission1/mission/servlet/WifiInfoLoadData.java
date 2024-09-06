package com.example.zerobasemission1.mission.servlet;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;


@WebServlet("/load-wifi")
public class WifiInfoLoadData extends HttpServlet {
    private final WifiInfoRepository wifiInfoRepository = new WifiInfoRepository();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        int loadDataCount = wifiInfoRepository.loadData();
        String result = loadDataCount + "개의 WIFI 정보를 정상적으로 저장하였습니다";
        req.setAttribute("result", result);
        req.getRequestDispatcher("load-wifi-view.jsp").forward(req, resp);
    }
}
