package com.seri.web.context;

import com.seri.web.utils.GlobalFunUtils;
import com.sun.org.apache.xpath.internal.operations.Bool;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;

import javax.servlet.*;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Arrays;
import java.util.Map;

/**
 * Created by puneet on 07/04/16.
 */
public class SeriSecurityFilter implements Filter {
    GlobalFunUtils globalFunUtils = new GlobalFunUtils();
    public static final String[] CONFIG_RESOURCE_DIRECTORY = new String[] {"resources","login"};


    @Override
    public void init(FilterConfig filterConfig) throws ServletException {

    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        HttpServletRequest request = (HttpServletRequest) servletRequest;
        HttpServletResponse response = (HttpServletResponse) servletResponse;
        Cookie[] cookies = request.getCookies();


        //

        String seriURL = request.getRequestURL().toString();
        String pageName = null;
        String tempUrl[] = seriURL.split("/");
        if(tempUrl.length>3)
            pageName=tempUrl[3];


        if(tempUrl!=null && !Arrays.asList(CONFIG_RESOURCE_DIRECTORY).contains(pageName)) {

            Map<String, String> loggedInUser = globalFunUtils.getLoggedInUserDetails();
            if (loggedInUser.get("firstLogin").equals("1") && !pageName.equals("updateprofile")) {
                Boolean tempFlag = false;
                for (Cookie cookie : cookies) {
                    if (cookie.getName().equalsIgnoreCase("firstTimeUserRedirect")) {
                        if (cookie.getValue().equals("1")) {
                            tempFlag = true;
                            break;
                        }
                    }
                }
                if (!tempFlag) {
                    Cookie ck = new Cookie("firstTimeUserRedirect", "1");
                    ck.setPath("/");
                    response.addCookie(ck);

                    response.sendRedirect(globalFunUtils.getSiteUrl(request) + "/updateprofile");
                }
            }

        }
        filterChain.doFilter(servletRequest, servletResponse);
    }

    @Override
    public void destroy() {

    }
}
