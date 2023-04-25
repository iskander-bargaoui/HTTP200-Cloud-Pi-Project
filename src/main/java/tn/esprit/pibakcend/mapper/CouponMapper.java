package tn.esprit.pibakcend.mapper;

import org.mapstruct.Builder;
import org.mapstruct.InjectionStrategy;
import org.mapstruct.Mapper;
import tn.esprit.pibakcend.dto.CouponViewDto;
import tn.esprit.pibakcend.entities.Coupon;

import java.util.List;

@Mapper(componentModel = "spring",
        builder = @Builder(disableBuilder = true),
        injectionStrategy = InjectionStrategy.CONSTRUCTOR)
public interface CouponMapper {
    CouponViewDto toDto(Coupon source);

    List<CouponViewDto> toDto(List<Coupon> source);
}
