package dao.Orders;

import dto.Order;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.*;

public class FlooringMasteryOrdersDaoStubImpl implements FlooringMasteryOrdersDao {

    Order order1;
    Order order2;

    private final Map<Integer, Order> orders = new LinkedHashMap<>();

    public FlooringMasteryOrdersDaoStubImpl() {
        order1 = new Order();
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

        orders.put(order1.getOrderNumber(), order1);

        order2 = new Order();
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

        orders.put(order2.getOrderNumber(), order2);
    }

    public int loadCount = 0;
    
    @Override
    public void loadOrderData() {
        loadCount++;
    }

    public int saveCount = 0;
    
    @Override
    public void writeOrderData() {
        saveCount++;
    }

    @Override
    public void addOrder(Order newOrder) {
    }

    @Override
    public List<Order> getAllOrders() {
        Collection<Order> allOrders = this.orders.values();
        return new ArrayList<>(allOrders);
    }

    @Override
    public Order getOrder(int orderNumber) {
        if (orderNumber == 1) {
            return order1;
        } else if (orderNumber == 2) {
            return order2;
        } else {
            return null;
        }
    }

    @Override
    public void removeOrder(Order removedOrder) {
        if (removedOrder == order1) {
            orders.remove(order1.getOrderNumber());
            order1 = orders.get(0);
        } else if (removedOrder == order2) {
            orders.remove(order2.getOrderNumber());
            order1 = orders.get(1);
        }
    }

    @Override
    public void editOrder(int oldOrderNumber,
            Order orderToEdit) {
        if (oldOrderNumber == orderToEdit.getOrderNumber()) {
            this.orders.replace(oldOrderNumber, orderToEdit);

        } else {
            this.orders.remove(oldOrderNumber);
            this.orders.put(orderToEdit.getOrderNumber(), orderToEdit);
        }
    }

}
