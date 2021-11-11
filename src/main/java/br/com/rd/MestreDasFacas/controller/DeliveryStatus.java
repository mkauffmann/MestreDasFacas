package br.com.rd.MestreDasFacas.controller;



import br.com.rd.MestreDasFacas.model.dto.DeliveryStatusDTO;
import br.com.rd.MestreDasFacas.service.DeliveryStatusService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/deliveryStatus")
public class DeliveryStatus {
    @Autowired
    DeliveryStatusService deliveryStatusService;
    @PostMapping
    public DeliveryStatusDTO create(@RequestBody DeliveryStatusDTO deliveryStatus){
        return deliveryStatusService.addDeliveryStatus(deliveryStatus);
    }

    @GetMapping
    public List<DeliveryStatusDTO> findAll(){
        return deliveryStatusService.findAll();
    }

    @PutMapping("/{id}")
    public DeliveryStatusDTO updateById(@RequestBody DeliveryStatusDTO dto, @PathVariable Long id){
        return deliveryStatusService.updateById(dto, id);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(code = HttpStatus.NO_CONTENT)
    public void deleteById(@PathVariable("id")Long id){
        deliveryStatusService.deleteById(id);
    }

    @GetMapping("/{id}")
    public DeliveryStatusDTO searchById(@PathVariable("id") Long id) {
        return deliveryStatusService.findDeliveryStatusById(id);
    }
}
