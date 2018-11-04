package stream.processing;

import org.apache.kafka.clients.producer.ProducerConfig;
import org.apache.kafka.common.serialization.StringSerializer;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.core.DefaultKafkaProducerFactory;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.core.ProducerFactory;

import java.util.HashMap;
import java.util.Map;

@Configuration
public class KafkaConfig {

    @Bean
    public KafkaTemplate<String, TestData> kafkaTemplate(@Value("${kafka.host}") String host) {
        return new KafkaTemplate<>(producerFactory(host));
    }

    private ProducerFactory<String, TestData> producerFactory(String host) {
        return new DefaultKafkaProducerFactory<>(producerConfigs(host));
    }

    private Map<String, Object> producerConfigs(String host) {
        Map<String, Object> props = new HashMap<>();
        props.put(ProducerConfig.BOOTSTRAP_SERVERS_CONFIG, host);
        props.put(ProducerConfig.KEY_SERIALIZER_CLASS_CONFIG, StringSerializer.class);
        props.put(ProducerConfig.VALUE_SERIALIZER_CLASS_CONFIG, TestDataSerializer.class);
        return props;
    }

    @Bean
    public KafkaBatchDataProducerJob<TestData> kafkaProducerJob(
            KafkaTemplate<String, TestData> kafkaTemplate,
            TestDataGenerator testDataGenerator
    ) {
        return new KafkaBatchDataProducerJob<>("test", kafkaTemplate, testDataGenerator, 100);
    }
}
