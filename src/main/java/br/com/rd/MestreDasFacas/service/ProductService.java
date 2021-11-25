package br.com.rd.MestreDasFacas.service;

import br.com.rd.MestreDasFacas.model.dto.*;

import br.com.rd.MestreDasFacas.model.entity.Category;
import br.com.rd.MestreDasFacas.model.entity.Brand;
//import br.com.rd.MestreDasFacas.model.entity.CableColor;
import br.com.rd.MestreDasFacas.model.entity.Product;
import br.com.rd.MestreDasFacas.model.entity.ProductPrice;
import br.com.rd.MestreDasFacas.repository.contract.BrandRepository;
//import br.com.rd.MestreDasFacas.repository.contract.CableColorRepository;
import br.com.rd.MestreDasFacas.repository.contract.ProductRepository;

import br.com.rd.MestreDasFacas.repository.contract.CategoryRepository;
import br.com.rd.MestreDasFacas.repository.contract.ProductPriceRepository;
import br.com.rd.MestreDasFacas.service.conversion.DtoConversion;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class ProductService {

    @Autowired
    ProductRepository productRepository;

    @Autowired
    BrandRepository brandRepository;

//    @Autowired
//    CableColorRepository cableColorRepository;

    @Autowired
    CategoryRepository categoryRepository;

    @Autowired
    ProductPriceRepository productPriceRepository;

    @Autowired
    DtoConversion dtoConversion;


    // Métodos de adição:

    public ProductDTO addProduct(ProductDTO dto) {
        Product newProduct = dtoConversion.productDtoToBusiness(dto);
        newProduct = productRepository.save(newProduct);
        return dtoConversion.productBusinessToDto(newProduct);
    }

    public List<ProductDTO> showList() {
        List<Product> list = productRepository.findAll();
        return dtoConversion.productListToDto(list);
    }

    public ProductDTO findById(Long id) {
        Optional<Product> op = productRepository.findById(id);

        if(op.isPresent()) {
            return dtoConversion.productBusinessToDto(op.get());
        }

        return null;
    }

    public ProductDTO update(ProductDTO dto, Long id) {
        Optional<Product> op = productRepository.findById(id);

        if(op.isPresent()) {

            Product obj = op.get();

            if(dto.getProductName() != null) {
                obj.setProductName(dto.getProductName());
            }

            if(dto.getDescriptionProduct() != null) {
                obj.setDescriptionProduct(dto.getDescriptionProduct());
            }

            if(dto.getHeight() != null) {
                obj.setHeight(dto.getHeight());
            }

            if(dto.getWidth() != null) {
                obj.setWidth(dto.getWidth());
            }

            if(dto.getLength() != null) {
                obj.setLength(dto.getLength());
            }

            if(dto.getWeight() != null) {
                obj.setWeight(dto.getWeight());
            }

            if(dto.getBrand() != null) {
                Brand newBrand = dtoConversion.brandDtoToBusiness(dto.getBrand());
                obj.setBrand(newBrand);
            }

//            if(dto.getCableColor() != null) {
//                CableColor newCable = dtoConversion.cableColorDtoToBusiness(dto.getCableColor());
//                obj.setCableColor(newCable);
//            }

            if(dto.getCategory() != null) {
                Category newCategory = dtoConversion.categoryDtoToBusiness(dto.getCategory());
                obj.setCategory(newCategory);
            }

            productRepository.save(obj);
            return dtoConversion.productBusinessToDto(obj);

        }
        return null;
    }

    public void deleteById(Long id) {
        if(productRepository.existsById(id)) {
            productRepository.deleteById(id);
        }
    }

    // Método para barra de busca:

    public List<ProductDTO> listProductsSearch(String search) {
        List<Product> productList = this.productRepository.myFindAllBySearch(search);
        return dtoConversion.productListToDto(productList);
    }

    // Método para listar produtos pelo id de categoria:

    public List<ProductDTO> listProductByCategory(Long id) {
        List<Product> productList = this.productRepository.myFindAllByIdCategory(id);
        return dtoConversion.productListToDto(productList);
    }

    // Método para ordenar produtos pelo preço e categoria decrescente:

    public List<ProductDTO> listProductsByPriceDescCatg(Long id) {
        List<Product> productList = this.productRepository.myFindByPriceDescCatg(id);
        return dtoConversion.productListToDto(productList);
    }

    // Método para ordenar produtos pelo preço e categoria crescente:

    public List<ProductDTO> listProductsByPriceAscCatg(Long id) {
        List<Product> productList = this.productRepository.myFindByPriceAscCatg(id);
        return dtoConversion.productListToDto(productList);
    }

    // Método para ordenar produtos pelo preço e pesquisa decrescente:

    public List<ProductDTO> listProductsByPriceDescSearch(String search) {
        List<Product> productList = this.productRepository.myFindByPriceDescSearch(search);
        return dtoConversion.productListToDto(productList);
    }

    // Método para ordenar produtos pelo preço e pesquisa crescente:

    public List<ProductDTO> listProductsByPriceAscSearch(String search) {
        List<Product> productList = this.productRepository.myFindByPriceAscSearch(search);
        return dtoConversion.productListToDto(productList);
    }

    // Método para ordenar produtos pelo preço decrescente:

    public List<ProductDTO> listProductsByPriceDesc() {
        List<Product> productList = this.productRepository.myFindByPriceDesc();
        return dtoConversion.productListToDto(productList);
    }

    // Método para ordenar produtos pelo preço decrescente:

    public List<ProductDTO> listProductsByPriceAsc() {
        List<Product> productList = this.productRepository.myFindByPriceAsc();
        return dtoConversion.productListToDto(productList);
    }

    // Método para listar produtos destaque:

    public List<ProductDTO> listProductsByFeatured() {
        List<Product> productList = this.productRepository.myFindAllByFeatured();
        return dtoConversion.productListToDto(productList);
    }

    // Método para listar produtos novidade:

    public List<ProductDTO> listProductsByNews() {
        List<Product> productList = this.productRepository.myFindAllByNews();
        return dtoConversion.productListToDto(productList);
    }

    ////////////////////////////////////////////////////////


    // Métodos de conversão:

    // Métodos de conversão para Brand:

//    private Brand brandDtoToBusiness(BrandDTO dto) {
//        Brand business = new Brand();
//
//        if(dto.getId() != null) {
//            Long brandId = dto.getId();
//            if (brandRepository.existsById(brandId)) {
//                business = brandRepository.getById(brandId);
//            } else {
//                business.setBrand(dto.getBrand());
//            }
//        } else {
//            business.setBrand(dto.getBrand());
//        }
//        return business;
//    }
//
//    private BrandDTO brandBusinessToDto(Brand business) {
//
//        BrandDTO dto = new BrandDTO();
//
//        dto.setId(business.getId());
//        dto.setBrand(business.getBrand());
//
//        return dto;
//
//    }
//
//    // Métodos de conversão para CableColor:
//
//    private CableColor cableColorDtoToBusiness(CableColorDTO dto) {
//
//        CableColor business = new CableColor();
//
//        if(dto.getId() != null) {
//
//            Long cableId = dto.getId();
//            if(cableColorRepository.existsById(cableId)){
//                business = cableColorRepository.getById(cableId);
//            } else {
//                business.setCableColor(dto.getCableColor());
//            }
//        } else {
//            business.setCableColor(dto.getCableColor());
//        }
//
//        return business;
//
//    }
//
//    private CableColorDTO cableColorBusinessToDto(CableColor business) {
//
//        CableColorDTO dto = new CableColorDTO();
//
//        dto.setId(business.getId());
//        dto.setCableColor(business.getCableColor());
//
//        return dto;
//    }
//
//    // Métodos de conversão para Category:
//
//    private Category categoryDtoToBusiness(CategoryDTO dto) {
//        Category business = new Category();
//
//        if(dto.getId() != null) {
//            Long ctgId = dto.getId();
//
//            if (categoryRepository.existsById(ctgId)) {
//                business = categoryRepository.getById(ctgId);
//            } else {
//                business.setDescription_category(dto.getDescription_category());
//            }
//        } else {
//            business.setDescription_category(dto.getDescription_category());
//        }
//        return business;
//    }
//
//    private CategoryDTO categoryBusinessToDto(Category business) {
//
//        CategoryDTO dto = new CategoryDTO();
//
//        dto.setId(business.getId());
//        dto.setDescription_category(business.getDescription_category());
//
//        return dto;
//
//    }
//
//    // Métodos de conversão para Preço do Produto:
//
//    private ProductPrice productPriceDtoToBusiness(ProductPriceDTO dto) {
//        ProductPrice business = new ProductPrice();
//
//        if(dto.getId() != null) {
//            Long ppId = dto.getId();
//
//            if (productPriceRepository.existsById(ppId)) {
//                business = productPriceRepository.getById(ppId);
//            } else {
//                business.setValue(dto.getValue());
//            }
//        } else {
//            business.setValue(dto.getValue());
//        }
//        return business;
//    }
//
//    private ProductPriceDTO productPriceBusinessToDto(ProductPrice business) {
//
//        ProductPriceDTO dto = new ProductPriceDTO();
//
//        dto.setId(business.getId());
//        dto.setValue(business.getValue());
//
//        return dto;
//    }
//
//    // Métodos de conversão para Produto:
//
//    private Product dtoToBusiness(ProductDTO dto) {
//        Product business = new Product();
//        Brand brand = brandDtoToBusiness(dto.getBrand());
//        CableColor cableColor = cableColorDtoToBusiness(dto.getCableColor());
//        Category category = categoryDtoToBusiness(dto.getCategory());
//        ProductPrice pp = productPriceDtoToBusiness(dto.getProductPrice());
//
//        business.setProductName(dto.getProductName());
//        business.setDescriptionProduct(dto.getDescriptionProduct());
//        business.setWeight(dto.getWeight());
//        business.setLength(dto.getLength());
//        business.setHeight(dto.getHeight());
//        business.setWidth(dto.getWidth());
//        business.setBrand(brand);
//        business.setCableColor(cableColor);
//        business.setCategory(category);
//        business.setProductPrice(pp);
//
//        return business;
//    }
//
//    private ProductDTO businessToDto(Product business) {
//
//        ProductDTO dto = new ProductDTO();
//        BrandDTO brandDto = brandBusinessToDto(business.getBrand());
//        CableColorDTO cableDto = cableColorBusinessToDto(business.getCableColor());
//        CategoryDTO categoryDto = categoryBusinessToDto(business.getCategory());
//        ProductPriceDTO pdDto = productPriceBusinessToDto(business.getProductPrice());
//
//        dto.setId(business.getId());
//        dto.setProductName(business.getProductName());
//        dto.setDescriptionProduct(business.getDescriptionProduct());
//        dto.setHeight(business.getHeight());
//        dto.setLength(business.getLength());
//        dto.setWeight(business.getWeight());
//        dto.setWidth(business.getWidth());
//        dto.setBrand(brandDto);
//        dto.setCableColor(cableDto);
//        dto.setCategory(categoryDto);
//        dto.setProductPrice(pdDto);
//
//        return dto;
//    }
//
//    private List<ProductDTO> listToDto(List<Product> list) {
//        List<ProductDTO> listDto = new ArrayList<>();
//
//        for(Product p : list) {
//            listDto.add(businessToDto(p));
//        }
//
//        return listDto;
//    }

//    // MÉTODOS DE CONVERSÃO 2:
//
//    private BrandDTO2 brandBusinessToDto2(Brand business) {
//
//        BrandDTO2 dto = new BrandDTO2();
//
//        dto.setBrand(business.getBrand());
//
//        return dto;
//    }
//
//    private CableColorDTO2 cableColorBusinessToDto2(CableColor business) {
//
//        CableColorDTO2 dto = new CableColorDTO2();
//
//        dto.setCableColor(business.getCableColor());
//
//        return dto;
//    }
//
//    private CategoryDTO2 categoryBusinessToDto2(Category business) {
//
//        CategoryDTO2 dto = new CategoryDTO2();
//
//        dto.setDescription_category(business.getDescription_category());
//
//        return dto;
//    }
//
//    private ProductPriceDTO2 productPriceBusinessToDto2(ProductPrice business) {
//
//        ProductPriceDTO2 dto = new ProductPriceDTO2();
//
//        dto.setValue(business.getValue());
//
//        return dto;
//
//    }
//
//    private ProductDTO2 businessToDto2(Product business) {
//
//        ProductDTO2 dto = new ProductDTO2();
//        BrandDTO2 brandDto = brandBusinessToDto2(business.getBrand());
//        CableColorDTO2 cableDto = cableColorBusinessToDto2(business.getCableColor());
//        CategoryDTO2 categoryDTO2 = categoryBusinessToDto2(business.getCategory());
//        ProductPriceDTO2 ppdto = productPriceBusinessToDto2(business.getProductPrice());
//
//        dto.setProductName(business.getProductName());
//        dto.setDescriptionProduct(business.getDescriptionProduct());
//        dto.setHeight(business.getHeight());
//        dto.setLength(business.getLength());
//        dto.setWeight(business.getWeight());
//        dto.setWidth(business.getWidth());
//        dto.setBrand(brandDto);
//        dto.setCableColor(cableDto);
//        dto.setCategory(categoryDTO2);
//        dto.setProductPrice(ppdto);
//
//        return dto;
//    }
//
//    private List<ProductDTO2> listToDto2(List<Product> list) {
//        List<ProductDTO2> listDto = new ArrayList<>();
//
//        for(Product p : list) {
//            listDto.add(businessToDto2(p));
//        }
//
//        return listDto;
//    }

}
