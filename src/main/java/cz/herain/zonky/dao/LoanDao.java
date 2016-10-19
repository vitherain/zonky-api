package cz.herain.zonky.dao;

import cz.herain.zonky.entity.Loan;

import java.util.List;

/**
 * Created by Vít on 19. 10. 2016.
 */
public interface LoanDao {

    boolean exists(Loan loan);

    void saveAll(List<Loan> loans);
}
