
package acme.entities;

import java.util.Date;

import javax.persistence.Column;
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
import javax.validation.constraints.Pattern;

import org.hibernate.validator.constraints.URL;

import acme.entities.roles.Entrepreneur;
import acme.framework.entities.DomainEntity;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
@Table(indexes = {
	@Index(columnList = "entrepreneur_id")
})
public class InvestmentRound extends DomainEntity {

	// Serialization identifier -----------------------------------------------

	private static final long	serialVersionUID	= 1L;

	// Attributes --------------------------------------------------------------

	@Column(unique = true)
	@NotBlank
	@Pattern(regexp = "^[A-Z]{3}[-][0-9]{2}[-][0-9]{6}$", message = "{default.error.ticker-pattern}")
	private String				ticker;

	@NotNull
	@Temporal(TemporalType.TIMESTAMP)
	@Past
	private Date				creationDate;

	@NotBlank
	private String				kindOfRound; //SEED, ANGEL, SERIES-A, SERIES-B, SERIES-C, BRIDGE

	@NotBlank
	private String				title;

	@NotBlank
	private String				description;

	@NotNull
	private Double				amountOfMoney;

	@URL
	private String				additionalInfo;

	@NotNull
	@Valid
	@ManyToOne(optional = false)
	private Entrepreneur		entrepreneur;

	//Este método hay que implementarlo en los Create/Update Services del siguientre entregable

	//	public boolean validTicker() {
	//
	//		boolean firstPart, secondPart;
	//
	//		String SSS = this.ticker.substring(0, 3);
	//		String YY = this.ticker.substring(4, 6);
	//
	//		String FirstThreeActivitySector = this.entrepreneur.getActivitySector().substring(0, 3);
	//		String LastTwoCreationYear = this.creationDate.toString().substring(8, 10);
	//
	//		firstPart = SSS.equals(FirstThreeActivitySector);
	//		secondPart = YY.equals(LastTwoCreationYear);
	//
	//		return firstPart == true && secondPart == true;
	//	}

}
