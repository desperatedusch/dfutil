package de.dfutil.entities;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;

@Entity
public class Strasse implements PostalObject {

    @Id
    private Long id;

    public void setId(Long id) {
        this.id = id;
    }

    public Long getId() {
        return id;
    }
}
