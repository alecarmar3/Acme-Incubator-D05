
package acme.features.authenticated.message;

import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import acme.entities.Message;
import acme.framework.components.Model;
import acme.framework.components.Request;
import acme.framework.entities.Authenticated;
import acme.framework.entities.Principal;
import acme.framework.services.AbstractListService;

@Service
public class AuthenticatedMessageListService implements AbstractListService<Authenticated, Message> {

	@Autowired
	AuthenticatedMessageRepository repository;


	@Override
	public boolean authorise(final Request<Message> request) {
		assert request != null;
		boolean result, isTheEntrepreneur, isTheInvestor;
		int investmentRoundId, accountId;

		investmentRoundId = request.getModel().getInteger("id");
		Principal principal = request.getPrincipal();

		accountId = principal.getAccountId();

		isTheEntrepreneur = this.repository.findInvestmentRoundsOfEntrepreneurById(accountId).contains(this.repository.getInvestmentRoundById(investmentRoundId));

		isTheInvestor = this.repository.findInvestmentRoundsOfInvestorById(accountId).contains(this.repository.getInvestmentRoundById(investmentRoundId));

		result = isTheEntrepreneur || isTheInvestor;

		return result;
	}

	@Override
	public void unbind(final Request<Message> request, final Message entity, final Model model) {
		assert request != null;
		assert entity != null;
		assert model != null;

		request.unbind(entity, model, "title", "creationDate", "tags");
	}

	@Override
	public Collection<Message> findMany(final Request<Message> request) {
		assert request != null;

		Collection<Message> result;
		int id;

		id = request.getModel().getInteger("id");

		result = this.repository.findMessagesOfInvestmentRound(id);

		return result;
	}

	public void onSuccess() {

	}
}
