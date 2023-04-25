package tn.esprit.pibakcend.mapper;

import org.mapstruct.Builder;
import org.mapstruct.InjectionStrategy;
import org.mapstruct.Mapper;

import tn.esprit.pibakcend.dto.ProductViewDto;

import tn.esprit.pibakcend.entities.Product;

import java.util.List;


@Mapper(componentModel = "spring",
      builder = @Builder(disableBuilder = true),
            injectionStrategy = InjectionStrategy.CONSTRUCTOR)
public interface ProductMapper {

    ProductViewDto toDto(Product source);

    List<ProductViewDto> toDto(List<Product> source);

}
