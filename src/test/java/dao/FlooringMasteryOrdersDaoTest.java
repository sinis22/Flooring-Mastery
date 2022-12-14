package dao;

import dao.Orders.FlooringMasteryOrdersDao;
import dao.Orders.FlooringMasteryOrdersDaoFileImpl;
import dto.Order;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;
import org.junit.Assert;
import org.junit.Test;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;

public class FlooringMasteryOrdersDaoTest {

    private final FlooringMasteryOrdersDao ordersDao = new FlooringMasteryOrdersDaoFileImpl();

    public FlooringMasteryOrdersDaoTest() {
    }

    @BeforeAll
    public static void setUpClass() {
    }

    @AfterAll
    public static void tearDownClass() {
    }

    @BeforeEach
    public void setUp() {
        List<Order> orderList = ordersDao.getAllOrders();
        for (Order order : orderList) {
            ordersDao.removeOrder(order);
        }
    }

    @AfterEach
    public void tearDown() {
    }

    @Test
    public void testAddGetOrder() {
        Order order1 = new Order();
        order1.setOrderNumber(1);
        order1.setOrderDate(LocalDate.parse("09/20/2022", DateTimeFormatter.ofPattern("MM/dd/yyyy")));
        order1.setCustomerName("Hamsini");
        order1.setState("MT");
        order1.setTaxRate(new BigDecimal("6.75"));
        order1.setProductType("Laminate");
        order1.setArea(new BigDecimal("500.00"));
        order1.setCostPerSquareFoot(new BigDecimal("1.75"));
        order1.setLaborCostPerSquareFoot(new BigDecimal("2.10"));
        order1.setMaterialCost(new BigDecimal("875.00"));
        order1.setLaborCost(new BigDecimal("1050.00"));
        order1.setTax(new BigDecimal("129.94"));
        order1.setTotal(new BigDecimal("2054.94"));

        ordersDao.addOrder(order1);

        Order fromOrdersDao = ordersDao.getOrder(order1.getOrderNumber());
        Assert.assertNotNull("Get order1 should not return null", fromOrdersDao);
        Assert.assertEquals("order1 should match what is returned.", order1, fromOrdersDao);
    }

    /**
     * Test of getAllOrders method, of class FlooringMasteryOrdersDao.
     */
    @Test
    public void testGetAllOrders() {
        Order order1 = new Order();
        order1.setOrderNumber(1);
        order1.setOrderDate(LocalDate.parse("09/20/2022", DateTimeFormatter.ofPattern("MM/dd/yyyy")));
        order1.setCustomerName("Hamsini");
        order1.setState("MT");
        order1.setTaxRate(new BigDecimal("6.75"));
        order1.setProductType("Laminate");
        order1.setArea(new BigDecimal("500.00"));
        order1.setCostPerSquareFoot(new BigDecimal("1.75"));
        order1.setLaborCostPerSquareFoot(new BigDecimal("2.10"));
        order1.setMaterialCost(new BigDecimal("875.00"));
        order1.setLaborCost(new BigDecimal("1050.00"));
        order1.setTax(new BigDecimal("129.94"));
        order1.setTotal(new BigDecimal("2054.94"));

        ordersDao.addOrder(order1);

        Order order2 = new Order();
        order2.setOrderNumber(2);
        order2.setOrderDate(LocalDate.parse("09/20/2023", DateTimeFormatter.ofPattern("MM/dd/yyyy")));
        order2.setCustomerName("Shivakumar");
        order2.setState("CT");
        order2.setTaxRate(new BigDecimal("6.99"));
        order2.setProductType("Carpet");
        order2.setArea(new BigDecimal("231.00"));
        order2.setCostPerSquareFoot(new BigDecimal("2.25"));
        order2.setLaborCostPerSquareFoot(new BigDecimal("2.10"));
        order2.setMaterialCost(new BigDecimal("519.75"));
        order2.setLaborCost(new BigDecimal("485.10"));
        order2.setTax(new BigDecimal("70.24"));
        order2.setTotal(new BigDecimal("1075.09"));

        ordersDao.addOrder(order2);

        Assert.assertEquals("There should be 2 orders.", 2, ordersDao.getAllOrders().size());
        Assert.assertTrue("Should contain order1.", ordersDao.getAllOrders().contains(order1));
        Assert.assertTrue("Should contain order2", ordersDao.getAllOrders().contains(order2));
        Assert.assertNotNull("Should not be null. 2 orders", ordersDao.getAllOrders());
        Assert.assertFalse("Should not be empty.", ordersDao.getAllOrders().isEmpty());
    }

