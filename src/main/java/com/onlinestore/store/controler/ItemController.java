package com.onlinestore.store.controler;

import com.onlinestore.store.model.Item;
import com.onlinestore.store.service.ItemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/item")
public class ItemController {

    @Autowired
    private ItemService itemService;

    @PostMapping
    public ResponseEntity<Integer> createNewItem(@RequestBody Item item) {
        Integer itemId  = itemService.createNewItem(item);
        return ResponseEntity.status(HttpStatus.CREATED).body(itemId);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Item> getItem(@PathVariable Integer id) {
        Optional<Item> optionalItem = itemService.getItem(id);
        if (optionalItem.isPresent()) {
            return ResponseEntity.status(HttpStatus.OK).body(optionalItem.get());
        }
        return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
    }

    @GetMapping
    public ResponseEntity<List<Item>> getAllItems() {
        List<Item> items = itemService.getAlItems();
        return ResponseEntity.status(HttpStatus.OK).body(items);
    }

    @PutMapping
    public ResponseEntity<Integer> updateItem(@RequestBody Item item) {
        itemService.updateItem(item);
        return ResponseEntity.status(HttpStatus.OK).build();

    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Integer> delete(@PathVariable Integer id) {
        itemService.removeItem(id);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }
}
