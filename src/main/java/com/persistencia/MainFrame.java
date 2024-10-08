/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.persistencia;

import com.objetos.Paciente;
import com.formdev.flatlaf.FlatDarculaLaf;
import com.formdev.flatlaf.FlatIntelliJLaf;
import java.io.FileReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.List;
import java.util.Optional;
import java.util.Properties;
import java.util.Scanner;
import java.util.Vector;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JPanel;
import javax.swing.JTable;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author
 */
public class MainFrame extends javax.swing.JFrame {

    // Valores para la conexión a la base de datos (su nombre, URL, Usuario y Contraseña)
    private static final String DB_NAME = "prueba";
    private static final String DB_URL = "jdbc:postgresql://localhost:5432/" + DB_NAME;
    private static final String DB_USER = "postgres"; // Revisar estos nombres, son diferentes en mi pc
    private static final String DB_PWD = "admin";
    
    // Mensajes de error
    private static final String ERROR_MSG_INSERT = "Error al intentar dar de alta a esta persona.";
    private static final String ERROR_MSG_INSERT_INPUT = "No se admiten campos vacíos.";
    
    // Objetos utilizados para interactuar con la base de datos
    // (conexión, realizar consultas con y sin parámetros, y recibir los resultados)
    private static Connection conn = null;
    private static Statement query = null;
    private static PreparedStatement p_query = null;
    private static ResultSet result = null;



