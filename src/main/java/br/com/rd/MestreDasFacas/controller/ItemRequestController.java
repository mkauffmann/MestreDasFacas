package br.com.rd.MestreDasFacas.controller;


import br.com.rd.MestreDasFacas.model.dto.ItemRequestDTO;
import br.com.rd.MestreDasFacas.service.ItemRequestService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping ("/itemRequest")
public class ItemRequestController {


    @Autowired
    ItemRequestService itemRequestService;

    @PostMapping
    @ResponseStatus (code = HttpStatus.CREATED)

    public ItemRequestDTO create(@RequestBody ItemRequestDTO dto){
        return this.itemRequestService.addItem(dto);


    }
    @GetMapping
    public List<ItemRequestDTO> showList (){
        return this.itemRequestService.showList();



    }

    @PutMapping ("/{id}")
    public ItemRequestDTO upDate (@RequestBody ItemRequestDTO dto, @PathVariable ("id") Long id){

        return this.itemRequestService.update(dto, id);


    }

    @DeleteMapping ("/{id}")
    @ResponseStatus (code = HttpStatus.NO_CONTENT)

    public void deletById (@PathVariable ("id") Long id){
        this.itemRequestService.deleteById(id);

    }











}
