package com.pjentertainments.Models;

/**
 * Created by Naveen on 2/4/2017.
 */

public class Media_Details {

    private int width;
    private int height;
    private String file;
    private Media.Media_Details_Sizes sizes;

    public int getWidth() {
        return width;
    }

    public void setWidth(int width) {
        this.width = width;
    }

    public int getHeight() {
        return height;
    }

    public void setHeight(int height) {
        this.height = height;
    }

    public String getFile() {
        return file;
    }

    public void setFile(String file) {
        this.file = file;
    }

    public Media.Media_Details_Sizes getSizes() {
        return sizes;
    }

    public void setSizes(Media.Media_Details_Sizes sizes) {
        this.sizes = sizes;
    }
}