    /**
     * Creates new form MainFrame
     */
    public MainFrame() throws SQLException {
        // Espeecificar el tema flatlaf (look and feel)
        try {
            UIManager.setLookAndFeel(new FlatIntelliJLaf());
        } catch (UnsupportedLookAndFeelException ex) {
            System.out.println("Error al cargar la interfaz");
        }
        
        initComponents();
        
        
        label_error.setVisible(false);

        // Una vez creado el formulario e inicializado sus componentes ↑↑↑
        // nos enlazamos con el DBMS para conectarnos a la base de datos solicitada
        // utilizando las credenciales correspondientes
        //conn = DriverManager.getConnection(DB_URL, DB_USER, DB_PWD);
        conn = DataBaseSingleton.getInstance().getConnection();
        
        
        // una vez conectados, nuestro programa creará las tabas que sean necesarias
        // para funcionar, en el caso de que ya no estén creadas
        // (de ahí el "IF NOT EXISTS" luego del "CREATE TABLE").
        // En este ejemplo básico vamos a crear la tabla "ejemplo_personas"
        query = conn.createStatement();
        
        
        
        // El dao va a llamar dentro de la clase ManagerPaciente en la capa de negocio/logica
        
        Boolean firstTime = false;
        
        
        
        
        //query.execute("DROP TABLE IF EXISTS sitios,cuadriculas,cajas,personas,objetos,liticos,ceramicos"); // Elimina todas las tablas (Evita problema de claves repetidas al hacer la precarga)
        
        try{
            query.execute("SELECT * FROM pacientes"); // Consulta de prueba para detectar que existen las tablas
        }catch(Exception ex){
            firstTime = true;
            System.out.println("Es la 1ra vez que se ejecuta el programa");
        }
        
        if (firstTime){
        // Crear las tablas
        // Tabla de prueba de pacientes
        query.execute("CREATE TABLE IF NOT EXISTS pacientes("
                + "dni INTEGER NOT NULL, "
                + "nombre TEXT NOT NULL, "
                + "apellido TEXT NOT NULL, "
                + "sexo INTEGER NOT NULL, "
                + "edad INTEGER NOT NULL, "
                + "domicilio TEXT NOT NULL, "
                + "telefono TEXT NOT NULL, "
                + "correoElectronico TEXT NOT NULL, "
                + "activo INT NOT NULL, "
                + "PRIMARY KEY (dni))");
        
        
        
        query.execute("CREATE TABLE IF NOT EXISTS ejemplo_personas("
                + "DNI INTEGER NOT NULL, "
                + "nombre TEXT NOT NULL, "
                + "edad INTEGER NOT NULL, "
                + "PRIMARY KEY (DNI))");
        
        query.execute("CREATE TABLE IF NOT EXISTS Sitios("
                + "s_cod TEXT NOT NULL, "
                + "s_localidad TEXT NOT NULL, "
                + "PRIMARY KEY (s_Cod))");
        
        query.execute("CREATE TABLE IF NOT EXISTS Cuadriculas("
                + "cu_cod TEXT NOT NULL, "
                + "s_cod_dividido TEXT NOT NULL, "
                + "PRIMARY KEY (cu_cod),"
                + "FOREIGN KEY (s_cod_dividido) REFERENCES Sitios(s_cod))");
        
        query.execute("CREATE TABLE IF NOT EXISTS cajas("
                + "ca_cod TEXT NOT NULL, "
                + "ca_fecha DATE NOT NULL, "
                + "ca_lugar TEXT NOT NULL, "
                + "PRIMARY KEY (ca_cod))");
        
        query.execute("CREATE TABLE IF NOT EXISTS personas("
                + "p_dni INTEGER NOT NULL, "
                + "p_nombre TEXT NOT NULL, "
                + "p_apellido TEXT NOT NULL, "
                + "p_email TEXT NOT NULL, "
                + "p_telefono TEXT NOT NULL, "
                + "PRIMARY KEY (p_dni))");
        
        query.execute("CREATE TABLE IF NOT EXISTS objetos("
                + "o_cod TEXT NOT NULL, "
                + "o_nombre TEXT NOT NULL, "
                + "o_tipoExtraccion TEXT NOT NULL, "
                + "o_alto INTEGER NOT NULL, "
                + "o_largo INTEGER NOT NULL, "
                + "o_espesor INTEGER NOT NULL, "
                + "o_peso INTEGER NOT NULL, "
                + "o_cantidad INTEGER NOT NULL, "                
                + "o_fechaRegistro DATE NOT NULL, "
                + "o_descripcion TEXT NOT NULL, "
                + "o_origen TEXT NOT NULL, "
                + "cu_cod_asocia TEXT NOT NULL, "
                + "ca_cod_contiene TEXT NOT NULL, "
                + "p_dni_ingresa INTEGER NOT NULL, "
                
                // dudas sobre {L,C} (es)
                + "o_es CHAR(1) NOT NULL, "
                
                + "PRIMARY KEY (o_cod),"
                + "FOREIGN KEY (cu_cod_asocia) REFERENCES cuadriculas(cu_cod), "
                + "FOREIGN KEY (ca_cod_contiene) REFERENCES cajas(ca_cod), "
                + "FOREIGN KEY (p_dni_ingresa) REFERENCES personas(p_dni)) ");
        
        query.execute("CREATE TABLE IF NOT EXISTS liticos("
                + "o_cod TEXT NOT NULL,"
                + "l_fechaCreacion INTEGER NOT NULL,"
                + "PRIMARY KEY (o_cod),"
                + "FOREIGN KEY (o_cod) REFERENCES objetos(o_cod)) ");
        
        query.execute("CREATE TABLE IF NOT EXISTS ceramicos("
                + "o_cod TEXT NOT NULL,"
                + "c_color TEXT NOT NULL,"
                + "PRIMARY KEY (o_cod),"
                + "FOREIGN KEY (o_cod) REFERENCES objetos(o_cod)) ");
        
        
       
        // Precarga de datos
        StringBuilder sb = new StringBuilder();
        try{
            File f = new File("Inserta_Datos 2023.sql");
            Scanner sc = new Scanner(f);
            while(sc.hasNextLine()){
                String line = sc.nextLine();
                System.out.println(line);
                
                if (line.startsWith("insert into Cajas")){
                    String[] parsedLine = line.split(",");
                    
                    //System.out.println(parsedLine[3]);
                    
                    // Reformateo de Fecha para que pueda ser aceptada por el tipo DATE de SQL
                    String[] parsedDate = parsedLine[3].replace("'", "").trim().split("-");
                    
                    String tmp = parsedDate[0];
                    parsedDate[0] = parsedDate[2];
                    parsedDate[2] = tmp;
                    
                    parsedLine[3] =  "'"+String.join("-", List.of(parsedDate))+"'";
                    
                    String queryString = String.join(",", List.of(parsedLine));
                    
                    System.out.println(queryString);
                    query.execute(queryString);
                }
                else if(line.startsWith("insert into Objetos")){
                    String[] parsedLine = line.split(",");
                    
//                    System.out.println(parsedLine[22]);
                    
                    // Reformateo de Fecha para que pueda ser aceptada por el tipo DATE de SQL
                    String[] parsedDate = parsedLine[22].replace("'", "").trim().split("-");
                    
                    String tmp = parsedDate[0];
                    parsedDate[0] = parsedDate[2];
                    parsedDate[2] = tmp;
                    
                    parsedLine[22] =  "'"+String.join("-", List.of(parsedDate))+"'";
                    String queryString = String.join(",", List.of(parsedLine));
                    
                    System.out.println(queryString);
                    query.execute(queryString);
                }
                else{
                    query.execute(line);
                }
            //sb.append(sc.nextLine());
            //System.out.println(sc.nextLine());
            }
            sc.close();
        }
        catch(FileNotFoundException e){
            System.out.println("archivo no encontrado");
        }
        
        //System.out.println(sb.toString());
        //query.execute(sb.toString());
        
        // Requisitos del lab
        // Inserta a Rodolphe Rominov
        query.execute("INSERT INTO personas (P_Nombre, P_Apellido, P_Email, P_Dni, P_Telefono) values ('Rodolphe', 'Rominov', 'rrominovm@sciencedaily.com',25544555, '7135986253');");
        
        // Elmina subtipos de objetos
        query.execute("DELETE FROM ceramicos WHERE o_cod IN (SELECT o_cod FROM objetos WHERE p_dni_ingresa = (SELECT p_dni FROM personas WHERE p_nombre = 'Benji' AND p_apellido = 'Colchett'))");
        query.execute("DELETE FROM liticos WHERE o_cod IN (SELECT o_cod FROM objetos WHERE p_dni_ingresa = (SELECT p_dni FROM personas WHERE p_nombre = 'Benji' AND p_apellido = 'Colchett'))");
        
        // Elimina objetos generales
        query.execute("DELETE FROM objetos WHERE p_dni_ingresa = (SELECT p_dni FROM personas WHERE p_nombre = 'Benji' AND p_apellido = 'Colchett')");
        
        // Elimina a Benji
        query.execute("DELETE FROM personas WHERE p_nombre = 'Benji' AND p_apellido = 'Colchett'");
        
        // Inicializamos/Actualizamos la lista de personas del formulario
        // para que muestre las personas que ya están cargadas en el sistema
        
        }
        
        // 1
        updateListaResultados(jTablaObjetos, "objetos");
        updateListaResultados(jTablaPersonas, "personas");
        
        
        // 5
//        queryToTableUpdate(jTablaPersonas,"SELECT o_cod,o_nombre FROM objetos WHERE o_fechaRegistro BETWEEN '2022-01-03' AND '2023-01-07'");
        
        
        // 6
//        queryToTableUpdate(jTablaPersonas,"SELECT o_es, COUNT(o_es) FROM objetos GROUP BY o_es");
        
        // 7
        /*
        ResultSet personas = query.executeQuery("SELECT COUNT(p_dni) FROM personas");
        personas.next();
        
        ResultSet cuadriculas = query.executeQuery("SELECT COUNT(cu_cod) FROM cuadriculas");
        cuadriculas.next();
        
        ResultSet objetos = query.executeQuery("SELECT COUNT(o_cod) FROM objetos");
        objetos.next();
        
        ResultSet cajas = query.executeQuery("SELECT COUNT(ca_cod) FROM cajas");
        cajas.next();
        
        
        System.out.println("Nro Personas: " + personas.getString(1));
        System.out.println("Nro Cuadriculas: " + cuadriculas.getString(1));
        System.out.println("Nro Objetos: " + objetos.getString(1));
        System.out.println("Nro Cajas: " + cajas.getString(1));
        */
        
        // 8
        //queryToTableUpdate(jTablaPersonas,"SELECT p_nombre,p_apellido,COUNT(p_dni_ingresa) FROM personas,objetos WHERE p_dni = p_dni_ingresa GROUP BY p_nombre,p_apellido");
        
        // 9
        //queryToTableUpdate(jTablaPersonas,"SELECT ca_cod,ca_lugar FROM cajas EXCEPT SELECT ca_cod,ca_lugar FROM cajas,objetos WHERE ca_cod = ca_cod_contiene GROUP BY ca_cod HAVING COUNT(ca_cod) > 0" );
        
        // 10
        //queryToTableUpdate(jTablaPersonas,"SELECT MAX(o_peso),MIN(o_peso),AVG(o_peso) FROM objetos");
        
        // 11
        //queryToTableUpdate(jTablaPersonas,"SELECT ca_cod,SUM(o_peso) FROM cajas,objetos WHERE ca_cod = ca_cod_contiene GROUP BY ca_cod");
        updateExtraQueries();
        
        
//        DaoOld pacienteDao = new PacienteDAOSQL();
//        Optional p = pacienteDao.get(23);
//        if (p.isPresent()){
//            System.out.println(p.get());
//            
//        }
//        
//        pacienteDao.save(new Paciente("juan","perez",23,1,30,"","",""));
//        pacienteDao.save(new Paciente("juan1","perez1",24,1,30,"","",""));
//        pacienteDao.save(new Paciente("juan2","perez2",25,1,30,"","",""));
//        pacienteDao.save(new Paciente("juan3","perez43",26,1,30,"","",""));
//        pacienteDao.save(new Paciente("jasdwuan3","pereawdwz43",2326,1,30,"","",""));
//        
//        
//        // modificar
//        Object param[] = {25,"josh","broh",1,12,"","","",1};
//        pacienteDao.update((Paciente) pacienteDao.get(25).get(), param);
//        
//        
//        System.out.println(pacienteDao.getAll());
//        
//        pacienteDao.delete(new Paciente("juan3","perez43",26,1,30,"","",""));
//        
//        if (p.isPresent()){
//            pacienteDao.delete((Paciente) p.get());
//        }
//        
//        System.out.println(pacienteDao.getAll());
//        
//        
//        queryToTableUpdate(jTablaPersonas,"SELECT * FROM pacientes");



        Dao pacienteDao = new DAOPacienteSQL();
        Optional p = pacienteDao.get(2326);
        if (p.isPresent()){
            System.out.println(p.get());
        }
        
        if (pacienteDao.get(23).isEmpty()){
            pacienteDao.save(new Paciente(23,"juan","perez",1,30,"","",""));
        }
        
        pacienteDao.save(new Paciente(22,"eeeeh","qwe",1,90,"casita","",""));
        pacienteDao.save(new Paciente(24,"juan1","perez1",1,30,"","",""));
        pacienteDao.save(new Paciente(25,"juan2","perez2",1,30,"","",""));
        pacienteDao.save(new Paciente(26,"juan3","perez43",1,30,"","",""));
        pacienteDao.save(new Paciente(2326,"jasdwuan3","pereawdwz43",1,30,"","",""));
        
        
        // modificar
        Paciente pModific = new Paciente(25,"joshadw","br22221oh",1,12,"","","");
        pacienteDao.update(pModific);
        
        
        System.out.println(pacienteDao.getAll());
        
        pacienteDao.delete(22);
        pacienteDao.delete(24);
        
        
        System.out.println(pacienteDao.getAll());
        
        
        queryToTableUpdate(jTablaPersonas,"SELECT * FROM pacientes");


        
    }
    
    
    private void updateExtraQueries(){
        try{
            // 6
        queryToTableUpdate(jTableNroLiticoCeramicos,"SELECT o_es, COUNT(o_es) FROM objetos GROUP BY o_es");
        
        // 7
        
        ResultSet personas = query.executeQuery("SELECT COUNT(p_dni) FROM personas");
        personas.next();
        
        ResultSet cuadriculas = query.executeQuery("SELECT COUNT(cu_cod) FROM cuadriculas");
        cuadriculas.next();
        
        ResultSet objetos = query.executeQuery("SELECT COUNT(o_cod) FROM objetos");
        objetos.next();
        
        ResultSet cajas = query.executeQuery("SELECT COUNT(ca_cod) FROM cajas");
        cajas.next();
        
        jLabelCantPersonas.setText("Cantidad Personas: " + personas.getString(1));
        jLabelCantCuadrantes.setText("Cantidad Cuadrantes: " + cuadriculas.getString(1));
        jLabelCantObjetos.setText("Cantidad Objetos: " + objetos.getString(1));
        jLabelCantCajas.setText("Cantidad Cajas: " + cajas.getString(1));
        
//        System.out.println("Nro Personas: " + personas.getString(1));
//        System.out.println("Nro Cuadriculas: " + cuadriculas.getString(1));
//        System.out.println("Nro Objetos: " + objetos.getString(1));
//        System.out.println("Nro Cajas: " + cajas.getString(1));
        
        
        // 8
        queryToTableUpdate(jTableObjArqueologo,"SELECT p_nombre,p_apellido,COUNT(p_dni_ingresa) FROM personas,objetos WHERE p_dni = p_dni_ingresa GROUP BY p_nombre,p_apellido");
        
        // 9
        queryToTableUpdate(jTableCajasVacias,"SELECT ca_cod,ca_lugar FROM cajas EXCEPT SELECT ca_cod,ca_lugar FROM cajas,objetos WHERE ca_cod = ca_cod_contiene GROUP BY ca_cod HAVING COUNT(ca_cod) > 0" );
        
        // 10
        queryToTableUpdate(jTableMaxMinAvg,"SELECT MAX(o_peso),MIN(o_peso),AVG(o_peso) FROM objetos");
        
        // 11
        queryToTableUpdate(jTablePesoCaja,"SELECT ca_cod,SUM(o_peso) FROM cajas,objetos WHERE ca_cod = ca_cod_contiene GROUP BY ca_cod");
        }
        catch(Exception ex){
            System.out.println("Error al actualizar consultas");
        }
    }
    
    
    private void queryToTableUpdate(JTable panel, String sqlquery) throws SQLException {
        // realiza la consulta dada por "sqlquery" a la base de datos
        // utilizando la conexión ya establecida (almacenada en la variable conn).
        // Finalmente muestra el resultado de la consulta en la tabla principal
        // del programa (JTable panel).
        query = conn.createStatement();
        result = query.executeQuery(sqlquery);
        panel.setModel(resultToTable(result));
    }
    
