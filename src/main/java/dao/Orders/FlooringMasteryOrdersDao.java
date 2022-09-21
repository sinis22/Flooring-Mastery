
package dao.Orders;

import dao.FlooringMasteryPersistenceException;
import dto.Order;

import java.util.List;


public interface FlooringMasteryOrdersDao {

    void loadOrderData() throws FlooringMasteryPersistenceException;

    void writeOrderData() throws FlooringMasteryPersistenceException;

    void addOrder(Order newOrder);

    List<Order> getAllOrders();

    Order getOrder(int orderNumber);

    void removeOrder(Order removedOrder);

    void editOrder(int oldOrderNumber, Order orderToEdit);

}
