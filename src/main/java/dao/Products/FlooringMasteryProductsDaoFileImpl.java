package dao.Products;

import dao.FlooringMasteryPersistenceException;
import dto.Product;
import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Collection;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Scanner;


public class FlooringMasteryProductsDaoFileImpl implements FlooringMasteryProductsDao {

    public final String DELIMITER = ",";
    public final String DATA_FILE = "src/main/Data/Products/ProductType.txt";

    private final Map<String, Product> products = new LinkedHashMap<>();

    @Override
    public void loadProductData() throws FlooringMasteryPersistenceException {
        Scanner textScanner;

        try {

            FileReader fileReader = new FileReader(DATA_FILE);
            BufferedReader bufferedReader = new BufferedReader(fileReader);
            textScanner = new Scanner(bufferedReader);

            while (textScanner.hasNextLine()) {

                new Product();
                Product newProduct;

                String currentLine = textScanner.nextLine();
                String[] currentTokens = currentLine.split(DELIMITER);

                newProduct = unmarshallProduct(currentTokens);
                this.addProduct(newProduct);

            }
            textScanner.close();

        } catch (FileNotFoundException fileNotThere) {
            throw new FlooringMasteryPersistenceException(DATA_FILE + " not found. -_-");
        }

    }

    private Product unmarshallProduct(String[] currentTokens) throws FlooringMasteryPersistenceException {

        try {

            Product newProduct = new Product();
            newProduct.setProductType(currentTokens[0]);
            newProduct.setCostPerSquareFoot(new BigDecimal(currentTokens[1]));
            newProduct.setLaborCostPerSquareFoot(new BigDecimal(currentTokens[2]));

            return newProduct;

        } catch (NumberFormatException e) {
            throw new FlooringMasteryPersistenceException("Bad data in file.");
        }
    }

    @Override
    public void addProduct(Product newProduct) {
        this.products.put(newProduct.getProductType(), newProduct);
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
    public void writeProductData() throws FlooringMasteryPersistenceException {
        try {
            PrintWriter writer = new PrintWriter(new FileWriter(DATA_FILE));
            Collection<Product> allProducts = this.products.values();
            for (Product singleProduct : allProducts) {
                String productAsString = this.marshallProduct(singleProduct);
                writer.println(productAsString);
                writer.flush();
            }
            writer.close();
        } catch (IOException e) {
            throw new FlooringMasteryPersistenceException(
                    "Could not save data.", e);
        }

    }

    private String marshallProduct(Product singleProduct) {
        return singleProduct.getProductType() + DELIMITER
                + singleProduct.getCostPerSquareFoot() + DELIMITER
                + singleProduct.getLaborCostPerSquareFoot();
    }

    @Override
    public void editProduct(String productType,
            Product productToEdit) {
        if (productType.equals(productToEdit.getProductType())) {
            this.products.replace(productType, productToEdit);
        } else {
            this.products.remove(productType);
            this.products.put(productToEdit.getProductType(), productToEdit);
        }
    }

    @Override
    public void removeProduct(Product removedProduct) {
        this.products.remove(removedProduct.getProductType());
    }

}
