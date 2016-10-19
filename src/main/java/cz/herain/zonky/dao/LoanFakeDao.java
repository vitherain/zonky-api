package cz.herain.zonky.dao;

import cz.herain.zonky.entity.Loan;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;

/**
 * Created by Vít on 19. 10. 2016.
 */
@Repository
public class LoanFakeDao implements LoanDao {

    private final List<Loan> fakeDatabaseTable = new CopyOnWriteArrayList<>();

    @Override
    public boolean exists(final Loan loan) {
        return fakeDatabaseTable.contains(loan);
    }

    @Override
    public void saveAll(final List<Loan> loans) {
        fakeDatabaseTable.addAll(loans);
    }
}
