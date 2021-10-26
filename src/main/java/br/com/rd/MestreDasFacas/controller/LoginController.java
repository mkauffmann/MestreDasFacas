package br.com.rd.MestreDasFacas.controller;

import br.com.rd.MestreDasFacas.model.dto.LoginDTO;
import br.com.rd.MestreDasFacas.service.LoginService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/login")
public class LoginController {
    @Autowired
    LoginService loginService;

    @PostMapping("/validatePassword")
    public ResponseEntity<String> validatePassword(@RequestBody LoginDTO dto) {
        Boolean valid = loginService.validatePassword(dto.getEmail(), dto.getPassword());

        if(valid == null){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Usuário não encontrado");
        } else if (valid){
            return ResponseEntity.status(HttpStatus.OK).body("Usuário e senha ok");
        }

        return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Senha incorreta");
    }
}
