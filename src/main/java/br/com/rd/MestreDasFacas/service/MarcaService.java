package br.com.rd.MestreDasFacas.service;

import br.com.rd.MestreDasFacas.model.dto.MarcaDTO;
import br.com.rd.MestreDasFacas.model.entity.Marca;
import br.com.rd.MestreDasFacas.repository.MarcaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class MarcaService {

    @Autowired
    MarcaRepository marcaRepository;

    // Método de adição:

    public MarcaDTO addMarca(MarcaDTO dto) {
        Marca newMarca = this.dtoToBusiness(dto);
        newMarca = this.marcaRepository.save(newMarca);
        return businessToDto(newMarca);
    }

    // Método de listar:

    public List<MarcaDTO> showListMarca() {
        List<Marca> allList = marcaRepository.findAll();
        return listToDto(allList);
    }

    // Método de encontrar por id:

    public MarcaDTO findMarcaById(Long id) {
        Optional<Marca> op = this.marcaRepository.findById(id);
        if (op.isPresent()) {
            return businessToDto(op.get());
        }
        return null;
    }

    public MarcaDTO updateById(MarcaDTO dto, Long id) {
        Optional<Marca> op = this.marcaRepository.findById(id);

        if(op.isPresent()) {

            Marca obj = op.get();

            if(dto.getMarca() != null) {
                obj.setMarca(dto.getMarca());
            }

            obj = this.marcaRepository.save(obj);
            return businessToDto(obj);

        }
        return null;
    }

    public void deleteById(Long id) {
        if (this.marcaRepository.existsById(id)) {
            this.marcaRepository.deleteById(id);
        }
    }

    // Métodos de conversão:

    public Marca dtoToBusiness(MarcaDTO dto) {
        Marca business = new Marca();
        business.setMarca(dto.getMarca());
        return business;
    }

    public MarcaDTO businessToDto(Marca business) {
        MarcaDTO dto = new MarcaDTO();
        dto.setId(business.getId());
        dto.setMarca(business.getMarca());
        return dto;
    }

    public List<MarcaDTO> listToDto (List<Marca> list) {
        List<MarcaDTO> listDto = new ArrayList<>();
        for (Marca m : list) {
            listDto.add(this.businessToDto(m));
        }
        return listDto;
    }

}
