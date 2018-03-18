package training.domain;


public class User {
    public Long id;
    public String firsName;
    public String name;

    public User(long id, String first_name, String name) {
        this.id = id;
        this.firsName = first_name;
        this.name = name;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getFirsName() {
        return firsName;
    }

    public void setFirsName(String firsName) {
        this.firsName = firsName;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
