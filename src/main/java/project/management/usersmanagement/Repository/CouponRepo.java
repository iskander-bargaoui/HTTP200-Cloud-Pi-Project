package project.management.usersmanagement.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import project.management.usersmanagement.entities.Coupon;

import java.util.Optional;
@Repository
public interface CouponRepo extends JpaRepository<Coupon,Long> {
    Optional<Coupon> findByCode(String code);

}
