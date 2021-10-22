package br.com.rd.HPProjetoServico.service;

import br.com.rd.HPProjetoServico.model.dto.CategoryDTO;
import br.com.rd.HPProjetoServico.model.enity.Category;
import br.com.rd.HPProjetoServico.repository.contract.CategoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class CategoryService {
    @Autowired
    CategoryRepository categoryRepository;


    public CategoryDTO createCategory(CategoryDTO category){
        Category newCategory = this.dtoToBusiness(category);
        newCategory = categoryRepository.save(newCategory);
        return this.businessToDto(newCategory);
    }

    public List<CategoryDTO> findAllCategory() {
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

    public void deleteById(Long id){
        if(categoryRepository.existsById(id)){
            categoryRepository.deleteById(id);
        }
    }

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
        dto.setId_categoria(business.getId_categoria());
        dto.setDescription_category(business.getDescription_category());
        return dto;
    }
}
