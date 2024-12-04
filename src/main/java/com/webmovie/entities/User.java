package com.webmovie.entities;


import jakarta.persistence.*;
import java.util.List;

@Entity
@Table(name="Users")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    @Column(name="password")
    private String password;
    @Column(name="fullname", columnDefinition = ("nvarchar(50)"))
    private String fullname;
    @Column(name="email" , columnDefinition = ("varchar(255)"))
    private String email;
    @Column(name="admin")
    private Boolean admin = false;
    @Column (name="username" , columnDefinition = ("nvarchar(50)"))
    private String username;
    @OneToMany(mappedBy = "user") // Kết nối với field user bên bên favourite
    private List<Favourite> favourites;
    @OneToMany(mappedBy = "user") // Kết nối với field user bên bên share
    private List<Share> shares;

    public User() {
    }

    public User(String password, String fullname, String email, Boolean admin, String username, List<Favourite> favourites, List<Share> shares) {
        this.password = password;
        this.fullname = fullname;
        this.email = email;
        this.admin = admin;
        this.username = username;
        this.favourites = favourites;
        this.shares = shares;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getFullname() {
        return fullname;
    }

    public void setFullname(String fullname) {
        this.fullname = fullname;
    }


    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Boolean getAdmin() {
        return admin;
    }

    public void setAdmin(Boolean admin) {
        this.admin = admin;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public List<Favourite> getFavourites() {
        return favourites;
    }

    public void setFavourites(List<Favourite> favourites) {
        this.favourites = favourites;
    }

    public List<Share> getShares() {
        return shares;
    }

    public void setShares(List<Share> shares) {
        this.shares = shares;
    }
}

