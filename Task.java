import java.util.ArrayList;
import java.util.List;

public class Task {
    private String name;
    private int hoursRequired;

    public Task(String name, int hoursRequired) {
        this.name = name;
        this.hoursRequired = hoursRequired;
    }

    public String getName() {
        return name;
    }

    public int getHoursRequired() {
        return hoursRequired;
    }
}