    /**
     * Test of removeOrder method, of class FlooringMasteryOrdersDao.
     */
    @Test
    public void testRemoveOrder() {
        Order order1 = new Order();
        order1.setOrderNumber(1);
        order1.setOrderDate(LocalDate.parse("09/20/2022", DateTimeFormatter.ofPattern("MM/dd/yyyy")));
        order1.setCustomerName("Hamsini");
        order1.setState("MT");
        order1.setTaxRate(new BigDecimal("6.75"));
        order1.setProductType("Laminate");
        order1.setArea(new BigDecimal("500.00"));
        order1.setCostPerSquareFoot(new BigDecimal("1.75"));
        order1.setLaborCostPerSquareFoot(new BigDecimal("2.10"));
        order1.setMaterialCost(new BigDecimal("875.00"));
        order1.setLaborCost(new BigDecimal("1050.00"));
        order1.setTax(new BigDecimal("129.94"));
        order1.setTotal(new BigDecimal("2054.94"));

        ordersDao.addOrder(order1);

        Order order2 = new Order();
        order2.setOrderNumber(2);
        order2.setOrderDate(LocalDate.parse("09/20/2023", DateTimeFormatter.ofPattern("MM/dd/yyyy")));
        order2.setCustomerName("Shivakumar");
        order2.setState("CT");
        order2.setTaxRate(new BigDecimal("6.99"));
        order2.setProductType("Carpet");
        order2.setArea(new BigDecimal("231.00"));
        order2.setCostPerSquareFoot(new BigDecimal("2.25"));
        order2.setLaborCostPerSquareFoot(new BigDecimal("2.10"));
        order2.setMaterialCost(new BigDecimal("519.75"));
        order2.setLaborCost(new BigDecimal("485.10"));
        order2.setTax(new BigDecimal("70.24"));
        order2.setTotal(new BigDecimal("1075.09"));

        ordersDao.addOrder(order2);

        ordersDao.removeOrder(order1);
        Assert.assertEquals("Should only be 1 order since 1 was "
                + "removed.", 1, ordersDao.getAllOrders().size());
        Assert.assertNull("Order 1 should no longer be in there after "
                + "removal.", ordersDao.getOrder(order1.getOrderNumber()));

        ordersDao.removeOrder(order2);
        Assert.assertEquals("Should be zero orders since both "
                + "were removed.", 0, ordersDao.getAllOrders().size());
        Assert.assertNull("When trying to get an order that is not there, "
                + "should return null.", ordersDao.getOrder(order2.getOrderNumber()));
    }

    /**
     * Test of editOrder method, of class FlooringMasteryOrdersDao.
     */
    @Test
    public void testEditOrder() {
        Order order1 = new Order();
        order1.setOrderNumber(1);
        order1.setOrderDate(LocalDate.parse("09/20/2022", DateTimeFormatter.ofPattern("MM/dd/yyyy")));
        order1.setCustomerName("Hamsini");
        order1.setState("MT");
        order1.setTaxRate(new BigDecimal("6.75"));
        order1.setProductType("Laminate");
        order1.setArea(new BigDecimal("500.00"));
        order1.setCostPerSquareFoot(new BigDecimal("1.75"));
        order1.setLaborCostPerSquareFoot(new BigDecimal("2.10"));
        order1.setMaterialCost(new BigDecimal("875.00"));
        order1.setLaborCost(new BigDecimal("1050.00"));
        order1.setTax(new BigDecimal("129.94"));
        order1.setTotal(new BigDecimal("2054.94"));

        ordersDao.addOrder(order1);

        Order order2 = new Order();
        order2.setOrderNumber(2);
        order2.setOrderDate(LocalDate.parse("09/20/2023", DateTimeFormatter.ofPattern("MM/dd/yyyy")));
        order2.setCustomerName("Shivakumar");
        order2.setState("CT");
        order2.setTaxRate(new BigDecimal("6.99"));
        order2.setProductType("Carpet");
        order2.setArea(new BigDecimal("231.00"));
        order2.setCostPerSquareFoot(new BigDecimal("2.25"));
        order2.setLaborCostPerSquareFoot(new BigDecimal("2.10"));
        order2.setMaterialCost(new BigDecimal("519.75"));
        order2.setLaborCost(new BigDecimal("485.10"));
        order2.setTax(new BigDecimal("70.24"));
        order2.setTotal(new BigDecimal("1075.09"));

        ordersDao.editOrder(1, order2);

        Order fromOrdersDao = ordersDao.getOrder(order2.getOrderNumber());

        Assert.assertEquals("getAllOrders should return 1 since the "
                + "order was edited and another was not "
                + "added.", 1, ordersDao.getAllOrders().size());
        Assert.assertEquals("Order2 should match what is returned "
                + "since Order1 was edited to be replaced by order2.", order2, fromOrdersDao);
        Assert.assertEquals("2 should be the new order number of the order returned "
                + "", 2, fromOrdersDao.getOrderNumber());
        Assert.assertNotNull("There should be 1 order , not null.", fromOrdersDao);
    }

}
