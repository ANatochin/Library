import java.util.concurrent.atomic.AtomicInteger;

public class Reader {
    static final AtomicInteger rId = new AtomicInteger();
    private final int readerId;
    private final String name;

    public Reader (String name) {
        this.name = name;
        this.readerId = rId.incrementAndGet();
    }

    public int getId() {
        return this.readerId;
    }

    @Override
    public String toString() {
        return this.name + "/Reader ID-" + this.getId() + "/";
    }
}
