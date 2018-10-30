package stream.processing;

import org.apache.kafka.clients.producer.Producer;
import org.apache.kafka.clients.producer.ProducerRecord;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.scheduling.annotation.Scheduled;

public class KafkaBatchDataProducerJob<T> {
    private static final Logger log = LoggerFactory.getLogger(KafkaBatchDataProducerJob.class);
    private static final long JOB_DELAY_MILLIS = 100;

    private final String topic;
    private final Producer<String, T> producer;
    private final DataGenerator<T> generator;
    private final long batchSize;

    public KafkaBatchDataProducerJob(
            String topic,
            Producer<String, T> producer,
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
            producer.send(new ProducerRecord<>(topic, generator.generate()), (metadata, exception) -> {
                log.debug("Metadata: {}. Exception: {}", metadata, exception);
            });
        }
        log.debug("Batch end");
    }
}
