package tn.esprit.pibakcend.Service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import tn.esprit.pibakcend.configuration.exceptions.ProductNotExistException;
import tn.esprit.pibakcend.Repository.*;

import tn.esprit.pibakcend.dto.CreateProductDto;
import tn.esprit.pibakcend.entities.*;


import java.util.List;
import java.util.Optional;

@Service
public class ProductService {
    @Autowired
    private ProductRepository productRepository;
@Autowired
private CategoryRepository categoryRepository;
    public List<Product> listProducts() {
        return productRepository.findAll();
    }
    public Product addProduct(CreateProductDto productDto) {
        Optional<Category> category = categoryRepository.findById(productDto.getCategoryId());
        // throw erorr if not exist
        Product product= Product.builder()
                .price(productDto.getPrice())
                .description(productDto.getDescription())
                .name(productDto.getName())
                .imageURL(productDto.getImageURL())
                .category(category.get())
                .build();
        return productRepository.save(product);
    }

    public Product updateProduct(Integer productId, CreateProductDto updatedProduct) throws ProductNotExistException {
        Optional<Product> optionalProduct = productRepository.findById(productId);
        if (!optionalProduct.isPresent()) {
            throw new ProductNotExistException("Product id is invalid " + productId);
        }
        Optional<Category> category = categoryRepository.findById(updatedProduct.getCategoryId());
        Product product = optionalProduct.get();
        product.setName(updatedProduct.getName());
        product.setImageURL(updatedProduct.getImageURL());
        product.setDescription(updatedProduct.getDescription());
        product.setPrice(updatedProduct.getPrice());
        product.setCategory(category.get());
      return  productRepository.save(product);
    }


    public Product getProductById(Integer productId) throws ProductNotExistException {
        Optional<Product> optionalProduct = productRepository.findById(productId);
        if (!optionalProduct.isPresent()) {
            throw new ProductNotExistException("Product id is invalid " + productId);
        }
        return optionalProduct.get();
    }
}
