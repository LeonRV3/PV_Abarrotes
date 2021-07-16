
package controlador;

import java.net.URL;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javax.swing.JOptionPane;
import modelo.Producto;

import com.mysql.jdbc.Driver;
import java.awt.event.KeyEvent;
import java.io.IOException;
import java.sql.ResultSetMetaData;
import javafx.application.Platform;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.CheckBox;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;
import javafx.stage.Screen;
import javafx.stage.Stage;
import javax.swing.JTextField;
import org.controlsfx.control.textfield.AutoCompletionBinding;
import org.controlsfx.control.textfield.TextFields;

public class ProductoController implements Initializable {

    @FXML
    private TableView<Producto> tblPersonas;
    @FXML
    private TableColumn<?, ?> colNombre;
    @FXML
    private TableColumn<?, ?> colApellidos;
    @FXML
    private TableColumn<?, ?> colEdad;
    @FXML
    private Button btnAgregar;
    private TextField txtEdad;
    private TextField txtApellidos;

    @FXML
    private TextField txtNombre;

    private ObservableList<Producto> productos = FXCollections.observableArrayList();
    //private ObservableList<Suma> sumas = FXCollections.observableArrayList();
    @FXML
    private Button eliminarPersona;
    private TextField tblSuma;
    @FXML
    private TextField txtTotal;
    @FXML
    private Button btnBuscar;
    @FXML
    private TableColumn<?, ?> colCantidad;

    private AutoCompletionBinding<String> autoCompletionBinding;
    @FXML
    private TextField txtCantidad;
    @FXML
    private Button btnInventario;
    @FXML
    private AnchorPane apPrincipal;
    @FXML
    private Button btnModificar;
    @FXML
    private TextField txtPago;
    @FXML
    private TableColumn<?, ?> colSubTotal;
    @FXML
    private TextField txtCambio;
    @FXML
    private Button btnClean;
    @FXML
    private TextField txtPrecio;
    @FXML
    private TextField txtCodigo;

    PreparedStatement ps = null;
    ResultSet rs = null;
    Conexion conn = new Conexion();
    Connection con = conn.getConection();

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // personas = FXCollections.observableArrayList(); Ya esta declarado arriba 
        /* PreparedStatement ps = null;
            ResultSet rs = null;
            Conexion conn = new Conexion();
            Connection con = conn.getConection();
            try{
                ps = con.prepareStatement("SELECT * FROM product WHERE codigo = ?");
              ps.setString(1, txtNombre.getText());
       TextFields.bindAutoCompletion(txtApellidos, rs.getString("nombre"));
            }catch(SQLException e){
                
            }*/

        Platform.runLater(() -> txtCodigo.requestFocus()); //Enfoca en el txtNombre al iniciar

        this.colNombre.setCellValueFactory(new PropertyValueFactory("codigo"));
        this.colApellidos.setCellValueFactory(new PropertyValueFactory("nombre"));
        this.colEdad.setCellValueFactory(new PropertyValueFactory("precio"));
        this.colCantidad.setCellValueFactory(new PropertyValueFactory("cantidad"));
        this.colSubTotal.setCellValueFactory(new PropertyValueFactory("subTotal"));
        //lo que esta entre comillas hace ref
        //a la variable que se va a pasar como parametro del constructor de la clase Persona
        // this.colTotalEdades.setCellValueFactory(new PropertyValueFactory("edades"))

