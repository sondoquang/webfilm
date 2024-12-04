package com.webmovie.entities;
import jakarta.persistence.*;
import java.util.Date;
import java.util.List;


@Entity
@Table(name = "Videos")
public class Video {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    @Column(name = "title",columnDefinition = "nvarchar(255)")
    private String title;
    @Column(name = "poster")
    private String poster;
    @Column (name ="ImagePoster")
    private String imagePoster;
    @Column(name = "views")
    private Integer views;
    @Column(name = "description" ,columnDefinition = "nvarchar(max)")
    private String description;
    @Column(name = "active")
    private Boolean active;
    @Column(name = "linkVideo")
    private String linkVideo;
    @Temporal(TemporalType.DATE)
    @Column( name = "datePost")
    private Date postDate;
    @OneToMany(mappedBy = "video") // Mapping với field video bên table favourite
    private List<Favourite> favourites;
    @OneToMany(mappedBy = "video") // Mapping với field video bên table favourite
    private List<Share> shares;

    public Video() {
    }

    public Video(String title, String poster, String imagePoster, Integer views, String description, Boolean active, String linkVideo, Date postDate, List<Favourite> favourites, List<Share> shares) {
        this.title = title;
        this.poster = poster;
        this.imagePoster = imagePoster;
        this.views = views;
        this.description = description;
        this.active = active;
        this.linkVideo = linkVideo;
        this.postDate = postDate;
        this.favourites = favourites;
        this.shares = shares;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getPoster() {
        return poster;
    }

    public void setPoster(String poster) {
        this.poster = poster;
    }

    public String getImagePoster() {
        return imagePoster;
    }

    public void setImagePoster(String imagePoster) {
        this.imagePoster = imagePoster;
    }

    public Integer getViews() {
        return views;
    }

    public void setViews(Integer views) {
        this.views = views;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Boolean getActive() {
        return active;
    }

    public void setActive(Boolean active) {
        this.active = active;
    }

    public String getLinkVideo() {
        return linkVideo;
    }

    public void setLinkVideo(String linkVideo) {
        this.linkVideo = linkVideo;
    }

    public Date getPostDate() {
        return postDate;
    }

    public void setPostDate(Date postDate) {
        this.postDate = postDate;
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
