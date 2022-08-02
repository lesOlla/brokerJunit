package broker.dto;

import java.io.Serializable;
import java.util.LinkedHashMap;

import com.fasterxml.jackson.annotation.JsonInclude;

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
//@JsonInclude(Include.NON_NULL)
public class ReturnDto implements Serializable {
	private static final long serialVersionUID = 1L;
	String pk;
	LinkedHashMap <String, String> data;
	
	public ReturnDto(String pk) {
		this.pk = pk;
	}
	
	
}
