
package acme.entities;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.Index;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.Valid;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Past;

import acme.framework.datatypes.Money;
import acme.framework.entities.DomainEntity;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
@Table(indexes = {
	@Index(columnList = "deadline")
})
public class Challenge extends DomainEntity {

	// Serialization identifier -----------------------------------------------

	private static final long	serialVersionUID	= 1L;

	// Attributes --------------------------------------------------------------

	@NotBlank
	private String				title;

	@NotNull
	@Temporal(TemporalType.TIMESTAMP)
	private Date				deadline;

	@Temporal(TemporalType.TIMESTAMP)
	@Past
	private Date				updateDate;

	@NotBlank
	private String				description;

	@NotBlank
	private String				expertGoal;

	@NotBlank
	private String				averageGoal;

	@NotBlank
	private String				rookieGoal;

	@NotNull
	@Valid
	private Money				expertReward;

	@NotNull
	@Valid
	private Money				averageReward;

	@NotNull
	@Valid
	private Money				rookieReward;

}
