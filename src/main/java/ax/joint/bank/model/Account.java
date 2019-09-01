package ax.joint.bank.model;

public class Account {

    private final int id;

    private final String name;

    public Account(final int id, final String name) {
        this.id = id;
        this.name = name;
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }
}
