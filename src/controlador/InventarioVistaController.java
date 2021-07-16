
package controlador;

import java.net.URL;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
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
import javafx.scene.control.CheckBox;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javax.swing.JOptionPane;
import modelo.Producto;
import modelo.Producto_Inventario;
import org.controlsfx.control.textfield.TextFields;


public class InventarioVistaController implements Initializable {

    @FXML
    private TableView<Producto_Inventario> tbvDatos;
    @FXML
    private TableColumn<?, ?> tcCodigo;
    @FXML
    private TableColumn<?, ?> tcNombre;
    @FXML
    private TableColumn<?, ?> tcPrecio;
    @FXML
    private TableColumn<?, ?> tcCantidad;
    @FXML
    private TextField txtCodigo;
    @FXML
    private TextField txtNombre;
    @FXML
    private TextField txtPrecio;
    @FXML
    private TextField txtCantidad;
    @FXML
    private Button create;
    @FXML
    private Button update;
    @FXML
    private Button delete;

    private ObservableList<Producto_Inventario> productos = FXCollections.observableArrayList();
    @FXML
    private Button btnBuscar;
    @FXML
    private TextField txtCategoria;
    @FXML
    private TableColumn<?, ?> tcCategoria;

    private String codigoPrimitivo;
    @FXML
    private Button btnLimpiar;
    @FXML
    private CheckBox cbAutoCompletado;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        this.tcCodigo.setCellValueFactory(new PropertyValueFactory("codigo"));
        this.tcNombre.setCellValueFactory(new PropertyValueFactory("nombre"));
        this.tcPrecio.setCellValueFactory(new PropertyValueFactory("precio"));
        this.tcCantidad.setCellValueFactory(new PropertyValueFactory("cantidad"));
        this.tcCategoria.setCellValueFactory(new PropertyValueFactory("categoria"));

        ////////////////////////////////////////////////////////////////////////////////////////////////////
        class Conexion {

            private final String URL = "jdbc:mysql://localhost:3306/store";
            private final String USERNAME = "root";
            private final String PASSWORD = "leon12345";
            private Connection con = null;

            public Connection getConection() {

                try {
                    Class.forName("com.mysql.jdbc.Driver");
                    con = (Connection) DriverManager.getConnection(this.URL, this.USERNAME, this.PASSWORD);
                    //JOptionPane.showMessageDialog(null, "Conexion exitosa");
                } catch (SQLException e) {
                    System.out.println(e);
                } catch (ClassNotFoundException ex) {
                    Logger.getLogger(Conexion.class.getName()).log(Level.SEVERE, null, ex);
                }
                return con;
            }

        }

