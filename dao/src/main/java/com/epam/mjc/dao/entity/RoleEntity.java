package com.epam.mjc.dao.entity;

import com.epam.mjc.dao.AuditListener;
import org.springframework.security.core.GrantedAuthority;

import javax.persistence.*;
import java.util.Objects;

@EntityListeners(AuditListener.class)
@Entity
@Table(name = "role")
@NamedQueries({
        @NamedQuery(name = "Roles.findByName", query = "select r from RoleEntity r where r.name = :name")
})
public class RoleEntity implements GrantedAuthority {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", insertable = false, updatable = false)
    private Long id;
    @Column(name = "name")
    private String name;

    public RoleEntity() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        RoleEntity roleEntity = (RoleEntity) o;
        return Objects.equals(id, roleEntity.id) &&
                Objects.equals(name, roleEntity.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name);
    }

    @Override
    public String toString() {
        return "Role{" +
                "id=" + id +
                ", name='" + name + '\'' +
                '}';
    }

    @Override
    public String getAuthority() {
        return getName();
    }
}
