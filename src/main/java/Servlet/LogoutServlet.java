package Servlet;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

public class LogoutServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        // 세션을 얻어옵니다.
        HttpSession session = req.getSession(false);

        // 세션에 로그인 정보가 있다면 삭제합니다.
        if (session != null) {
            session.invalidate();
        }

        // 쿠키에서도 로그인 정보를 삭제합니다.
        Cookie[] cookies = req.getCookies();
        if (cookies != null) {
            for (Cookie cookie : cookies) {
                if (cookie.getName().equals("loginID")) {
                    cookie.setMaxAge(0);
                    resp.addCookie(cookie);
                    break;
                }
            }
        }

        // 로그인 페이지로 리다이렉트합니다.
        resp.sendRedirect(req.getContextPath() + "/main");
    }
}
