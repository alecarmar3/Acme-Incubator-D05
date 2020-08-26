
package acme.features.entrepreneur.investmentRound;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import acme.entities.Configuration;
import acme.entities.InvestmentRound;
import acme.entities.roles.Entrepreneur;
import acme.framework.components.Errors;
import acme.framework.components.HttpMethod;
import acme.framework.components.Model;
import acme.framework.components.Request;
import acme.framework.components.Response;
import acme.framework.helpers.PrincipalHelper;
import acme.framework.services.AbstractUpdateService;

@Service
public class EntrepreneurInvestmentRoundUpdateService implements AbstractUpdateService<Entrepreneur, InvestmentRound> {

	@Autowired
	EntrepreneurInvestmentRoundRepository repository;


	@Override
	public boolean authorise(final Request<InvestmentRound> request) {
		assert request != null;

		return true;
	}

	@Override
	public void bind(final Request<InvestmentRound> request, final InvestmentRound entity, final Errors errors) {
		assert request != null;
		assert entity != null;
		assert errors != null;

		Date now = new Date(System.currentTimeMillis() - 1);
		entity.setUpdateDate(now);

		request.bind(entity, errors, "updateDate");
	}

	@Override
	public void unbind(final Request<InvestmentRound> request, final InvestmentRound entity, final Model model) {
		assert request != null;
		assert entity != null;
		assert model != null;

		request.unbind(entity, model, "kindOfRound", "title", "description", "amountOfMoney", "finalMode", "additionalInfo");
	}

	@Override
	public InvestmentRound findOne(final Request<InvestmentRound> request) {
		assert request != null;

		InvestmentRound result;
		int id;

		id = request.getModel().getInteger("id");
		result = this.repository.findOneById(id);

		return result;
	}

	@Override
	public void validate(final Request<InvestmentRound> request, final InvestmentRound entity, final Errors errors) {
		assert request != null;
		assert entity != null;
		assert errors != null;

		int id = entity.getId();
		Boolean showFinalMode = this.repository.findFinalMode(id);

		Configuration config;
		config = this.repository.findManyConfiguration().stream().findFirst().get();

		if (!errors.hasErrors("kindOfRound")) {
			List<String> kindOfRounds = new ArrayList<String>(Arrays.asList("SEED", "ANGEL", "SERIES-A", "SERIES-B", "SERIES-C", "BRIDGE"));
			Boolean isOneOption = kindOfRounds.contains(entity.getKindOfRound());
			errors.state(request, isOneOption, "kindOfRound", "This value must fit one of these options:" + kindOfRounds.toString(), entity.getKindOfRound());
		}

		if (!errors.hasErrors("finalMode") && entity.getFinalMode() == true) {
			Double acum = this.repository.findBudgetsOfActivities(id).stream().mapToDouble(x -> x.doubleValue()).sum();
			Boolean workProgrammSumsUpMoney = acum >= entity.getAmountOfMoney().getAmount();
			errors.state(request, workProgrammSumsUpMoney, "amountOfMoney", "default.error.insufficient-budget", entity.getAmountOfMoney());
			errors.state(request, !showFinalMode, "finalMode", "default.error.updating-unavailable", entity.getFinalMode());
		}

		if (!errors.hasErrors("title")) {
			boolean isSpam = config.isSpam(entity.getTitle());
			errors.state(request, !isSpam, "title", "default.error.spam");
		}

		if (!errors.hasErrors("description")) {
			boolean isSpam = config.isSpam(entity.getDescription());
			errors.state(request, !isSpam, "description", "default.error.spam");
		}

	}

	@Override
	public void update(final Request<InvestmentRound> request, final InvestmentRound entity) {
		assert request != null;
		assert entity != null;

		Date now = new Date(System.currentTimeMillis() - 1);
		entity.setUpdateDate(now);

		this.repository.save(entity);
	}

	@Override
	public void onSuccess(final Request<InvestmentRound> request, final Response<InvestmentRound> response) {
		assert request != null;
		assert response != null;

		if (request.isMethod(HttpMethod.POST)) {
			PrincipalHelper.handleUpdate();
		}
	}
}
