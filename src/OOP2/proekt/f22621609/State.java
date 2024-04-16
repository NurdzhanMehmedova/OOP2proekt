package OOP2.proekt.f22621609;

import java.io.Serializable;

public class State implements Serializable {
    private String name;
    private String id;

    public State(String name, String id) {
        this.name = name;
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public String getId() {
        return id;
    }

    @Override
    public String toString() {
        return name;
    }
}
