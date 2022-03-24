/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Project.Data;

import Project.DbProject.DbProject;
import Project.DbProject.DbType;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.text.SimpleDateFormat;
import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.util.Date;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

/**
 *
 * @author DELL
 */
public class DateDAOImpl implements DateDAO {

    DbType data = DbType.SQL;
    @Override
    public ObservableList<Dates> selectAll() {
        String sql = "SELECT OrderID, dates FROM Orders";
        ObservableList<Dates> list = FXCollections.observableArrayList();
        try (
                Connection con = DbProject.getConnection(data);
                Statement stmt = con.createStatement();
                ResultSet rs = stmt.executeQuery(sql);) {
            while(rs.next()){
                Dates o = new Dates();
                o.setID(rs.getInt("OrderID"));
                o.setDate(rs.getDate("dates"));   
                list.add(o);
            }
            
        } catch (Exception e) {
            System.out.println(e.getMessage());
            return null;
        }
        return list;
    }

    @Override
    public ObservableList<DateProduct> SelectByDate(Date date){
        java.sql.Date sqlDate = new java.sql.Date(date.getTime());
        String dateSQL = sqlDate.toString().replace("-", ""); 
        
        String sql = "SELECT p.Name,od.Quantity, p.Price FROM Orders as o "
                + "JOIN order_detail as od ON o.OrderID = od.OrderID "
                + "JOIN Products as p on p.ProductID = od.ProductID WHERE o.dates = " + dateSQL;
        ObservableList<DateProduct> list = FXCollections.observableArrayList();
        try(
               Connection con = DbProject.getConnection(data);
                Statement stmt = con.createStatement();
                ResultSet rs = stmt.executeQuery(sql);
                ){
            while(rs.next()){
                DateProduct dp = new DateProduct();
                
                dp.setName(rs.getString("p.Name"));
                dp.setQuantity(rs.getInt("od.Quantity"));
                dp.setSum(rs.getFloat("p.Price") * dp.getQuantity());
                list.add(dp);
            }
            
        }catch(Exception e){
            System.out.println(e.getMessage());
            return null;
        }
        return list;
    }
}
