/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao.Taxes;

import dao.FlooringMasteryPersistenceException;
import dto.Tax;

import java.util.List;


public interface FlooringMasteryTaxesDao {

    void loadTaxData() throws FlooringMasteryPersistenceException;

    void saveTaxData() throws FlooringMasteryPersistenceException;

    void addTax(Tax newTax);

    Tax getTax(String state);

    List<Tax> getAllTaxes();

    void editTax(String state,
            Tax taxToEdit);

    void removeTax(Tax removedTax);

}
