package com.walking.common;

import org.springframework.util.StringUtils;

public class CommonUtils {
    /**
     * 인증 제외 URL확인 (특정 패스이하 전부 허용의 경우 [/패스/**], 그이외의 경우 완전일치만 허용)
     * @param urlPattern
     * @param url
     * @return
     */
    public static boolean match (String urlPattern, String url){
        // path 디렉토리이하를 전부 허용한 경우
        if (urlPattern.endsWith("/**")){
            String match = urlPattern.substring(0, urlPattern.lastIndexOf("**"));
            String matchEqual = urlPattern.substring(0, urlPattern.lastIndexOf("/**"));
            return url.startsWith(match) || url.equals(matchEqual);
        } else {
            // 단일패스 허용
            if (StringUtils.pathEquals(urlPattern, url)){
                return true;
            }
        }
        return false;
    }
}
