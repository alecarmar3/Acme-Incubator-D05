
package acme.entities;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Past;
import javax.validation.constraints.Pattern;

import org.hibernate.validator.constraints.CreditCardNumber;
import org.hibernate.validator.constraints.URL;

import acme.framework.entities.DomainEntity;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
public class Banner extends DomainEntity {

	private static final long	serialVersionUID	= 1L;

	@NotBlank
	@URL
	private String				picture;

	@NotBlank
	private String				slogan;

	@Temporal(TemporalType.TIMESTAMP)
	@Past
	private Date				updateDate;

	@NotBlank
	@URL
	private String				targetUrl;

	@NotBlank
	@CreditCardNumber
	private String				creditCardNumber; //number

	@NotBlank
	private String				holderName;

	@NotBlank
	private String				brand;

	@Pattern(regexp = "^\\d{2}\\/\\d{2}$", message = "{default.banner.error.expiration-date-pattern}")
	@NotBlank
	private String				expirationDate;

	@Pattern(regexp = "^[0-9]{3}$", message = "{default.banner.error.cvv-pattern}")
	@NotBlank
	private String				cvv;

}
