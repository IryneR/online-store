package com.onlinestore.store.controler;

import com.onlinestore.store.model.Discount;
import com.onlinestore.store.service.DiscountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/discount")
public class DiscountController {

    @Autowired
    private DiscountService discountService;

    @PostMapping
    public ResponseEntity<Integer> createNewDiscount(@RequestBody Discount discount) {
        Integer discountId = discountService.createNewDiscount(discount);
        return ResponseEntity.status(HttpStatus.CREATED).body(discountId);
    }

       @GetMapping
    public ResponseEntity<List<Discount>> getAllDiscounts() {
        List<Discount> discounts = discountService.getAllDiscounts();
        return ResponseEntity.status(HttpStatus.OK).body(discounts);
    }

    @PutMapping
    public ResponseEntity<Integer> update(@RequestBody Discount discount) {
        discountService.updateDiscount(discount);
        return ResponseEntity.status(HttpStatus.OK).build();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Integer> delete(@PathVariable Integer id) {
        discountService.removeDiscount(id);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }
}
