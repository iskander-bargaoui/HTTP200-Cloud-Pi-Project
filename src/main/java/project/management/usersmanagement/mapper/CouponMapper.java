package project.management.usersmanagement.mapper;

import org.mapstruct.Builder;
import org.mapstruct.InjectionStrategy;
import org.mapstruct.Mapper;
import project.management.usersmanagement.dto.CouponViewDto;
import project.management.usersmanagement.dto.OrderViewDto;
import project.management.usersmanagement.entities.Coupon;
import project.management.usersmanagement.entities.Order;

import java.util.List;

@Mapper(componentModel = "spring",
        builder = @Builder(disableBuilder = true),
        injectionStrategy = InjectionStrategy.CONSTRUCTOR)
public interface CouponMapper {
    CouponViewDto toDto(Coupon source);

    List<CouponViewDto> toDto(List<Coupon> source);
}
