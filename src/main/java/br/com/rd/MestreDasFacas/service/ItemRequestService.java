package br.com.rd.MestreDasFacas.service;


import br.com.rd.MestreDasFacas.model.dto.ItemRequestDTO;
import br.com.rd.MestreDasFacas.model.dto.ProductDTO;
import br.com.rd.MestreDasFacas.model.entity.ItemRequest;
import br.com.rd.MestreDasFacas.model.entity.Product;
import br.com.rd.MestreDasFacas.repository.contract.InventoryRepository;
import br.com.rd.MestreDasFacas.repository.contract.ItemRequestRepository;
import br.com.rd.MestreDasFacas.service.conversion.DtoConversion;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ItemRequestService {

    @Autowired
    DtoConversion conversion;

    @Autowired
    ItemRequestRepository itemRequestRepository;

//    @Autowired
//    InventoryRepository inventoryRepository;

    public ItemRequestDTO addItem(ItemRequestDTO dto) {
        ItemRequest newItemRequest = conversion.itemRequestDtoToBusiness(dto);
        newItemRequest = itemRequestRepository.save(newItemRequest);
//        inventoryRepository.myInventoryUpdate(newItemRequest.getProduct().getId());
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