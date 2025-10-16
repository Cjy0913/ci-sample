package com.sparta.delivery.global.unit.utils;

<<<<<<< Updated upstream
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletRequest;
=======
>>>>>>> Stashed changes
import jakarta.servlet.http.HttpServletResponse;
import java.time.Duration;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseCookie;

<<<<<<< Updated upstream

=======
>>>>>>> Stashed changes
public class CookieUtils {

    public static void setRefreshTokenCookie(HttpServletResponse response, String refreshToken, Duration tokenTime) {

        ResponseCookie responseCookie = ResponseCookie.from("refreshToken", refreshToken)
                .httpOnly(true)
                .secure(true)
                .path("/")
                .maxAge(tokenTime)
                .sameSite("None")
                .build();

        response.addHeader(HttpHeaders.SET_COOKIE, responseCookie.toString());
    }

<<<<<<< Updated upstream
    public static String getRefreshTokenCookie(HttpServletRequest request) {
        if (request.getCookies() == null) return null;

        for (Cookie cookie : request.getCookies()) {
            if ("refreshToken".equals(cookie.getName())) {
                return cookie.getValue();
            }
        }
        return null;
    }

=======
>>>>>>> Stashed changes
    public static void deleteRefreshTokenCookie(HttpServletResponse response) {

        ResponseCookie deleteCookie = ResponseCookie.from("refreshToken", "")
                .httpOnly(true)
                .secure(true)
                .path("/")
                .sameSite("None")
                .maxAge(0)
                .build();

        response.addHeader(HttpHeaders.SET_COOKIE, deleteCookie.toString());
    }
<<<<<<< Updated upstream
}

=======
}
>>>>>>> Stashed changes
