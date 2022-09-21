package service;

import dao.Orders.FlooringMasteryOrdersDao;
import dao.FlooringMasteryPersistenceException;
import dao.Products.FlooringMasteryProductsDao;
import dao.Taxes.FlooringMasteryTaxesDao;
import dto.Order;
import dto.Product;
import dto.Tax;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;


public class FlooringMasteryServiceLayerImpl implements FlooringMasteryServiceLayer {

    private final FlooringMasteryOrdersDao orderDao;
    private final FlooringMasteryTaxesDao taxDao;
    private final FlooringMasteryProductsDao productDao;
    
    private String bootConfig;
    
    public String getBootConfig() {
        return bootConfig;
    }
    
    public void setBootConfig(String bootConfig){
        this.bootConfig = bootConfig;
    }

    public FlooringMasteryServiceLayerImpl(FlooringMasteryOrdersDao orderDao,
            FlooringMasteryTaxesDao taxDao,
            FlooringMasteryProductsDao productDao) {
        this.orderDao = orderDao;
        this.taxDao = taxDao;
        this.productDao = productDao;
    }
    
    @Override
    public boolean bootConfig(){
        return bootConfig.equals("PROD");
    }

    @Override
    public void load() throws FlooringMasteryPersistenceException {
        orderDao.loadOrderData();
        taxDao.loadTaxData();
        productDao.loadProductData();
    }

    @Override
    public void save() throws FlooringMasteryPersistenceException {
        if (bootConfig.equals("PROD")) {
            orderDao.writeOrderData();
        }
    }

    @Override
    public List<Order> getAllOrdersByDate(LocalDate orderDate) throws FlooringMasteryNoOrderExistsByDateException {
        Collection<Order> allOrders = orderDao.getAllOrders();
        Stream<Order> allOrdersByDateStream = allOrders.stream().filter(p -> p.getOrderDate().equals(orderDate));
        List<Order> allOrdersByDateList;
        allOrdersByDateList = allOrdersByDateStream.collect(Collectors.toList());
        if (allOrdersByDateList.isEmpty()) {
            throw new FlooringMasteryNoOrderExistsByDateException("No order exists for " + orderDate.format(DateTimeFormatter.ofPattern("MM/dd/yyyy")) + ".");
        }
        return allOrdersByDateList;
    }

    @Override
    public void createOrder(Order newOrder) {
        setOrderNumber(newOrder);
        setOrderFields(newOrder);
        orderDao.addOrder(newOrder);
    }

    @Override
    public Order generateOrderData(Order editedOrder) {
        setOrderFields(editedOrder);
        return editedOrder;
    }

    private void setOrderNumber(Order newOrder) {
        int newOrderNumber = validateOrderNumber();
        newOrder.setOrderNumber(newOrderNumber);
    }

    private void setOrderFields(Order newOrder) {
        String state = newOrder.getState();
        newOrder.setTaxRate(taxDao.getTax(state).getTaxRate());

        String productType = newOrder.getProductType();
        newOrder.setCostPerSquareFoot(productDao.getProduct(productType).getCostPerSquareFoot());
        newOrder.setLaborCostPerSquareFoot(productDao.getProduct(productType).getLaborCostPerSquareFoot());

        BigDecimal materialCost = newOrder.getArea().multiply(newOrder.getCostPerSquareFoot());
        newOrder.setMaterialCost(materialCost);

        BigDecimal laborCost = newOrder.getArea().multiply(newOrder.getLaborCostPerSquareFoot());
        newOrder.setLaborCost(laborCost);

        BigDecimal materialAndLaborCostTotal = materialCost.add(laborCost);

        BigDecimal tax = newOrder.getTaxRate().multiply(materialAndLaborCostTotal);
        newOrder.setTax(tax.movePointLeft(2));

        BigDecimal total = materialAndLaborCostTotal.add(tax.movePointLeft(2));
        newOrder.setTotal(total);
    }

    @Override
    public List<Order> getAllOrders() {
        return orderDao.getAllOrders();
    }

    private int validateOrderNumber() {
        List<Order> allOrders = orderDao.getAllOrders();
        ArrayList<Integer> allOrderNumbers = new ArrayList<>();
        for (Order allOrderNumbersOrder : allOrders) {
            int orderNumber = allOrderNumbersOrder.getOrderNumber();
            allOrderNumbers.add(orderNumber);
        }
        int highestExistingOrderNumber = Collections.max(allOrderNumbers);
        return generateOrderNumber(highestExistingOrderNumber);
    }

    private int generateOrderNumber(int highestExistingOrderNumber) {
        return highestExistingOrderNumber + 1;
    }

    @Override
    public Order getOrderByDateAndOrderNumber(int orderNumber,
            LocalDate orderDate) throws FlooringMasteryNoOrderExistsByOrderNumberAndByDateException {

        Order singleOrderByDateAndOrderNumber;

        Collection<Order> allOrders = orderDao.getAllOrders();
        Stream<Order> orderByDateAndOrderNumberStream = allOrders.stream().filter(p -> p.getOrderDate().equals(orderDate) && p.getOrderNumber() == orderNumber);

        List<Order> orderByDateAndOrderNumber;

        orderByDateAndOrderNumber = orderByDateAndOrderNumberStream.collect(Collectors.toList());
        if (!orderByDateAndOrderNumber.isEmpty()) {
            singleOrderByDateAndOrderNumber = orderByDateAndOrderNumber.get(0);
        } else {
            throw new FlooringMasteryNoOrderExistsByOrderNumberAndByDateException("No order exists for order number: " + orderNumber + " and date: " + orderDate.format(DateTimeFormatter.ofPattern("MM/dd/yyyy")) + ".");
        }

        return singleOrderByDateAndOrderNumber;
    }

    @Override
    public void removeOrder(Order removedOrder) {
        orderDao.removeOrder(removedOrder);
    }

    @Override
    public List<Tax> getAllTaxes() {
        return taxDao.getAllTaxes();
    }

    @Override
    public List<Product> getAllProducts() {
        return productDao.getAllProducts();
    }

    @Override
    public void editOrder(Order editedOrder) {
        orderDao.editOrder(editedOrder.getOrderNumber(), editedOrder);
    }

}
