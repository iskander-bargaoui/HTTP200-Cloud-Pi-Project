package tn.esprit.pibakcend.Service;

import java.util.List;
import javax.transaction.Transactional;

import org.springframework.stereotype.Service;
import tn.esprit.pibakcend.Repository.WishListRepository;
import tn.esprit.pibakcend.entities.WishList;


@Service
@Transactional
public class WishListService {

    private final WishListRepository wishListRepository;

    public WishListService(WishListRepository wishListRepository) {
        this.wishListRepository = wishListRepository;
    }

    public void createWishlist(WishList wishList) {
        wishListRepository.save(wishList);
    }

    public List<WishList> readWishList(Integer userId) {
        return wishListRepository.findAllByUserIdOrderByCreatedDateDesc(userId);
    }
}
