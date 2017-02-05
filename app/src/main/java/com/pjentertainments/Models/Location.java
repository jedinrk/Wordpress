package com.pjentertainments.Models;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

/**
 * Created by Naveen on 2/5/2017.
 */

public class Location {

    @SerializedName("id")
    @Expose
    private Integer id;
    @SerializedName("count")
    @Expose
    private Integer count;
    @SerializedName("description")
    @Expose
    private String description;
    @SerializedName("link")
    @Expose
    private String link;
    @SerializedName("name")
    @Expose
    private String name;
    @SerializedName("slug")
    @Expose
    private String slug;
    @SerializedName("taxonomy")
    @Expose
    private String taxonomy;
    @SerializedName("parent")
    @Expose
    private Integer parent;
    @SerializedName("meta")
    @Expose
    private List<Object> meta = null;
    /*@SerializedName("theaters_here")
    @Expose
    private List<TheatreList> theatersHere = null;*/
    @SerializedName("_links")
    @Expose
    private Post.Link links;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getCount() {
        return count;
    }

    public void setCount(Integer count) {
        this.count = count;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getLink() {
        return link;
    }

    public void setLink(String link) {
        this.link = link;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSlug() {
        return slug;
    }

    public void setSlug(String slug) {
        this.slug = slug;
    }

    public String getTaxonomy() {
        return taxonomy;
    }

    public void setTaxonomy(String taxonomy) {
        this.taxonomy = taxonomy;
    }

    public Integer getParent() {
        return parent;
    }

    public void setParent(Integer parent) {
        this.parent = parent;
    }

    public List<Object> getMeta() {
        return meta;
    }

    public void setMeta(List<Object> meta) {
        this.meta = meta;
    }

    /*public List<TheatreList> getTheatersHere() {
        return theatersHere;
    }

    public void setTheatersHere(List<TheatreList> theatersHere) {
        this.theatersHere = theatersHere;
    }*/

    public Post.Link getLinks() {
        return links;
    }

    public void setLinks(Post.Link links) {
        this.links = links;
    }
}
