package ua.com.alevel.entity;

import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.MappedSuperclass;
import jakarta.persistence.PreUpdate;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.TimeZoneStorage;
import org.hibernate.annotations.TimeZoneStorageType;

import java.time.OffsetDateTime;

@Getter
@Setter
@MappedSuperclass
public abstract class BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @TimeZoneStorage(TimeZoneStorageType.NATIVE)
    private OffsetDateTime created;

    @TimeZoneStorage(TimeZoneStorageType.NATIVE)
    private OffsetDateTime updated;

    public BaseEntity() {
        this.created = OffsetDateTime.now();
        this.updated = OffsetDateTime.now();
    }

    @PreUpdate
    public void onPreUpdate() {
        this.updated = OffsetDateTime.now();
    }
}
