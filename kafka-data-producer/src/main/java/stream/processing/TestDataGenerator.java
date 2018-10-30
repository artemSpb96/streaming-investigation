package stream.processing;

import org.springframework.stereotype.Component;

import java.time.ZonedDateTime;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Random;
import java.util.concurrent.atomic.AtomicInteger;

@Component
public class TestDataGenerator implements DataGenerator<TestData> {
    private final AtomicInteger testDataCounter = new AtomicInteger();
    private final List<TestType> testTypes;
    private final Random random = new Random();

    public TestDataGenerator() {
        testTypes = new ArrayList<>();
        testTypes.add(null);
        testTypes.addAll(Arrays.asList(TestType.values()));
    }

    @Override
    public TestData generate() {
        return new TestData(
                "test_date_" + testDataCounter.incrementAndGet(),
                testTypes.get(random.nextInt(testTypes.size())),
                random.nextInt() % 100 == 0 ? ZonedDateTime.now() : null
        );
    }
}
