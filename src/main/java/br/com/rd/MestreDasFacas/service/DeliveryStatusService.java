package br.com.rd.MestreDasFacas.service;


import br.com.rd.MestreDasFacas.model.dto.DeliveryStatusDTO;
import br.com.rd.MestreDasFacas.model.entity.DeliveryStatus;
import br.com.rd.MestreDasFacas.repository.contract.DeliveryStatusRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class DeliveryStatusService {

    @Autowired
    DeliveryStatusRepository deliveryStatusRepository;

    // Create

    public DeliveryStatusDTO addDeliveryStatus(DeliveryStatusDTO dto) {
        DeliveryStatus newDeliveryStatus = this.dtoToBusiness(dto);
        newDeliveryStatus = deliveryStatusRepository.save(newDeliveryStatus);
        return businessToDto(newDeliveryStatus);
    }

    // Show all

    public List<DeliveryStatusDTO> findAll() {
        List<DeliveryStatus> allList = deliveryStatusRepository.findAll();
        return listToDto(allList);
    }

    // Update by id

    public DeliveryStatusDTO updateById(DeliveryStatusDTO dto, Long id) {
        Optional<DeliveryStatus> op = deliveryStatusRepository.findById(id);

        if(op.isPresent()) {

            DeliveryStatus obj = op.get();

            if(dto.getDescription_status_delivery() != null) {
                obj.setDescription_status_delivery(dto.getDescription_status_delivery());
            }

            obj = this.deliveryStatusRepository.save(obj);
            return businessToDto(obj);

        }
        return null;
    }

    //delete by id

    public void deleteById(Long id) {
        if (this.deliveryStatusRepository.existsById(id)) {
            this.deliveryStatusRepository.deleteById(id);
        }
    }

    // Show by id

    public DeliveryStatusDTO findDeliveryStatusById(Long id) {
        Optional<DeliveryStatus> op = deliveryStatusRepository.findById(id);
        if (op.isPresent()) {
            return businessToDto(op.get());
        }
        return null;
    }

    //Convertion;

    public DeliveryStatus dtoToBusiness(DeliveryStatusDTO dto) {
        DeliveryStatus business = new DeliveryStatus();
        business.setDescription_status_delivery(dto.getDescription_status_delivery());
        return business;
    }

    public DeliveryStatusDTO businessToDto(DeliveryStatus business) {
        DeliveryStatusDTO dto = new DeliveryStatusDTO();
        dto.setId(business.getId());
        dto.setDescription_status_delivery(business.getDescription_status_delivery());
        return dto;
    }

    public List<DeliveryStatusDTO> listToDto (List<DeliveryStatus> list) {
        List<DeliveryStatusDTO> listDto = new ArrayList<>();
        for (DeliveryStatus l : list) {
            listDto.add(this.businessToDto(l));
        }
        return listDto;
    }

}
