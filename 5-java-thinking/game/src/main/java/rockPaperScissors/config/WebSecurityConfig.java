package rockPaperScissors.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.password.NoOpPasswordEncoder;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import rockPaperScissors.filter.JWTAuthenticationFilter;
import rockPaperScissors.filter.JWTLoginFilter;
import javax.sql.DataSource;


/**
 * Created by nhs3108 on 29/03/2017.
 */
@Configuration
@EnableWebSecurity
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {
    @Autowired
    private DataSource dataSource;

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.csrf().disable().authorizeRequests()

                .antMatchers("/").permitAll() // Có nghĩa là request "/" ko cần phải đc xác thực
                .antMatchers(HttpMethod.POST, "/gamerps/login").permitAll()
                .antMatchers(HttpMethod.GET, "/gamerps/signup").permitAll() // Request dạng POST tới "/login" luôn được phép truy cập dù là đã authenticated hay chưa
                .anyRequest().authenticated() // Các request còn lại đều cần được authenticated
                .and()
                // Add các filter vào ứng dụng của chúng ta, thứ mà sẽ hứng các request để xử lý trước khi tới các xử lý trong controllers.
                // Về thứ tự của các filter, các bạn tham khảo thêm tại http://docs.spring.io/spring-security/site/docs/3.0.x/reference/security-filter-chain.html mục 7.3 Filter Ordering
                .addFilterBefore(new JWTLoginFilter("/gamerps/login", authenticationManager()), UsernamePasswordAuthenticationFilter.class)
                .addFilterBefore(new JWTAuthenticationFilter(), UsernamePasswordAuthenticationFilter.class);
    }

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
//        auth.inMemoryAuthentication().withUser("admin").password("{noop}password").roles("ADMIN");

       // Mình comment phần dưới này vì chúng ta ko sử dụng DB nhé. Nếu các bạn sử dụng, bỏ comment và config query sao cho phù hợp. Các bạn có thể GG để tìm hiểu thêm
       auth.jdbcAuthentication().dataSource(dataSource)
               .passwordEncoder(NoOpPasswordEncoder.getInstance())
               .usersByUsernameQuery("select user_name,user_password, true as enabled from user where user_name=?")
               .authoritiesByUsernameQuery("select user_name, 'ROLE_ADMIN' from user where user_name=?");


    }
}
