package cz.herain.zonky.schedule;

import cz.herain.zonky.task.LoansConsumerTask;
import org.slf4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

/**
 * Created by Vít on 16. 10. 2016.
 */
@Component
public class ScheduledTasks {

    private static final String CRON_EVERY_FIVE_SECONDS = "*/5 * * * * *";
    private static final String CRON_EVERY_FIVE_MINUTES = "0 0/5 * * * ?";

    @Autowired
    private Logger logger;

    @Autowired
    private LoansConsumerTask loansConsumerTask;

    @Scheduled(cron = CRON_EVERY_FIVE_SECONDS)
    public void runLoanConsumerTask() {
        try {
            logger.info("Starting scheduled task LoansConsumerTask...");
            loansConsumerTask.consumeAndWriteNewLoans();
            logger.info("Scheduled task LoansConsumerTask ended successfully...");
        } catch (Exception ex) {
            logger.error("Scheduled task LoansConsumerTask ended with error: {} | {}", ex.getClass(), ex.getMessage());
        }
    }
}
