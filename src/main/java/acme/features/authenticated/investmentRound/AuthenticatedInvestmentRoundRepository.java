
package acme.features.authenticated.investmentRound;

import java.util.Collection;

import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import acme.entities.InvestmentRound;
import acme.framework.repositories.AbstractRepository;

@Repository
public interface AuthenticatedInvestmentRoundRepository extends AbstractRepository {

	@Query("select ir from InvestmentRound ir where ir.id = ?1")
	InvestmentRound findOneById(int id);

	@Query("select ir from InvestmentRound ir where exists(select a from Activity a where a.endDate > current_date() AND a.investmentRound.id = ir.id)")
	Collection<InvestmentRound> findActiveInvestmentRounds();

	@Query("select a.investmentRound from Application a where a.status='ACCEPTED' AND (a.investor.userAccount.id = ?1 OR a.investmentRound.entrepreneur.userAccount.id = ?1)")
	Collection<InvestmentRound> findMyInvestmentRounds(int id);

	@Query("select ir from InvestmentRound ir")
	Collection<InvestmentRound> findAllRounds();

	@Query("select sum(budget.amount) from Activity a where a.investmentRound.id = ?1")
	Double getBudgetSumOfInvestmentRound(int id);
}
