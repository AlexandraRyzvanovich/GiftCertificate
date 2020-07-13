package com.epam.mjc.dao.entity;

import com.epam.mjc.dao.AuditListener;

import javax.persistence.*;
import java.util.Objects;

@EntityListeners(AuditListener.class)
@Entity
@Table(name = "tag")
@NamedQueries({
        @NamedQuery(name = "Tags.findAll", query = "select t from Tag t"),
        @NamedQuery(name = "Tags.findById", query = "select distinct t from Tag t where t.id = :id"),
        @NamedQuery(name = "Tags.getByName", query = "select t from Tag t where t.name = :name"),
        @NamedQuery(name = "Tags.deleteById", query = "delete from Tag where id = :id")
})
public class Tag implements Identifiable{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", insertable = false, updatable = false)
    private Long id;
    @Column(name = "name")
    private String name;

    public Tag() {
    }

    public Tag(Long id, String name) {
        this.id = id;
        this.name = name;
    }

    public Tag(String name) {
        this.name = name;
    }

    public Long getId() {
        return id;
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
        Tag tag = (Tag) o;
        return Objects.equals(id, tag.id) &&
                Objects.equals(name, tag.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name);
    }

    @Override
    public String toString() {
        return "Tag{" +
                "id=" + id +
                ", name='" + name + '\'' +
                '}';
    }
}
