package com.onlinestore.store.controler;

import com.onlinestore.store.service.BasketService;
import com.onlinestore.store.to.NewBasket;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/basket")
public class BasketController {

    @Autowired
    private BasketService basketService;

    @PostMapping
    public ResponseEntity<Integer> addItemToBasket(@RequestBody NewBasket newBasket) {
        basketService.addItem(newBasket);
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

    @PostMapping
    public ResponseEntity<Integer> pay(@RequestBody NewBasket basket) {
        Boolean payed = basketService.pay(basket);
        if (payed)
            return ResponseEntity.status(HttpStatus.OK).build();
        else
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
    }

    @DeleteMapping("/{userId}/{id}")
    public ResponseEntity<Integer> deleteItem(@PathVariable Integer userId, @PathVariable Integer itemId) {
        basketService.deleteItemById(userId, itemId);
        return ResponseEntity.status(HttpStatus.OK).build();

    }

    @DeleteMapping("/{userId}/{id}")
    public ResponseEntity<Integer> deleteItems(@PathVariable Integer userId, @PathVariable Integer itemId) {
        basketService.deleteAllItemsById(userId, itemId);
        return ResponseEntity.status(HttpStatus.OK).build();
    }

    @DeleteMapping("/{userId}")
    public ResponseEntity<Integer> deleteItems(@PathVariable Integer userId) {
        basketService.deleteAllItems(userId);
        return ResponseEntity.status(HttpStatus.OK).build();
    }

}
