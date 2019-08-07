package rockPaperScissors;

import org.apache.logging.log4j.LogManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Configuration;
import org.apache.logging.log4j.Logger;


@Configuration
@SpringBootApplication
public class Application implements ApplicationRunner {
    private static final Logger logger = LogManager.getLogger(Application.class);


    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);

    }

    @Override
    public void run(ApplicationArguments args) throws Exception {

    }
}