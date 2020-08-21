
package acme.entities;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.Transient;
import javax.validation.constraints.Digits;
import javax.validation.constraints.NotBlank;

import org.hibernate.validator.constraints.Range;

import acme.framework.entities.DomainEntity;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
public class Configuration extends DomainEntity {

	// Serialization identifier -----------------------------------------------

	private static final long	serialVersionUID	= 1L;

	// Attributes --------------------------------------------------------------

	@NotBlank
	private String				spamWords;

	@Range(min = 0, max = 1)
	@Digits(integer = 1, fraction = 3)
	private Double				spamThreshold;

	@NotBlank
	private String				activitySectors;


	@Transient
	public List<String> activitySectorsToList() {
		List<String> activitySectorsToList = new ArrayList<String>();

		activitySectorsToList = Arrays.asList(this.activitySectors.split(", "));

		return activitySectorsToList;
	}

}
