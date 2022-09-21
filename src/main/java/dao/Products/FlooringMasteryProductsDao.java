package dao.Products;

import dao.FlooringMasteryPersistenceException;
import dto.Product;

import java.util.List;

public interface FlooringMasteryProductsDao {

    void loadProductData() throws FlooringMasteryPersistenceException;
    
    void writeProductData() throws FlooringMasteryPersistenceException;

    void addProduct(Product newProduct);
    
    Product getProduct(String productType);

    List<Product> getAllProducts();
    
    void editProduct(String productType, Product productToEdit);
    
    void removeProduct(Product removedProduct);

}
