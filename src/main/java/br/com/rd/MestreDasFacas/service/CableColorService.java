//package br.com.rd.MestreDasFacas.service;
//
//import br.com.rd.MestreDasFacas.model.dto.CableColorDTO;
//import br.com.rd.MestreDasFacas.model.entity.CableColor;
//import br.com.rd.MestreDasFacas.repository.contract.CableColorRepository;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.stereotype.Service;
//
//import java.util.ArrayList;
//import java.util.List;
//import java.util.Optional;
//
//@Service
//public class CableColorService {
//
//    @Autowired
//    CableColorRepository cableColorRepository;
//
//    // Método de adição:
//
//    public CableColorDTO addCableColor(CableColorDTO dto) {
//        CableColor newCableColor = this.dtoToBusiness(dto);
//        newCableColor = cableColorRepository.save(newCableColor);
//        return businessToDto(newCableColor);
//    }
//
//    // Método de listar:
//
//    public List<CableColorDTO> showListCableColor() {
//        List<CableColor> allList = cableColorRepository.findAll();
//        return listToDto(allList);
//    }
//
//    // Método de encontrar por id:
//
//    public CableColorDTO findCableColorById(Long id) {
//        Optional<CableColor> op = cableColorRepository.findById(id);
//        if (op.isPresent()) {
//            return businessToDto(op.get());
//        }
//        return null;
//    }
//
//    // Método de atualizar por id:
//
//    public CableColorDTO updateById(CableColorDTO dto, Long id) {
//        Optional<CableColor> op = cableColorRepository.findById(id);
//
//        if(op.isPresent()) {
//
//            CableColor obj = op.get();
//
//            if(dto.getCableColor() != null) {
//                obj.setCableColor(dto.getCableColor());
//            }
//
//            obj = this.cableColorRepository.save(obj);
//            return businessToDto(obj);
//
//        }
//        return null;
//    }
//
//    // Método de deletar:
//
//    public void deleteById(Long id) {
//        if (this.cableColorRepository.existsById(id)) {
//            this.cableColorRepository.deleteById(id);
//        }
//    }
//
//    // Métodos de conversão:
//
//    public CableColor dtoToBusiness(CableColorDTO dto) {
//        CableColor business = new CableColor();
//        business.setCableColor(dto.getCableColor());
//        return business;
//    }
//
//    public CableColorDTO businessToDto(CableColor business) {
//        CableColorDTO dto = new CableColorDTO();
//        dto.setId(business.getId());
//        dto.setCableColor(business.getCableColor());
//        return dto;
//    }
//
//    public List<CableColorDTO> listToDto (List<CableColor> list) {
//        List<CableColorDTO> listDto = new ArrayList<>();
//        for (CableColor l : list) {
//            listDto.add(this.businessToDto(l));
//        }
//        return listDto;
//    }
//
//}
