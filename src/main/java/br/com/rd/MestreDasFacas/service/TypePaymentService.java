package br.com.rd.MestreDasFacas.service;

import br.com.rd.MestreDasFacas.model.dto.TypePaymentDTO;
import br.com.rd.MestreDasFacas.model.entity.TypePayment;
import br.com.rd.MestreDasFacas.repository.contract.TypePaymentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class TypePaymentService {

    @Autowired
    TypePaymentRepository typePaymentRepository;

    // create

    public TypePaymentDTO addTypePayment(TypePaymentDTO dto) {
        TypePayment newTypePayment = this.dtoToBusiness(dto);
        newTypePayment = this.typePaymentRepository.save(newTypePayment);
        return businessToDto(newTypePayment);
    }

    // Show all

    public List<TypePaymentDTO> findAll() {
        List<TypePayment> allList = typePaymentRepository.findAll();
        return listToDto(allList);
    }

    // Update by id

    public TypePaymentDTO updateById(TypePaymentDTO dto, Long id) {
        Optional<TypePayment> op = this.typePaymentRepository.findById(id);

        if(op.isPresent()) {

            TypePayment obj = op.get();

            if(dto.getDescription_type_payment() != null) {
                obj.setDescription_type_payment(dto.getDescription_type_payment());
            }

            obj = this.typePaymentRepository.save(obj);
            return businessToDto(obj);

        }
        return null;
    }

    //delete by id

    public void deleteById(Long id) {
        if (this.typePaymentRepository.existsById(id)) {
            this.typePaymentRepository.deleteById(id);
        }
    }

    // Show by id

    public TypePaymentDTO findTypePaymentById(Long id) {
        Optional<TypePayment> op = this.typePaymentRepository.findById(id);
        if (op.isPresent()) {
            return businessToDto(op.get());
        }
        return null;
    }

    //Convertion

    public TypePayment dtoToBusiness(TypePaymentDTO dto) {
        TypePayment business = new TypePayment();
        business.setDescription_type_payment(dto.getDescription_type_payment());
        return business;
    }

    public TypePaymentDTO businessToDto(TypePayment business) {
        TypePaymentDTO dto = new TypePaymentDTO();
        dto.setId(business.getId());
        dto.setDescription_type_payment(business.getDescription_type_payment());
        return dto;
    }

    public List<TypePaymentDTO> listToDto (List<TypePayment> list) {
        List<TypePaymentDTO> listDto = new ArrayList<>();
        for (TypePayment b : list) {
            listDto.add(this.businessToDto(b));
        }
        return listDto;
    }

}
