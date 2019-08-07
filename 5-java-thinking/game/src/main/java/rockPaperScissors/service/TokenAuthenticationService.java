package rockPaperScissors.service;

import com.auth0.net.TokenRequest;
import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.gson.Gson;
import com.rockPaperScissors.grpc.gamerps.TokenMessage;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import net.minidev.json.JSONObject;
import org.bouncycastle.math.ec.ScaleYPointMap;
import org.springframework.http.HttpStatus;
import org.springframework.security
        .authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import rockPaperScissors.model.User;

import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.*;
import java.util.function.Function;

import static java.util.Collections.emptyList;

public class TokenAuthenticationService {
    static final long EXPIRATIONTIME = 864_000_000; // 10 days
    static final String SECRET = "ThisIsASecret";
    static final String TOKEN_PREFIX = "Bearer";
    static final String HEADER_STRING = "Authorization";
    static  String username="";
    static Integer iduser;

    public static void addAuthentication(HttpServletResponse res, String username) throws IOException {
//        Map<String,Object> map = new HashMap<>();
//        map.put("username",username);
//        map.put("iduser",iduser);

        String JWT = Jwts.builder()
                .setSubject(username)
                .setExpiration(new Date(System.currentTimeMillis() + EXPIRATIONTIME))
                .signWith(SignatureAlgorithm.HS512, SECRET)
                .compact();
        String token = HEADER_STRING+ TOKEN_PREFIX + " " + JWT;
        res.addHeader(HEADER_STRING, TOKEN_PREFIX + " " + JWT);

        token=token.split("Authorization")[1];
        Dictionary<String,String> dictionary = new Hashtable<String,String>();
        dictionary.put("token",token);
        Gson gson = new Gson();
        String json = gson.toJson(dictionary);

        res.setStatus(HttpStatus.OK.value());
        res.getWriter().write(json);
        res.flushBuffer();
    }

    public static String addAuthentication(String username) {
//        Map<String,Object> map = new HashMap<>();
//        map.put("username",username);
//        map.put("iduser",iduser);

        String JWT = Jwts.builder()
                .setSubject(username)
                .setExpiration(new Date(System.currentTimeMillis() + EXPIRATIONTIME))
                .signWith(SignatureAlgorithm.HS512, SECRET)
                .compact();
        String token = HEADER_STRING+ TOKEN_PREFIX + " " + JWT;
        return token;

    }

    public static Authentication getAuthentication(HttpServletRequest request) {
        String token = request.getHeader(HEADER_STRING);
        if (token != null) {
            // parse the token.
            String user = Jwts.parser()
                    .setSigningKey(SECRET)
                    .parseClaimsJws(token.replace(TOKEN_PREFIX, ""))
                    .getBody()
                    .getSubject()
                    ;
            System.out.println("user "+user);
            username=user;


            return user != null ?
                    new UsernamePasswordAuthenticationToken(user,null,  emptyList()) :
                    null;
        }
        return null;
    }





    public static String getUsername(){

        return username;
    }

    public static String getUsername(String token){
        String user = Jwts.parser()
                .setSigningKey(SECRET)
                .parseClaimsJws(token.replace(TOKEN_PREFIX, ""))
                .getBody()
                .getSubject()
                ;
        System.out.println("user "+user);
        username=user;
        return username;
    }



}