/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Project.Data;

import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

/**
 *
 * @author DELL
 */
public class DateProduct {
     private StringProperty Name;
    private ObjectProperty<Integer> Quantity;
    private ObjectProperty<Float> Sum;
    
    public DateProduct(){
        Name = new SimpleStringProperty();
        Quantity = new SimpleObjectProperty<>();
        Sum = new SimpleObjectProperty<>();
    }
    
    public String getName() {
        return Name.get();

    }

    public Integer getQuantity() {
        return Quantity.get();
    }

    public Float getSum() {
        return Sum.get();
    }
    
     public void setName(String name) {
        this.Name.set(name);
    }

    public void setQuantity(int quantity) {
        this.Quantity.set(quantity);
    }
    
    public void setSum(float sum){
        this.Sum.set(sum);
    }
    
    public StringProperty getNameProperty() {
        return this.Name;
    }

    public ObjectProperty<Integer> getQuantityProperty() {
        return this.Quantity;
    }

    public ObjectProperty<Float> getSumProperty() {
        return this.Sum;
    }
}
