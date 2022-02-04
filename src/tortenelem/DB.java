/*
 * Made by Andrea Mate.
 * For practice.
 * This is the way!
 */

package tortenelem;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import javafx.collections.ObservableList;

/* @author Máté Andrea */
public class DB {
    final String db="jdbc:mysql://localhost:3306/tortenelem?useUnicode=true&characterEncoding=UTF-8";
    final String user="tanulo";
    final String pass="tanulo";
    
    public void beolvas(ObservableList<Evszam> tabla,String s){
        try (Connection kapcs = DriverManager.getConnection(db,user,pass);
                PreparedStatement ekp=kapcs.prepareStatement(s)){
            tabla.clear();
            ResultSet eredmeny=ekp.executeQuery();
            while (eredmeny.next()) {                
                tabla.add(new Evszam(
                        eredmeny.getInt("evID"), 
                        eredmeny.getInt("ev"),
                        eredmeny.getString("esemeny")));
            }
        } catch (SQLException e) {
            panel.Panel.hiba("Hiba!", e.getMessage());
        }
    }

    public int hozzaad(int ev,String esemeny){
    String s="insert into evszamok (ev,esemeny) values(?,?);";
        try (Connection kapcs=DriverManager.getConnection(db,user,pass);
                PreparedStatement ekp=kapcs.prepareStatement(s)) {
            ekp.setInt(1, ev);
            ekp.setString(2, esemeny);
            return ekp.executeUpdate();
        } catch (SQLException e) {
            panel.Panel.hiba("Hiba", e.getMessage());
            return 0;
        }
}
  public int modosit(int id,int ev,String esemeny){
    String s="update evszamok set ev=?,esemeny=? where evid=?;";
        try (Connection kapcs=DriverManager.getConnection(db,user,pass);
                PreparedStatement ekp=kapcs.prepareStatement(s)) {
            ekp.setInt(1, ev);
            ekp.setString(2, esemeny);
            ekp.setInt(3, id);
            return ekp.executeUpdate();
        } catch (SQLException e) {
            panel.Panel.hiba("Hiba", e.getMessage());
            return 0;
        }
}
  public int torol(int id){
      String s="delete from evszamok where evid=?";
      try (Connection kapcs=DriverManager.getConnection(db,user,pass);
                PreparedStatement ekp=kapcs.prepareStatement(s)) {
            ekp.setInt(1, id);
            return ekp.executeUpdate();
        } catch (SQLException e) {
            panel.Panel.hiba("Hiba", e.getMessage());
            return 0;
        }
  }
}

