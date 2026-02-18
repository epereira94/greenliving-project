package com.ecosolutions.greenliving.model;

import java.math.BigDecimal;

public class Product {
    private int productId;
    private String name;
    private String category;
    private String description;
    private int ecoScore;
    private BigDecimal price;
    private String productUrl;
    private String imageUrl;

    public Product() {}

    public int getProductId() { return productId; }
    public void setProductId(int productId) { this.productId = productId; }

    public String getName() { return name; }
    public void setName(String name) { this.name = name; }

    public String getCategory() { return category; }
    public void setCategory(String category) { this.category = category; }

    public String getDescription() { return description; }
    public void setDescription(String description) { this.description = description; }

    public int getEcoScore() { return ecoScore; }
    public void setEcoScore(int ecoScore) { this.ecoScore = ecoScore; }

    public BigDecimal getPrice() { return price; }
    public void setPrice(BigDecimal price) { this.price = price; }

    public String getProductUrl() { return productUrl; }
    public void setProductUrl(String productUrl) { this.productUrl = productUrl; }

    public String getImageUrl() { return imageUrl; }
    public void setImageUrl(String imageUrl) { this.imageUrl = imageUrl; }
}
