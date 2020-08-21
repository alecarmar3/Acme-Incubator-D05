
package acme.features.authenticated.message;

import java.util.Collection;

import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import acme.entities.InvestmentRound;
import acme.entities.Message;
import acme.framework.repositories.AbstractRepository;

@Repository
public interface AuthenticatedMessageRepository extends AbstractRepository {

	@Query("select ir from Message ir where ir.id = ?1")
	Message findOneById(int id);

	@Query("select m from Message m where m.investmentRound.id = ?1")
	Collection<Message> findMessagesOfInvestmentRound(int id);

	@Query("select ir from InvestmentRound ir where ir.id = ?1")
	InvestmentRound getInvestmentRoundById(int id);

	@Query("select a.investmentRound from Application a where a.investor.userAccount.id = ?1 AND a.status='ACCEPTED'")
	Collection<InvestmentRound> findInvestmentRoundsOfInvestorById(int id);

	@Query("select ir from InvestmentRound ir where ir.entrepreneur.userAccount.id = ?1")
	Collection<InvestmentRound> findInvestmentRoundsOfEntrepreneurById(int id);

}
