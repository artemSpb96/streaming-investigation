package stream.processing;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.scheduling.annotation.Scheduled;

public class KafkaBatchDataProducerJob<T> {
    private static final Logger log = LoggerFactory.getLogger(KafkaBatchDataProducerJob.class);
    private static final long JOB_DELAY_MILLIS = 100;

    private final String topic;
    private final KafkaTemplate<String, T> producer;
    private final DataGenerator<T> generator;
    private final long batchSize;

    public KafkaBatchDataProducerJob(
            String topic,
            KafkaTemplate<String, T> producer,
            DataGenerator<T> generator,
            long batchSize
    ) {
        this.topic = topic;
        this.producer = producer;
        this.generator = generator;
        this.batchSize = batchSize;
    }

    @Scheduled(fixedDelay = JOB_DELAY_MILLIS)
    public void produce() {
        log.debug("Batch start");
        for (int i = 0; i < batchSize; i++) {
            producer.send(topic, generator.generate());
        }
        log.debug("Batch end");
    }
}
