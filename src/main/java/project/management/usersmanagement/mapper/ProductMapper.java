package project.management.usersmanagement.mapper;

import org.mapstruct.Builder;
import org.mapstruct.InjectionStrategy;
import org.mapstruct.Mapper;

import project.management.usersmanagement.dto.ProductViewDto;

import project.management.usersmanagement.entities.Product;

import java.util.List;


@Mapper(componentModel = "spring",
      builder = @Builder(disableBuilder = true),
            injectionStrategy = InjectionStrategy.CONSTRUCTOR)
public interface ProductMapper {

    ProductViewDto toDto(Product source);

    List<ProductViewDto> toDto(List<Product> source);

}
