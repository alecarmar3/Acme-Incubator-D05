
package acme.features.authenticated.investmentRound;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import acme.components.CustomCommand;
import acme.entities.InvestmentRound;
import acme.framework.components.BasicCommand;
import acme.framework.controllers.AbstractController;
import acme.framework.entities.Authenticated;

@Controller
@RequestMapping("/authenticated/investment-round/")
public class AuthenticatedInvestmentRoundController extends AbstractController<Authenticated, InvestmentRound> {

	@Autowired
	private AuthenticatedInvestmentRoundListActiveService	listActiveService;

	@Autowired
	private AuthenticatedInvestmentRoundListMineService		listMineService;

	@Autowired
	private AuthenticatedInvestmentRoundShowService			showService;


	@PostConstruct
	private void initialise() {
		super.addCustomCommand(CustomCommand.LIST_ACTIVE, BasicCommand.LIST, this.listActiveService);
		super.addCustomCommand(CustomCommand.LIST_MINE, BasicCommand.LIST, this.listMineService);
		super.addBasicCommand(BasicCommand.SHOW, this.showService);
	}
}
