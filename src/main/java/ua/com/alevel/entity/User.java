package ua.com.alevel.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name = "users")
public class User extends BaseEntity {

    private String firstName;
    private String lastName;

    @Column(nullable = false, unique = true)
    private String email;

    public User() {
        super();
    }
}
