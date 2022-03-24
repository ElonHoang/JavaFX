/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Project.Data;

import java.util.Date;
import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

/**
 *
 * @author DELL
 */
public class Dates {

    private ObjectProperty<Integer> ID;
    private ObjectProperty<Date> Date;
    private StringProperty Name;
    private ObjectProperty<Integer> Quantity;
    private ObjectProperty<Float> Sum;

    public Dates() {
        ID = new SimpleObjectProperty<>(null);
        Date = new SimpleObjectProperty<>();
        Name = new SimpleStringProperty();
        Quantity = new SimpleObjectProperty<>();
        Sum = new SimpleObjectProperty<>();
    }

    public Integer getID() {
        return ID.get();
    }

    public Date getDate() {
        return Date.get();
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

    public void setID(int id) {
        this.ID.set(id);
    }

    public void setDate(Date date) {
        this.Date.set(date);
    }

    public void setName(String name) {
        this.Name.set(name);
    }

    public void setQuantity(int quantity) {
        this.Quantity.set(quantity);
    }

    public ObjectProperty<Integer> getIDProperty() {
        return this.ID;
    }

    public ObjectProperty<Date> getDateProperty() {
        return this.Date;
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