    // Capa de interfaz (actualiza resultados en las tablas)
    private void updateListaResultados(JTable panel, String table) throws SQLException {
        // realiza la consulta "SELECT * FROM ejemplo_personas" a la base de datos
        // utilizando la conexión ya establecida (almacenada en la variable conn).
        // Finalmente muestra el resultado de la consulta en la tabla principal
        // del programa (jTablaPersonas).
        query = conn.createStatement();
        result = query.executeQuery("SELECT * FROM " + table);
        panel.setModel(resultToTable(result));
    }

    private static DefaultTableModel resultToTable(ResultSet rs) throws SQLException {
        // Esta es una función auxiliar que les permite convertir los resultados de las
        // consultas (ResultSet) a un modelo interpretable para la tabla mostrada en pantalla
        // (es decir, para un objeto de tipo JTable, ver línea 81)
        ResultSetMetaData metaData = rs.getMetaData();

        // creando las culmnas de la tabla
        Vector<String> columnNames = new Vector<String>();
        int columnCount = metaData.getColumnCount();
        for (int column = 1; column <= columnCount; column++) {
            columnNames.add(metaData.getColumnName(column));
        }

        // creando las filas de la tabla con los resultados de la consulta
        Vector<Vector<Object>> data = new Vector<Vector<Object>>();
        while (rs.next()) {
            Vector<Object> vector = new Vector<Object>();
            for (int columnIndex = 1; columnIndex <= columnCount; columnIndex++) {
                vector.add(rs.getObject(columnIndex));
            }
            data.add(vector);
        }

        return new DefaultTableModel(data, columnNames);
    }
//
//    private void updateForm() throws SQLException {
//        // actualizar y limpiar el formulario luego de una operación exitosa
//        jsDni1.setValue(0);
//        jtNombre1.setText("");
//        jsEdad1.setValue(0);
//        jsDniDelete.setValue(0);
//        
//        label_error.setVisible(false);
//        updateListaResultados(jTablaPersonas,"ejemplo_personas");
//    }
    

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jTabbedPane1 = new javax.swing.JTabbedPane();
        jPanel1 = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        jTablaPersonas = new javax.swing.JTable();
        jPanel4 = new javax.swing.JPanel();
        jScrollPane2 = new javax.swing.JScrollPane();
        jTablaObjetos = new javax.swing.JTable();
        jPanel7 = new javax.swing.JPanel();
        jLabel3 = new javax.swing.JLabel();
        oCod = new javax.swing.JTextField();
        oNombre = new javax.swing.JTextField();
        jLabel11 = new javax.swing.JLabel();
        oTipoExtraccion = new javax.swing.JTextField();
        jLabel12 = new javax.swing.JLabel();
        jLabel13 = new javax.swing.JLabel();
        oAlto = new javax.swing.JSpinner();
        oLargo = new javax.swing.JSpinner();
        jLabel14 = new javax.swing.JLabel();
        jLabel15 = new javax.swing.JLabel();
        oEspesor = new javax.swing.JSpinner();
        jLabel16 = new javax.swing.JLabel();
        oCantidad = new javax.swing.JSpinner();
        jLabel17 = new javax.swing.JLabel();
        oFechaIngreso = new javax.swing.JFormattedTextField();
        jLabel18 = new javax.swing.JLabel();
        oDescripcion = new javax.swing.JTextField();
        jLabel19 = new javax.swing.JLabel();
        oOrigen = new javax.swing.JTextField();
        jLabel20 = new javax.swing.JLabel();
        cuCodAsocia = new javax.swing.JTextField();
        jLabel21 = new javax.swing.JLabel();
        caCodContiene = new javax.swing.JTextField();
        jLabel22 = new javax.swing.JLabel();
        pDniIngresa = new javax.swing.JSpinner();
        jRadioLitico = new javax.swing.JRadioButton();
        jRadioCeramico = new javax.swing.JRadioButton();
        jLabel23 = new javax.swing.JLabel();
        oFechaCreacion = new javax.swing.JFormattedTextField();
        jLabel24 = new javax.swing.JLabel();
        oColor = new javax.swing.JTextField();
        jButton1 = new javax.swing.JButton();
        oPeso = new javax.swing.JSpinner();
        jLabel26 = new javax.swing.JLabel();
        jPanel8 = new javax.swing.JPanel();
        jButton2 = new javax.swing.JButton();
        jLabel25 = new javax.swing.JLabel();
        jTextOCodDelete = new javax.swing.JTextField();
        jPanel2 = new javax.swing.JPanel();
        jButton3 = new javax.swing.JButton();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jFormattedTextDate2 = new javax.swing.JFormattedTextField();
        jFormattedTextDate1 = new javax.swing.JFormattedTextField();
        jScrollPane4 = new javax.swing.JScrollPane();
        jTablaDosFechas = new javax.swing.JTable();
        jPanel3 = new javax.swing.JPanel();
        jLabel4 = new javax.swing.JLabel();
        jbEliminar = new javax.swing.JButton();
        jScrollPane5 = new javax.swing.JScrollPane();
        jTableCajaContenido = new javax.swing.JTable();
        jTextCodCajaContenido = new javax.swing.JTextField();
        jPanel9 = new javax.swing.JPanel();
        jTabbedPane3 = new javax.swing.JTabbedPane();
        jPanel10 = new javax.swing.JPanel();
        jScrollPane3 = new javax.swing.JScrollPane();
        jTableNroLiticoCeramicos = new javax.swing.JTable();
        jPanel11 = new javax.swing.JPanel();
        jLabelCantPersonas = new javax.swing.JLabel();
        jLabelCantCuadrantes = new javax.swing.JLabel();
        jLabelCantObjetos = new javax.swing.JLabel();
        jLabelCantCajas = new javax.swing.JLabel();
        jPanel12 = new javax.swing.JPanel();
        jScrollPane7 = new javax.swing.JScrollPane();
        jTableObjArqueologo = new javax.swing.JTable();
        jPanel13 = new javax.swing.JPanel();
        jScrollPane8 = new javax.swing.JScrollPane();
        jTableCajasVacias = new javax.swing.JTable();
        jPanel14 = new javax.swing.JPanel();
        jScrollPane9 = new javax.swing.JScrollPane();
        jTableMaxMinAvg = new javax.swing.JTable();
        jPanel15 = new javax.swing.JPanel();
        jScrollPane10 = new javax.swing.JScrollPane();
        jTablePesoCaja = new javax.swing.JTable();
        label_error = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("Base de Datos (Laboratorio 2 SQL)");
        addWindowListener(new java.awt.event.WindowAdapter() {
            public void windowClosed(java.awt.event.WindowEvent evt) {
                formWindowClosed(evt);
            }
        });

