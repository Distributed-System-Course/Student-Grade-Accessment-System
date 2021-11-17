package com.example.redshop.interceptors;

import com.example.redshop.domain.Admin;
import com.example.redshop.domain.buyer;
import com.example.redshop.domain.seller;
import com.example.redshop.util.Const;
import org.springframework.util.StringUtils;
import org.springframework.web.servlet.HandlerInterceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;


public class LoginInterceptor implements HandlerInterceptor {
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws IOException {
        Admin user = (Admin)request.getSession().getAttribute(Const.ADMIN);
        seller seller = (seller)request.getSession().getAttribute(Const.seller);
        buyer buyer = (buyer)request.getSession().getAttribute(Const.buyer);
        if(!StringUtils.isEmpty(user) || !StringUtils.isEmpty(seller) || !StringUtils.isEmpty(buyer)){
            return true;
        }
        response.sendRedirect(request.getContextPath() + "/system/login");
        return false;
    }

}
