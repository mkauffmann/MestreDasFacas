package br.com.rd.MestreDasFacas.service;

import br.com.rd.MestreDasFacas.model.dto.ProductPriceDTO;
import br.com.rd.MestreDasFacas.model.entity.ProductPrice;
import br.com.rd.MestreDasFacas.repository.contract.ProductPriceRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class ProductPriceService {

    @Autowired
    ProductPriceRepository productPriceRepository;

    // Método de adição:

    public ProductPriceDTO addProductPrice(ProductPriceDTO dto) {
        ProductPrice newProductPrice = this.dtoToBusiness(dto);
        newProductPrice = productPriceRepository.save(newProductPrice);
        return businessToDto(newProductPrice);
    }

    // Método de listar:

    public List<ProductPriceDTO> showListProductPrice() {
        List<ProductPrice> allList = productPriceRepository.findAll();
        return listToDto(allList);
    }

    // Método de atualizar por id:

    public ProductPriceDTO updateById(ProductPriceDTO dto, Long id) {

        Optional<ProductPrice> op = productPriceRepository.findById(id);

        if(op.isPresent()) {

            ProductPrice obj = op.get();

            if (dto.getValue() != null) {
                obj.setValue(dto.getValue());
            }

            obj = this.productPriceRepository.save(obj);
            return businessToDto(obj);

        }

        return null;

    }

    // Método de deletar:

    public void deleteById(Long id) {
        if (this.productPriceRepository.existsById(id)) {
            this.productPriceRepository.deleteById(id);
        }
    }

    // Métodos de conversão:

    public ProductPrice dtoToBusiness(ProductPriceDTO dto) {
        ProductPrice business = new ProductPrice();
        business.setValue(dto.getValue());
        return business;
    }

    public ProductPriceDTO businessToDto(ProductPrice business) {
        ProductPriceDTO dto = new ProductPriceDTO();
        dto.setValue(business.getValue());
        dto.setId(business.getId());
        return dto;
    }

    public List<ProductPriceDTO> listToDto (List<ProductPrice> list) {
        List<ProductPriceDTO> listDto = new ArrayList<>();
        for (ProductPrice p: list) {
            listDto.add(this.businessToDto(p));
        }
        return listDto;
    }





}
