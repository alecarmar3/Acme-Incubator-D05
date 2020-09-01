
package acme.features.entrepreneur.investmentRound;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import acme.entities.Configuration;
import acme.entities.InvestmentRound;
import acme.entities.roles.Entrepreneur;
import acme.framework.components.Errors;
import acme.framework.components.Model;
import acme.framework.components.Request;
import acme.framework.entities.Principal;
import acme.framework.services.AbstractUpdateService;

@Service
public class EntrepreneurInvestmentRoundUpdateFinalModeService implements AbstractUpdateService<Entrepreneur, InvestmentRound> {

	@Autowired
	EntrepreneurInvestmentRoundRepository repository;


	@Override
	public boolean authorise(final Request<InvestmentRound> request) {
		assert request != null;

		Boolean isNotFinalMode = this.repository.findFinalMode(request.getModel().getInteger("id")).equals(false);

		return isNotFinalMode;
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

		request.unbind(entity, model, "title", "description", "finalMode");
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

		int investmentRoundId = entity.getId();
		Boolean showFinalMode = this.repository.findFinalMode(investmentRoundId);

		Configuration config;
		config = this.repository.findManyConfiguration().stream().findFirst().get();

		Principal principal = request.getPrincipal();
		int id = principal.getActiveRoleId();

		String ticker = entity.getTicker();

		Calendar now = Calendar.getInstance();
		int year = now.get(Calendar.YEAR);
		String yearInString = String.valueOf(year);

		String activitySectorOfThisEntrepreneur = this.repository.findActivitySectorOfEntrepreneur(id);

		InvestmentRound investmentRoundWithTicker = this.repository.findInvestmentRoundByTicker(ticker);

		if (!errors.hasErrors("ticker")) {

			Boolean activitySectorLetters = request.getModel().getAttribute("ticker").toString().toUpperCase().substring(0, 3).equals(activitySectorOfThisEntrepreneur.toUpperCase().subSequence(0, 3));

			Boolean yearDigits = request.getModel().getAttribute("ticker").toString().substring(4, 6).equals(yearInString.substring(2, 4));

			errors.state(request, activitySectorLetters, "ticker", "default.error.ticker-pattern.letters", entity.getTicker());
			errors.state(request, yearDigits, "ticker", "default.error.ticker-pattern.yearDigits", entity.getTicker());
			if (investmentRoundWithTicker != null) {
				Boolean investmentRoundWithTickerIsThis = investmentRoundWithTicker.equals(this.repository.findOneById(entity.getId()));
				errors.state(request, investmentRoundWithTickerIsThis, "ticker", "default.error.already-in-use", entity.getTicker());
			}
		}

		if (!errors.hasErrors("kindOfRound")) {
			List<String> kindOfRounds = new ArrayList<String>(Arrays.asList("SEED", "ANGEL", "SERIES-A", "SERIES-B", "SERIES-C", "BRIDGE"));
			Boolean isOneOption = kindOfRounds.contains(entity.getKindOfRound());
			errors.state(request, isOneOption, "kindOfRound", "This value must fit one of these options:" + kindOfRounds.toString(), entity.getKindOfRound());
		}

		if (!errors.hasErrors("amountOfMoney")) {
			Boolean amountOfMoneyEuros = entity.getAmountOfMoney().getCurrency().matches("€|EUROS|Euros|euros|EUR|Eur|eur");
			errors.state(request, amountOfMoneyEuros, "amountOfMoney", "default.error.wrong-currency", entity.getAmountOfMoney());
		}

		if (!errors.hasErrors("finalMode") && entity.getAmountOfMoney() != null) {
			Double acum = this.repository.findBudgetsOfActivities(investmentRoundId).stream().mapToDouble(x -> x.doubleValue()).sum();
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
		entity.setFinalMode(true);

		this.repository.save(entity);
	}

}
