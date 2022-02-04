/*
 * Made by Andrea Mate.
 * For practice.
 * This is the way!
 */
package tortenelem;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;

/**
 *
 * @author Máté Andrea
 */
public class FXMLDocumentController implements Initializable {
    
    @FXML
    private TableView<Evszam> tblEvek;

    @FXML
    private TableColumn<Evszam, Integer> oEv;

    @FXML
    private TableColumn<Evszam, String> oEsemeny;

    @FXML
    private TextField txtKeres;

    @FXML
    private TextField txtEv;

    @FXML
    private TextField txtEsemeny;

    @FXML
    void torol() {
int ind=tblEvek.getSelectionModel().getSelectedIndex();
        if (ind==-1) {
            return ;
        }
        if (!panel.Panel.igennem("Törlés", "Biztosan törölni szeretnéd ezt az évszámot?")) {
            return;
        }
        int id=tblEvek.getItems().get(ind).getId();
        int sor=ab.torol(id);
        if (sor>0) {
            beolvas();
        }
    }

    @FXML
    void hozzaad() {
String s=txtEv.getText();
int ev;
        try {
            ev=Integer.parseInt(s);
        } catch (NumberFormatException e) {
            panel.Panel.hiba("Hiba!", "Hibás év!");
            txtEv.requestFocus();
            return;
        }
        s=txtEsemeny.getText();
        if (s.length()<1 || s.length()>80) {
            panel.Panel.hiba("Hiba!", "Az esemény hossza 1-80 karakter lehet!");
            txtEsemeny.requestFocus();
            return;
        }
        int sor=ab.hozzaad(ev, s);
        if (sor>0) {
            beolvas();
            uj();
        }
    }
    
     @FXML
    void modosit() {
int ind=tblEvek.getSelectionModel().getSelectedIndex();
         if (ind==-1) {
             return;
         }
         int id=tblEvek.getItems().get(ind).getId();
         String s=txtEv.getText();
         int ev;
         try {
             ev=Integer.parseInt(s);
         } catch (NumberFormatException e) {
             txtEv.requestFocus();
             return ;
         }
         s=txtEsemeny.getText();
         if (s.length()<1 || s.length()>80) {
            panel.Panel.hiba("Hiba!", "Az esemény hossza 1-80 karakter lehet!");
            txtEsemeny.requestFocus();
            return;
        }
         int sor=ab.modosit(id, ev, s);
         if (sor>0) {
             beolvas();
             int i=0;
             while (i<tblEvek.getItems().size() && tblEvek.getItems().get(i).getId()!=id) {                 
       i++;          
             }
             if (id==tblEvek.getItems().get(i).getId()) {
       tblEvek.getSelectionModel().select(i);
             }
         }
    }

    @FXML
    void szuro_torol() {
txtKeres.clear();
    }

    @FXML
    void uj() {
txtEv.clear();
txtEsemeny.clear();
txtEv.requestFocus();
tblEvek.getSelectionModel().select(null);
    }
    
    DB ab= new DB();
    
    private void beolvas(){
        String szuro="'%"+txtKeres.getText()+"%'";
        String s="select *from evszamok where esemeny like "+szuro+"order by ev";
        ab.beolvas(tblEvek.getItems(), s);
    }
    
    private void tablabol(int i){
        if (i==-1) {
            return;
        }
        Evszam esz=tblEvek.getItems().get(i);
        txtEv.setText(""+esz.getEv());
        txtEsemeny.setText(esz.getEsemeny());
    }
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        oEv.setCellValueFactory(new PropertyValueFactory<>("ev"));
        oEsemeny.setCellValueFactory(new PropertyValueFactory<>("esemeny"));
        beolvas();
        txtKeres.textProperty().addListener((o,regi,uj)->beolvas());
        tblEvek.getSelectionModel().selectedIndexProperty().addListener(
        (o,regi,uj)->tablabol(uj.intValue()));
    }    
    
}
