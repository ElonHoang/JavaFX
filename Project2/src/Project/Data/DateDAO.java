/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Project.Data;

import java.util.Date;
import javafx.collections.ObservableList;

/**
 *
 * @author DELL
 */
public interface DateDAO {
     public ObservableList<Dates> selectAll();
    public ObservableList<DateProduct> SelectByDate(Date date);
}
