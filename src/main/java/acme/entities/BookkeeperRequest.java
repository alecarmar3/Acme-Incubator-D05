// elreyrata 22.11.19

package acme.entities;

import javax.persistence.Entity;
import javax.persistence.Index;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.validation.Valid;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

import acme.framework.entities.DomainEntity;
import acme.framework.entities.UserAccount;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
@Table(indexes = {
	@Index(columnList = "authenticated_id")
})
public class BookkeeperRequest extends DomainEntity {

	// Serialization identifier -----------------------------------------------

	private static final long		serialVersionUID	= 1L;

	// Attributes -------------------------------------------------------------

	@NotBlank
	private String					firmName;

	@NotBlank
	private String					responsibilityStatement;

	@NotNull
	private BookkeeperRequestStatus	status;

	private boolean					approved;

	// Relationships ----------------------------------------------------------

	@NotNull
	@Valid
	@OneToOne(optional = false)
	private UserAccount				authenticated;

}
