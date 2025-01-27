package efr.iv.igr.thriftlimit.runner;

import org.springframework.boot.CommandLineRunner;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.util.concurrent.TimeUnit;

@Component
public class SchedulerCheckRateCurrency implements CommandLineRunner {
    @Override
    public void run(String... args) throws Exception {
        schedule();
    }

    @Scheduled(initialDelay = 1, fixedDelay = 60 * 12, timeUnit = TimeUnit.MINUTES)
    private void schedule() {
    }
}
