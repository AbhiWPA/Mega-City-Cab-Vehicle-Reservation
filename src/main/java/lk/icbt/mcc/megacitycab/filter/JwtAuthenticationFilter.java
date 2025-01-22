package lk.icbt.mcc.megacitycab.filter;

import jakarta.servlet.*;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lk.icbt.mcc.megacitycab.enums.Role;
import lk.icbt.mcc.megacitycab.service.JWTService;
import lk.icbt.mcc.megacitycab.util.HeaderHolder;

import java.io.IOException;

/**
 * Title: Mega-City-Cab
 * Description: JwtAuthenticcationFilter Class
 * Created by Abhishek Ashinsa on 1/22/2025
 * Email: abhi.ashinsa@gmail.com
 * Company: Epic Lanka (Pvt) Ltd.
 * Java Version: 17
 */

public class JwtAuthenticationFilter implements Filter {
    private final JWTService jwtService;
    private final HeaderHolder headerHolder;

    public JwtAuthenticationFilter(JWTService jwtService, HeaderHolder headerHolder) {
        this.jwtService = jwtService;
        this.headerHolder = headerHolder;
    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
            throws IOException, ServletException {
        HttpServletRequest httpRequest = (HttpServletRequest) request;
        HttpServletResponse httpResponse = (HttpServletResponse) response;

        String authHeader = httpRequest.getHeader("Authorization");

        httpResponse.setHeader("Access-Control-Allow-Origin", "*");
        httpResponse.setHeader("Access-Control-Allow-Methods", "GET, POST, PUT, DELETE, OPTIONS");
        httpResponse.setHeader("Access-Control-Allow-Headers", "Authorization, Content-Type");

        if (authHeader != null && authHeader.startsWith("Bearer ")) {
            String jwt = authHeader.substring(7);
            String username = jwtService.extractUsername(jwt);

            if (jwtService.isTokenValid(jwt, username)) {
                Role role = jwtService.extractRole(jwt);

                // Store role in request attribute for later use
                httpRequest.setAttribute("role", role);
                httpRequest.setAttribute("username", username);
            }
        }

        chain.doFilter(request, response);
    }

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
        //Filter.super.init(filterConfig);
    }

    @Override
    public void destroy() {
        //Filter.super.destroy();
    }
}