        //////////////////////////////////////////////////////////////////////////////////////////////////
        /*  PreparedStatement ps = null;
        ResultSet rs = null;
        Conexion conn = new Conexion();
        Connection con = conn.getConection();

        try {

            String sql = "SELECT * FROM Product";
            System.out.println(sql);
            ps = con.prepareStatement(sql);
            rs = ps.executeQuery();

            ResultSetMetaData rsMd = rs.getMetaData();

            int cantidadColumnas = rsMd.getColumnCount();
            int contador = 0;
            while (rs.next()) {
                

                Object[] filas = new Object[cantidadColumnas];

                for (int i = 0; i < cantidadColumnas; i++) {
                    filas[i] = rs.getObject(i + 1);
                    contador++;
                   // System.out.println("objeto " + contador + " " + rs.getObject(i + 1));
                    if (contador == 2) {
                        String nombre = (String) rs.getObject(i + 1);
                        System.out.println(nombre);

                    }
                    if (contador == 3) {
                        String apellidos = (String)rs.getObject(i + 1); 
                          System.out.println(apellidos);
                    }
                    if (contador == 4) {
                        double edad = (double)rs.getObject(i + 1);
                        System.out.println(edad);
                    }
                    if (contador == 5) {
                        double cantidad = (double) rs.getObject(i + 1);
                        System.out.println(cantidad);
                    }
                    if (contador == 6) {
                        contador = 0;
                         
                    }
                    //Persona p = new Persona(nombre, apellidos, edad, cantidad);
                   
                    

                }

            }

            //}
        } catch (Exception e) {
            System.err.println(e);
        }*/
    }

    @FXML
    private void create(ActionEvent event) {

        Conexion conn = new Conexion();
        Connection con = conn.getConection();
        PreparedStatement ps = null;
        ResultSet rs = null;
        try {
            PreparedStatement insertar;
            String INSERTAR = "INSERT INTO product(codigo,nombre,precio,cantidad,categoria) VALUES (?,?,?,?,?)";
            insertar = con.prepareStatement(INSERTAR);

            insertar.setString(1, txtCodigo.getText());
            insertar.setString(2, txtNombre.getText());
            insertar.setDouble(3, Double.parseDouble(txtPrecio.getText()));
            insertar.setDouble(4, Double.parseDouble(txtCantidad.getText()));
            insertar.setString(5, txtCategoria.getText());

            insertar.executeUpdate(); //AÑADE ALERT PARA NOTIFICAR QUE SE REALIZO CORRECTAMENTE
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setHeaderText(null);
            alert.setTitle("Create");
            alert.setContentText("Producto creado");
            alert.showAndWait();

            String codigo = (String) this.txtCodigo.getText();
            String nombre = (String) this.txtNombre.getText();
            double precio = Double.parseDouble(this.txtPrecio.getText());
            double cantidad = Double.parseDouble(this.txtCantidad.getText());
            String categoria = (String) this.txtCategoria.getText();

            Producto_Inventario p = new Producto_Inventario(codigo, nombre, precio, cantidad, categoria);

            this.productos.add(p);

            this.tbvDatos.setItems(productos);

            txtCodigo.clear();
            txtNombre.clear();
            txtPrecio.clear();
            txtCantidad.clear();
            txtCategoria.clear();

        } catch (SQLException e) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setHeaderText(null);
            alert.setTitle("Error");
            alert.setContentText(e.toString());
            alert.showAndWait();
            
            

        }

    }

    @FXML
    private void update(ActionEvent event) {
        PreparedStatement ps = null;
        ResultSet rs = null;
        PreparedStatement ps2 = null;
        ResultSet rs2 = null;
        Conexion conn = new Conexion();
        Connection con = conn.getConection();
        try {
            String sql = "UPDATE product SET Codigo=? ,Nombre=?, Precio=?,Cantidad = ? ,Categoria = ? Where Codigo=?";
            ps = con.prepareStatement(sql);
            ps.setString(1, txtCodigo.getText());
            ps.setString(2, txtNombre.getText());
            ps.setDouble(3, Double.parseDouble(txtPrecio.getText()));
            ps.setDouble(4, Double.parseDouble(txtCantidad.getText()));
            ps.setString(5, txtCategoria.getText());
            ps.setString(6, codigoPrimitivo);

            ps.executeUpdate();
            System.out.println(ps.executeUpdate());

            //VA A METER EL CODIGO ACTUALIZADO A LA TABLA NUEVAMENTE
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setHeaderText(null);
            alert.setTitle("Update");
            alert.setContentText("Producto actualizado");
            alert.showAndWait();

            String codigo = (String) this.txtCodigo.getText();
            String nombre = (String) this.txtNombre.getText();
            double precio = Double.parseDouble(this.txtPrecio.getText());
            double cantidad = Double.parseDouble(this.txtCantidad.getText());
            String categoria = (String) this.txtCategoria.getText();

            Producto_Inventario p = new Producto_Inventario(codigo, nombre, precio, cantidad, categoria);

            this.productos.add(p);

            this.tbvDatos.setItems(productos);

            txtCodigo.clear();
            txtNombre.clear();
            txtPrecio.clear();
            txtCantidad.clear();
            txtCategoria.clear();

        } catch (SQLException e) {
            System.out.println(e);
        }
    }

    @FXML
    private void delete(ActionEvent event) {
        PreparedStatement ps = null;
        ResultSet rs = null;
        PreparedStatement ps2 = null;
        ResultSet rs2 = null;
        Conexion conn = new Conexion();
        Connection con = conn.getConection();
        try {
            String sql = "DELETE FROM product WHERE codigo = ?";
            ps = con.prepareStatement(sql);
            ps.setString(1, txtCodigo.getText());

            ps.executeUpdate();
            //tbvDatos.getSelectionModel().clearAndSelect(0);
            //ALERT DE NOTIFICACION PARA INDICAR QUE SE REALIZO CORRECTAMENTE
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setHeaderText(null);
            alert.setTitle("Delete");
            alert.setContentText("Producto eliminado");
            alert.showAndWait();
            tbvDatos.getItems().clear();
        } catch (SQLException e) {
            System.out.println(e);
        }
    }

    @FXML
    private void buscar(ActionEvent event) {
        PreparedStatement ps = null;
        ResultSet rs = null;
        PreparedStatement ps2 = null;
        ResultSet rs2 = null;
        Conexion conn = new Conexion();
        Connection con = conn.getConection();
        boolean cajaCodigoVacia = false;
        boolean cajaNombreVacia = false;

        try {

            // ps2 = con.prepareStatement("Select count(*) from product");
            //rs2 = ps2.executeQuery()
            //System.out.println(rs2.next());
            //int tamaño = rs2.getInt("count(*)");
            //System.out.println(tamaño);
            /*if (rs2.next() == true) {
                int tamaño = rs2.getInt("count(*)");
                System.out.println(tamaño);
                for (int i = 1; i <= tamaño; i++) {
                    ps = con.prepareStatement("SELECT * FROM product WHERE id = ?");
                    ps.setString(1, String.valueOf(i));
                    rs = ps.executeQuery();
                    rs.next();*/
            if (txtCodigo.toString().isEmpty()) {
                cajaCodigoVacia = true;
            }
            if (txtNombre.toString().isEmpty()) {
                cajaNombreVacia = true;
            }

            String sql = "SELECT * FROM Product";
            System.out.println(sql);
            ps = con.prepareStatement(sql);
            rs = ps.executeQuery();

            ResultSetMetaData rsMd = rs.getMetaData();

            int cantidadColumnas = rsMd.getColumnCount();

            while (rs.next()) {

                Object[] filas = new Object[cantidadColumnas]; //Arreglo de tipo objeto del tamaño de la cantidad de columnas
                System.out.println(cantidadColumnas);
                for (int i = 1; i <= cantidadColumnas; i++) {
                    filas[i - 1] = rs.getObject(i); //Aqui va guardando valor tras valor dentro del arreglo

                }
                String codigo = (String) filas[0];
                String nombre = (String) filas[1];
                String precioString = String.valueOf(filas[2]);
                String cantidadString = String.valueOf(filas[3]);
                String categoria = (String) filas[4];
                Double precio = Double.parseDouble(precioString);
                Double cantidad = Double.parseDouble(cantidadString);

                txtCodigo.setText(codigo);
                txtNombre.setText(nombre);
                txtPrecio.setText(precioString);
                txtCategoria.setText(categoria);
                txtCantidad.setText(cantidadString);

                String cod = (String) this.txtCodigo.getText();
                String nomb = (String) this.txtNombre.getText();
                double pre = Double.parseDouble(this.txtPrecio.getText());
                double cant = Double.parseDouble(this.txtCantidad.getText());
                String cat = (String) this.txtCategoria.getText();

                Producto_Inventario p = new Producto_Inventario(cod, nomb, pre, cant, cat);

                this.productos.add(p);

                this.tbvDatos.setItems(productos);
                
                limpiarCajas();

            }

            /*txtCodigo.setText(rs.getString("codigo"));
            txtNombre.setText(rs.getString("nombre"));
            txtPrecio.setText(rs.getString("precio"));
            txtCantidad.setText(rs.getString("cantidad"));

            String codigo = (String) this.txtCodigo.getText();
            String nombre = (String) this.txtNombre.getText();
            double precio = Double.parseDouble(this.txtPrecio.getText());
            double cantidad = Double.parseDouble(this.txtCantidad.getText());
            String categoria = this.txtCategoria.getText();

            Producto p = new Producto(codigo, nombre, precio, cantidad, categoria);

            this.productos.add(p);

            this.tbvDatos.setItems(productos);
            contador++;*/
 /*if (rs.next()) {

                txt.setText(rs.getString("nombre"));
                txtEdad.setText(rs.getString("precio"));

            } else {
                JOptionPane.showMessageDialog(null, "No existe la persona con esta clave");
            }*/ } catch (SQLException e) {
            System.err.println(e);
        }
    }

    @FXML
    private void buscarNombreConCodigo(KeyEvent event) {

        switch (event.getCode()) {
            case TAB:
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
                        txtCantidad.setText(rs.getString("cantidad"));
                        txtCategoria.setText(rs.getString("categoria"));

                        String codigo = (String) this.txtCodigo.getText();
                        String nombre = (String) this.txtNombre.getText();
                        double precio = Double.parseDouble(this.txtPrecio.getText());
                        double cantidad = Double.parseDouble(this.txtCantidad.getText());
                        String categoria = (String) this.txtCategoria.getText();

                        Producto_Inventario p = new Producto_Inventario(codigo, nombre, precio, cantidad, categoria);

                        this.productos.add(p);

                        this.tbvDatos.setItems(productos);

                        txtCodigo.clear();
                        txtNombre.clear();
                        txtPrecio.clear();
                        txtCantidad.clear();
                        txtCategoria.clear();

                    } else {
                        JOptionPane.showMessageDialog(null, "No existe producto con esta clave");
                    }
                } catch (SQLException e) {
                    System.err.println(e);
                }
                break;
        }
    }

    @FXML
    private void buscarNombreConEnter(javafx.scene.input.KeyEvent event) {
        switch (event.getCode()) {
            case TAB:
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
                        txtCantidad.setText(rs.getString("cantidad"));
                        txtCategoria.setText(rs.getString("categoria"));

                        String codigo = (String) this.txtCodigo.getText();
                        String nombre = (String) this.txtNombre.getText();
                        double precio = Double.parseDouble(this.txtPrecio.getText());
                        double cantidad = Double.parseDouble(this.txtCantidad.getText());
                        String categoria = (String) this.txtCategoria.getText();

                        Producto_Inventario p = new Producto_Inventario(codigo, nombre, precio, cantidad, categoria);

                        this.productos.add(p);

                        this.tbvDatos.setItems(productos);

                        txtCodigo.clear();
                        txtNombre.clear();
                        txtPrecio.clear();
                        txtCantidad.clear();
                        txtCategoria.clear();

                    } else {
                        JOptionPane.showMessageDialog(null, "No existe producto con esta clave");
                    }
                } catch (SQLException e) {
                    System.err.println(e);
                }
                break;
        }
    }

    @FXML
    private void activarAutoCompletado(MouseEvent event) {
        /* PreparedStatement ps = null;
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
                    TextFields.bindAutoCompletion(txtNombre, filas[i]);
                }

            }

            //}
        } catch (SQLException e) {
            System.err.println(e);
        }*/
    }

    @FXML
    private void seleccionar(MouseEvent event) {

        Producto_Inventario p = tbvDatos.getSelectionModel().getSelectedItem(); //Guarda los datos seleccionados del modelo, del item y los guarda en el objeto p

        if (p != null) {
            this.txtCodigo.setText(p.getCodigo()); //Va obtener los datos guardados en el objeto y los pegara en las cajas de texto
            this.txtNombre.setText(p.getNombre());
            this.txtPrecio.setText(p.getPrecio() + "");
            this.txtCantidad.setText(p.getCantidad() + "");
            this.txtCategoria.setText(p.getCategoria());

        }
        codigoPrimitivo = txtCodigo.getText();
    }

    @FXML
    private void Limpiar(ActionEvent event) {
        txtCodigo.clear();
        txtNombre.clear();
        txtCantidad.clear();
        txtPrecio.clear();
        txtCategoria.clear();
        tbvDatos.getItems().clear();
    }

    @FXML
    private void AutoCompletar(ActionEvent event) {

        PreparedStatement ps = null;
        ResultSet rs = null;
        Conexion conn = new Conexion();
        Connection con = conn.getConection();

        try {
            if (cbAutoCompletado.isSelected()) {
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
            }else if(!cbAutoCompletado.isSelected()){
                TextFields.bindAutoCompletion(txtNombre, ""); ///
            }

            //}
        } catch (SQLException e) {
            System.err.println(e);
        }

    }
    public void limpiarCajas() {
        txtCodigo.clear();
        txtNombre.clear();
        txtPrecio.clear();
        txtCantidad.clear();
        txtCategoria.clear();

    }

}
