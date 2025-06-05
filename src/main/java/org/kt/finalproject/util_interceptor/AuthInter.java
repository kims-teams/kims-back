package org.kt.finalproject.util_interceptor;

import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.interfaces.DecodedJWT;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;

@Component
public class AuthInter implements HandlerInterceptor {

    @Value("${secret}")
    private String secret;

    @Override
    public boolean preHandle(HttpServletRequest request,
                             HttpServletResponse response,
                             Object handler) throws Exception {

        System.out.println(secret);

        if(request.getMethod().equals("OPTIONS")){
            return true;
        }

        String authorization = request.getHeader("Authorization");
        System.out.println("Authorization = " + authorization);
        if(authorization == null || !authorization.startsWith("Bearer ")){
            response.sendError(401);
            return false;
        }

        String token = authorization.replace("Bearer ", "");
         try {
             JWTVerifier verifier = JWT.require(Algorithm.HMAC256(secret))
                     .withIssuer("kimsteams")
                     .build();
             DecodedJWT jwt = verifier.verify(token);
             String subject = jwt.getSubject();
             request.setAttribute("subject", subject);
             return true;
         }catch (Exception e) {
             response.sendError(401);
             return false;
         }
    }
}