        ////////////////////////////////////////////////////////////////////////////////////////////////////
//        class Conexion {
//
//            private final String URL = "jdbc:mysql://localhost:3306/store";
//            private final String USERNAME = "root";
//            private final String PASSWORD = "leon12345";
//            private Connection con = null;
//
//            public Connection getConection() {
//
//                try {
//                    Class.forName("com.mysql.jdbc.Driver");
//                    con = (Connection) DriverManager.getConnection(this.URL, this.USERNAME, this.PASSWORD);
//                    //JOptionPane.showMessageDialog(null, "Conexion exitosa");
//                } catch (SQLException e) {
//                    System.out.println(e);
//                } catch (ClassNotFoundException ex) {
//                    Logger.getLogger(Conexion.class.getName()).log(Level.SEVERE, null, ex);
//                }
//                return con;
//            }
//
//        }
        //////////////////////////////////////////////////////////////////////////////////////////////////
    }

    @FXML
    private void agregarPersona(ActionEvent event) {

        // try{
        String codigo = (String) this.txtCodigo.getText();
        String nombre = (String) this.txtNombre.getText();
        double precio = Double.parseDouble(this.txtPrecio.getText()); //Precio
        double cantidad = Double.parseDouble(this.txtCantidad.getText());
        double subTotal = precio * cantidad;

        Producto p = new Producto(codigo, nombre, precio, cantidad, subTotal);
        /* Suma s = new Suma(edades);
         this.sumas.add(s);
       this.tbtlTotal.setItems(sumas);
         */
        //   if(!this.personas.contains(p)) {
        this.productos.add(p);

        this.tblPersonas.setItems(productos);

        // this.tblSuma.setText(String.valueOf(p.getEdad()+p.getEdad()));
        /* Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setHeaderText(null);
            alert.setTitle("Informacion");
            alert.setContentText("Persona agregada");
            alert.showAndWait();
        }else{
             Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setHeaderText(null);
            alert.setTitle("Error");
            alert.setContentText("La persona existe");
            alert.showAndWait();
         }
        }catch(NumberFormatException e ){
             Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setHeaderText(null);
            alert.setTitle("Error");
            alert.setContentText("Formato incorrecto");
            alert.showAndWait();
        }*/
 /* double tamaño = tblPersonas.getItems().size();
        double z = 0;
        double k = 0;
        double l = 0;
        double o = 0;
        for (int i = 0; i < tamaño; i++) {
            Object x = colEdad.getCellData(i);
            l = (double)x;
            System.out.println("Yo soy l: "+l);
        }
            for (int j = 0; j < tamaño; j++) {
                Object w = colCantidad.getCellData(j);
                
                k =(double) w;
                System.out.println("Yo soy k: "+k);
                o = (double) l * k ;
                System.out.println("Yo soy o: "+o);
                z = (double) z+o;
                System.out.println("Yo soy z: "+z);
            }
       
         txtTotal.setText(String.valueOf(z));
       ¨*/
        //}}
        double tamaño = tblPersonas.getItems().size();
        double z = 0;
        double k = 0;
        double l = 0;
        double o = 0;
        for (int i = 0; i < tamaño; i++) {
            Object x = colEdad.getCellData(i);
            l = (double) x;

            for (int j = 0; j <= i; j++) {
                Object w = colCantidad.getCellData(j);

                k = (double) w;

            }
            o = (double) l * k;
            z = (double) z + o;
        }
    }

    @FXML
    private void seleccionar(MouseEvent event) {
        Producto p = this.tblPersonas.getSelectionModel().getSelectedItem();
        /* int tamaño = tblPersonas.getItems().size();
        for (int i = 0; i < tamaño; i++) {
            Object x = colEdad.getCellData(i);
            System.out.println(x);
        }*/

        if (p != null) {
            this.txtCodigo.setText(p.getCodigo());
            this.txtNombre.setText(p.getNombre());
            this.txtPrecio.setText(p.getPrecio() + "");
            this.txtCantidad.setText(p.getCantidad() + "");

        }
    }

    @FXML
    private void modificarPersona(ActionEvent event) {

        Producto p = this.tblPersonas.getSelectionModel().getSelectedItem();
        if (p == null) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setHeaderText(null);
            alert.setTitle("Error");
            alert.setContentText("Debes seleccionar un producto");
            alert.showAndWait();

        } else {
            try {
                String codigo = (String) this.txtCodigo.getText();
                String nombre = (String) this.txtNombre.getText();
                double precio = Double.parseDouble(this.txtPrecio.getText());
                double cantidad = Double.parseDouble(this.txtCantidad.getText());
                double subTotal = precio * cantidad;
                Producto aux = new Producto(codigo, nombre, precio, cantidad, subTotal);

                // if (!this.personas.contains(aux)) {
                p.setCodigo(aux.getCodigo());
                p.setNombre(aux.getNombre());

                //Modificacion 20/9 casteado a int por error, soluciona el parseo
                p.setPrecio(aux.getPrecio());
                p.setCantidad(aux.getCantidad());
                p.setSubTotal(subTotal);
                this.tblPersonas.refresh();

                limpiarCajas();

                double tamaño = tblPersonas.getItems().size();
                double z = 0;
                double k = 0;
                double l = 0;
                double o = 0;
                for (int i = 0; i < tamaño; i++) {
                    Object x = colEdad.getCellData(i);
                    l = (double) x;

                    for (int j = 0; j <= i; j++) {
                        Object w = colCantidad.getCellData(j);

                        k = (double) w;

                    }
                    o = (double) l * k;
                    z = (double) z + o;
                }

                txtTotal.setText(String.valueOf(z));

                Alert alert = new Alert(Alert.AlertType.INFORMATION);
                alert.setHeaderText(null);
                alert.setTitle("Informacion");
                alert.setContentText("Producto modificado");
                alert.showAndWait();
                /*} else {
                    Alert alert = new Alert(Alert.AlertType.ERROR);
                    alert.setHeaderText(null);
                    alert.setTitle("Error");
                    alert.setContentText("La persona existe");
                    alert.showAndWait();
                }*/

            } catch (NumberFormatException e) {

            }
        }
    }

    @FXML
    private void eliminar(ActionEvent event) {
        Producto p = this.tblPersonas.getSelectionModel().getSelectedItem();
        if (p == null) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setHeaderText(null);
            alert.setTitle("Error");
            alert.setContentText("Debes seleccionar un producto");
            alert.showAndWait();

        } else {
            this.productos.remove(p);
            this.tblPersonas.refresh();
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setHeaderText(null);
            alert.setTitle("Informacion");
            alert.setContentText("Producto eliminada");
            alert.showAndWait();

            double tamaño = tblPersonas.getItems().size();
            double z = 0;
            double k = 0;
            double l = 0;
            double o = 0;
            for (int i = 0; i < tamaño; i++) {
                Object x = colEdad.getCellData(i);
                l = (double) x;

                for (int j = 0; j <= i; j++) {
                    Object w = colCantidad.getCellData(j);

                    k = (double) w;

                }
                o = (double) l * k;
                z = (double) z + o;
            }

            txtTotal.setText(String.valueOf(z));
        }

    }

    private void Sumar(ActionEvent event) {
        double tamaño = tblPersonas.getItems().size();
        double z = 0;
        double k = 0;
        double l = 0;
        double o = 0;
        for (int i = 0; i < tamaño; i++) {
            Object x = colEdad.getCellData(i);
            l = (double) x;

            for (int j = 0; j <= i; j++) {
                Object w = colCantidad.getCellData(j);

                k = (double) w;

            }
            o = (double) l * k;
            z = (double) z + o;
        }

        txtTotal.setText(String.valueOf(z));

    }

    @FXML
    private void buscar(ActionEvent event) {
        PreparedStatement ps = null;
        ResultSet rs = null;
        Conexion conn = new Conexion();
        Connection con = conn.getConection();

        try {

            ps = con.prepareStatement("SELECT * FROM product WHERE codigo = ?");
            ps.setString(1, txtNombre.getText());
            rs = ps.executeQuery();

            if (rs.next()) {

                txtNombre.setText(rs.getString("nombre"));
                txtPrecio.setText(rs.getString("precio"));

            } else {
                JOptionPane.showMessageDialog(null, "No existe la persona con esta clave");
            }
        } catch (Exception e) {
            System.err.println(e);
        }
    }

    private void activar(ActionEvent event) {
        PreparedStatement ps = null;
        ResultSet rs = null;
        Conexion conn = new Conexion();
        Connection con = conn.getConection();

        try {

            String sql = "SELECT nombre FROM Product order by nombre";
            System.out.println(sql);
            ps = con.prepareStatement(sql);
            rs = ps.executeQuery();

            ResultSetMetaData rsMd = rs.getMetaData();

            int cantidadColumnas = rsMd.getColumnCount();

            while (rs.next()) {

                Object[] filas = new Object[cantidadColumnas];

                for (int i = 0; i < cantidadColumnas; i++) {
                    filas[i] = rs.getObject(i + 1);
                    System.out.println(rs.getObject(i + 1));
                    TextFields.bindAutoCompletion(txtApellidos, filas[i]);
                }

            }

            //}
        } catch (Exception e) {
            System.err.println(e);
        }
    }

    @FXML
    private void buscarNombreConEnter(javafx.scene.input.KeyEvent event) {
        switch (event.getCode()) {
            case ENTER:
                PreparedStatement ps = null;
                ResultSet rs = null;
                Conexion conn = new Conexion();
                Connection con = conn.getConection();

                try {

                    ps = con.prepareStatement("SELECT * FROM product WHERE nombre = ?");
                    ps.setString(1, txtNombre.getText());
                    rs = ps.executeQuery();

                    if (rs.next()) {

                        txtCodigo.setText(rs.getString("codigo"));
                        txtPrecio.setText(rs.getString("precio"));
                        txtCantidad.setText(String.valueOf(1));

                        String codigo = (String) this.txtCodigo.getText();
                        String nombre = (String) this.txtNombre.getText();
                        double precio = Double.parseDouble(this.txtPrecio.getText());
                        double cantidad = Double.parseDouble(this.txtCantidad.getText());
                        double subTotal = precio * cantidad;

                        Producto p = new Producto(codigo, nombre, precio, cantidad, subTotal);

                        this.productos.add(p);

                        this.tblPersonas.setItems(productos);

                        limpiarCajas();

                        double tamaño = tblPersonas.getItems().size();
                        double z = 0;
                        double k = 0;
                        double l = 0;
                        double o = 0;
                        for (int i = 0; i < tamaño; i++) {
                            Object x = colEdad.getCellData(i);
                            l = (double) x;

                            for (int j = 0; j <= i; j++) {
                                Object w = colCantidad.getCellData(j);

                                k = (double) w;

                            }
                            o = (double) l * k;
                            z = (double) z + o;
                        }

                        txtTotal.setText(String.valueOf(z));

                    } else {
                        Alert alert = new Alert(Alert.AlertType.ERROR);
                        alert.setHeaderText(null);
                        alert.setTitle("Error");
                        alert.setContentText("No existe producto con este nombre");
                        alert.showAndWait();
                    }
                } catch (Exception e) {
                    System.err.println(e);
                }
                break;
        }
    }

    @FXML
    private void buscarCodigoConEnter(javafx.scene.input.KeyEvent event) {
        switch (event.getCode()) {
            case ENTER:
                PreparedStatement ps = null;
                ResultSet rs = null;
                Conexion conn = new Conexion();
                Connection con = conn.getConection();

                try {

                    ps = con.prepareStatement("SELECT * FROM product WHERE codigo = ?");
                    ps.setString(1, txtCodigo.getText());
                    rs = ps.executeQuery();

                    if (rs.next()) {

                        txtNombre.setText(rs.getString("nombre"));
                        txtPrecio.setText(rs.getString("precio"));
                        txtCantidad.setText(String.valueOf(1));

                        String codigo = (String) this.txtCodigo.getText();
                        String nombre = (String) this.txtNombre.getText();
                        double precio = Double.parseDouble(this.txtPrecio.getText());
                        double cantidad = Double.parseDouble(this.txtCantidad.getText());
                        double subTotal = precio * cantidad;

                        Producto p = new Producto(codigo, nombre, precio, cantidad, subTotal);

                        this.productos.add(p);

                        this.tblPersonas.setItems(productos);

                        limpiarCajas();

                        double tamaño = tblPersonas.getItems().size();
                        double z = 0;
                        double k = 0;
                        double l = 0;
                        double o = 0;
                        for (int i = 0; i < tamaño; i++) {
                            Object x = colEdad.getCellData(i);
                            l = (double) x;

                            for (int j = 0; j <= i; j++) {
                                Object w = colCantidad.getCellData(j);

                                k = (double) w;

                            }
                            o = (double) l * k;
                            z = (double) z + o;
                        }

                        //Actualizacion de total - pago
                        txtTotal.setText(String.valueOf(z));
                        double pago;
                        if (txtPago.getText().isEmpty()) {
                            pago = 0.0;
                            System.out.println("Estoy vacio");
                            txtCambio.setText("0");
                        } else {
                            pago = Double.parseDouble(txtPago.getText());
                            txtCambio.setText(String.valueOf(z - pago));
                            System.out.println("Hola");
                            if (Double.parseDouble(txtCambio.getText()) > 0.0) {

                                txtCambio.clear();
                                txtCambio.setText("0");

                                Alert alert = new Alert(Alert.AlertType.INFORMATION);
                                alert.setHeaderText(null);
                                alert.setTitle("Pago");
                                alert.setContentText("Pago Insuficiente");
                                alert.showAndWait();
                            }
                        }

                    } else {
                        Alert alert = new Alert(Alert.AlertType.ERROR);
                        alert.setHeaderText(null);
                        alert.setTitle("Error");
                        alert.setContentText("No existe producto con esta clave");
                        alert.showAndWait();
                    }
                } catch (Exception e) {
                    System.err.println(e);
                }
                break;
        }
    }

    @FXML
    private void autoCompletadoATM(MouseEvent event) {

        try {

            String sql = "SELECT nombre FROM Product order by nombre";
            System.out.println(sql);
            ps = con.prepareStatement(sql);
            rs = ps.executeQuery();

            ResultSetMetaData rsMd = rs.getMetaData();

            int cantidadColumnas = rsMd.getColumnCount();

            while (rs.next()) {

                Object[] filas = new Object[cantidadColumnas];

                for (int i = 0; i < cantidadColumnas; i++) {
                    filas[i] = rs.getObject(i + 1);
                    System.out.println(rs.getObject(i + 1));
                    TextFields.bindAutoCompletion(txtNombre, filas[i]);
                }

            }

            //}
        } catch (Exception e) {
            System.err.println(e);
        }
    }

    @FXML
    private void irVentanaInventario(ActionEvent event) {

        try {
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(getClass().getResource("/vista/InventarioVista.fxml"));
            Parent root = loader.load();

            InventarioVistaController controlador = loader.getController();
            int screenWidth = (int) Screen.getPrimary().getBounds().getWidth();
            int screenHeight = (int) Screen.getPrimary().getBounds().getHeight();
            Scene scene = new Scene(root, screenWidth, screenHeight);

            Stage stage = new Stage();
            stage.setMaximized(true);
            stage.setScene(scene);
            stage.show();

        } catch (IOException e) {
            System.out.println(e.getMessage());
        }

    }

    @FXML
    private void PagarCuenta(javafx.scene.input.KeyEvent event) {

        switch (event.getCode()) {
            case ENTER:
                try {

                    System.out.println("Hola");
                    double valD = (Double.parseDouble(txtTotal.getText()) - Double.parseDouble(txtPago.getText()));
                    String valS = String.valueOf(valD);
                    System.out.println(valS);
                    txtCambio.setText(valS);
                } catch (Exception ex) {

                }
                break;
        }
    }

    @FXML
    private void Clean(ActionEvent event) {
        tblPersonas.getItems().clear();
        limpiarCajas();
        txtTotal.clear();
        txtPago.clear();
        txtCambio.clear();

    }

    public void limpiarCajas() {
        txtCodigo.clear();
        txtNombre.clear();
        txtPrecio.clear();
        txtCantidad.clear();

    }
}
