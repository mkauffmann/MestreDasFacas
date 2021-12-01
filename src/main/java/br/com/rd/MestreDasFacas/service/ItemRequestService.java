package br.com.rd.MestreDasFacas.service;


import br.com.rd.MestreDasFacas.enums.StatusEmail;
import br.com.rd.MestreDasFacas.model.dto.ItemRequestDTO;
import br.com.rd.MestreDasFacas.model.dto.ProductDTO;
import br.com.rd.MestreDasFacas.model.entity.Customer;
import br.com.rd.MestreDasFacas.model.entity.EmailModel;
import br.com.rd.MestreDasFacas.model.entity.ItemRequest;
import br.com.rd.MestreDasFacas.model.entity.Product;
import br.com.rd.MestreDasFacas.repository.contract.EmailRepository;
import br.com.rd.MestreDasFacas.repository.contract.InventoryRepository;
import br.com.rd.MestreDasFacas.repository.contract.ItemRequestRepository;
import br.com.rd.MestreDasFacas.service.conversion.DtoConversion;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.MailException;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Service
public class ItemRequestService {

    @Autowired
    DtoConversion conversion;

    @Autowired
    ItemRequestRepository itemRequestRepository;


    public ItemRequestDTO addItem(ItemRequestDTO dto) {
        ItemRequest newItemRequest = conversion.itemRequestDtoToBusiness(dto);

        newItemRequest = itemRequestRepository.save(newItemRequest);


        return conversion.itemRequestbusinessToDto(newItemRequest);
    }

    public List<ItemRequestDTO> showList() {
        List<ItemRequest> list = itemRequestRepository.findAll();
        return conversion.itemRequestListToDto(list);
    }

    public ItemRequestDTO update(ItemRequestDTO dto, Long id){

        Optional<ItemRequest> op = itemRequestRepository.findById(id);

        if(op.isPresent()){
            ItemRequest obj = op.get();

            if(dto.getQuantity() !=  null){
                obj.setQuantity(dto.getQuantity());

            }
            if(dto.getTotal_value() != null){
                obj.setTotal_value(dto.getTotal_value());
            }
            if(dto.getProduct() != null){
                Product product = conversion.productDtoToBusiness(dto.getProduct());
                obj.setProduct(product);
            }

            itemRequestRepository.save(obj);

            return conversion.itemRequestbusinessToDto(obj);
        }

        return null;

    }


    public void deleteById (Long id){
        if(itemRequestRepository.existsById(id)){
            itemRequestRepository.deleteById(id);



        }


    }




}
