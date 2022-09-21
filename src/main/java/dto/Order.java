package dto;

import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Objects;


@Setter
@Getter
public class Order {

    private int orderNumber;
    private LocalDate orderDate;
    private String customerName;
    private String state;
    private BigDecimal taxRate;
    private String productType;
    private BigDecimal area;
    private BigDecimal costPerSquareFoot;
    private BigDecimal laborCostPerSquareFoot;
    private BigDecimal materialCost;
    private BigDecimal laborCost;
    private BigDecimal tax;
    private BigDecimal total;
    
    public Order() {
        
    }
    
//    public Order(int orderNumber, LocalDate orderDate, String customerName, String state,
//            BigDecimal taxRate, String productType, BigDecimal area,
//            BigDecimal costPerSquareFoot, BigDecimal laborCostPerSquareFoot,
//            BigDecimal materialCost, BigDecimal laborCost, BigDecimal tax,
//            BigDecimal total) {
//
//        this.orderNumber = orderNumber;
//        this.orderDate = orderDate;
//        this.customerName = customerName;
//        this.state = state;
//        this.taxRate = taxRate.setScale(2, RoundingMode.HALF_UP);
//        this.productType = productType;
//        this.area = area.setScale(2, RoundingMode.HALF_UP);
//        this.costPerSquareFoot = costPerSquareFoot.setScale(2, RoundingMode.HALF_UP);
//        this.laborCostPerSquareFoot = laborCostPerSquareFoot.setScale(2, RoundingMode.HALF_UP);
//        this.materialCost = materialCost.setScale(2, RoundingMode.HALF_UP);
//        this.laborCost = laborCost.setScale(2, RoundingMode.HALF_UP);
//        this.tax = tax.setScale(2, RoundingMode.HALF_UP);
//        this.total = total.setScale(2, RoundingMode.HALF_UP);
//
//    }


    public void setOrderNumber(int orderNumber) {
        this.orderNumber = orderNumber;
    }

    public String getOrderDateString() {
        return orderDate.format(DateTimeFormatter.ofPattern("MM/dd/yyyy"));
    }


    public void setTaxRate(BigDecimal taxRate) {
        this.taxRate = taxRate.setScale(2, RoundingMode.HALF_UP);
    }







    public void setArea(BigDecimal area) {
        this.area = area.setScale(2, RoundingMode.HALF_UP);
    }



    public void setCostPerSquareFoot(BigDecimal costPerSquareFoot) {
        this.costPerSquareFoot = costPerSquareFoot.setScale(2, RoundingMode.HALF_UP);
    }

    public void setLaborCostPerSquareFoot(BigDecimal laborCostPerSquareFoot) {
        this.laborCostPerSquareFoot = laborCostPerSquareFoot.setScale(2, RoundingMode.HALF_UP);
    }

    public void setMaterialCost(BigDecimal materialCost) {
        this.materialCost = materialCost.setScale(2, RoundingMode.HALF_UP);
    }
    public void setLaborCost(BigDecimal laborCost) {
        this.laborCost = laborCost.setScale(2, RoundingMode.HALF_UP);
    }

    public void setTax(BigDecimal tax) {
        this.tax = tax.setScale(2, RoundingMode.HALF_UP);
    }

    public void setTotal(BigDecimal total) {
        this.total = total.setScale(2, RoundingMode.HALF_UP);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Order)) return false;
        Order order = (Order) o;
        return getOrderNumber() == order.getOrderNumber() && Objects.equals(getOrderDate(), order.getOrderDate())
                && Objects.equals(getCustomerName(), order.getCustomerName()) && Objects.equals(getState(),
                order.getState()) && Objects.equals(getTaxRate(), order.getTaxRate()) && Objects.equals(getProductType(),
                order.getProductType()) && Objects.equals(getArea(), order.getArea()) &&
                Objects.equals(getCostPerSquareFoot(), order.getCostPerSquareFoot()) &&
                Objects.equals(getLaborCostPerSquareFoot(), order.getLaborCostPerSquareFoot()) &&
                Objects.equals(getMaterialCost(), order.getMaterialCost()) && Objects.equals(getLaborCost(),
                order.getLaborCost()) && Objects.equals(getTax(), order.getTax()) && Objects.equals(getTotal(),
                order.getTotal());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getOrderNumber(), getOrderDate(), getCustomerName(), getState(), getTaxRate(),
                getProductType(), getArea(), getCostPerSquareFoot(), getLaborCostPerSquareFoot(), getMaterialCost(),
                getLaborCost(), getTax(), getTotal());
    }
}
