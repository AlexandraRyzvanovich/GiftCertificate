package com.epam.mjc.dao.dto;

import com.epam.mjc.dao.entity.RoleEntity;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.Objects;

public class UserDto {
    private Long id;
    @Email
    @NotNull(message = "Email should not be empty.")
    private String email;
    @NotNull(message = "Password should not be empty.")
    private String password;
    private RoleDto roleEntity;

    @Size(max = 200)
    private String name;

    @Size(max = 200)
    private String surname;

    private RoleEntity userRoleEntity;

    public UserDto() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public RoleEntity getUserRoleEntity() {
        return userRoleEntity;
    }

    public void setUserRoleEntity(RoleEntity userRoleEntity) {
        this.userRoleEntity = userRoleEntity;
    }

    public RoleDto getRoleEntity() {
        return roleEntity;
    }

    public void setRoleEntity(RoleDto roleEntity) {
        this.roleEntity = roleEntity;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        UserDto userDto = (UserDto) o;
        return Objects.equals(id, userDto.id) &&
                Objects.equals(email, userDto.email) &&
                Objects.equals(password, userDto.password) &&
                Objects.equals(roleEntity, userDto.roleEntity) &&
                Objects.equals(name, userDto.name) &&
                Objects.equals(surname, userDto.surname) &&
                Objects.equals(userRoleEntity, userDto.userRoleEntity);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, email, password, roleEntity, name, surname, userRoleEntity);
    }

    @Override
    public String toString() {
        return "UserDto{" +
                "id=" + id +
                ", email='" + email + '\'' +
                ", password='" + password + '\'' +
                ", roleEntity=" + roleEntity +
                ", name='" + name + '\'' +
                ", surname='" + surname + '\'' +
                ", userRoleEntity=" + userRoleEntity +
                '}';
    }
}
