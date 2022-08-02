package broker.consumer;

import java.util.function.Consumer;

import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import broker.dto.ReturnDto;


@Component
@Service
public class TestConsumer {
		@Bean
		public Consumer <ReturnDto> receiveSensorData(){
			return sensorData->{
				System.out.println(sensorData.toString());
			};
		}
}
