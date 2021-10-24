package br.com.rd.MestreDasFacas.controller;
import br.com.rd.MestreDasFacas.model.dto.ContatoDTO;
import br.com.rd.MestreDasFacas.service.ContatoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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



