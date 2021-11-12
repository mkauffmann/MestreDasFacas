package br.com.rd.MestreDasFacas.data;

import br.com.rd.MestreDasFacas.model.entity.Customer;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Optional;

public class CustomerUserDetailsData implements UserDetails {

    private final Optional<Customer> customer;

    public CustomerUserDetailsData(Optional<Customer> customer) {
        this.customer = customer;
    }

    public String getId(){
        String id =  Long.toString(customer.get().getId()) ;
        return id;
    }

    public String getName(){
        return customer.get().getName();
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return new ArrayList<>();
    }

    @Override
    public String getPassword() {
        //orElse metodo da Classe Optional
        return customer.orElse(new Customer()).getPassword();
    }

    @Override
    public String getUsername() {
        return customer.orElse(new Customer()).getEmail();
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }
}
