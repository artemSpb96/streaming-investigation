package stream.processing;

import java.time.ZonedDateTime;

public class TestData {
    private final String name;
    private final TestType type;
    private final ZonedDateTime date;

    public TestData(String name, TestType type, ZonedDateTime date) {
        this.name = name;
        this.type = type;
        this.date = date;
    }

    public String getName() {
        return name;
    }

    public TestType getType() {
        return type;
    }

    public ZonedDateTime getDate() {
        return date;
    }
}
