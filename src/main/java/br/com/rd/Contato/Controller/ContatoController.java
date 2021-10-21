package br.com.rd.Contato.Controller;

import br.com.rd.Contato.Model.DTO.ContatoDTO;
import br.com.rd.Contato.Service.ContatoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/contato")
public class ContatoController {
    @Autowired
    ContatoService contatoService;

    @PostMapping
    public ContatoDTO novamensagem(@RequestBody ContatoDTO contato){
        return contatoService.novaMensagem(contato);
    }







}



