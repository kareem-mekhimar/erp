/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.bridge.filters;

import java.io.IOException;
import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.apache.shiro.SecurityUtils;

/**
 *
 * @author Bridge
 */

@WebFilter(value="/faces/login.xhtml",asyncSupported = true)
public class LoginFilter implements Filter{

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {

        if(SecurityUtils.getSubject().isAuthenticated())
        {
            HttpServletResponse servletResponse = (HttpServletResponse) response ;
            
            HttpServletRequest servletRequest = (HttpServletRequest) request ;
            
            servletResponse.sendRedirect(servletRequest.getContextPath()+"/faces/home.xhtml");
        }
        
        chain.doFilter(request, response);
    }

    @Override
    public void destroy() {
    }
    
}
