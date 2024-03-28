package dev.movies.entities;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data @AllArgsConstructor@NoArgsConstructor
public class RoleModel {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    private String rolename;
}
