package cz.herain.zonky.task;

import com.google.common.base.Joiner;
import cz.herain.zonky.domain.Loan;
import cz.herain.zonky.service.LoanService;
import org.apache.commons.collections4.CollectionUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import java.util.Arrays;
import java.util.List;

/**
 * Created by Vít on 16. 10. 2016.
 */
@Component
public class LoansConsumerTaskImpl implements LoansConsumerTask {

    private static final Logger LOGGER = LoggerFactory.getLogger(LoansConsumerTaskImpl.class);

    private static final String ORDERING_HEADER_NAME = "X-Order";
    private static final String ORDERING_HEADER_VALUE = "-datePublished";

    @Autowired
    private RestTemplate restTemplate;

    @Autowired
    private LoanService loanService;

    @Override
    public void consumeAndWriteNewLoans() {
        List<Loan> loans = getLoansFromRestApi();
        printNewLoansOnly(loans);
    }

    private List<Loan> getLoansFromRestApi() {
        HttpHeaders headers = new HttpHeaders();
        headers.add(ORDERING_HEADER_NAME, ORDERING_HEADER_VALUE);
        HttpEntity<?> httpEntity = new HttpEntity<>(headers);

        HttpEntity<Loan[]> response = restTemplate.exchange(API_URL, HttpMethod.GET, httpEntity, Loan[].class);
        return Arrays.asList(response.getBody());
    }

    private void printNewLoansOnly(final List<Loan> loans) {
        final List<Loan> newLoans = loanService.filterOutAlreadyKnownLoans(loans);
        loanService.saveLoans(newLoans);

        if (CollectionUtils.isNotEmpty(newLoans)) {
            LOGGER.info(Joiner.on(", ").join(newLoans));
        } else {
            LOGGER.info("No new loans found on marketplace...");
        }
    }
}
