
package dao.Taxes;

import dto.Tax;

import java.math.BigDecimal;
import java.util.*;


public class FlooringMasteryTaxesDaoStubImpl implements FlooringMasteryTaxesDao {

    private final Map<String, Tax> taxes = new LinkedHashMap<>();
    
    public FlooringMasteryTaxesDaoStubImpl() {
        Tax newTax = new Tax();
        newTax.setState("MT");
        newTax.setTaxRate(new BigDecimal("6.75"));
        
        taxes.put(newTax.getState(), newTax);
    }

    public int loadCount = 0;

    @Override
    public void loadTaxData() {
        loadCount++;
    }

    public int saveCount = 0;

    @Override
    public void saveTaxData() {
        saveCount++;
    }

    @Override
    public void addTax(Tax newTax) {
    }

    @Override
    public Tax getTax(String state) {
        return this.taxes.get(state); 
    }

    @Override
    public List<Tax> getAllTaxes() {
        Collection<Tax> allTaxes = this.taxes.values();
        return new ArrayList<>(allTaxes);
    }

    @Override
    public void editTax(String state,
            Tax taxToEdit) {
    }

    @Override
    public void removeTax(Tax removedTax) {
    }

}
