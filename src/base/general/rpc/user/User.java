package base.general.rpc.user;

import java.io.Serializable;

public class User implements Serializable {
    // @SuppressWarnings("unused")
    // private static final long serialVersioUID = 1L;

    private Integer id;
    private String name;

    public User(Integer id, String name) {
        this.id = id;
        this.name = name;
    }

    public Integer getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return "user: { id=" + id + ", name=" + name + "}";
    }
}
