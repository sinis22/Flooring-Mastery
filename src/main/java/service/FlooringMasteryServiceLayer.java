package service;

import dao.FlooringMasteryPersistenceException;
import dto.Order;
import dto.Product;
import dto.Tax;
import java.time.LocalDate;
import java.util.List;

public interface FlooringMasteryServiceLayer {
    
    void load() throws FlooringMasteryPersistenceException;
    void save() throws FlooringMasteryPersistenceException;
    
    List<Order> getAllOrdersByDate(LocalDate orderDate) throws FlooringMasteryNoOrderExistsByDateException;
    
    Order getOrderByDateAndOrderNumber(int orderNumber, LocalDate orderDate) throws FlooringMasteryNoOrderExistsByOrderNumberAndByDateException;
    
    void createOrder(Order newOrder);
    
    List<Order> getAllOrders();
    
    void removeOrder(Order removedOrder);
    
    List<Tax> getAllTaxes();
    
    List<Product> getAllProducts();
    
    void editOrder(Order editedOrder);
    
    Order generateOrderData(Order generatedOrder);
    
    boolean bootConfig();
    
}
