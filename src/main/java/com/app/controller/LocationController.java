package com.app.controller;

import com.app.service.GeolocationService;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class LocationController {

    @Autowired
    private GeolocationService geoLocationService;

    @GetMapping("/get-location")
    public String getLocation(HttpServletRequest request){
        String clientIp= getClientIp(request);
        String locationInfo= geoLocationService.getLocation("152.58.179.141");
        return locationInfo;
    }

    private String getClientIp(HttpServletRequest request){
        String remoteAddr= request.getRemoteAddr();
        String xForwardedFor = request.getHeader("X-Forwarded-For");
        if(xForwardedFor != null && !xForwardedFor.isEmpty()){
            remoteAddr= xForwardedFor.split(",")[0];
        }
        return remoteAddr;
    }

}
