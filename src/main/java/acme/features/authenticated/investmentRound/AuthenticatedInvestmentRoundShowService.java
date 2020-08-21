
package acme.features.authenticated.investmentRound;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import acme.entities.InvestmentRound;
import acme.framework.components.Model;
import acme.framework.components.Request;
import acme.framework.entities.Authenticated;
import acme.framework.services.AbstractShowService;

@Service
public class AuthenticatedInvestmentRoundShowService implements AbstractShowService<Authenticated, InvestmentRound> {

	@Autowired
	private AuthenticatedInvestmentRoundRepository repository;


	@Override
	public boolean authorise(final Request<InvestmentRound> request) {
		assert request != null;
		return true;
	}

	@Override
	public void unbind(final Request<InvestmentRound> request, final InvestmentRound entity, final Model model) {
		assert request != null;
		assert entity != null;
		assert model != null;

		request.unbind(entity, model, "ticker", "creationDate", "kindOfRound", "title", "description", "amountOfMoney", "additionalInfo", "entrepreneur.userAccount.username");
	}

	@Override
	public InvestmentRound findOne(final Request<InvestmentRound> request) {
		assert request != null;

		InvestmentRound result;
		int id;
		id = request.getModel().getInteger("id");
		result = this.repository.findOneById(id);

		result.setAmountOfMoney(this.repository.getBudgetSumOfInvestmentRound(id));

		return result;
	}

}
