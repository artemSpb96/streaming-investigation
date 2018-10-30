package stream.processing;

import org.apache.kafka.clients.producer.KafkaProducer;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.Properties;

@Configuration
public class KafkaConfig {

    @Bean
    public KafkaProducer<String, TestData> kafkaProducer(@Value("${kafka.host}") String host) {
        Properties props = new Properties();
        props.setProperty("value.serializer", "stream.processing.TestDataSerializer");
        props.setProperty("key.serializer", "org.apache.kafka.common.serialization.StringSerializer");
        props.setProperty("bootstrap.servers", host);
        return new KafkaProducer<>(props);
    }

    @Bean
    public KafkaBatchDataProducerJob<TestData> kafkaProducerJob(
            KafkaProducer<String, TestData> kafkaProducer,
            TestDataGenerator testDataGenerator
    ) {
        return new KafkaBatchDataProducerJob<>("test", kafkaProducer, testDataGenerator, 100);
    }
}
