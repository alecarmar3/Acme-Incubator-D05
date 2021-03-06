
package acme.features.administrator.challenge;

import java.util.Calendar;
import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import acme.entities.Challenge;
import acme.framework.components.Errors;
import acme.framework.components.Model;
import acme.framework.components.Request;
import acme.framework.entities.Administrator;
import acme.framework.services.AbstractCreateService;

@Service
public class AdministratorChallengeCreateService implements AbstractCreateService<Administrator, Challenge> {

	@Autowired
	AdministratorChallengeRepository repository;


	@Override
	public boolean authorise(final Request<Challenge> request) {
		assert request != null;

		return true;
	}

	@Override
	public void bind(final Request<Challenge> request, final Challenge entity, final Errors errors) {
		assert request != null;
		assert entity != null;
		assert errors != null;

		request.bind(entity, errors);
	}

	@Override
	public void unbind(final Request<Challenge> request, final Challenge entity, final Model model) {
		assert request != null;
		assert entity != null;
		assert model != null;

		request.unbind(entity, model, "title", "deadline", "description", "expertGoal", "averageGoal", "rookieGoal", "expertReward", "averageReward", "rookieReward");
	}

	@Override
	public Challenge instantiate(final Request<Challenge> request) {
		Challenge result;

		result = new Challenge();

		return result;
	}

	@Override
	public void validate(final Request<Challenge> request, final Challenge entity, final Errors errors) {
		assert request != null;
		assert entity != null;
		assert errors != null;

		if (!errors.hasErrors("rookieReward")) {
			Boolean rookieRewardEuros = entity.getRookieReward().getCurrency().matches("€|EUROS|Euros|euros|EUR|Eur|eur");
			errors.state(request, rookieRewardEuros, "rookieReward", "default.error.wrong-currency", entity.getRookieReward());
		}

		if (!errors.hasErrors("averageReward")) {
			Boolean averageRewardEuros = entity.getAverageReward().getCurrency().matches("€|EUROS|Euros|euros|EUR|Eur|eur");
			errors.state(request, averageRewardEuros, "averageReward", "default.error.wrong-currency", entity.getAverageReward());
		}

		if (!errors.hasErrors("expertReward")) {
			Boolean expertRewardEuros = entity.getExpertReward().getCurrency().matches("€|EUROS|Euros|euros|EUR|Eur|eur");
			errors.state(request, expertRewardEuros, "expertReward", "default.error.wrong-currency", entity.getExpertReward());
		}

		if (!errors.hasErrors("rookieReward") && !errors.hasErrors("averageReward")) {
			Boolean lowerThanAverage = entity.getAverageReward().getAmount() > entity.getRookieReward().getAmount();
			errors.state(request, lowerThanAverage, "rookieReward", "administrator.challenge.error.average-higher-than-rookie");
		}

		if (!errors.hasErrors("averageReward") && !errors.hasErrors("rookieReward")) {
			Boolean higherThanRookie = entity.getAverageReward().getAmount() > entity.getRookieReward().getAmount();
			errors.state(request, higherThanRookie, "averageReward", "administrator.challenge.error.average-lower-than-rookie");
		}

		if (!errors.hasErrors("expertReward") && !errors.hasErrors("averageReward")) {
			Boolean higherThanAverage = entity.getExpertReward().getAmount() > entity.getAverageReward().getAmount();
			errors.state(request, higherThanAverage, "expertReward", "administrator.challenge.error.expert-lower-than-average");
		}

		if (!errors.hasErrors("deadline")) {
			Boolean isFuture = entity.getDeadline().after(new Date());
			errors.state(request, isFuture, "deadline", "administrator.challenge.error.past-deadline", entity.getDeadline());
		}
		if (!errors.hasErrors("deadline")) {
			Calendar calendar = Calendar.getInstance();
			calendar.setTime(new Date());
			calendar.add(Calendar.MONTH, 1);
			Boolean isFuture1Month = entity.getDeadline().after(calendar.getTime());
			errors.state(request, isFuture1Month, "deadline", "administrator.challenge.error.past-deadline-1month", entity.getDeadline());
		}

	}

	@Override
	public void create(final Request<Challenge> request, final Challenge entity) {
		this.repository.save(entity);
	}

}
