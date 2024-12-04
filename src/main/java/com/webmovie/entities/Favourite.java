package com.webmovie.entities;

import java.util.Date;
import jakarta.persistence.*;

@Entity
@Table(name="Favourites",uniqueConstraints =  {@UniqueConstraint(columnNames = {"userId","videoId"})})

public class Favourite {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY )
    private Integer id;
    @ManyToOne
    @JoinColumn(name="userId") // Tên khóa ngoại trong table Favourite
    private User user;
    @ManyToOne
    @JoinColumn(name="videoId")// Tên khóa ngoại trong table Video
    private Video video;
    @Temporal(TemporalType.DATE)
    @Column (name="likeDate")
    private Date likeDate = new Date();

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Video getVideo() {
        return video;
    }

    public void setVideo(Video video) {
        this.video = video;
    }

    public Date getLikeDate() {
        return likeDate;
    }

    public void setLikeDate(Date likeDate) {
        this.likeDate = likeDate;
    }

    public Favourite() {
    }

    public Favourite(Integer id, User user, Video video, Date likeDate) {
        this.user = user;
        this.video = video;
        this.likeDate = likeDate;
    }

    public Favourite(User user, Video video, Date likeDate) {
        this.user = user;
        this.video = video;
        this.likeDate = likeDate;
    }
}

