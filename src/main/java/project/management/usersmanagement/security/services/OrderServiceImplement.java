package project.management.usersmanagement.security.services;
import com.stripe.Stripe;
import com.stripe.exception.StripeException;
import com.stripe.model.checkout.Session;
import com.stripe.param.checkout.SessionCreateParams;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.social.ResourceNotFoundException;
import org.springframework.stereotype.Service;
import project.management.usersmanagement.Config.CouponException;
import project.management.usersmanagement.Repository.CouponRepo;
import project.management.usersmanagement.Repository.OrderItemRep;
import project.management.usersmanagement.Repository.OrderRepo;
import project.management.usersmanagement.Repository.UserRepository;
import project.management.usersmanagement.entities.*;

import javax.transaction.Transactional;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;
@Service
@Transactional
public class OrderServiceImplement  implements IOrder {
    @Autowired
    private OrderRepo orderRepository;
    @Autowired
    private OrderItemRep orderItemRepository;
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private CouponRepo couponRepository;
    @Value("${baseURL}")
    private String baseURL;

    @Value("${stripe.api.Key}")
    private String apiKey;

    @Override
    public Order createOrder(User user, List<OrderItem> orderItems) {
        Order order = new Order();
        order.setUser(user);
        order.setOrderItems(orderItems);
        order.setStatus(EOrderStatus.CREATED);
        order.setCreatedDate(new Date());
        order.setUpdatedAt(new Date());
        order.setTotalPrice(calculateTotalPrice(orderItems));
        return orderRepository.findByUser(user).get(0);
    }

    @Override
    public List<Order> getOrdersByUser(User user) {
        return orderRepository.findByUser(user);
    }

    @Override
    public Order getOrderById(Long id) {
        return orderRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Order", "id"));
    }

    /*  @Override
      public Double calculateTotalPrice(List<OrderItem> orderItems) {
          return null;
      }
      public Double calculateTotalPrice(String orderItemsJson) throws JsonProcessingException {
          ObjectMapper mapper = new ObjectMapper();
          List<OrderItem> orderItems = mapper.readValue(orderItemsJson, new TypeReference<List<OrderItemsRepository>>(){});
          Double totalPrice = 0.0;
          for (OrderItem orderItem : orderItems) {
              totalPrice += orderItem.getProduct().getPrice() * orderItem.getQuantity();
          }
          return totalPrice;
      }*/
    @Override
    public Double calculateTotalPrice(List<OrderItem> orderItems) {
        Double totalPrice = 0.0;
        for (OrderItem orderItem : orderItems) {
            totalPrice += orderItem.getProduct().getPrice() * orderItem.getQuantity();
        }
        return totalPrice;
    }

    @Override
    public List<ERole> getUserRoles(User user) {
        return userRepository.getRoles(user);
    }

    /*@Override
    public Purchase createPurchase(User user, Double amount) {
        Purchase purchase = new Purchase();
        purchase.setUser(user);
        purchase.setAmount(amount);
        purchase.setCreatedAt(new Date());
        purchase.setUpdatedAt(new Date());
        //purchase = purchaseRepository.save(purchase);
        return purchase;
        //return stripeService.chargeCreditCard(user, amount);

    }*/

    @Override
    public boolean isEligibleForCoupon(User user, Double amount) {
        Double purchasesAmountByUser = userRepository.getPurchasesAmountByUser(user);
        if (amount >= 500.0 || (purchasesAmountByUser != null && purchasesAmountByUser >= 500.0)) {
            return true;
        }
        return false;
    }

    @Override
    public Order applyCoupon(User user, Order order, String couponCode) throws CouponException {
        Optional<Coupon> coupon = couponRepository.findByCode(couponCode);
        if (coupon.isPresent()) {
            if (order.getCoupon() != null) {
                throw new CouponException("Order already has a coupon applied");
            }
            if (!coupon.get().getUser().getId().equals(user.getId())) {
                throw new CouponException("Coupon code is not valid for this user");
            }
            if (!coupon.get().isValid()) {
                throw new CouponException("Coupon code is expired");
            }
            if (isEligibleForCoupon(user, calculateTotalPrice(order.getOrderItems()))) {
                order.setCoupon(coupon.get());
                double discount = coupon.get().getDiscount().doubleValue();
                order.setTotalPrice(calculateTotalPrice(order.getOrderItems()) * (1 - discount));
                orderRepository.save(order);
            } else {
                throw new CouponException("User is not eligible for this coupon");
            }
        } else {
            throw new CouponException("Coupon code is invalid");
        }

        return order;
    }

    @Override
    public Order save(Order order) {
        return orderRepository.save(order);
    }

    SessionCreateParams.LineItem.PriceData createPriceData(OrderItem checkoutItemDto) {
        return SessionCreateParams.LineItem.PriceData.builder()
                .setCurrency("usd")
                .setUnitAmount((long) (checkoutItemDto.getUnitprice() * 100))
                .setProductData(
                        SessionCreateParams.LineItem.PriceData.ProductData.builder()
                                .setName(checkoutItemDto.getProduct().getName())
                                .build())
                .build();
    }

    SessionCreateParams.LineItem createSessionLineItem(OrderItem checkoutItemDto) {
        return SessionCreateParams.LineItem.builder()
                // set price for each product
                .setPriceData(createPriceData(checkoutItemDto))
                // set quantity for each product
                .setQuantity(Long.parseLong(String.valueOf(checkoutItemDto.getQuantity())))
                .build();
    }

    // create session from list of checkout items
    public Session createSession(String orderId, List<OrderItem> checkoutItemDtoList) throws StripeException {

        // supply success and failure url for stripe
        String successURL = baseURL + "payment/success?sessionId={CHECKOUT_SESSION_ID}";
        String failedURL = baseURL + "payment/failed";

        // set the private key
        Stripe.apiKey = apiKey;

        List<SessionCreateParams.LineItem> sessionItemsList = new ArrayList<>();

        // for each product compute SessionCreateParams.LineItem
        for (OrderItem checkoutItemDto : checkoutItemDtoList) {
            sessionItemsList.add(createSessionLineItem(checkoutItemDto));
        }

        // build the session param
        SessionCreateParams params = SessionCreateParams.builder()
                .addPaymentMethodType(SessionCreateParams.PaymentMethodType.CARD)
                .setMode(SessionCreateParams.Mode.PAYMENT)
                .setCancelUrl(failedURL)
                .addAllLineItem(sessionItemsList)
                .setSuccessUrl(successURL)
                .putMetadata("order_id", orderId)
                .build();
        return Session.create(params);
    }

    public Double applyCoupon(Double total, Coupon coupon) {
        BigDecimal discountAmount = BigDecimal.valueOf(coupon.getDiscount())
                .multiply(BigDecimal.valueOf(total));
        BigDecimal newTotal = BigDecimal.valueOf(total).subtract(discountAmount);
        return newTotal.doubleValue();
    }

}

