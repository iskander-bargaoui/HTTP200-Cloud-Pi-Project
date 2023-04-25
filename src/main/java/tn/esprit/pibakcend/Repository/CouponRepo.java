package tn.esprit.pibakcend.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import tn.esprit.pibakcend.entities.Coupon;

import java.util.Optional;
@Repository
public interface CouponRepo extends JpaRepository<Coupon,Long> {
    Optional<Coupon> findByCode(String code);

}
