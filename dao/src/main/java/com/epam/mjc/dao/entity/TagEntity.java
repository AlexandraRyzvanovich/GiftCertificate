package com.epam.mjc.dao.entity;

import com.epam.mjc.dao.AuditListener;

import javax.persistence.*;
import java.util.Objects;

@EntityListeners(AuditListener.class)
@Entity
@Table(name = "tag")
@NamedQueries({
        @NamedQuery(name = "Tags.findAll", query = "select t from TagEntity t"),
        @NamedQuery(name = "Tags.findById", query = "select distinct t from TagEntity t where t.id = :id"),
        @NamedQuery(name = "Tags.getByName", query = "select t from TagEntity t where t.name = :name"),
        @NamedQuery(name = "Tags.deleteById", query = "delete from TagEntity where id = :id")
})
public class TagEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", insertable = false, updatable = false)
    private Long id;
    @Column(name = "name")
    private String name;

    public TagEntity() {
    }

    public TagEntity(Long id, String name) {
        this.id = id;
        this.name = name;
    }

    public TagEntity(String name) {
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

    public void setId(Long id) {
        this.id = id;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        TagEntity tagEntity = (TagEntity) o;
        return Objects.equals(id, tagEntity.id) &&
                Objects.equals(name, tagEntity.name);
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
