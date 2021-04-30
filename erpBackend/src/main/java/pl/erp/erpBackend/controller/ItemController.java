package pl.erp.erpBackend.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import pl.erp.erpBackend.entity.Item;
import pl.erp.erpBackend.repository.ItemRepository;

import java.util.List;

@RestController
@RequiredArgsConstructor
public class ItemController {
    private final ItemRepository itemRepository;

    @PostMapping("/item")
    public Item newItem(@RequestBody Item newItem) {
        return itemRepository.save(newItem);
    }

    @GetMapping("/item")
    public List<Item> showItems() {
        return itemRepository.findAll();
    }

    //TODO zabezpieczenie przed wywaleniem serwera
    @DeleteMapping("/item")
    public ResponseEntity deleteItem(@RequestBody Long id) {
        itemRepository.deleteById(id);
        return ResponseEntity.ok().build();
    }
}
