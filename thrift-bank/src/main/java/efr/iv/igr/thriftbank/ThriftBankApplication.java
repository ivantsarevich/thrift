package efr.iv.igr.thriftbank;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.redis.repository.configuration.EnableRedisRepositories;

@SpringBootApplication
public class ThriftBankApplication {

    public static void main(String[] args) {
        SpringApplication.run(ThriftBankApplication.class, args);
    }

}