        jTablaPersonas.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Title 1", "Title 2", "Title 3", "Title 4"
            }
        ));
        jScrollPane1.setViewportView(jTablaPersonas);
        jTablaPersonas.getAccessibleContext().setAccessibleName("");
        jTablaPersonas.getAccessibleContext().setAccessibleDescription("");

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 529, Short.MAX_VALUE)
                .addGap(43, 43, 43))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 269, Short.MAX_VALUE)
        );

        jTabbedPane1.addTab("Lista de Personas", jPanel1);

        jTablaObjetos.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Title 1", "Title 2", "Title 3", "Title 4"
            }
        ));
        jScrollPane2.setViewportView(jTablaObjetos);

        javax.swing.GroupLayout jPanel4Layout = new javax.swing.GroupLayout(jPanel4);
        jPanel4.setLayout(jPanel4Layout);
        jPanel4Layout.setHorizontalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jScrollPane2, javax.swing.GroupLayout.DEFAULT_SIZE, 572, Short.MAX_VALUE)
        );
        jPanel4Layout.setVerticalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jScrollPane2, javax.swing.GroupLayout.DEFAULT_SIZE, 269, Short.MAX_VALUE)
        );

        jTabbedPane1.addTab("Lista de Objetos", jPanel4);

        jLabel3.setText("Codigo:");

        oCod.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                oCodActionPerformed(evt);
            }
        });

        oNombre.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                oNombreActionPerformed(evt);
            }
        });

        jLabel11.setText("Nombre:");

        oTipoExtraccion.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                oTipoExtraccionActionPerformed(evt);
            }
        });

        jLabel12.setText("Tipo Extraccion:");

        jLabel13.setText("Alto:");

        jLabel14.setText("Largo:");

        jLabel15.setText("Espesor:");

        jLabel16.setText("Cantidad:");

        jLabel17.setText("Fecha Ingreso");

        jLabel18.setText("Descripcion");

        oDescripcion.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                oDescripcionActionPerformed(evt);
            }
        });

        jLabel19.setText("Origen");

        oOrigen.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                oOrigenActionPerformed(evt);
            }
        });

        jLabel20.setText("cod cuadrante");

        cuCodAsocia.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cuCodAsociaActionPerformed(evt);
            }
        });

        jLabel21.setText("cod caja");

        caCodContiene.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                caCodContieneActionPerformed(evt);
            }
        });

        jLabel22.setText("dni ingresa");

        jRadioLitico.setText("Litico");
        jRadioLitico.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jRadioLiticoActionPerformed(evt);
            }
        });

        jRadioCeramico.setSelected(true);
        jRadioCeramico.setText("Ceramico");
        jRadioCeramico.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jRadioCeramicoActionPerformed(evt);
            }
        });

        jLabel23.setText("Fecha Creacion");

        jLabel24.setText("Color");

        oColor.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                oColorActionPerformed(evt);
            }
        });

        jButton1.setText("Guardar");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        jLabel26.setText("Peso:");

        javax.swing.GroupLayout jPanel7Layout = new javax.swing.GroupLayout(jPanel7);
        jPanel7.setLayout(jPanel7Layout);
        jPanel7Layout.setHorizontalGroup(
            jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel7Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel7Layout.createSequentialGroup()
                        .addComponent(jRadioLitico)
                        .addGap(27, 27, 27)
                        .addComponent(jLabel23)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(oFechaCreacion))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel7Layout.createSequentialGroup()
                        .addComponent(jLabel14)
                        .addGap(18, 18, 18)
                        .addComponent(oLargo)
                        .addGap(18, 18, 18)
                        .addComponent(jLabel13)
                        .addGap(18, 18, 18)
                        .addComponent(oAlto, javax.swing.GroupLayout.PREFERRED_SIZE, 74, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel7Layout.createSequentialGroup()
                        .addGap(0, 0, Short.MAX_VALUE)
                        .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel7Layout.createSequentialGroup()
                                .addComponent(jLabel11)
                                .addGap(18, 18, 18)
                                .addComponent(oNombre, javax.swing.GroupLayout.PREFERRED_SIZE, 141, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel7Layout.createSequentialGroup()
                                .addComponent(jLabel12)
                                .addGap(18, 18, 18)
                                .addComponent(oTipoExtraccion, javax.swing.GroupLayout.PREFERRED_SIZE, 141, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel7Layout.createSequentialGroup()
                                .addComponent(jLabel3)
                                .addGap(18, 18, 18)
                                .addComponent(oCod, javax.swing.GroupLayout.PREFERRED_SIZE, 141, javax.swing.GroupLayout.PREFERRED_SIZE))))
                    .addGroup(jPanel7Layout.createSequentialGroup()
                        .addComponent(jRadioCeramico)
                        .addGap(18, 18, 18)
                        .addComponent(jLabel24)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(oColor))
                    .addGroup(jPanel7Layout.createSequentialGroup()
                        .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel16)
                            .addComponent(jLabel26))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(oPeso, javax.swing.GroupLayout.DEFAULT_SIZE, 214, Short.MAX_VALUE)
                            .addGroup(jPanel7Layout.createSequentialGroup()
                                .addComponent(oCantidad, javax.swing.GroupLayout.DEFAULT_SIZE, 79, Short.MAX_VALUE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(jLabel15)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(oEspesor, javax.swing.GroupLayout.PREFERRED_SIZE, 74, javax.swing.GroupLayout.PREFERRED_SIZE)))))
                .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel7Layout.createSequentialGroup()
                        .addGap(18, 18, 18)
                        .addComponent(jLabel20)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(cuCodAsocia, javax.swing.GroupLayout.PREFERRED_SIZE, 140, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addGroup(jPanel7Layout.createSequentialGroup()
                        .addGap(23, 23, 23)
                        .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                .addGroup(jPanel7Layout.createSequentialGroup()
                                    .addComponent(jLabel21)
                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                    .addComponent(caCodContiene, javax.swing.GroupLayout.PREFERRED_SIZE, 141, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGroup(jPanel7Layout.createSequentialGroup()
                                    .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                        .addComponent(jLabel18)
                                        .addComponent(jLabel17)
                                        .addComponent(jLabel19))
                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                    .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                        .addComponent(oOrigen, javax.swing.GroupLayout.DEFAULT_SIZE, 141, Short.MAX_VALUE)
                                        .addComponent(oDescripcion, javax.swing.GroupLayout.DEFAULT_SIZE, 141, Short.MAX_VALUE)
                                        .addComponent(oFechaIngreso))))
                            .addGroup(jPanel7Layout.createSequentialGroup()
                                .addGap(6, 6, 6)
                                .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(jPanel7Layout.createSequentialGroup()
                                        .addGap(142, 142, 142)
                                        .addComponent(jButton1))
                                    .addGroup(jPanel7Layout.createSequentialGroup()
                                        .addComponent(jLabel22)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                        .addComponent(pDniIngresa)))))
                        .addContainerGap(52, Short.MAX_VALUE))))
        );
        jPanel7Layout.setVerticalGroup(
            jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel7Layout.createSequentialGroup()
                .addGap(24, 24, 24)
                .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel3)
                    .addComponent(oCod, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel17)
                    .addComponent(oFechaIngreso, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(7, 7, 7)
                .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel11)
                    .addComponent(oNombre, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel18)
                    .addComponent(oDescripcion, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel7Layout.createSequentialGroup()
                        .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel7Layout.createSequentialGroup()
                                .addGap(1, 1, 1)
                                .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(jLabel19)
                                    .addComponent(oOrigen, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGap(34, 34, 34)
                                .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(jLabel21)
                                    .addComponent(caCodContiene, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGap(18, 18, 18)
                                .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(pDniIngresa, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jLabel22)))
                            .addGroup(jPanel7Layout.createSequentialGroup()
                                .addGap(28, 28, 28)
                                .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(jLabel20)
                                    .addComponent(cuCodAsocia, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))))
                        .addGap(18, 18, 18)
                        .addComponent(jButton1)
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addGroup(jPanel7Layout.createSequentialGroup()
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel12)
                            .addComponent(oTipoExtraccion, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel13)
                            .addComponent(oAlto, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(oLargo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel14))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(oEspesor, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel15)
                            .addComponent(oCantidad, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel16))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(oPeso, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel26))
                        .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel7Layout.createSequentialGroup()
                                .addGap(1, 1, 1)
                                .addComponent(oColor, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                .addComponent(jRadioCeramico)
                                .addComponent(jLabel24)))
                        .addGap(4, 4, 4)
                        .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jRadioLitico)
                            .addComponent(jLabel23)
                            .addComponent(oFechaCreacion, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(28, 28, 28))))
        );

        jTabbedPane1.addTab("Agregar Objeto", jPanel7);

        jButton2.setText("Eliminar");
        jButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton2ActionPerformed(evt);
            }
        });

        jLabel25.setText("Codigo Caja:");

        javax.swing.GroupLayout jPanel8Layout = new javax.swing.GroupLayout(jPanel8);
        jPanel8.setLayout(jPanel8Layout);
        jPanel8Layout.setHorizontalGroup(
            jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel8Layout.createSequentialGroup()
                .addGap(24, 24, 24)
                .addComponent(jLabel25)
                .addGap(18, 18, 18)
                .addComponent(jTextOCodDelete, javax.swing.GroupLayout.PREFERRED_SIZE, 118, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(27, 27, 27)
                .addComponent(jButton2)
                .addContainerGap(244, Short.MAX_VALUE))
        );
        jPanel8Layout.setVerticalGroup(
            jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel8Layout.createSequentialGroup()
                .addGap(11, 11, 11)
                .addGroup(jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel25)
                    .addComponent(jTextOCodDelete, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jButton2))
                .addContainerGap(235, Short.MAX_VALUE))
        );

        jTabbedPane1.addTab("Eliminar Caja", jPanel8);

        jButton3.setText("buscar");
        jButton3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton3ActionPerformed(evt);
            }
        });

        jLabel1.setText("fecha 1");

        jLabel2.setText("fecha 2");

        jTablaDosFechas.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Title 1", "Title 2", "Title 3", "Title 4"
            }
        ));
        jScrollPane4.setViewportView(jTablaDosFechas);

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGap(33, 33, 33)
                        .addComponent(jLabel1)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 61, Short.MAX_VALUE)
                        .addComponent(jFormattedTextDate1, javax.swing.GroupLayout.PREFERRED_SIZE, 141, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jLabel2)
                        .addGap(18, 18, 18)
                        .addComponent(jFormattedTextDate2, javax.swing.GroupLayout.PREFERRED_SIZE, 123, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(30, 30, 30)
                        .addComponent(jButton3))
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(jScrollPane4)))
                .addContainerGap())
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jButton3)
                    .addComponent(jLabel1)
                    .addComponent(jLabel2)
                    .addComponent(jFormattedTextDate2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jFormattedTextDate1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane4, javax.swing.GroupLayout.DEFAULT_SIZE, 234, Short.MAX_VALUE))
        );

        jTabbedPane1.addTab("Buscar 2 fechas", jPanel2);

        jLabel4.setText("Codigo Caja:");

        jbEliminar.setText("Buscar");
        jbEliminar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jbEliminarActionPerformed(evt);
            }
        });

        jTableCajaContenido.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null}
            },
            new String [] {
                "Title 1", "Title 2", "Title 3", "Title 4"
            }
        ));
        jScrollPane5.setViewportView(jTableCajaContenido);

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane5, javax.swing.GroupLayout.DEFAULT_SIZE, 560, Short.MAX_VALUE)
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addComponent(jLabel4)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jTextCodCajaContenido, javax.swing.GroupLayout.PREFERRED_SIZE, 131, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(jbEliminar)
                        .addGap(0, 0, Short.MAX_VALUE)))
                .addContainerGap())
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addGap(16, 16, 16)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel4)
                    .addComponent(jbEliminar)
                    .addComponent(jTextCodCajaContenido, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane5, javax.swing.GroupLayout.DEFAULT_SIZE, 218, Short.MAX_VALUE)
                .addContainerGap())
        );

        jTabbedPane1.addTab("Mostrar contenido caja", jPanel3);

        jTableNroLiticoCeramicos.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null}
            },
            new String [] {
                "Title 1", "Title 2", "Title 3", "Title 4"
            }
        ));
        jScrollPane3.setViewportView(jTableNroLiticoCeramicos);

        javax.swing.GroupLayout jPanel10Layout = new javax.swing.GroupLayout(jPanel10);
        jPanel10.setLayout(jPanel10Layout);
        jPanel10Layout.setHorizontalGroup(
            jPanel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jScrollPane3, javax.swing.GroupLayout.DEFAULT_SIZE, 560, Short.MAX_VALUE)
        );
        jPanel10Layout.setVerticalGroup(
            jPanel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel10Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane3, javax.swing.GroupLayout.DEFAULT_SIZE, 239, Short.MAX_VALUE))
        );

        jTabbedPane3.addTab("Litico y Ceramicos", jPanel10);

        jLabelCantPersonas.setText("Nro. Personas: ");

        jLabelCantCuadrantes.setText("Nro. Cuadrantes: ");

        jLabelCantObjetos.setText("Nro. Objetos: ");

        jLabelCantCajas.setText("Cantidad Cajas:");

        javax.swing.GroupLayout jPanel11Layout = new javax.swing.GroupLayout(jPanel11);
        jPanel11.setLayout(jPanel11Layout);
        jPanel11Layout.setHorizontalGroup(
            jPanel11Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel11Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel11Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabelCantPersonas)
                    .addComponent(jLabelCantCuadrantes)
                    .addComponent(jLabelCantObjetos)
                    .addComponent(jLabelCantCajas))
                .addContainerGap(462, Short.MAX_VALUE))
        );
        jPanel11Layout.setVerticalGroup(
            jPanel11Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel11Layout.createSequentialGroup()
                .addGap(24, 24, 24)
                .addComponent(jLabelCantPersonas)
                .addGap(29, 29, 29)
                .addComponent(jLabelCantCuadrantes)
                .addGap(18, 18, 18)
                .addComponent(jLabelCantObjetos)
                .addGap(18, 18, 18)
                .addComponent(jLabelCantCajas)
                .addContainerGap(92, Short.MAX_VALUE))
        );

        jTabbedPane3.addTab("Nro Personas, Cuadric, obj y cajas", jPanel11);

        jTableObjArqueologo.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null}
            },
            new String [] {
                "Title 1", "Title 2", "Title 3", "Title 4"
            }
        ));
        jScrollPane7.setViewportView(jTableObjArqueologo);

        javax.swing.GroupLayout jPanel12Layout = new javax.swing.GroupLayout(jPanel12);
        jPanel12.setLayout(jPanel12Layout);
        jPanel12Layout.setHorizontalGroup(
            jPanel12Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jScrollPane7, javax.swing.GroupLayout.DEFAULT_SIZE, 560, Short.MAX_VALUE)
        );
        jPanel12Layout.setVerticalGroup(
            jPanel12Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel12Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane7, javax.swing.GroupLayout.DEFAULT_SIZE, 239, Short.MAX_VALUE))
        );

        jTabbedPane3.addTab("Obj por arqueologo", jPanel12);

        jTableCajasVacias.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null}
            },
            new String [] {
                "Title 1", "Title 2", "Title 3", "Title 4"
            }
        ));
        jScrollPane8.setViewportView(jTableCajasVacias);

        javax.swing.GroupLayout jPanel13Layout = new javax.swing.GroupLayout(jPanel13);
        jPanel13.setLayout(jPanel13Layout);
        jPanel13Layout.setHorizontalGroup(
            jPanel13Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jScrollPane8, javax.swing.GroupLayout.DEFAULT_SIZE, 560, Short.MAX_VALUE)
        );
        jPanel13Layout.setVerticalGroup(
            jPanel13Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel13Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane8, javax.swing.GroupLayout.DEFAULT_SIZE, 239, Short.MAX_VALUE))
        );

        jTabbedPane3.addTab("cajas vacias", jPanel13);

        jTableMaxMinAvg.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null}
            },
            new String [] {
                "Title 1", "Title 2", "Title 3", "Title 4"
            }
        ));
        jScrollPane9.setViewportView(jTableMaxMinAvg);

        javax.swing.GroupLayout jPanel14Layout = new javax.swing.GroupLayout(jPanel14);
        jPanel14.setLayout(jPanel14Layout);
        jPanel14Layout.setHorizontalGroup(
            jPanel14Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jScrollPane9, javax.swing.GroupLayout.DEFAULT_SIZE, 560, Short.MAX_VALUE)
        );
        jPanel14Layout.setVerticalGroup(
            jPanel14Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel14Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane9, javax.swing.GroupLayout.DEFAULT_SIZE, 239, Short.MAX_VALUE))
        );

        jTabbedPane3.addTab("max,min,avg peso cajas", jPanel14);

        jTablePesoCaja.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null}
            },
            new String [] {
                "Title 1", "Title 2", "Title 3", "Title 4"
            }
        ));
        jScrollPane10.setViewportView(jTablePesoCaja);

        javax.swing.GroupLayout jPanel15Layout = new javax.swing.GroupLayout(jPanel15);
        jPanel15.setLayout(jPanel15Layout);
        jPanel15Layout.setHorizontalGroup(
            jPanel15Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jScrollPane10, javax.swing.GroupLayout.DEFAULT_SIZE, 560, Short.MAX_VALUE)
        );
        jPanel15Layout.setVerticalGroup(
            jPanel15Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel15Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane10, javax.swing.GroupLayout.DEFAULT_SIZE, 239, Short.MAX_VALUE))
        );

        jTabbedPane3.addTab("peso por caja", jPanel15);

        javax.swing.GroupLayout jPanel9Layout = new javax.swing.GroupLayout(jPanel9);
        jPanel9.setLayout(jPanel9Layout);
        jPanel9Layout.setHorizontalGroup(
            jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel9Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jTabbedPane3)
                .addContainerGap())
        );
        jPanel9Layout.setVerticalGroup(
            jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel9Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jTabbedPane3)
                .addContainerGap())
        );

        jTabbedPane1.addTab("Consultas Extras", jPanel9);

        label_error.setForeground(new java.awt.Color(255, 0, 0));
        label_error.setText("mensajes de error");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jTabbedPane1)
            .addComponent(label_error, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jTabbedPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 334, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(label_error, javax.swing.GroupLayout.DEFAULT_SIZE, 17, Short.MAX_VALUE))
        );

        jTabbedPane1.getAccessibleContext().setAccessibleName("tab_panel");

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jbEliminarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jbEliminarActionPerformed
        try{
            queryToTableUpdate(jTableCajaContenido,"SELECT * FROM objetos,cajas WHERE ca_cod_contiene = ca_cod AND ca_cod_contiene = '"+jTextCodCajaContenido.getText().trim()+"'");
            label_error.setVisible(false);
        }
        catch(Exception ex){
            System.out.println("error, codigo no valido");
            label_error.setVisible(true);
        }
    }//GEN-LAST:event_jbEliminarActionPerformed

    private void oColorActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_oColorActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_oColorActionPerformed

    private void jRadioCeramicoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jRadioCeramicoActionPerformed
        // TODO add your handling code here:
        jRadioLitico.setSelected(false);
        jRadioCeramico.setSelected(true);
        
    }//GEN-LAST:event_jRadioCeramicoActionPerformed

    private void caCodContieneActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_caCodContieneActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_caCodContieneActionPerformed

    private void cuCodAsociaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cuCodAsociaActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_cuCodAsociaActionPerformed

    private void oOrigenActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_oOrigenActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_oOrigenActionPerformed

    private void oDescripcionActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_oDescripcionActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_oDescripcionActionPerformed

    private void oTipoExtraccionActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_oTipoExtraccionActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_oTipoExtraccionActionPerformed

    private void oNombreActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_oNombreActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_oNombreActionPerformed

    private void oCodActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_oCodActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_oCodActionPerformed

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        // TODO add your handling code here:
        
        
        
        // Antes de realizar la inserción, primero controlar que los campos tengan valores
        // válidos. En este caso, por ser un ejemplo, sólo se controla por que el campo "nombre"
        // del usuario no esté vacío, pero para su versión real pueden realizar controles más elaborados.
        // (por ejemplo, que los DNIs y la edad sean números positivos, etc.)
