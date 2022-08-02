package broker.service;

import java.util.function.Consumer;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.stream.function.StreamBridge;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import broker.dto.RequestDto;
import broker.dto.ReturnDto;

@Service
public class HapoalimBrokerService {

	ObjectMapper mapper = new ObjectMapper();
	@Autowired
	StreamBridge streamBridge;
	
	 @Value("${test.topic}")
	    private String topic;

	@Bean
	public Consumer<RequestDto> handleRequestDto() {
		// TODO separate ENUM list for sep operations, map - keyword, value - special
		// TODO Exception handlers
		return message -> {
			ReturnDto temp = new ReturnDto(null, null);
			if (message.getHeaders().getOperation().equalsIgnoreCase("Delete")) {
				temp = new ReturnDto(message.getPk(), null);
			}
			if (message.getHeaders().getOperation().equalsIgnoreCase("Insert")
					|| message.getHeaders().getOperation().equalsIgnoreCase("Update")) {
				temp = new ReturnDto(message.getPk(), message.getData());
			}
			try {
				sendToKafka(temp);
			} catch (JsonProcessingException e) {
				e.printStackTrace();
			}
			return;
		};
	}

	private void sendToKafka(ReturnDto temp) throws JsonProcessingException {
		//String topic="sendSensorData-out-0";
		String mess = mapper.writeValueAsString(temp);
		streamBridge.send(this.topic, mess);
	}
	
	
}
