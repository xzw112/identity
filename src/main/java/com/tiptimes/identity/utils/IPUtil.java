package com.tiptimes.identity.utils;

import org.apache.commons.lang3.StringUtils;
import javax.servlet.http.HttpServletRequest;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * ClassName IPUtil
 * Description: IP工具类
 * Author user
 * Date 2020/3/24
 * Version V1.0
 **/
public class IPUtil {

    /**
     * 将127.0.0.1形式的IP地址转换成十进制整数，这里没有进行任何错误处理
     * @param strIp
     * @return
     */
    public static long ipToLong(String strIp) {
        if (StringUtils.isBlank(strIp))
            throw new IllegalArgumentException("wrong ip format!");
        long[] ip = new long[4];
        String[] split = strIp.split("\\.");
        if (null == split || split.length < 4)
            throw new IllegalArgumentException("wrong ip format!");
        // 将每个.之间的字符串转换成整型
        ip[0] = Long.parseLong(split[0]);
        ip[1] = Long.parseLong(split[1]);
        ip[2] = Long.parseLong(split[2]);
        ip[3] = Long.parseLong(split[3]);
        return (ip[0] << 24) + (ip[1] << 16) + (ip[2] << 8) + ip[3];
    }

    /**
     * 将十进制整数形式转换成127.0.0.1形式的ip地址
     * @param longIp
     * @return
     */
    public static String longToIP(long longIp) {
        StringBuffer sb = new StringBuffer("");
        // 直接右移24位
        sb.append(String.valueOf((longIp >>> 24)));
        sb.append(".");
        // 将高8位置0，然后右移16位
        sb.append(String.valueOf((longIp & 0x00FFFFFF) >>> 16));
        sb.append(".");
        // 将高16位置0，然后右移8位
        sb.append(String.valueOf((longIp & 0x0000FFFF) >>> 8));
        sb.append(".");
        // 将高24位置0
        sb.append(String.valueOf((longIp & 0x000000FF)));
        return sb.toString();
    }

    /**
     * 判断ipAddress是否为IP
     * @param ipAddress
     * @return
     */
    public static boolean isIP(String ipAddress) {
        if (ipAddress.length() < 7 || ipAddress.length() > 15 || "".equals(ipAddress)) {
            return false;
        }
        /**
         * 判断IP格式和范围
         */
        String rexp = "([1-9]|[1-9]\\d|1\\d{2}|2[0-4]\\d|25[0-5])(\\.(\\d|[1-9]\\d|1\\d{2}|2[0-4]\\d|25[0-5])){3}";

        Pattern pat = Pattern.compile(rexp);

        Matcher mat = pat.matcher(ipAddress);

        return mat.find();
    }

    /**
     * 判断ipAddress是否为内网IP
     * @param ipAddress
     * @return
     */
    public static boolean isInnerIP(String ipAddress) {
        boolean isInnerIp = false;
        boolean isIPFlag = isIP(ipAddress);
        if (isIPFlag) {
            long ipNum = getIpNum(ipAddress);
            /**
             * 私有IP：A类 10.0.0.0-10.255.255.255 B类 172.16.0.0-172.31.255.255 C类
             * 192.168.0.0-192.168.255.255 当然，还有127这个网段是环回地址
             **/
            long aBegin = getIpNum("10.0.0.0");
            long aEnd = getIpNum("10.255.255.255");
            long bBegin = getIpNum("172.16.0.0");
            long bEnd = getIpNum("172.31.255.255");
            long cBegin = getIpNum("192.168.0.0");
            long cEnd = getIpNum("192.168.255.255");
            isInnerIp = isInner(ipNum, aBegin, aEnd) || isInner(ipNum, bBegin, bEnd) || isInner(ipNum, cBegin, cEnd)
                    || ipAddress.equals("127.0.0.1");
        } else {
            if ("localhost".equalsIgnoreCase(ipAddress)) {
                isInnerIp = true;
            }
        }
        return isInnerIp;
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
     * 获取客户端真实的IP地址
     * @param request
     * @return
     */
    public static String getRealClientIpAddr(HttpServletRequest request) {
        String ip = request.getHeader("x-forwarded-for");
        if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
            ip = request.getHeader("Proxy-Client-IP");
        }
        if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
            ip = request.getHeader("WL-Proxy-Client-IP");
        }
        if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
            ip = request.getRemoteAddr();
        }
        return ip;
    }
}
