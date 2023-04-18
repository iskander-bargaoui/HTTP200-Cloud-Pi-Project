package tn.esprit.pibakcend.controllers;;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import org.springframework.web.bind.annotation.*;
import tn.esprit.pibakcend.Service.AuthenticationService;
import tn.esprit.pibakcend.Service.ProductService;
import tn.esprit.pibakcend.Service.WishListService;
import tn.esprit.pibakcend.common.ApiResponse;
import tn.esprit.pibakcend.dto.product.ProductDto;
import tn.esprit.pibakcend.entities.Product;
import tn.esprit.pibakcend.entities.User;
import tn.esprit.pibakcend.entities.WishList;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/wishlist")
public class WishListController {

        @Autowired
        private WishListService wishListService;

        @Autowired
        private AuthenticationService authenticationService;

        @GetMapping("/{token}")
        public ResponseEntity<List<ProductDto>> getWishList(@PathVariable("token") String token) {
                Long user_id = authenticationService.getUser(token).getId();
                List<WishList> body = wishListService.readWishList(Math.toIntExact(user_id));
                List<ProductDto> products = new ArrayList<ProductDto>();
                for (WishList wishList : body) {
                        products.add(ProductService.getDtoFromProduct(wishList.getProduct()));
                }

                return new ResponseEntity<List<ProductDto>>(products, HttpStatus.OK);
        }

        @PostMapping("/add")
        public ResponseEntity<ApiResponse> addWishList(@RequestBody Product product, @RequestParam("token") String token) {
                authenticationService.authenticate(token);
                User user = authenticationService.getUser(token);
                WishList wishList = new WishList(user, product);
                wishListService.createWishlist(wishList);
                return new ResponseEntity<ApiResponse>(new ApiResponse(true, "Add to wishlist"), HttpStatus.CREATED);

        }


}
