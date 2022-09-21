package dto;

import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.Objects;
@Getter
@Setter
public class Tax {
    
    private String state;
    private BigDecimal taxRate;
    
    public Tax() {
        
    }

    public void setTaxRate(BigDecimal taxRate) {
        this.taxRate = taxRate.setScale(2, RoundingMode.HALF_UP);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Tax)) return false;
        Tax tax = (Tax) o;
        return Objects.equals(getState(), tax.getState()) && Objects.equals(getTaxRate(), tax.getTaxRate());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getState(), getTaxRate());
    }

}
