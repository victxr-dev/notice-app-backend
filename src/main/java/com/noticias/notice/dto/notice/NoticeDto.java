package com.noticias.notice.dto.notice;

import jakarta.validation.constraints.NotBlank;

public class NoticeDto {
    @NotBlank(message = "title is required")
    private String title;
    private String image;
    private String description;
    @NotBlank(message = "title is required")
    @NotBlank(message = "category is required")
    private String category;

    public @NotBlank(message = "title is required") String getTitle() {
        return title;
    }

    public NoticeDto setTitle(@NotBlank(message = "title is required") String title) {
        this.title = title;
        return this;
    }

    public String getImage() {
        return image;
    }

    public NoticeDto setImage(String image) {
        this.image = image;
        return this;
    }

    public String getDescription() {
        return description;
    }

    public NoticeDto setDescription(String description) {
        this.description = description;
        return this;
    }

    public @NotBlank(message = "title is required") @NotBlank(message = "category is required") String getCategory() {
        return category;
    }

    public NoticeDto setCategory(@NotBlank(message = "title is required") @NotBlank(message = "category is required") String category) {
        this.category = category;
        return this;
    }
}
