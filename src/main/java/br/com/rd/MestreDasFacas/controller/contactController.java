package br.com.rd.MestreDasFacas.controller;
import br.com.rd.MestreDasFacas.model.dto.ContactDTO;
import br.com.rd.MestreDasFacas.service.ContactService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/contato")
public class contactController {
    @Autowired
    ContactService contatoService;

    @PostMapping
    public ContactDTO novamensagem(@RequestBody ContactDTO contato){
        return contatoService.novaMensagem(contato);
    }







}



