package br.com.rd.MestreDasFacas.service;

import br.com.rd.MestreDasFacas.data.CustomerUserDetailsData;
import br.com.rd.MestreDasFacas.model.entity.Customer;
import br.com.rd.MestreDasFacas.repository.contract.CustomerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
public class CustomerUserServiceImpl implements UserDetailsService {
    @Autowired
    CustomerRepository customerRepository;

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        Optional<Customer> customer = customerRepository.findByEmail(email);

        if(!customer.isPresent()){
            throw new UsernameNotFoundException("Usuário com o email  " + email + "não encontrado");
        }

        return new CustomerUserDetailsData(customer);
    }
}
