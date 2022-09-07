public class Reader {
    private int id;
    private String name;

    public Reader(int id, String name){
        this.id = id;
        this.name = name;
    }
    public int getId(){
        return this.id;
    }

    public String getName() {
        return name;
    }

    @Override
    public String toString(){
        return this.getName() + "/Reader ID-" + this.getId() + "/";
    }
}
