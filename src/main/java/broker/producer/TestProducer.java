/*
 * package broker.producer;
 * 
 * 
 * import java.util.function.Supplier;
 * 
 * import org.apache.kafka.clients.producer.KafkaProducer; import
 * org.apache.logging.log4j.Logger; import org.slf4j.LoggerFactory; import
 * org.springframework.beans.factory.annotation.Autowired; import
 * org.springframework.context.annotation.Bean; import
 * org.springframework.kafka.core.KafkaTemplate; import
 * org.springframework.stereotype.Component; import
 * org.springframework.stereotype.Service;
 * 
 * import broker.dto.RequestDto;
 * 
 * @Component public class TestProducer {
 * 
 * 
 * @Autowired private KafkaTemplate<String, String> kafkaTemplate;
 * 
 * public void send(String topic, String dto) { kafkaTemplate.send(topic, dto);
 * 
 * }; }
 * 
 */