package project.management.usersmanagement.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import project.management.usersmanagement.entities.OrderItem;
import project.management.usersmanagement.security.services.OrderItemImplement;

import java.util.List;

@RestController
@RequestMapping("/api/orderitems")
public class OrderIdemController {

        private OrderItemImplement orderItemService;

        @Autowired
        public void OrderItemController(OrderItemImplement orderItemService) {
            this.orderItemService = orderItemService;
        }

        @PostMapping
        public ResponseEntity<OrderItem> createOrderItem(@RequestBody OrderItem orderItem) {
            OrderItem createdOrderItem = orderItemService.createOrderItem(orderItem);
            return new ResponseEntity<>(createdOrderItem, HttpStatus.CREATED);
        }

        @PutMapping("/{id}")
        public ResponseEntity<OrderItem> updateOrderItem(@PathVariable Long id, @RequestBody OrderItem orderItem) {
            OrderItem existingOrderItem = orderItemService.getOrderItemById(id);
            if (existingOrderItem == null) {
                return new ResponseEntity<>(HttpStatus.NOT_FOUND);
            }
            existingOrderItem.setPrice(orderItem.getPrice());
            existingOrderItem.setQuantity(orderItem.getQuantity());
            OrderItem updatedOrderItem = orderItemService.updateOrderItem(existingOrderItem);
            return new ResponseEntity<>(updatedOrderItem, HttpStatus.OK);
        }

        @DeleteMapping("/{id}")
        public ResponseEntity<Void> deleteOrderItem(@PathVariable Long id) {
            OrderItem existingOrderItem = orderItemService.getOrderItemById(id);
            if (existingOrderItem == null) {
                return new ResponseEntity<>(HttpStatus.NOT_FOUND);
            }
            orderItemService.deleteOrderItem(existingOrderItem);
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }

        @GetMapping("/order/{orderId}")
        public ResponseEntity<List<OrderItem>> getOrderItemsByOrderId(@PathVariable Long orderId) {
            List<OrderItem> orderItems = orderItemService.getOrderItemsByOrderId(orderId);
            if (orderItems.isEmpty()) {
                return new ResponseEntity<>(HttpStatus.NOT_FOUND);
            }
            return new ResponseEntity<>(orderItems, HttpStatus.OK);
        }
    }



