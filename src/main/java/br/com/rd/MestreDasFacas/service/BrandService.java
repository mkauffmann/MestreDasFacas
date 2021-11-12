package br.com.rd.MestreDasFacas.service;

import br.com.rd.MestreDasFacas.model.dto.BrandDTO;
import br.com.rd.MestreDasFacas.model.entity.Brand;
import br.com.rd.MestreDasFacas.repository.contract.BrandRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class BrandService {

    @Autowired
    BrandRepository brandRepository;

    // Método de adição:

    public BrandDTO addBrand(BrandDTO dto) {
        Brand newMarca = this.dtoToBusiness(dto);
        newMarca = this.brandRepository.save(newMarca);
        return businessToDto(newMarca);
    }

    // Método de listar:

    public List<BrandDTO> showListBrand() {
        List<Brand> allList = brandRepository.findAll();
        return listToDto(allList);
    }

    // Método de encontrar por id:

    public BrandDTO findBrandById(Long id) {
        Optional<Brand> op = this.brandRepository.findById(id);
        if (op.isPresent()) {
            return businessToDto(op.get());
        }
        return null;
    }

    public BrandDTO updateById(BrandDTO dto, Long id) {
        Optional<Brand> op = this.brandRepository.findById(id);

        if(op.isPresent()) {

            Brand obj = op.get();

            if(dto.getBrand() != null) {
                obj.setBrand(dto.getBrand());
            }

            obj = this.brandRepository.save(obj);
            return businessToDto(obj);

        }
        return null;
    }

    public void deleteById(Long id) {
        if (this.brandRepository.existsById(id)) {
            this.brandRepository.deleteById(id);
        }
    }

    // Métodos de conversão:

    public Brand dtoToBusiness(BrandDTO dto) {
        Brand business = new Brand();
        business.setBrand(dto.getBrand());
        return business;
    }

    public BrandDTO businessToDto(Brand business) {
        BrandDTO dto = new BrandDTO();
        dto.setId(business.getId());
        dto.setBrand(business.getBrand());
        return dto;
    }

    public List<BrandDTO> listToDto (List<Brand> list) {
        List<BrandDTO> listDto = new ArrayList<>();
        for (Brand b : list) {
            listDto.add(this.businessToDto(b));
        }
        return listDto;
    }

}
