package com.zzuzl.common;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class CorsFilter implements Filter {
    public void init(FilterConfig filterConfig) throws ServletException {

    }

    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
        /*HttpServletResponse res = (HttpServletResponse) response;
        res.addHeader("Access-Control-Allow-Origin", "http://localhost:8100");
        res.addHeader("Access-Control-Allow-Methods", "POST, GET, OPTIONS, DELETE");
        res.addHeader("Access-Control-Max-Age", "3600");
        // res.addHeader("Access-Control-Allow-Credentials", "true");
        res.addHeader("Access-Control-Allow-Headers", "X-Requested-With, Content-Type, Accept");
*/
        HttpServletRequest req = (HttpServletRequest) request;
        if(req.getMethod().equalsIgnoreCase("OPTIONS")) {
            // res.setStatus(200);
        } else {
            chain.doFilter(request, response);
        }
    }

    public void destroy() {

    }
}
