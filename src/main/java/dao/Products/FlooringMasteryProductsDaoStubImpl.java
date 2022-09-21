/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao.Products;

import dao.FlooringMasteryPersistenceException;
import dto.Product;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Collection;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
public class FlooringMasteryProductsDaoStubImpl implements FlooringMasteryProductsDao {

    private final Map<String, Product> products = new LinkedHashMap<>();
    
    public FlooringMasteryProductsDaoStubImpl() {
        Product product1 = new Product();
        product1.setProductType("Laminate");
        product1.setCostPerSquareFoot(new BigDecimal(1.75));
        product1.setLaborCostPerSquareFoot(new BigDecimal(2.10));
        
        products.put(product1.getProductType(), product1);
    }

    public int loadCount = 0;

    @Override
    public void loadProductData() {
        loadCount++;
    }

    public int saveCount = 0;

    @Override
    public void writeProductData() {
        saveCount++;
    }

    @Override
    public void addProduct(Product newProduct) {
    }

    @Override
    public Product getProduct(String productType) {
        return this.products.get(productType);
    }

    @Override
    public List<Product> getAllProducts() {
        Collection<Product> allProducts = this.products.values();
        return new ArrayList<>(allProducts);
    }

    @Override
    public void editProduct(String productType,
            Product productToEdit) {
    }

    @Override
    public void removeProduct(Product removedProduct) {
    }

}
