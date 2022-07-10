package pattern.builder.people;

import java.util.Arrays;
import java.util.List;

public class People {
    private Integer id;
    private String name;
    private String sex;
    private String race;
    private boolean isAdult;
    private int age;
    private List<String> address;

    public People(Integer id, String name, String sex, String race, boolean isAdult, int age, List<String> address) {
        this.id = id;
        this.name = name;
        this.sex = sex;
        this.race = race;
        this.isAdult = isAdult;
        this.age = age;
        this.address = address;
    }

    protected People(Integer id, String name, String sex, String race, boolean isAdult, int age, String... address) {
        this(id, name, sex, race, isAdult, age, Arrays.asList(address));
    }

    public static PeopleBuilder builder() {
        return new PeopleBuilder();
    }

    public Integer getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getSex() {
        return sex;
    }

    public String getRace() {
        return race;
    }

    public boolean isAdult() {
        return isAdult;
    }

    public int getAge() {
        return age;
    }

    public List<String> getAddress() {
        return address;
    }

    @Override
    public String toString() {
        return "People{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", sex='" + sex + '\'' +
                ", race='" + race + '\'' +
                ", isAdult=" + isAdult +
                ", age=" + age +
                ", address=" + address +
                '}';
    }
}
