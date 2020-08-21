
package acme.entities;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.Index;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.Valid;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Past;

import acme.entities.roles.Bookkeeper;
import acme.framework.entities.DomainEntity;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
@Table(indexes = {
	@Index(columnList = "investment_round_id"), @Index(columnList = "bookkeeper_id")
})
public class AccountingRecord extends DomainEntity {

	// Serialization identifier -----------------------------------------------

	private static final long		serialVersionUID	= 1L;

	// Attributes --------------------------------------------------------------

	@NotBlank
	private String					title;

	@NotNull
	private AccountingRecordStatus	status;

	@NotNull
	@Temporal(TemporalType.TIMESTAMP)
	@Past
	private Date					creationDate;

	@NotBlank
	private String					body;

	@NotNull
	@Valid
	@ManyToOne(optional = false)
	private Bookkeeper				bookkeeper;

	@NotNull
	@Valid
	@ManyToOne(optional = false)
	private InvestmentRound			investmentRound;

}
