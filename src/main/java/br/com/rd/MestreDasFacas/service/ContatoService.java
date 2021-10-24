package br.com.rd.MestreDasFacas.service;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import br.com.rd.MestreDasFacas.model.dto.ContatoDTO;
import br.com.rd.MestreDasFacas.model.entity.Contato;
import br.com.rd.MestreDasFacas.repository.contract.ContatoRepository;

@Service
public class ContatoService {

    @Autowired
    ContatoRepository contatoRepository;

    public ContatoDTO novaMensagem(ContatoDTO contato){
        Contato newContato = this.dtoToBusiness(contato);
        newContato = contatoRepository.save(newContato);
        return this.businessToDto(newContato);
    }

    public Contato dtoToBusiness(ContatoDTO dto) {
        Contato business = new Contato();
        business.setId(dto.getId());
        business.setTitulo(dto.getTitulo());
        business.setNome(dto.getNome());
        business.setEmail(dto.getEmail());
        business.setMensagem(dto.getMensagem());
        return business;
    }

    public ContatoDTO businessToDto(Contato business) {
        ContatoDTO dto = new ContatoDTO();
        dto.setId(business.getId());
        dto.setTitulo(business.getTitulo());
        dto.setNome(business.getNome());
        dto.setEmail(business.getEmail());
        dto.setMensagem(business.getMensagem());
        return dto;
    }
}



