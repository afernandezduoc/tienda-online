package com.tiendaonline.demo.model;

import javax.persistence.*;

@Entity
@Table(name = "users")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "user_id")
    private Integer id;

    @Column(name = "name", nullable = false, length = 200)
    private String name;

    @Column(name = "email", nullable = false, unique = true, length = 200)
    private String email;

    @Enumerated(EnumType.STRING) 
    @Column(nullable = false)
    private Role role;

    @ManyToOne
    @JoinColumn(name = "address_id", nullable = false)
    private Address address;

    // Constructor sin parámetros
    public User() {
    }

    // Constructor
    public User(Integer id, String name, String email, Role role, Address address) {
        this.id = id;
        this.name = name;
        this.email = email;
        this.role = role;
        this.address = address;
    }
    
    // Getter and Setters
    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Role getRole() {
        return role;
    }

    public void setRole(Role role) {
        this.role = role;
    }

    public Address getAddress() {
        return address;
    }

    public void setAddress(Address address) {
        this.address = address;
    }
    
}
