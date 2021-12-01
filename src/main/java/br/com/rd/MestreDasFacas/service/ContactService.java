package br.com.rd.MestreDasFacas.service;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import br.com.rd.MestreDasFacas.model.dto.ContactDTO;
import br.com.rd.MestreDasFacas.model.entity.Contact;
import br.com.rd.MestreDasFacas.repository.contract.ContactRepository;

@Service
public class ContactService {

    @Autowired
    ContactRepository contatoRepository;

    public ContactDTO novaMensagem(ContactDTO contato){
        Contact newContato = this.dtoToBusiness(contato);
        newContato = contatoRepository.save(newContato);
        return this.businessToDto(newContato);
    }

    public Contact dtoToBusiness(ContactDTO dto) {
        Contact business = new Contact();
        business.setId(dto.getId());
        business.setCpf(dto.getCpf());
        business.setComplete_name(dto.getComplete_name());
        business.setEmail(dto.getEmail());
        business.setTel(dto.getTel());
        business.setMotivo(dto.getMotivo());
        business.setPd_numero(dto.getPd_numero());
        business.setMsg(dto.getMsg());


        return business;
    }

    public ContactDTO businessToDto(Contact business) {
        ContactDTO dto = new ContactDTO();
        dto.setId(business.getId());
        dto.setCpf(business.getCpf());
        dto.setComplete_name(business.getComplete_name());
        dto.setEmail(business.getEmail());
        dto.setTel(business.getTel());
        dto.setMotivo(business.getMotivo());
        dto.setPd_numero(business.getPd_numero());
        dto.setMsg(business.getMsg());


        return dto;
    }
}



