package tn.esprit.pibakcend.RestController;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import tn.esprit.pibakcend.Service.ProductService;
import tn.esprit.pibakcend.dto.CreateProductDto;
import tn.esprit.pibakcend.dto.ProductViewDto;
import tn.esprit.pibakcend.entities.Product;
import tn.esprit.pibakcend.mapper.ProductMapper;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/product")
public class ProductController {
    @Autowired
    private ProductService productService;
    @Autowired
    private ProductMapper productMapper;

    @GetMapping("/")
    public ResponseEntity<List<ProductViewDto>> getProducts() {
        List<Product> products = productService.listProducts();
        return new ResponseEntity<>(productMapper.toDto(products), HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<ProductViewDto> getProductById(@PathVariable("id") Integer id) {
        Product product = productService.getProductById(id);
        return new ResponseEntity<>(productMapper.toDto(product), HttpStatus.OK);
    }

    @PostMapping("/add")
    public ResponseEntity<ProductViewDto> addProduct(@RequestBody CreateProductDto productDto) {
        Product product = productService.addProduct(productDto);
        return new ResponseEntity<>(productMapper.toDto(product), HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<ProductViewDto> updateProduct(@PathVariable("id") Integer id, @RequestBody @Valid CreateProductDto productDto) {
        Product product = productService.updateProduct(id, productDto);
        return new ResponseEntity<>(productMapper.toDto(product), HttpStatus.CREATED);
    }


}
