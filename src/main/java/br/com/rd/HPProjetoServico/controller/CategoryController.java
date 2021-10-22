package br.com.rd.HPProjetoServico.controller;


import br.com.rd.HPProjetoServico.model.dto.CategoryDTO;
import br.com.rd.HPProjetoServico.service.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/categoria")
public class CategoryController {
    @Autowired
    CategoryService categoryService;
    @PostMapping
    public CategoryDTO create(@RequestBody CategoryDTO category){
        return categoryService.createCategory(category);
    }

    @GetMapping
    public List<CategoryDTO> findAll(){
        return categoryService.findAllCategory();
    }

    @PutMapping("/{id}")
    public CategoryDTO updateById(@RequestBody CategoryDTO dto, @PathVariable Long id){
        return categoryService.updateById(dto, id);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(code = HttpStatus.NO_CONTENT)
    public void deleteById(@PathVariable("id")Long id){
        categoryService.deleteById(id);
    }

    @GetMapping("/{id}")
    public CategoryDTO searchById(@PathVariable("id") Long id) {
        return categoryService.searchCategoryById(id);
    }
}
