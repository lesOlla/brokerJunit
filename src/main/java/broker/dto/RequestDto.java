package broker.dto;

import java.io.Serializable;
import java.util.LinkedHashMap;

import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@EqualsAndHashCode(of = { "pk", "data" })
@ToString
public class RequestDto implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	String pk;
	LinkedHashMap <String, String> data;
	String beforeData;
	HeaderDto headers;

}
