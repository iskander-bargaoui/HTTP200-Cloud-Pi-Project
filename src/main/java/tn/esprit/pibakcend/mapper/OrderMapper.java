package tn.esprit.pibakcend.mapper;

import java.util.List;
import org.mapstruct.Builder;
import org.mapstruct.InjectionStrategy;
import org.mapstruct.Mapper;

import tn.esprit.pibakcend.dto.OrderViewDto;
import tn.esprit.pibakcend.entities.Order;


@Mapper(componentModel = "spring",
      builder = @Builder(disableBuilder = true),
            injectionStrategy = InjectionStrategy.CONSTRUCTOR)
public interface OrderMapper {

    OrderViewDto toDto(Order source);

    List<OrderViewDto> toDto(List<Order> source);

}
