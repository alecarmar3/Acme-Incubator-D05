
package acme.entities.roles;

import javax.persistence.Entity;
import javax.validation.constraints.NotBlank;

import org.hibernate.validator.constraints.Length;

import acme.framework.entities.UserRole;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
public class Entrepreneur extends UserRole {

	// Serialisation identifier -----------------------------------------------

	private static final long	serialVersionUID	= 1L;

	// Attributes -------------------------------------------------------------

	@NotBlank
	@Length(max = 250)
	private String				startUpName;

	@NotBlank
	private String				activitySector;

	@NotBlank
	@Length(max = 250)
	private String				qualificationRecord;

	@NotBlank
	@Length(max = 250)
	private String				skillsRecord;

}
