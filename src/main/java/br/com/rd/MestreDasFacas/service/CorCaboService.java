package br.com.rd.MestreDasFacas.service;

import br.com.rd.MestreDasFacas.model.dto.CorCaboDTO;
import br.com.rd.MestreDasFacas.model.entity.CorCabo;
import br.com.rd.MestreDasFacas.repository.CorCaboRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class CorCaboService {

    @Autowired
    CorCaboRepository corCaboRepository;

    // Método de adição:

    public CorCaboDTO addCorCabo(CorCaboDTO dto) {
        CorCabo newCorCabo = this.dtoToBusiness(dto);
        newCorCabo = corCaboRepository.save(newCorCabo);
        return businessToDto(newCorCabo);
    }

    // Método de listar:

    public List<CorCaboDTO> showListCorCabo() {
        List<CorCabo> allList = corCaboRepository.findAll();
        return listToDto(allList);
    }

    // Método de encontrar por id:

    public CorCaboDTO findCorCaboById(Long id) {
        Optional<CorCabo> op = corCaboRepository.findById(id);
        if (op.isPresent()) {
            return businessToDto(op.get());
        }
        return null;
    }

    // Método de atualizar por id:

    public CorCaboDTO updateById(CorCaboDTO dto, Long id) {
        Optional<CorCabo> op = corCaboRepository.findById(id);

        if(op.isPresent()) {

            CorCabo obj = op.get();

            if(dto.getCorCabo() != null) {
                obj.setCorCabo(dto.getCorCabo());
            }

            obj = this.corCaboRepository.save(obj);
            return businessToDto(obj);

        }
        return null;
    }

    // Método de deletar:

    public void deleteById(Long id) {
        if (this.corCaboRepository.existsById(id)) {
            this.corCaboRepository.deleteById(id);
        }
    }

    // Métodos de conversão:

    public CorCabo dtoToBusiness(CorCaboDTO dto) {
        CorCabo business = new CorCabo();
        business.setCorCabo(dto.getCorCabo());
        return business;
    }

    public CorCaboDTO businessToDto(CorCabo business) {
        CorCaboDTO dto = new CorCaboDTO();
        dto.setId(business.getId());
        dto.setCorCabo(business.getCorCabo());
        return dto;
    }

    public List<CorCaboDTO> listToDto (List<CorCabo> list) {
        List<CorCaboDTO> listDto = new ArrayList<>();
        for (CorCabo l : list) {
            listDto.add(this.businessToDto(l));
        }
        return listDto;
    }

}
