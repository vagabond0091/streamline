package com.pos.streamline.data;

public class ProductData {
    private Long id;
    private String title;
    private String description;
    private double price;
    private int quantity;
    private String frontImgUrl;
    private String leftImgUrl;
    private String rightImgUrl;
    private String backImgUrl;
    public ProductData(Long id, String name, String description, double price, int quantity, String frontImgUrl,String leftImgUrl,String rightImgUrl,String backImgUrl) {
        this.id = id;
        this.title = name;
        this.description = description;
        this.price = price;
        this.quantity = quantity;
        this.frontImgUrl  = frontImgUrl;
        this.leftImgUrl = leftImgUrl;
        this.rightImgUrl = rightImgUrl;
        this.backImgUrl = backImgUrl;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public String getFrontImgUrl() {
        return frontImgUrl;
    }

    public void setFrontImgUrl(String frontImgUrl) {
        this.frontImgUrl = frontImgUrl;
    }

    public String getLeftImgUrl() {
        return leftImgUrl;
    }

    public void setLeftImgUrl(String leftImgUrl) {
        this.leftImgUrl = leftImgUrl;
    }

    public String getRightImgUrl() {
        return rightImgUrl;
    }

    public void setRightImgUrl(String rightImgUrl) {
        this.rightImgUrl = rightImgUrl;
    }

    public String getBackImgUrl() {
        return backImgUrl;
    }

    public void setBackImgUrl(String backImgUrl) {
        this.backImgUrl = backImgUrl;
    }
}
