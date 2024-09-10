package com.example.zerobasemission1.mission.servlet.controller;

import com.example.zerobasemission1.mission.servlet.WifiInfoRepository;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class WifiDeleteFavoriteController implements Controller {
    private static final WifiInfoRepository wifiInfoRepository = WifiInfoRepository.getInstance();

    @Override
    public void process(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String favoriteId = request.getParameter("favoriteId");
        wifiInfoRepository.updateFavoriteStatus(Long.parseLong(favoriteId));
        response.sendRedirect("my-favorite-list");
    }
}
