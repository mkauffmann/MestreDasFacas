package br.com.rd.MestreDasFacas.service;

import br.com.rd.MestreDasFacas.model.dto.InventoryDTO;
import br.com.rd.MestreDasFacas.model.entity.Inventory;
import br.com.rd.MestreDasFacas.model.entity.Product;
import br.com.rd.MestreDasFacas.repository.contract.InventoryRepository;
import br.com.rd.MestreDasFacas.service.conversion.DtoConversion;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class InventoryService {

    @Autowired
    InventoryRepository inventoryRepository;

    @Autowired
    DtoConversion conversion;

    public InventoryDTO addInventory(InventoryDTO dto) {
        Inventory newInventory = conversion.inventoryDtoToBusiness(dto);
        newInventory = inventoryRepository.save(newInventory);
        return conversion.inventoryBusinessToDto(newInventory);
    }

    public List<InventoryDTO> showList() {
        List<Inventory> list = this.inventoryRepository.findAll();
        return conversion.inventoryListToDto(list);
    }

    public InventoryDTO findById(Long id) {
        Optional<Inventory> op = inventoryRepository.findById(id);

        if(op.isPresent()) {
            return conversion.inventoryBusinessToDto(op.get());
        }

        return null;
    }

    public InventoryDTO update(InventoryDTO dto, Long id) {
        Optional<Inventory> op = inventoryRepository.findById(id);

        if(op.isPresent()) {

            Inventory obj = op.get();

            if(dto.getQuantityInventory() != null) {
                obj.setQuantityInventory(dto.getQuantityInventory());
            }

            if(dto.getProduct() != null) {
                Product newProduct = conversion.productDtoToBusiness(dto.getProduct());
                obj.setProduct(newProduct);
            }

            inventoryRepository.save(obj);
            return conversion.inventoryBusinessToDto(obj);

        }

        return null;
    }

    public void deleteById(Long id) {
        if(inventoryRepository.existsById(id)) {
            inventoryRepository.deleteById(id);
        }
    }

}
