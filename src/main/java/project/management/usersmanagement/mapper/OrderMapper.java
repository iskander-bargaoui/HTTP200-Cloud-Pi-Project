package project.management.usersmanagement.mapper;

import java.util.List;
import org.mapstruct.Builder;
import org.mapstruct.InjectionStrategy;
import org.mapstruct.Mapper;

import project.management.usersmanagement.dto.OrderViewDto;
import project.management.usersmanagement.entities.Order;


@Mapper(componentModel = "spring",
      builder = @Builder(disableBuilder = true),
            injectionStrategy = InjectionStrategy.CONSTRUCTOR)
public interface OrderMapper {

    OrderViewDto toDto(Order source);

    List<OrderViewDto> toDto(List<Order> source);

}
