package br.com.rd.MestreDasFacas.controller;

import br.com.rd.MestreDasFacas.model.dto.InventoryDTO;
import br.com.rd.MestreDasFacas.service.InventoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/inventory")
public class InventoryController {

    @Autowired
    InventoryService inventoryService;

    @PostMapping
    @ResponseStatus(code = HttpStatus.CREATED)
    public InventoryDTO create(@RequestBody InventoryDTO dto) {
        return this.inventoryService.addInventory(dto);
    }

    @GetMapping
    public List<InventoryDTO> showList() {
        return this.inventoryService.showList();
    }

    @GetMapping("/{id}")
    public InventoryDTO find(@PathVariable("id") Long id) {
        return this.inventoryService.findById(id);
    }

    @PutMapping("/{id}")
    public InventoryDTO update(@RequestBody InventoryDTO dto, @PathVariable("id") Long id) {
        return this.inventoryService.update(dto, id);
    }

    @DeleteMapping("{id}")
    @ResponseStatus(code = HttpStatus.NO_CONTENT)
    public void deleteById(@PathVariable("id") Long id) {
        this.inventoryService.deleteById(id);
    }

}
