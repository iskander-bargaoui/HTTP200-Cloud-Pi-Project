package tn.esprit.pibakcend.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ProductViewDto {
    private Integer id;
    private  String name;
    private  String imageURL;
    private  double price;
    private  String description;

    private CategoryViewDto category;
}
