package br.com.rd.MestreDasFacas.service;


import br.com.rd.MestreDasFacas.enums.StatusEmail;
import br.com.rd.MestreDasFacas.model.dto.*;
import br.com.rd.MestreDasFacas.model.entity.*;
import br.com.rd.MestreDasFacas.repository.contract.*;
import br.com.rd.MestreDasFacas.security.JWTConfiguration;
import br.com.rd.MestreDasFacas.service.conversion.DtoConversion;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.MailException;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import javax.validation.constraints.Email;
import java.sql.SQLException;
import java.sql.SQLIntegrityConstraintViolationException;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class CustomerService {
    @Autowired
    CustomerRepository customerRepository;

    @Autowired
    CreditCardRepository creditCardRepository;

    @Autowired
    AddressRepository addressRepository;

    @Autowired
    TelephoneRepository telephoneRepository;

    @Autowired
    DtoConversion conversion;

    @Autowired
    PasswordEncoder encoder;

    @Autowired
    EmailRepository emailRepository;

    @Autowired
    private JavaMailSender emailSender;



    public CustomerDTO add(CustomerDTO dto) throws SQLIntegrityConstraintViolationException {
        Customer newCustomer = conversion.customerDtoToBusiness(dto);

        //encriptar senha
        String passwordCrypt = encoder.encode(dto.getPassword());
        newCustomer.setPassword(passwordCrypt);

        try {
            newCustomer = customerRepository.save(newCustomer);
            sendSignUpEmail(newCustomer);
            return conversion.customerBusinessToDto(newCustomer);
        } catch (Exception e){
            throw new SQLIntegrityConstraintViolationException("Email já cadastrado em outro usuário", e.getCause());
        }



    }

    public CustomerDTO update(Long id, CustomerDTO dto) throws Exception {
        Optional<Customer> op = customerRepository.findById(id);
        if(op.isPresent()){
            Customer update = op.get();

            if(dto.getName() != null){
                update.setName(dto.getName());
            }
            if(dto.getEmail() != null){
                update.setEmail(dto.getEmail());
            }
            if(dto.getCpf() != null){
                update.setCpf(dto.getCpf());
            }
            if(dto.getBirthDate() != null){
                update.setBirthDate(dto.getBirthDate());
            }
//            if(dto.getPassword() != null){
//                //encriptar senha
//                String passwordCrypt = encoder.encode(dto.getPassword());
//                update.setPassword(passwordCrypt);
//            }
            if(dto.getGender() != null){
                Gender gender = conversion.genderDtoToBusiness(dto.getGender());
                update.setGender(gender);
            }

            try{
                update = customerRepository.save(update);
                return conversion.customerBusinessToDto(update);
            } catch (Exception e){
                throw new Exception("Email já cadastrado em outro usuário", e);
            }

        }
        return null;
    }

    public boolean changePassword(PasswordDTO dto){
        Optional<Customer> op = customerRepository.findById(dto.getCustomerId());

        if (op.isPresent()){
            Customer customer = op.get();

           if(encoder.matches(dto.getCurrentPassword(), customer.getPassword())){
               //encriptar senha
               String newPasswordCrypt = encoder.encode((dto.getPassword()));
               customer.setPassword(newPasswordCrypt);
               customer = customerRepository.save(customer);
               return true;
           }

        }
        return false;
    }

    public CustomerDTO findById(Long id){
        Optional<Customer> op = customerRepository.findById(id);
        if(op.isPresent()){
            Customer customer = op.get();
            return conversion.customerBusinessToDto(customer);
        }
        return null;
    }

    public CustomerDTO findByEmail(String email){
        Optional<Customer> op2 = customerRepository.findByEmail(email);
        if(op2.isPresent()){
            Customer customer = op2.get();
            return conversion.customerBusinessToDto(customer);
        }
        return null;
    }

    public List<CustomerDTO> findAll(){
        List<Customer> list = customerRepository.findAll();

        return conversion.customerListToDto(list);
    }

    public void deleteById(Long id){
        if (customerRepository.existsById(id)){
            customerRepository.deleteById(id);
        }
    }

    public CustomerDTO addAddressToCustomer(AddressDTO addressDTO, Long customerId){
        Optional<Customer> op = customerRepository.findById(customerId);

        if(op.isPresent()){
            Customer customer = op.get();
            if(customer.getAddresses() != null){
                List<Address> listAddress = customer.getAddresses();
                Address newAddress = conversion.addressDtoToBusiness(addressDTO);
                newAddress = addressRepository.save(newAddress);

                listAddress.add(newAddress);
                customer.setAddresses(listAddress);
                customer = customerRepository.save(customer);
                return conversion.customerBusinessToDto(customer);
            }
        }
        return null;
    }

    public CustomerDTO removeAddressFromCustomer(Long customerId, Long addressId){
        Optional<Customer> opCustomer = customerRepository.findById(customerId);
        Optional<Address> opAddress = addressRepository.findById(addressId);

        if(opCustomer.isPresent() && opAddress.isPresent()){
            Customer customer = opCustomer.get();
            Address address = opAddress.get();

            if(customer.getAddresses() != null){
                List<Address> customerAddressList = customer.getAddresses();

                for(Address a : customerAddressList){
                    if(a.getId() == addressId){
                        customerAddressList.remove(address);
                        break;
                    }
                }
                customer.setAddresses(customerAddressList);
                customer = customerRepository.save(customer);
                return conversion.customerBusinessToDto(customer);
            }
        }
        return null;
    }

    public CustomerDTO addTelephoneToCustomer(TelephoneDTO dto, Long customerId){
        Optional<Customer> op = customerRepository.findById(customerId);

        if(op.isPresent()){
            Customer customer = op.get();
            if(customer.getTelephones() != null){
                List<Telephone> telephoneList = customer.getTelephones();
                Telephone newTelephone = conversion.telephoneDtoToBusiness(dto);

                newTelephone = telephoneRepository.save(newTelephone);
                telephoneList.add(newTelephone);
                customer.setTelephones(telephoneList);
                customer = customerRepository.save(customer);

                return conversion.customerBusinessToDto(customer);
            }
        }
        return null;
    }



    public CustomerDTO removeTelephoneFromCustomer(Long customerId, Long telephoneId){
        Optional<Customer> opCustomer = customerRepository.findById(customerId);
        Optional<Telephone> opTelephone = telephoneRepository.findById(telephoneId);

        if(opCustomer.isPresent() && opTelephone.isPresent()){
            Customer customer = opCustomer.get();
            Telephone telephone = opTelephone.get();

            if(customer.getTelephones() != null){
                List<Telephone> telephoneList = customer.getTelephones();

                for (Telephone t : telephoneList){
                    if(t.getId() == telephone.getId()){
                        telephoneList.remove(telephone);
                        break;
                    }
                }

                customer.setTelephones(telephoneList);
                customer = customerRepository.save(customer);

                return conversion.customerBusinessToDto(customer);
            }
        }
        return null;
    }

    public CustomerDTO addCreditCardToCustomer(CreditCardDTO dto, Long customerId){
        Optional<Customer> op = customerRepository.findById(customerId);

        if(op.isPresent()){
            Customer customer = op.get();

            if(customer.getCreditCards() != null){
                List<CreditCard> creditCardList = customer.getCreditCards();
                CreditCard newCard = conversion.creditCardDtoToBusiness(dto);

                newCard = creditCardRepository.save(newCard);
                creditCardList.add(newCard);
                customer.setCreditCards(creditCardList);
                customer = customerRepository.save(customer);

                return conversion.customerBusinessToDto(customer);
            }
        }
        return null;
    }

    public CustomerDTO removeCreditCardFromCustomer(Long customerId, Long creditCardId){
        Optional<Customer> opCustomer = customerRepository.findById(customerId);
        Optional<CreditCard> opCreditCard = creditCardRepository.findById(creditCardId);

        if(opCustomer.isPresent() && opCreditCard.isPresent()){
            Customer customer = opCustomer.get();
            CreditCard card = opCreditCard.get();

            if(customer.getCreditCards() != null){
                List<CreditCard> creditCardList = customer.getCreditCards();

                for (CreditCard c : creditCardList){
                    if(c.getId() == card.getId()){
                        creditCardList.remove(card);
                        break;
                    }
                }

                customer.setCreditCards(creditCardList);
                customer = customerRepository.save(customer);

                return conversion.customerBusinessToDto(customer);
            }
        }
        return null;

    }
    public void sendSignUpEmail(Customer newCustomer){

        EmailModel email = new EmailModel();
        email.setSendDateEmail(LocalDateTime.now());
        email.setOwnerRef(newCustomer.getId());
        email.setEmailTo(newCustomer.getEmail());
        email.setEmailFrom("mestredasfacas2021@gmail.com");
        email.setSubject("Bem-vido(a) a Mestre das facas");
        email.setText(String.format(" Ola, %s, seja bem-vindo(a) à Mestre Das Facas! Espero que goste.", newCustomer.getName()));

        try{
            SimpleMailMessage message = new SimpleMailMessage();
            message.setFrom(email.getEmailFrom());
            message.setTo(email.getEmailTo());
            message.setSubject(email.getSubject());
            message.setText(email.getText());
            emailSender.send(message);
            email.setStatusEmail(StatusEmail.SENT);
        }catch (MailException e ) {
            email.setStatusEmail(StatusEmail.ERROR);

        }finally {
            emailRepository.save(email);
        }





    }


    public void updateResetPasswordToken(String token, String email) throws CustomerNotFoundException {
       Optional <Customer> customer = customerRepository.findByEmail(email);
       if (customer.isPresent()){
           customer.get().setResetPasswordToken(token);
           customerRepository.save(customer.get());
       }else {
           throw new CustomerNotFoundException("Não foi possivel encontrar nenhum usuario com o e-mail " + email);
       }
    }

    public CustomerDTO get (String resetPasswordToken){
        return conversion.customerBusinessToDto(customerRepository.findByResetPasswordToken(resetPasswordToken));
    }

    public void updatePassword(CustomerDTO customerDTO, String newPassword){

        Customer customer = conversion.customerDtoToBusiness(customerDTO);

        customer.setId(customerDTO.getId());
        customer.setPassword(encoder.encode(newPassword));
        customer.setResetPasswordToken(null);

        customerRepository.save(customer);



    }


    public void sendPasswordRecoveryEmail(String email, String resetPasswordLink){

        Optional <Customer> customer = customerRepository.findByEmail(email);



        EmailModel newEmail = new EmailModel();


        newEmail.setSendDateEmail(LocalDateTime.now());
        newEmail.setOwnerRef(customer.get().getId());
        newEmail.setEmailTo(email);
        newEmail.setEmailFrom("mestredasfacas2021@gmail.com");
        newEmail.setText(
                "<p> Olá, </p>"
                + "<p>Você solicitou a redefinição </p> "
                + "<p> Clique no link abaixo para resetar sua senha: </p>"
                + "<p> <b> <a href=\"" + resetPasswordLink + "\" > Resetar minha senha</a><b></p>"
                + "<p> Caso não tenha sido você que tenha feito esta solicitação por favor ignore este email </p>");




        try{
            SimpleMailMessage message = new SimpleMailMessage();
            message.setFrom(newEmail.getEmailFrom());
            message.setTo(newEmail.getEmailTo());
            message.setSubject(newEmail.getSubject());
            message.setText(newEmail.getText());

            emailSender.send(message);
            newEmail.setStatusEmail(StatusEmail.SENT);
        }catch (MailException e ) {
            newEmail.setStatusEmail(StatusEmail.ERROR);

        }finally {
            emailRepository.save(newEmail);
        }



}


    public CustomerDTO getByResetPasswordToken(String token) {

        return conversion.customerBusinessToDto(customerRepository.findByResetPasswordToken(token));
    }



}