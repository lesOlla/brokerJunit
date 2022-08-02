package broker.dto;

import java.io.Serializable;
import java.sql.Timestamp;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@EqualsAndHashCode()
@ToString
public class HeaderDto  implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	String operation;
	Timestamp timestamp;
	String streamPosition;

}
