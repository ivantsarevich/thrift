package efr.iv.igr.thriftlimit;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;

@EnableFeignClients
@SpringBootApplication
public class ThriftLimitApplication {

    public static void main(String[] args) {
        SpringApplication.run(ThriftLimitApplication.class, args);
    }

}
