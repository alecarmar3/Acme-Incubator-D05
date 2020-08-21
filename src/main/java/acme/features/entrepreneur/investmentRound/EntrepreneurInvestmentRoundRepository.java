
package acme.features.entrepreneur.investmentRound;

import java.util.Collection;

import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import acme.entities.InvestmentRound;
import acme.framework.repositories.AbstractRepository;

@Repository
public interface EntrepreneurInvestmentRoundRepository extends AbstractRepository {

	@Query("select ir from InvestmentRound ir where ir.id = ?1")
	InvestmentRound findOneById(int id);

	@Query("select ir from InvestmentRound ir where ir.entrepreneur.id = ?1")
	Collection<InvestmentRound> findMyInvestmentRounds(int id);

	@Query("select sum(budget.amount) from Activity a where a.investmentRound.id = ?1")
	Double getBudgetSumOfInvestmentRound(int id);

}
