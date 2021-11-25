package br.com.rd.MestreDasFacas.controller;


import br.com.rd.MestreDasFacas.model.dto.RequestDTO;
import br.com.rd.MestreDasFacas.service.RequestService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/request")
public class RequestController {

   @Autowired
   RequestService requestService;

    @PostMapping
    @ResponseStatus(code = HttpStatus.CREATED)
    public RequestDTO create(@RequestBody RequestDTO dto) throws Exception {
        return this.requestService.addRequest(dto);
    }

    @GetMapping
    public List<RequestDTO> findAll() {
        return this.requestService.findAll();
    }

    @PutMapping("/{id}")
    public RequestDTO update(@RequestBody RequestDTO dto, @PathVariable("id") Long id) {
        return this.requestService.update(dto, id);
    }

    @DeleteMapping("{id}")
    @ResponseStatus(code = HttpStatus.NO_CONTENT)
    public void deleteById(@PathVariable("id") Long id) {
        this.requestService.deleteById(id);
    }

    @GetMapping("/{id}")
    public RequestDTO searchById(@PathVariable("id") Long id) {
        return this.requestService.findById(id);
    }

    @GetMapping("/customer/{id}")
    public List<RequestDTO> showListAllByCustomer(@PathVariable("id") Long id) {
        return this.requestService.listAllByCustomers(id);
    }

}
