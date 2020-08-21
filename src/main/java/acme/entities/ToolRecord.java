
package acme.entities;

import javax.persistence.Entity;

import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
public class ToolRecord extends Record {

	// Serialization identifier -----------------------------------------------

	private static final long serialVersionUID = 1L;

}
