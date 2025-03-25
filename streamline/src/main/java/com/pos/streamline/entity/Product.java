package com.pos.streamline.entity;

import jakarta.persistence.*;

import java.util.Date;
import java.util.Set;

@Entity
@Table(name = "Product_TBL")
public class Product {
    @Id
    @Column(name = "productId")
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    @Column(name="title")
    private String title;
    @Column(name="description")
    private String description;
    @Column(name="price")
    private double price;
    @Column(name="quantity")
    private int quantity;
    @Column(name="frontImgUrl")
    private String frontImgUrl;
    @Column(name="leftImgUrl")
    private String leftImgUrl;
    @Column(name="rightImgUrl")
    private String rightImgUrl;
    @Column(name="backImgUrl")
    private String backImgUrl;
    @Temporal(TemporalType.TIMESTAMP)
    @Column(name="createdAt")
    Date createdAt;
    @ManyToMany(mappedBy = "products")
    private Set<Order> orders;
    @ManyToOne
    @JoinColumn(name = "categoryId")
    private Category category;


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

    public Date getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(Date createdAt) {
        this.createdAt = createdAt;
    }

    public Set<Order> getOrders() {
        return orders;
    }

    public void setOrders(Set<Order> orders) {
        this.orders = orders;
    }

    public Category getCategory() {
        return category;
    }

    public void setCategory(Category category) {
        this.category = category;
    }
}
