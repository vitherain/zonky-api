package cz.herain.zonky.task;

/**
 * Created by Vít on 16. 10. 2016.
 */
public interface LoansConsumerTask {

    String API_URL = "https://api.zonky.cz/loans/marketplace";

    void consumeAndWriteNewLoans() throws Exception;
}
