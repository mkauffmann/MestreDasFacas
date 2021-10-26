package br.com.rd.MestreDasFacas.service;

import br.com.rd.MestreDasFacas.model.entity.Customer;
import br.com.rd.MestreDasFacas.repository.contract.CustomerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class LoginService {
    @Autowired
    CustomerRepository customerRepository;

    @Autowired
    PasswordEncoder encoder;

    public Boolean validatePassword(String email, String password){
        Optional<Customer> op = customerRepository.findByEmail(email);
        if(op.isPresent()){
            Customer customer = op.get();
            return encoder.matches(password, customer.getPassword());
        }
        return null;
    }
}
