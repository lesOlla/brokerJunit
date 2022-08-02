package broker;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;

import java.sql.Timestamp;
import java.util.LinkedHashMap;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.WebApplicationType;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.cloud.stream.binder.test.OutputDestination;
import org.springframework.cloud.stream.binder.test.TestChannelBinderConfiguration;
import org.springframework.cloud.stream.function.StreamBridge;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.kafka.test.context.EmbeddedKafka;
import org.springframework.test.annotation.DirtiesContext;

import com.fasterxml.jackson.databind.ObjectMapper;

import broker.consumer.TestConsumer;
import broker.dto.HeaderDto;
import broker.dto.RequestDto;
import broker.service.HapoalimBrokerService;

@SpringBootTest
@DirtiesContext
@EmbeddedKafka(partitions = 1, brokerProperties = { "listeners=PLAINTEXT://localhost:9092", "port=9092" })
class TestHandler { 
	@Autowired
	HapoalimBrokerService hapoalimService;
	@Autowired
	StreamBridge streamBridge;
	@Autowired
	TestConsumer consumer;
	/*
	 * @Autowired TestProducer producer;
	 */ObjectMapper mapper = new ObjectMapper();
    @Value("${test.topic}")
    private String topic;

	@Test
	void testHandleRequestDto()	throws Exception {
		ConfigurableApplicationContext context = new
				SpringApplicationBuilder(TestChannelBinderConfiguration
				 .getCompleteConfiguration()) .web(WebApplicationType.NONE)
				 .run("--spring.cloud.stream.function.definition=handleRequestDto",
				 "--spring.jmx.enabled=false");
		
		LinkedHashMap<String, String> temp = new LinkedHashMap<>();
		temp.put("a", "foo");
		temp.put("b", "bar");
		temp.put("c", "123");
		RequestDto sample1 = new RequestDto("123456", temp, null, new HeaderDto("Insert",
				Timestamp.valueOf("2021-01-27 13:42:13.383"), "123154689132138433181312132194984313218"));
		RequestDto sample2 = new RequestDto("123457", temp, "2020-01-27 13:42:13.383", new HeaderDto("update",
				Timestamp.valueOf("2021-01-27 13:42:13.383"), "123154689132138433181312132194984313218"));
		RequestDto sample3 = new RequestDto("123458", temp, null, new HeaderDto("Delete",
				Timestamp.valueOf("2021-01-27 13:42:13.383"), "123154689132138433181312132194984313218"));
		String jsonPayload = "{\r\n" + "  \"pk\": \"123456\",\r\n"
				+ "  \"data\": {\"a\":  \"foo\", \"b\":  \"bar\", \"c\": 123},\r\n" + "  \"beforeData\": null,\r\n"
				+ "  \"headers\": {\r\n" + "    \"operation\": \"INSERT\",\r\n"
				+ "    \"timestamp\": \"2021-01-27T13:42:13.383\",\r\n"
				+ "    \"streamPosition\": \"123154689132138433181312132194984313218\"\r\n" + "  }}";
	
	
	        String data1 = "{\"pk\": \"123456\"+  \"data\": {\"a\":  \"foo\", \"b\":  \"bar\", \"c\": 123}";
	        String data3 = "{\"pk\": \"123458\"}";
		      
	      //  streamBridge.send(topic, data);
	        String toSend1=mapper.writeValueAsString(sample1);
	       // producer.send(topic, toSend1);
	        hapoalimService.handleRequestDto().accept(sample1);
	        assertThat(mapper.writeValueAsString(consumer.receiveSensorData()).equals(data1));
	       hapoalimService.handleRequestDto().accept(sample3);
	        System.out.println(consumer.receiveSensorData().toString());
	        assertThat(consumer.receiveSensorData().equals(data3));
}
	}