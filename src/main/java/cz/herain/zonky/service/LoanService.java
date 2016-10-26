package cz.herain.zonky.service;

import cz.herain.zonky.domain.Loan;

import java.util.List;

/**
 * Created by Vít on 19. 10. 2016.
 */
public interface LoanService {

    List<Loan> filterOutAlreadyKnownLoans(List<Loan> loans);

    void saveLoans(List<Loan> loans);
}
