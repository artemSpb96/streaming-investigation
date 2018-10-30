package stream.processing;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
@EnableScheduling
public class KafkaDataProducerApplication {

    public static void main(String[] args) {
        SpringApplication.run(KafkaDataProducerApplication.class, args);
    }
}
