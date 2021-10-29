package br.com.rd.MestreDasFacas.service;

import br.com.rd.MestreDasFacas.model.dto.CategoryDTO;
import br.com.rd.MestreDasFacas.model.entity.Category;
import br.com.rd.MestreDasFacas.repository.contract.CategoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class CategoryService {
    @Autowired
    CategoryRepository categoryRepository;

    // Create

    public CategoryDTO createCategory(CategoryDTO category){
        Category newCategory = this.dtoToBusiness(category);
        newCategory = categoryRepository.save(newCategory);
        return this.businessToDto(newCategory);
    }

    // Show all

    public List<CategoryDTO> findAll() {
        List<Category> allList = categoryRepository.findAll();
        return this.listToDto(allList);
    }

    private List<CategoryDTO> listToDto(List<Category> list){
        List<CategoryDTO> listDto = new ArrayList<CategoryDTO>();
        for (Category v : list) {
            listDto.add(this.businessToDto(v));
        }
        return listDto;
    }

    // Update by id

    public CategoryDTO updateById(CategoryDTO dto, Long id){
        Optional<Category> op = categoryRepository.findById(id);

        if (op.isPresent()){
            Category obj = op.get();

            if(dto.getDescription_category() != null){
                obj.setDescription_category(dto.getDescription_category());
            }

            categoryRepository.save(obj);
            return  businessToDto(obj);

        }
        return null;
    }

    //delete by id

    public void deleteById(Long id){
        if(categoryRepository.existsById(id)){
            categoryRepository.deleteById(id);
        }
    }

    // Show by id

    public CategoryDTO searchCategoryById(Long id) {
        Optional<Category> op = categoryRepository.findById(id);

        if (op.isPresent()){
            return businessToDto(op.get());
        }
        return null;
    }


    public Category dtoToBusiness(CategoryDTO dto) {
        Category business = new Category();
        business.setDescription_category(dto.getDescription_category());
        return business;
    }

    public CategoryDTO businessToDto(Category business) {
        CategoryDTO dto = new CategoryDTO();
        dto.setId(business.getId());
        dto.setDescription_category(business.getDescription_category());
        return dto;
    }
}
