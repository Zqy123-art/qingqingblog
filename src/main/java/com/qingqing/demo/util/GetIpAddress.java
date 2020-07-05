//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by Fernflower decompiler)
//

package com.qingqing.demo.util;

import java.net.InetAddress;
import java.net.UnknownHostException;
import javax.servlet.http.HttpServletRequest;

public class GetIpAddress {
    public GetIpAddress() {
    }

    public static String getIpAddr(HttpServletRequest request) {
        String ipAddress = null;

        try {
            ipAddress = request.getHeader("x-forwarded-for");
            if (ipAddress == null || ipAddress.length() == 0 || "unknown".equalsIgnoreCase(ipAddress)) {
                ipAddress = request.getHeader("Proxy-Client-IP");
            }

            if (ipAddress == null || ipAddress.length() == 0 || "unknown".equalsIgnoreCase(ipAddress)) {
                ipAddress = request.getHeader("WL-Proxy-Client-IP");
            }

            if (ipAddress == null || ipAddress.length() == 0 || "unknown".equalsIgnoreCase(ipAddress)) {
                ipAddress = request.getRemoteAddr();
                if (ipAddress.equals("127.0.0.1")) {
                    try {
                        ipAddress = InetAddress.getLocalHost().getHostAddress();
                    } catch (UnknownHostException var3) {
                        var3.printStackTrace();
                    }
                }
            }

            if (ipAddress != null) {
                return ipAddress.contains(",") ? ipAddress.split(",")[0] : ipAddress;
            } else {
                return "";
            }
        } catch (Exception var4) {
            var4.printStackTrace();
            return "";
        }
    }
}
