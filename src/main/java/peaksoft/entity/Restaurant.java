package peaksoft.entity;

import jakarta.persistence.*;
import lombok.*;

import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@Entity
@Table(name = "restaurants")
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode
public class Restaurant {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "restaurant_seq")
    @SequenceGenerator(name = "restaurant_seq", allocationSize = 1)

    private Long id;
    private String name;
    private String location;
    private String restType;
    private int numberOfEmployees;
    private int service;

    @OneToMany(mappedBy = "restaurant", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<User> users = new ArrayList<>();

    @OneToMany(mappedBy = "restaurant", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<MenuItem> menuItems = new ArrayList<>();

    public void addMenuItem(MenuItem menuItem) {
        if (this.menuItems == null) {
            menuItems = new ArrayList<>();
        }
        menuItems.add(menuItem);
    }

    public void addEmployees(User user) {
        if (this.users == null) {
            users = new ArrayList<>();
        }
        users.add(user);

    }
}