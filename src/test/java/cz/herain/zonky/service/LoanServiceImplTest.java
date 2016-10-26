package cz.herain.zonky.service;

import cz.herain.zonky.ZonkyRestConsumingApplicationTest;
import cz.herain.zonky.dao.LoanDao;
import cz.herain.zonky.domain.Loan;
import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;

import java.time.OffsetDateTime;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;
import static org.mockito.Mockito.*;

/**
 * Created by Vít on 26. 10. 2016.
 */
public class LoanServiceImplTest extends ZonkyRestConsumingApplicationTest {

    @InjectMocks
    private LoanServiceImpl loanService;

    @Mock
    private LoanDao loanDaoMock;

    private Loan loan1;
    private Loan loan2;
    private Loan loan3;
    private List<Loan> loans;
    private List<Loan> emptyLoans;

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

        this.loans = Arrays.asList(loan1, loan2, loan3);
        this.emptyLoans = Collections.emptyList();
    }

    @Test
    public void testFilterOutAlreadyKnownLoans() {
        List<Loan> expectedUnknownLoans = Arrays.asList(loan1, loan3);

        // loan1 is unknown yet
        when(loanDaoMock.exists(loan1)).thenReturn(false);
        // loan2 is known
        when(loanDaoMock.exists(loan2)).thenReturn(true);
        // loan3 is unknown
        when(loanDaoMock.exists(loan3)).thenReturn(false);

        List<Loan> actualFilteredLoans = loanService.filterOutAlreadyKnownLoans(loans);
        assertThat(actualFilteredLoans, is(expectedUnknownLoans));
    }

    @Test
    public void testFilterOutEmptyListNotFail() {
        List<Loan> expectedLoans = Collections.emptyList();
        List<Loan> actualLoans = loanService.filterOutAlreadyKnownLoans(emptyLoans);
        assertThat(actualLoans, is(expectedLoans));
    }

    @Test
    public void testSaveLoans() {
        doNothing().when(loanDaoMock).saveAll(loans);

        loanService.saveLoans(loans);

        verify(loanDaoMock, times(1)).saveAll(loans);
    }
}
