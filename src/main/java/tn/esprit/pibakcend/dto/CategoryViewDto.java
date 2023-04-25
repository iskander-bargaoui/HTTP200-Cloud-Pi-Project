package tn.esprit.pibakcend.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@AllArgsConstructor
@NoArgsConstructor
public class CategoryViewDto {
    private Integer id;

    private String categoryName;

    private  String description;

    private  String imageUrl;
}
