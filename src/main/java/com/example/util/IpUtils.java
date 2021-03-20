package com.example.util;

import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;

import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.net.InetAddress;
import java.net.NetworkInterface;
import java.net.SocketException;
import java.util.Enumeration;

/**
 * @Description:
 * @Author: chenchong
 * @Date: 2021/3/20 22:02
 */
@Slf4j
public class IpUtils {
    public static String getIpAddress(HttpServletRequest request) {
        String ip = request.getHeader("x-forwarded-for");
        if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
            ip = request.getHeader("Proxy-Client-IP");
        }
        if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
            ip = request.getHeader("WL-Proxy-Client-IP");
        }
        if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
            ip = request.getHeader("HTTP_CLIENT_IP");
        }
        if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
            ip = request.getHeader("HTTP_X_FORWARDED_FOR");
        }
        if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
            ip = request.getRemoteAddr();
        }
        if(ip.contains(",")){
            log.error("客户端ip 出现拼接----------------------------！！！！！{}",ip);
            String[] split = ip.split(",");
            if (split.length==1){
                ip=split[0];
            }
            if (split.length>1){
                ip=split[1];
            }
        }

        if (ip.contains("，")){
            log.error("客户端ip 出现拼接-----------------------------！！！！！{}",ip);
            String[] split = ip.split("，");
            if (split.length==1){
                ip=split[0];
            }
            if (split.length>1){
                ip=split[1];
            }
        }
        return ip.trim();
    }


    /**
     * 获取请求主机IP地址,如果通过代理进来，则透过防火墙获取真实IP地址;
     *
     * @param request
     * @return
     * @throws IOException
     */
    public final static String getIpAddr(HttpServletRequest request) {
        // 获取请求主机IP地址,如果通过代理进来，则透过防火墙获取真实IP地址

        String ip = request.getHeader("X-Forwarded-For");

        log.info("getIpAddress(HttpServletRequest) - X-Forwarded-For - String ip=" + ip);


        if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
            if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
                ip = request.getHeader("Proxy-Client-IP");
                if (log.isInfoEnabled()) {
                    log.info("getIpAddress(HttpServletRequest) - Proxy-Client-IP - String ip=" + ip);
                }
            }
            if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
                ip = request.getHeader("WL-Proxy-Client-IP");
                if (log.isInfoEnabled()) {
                    log.info("getIpAddress(HttpServletRequest) - WL-Proxy-Client-IP - String ip=" + ip);
                }
            }
            if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
                ip = request.getHeader("HTTP_CLIENT_IP");
                if (log.isInfoEnabled()) {
                    log.info("getIpAddress(HttpServletRequest) - HTTP_CLIENT_IP - String ip=" + ip);
                }
            }
            if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
                ip = request.getHeader("HTTP_X_FORWARDED_FOR");
                if (log.isInfoEnabled()) {
                    log.info("getIpAddress(HttpServletRequest) - HTTP_X_FORWARDED_FOR - String ip=" + ip);
                }
            }
            if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
                ip = request.getRemoteAddr();
                if (log.isInfoEnabled()) {
                    log.info("getIpAddress(HttpServletRequest) - getRemoteAddr - String ip=" + ip);
                }
            }
        } else if (ip.length() > 15) {
            String[] ips = ip.split(",");
            for (int index = 0; index < ips.length; index++) {
                String strIp = (String) ips[index];
                if (!("unknown".equalsIgnoreCase(strIp))) {
                    ip = strIp;
                    break;
                }
            }
        }
        return ip;
    }

    public static boolean isInnerIP(String ipAddress) {
        boolean isInnerIp = false;
        try {
            long ipNum = getIpNum(ipAddress);
            /**
             * 私有IP：A类 10.0.0.0-10.255.255.255
             * B类 172.16.0.0-172.31.255.255
             * C类 192.168.0.0-192.168.255.255
             * 还有127这个网段是环回地址
             **/
            long aBegin = getIpNum("10.0.0.0");
            long aEnd = getIpNum("10.255.255.255");
            long bBegin = getIpNum("172.16.0.0");
            long bEnd = getIpNum("172.31.255.255");
            long cBegin = getIpNum("192.168.0.0");
            long cEnd = getIpNum("192.168.255.255");
            isInnerIp = isInner(ipNum, aBegin, aEnd) || isInner(ipNum, bBegin, bEnd) || isInner(ipNum, cBegin, cEnd)
                    ||      "127.0.0.1".equals(ipAddress);
            return isInnerIp;
        } catch (Exception e) {
            // 有任何报错的话，当内网地址处理
            return true;
        }

    }

    private static long getIpNum(String ipAddress) {
        String[] ip = ipAddress.split("\\.");
        long a = Integer.parseInt(ip[0]);
        long b = Integer.parseInt(ip[1]);
        long c = Integer.parseInt(ip[2]);
        long d = Integer.parseInt(ip[3]);

        long ipNum = a * 256 * 256 * 256 + b * 256 * 256 + c * 256 + d;
        return ipNum;
    }

    private static boolean isInner(long userIp, long begin, long end) {
        return (userIp >= begin) && (userIp <= end);
    }

    /**
     * @param args
     */
    public static void main(String[] args) {
        log.debug("true-or-false,{}",isInnerIP("221.224.63.26, 120.55.124.98"));
    }

    /**
     * 获取本地服务器ip ，如果没有，则返回本地ip
     *
     * @return
     */
    @SuppressWarnings("rawtypes")
    public static String getServerIp() {
        String localip = null;// 本地IP，如果没有配置外网IP则返回它
        String netip = null;// 外网IP
        try {
            Enumeration netInterfaces = NetworkInterface.getNetworkInterfaces();
            InetAddress ip = null;
            boolean finded = false;// 是否找到外网IP
            while (netInterfaces.hasMoreElements() && !finded) {
                NetworkInterface ni = (NetworkInterface) netInterfaces.nextElement();
                Enumeration address = ni.getInetAddresses();
                while (address.hasMoreElements()) {
                    ip = (InetAddress) address.nextElement();
                    if (!ip.isSiteLocalAddress() && !ip.isLoopbackAddress() && ip.getHostAddress().indexOf(":") == -1) {// 外网IP
                        netip = ip.getHostAddress();
                        finded = true;
                        break;
                    } else if (ip.isSiteLocalAddress() && !ip.isLoopbackAddress() && ip.getHostAddress().indexOf(":") == -1) {// 内网IP
                        localip = ip.getHostAddress();
                    }
                }
            }
        } catch (SocketException e) {
            e.printStackTrace();
        }

        if (netip != null && !"".equals(netip)) {
            return netip;
        } else {
            return localip;
        }
    }


    public static String getRemoteIp(HttpServletRequest request) {
        String remoteIp = request.getHeader("x-forwarded-for"); // nginx反向代理
        if (StringUtils.isNotEmpty(remoteIp)) {
            return remoteIp;
        } else {
            remoteIp = request.getHeader("x-forwarded-for");// apache反射代理
            if (StringUtils.isNotEmpty(remoteIp)) {
                String[] ips = remoteIp.split(",");
                for (String ip : ips) {
                    if (!"null".equalsIgnoreCase(ip)) {
                        return ip;
                    }
                }
            }
            return request.getRemoteAddr();
        }
    }
}
