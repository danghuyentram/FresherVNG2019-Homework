package rockPaperScissors.filter;

import com.google.gson.Gson;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.AbstractAuthenticationProcessingFilter;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;
import org.springframework.security.web.util.matcher.RequestMatcher;
import rockPaperScissors.model.User;
import rockPaperScissors.service.TokenAuthenticationService;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Collections;
import java.util.stream.Collectors;

/**
 * Created by nhs3108 on 29/03/2017.
 */
public class JWTLoginFilter extends AbstractAuthenticationProcessingFilter {
    public JWTLoginFilter(String url, AuthenticationManager authManager) {
        super(new AntPathRequestMatcher(url));
        setAuthenticationManager(authManager);
    }

//    @Override
//    public Authentication attemptAuthentication(HttpServletRequest request, HttpServletResponse response) throws AuthenticationException, IOException, ServletException {
//        User user = new User();
//        user.setUserName(request.getParameter("username"));
//        user.setUserPassword(request.getParameter("password"));
//
//        return getAuthenticationManager().authenticate(
//                new UsernamePasswordAuthenticationToken(
//                        user.getUserName(),
//                        user.getUserPassword(),
//                        Collections.emptyList()
//                )
//        );
//    }


    @Override
    public Authentication attemptAuthentication(HttpServletRequest request, HttpServletResponse response) throws AuthenticationException, IOException, ServletException {

        User user = new User();

        if(request.getParameter("username")!=null){
            user.setUserName(request.getParameter("username"));
            user.setUserPassword(request.getParameter("password"));
        }
        else{
            String test="";
            if ("POST".equalsIgnoreCase(request.getMethod()))
            {
                test  = request.getReader().lines().collect(Collectors.joining(System.lineSeparator()));
            }
            Gson g = new Gson();
            user = g.fromJson(test, User.class);

        }



        return getAuthenticationManager().authenticate(
                new UsernamePasswordAuthenticationToken(
                        user.getUserName(),
                        user.getUserPassword(),
                        Collections.emptyList()
                )
        );
    }

    @Override
    protected void successfulAuthentication(HttpServletRequest request, HttpServletResponse response, FilterChain chain, Authentication authResult) throws IOException, ServletException {
        TokenAuthenticationService.addAuthentication(response, authResult.getName());
        logger.info("User: "+authResult.getName()+" is login success");
    }
}