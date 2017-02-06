package com.pjentertainments.Models;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

/**
 * Created by Naveen on 2/6/2017.
 */

public class Movie {

    @SerializedName("id")
    @Expose
    private Integer id;
    @SerializedName("date")
    @Expose
    private String date;
    @SerializedName("date_gmt")
    @Expose
    private String dateGmt;
    /*@SerializedName("guid")
    @Expose
    private Guid guid;*/
    @SerializedName("modified")
    @Expose
    private String modified;
    @SerializedName("modified_gmt")
    @Expose
    private String modifiedGmt;
    @SerializedName("slug")
    @Expose
    private String slug;
    @SerializedName("status")
    @Expose
    private List<Integer> status = null;
    @SerializedName("type")
    @Expose
    private String type;
    @SerializedName("link")
    @Expose
    private String link;
    @SerializedName("title")
    @Expose
    private Title title;
    /*@SerializedName("content")
    @Expose
    private Content content;*/
    @SerializedName("featured_media")
    @Expose
    private Integer featuredMedia;
    @SerializedName("template")
    @Expose
    private String template;
    @SerializedName("actor")
    @Expose
    private List<Integer> actor = null;
    @SerializedName("genre")
    @Expose
    private List<Integer> genre = null;
    @SerializedName("language")
    @Expose
    private List<Integer> language = null;
    @SerializedName("location")
    @Expose
    private List<Integer> location = null;
    @SerializedName("theater")
    @Expose
    private List<Integer> theater = null;
    @SerializedName("_links")
    @Expose
    private Post.Link links;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getDateGmt() {
        return dateGmt;
    }

    public void setDateGmt(String dateGmt) {
        this.dateGmt = dateGmt;
    }

    /*public Guid getGuid() {
        return guid;
    }

    public void setGuid(Guid guid) {
        this.guid = guid;
    }*/

    public String getModified() {
        return modified;
    }

    public void setModified(String modified) {
        this.modified = modified;
    }

    public String getModifiedGmt() {
        return modifiedGmt;
    }

    public void setModifiedGmt(String modifiedGmt) {
        this.modifiedGmt = modifiedGmt;
    }

    public String getSlug() {
        return slug;
    }

    public void setSlug(String slug) {
        this.slug = slug;
    }

    public List<Integer> getStatus() {
        return status;
    }

    public void setStatus(List<Integer> status) {
        this.status = status;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getLink() {
        return link;
    }

    public void setLink(String link) {
        this.link = link;
    }

    public Title getTitle() {
        return title;
    }

    public void setTitle(Title title) {
        this.title = title;
    }

    /*public Content getContent() {
        return content;
    }

    public void setContent(Content content) {
        this.content = content;
    }*/

    public Integer getFeaturedMedia() {
        return featuredMedia;
    }

    public void setFeaturedMedia(Integer featuredMedia) {
        this.featuredMedia = featuredMedia;
    }

    public String getTemplate() {
        return template;
    }

    public void setTemplate(String template) {
        this.template = template;
    }

    public List<Integer> getActor() {
        return actor;
    }

    public void setActor(List<Integer> actor) {
        this.actor = actor;
    }

    public List<Integer> getGenre() {
        return genre;
    }

    public void setGenre(List<Integer> genre) {
        this.genre = genre;
    }

    public List<Integer> getLanguage() {
        return language;
    }

    public void setLanguage(List<Integer> language) {
        this.language = language;
    }

    public List<Integer> getLocation() {
        return location;
    }

    public void setLocation(List<Integer> location) {
        this.location = location;
    }

    public List<Integer> getTheater() {
        return theater;
    }

    public void setTheater(List<Integer> theater) {
        this.theater = theater;
    }

    public Post.Link getLinks() {
        return links;
    }

    public void setLinks(Post.Link links) {
        this.links = links;
    }

    public class Title {

        @SerializedName("rendered")
        @Expose
        private String rendered;

        public String getRendered() {
            return rendered;
        }

        public void setRendered(String rendered) {
            this.rendered = rendered;
        }

    }
}
