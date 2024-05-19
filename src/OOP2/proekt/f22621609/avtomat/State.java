package OOP2.proekt.f22621609.avtomat;

/**
 * The State class represents a state in a finite automaton, characterized by an ID and a name.
 */
public class State {
    private String id;
    private String name;

    /**
     * Constructs a State with the specified ID and name.
     *
     * @param id   the unique identifier of the state
     * @param name the name of the state
     */
    public State(String id, String name) {
        this.id = id;
        this.name = name;
    }

    /**
     * Returns the ID of the state.
     *
     * @return the ID of the state
     */
    public String getId() {
        return id;
    }

    /**
     * Returns the name of the state.
     *
     * @return the name of the state
     */
    public String getName() {
        return name;
    }
}
