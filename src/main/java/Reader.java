import java.util.Objects;
import java.util.concurrent.atomic.AtomicInteger;

public class Reader {
    static final AtomicInteger rId = new AtomicInteger();
    private final int id;
    private final String name;

    public Reader (String name) {
        this.name = name;
        this.id = rId.incrementAndGet();
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    @Override
    public String toString() {
        return name + "/Reader ID-" + this.getId() + "/";
    }

    @Override
    public boolean equals(Object o) {
        if(o == null){
            return false;
        }
        if(o == this){
            return true;
        }
        if(!(o instanceof Reader)){
            return false;
        }
        Reader compared = (Reader) o;

        return compared.getId() == this.getId() && compared.getName().equals(this.getName());
    }


    @Override
    public int hashCode() {
        return Objects.hash(id, name);
    }
}
