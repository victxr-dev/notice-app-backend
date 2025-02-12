package com.noticias.notice.dto.notice;

public class CategoryDto {
    private String category;

    public CategoryDto() {
    }

    public CategoryDto(String category) {
        this.category = category;
    }

    public String getCategory() {
        return category;
    }

    public CategoryDto setCategory(String category) {
        this.category = category;
        return this;
    }
}
