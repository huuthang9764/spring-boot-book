package com.springboot.music.payload;

import com.springboot.music.entity.Category;
import lombok.*;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class BookDTO {
    private String title;
    private String image;
    private String description;
    private Category category;
}
