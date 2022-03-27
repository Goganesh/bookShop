package com.goganesh.bookshop.dto;

import com.goganesh.bookshop.domain.Tag;
import lombok.Data;

@Data
public class TagPageDto {
    private final String slug;
    private final String name;

    public TagPageDto(Tag tag) {
        this.slug = tag.getSlug();
        this.name = tag.getName();
    }
}
