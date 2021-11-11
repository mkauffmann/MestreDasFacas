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
        business.setTitulo(dto.getTitulo());
        business.setNome(dto.getNome());
        business.setEmail(dto.getEmail());
        business.setMensagem(dto.getMensagem());

        return business;
    }

    public ContactDTO businessToDto(Contact business) {
        ContactDTO dto = new ContactDTO();
        dto.setId(business.getId());
        dto.setTitulo(business.getTitulo());
        dto.setNome(business.getNome());
        dto.setEmail(business.getEmail());
        dto.setMensagem(business.getMensagem());
        return dto;
    }
}



