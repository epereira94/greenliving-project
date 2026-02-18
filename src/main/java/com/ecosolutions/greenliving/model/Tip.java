package com.ecosolutions.greenliving.model;

public class Tip {
    private int tipId;
    private String title;
    private String category;
    private String content;
    private boolean active;

    public Tip() {}

    public Tip(int tipId, String title, String category, String content, boolean active) {
        this.tipId = tipId;
        this.title = title;
        this.category = category;
        this.content = content;
        this.active = active;
    }

    public int getTipId() {
        return tipId;
    }

    public void setTipId(int tipId) {
        this.tipId = tipId;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public boolean isActive() {
        return active;
    }

    public void setActive(boolean active) {
        this.active = active;
    }
}
