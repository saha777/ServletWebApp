package dbService.datasets;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table(name = "pets")
public class Pet implements Serializable{

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "pet_id", unique = true)
    private long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id", nullable = false)
    private User user;

    @Column(name = "name")
    private String name;

    @Column(name = "class")
    private String animalClass;

    @Column(name = "age")
    private int age;

    @Column(name = "date")
    private String date;

    @SuppressWarnings("UnusedDeclaration")
    public Pet(){}

    @SuppressWarnings("UnusedDeclaration")
    public Pet(User user, String name, String animalClass, int age, String date) {
        this.user = user;
        this.name = name;
        this.animalClass = animalClass;
        this.age = age;
        this.date = date;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAnimalClass() {
        return animalClass;
    }

    public void setAnimalClass(String animalClass) {
        this.animalClass = animalClass;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    @Override
    public String toString(){
        String str = "Pet{id = "+id+", name = '"+name+"', class = '"+animalClass+"', age = "+age+", userId = "+", date = "+date +"}";
        return str;
    }
}