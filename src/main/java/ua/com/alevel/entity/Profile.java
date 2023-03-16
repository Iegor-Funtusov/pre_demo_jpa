package ua.com.alevel.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.TimeZoneStorage;
import org.hibernate.annotations.TimeZoneStorageType;
import ua.com.alevel.type.GenderType;

import java.time.OffsetDateTime;

@Getter
@Setter
@Entity
@Table(name = "profiles")
public class Profile extends BaseEntity {

    @Column(name = "birth_day")
    @TimeZoneStorage(TimeZoneStorageType.NATIVE)
    private OffsetDateTime birthDay;

    @Column(name = "gender_type")
    @Enumerated(EnumType.STRING)
    private GenderType genderType;

    @OneToOne
    private User user;

    public Profile() {
        super();
    }
}
