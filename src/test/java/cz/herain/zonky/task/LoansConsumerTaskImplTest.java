package cz.herain.zonky.task;

import com.google.common.base.Joiner;
import cz.herain.zonky.ZonkyRestConsumingApplicationTest;
import cz.herain.zonky.domain.Loan;
import cz.herain.zonky.service.LoanService;
import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.slf4j.Logger;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;

import java.time.OffsetDateTime;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import static org.mockito.Matchers.any;
import static org.mockito.Matchers.anyString;
import static org.mockito.Matchers.eq;
import static org.mockito.Mockito.*;

/**
 * Created by Vít on 26. 10. 2016.
 */
public class LoansConsumerTaskImplTest extends ZonkyRestConsumingApplicationTest {

    @InjectMocks
    private LoansConsumerTaskImpl loansConsumerTask;

    @Mock
    private RestTemplate restTemplateMock;

    @Mock
    private LoanService loanServiceMock;

    @Mock
    private Logger logger;

    private Loan loan1;
    private Loan loan2;
    private Loan loan3;
    private Loan[] loans;
    private List<Loan> filteredLoansList;
    private Loan[] emptyLoans;
    private List<Loan> emptyFilteredLoansList;

    @Before
    public void prepareTestData() {
        this.loan1 = Loan.LoanBuilder.aLoan()
                .withId(1L)
                .withAmount(50000)
                .withCovered(true)
                .withDatePublished(OffsetDateTime.parse("2016-10-26T18:38:56.000+02:00"))
                .withDeadline(OffsetDateTime.parse("2016-10-31T23:59:59.999+02:00"))
                .withInterestRate(0.059f)
                .build();

        this.loan2 = Loan.LoanBuilder.aLoan()
                .withId(2L)
                .withAmount(100000)
                .withCovered(true)
                .withDatePublished(OffsetDateTime.parse("2016-10-20T12:00:00.000+02:00"))
                .withDeadline(OffsetDateTime.parse("2016-11-30T23:59:59.999+02:00"))
                .withInterestRate(0.059f)
                .build();

        this.loan3 = Loan.LoanBuilder.aLoan()
                .withId(3L)
                .withAmount(150000000)
                .withCovered(false)
                .withDatePublished(OffsetDateTime.parse("2016-09-22T16:14:15.654+02:00"))
                .withDeadline(OffsetDateTime.parse("2016-09-30T23:59:59.999+02:00"))
                .withInterestRate(0.059f)
                .build();

        this.loans = new Loan[] {loan1, loan2, loan3};
        this.filteredLoansList = Arrays.asList(loan1, loan3);

        this.emptyLoans = new Loan[]{};
        this.emptyFilteredLoansList = Collections.emptyList();
    }

    @Test
    public void testConsumeAndWriteNewLoans() {
        when(restTemplateMock.exchange(eq(LoansConsumerTask.API_URL),
                                    eq(HttpMethod.GET),
                                    any(HttpEntity.class),
                                    eq(Loan[].class)))
                .thenReturn(new ResponseEntity<>(loans, HttpStatus.OK));

        when(loanServiceMock.filterOutAlreadyKnownLoans(Arrays.asList(loans)))
                .thenReturn(filteredLoansList);

        doNothing().when(loanServiceMock).saveLoans(filteredLoansList);

        doNothing().when(logger).info(Joiner.on(", ").join(filteredLoansList));

        // TESTED METHOD
        loansConsumerTask.consumeAndWriteNewLoans();

        //verify that service methods was called
        verify(loanServiceMock, times(1)).filterOutAlreadyKnownLoans(Arrays.asList(loans));
        verify(loanServiceMock, times(1)).saveLoans(filteredLoansList);

        //verify that correct message was logged
        verify(logger, times(1)).info(Joiner.on(", ").join(filteredLoansList));
    }

    @Test
    public void testConsumeEmptyLoansAndNotFail() {
        when(restTemplateMock.exchange(eq(LoansConsumerTask.API_URL),
                                    eq(HttpMethod.GET),
                                    any(HttpEntity.class),
                                    eq(Loan[].class)))
                .thenReturn(new ResponseEntity<>(emptyLoans, HttpStatus.OK));

        when(loanServiceMock.filterOutAlreadyKnownLoans(Arrays.asList(emptyLoans)))
                .thenReturn(emptyFilteredLoansList);

        doNothing().when(loanServiceMock).saveLoans(emptyFilteredLoansList);

        doNothing().when(logger).info(anyString());

        // TESTED METHOD
        loansConsumerTask.consumeAndWriteNewLoans();

        //verify that service methods was called
        verify(loanServiceMock, times(1)).filterOutAlreadyKnownLoans(Arrays.asList(emptyLoans));
        verify(loanServiceMock, times(1)).saveLoans(emptyFilteredLoansList);

        //verify that correct message was logged
        verify(logger, never()).info(Joiner.on(", ").join(emptyFilteredLoansList));
        verify(logger, times(1)).info(anyString());
    }
}
