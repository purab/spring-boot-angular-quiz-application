package in.purabtech.quiz.application.config;

import in.purabtech.quiz.application.service.impl.UserDetailsServiceImpl;
import io.jsonwebtoken.ExpiredJwtException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Component
public class JwtAuthenticationFilter extends OncePerRequestFilter {

    @Autowired
    private UserDetailsServiceImpl userDetailsService;

    @Autowired
    private JwtUtils jwtUitl;

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        final String requestHeaderToken = request.getHeader("Authorization");
        System.out.println(requestHeaderToken);
        String username = null;
        String jwtToken=null;

        if(requestHeaderToken!=null && requestHeaderToken.startsWith("Bearer ")) {
            jwtToken=requestHeaderToken.substring(7);

            try {
                username = jwtUitl.extractUsername(jwtToken);
            } catch (ExpiredJwtException e) {
                e.printStackTrace();
                System.out.println("jwt token has been expired");
            } catch (Exception e) {
                e.printStackTrace();
                System.out.println("another exception");
            }

            //validate toekn
            if(username!=null && SecurityContextHolder.getContext().getAuthentication()==null) {
                final UserDetails userDetails = userDetailsService.loadUserByUsername(username);
                if(jwtUitl.validateToken(jwtToken,userDetails)) {
                    UsernamePasswordAuthenticationToken usernamePasswordAuthenticationToken= new UsernamePasswordAuthenticationToken(userDetails,null,userDetails.getAuthorities());
                    usernamePasswordAuthenticationToken.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
                    //token is valid
                    SecurityContextHolder.getContext().setAuthentication(usernamePasswordAuthenticationToken);
                } else {
                    //token not valid
                    System.out.println("toekn not valid");
                }
            }
        } else {
            System.out.println("invalid token. not start with bearer");
        }
        filterChain.doFilter(request,response);
    }
}
