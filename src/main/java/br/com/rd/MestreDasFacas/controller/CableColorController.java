//package br.com.rd.MestreDasFacas.controller;
//
//import br.com.rd.MestreDasFacas.model.dto.CableColorDTO;
//import br.com.rd.MestreDasFacas.service.CableColorService;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.http.HttpStatus;
//import org.springframework.web.bind.annotation.*;
//
//import java.util.List;
//
//@RestController
//@RequestMapping("/cableColor")
//public class CableColorController {
//
//    @Autowired
//    CableColorService corCaboService;
//
//    @PostMapping
//    @ResponseStatus(code = HttpStatus.CREATED)
//    public CableColorDTO create(@RequestBody CableColorDTO dto) {
//        return this.corCaboService.addCableColor(dto);
//    }
//
//    @GetMapping
//    public List<CableColorDTO> showList() {
//        return this.corCaboService.showListCableColor();
//    }
//
//    @GetMapping("/{id}")
//    public CableColorDTO find(@PathVariable("id") Long id) {
//        return this.corCaboService.findCableColorById(id);
//    }
//
//    @PutMapping("/{id}")
//    public CableColorDTO update(@RequestBody CableColorDTO dto, @PathVariable("id") Long id) {
//        return this.corCaboService.updateById(dto, id);
//    }
//
//    @DeleteMapping("{id}")
//    @ResponseStatus(code = HttpStatus.NO_CONTENT)
//    public void deleteById(@PathVariable("id") Long id) {
//        this.corCaboService.deleteById(id);
//    }
//
//}