//        if (pNombre.getText().trim().equals("") && pApellido.getText().trim().equals("") && pEmail.getText().trim().equals("")) {
//            label_error.setText(ERROR_MSG_INSERT_INPUT);
//            label_error.setVisible(true);
//            return;
//        }

        try {
            // creamos una consulta INSERT parametrizada por los valores que queremos insertar
            // en este caso, son 3, el DNI, el nombre y la edad, en orden: (?, ?, ?)
            p_query = conn.prepareStatement("INSERT INTO objetos VALUES (?,?,?,?,?,?,?,?,CAST(? AS DATE),?,?,?,?,?,?)");

            // luego asignamos los valores reales a esos parámetros, dando primero su posición y luego su valor
            // del siguiente modo: p_query.setTIPO(POSICIÓN, VALOR)
            p_query.setString(1, oCod.getText().trim());
            p_query.setString(2, oNombre.getText().trim());
            p_query.setString(3, oTipoExtraccion.getText().trim());
            
            p_query.setInt(4, (int) oAlto.getValue());
            p_query.setInt(5, (int) oLargo.getValue());
            p_query.setInt(6, (int) oEspesor.getValue());
            p_query.setInt(7, (int) oPeso.getValue());
            p_query.setInt(8, (int) oCantidad.getValue());
            
            p_query.setString(9, oFechaIngreso.getText().trim());
            p_query.setString(10, oDescripcion.getText().trim());
            p_query.setString(11, oOrigen.getText().trim());
            
            // Atrib de relacion
            p_query.setString(12, cuCodAsocia.getText().trim());
            p_query.setString(13, caCodContiene.getText().trim());
            p_query.setInt(14, (int) pDniIngresa.getValue());
            
            // Dependiendo del tipo de objeto elegido, asignar relacion Es, e insertar Litico o Ceramico respectivamente en la tabla
            if (jRadioCeramico.isSelected())
                p_query.setString(15, "C");
            else
                p_query.setString(15, "L");
            
            // ejecutamos la consulta con los valores asignados
            p_query.executeUpdate();
            
            if (jRadioCeramico.isEnabled())
                query.execute("INSERT INTO Ceramicos VALUES ('"+oCod.getText().trim()+"','"+oColor.getText().trim()+"')");
            else
                query.execute("INSERT INTO Liticos VALUES ('"+oCod.getText().trim()+"','"+oFechaCreacion.getText().trim()+"')");

            // finalmente actualizamos nuestra tabla mostrando la lista de personas en el formulario principal
            //updateForm();

            // actualizar y limpiar el formulario luego de una operación exitosa
//            pDni.setValue(0);
//            pNombre.setText("");
//            pApellido.setText("");
//            pEmail.setText("");
//            pTelefono.setValue(0);
            label_error.setVisible(false);
            updateListaResultados(jTablaObjetos,"objetos");
            
            updateExtraQueries();

        } catch (Exception ex) {
            Logger.getLogger(MainFrame.class.getName()).log(Level.SEVERE, null, ex);
            label_error.setText("Error al dar de alta a este objeto");
            label_error.setVisible(true);
        }
    }//GEN-LAST:event_jButton1ActionPerformed

    private void jRadioLiticoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jRadioLiticoActionPerformed
        // TODO add your handling code here:
        jRadioCeramico.setSelected(false);
        jRadioLitico.setSelected(true);
        
    }//GEN-LAST:event_jRadioLiticoActionPerformed

    private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton2ActionPerformed
        
        if (jTextOCodDelete.getText().trim().equals("")){
            return;
        }
        // TODO add your handling code here:
        try{
            query.execute("DELETE FROM cajas WHERE ca_cod = '"+jTextOCodDelete.getText().trim()+"'");
            
            label_error.setVisible(false);
            updateExtraQueries();
        }
        catch(SQLException ex){
            System.out.println(ex.getErrorCode());
            label_error.setVisible(true);
            label_error.setText("Error al eliminar la caja: no encontrada");
            
        }
    }//GEN-LAST:event_jButton2ActionPerformed

    private void jButton3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton3ActionPerformed
        // TODO add your handling code here:
        try{
            queryToTableUpdate(jTablaDosFechas,"SELECT o_cod,o_nombre FROM objetos WHERE o_fechaRegistro BETWEEN '"+jFormattedTextDate1.getText().trim()+ "' AND '"+jFormattedTextDate2.getText().trim()+"'");
            label_error.setVisible(false);
        }
        catch(Exception ex){
            label_error.setVisible(true);
            System.out.println("ERROR, fecha no valida");
        }
    }//GEN-LAST:event_jButton3ActionPerformed

    private void formWindowClosed(java.awt.event.WindowEvent evt) {//GEN-FIRST:event_formWindowClosed
        // TODO add your handling code here:
        System.exit(0);
    }//GEN-LAST:event_formWindowClosed

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(MainFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(MainFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(MainFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(MainFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                try {
                    new MainFrame().setVisible(true);
                } catch (SQLException ex) {
                    Logger.getLogger(MainFrame.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JTextField caCodContiene;
    private javax.swing.JTextField cuCodAsocia;
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton2;
    private javax.swing.JButton jButton3;
    private javax.swing.JFormattedTextField jFormattedTextDate1;
    private javax.swing.JFormattedTextField jFormattedTextDate2;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel13;
    private javax.swing.JLabel jLabel14;
    private javax.swing.JLabel jLabel15;
    private javax.swing.JLabel jLabel16;
    private javax.swing.JLabel jLabel17;
    private javax.swing.JLabel jLabel18;
    private javax.swing.JLabel jLabel19;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel20;
    private javax.swing.JLabel jLabel21;
    private javax.swing.JLabel jLabel22;
    private javax.swing.JLabel jLabel23;
    private javax.swing.JLabel jLabel24;
    private javax.swing.JLabel jLabel25;
    private javax.swing.JLabel jLabel26;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabelCantCajas;
    private javax.swing.JLabel jLabelCantCuadrantes;
    private javax.swing.JLabel jLabelCantObjetos;
    private javax.swing.JLabel jLabelCantPersonas;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel10;
    private javax.swing.JPanel jPanel11;
    private javax.swing.JPanel jPanel12;
    private javax.swing.JPanel jPanel13;
    private javax.swing.JPanel jPanel14;
    private javax.swing.JPanel jPanel15;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JPanel jPanel7;
    private javax.swing.JPanel jPanel8;
    private javax.swing.JPanel jPanel9;
    private javax.swing.JRadioButton jRadioCeramico;
    private javax.swing.JRadioButton jRadioLitico;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane10;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JScrollPane jScrollPane4;
    private javax.swing.JScrollPane jScrollPane5;
    private javax.swing.JScrollPane jScrollPane7;
    private javax.swing.JScrollPane jScrollPane8;
    private javax.swing.JScrollPane jScrollPane9;
    private javax.swing.JTabbedPane jTabbedPane1;
    private javax.swing.JTabbedPane jTabbedPane3;
    private javax.swing.JTable jTablaDosFechas;
    private javax.swing.JTable jTablaObjetos;
    private javax.swing.JTable jTablaPersonas;
    private javax.swing.JTable jTableCajaContenido;
    private javax.swing.JTable jTableCajasVacias;
    private javax.swing.JTable jTableMaxMinAvg;
    private javax.swing.JTable jTableNroLiticoCeramicos;
    private javax.swing.JTable jTableObjArqueologo;
    private javax.swing.JTable jTablePesoCaja;
    private javax.swing.JTextField jTextCodCajaContenido;
    private javax.swing.JTextField jTextOCodDelete;
    private javax.swing.JButton jbEliminar;
    private javax.swing.JLabel label_error;
    private javax.swing.JSpinner oAlto;
    private javax.swing.JSpinner oCantidad;
    private javax.swing.JTextField oCod;
    private javax.swing.JTextField oColor;
    private javax.swing.JTextField oDescripcion;
    private javax.swing.JSpinner oEspesor;
    private javax.swing.JFormattedTextField oFechaCreacion;
    private javax.swing.JFormattedTextField oFechaIngreso;
    private javax.swing.JSpinner oLargo;
    private javax.swing.JTextField oNombre;
    private javax.swing.JTextField oOrigen;
    private javax.swing.JSpinner oPeso;
    private javax.swing.JTextField oTipoExtraccion;
    private javax.swing.JSpinner pDniIngresa;
    // End of variables declaration//GEN-END:variables
}
