package alex.winescanner;

import java.io.Serializable;

public class Wine implements Serializable{

    String ean;
    String description;
    String title;
    private String upc;
    private String brand;
    String color;
    String model;
    String size;
    String dimension;
    String weight;
    String currency;
    private double lowest_recorded_price;
    private double highest_recorded_price;
    private String[] images;
    private String source;
    private int points;
    private int ratingsCount;
    private String ratings;

    Wine() {

    }

    Wine(String description, String title, String upc, String brand, String color, double lowest_recorded_price, double highest_recorded_price, String[] images) {
        this.description = description;
        this.title = title;
        this.upc = upc;
        this.brand = brand;
        this.color = color;
        this.lowest_recorded_price = lowest_recorded_price;
        this.highest_recorded_price = highest_recorded_price;
        this.images = images;
        this.source = "";
        this.points = 0;
        this.ratingsCount = 1;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getUpc() {
        return upc;
    }

    public void setUpc(String upc) {
        this.upc = upc;
    }

    public String getBrand() {
        return brand;
    }

    public void setBrand(String brand) {
        this.brand = brand;
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }

    public double getHighest_recorded_price() {
        return highest_recorded_price;
    }

    public void setHighest_recorded_price(double highest_recorded_price) {
        this.highest_recorded_price = highest_recorded_price;
    }

    public double getLowest_recorded_price() {
        return lowest_recorded_price;
    }

    public void setLowest_recorded_price(double lowest_recorded_price) {
        this.lowest_recorded_price = lowest_recorded_price;
    }

    public String[] getImages() {
        return images;
    }

    public void setImages(String[] images) {
        this.images = images;
    }

    public String getSource() {
        return source;
    }

    public void setSource(String source) {
        this.source = source;
    }

    public int getPoints() {
        return points;
    }

    public void setPoints(int points) {
        this.points = points;
    }

    public int getRatingsCount() {
        return ratingsCount;
    }

    public void setRatingsCount(int ratingsCount) {
        this.ratingsCount = ratingsCount;
    }

    public String getRatings() {
        return String.valueOf(ratings);
    }

    public void setRatings(String ratings) {
        if(ratings == null) {
            ratings = "0";
        }
        this.ratings = "(" + ratings + ")";
    }
}
