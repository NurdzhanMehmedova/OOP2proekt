package OOP2.proekt.f22621609.avtomat;


public class State {
    private String id;
    private String name;

    public State(String id, String name) {
        this.id = id;
        this.name = name;
    }

    public String getId() {
        return id;
    }

    public String getName() {
        return name;
    }
}