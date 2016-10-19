package cz.herain.zonky.schedule;

import cz.herain.zonky.task.LoansConsumerTask;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

/**
 * Created by Vít on 16. 10. 2016.
 */
@Component
public class ScheduledTasks {

    private static final Logger LOGGER = LoggerFactory.getLogger(ScheduledTasks.class);

    private static final String CRON_EVERY_FIVE_SECONDS = "*/5 * * * * *";
    private static final String CRON_EVERY_FIVE_MINUTES = "0 0/5 * * * ?";

    @Autowired
    private LoansConsumerTask loansConsumerTask;

    @Scheduled(cron = CRON_EVERY_FIVE_MINUTES)
    public void runLoanConsumerTask() {
        try {
            LOGGER.info("Starting scheduled task LoansConsumerTask...");
            loansConsumerTask.consumeAndWriteNewLoans();
            LOGGER.info("Scheduled task LoansConsumerTask ended successfully...");
        } catch (Exception ex) {
            LOGGER.error("Scheduled task LoansConsumerTask ended with error: {} | {}", ex.getClass(), ex.getMessage());
        }
    }
}
