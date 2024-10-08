/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.interfaz;

import com.objetos.Paciente;
import com.formdev.flatlaf.FlatDarculaLaf;
import com.formdev.flatlaf.FlatIntelliJLaf;
import com.persistencia.DAOPacienteSQL;
import com.persistencia.Dao;
import com.persistencia.DataBaseSingleton;
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
        // Especificar el tema flatlaf (look and feel)
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
//        updateListaResultados(jTablaObjetos, "objetos");
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
    
    // Realiza una transición entre paneles
    private void gotoMenu(JPanel panel){
        jTabbedPane1.removeAll();
        jTabbedPane1.add(panel);
        jTabbedPane1.repaint();
        jTabbedPane1.revalidate();
    }
    
    private void updateExtraQueries(){
        try{
            // 6
//        queryToTableUpdate(jTableNroLiticoCeramicos,"SELECT o_es, COUNT(o_es) FROM objetos GROUP BY o_es");
        
        // 7
        
        ResultSet personas = query.executeQuery("SELECT COUNT(p_dni) FROM personas");
        personas.next();
        
        ResultSet cuadriculas = query.executeQuery("SELECT COUNT(cu_cod) FROM cuadriculas");
        cuadriculas.next();
        
        ResultSet objetos = query.executeQuery("SELECT COUNT(o_cod) FROM objetos");
        objetos.next();
        
        ResultSet cajas = query.executeQuery("SELECT COUNT(ca_cod) FROM cajas");
        cajas.next();
        
//        jLabelCantPersonas.setText("Cantidad Personas: " + personas.getString(1));
//        jLabelCantCuadrantes.setText("Cantidad Cuadrantes: " + cuadriculas.getString(1));
//        jLabelCantObjetos.setText("Cantidad Objetos: " + objetos.getString(1));
//        jLabelCantCajas.setText("Cantidad Cajas: " + cajas.getString(1));
        
//        System.out.println("Nro Personas: " + personas.getString(1));
//        System.out.println("Nro Cuadriculas: " + cuadriculas.getString(1));
//        System.out.println("Nro Objetos: " + objetos.getString(1));
//        System.out.println("Nro Cajas: " + cajas.getString(1));
        
        
        // 8
//        queryToTableUpdate(jTableObjArqueologo,"SELECT p_nombre,p_apellido,COUNT(p_dni_ingresa) FROM personas,objetos WHERE p_dni = p_dni_ingresa GROUP BY p_nombre,p_apellido");
//        
//        // 9
//        queryToTableUpdate(jTableCajasVacias,"SELECT ca_cod,ca_lugar FROM cajas EXCEPT SELECT ca_cod,ca_lugar FROM cajas,objetos WHERE ca_cod = ca_cod_contiene GROUP BY ca_cod HAVING COUNT(ca_cod) > 0" );
//        
//        // 10
//        queryToTableUpdate(jTableMaxMinAvg,"SELECT MAX(o_peso),MIN(o_peso),AVG(o_peso) FROM objetos");
//        
//        // 11
//        queryToTableUpdate(jTablePesoCaja,"SELECT ca_cod,SUM(o_peso) FROM cajas,objetos WHERE ca_cod = ca_cod_contiene GROUP BY ca_cod");
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

        jPanel7 = new javax.swing.JPanel();
        jTabbedPane1 = new javax.swing.JTabbedPane();
        jPanel5 = new javax.swing.JPanel();
        jScrollPane6 = new javax.swing.JScrollPane();
        jTablaPersonas1 = new javax.swing.JTable();
        jPanel2 = new javax.swing.JPanel();
        jScrollPane2 = new javax.swing.JScrollPane();
        jTablaPersonas2 = new javax.swing.JTable();
        jPanel4 = new javax.swing.JPanel();
        jScrollPane4 = new javax.swing.JScrollPane();
        jTablaPersonas4 = new javax.swing.JTable();
        jPanel6 = new javax.swing.JPanel();
        jScrollPane5 = new javax.swing.JScrollPane();
        jTablaPersonas5 = new javax.swing.JTable();
        jPanel3 = new javax.swing.JPanel();
        jScrollPane3 = new javax.swing.JScrollPane();
        jTablaPersonas3 = new javax.swing.JTable();
        jPanel1 = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        jTablaPersonas = new javax.swing.JTable();
        label_error = new javax.swing.JLabel();

        javax.swing.GroupLayout jPanel7Layout = new javax.swing.GroupLayout(jPanel7);
        jPanel7.setLayout(jPanel7Layout);
        jPanel7Layout.setHorizontalGroup(
            jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 100, Short.MAX_VALUE)
        );
        jPanel7Layout.setVerticalGroup(
            jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 100, Short.MAX_VALUE)
        );

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("Base de Datos (Laboratorio 2 SQL)");
        setMaximumSize(new java.awt.Dimension(800, 480));
        setMinimumSize(new java.awt.Dimension(800, 480));
        setResizable(false);
        setSize(new java.awt.Dimension(800, 480));
        addWindowListener(new java.awt.event.WindowAdapter() {
            public void windowClosed(java.awt.event.WindowEvent evt) {
                formWindowClosed(evt);
            }
        });

        jTabbedPane1.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        jTabbedPane1.setPreferredSize(new java.awt.Dimension(800, 480));

        jTablaPersonas1.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Title 1", "Title 2", "Title 3", "Title 4"
            }
        ));
        jScrollPane6.setViewportView(jTablaPersonas1);

        javax.swing.GroupLayout jPanel5Layout = new javax.swing.GroupLayout(jPanel5);
        jPanel5.setLayout(jPanel5Layout);
        jPanel5Layout.setHorizontalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jScrollPane6, javax.swing.GroupLayout.DEFAULT_SIZE, 800, Short.MAX_VALUE)
        );
        jPanel5Layout.setVerticalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jScrollPane6, javax.swing.GroupLayout.DEFAULT_SIZE, 423, Short.MAX_VALUE)
        );

        jTabbedPane1.addTab("Dar Turno", jPanel5);

        jTablaPersonas2.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Title 1", "Title 2", "Title 3", "Title 4"
            }
        ));
        jScrollPane2.setViewportView(jTablaPersonas2);

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jScrollPane2, javax.swing.GroupLayout.DEFAULT_SIZE, 800, Short.MAX_VALUE)
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jScrollPane2, javax.swing.GroupLayout.DEFAULT_SIZE, 423, Short.MAX_VALUE)
        );

        jTabbedPane1.addTab("Generar Resultado", jPanel2);

        jTablaPersonas4.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Title 1", "Title 2", "Title 3", "Title 4"
            }
        ));
        jScrollPane4.setViewportView(jTablaPersonas4);

        javax.swing.GroupLayout jPanel4Layout = new javax.swing.GroupLayout(jPanel4);
        jPanel4.setLayout(jPanel4Layout);
        jPanel4Layout.setHorizontalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jScrollPane4, javax.swing.GroupLayout.DEFAULT_SIZE, 800, Short.MAX_VALUE)
        );
        jPanel4Layout.setVerticalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jScrollPane4, javax.swing.GroupLayout.DEFAULT_SIZE, 423, Short.MAX_VALUE)
        );

        jTabbedPane1.addTab("Consultar Paciente", jPanel4);

        jTablaPersonas5.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Title 1", "Title 2", "Title 3", "Title 4"
            }
        ));
        jScrollPane5.setViewportView(jTablaPersonas5);

        javax.swing.GroupLayout jPanel6Layout = new javax.swing.GroupLayout(jPanel6);
        jPanel6.setLayout(jPanel6Layout);
        jPanel6Layout.setHorizontalGroup(
            jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jScrollPane5, javax.swing.GroupLayout.DEFAULT_SIZE, 800, Short.MAX_VALUE)
        );
        jPanel6Layout.setVerticalGroup(
            jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jScrollPane5, javax.swing.GroupLayout.DEFAULT_SIZE, 423, Short.MAX_VALUE)
        );

        jTabbedPane1.addTab("Consultar Analisis", jPanel6);

        jTablaPersonas3.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Title 1", "Title 2", "Title 3", "Title 4"
            }
        ));
        jScrollPane3.setViewportView(jTablaPersonas3);

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jScrollPane3, javax.swing.GroupLayout.DEFAULT_SIZE, 800, Short.MAX_VALUE)
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jScrollPane3, javax.swing.GroupLayout.DEFAULT_SIZE, 423, Short.MAX_VALUE)
        );

        jTabbedPane1.addTab("Consultar Reactivo", jPanel3);

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
            .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 800, Short.MAX_VALUE)
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 423, Short.MAX_VALUE)
        );

        jTabbedPane1.addTab("Pedir Reactivos", jPanel1);

        label_error.setForeground(new java.awt.Color(255, 0, 0));
        label_error.setText("mensajes de error");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jTabbedPane1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(label_error, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jTabbedPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 458, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(label_error))
        );

        jTabbedPane1.getAccessibleContext().setAccessibleName("tab_panel");

        pack();
    }// </editor-fold>//GEN-END:initComponents

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
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JPanel jPanel5;
    private javax.swing.JPanel jPanel6;
    private javax.swing.JPanel jPanel7;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JScrollPane jScrollPane4;
    private javax.swing.JScrollPane jScrollPane5;
    private javax.swing.JScrollPane jScrollPane6;
    private javax.swing.JTabbedPane jTabbedPane1;
    private javax.swing.JTable jTablaPersonas;
    private javax.swing.JTable jTablaPersonas1;
    private javax.swing.JTable jTablaPersonas2;
    private javax.swing.JTable jTablaPersonas3;
    private javax.swing.JTable jTablaPersonas4;
    private javax.swing.JTable jTablaPersonas5;
    private javax.swing.JLabel label_error;
    // End of variables declaration//GEN-END:variables
}
