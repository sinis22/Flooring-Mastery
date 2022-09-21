package dao;

import dao.Products.FlooringMasteryProductsDao;
import dao.Products.FlooringMasteryProductsDaoFileImpl;
import dto.Product;
import java.math.BigDecimal;
import java.util.List;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Assert;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;


public class FlooringMasteryProductsDaoTest {

    private final FlooringMasteryProductsDao productsDao = new FlooringMasteryProductsDaoFileImpl();

    public FlooringMasteryProductsDaoTest() {
    }

    @BeforeClass
    public static void setUpClass() {
    }

    @AfterClass
    public static void tearDownClass() {
    }

    @Before
    public void setUp() {
        List<Product> productList = productsDao.getAllProducts();
        for (Product product : productList) {
            productsDao.removeProduct(product);
        }
    }

    @After
    public void tearDown() {
    }

    @Test
    public void testAddGetProduct() {
        Product product1 = new Product();
        product1.setProductType("Ceramic");
        product1.setCostPerSquareFoot(new BigDecimal(1.50));
        product1.setLaborCostPerSquareFoot(new BigDecimal(2.30));

        productsDao.addProduct(product1);

        Product fromProductsDao = productsDao.getProduct(product1.getProductType());
        Assert.assertNotNull("Get product1 should not return null", fromProductsDao);
        Assert.assertEquals("product1 should match what is returned.", product1, fromProductsDao);
    }

    /**
     * Test of getAllProducts method, of class FlooringMasteryProductsDao.
     */
    @Test
    public void testGetAllProducts() {
        Product product1 = new Product();
        product1.setProductType("Ceramic");
        product1.setCostPerSquareFoot(new BigDecimal(1.50));
        product1.setLaborCostPerSquareFoot(new BigDecimal(2.30));

        productsDao.addProduct(product1);

        Product product2 = new Product();
        product2.setProductType("Stone");
        product2.setCostPerSquareFoot(new BigDecimal("25.00"));
        product2.setLaborCostPerSquareFoot(new BigDecimal("31.00"));

        productsDao.addProduct(product2);

        Assert.assertEquals("There should be 2 products.", 2, productsDao.getAllProducts().size());
        Assert.assertTrue("Should contain product1.", productsDao.getAllProducts().contains(product1));
        Assert.assertTrue("Should contain product2", productsDao.getAllProducts().contains(product2));
        Assert.assertNotNull("Should not be null. 2 products", productsDao.getAllProducts());
        Assert.assertFalse("Should not be empty.", productsDao.getAllProducts().isEmpty());
    }

    /**
     * Test of removeProduct method, of class FlooringMasteryProductsDao.
     */
    @Test
    public void testRemoveProduct() {
        Product product1 = new Product();
        product1.setProductType("Ceramic");
        product1.setCostPerSquareFoot(new BigDecimal(1.50));
        product1.setLaborCostPerSquareFoot(new BigDecimal(2.30));

        productsDao.addProduct(product1);

        Product product2 = new Product();
        product2.setProductType("Stone");
        product2.setCostPerSquareFoot(new BigDecimal("25.00"));
        product2.setLaborCostPerSquareFoot(new BigDecimal("31.00"));

        productsDao.addProduct(product2);

        productsDao.removeProduct(product1);
        Assert.assertEquals("Should only be 1 product since 1 was "
                + "removed.", 1, productsDao.getAllProducts().size());
        Assert.assertNull("Product 1 should no longer be in there after "
                + "removal.", productsDao.getProduct(product1.getProductType()));

        productsDao.removeProduct(product2);
        Assert.assertEquals("Should be zero products since both "
                + "were removed.", 0, productsDao.getAllProducts().size());
        Assert.assertNull("When trying to get an product that is not there, "
                + "should return null.", productsDao.getProduct(product2.getProductType()));
    }

    /**
     * Test of editProduct method, of class FlooringMasteryProductsDao.
     */
    @Test
    public void testEditProduct() {
        Product product1 = new Product();
        product1.setProductType("Ceramic");
        product1.setCostPerSquareFoot(new BigDecimal(1.50));
        product1.setLaborCostPerSquareFoot(new BigDecimal(2.30));

        productsDao.addProduct(product1);

        Product product2 = new Product();
        product2.setProductType("Stone");
        product2.setCostPerSquareFoot(new BigDecimal("25.00"));
        product2.setLaborCostPerSquareFoot(new BigDecimal("31.00"));

        productsDao.editProduct(product1.getProductType(), product2);

        Product fromProductsDao = productsDao.getProduct(product2.getProductType());

        Assert.assertEquals("getAllProducts should return 1 since the "
                + "product was edited and another was not "
                + "added.", 1, productsDao.getAllProducts().size());
        Assert.assertEquals("Product2 should match what is returned "
                + "since Product1 was edited to be replaced by product2.", product2, fromProductsDao);
        Assert.assertEquals("Stone should be the new product number of the product returned "
                + "", "Stone", fromProductsDao.getProductType());
        Assert.assertNotNull("There should be 1 product , not null.", fromProductsDao);
    }

}
