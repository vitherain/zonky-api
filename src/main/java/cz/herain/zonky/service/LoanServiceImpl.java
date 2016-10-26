package cz.herain.zonky.service;

import cz.herain.zonky.dao.LoanDao;
import cz.herain.zonky.domain.Loan;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

/**
 * Created by Vít on 19. 10. 2016.
 */
@Service
public class LoanServiceImpl implements LoanService {

    @Autowired
    private LoanDao loanDao;

    @Override
    public List<Loan> filterOutAlreadyKnownLoans(final List<Loan> loans) {
        return loans.stream()
                .filter(loan -> !loanDao.exists(loan))
                .collect(Collectors.toList());
    }

    @Override
    public void saveLoans(final List<Loan> loans) {
        loanDao.saveAll(loans);
    }
}
