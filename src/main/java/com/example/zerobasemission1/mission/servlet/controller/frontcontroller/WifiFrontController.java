package com.example.zerobasemission1.mission.servlet.controller.frontcontroller;

import com.example.zerobasemission1.mission.servlet.controller.*;
import com.example.zerobasemission1.mission.servlet.controller.impl_controller.*;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

@WebServlet("/app/*")
public class WifiFrontController extends HttpServlet {
    private Map<String, Controller> controllerMap = new HashMap<>();

    public WifiFrontController() {
        controllerMap.put("/app/search", new WifiSearchController());
        controllerMap.put("/app/load-wifi", new WifiLoadController());
        controllerMap.put("/app/history", new WifiLocationHistoryController());
        controllerMap.put("/app/delete-history", new WifiHistoryDeleteController());
        controllerMap.put("/app/detail", new WifiDetailController());
        controllerMap.put("/app/my-favorite-list", new WifiFavoriteController());
        controllerMap.put("/app/delete-favorite", new WifiDeleteFavoriteController());
        controllerMap.put("/app/favorite-add", new WifiFavoriteAddController());
        controllerMap.put("/app/bookmark-group", new WifiBookmarkListController());
        controllerMap.put("/app/bookmark-add", new WifiBookmarkAddFormController());
        controllerMap.put("/app/bookmark-group-add-submit", new WifiBookmarkAddController());
        controllerMap.put("/app/delete-bookmark", new WifiBookmarkDeleteController());
        controllerMap.put("/app/update-bookmark", new WifiBookmarkUpdateController());
        controllerMap.put("/app/update-bookmark-form", new WifiBookmarkUpdateFormController());
    }

    @Override
    protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        System.out.println("Wifi Front Controller 호출");
        String requestURI = request.getRequestURI();
        System.out.println("requestURI = " + requestURI);

        Controller controller = controllerMap.get(requestURI);
        if (controller == null) {
            response.setStatus(HttpServletResponse.SC_NOT_FOUND);
            return;
        }
        controller.process(request, response);
    }
}
