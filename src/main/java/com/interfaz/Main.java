/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package com.interfaz;

import com.formdev.flatlaf.FlatIntelliJLaf;
import com.objetos.Analisis;
import com.objetos.ObraSocial;
import com.objetos.Paciente;
import com.objetos.Reactivo;
import com.objetos.Stock;
import com.objetos.Turno;
import com.persistencia.DAOAnalisisSQL;
import com.persistencia.DAOObraSocialSQL;
import com.persistencia.DAOPacienteSQL;
import com.persistencia.DAOReactivoSQL;
import com.persistencia.DAOStockSQL;
import com.persistencia.DAOTurnoSQL;
import java.sql.Date;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;

/**
 *
 * @author jerem
 */
public class Main extends javax.swing.JFrame {

    /**
     * Creates new form Main
     */
    public Main() {
        // Especificar el tema flatlaf (look and feel)
        try {
            UIManager.setLookAndFeel(new FlatIntelliJLaf());
        } catch (UnsupportedLookAndFeelException ex) {
            System.out.println("Error al cargar la interfaz");
        }
        initComponents();
        
//        jCalendar1.setBackground(Color.BLACK);
//        
//        
//        ManagerPaciente.validarDni(Integer.valueOf(jTextField1.getText()));
//      

        // Cargar Reactivos de Prueba
        DAOReactivoSQL daoReactivo = new DAOReactivoSQL();
        
        daoReactivo.save(new Reactivo("T12",123));
        daoReactivo.save(new Reactivo("T125",123));
        daoReactivo.save(new Reactivo("E12",123));
        daoReactivo.save(new Reactivo("F1255",123));

        System.out.println(daoReactivo.getAll());
        
        // Cargar Analisis
        DAOAnalisisSQL daoAnalisis = new DAOAnalisisSQL();
        
        Analisis analisis1 = new Analisis("T3",3.5f,"Metodo 1",2500);
        analisis1.getReactivosUsados().add(new Reactivo("T12",70));
        analisis1.getReactivosUsados().add(new Reactivo("T125",80));
        analisis1.getReactivosUsados().add(new Reactivo("E12",20));
        daoAnalisis.save(analisis1);
        
        Analisis analisis2 = new Analisis("Glucemia",3.5f,"Metodo 2",2500);
        analisis2.getReactivosUsados().add(new Reactivo("T125",70));
        analisis2.getReactivosUsados().add(new Reactivo("E12",20));
        daoAnalisis.save(analisis2);
        
        Analisis analisis3 = new Analisis("AlgoMas",3.5f,"Metodo 3",2500);
        analisis3.getReactivosUsados().add(new Reactivo("E12",70));
        analisis3.getReactivosUsados().add(new Reactivo("F1255",80));
        analisis3.getReactivosUsados().add(new Reactivo("T125",20));
        analisis3.getReactivosUsados().add(new Reactivo("T12",20));
        daoAnalisis.save(analisis3);
        
        System.out.println(daoAnalisis.getAll());
        
        // Cargar Stock de Prueba
        DAOStockSQL daoStock = new DAOStockSQL();
        
        daoStock.save(new Stock(123,12,new Date(System.currentTimeMillis()),new Reactivo("T12",123)));
        daoStock.save(new Stock(123443,12,new Date(System.currentTimeMillis()),new Reactivo("E12",123)));
        daoStock.save(new Stock(12213,12,new Date(System.currentTimeMillis()),new Reactivo("F1255",123)));
        daoStock.save(new Stock(22213,12,new Date(System.currentTimeMillis()),new Reactivo("F1255",123)));
        
        System.out.println(daoStock.getAll());
        
        // Cargar Obras Sociales
        DAOObraSocialSQL daoObraSocial = new DAOObraSocialSQL();
        
        daoObraSocial.save(new ObraSocial("DOSEP"));
        daoObraSocial.save(new ObraSocial("DOSPU"));
        daoObraSocial.save(new ObraSocial("PAMI"));
        daoObraSocial.save(new ObraSocial("PARTICULAR"));
        
        
        System.out.println(daoObraSocial.getAll());
        
        // Cargar Pacientes
        DAOPacienteSQL daoPaciente = new DAOPacienteSQL();
        
        Paciente p1 = new Paciente(45123871,"Jere","Julian",1,23,"la ribera","2657719541","jeremiasjulian5003@gmail.com");
        p1.getObrasSociales().add(new ObraSocial("DOSEP"));  
        daoPaciente.save(p1);
        
        Paciente p2 = new Paciente(46123871,"Francisco","Julian",1,23,"la ribera","2657719541","jeremiasjulian5003@gmail.com");
        p2.getObrasSociales().add(new ObraSocial("DOSPU"));  
        daoPaciente.save(p2);
        
        Paciente p3 = new Paciente(47123871,"Pedro","Julian",1,23,"la ribera","2657719541","jeremiasjulian5003@gmail.com");
        p3.getObrasSociales().add(new ObraSocial("DOSEP"));  
        p3.getObrasSociales().add(new ObraSocial("PAMI"));  
        daoPaciente.save(p3);
        
        System.out.println(daoPaciente.getAll());
        
        
        // Crear Turnos
        DAOTurnoSQL daoTurno = new DAOTurnoSQL();
        
        Turno t1 = new Turno(12,new Date(System.currentTimeMillis()),"Marito","le duele el codo");
        t1.setPaciente(p1);
        t1.getAnalisis().add(analisis1);
        t1.getAnalisis().add(analisis2);
        t1.setObraSocial(new ObraSocial("DOSEP"));
        daoTurno.save(t1);
        
        System.out.println(daoTurno.getAll());
        
        
//        jLabelPacienteInexistente.setVisible(false);
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        backgroundJpanel = new javax.swing.JPanel();
        mainJpanel = new javax.swing.JPanel();
        jCalendar1 = new com.toedter.calendar.JCalendar();
        jButton1 = new javax.swing.JButton();
        listaProximasFechasLibres = new javax.swing.JList<>();
        jScrollPane2 = new javax.swing.JScrollPane();
        jTable2 = new javax.swing.JTable();
        crearTurnoJpanel = new javax.swing.JPanel();
        jButton2 = new javax.swing.JButton();
        crearTurnoPasosJTabbed = new javax.swing.JTabbedPane();
        crearTurnoP1 = new javax.swing.JPanel();
        jPanel1 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jTextFieldDniPaciente = new javax.swing.JTextField();
        jButtonCrearPaciente = new javax.swing.JButton();
        jCheckBoxParticular = new javax.swing.JCheckBox();
        jComboBoxObraSocial = new javax.swing.JComboBox<>();
        jButtonSiguienteAgregarPacienteP1 = new javax.swing.JButton();
        crearTurnoP2 = new javax.swing.JPanel();
        jPanel2 = new javax.swing.JPanel();
        jButton6 = new javax.swing.JButton();
        jCalendar2 = new com.toedter.calendar.JCalendar();
        jButton9 = new javax.swing.JButton();
        crearTurnoP3 = new javax.swing.JPanel();
        jPanel3 = new javax.swing.JPanel();
        jButton7 = new javax.swing.JButton();
        jLabel6 = new javax.swing.JLabel();
        jComboBox3 = new javax.swing.JComboBox<>();
        jScrollPane1 = new javax.swing.JScrollPane();
        jTable1 = new javax.swing.JTable();
        jButton8 = new javax.swing.JButton();
        jButton11 = new javax.swing.JButton();
        crearTurnoP4 = new javax.swing.JPanel();
        jPanel4 = new javax.swing.JPanel();
        jLabel7 = new javax.swing.JLabel();
        jButton10 = new javax.swing.JButton();
        jButton12 = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setLocationByPlatform(true);
        setMaximumSize(new java.awt.Dimension(800, 480));
        setMinimumSize(new java.awt.Dimension(800, 480));
        setResizable(false);
        setSize(new java.awt.Dimension(800, 480));

        backgroundJpanel.setLayout(new java.awt.CardLayout());

        jButton1.setText("cambiar ventana");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        listaProximasFechasLibres.setModel(new javax.swing.AbstractListModel<String>() {
            String[] strings = { "Item 1", "Item 2", "Item 3", "Item 4", "Item 5" };
            public int getSize() { return strings.length; }
            public String getElementAt(int i) { return strings[i]; }
        });

        jTable2.setModel(new javax.swing.table.DefaultTableModel(
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
        jScrollPane2.setViewportView(jTable2);

        javax.swing.GroupLayout mainJpanelLayout = new javax.swing.GroupLayout(mainJpanel);
        mainJpanel.setLayout(mainJpanelLayout);
        mainJpanelLayout.setHorizontalGroup(
            mainJpanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(mainJpanelLayout.createSequentialGroup()
                .addGroup(mainJpanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(mainJpanelLayout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(jCalendar1, javax.swing.GroupLayout.PREFERRED_SIZE, 336, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(listaProximasFechasLibres, javax.swing.GroupLayout.PREFERRED_SIZE, 242, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(2, 180, Short.MAX_VALUE))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, mainJpanelLayout.createSequentialGroup()
                        .addGap(0, 0, Short.MAX_VALUE)
                        .addComponent(jButton1))
                    .addComponent(jScrollPane2, javax.swing.GroupLayout.Alignment.TRAILING))
                .addContainerGap())
        );
        mainJpanelLayout.setVerticalGroup(
            mainJpanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(mainJpanelLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(mainJpanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jCalendar1, javax.swing.GroupLayout.DEFAULT_SIZE, 199, Short.MAX_VALUE)
                    .addComponent(listaProximasFechasLibres, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addGap(18, 18, 18)
                .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 189, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jButton1, javax.swing.GroupLayout.PREFERRED_SIZE, 38, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );

        backgroundJpanel.add(mainJpanel, "card2");

        jButton2.setText("volver");
        jButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton2ActionPerformed(evt);
            }
        });

        crearTurnoPasosJTabbed.setEnabled(false);

        jLabel1.setText("DNI:");

        jLabel2.setText("Obra Social: ");

        jTextFieldDniPaciente.addCaretListener(new javax.swing.event.CaretListener() {
            public void caretUpdate(javax.swing.event.CaretEvent evt) {
                jTextFieldDniPacienteCaretUpdate(evt);
            }
        });
        jTextFieldDniPaciente.addInputMethodListener(new java.awt.event.InputMethodListener() {
            public void caretPositionChanged(java.awt.event.InputMethodEvent evt) {
            }
            public void inputMethodTextChanged(java.awt.event.InputMethodEvent evt) {
                jTextFieldDniPacienteInputMethodTextChanged(evt);
            }
        });
        jTextFieldDniPaciente.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jTextFieldDniPacienteActionPerformed(evt);
            }
        });
        jTextFieldDniPaciente.addPropertyChangeListener(new java.beans.PropertyChangeListener() {
            public void propertyChange(java.beans.PropertyChangeEvent evt) {
                jTextFieldDniPacientePropertyChange(evt);
            }
        });
        jTextFieldDniPaciente.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                jTextFieldDniPacienteKeyPressed(evt);
            }
            public void keyTyped(java.awt.event.KeyEvent evt) {
                jTextFieldDniPacienteKeyTyped(evt);
            }
        });

        jButtonCrearPaciente.setText("Crear");
        jButtonCrearPaciente.setEnabled(false);
        jButtonCrearPaciente.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonCrearPacienteActionPerformed(evt);
            }
        });

        jCheckBoxParticular.setText("Particular");
        jCheckBoxParticular.setEnabled(false);
        jCheckBoxParticular.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jCheckBoxParticularActionPerformed(evt);
            }
        });

        jComboBoxObraSocial.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));
        jComboBoxObraSocial.setEnabled(false);

        jButtonSiguienteAgregarPacienteP1.setText("siguiente");
        jButtonSiguienteAgregarPacienteP1.setEnabled(false);
        jButtonSiguienteAgregarPacienteP1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonSiguienteAgregarPacienteP1ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel1)
                    .addComponent(jLabel2)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(jTextFieldDniPaciente, javax.swing.GroupLayout.PREFERRED_SIZE, 127, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(jButtonCrearPaciente, javax.swing.GroupLayout.PREFERRED_SIZE, 78, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(jComboBoxObraSocial, javax.swing.GroupLayout.PREFERRED_SIZE, 127, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jCheckBoxParticular, javax.swing.GroupLayout.PREFERRED_SIZE, 81, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jButtonSiguienteAgregarPacienteP1))))
                .addContainerGap(492, Short.MAX_VALUE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel1)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jButtonCrearPaciente, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jTextFieldDniPaciente, javax.swing.GroupLayout.PREFERRED_SIZE, 34, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jLabel2)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jComboBoxObraSocial, javax.swing.GroupLayout.PREFERRED_SIZE, 36, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jCheckBoxParticular, javax.swing.GroupLayout.PREFERRED_SIZE, 36, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addComponent(jButtonSiguienteAgregarPacienteP1, javax.swing.GroupLayout.PREFERRED_SIZE, 36, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(156, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout crearTurnoP1Layout = new javax.swing.GroupLayout(crearTurnoP1);
        crearTurnoP1.setLayout(crearTurnoP1Layout);
        crearTurnoP1Layout.setHorizontalGroup(
            crearTurnoP1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(crearTurnoP1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGap(46, 46, 46))
        );
        crearTurnoP1Layout.setVerticalGroup(
            crearTurnoP1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(crearTurnoP1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGap(20, 20, 20))
        );

        crearTurnoPasosJTabbed.addTab("Paso 1: Cargar Paciente", crearTurnoP1);

        jButton6.setText("siguiente");
        jButton6.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton6ActionPerformed(evt);
            }
        });

        jButton9.setText("anterior");
        jButton9.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton9ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addComponent(jButton9)
                        .addGap(18, 18, 18)
                        .addComponent(jButton6, javax.swing.GroupLayout.PREFERRED_SIZE, 102, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(jCalendar2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(566, Short.MAX_VALUE))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jCalendar2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jButton6)
                    .addComponent(jButton9))
                .addContainerGap(180, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout crearTurnoP2Layout = new javax.swing.GroupLayout(crearTurnoP2);
        crearTurnoP2.setLayout(crearTurnoP2Layout);
        crearTurnoP2Layout.setHorizontalGroup(
            crearTurnoP2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(crearTurnoP2Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );
        crearTurnoP2Layout.setVerticalGroup(
            crearTurnoP2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(crearTurnoP2Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );

        crearTurnoPasosJTabbed.addTab("Paso 2: Elegir Fecha", crearTurnoP2);

        jButton7.setText("Agregar");

        jLabel6.setText("Analisis:");

        jComboBox3.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));

        jTable1.setModel(new javax.swing.table.DefaultTableModel(
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
        jScrollPane1.setViewportView(jTable1);

        jButton8.setText("siguiente");
        jButton8.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton8ActionPerformed(evt);
            }
        });

        jButton11.setText("anterior");
        jButton11.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton11ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 752, Short.MAX_VALUE)
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel6)
                            .addGroup(jPanel3Layout.createSequentialGroup()
                                .addGap(210, 210, 210)
                                .addComponent(jButton11)
                                .addGap(33, 33, 33)
                                .addComponent(jButton8, javax.swing.GroupLayout.PREFERRED_SIZE, 102, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(jPanel3Layout.createSequentialGroup()
                                .addComponent(jComboBox3, javax.swing.GroupLayout.PREFERRED_SIZE, 164, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(18, 18, 18)
                                .addComponent(jButton7)))
                        .addGap(0, 0, Short.MAX_VALUE)))
                .addContainerGap())
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel6)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jComboBox3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jButton7))
                .addGap(42, 42, 42)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 199, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jButton8)
                    .addComponent(jButton11))
                .addGap(47, 47, 47))
        );

        javax.swing.GroupLayout crearTurnoP3Layout = new javax.swing.GroupLayout(crearTurnoP3);
        crearTurnoP3.setLayout(crearTurnoP3Layout);
        crearTurnoP3Layout.setHorizontalGroup(
            crearTurnoP3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(crearTurnoP3Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );
        crearTurnoP3Layout.setVerticalGroup(
            crearTurnoP3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(crearTurnoP3Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );

        crearTurnoPasosJTabbed.addTab("Paso 3: Cargar Analisis", crearTurnoP3);

        jLabel7.setText("Texto con los datos para confirmar");

        jButton10.setText("aceptar");

        jButton12.setText("anterior");
        jButton12.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton12ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel4Layout = new javax.swing.GroupLayout(jPanel4);
        jPanel4.setLayout(jPanel4Layout);
        jPanel4Layout.setHorizontalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel4Layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(jLabel7))
                    .addGroup(jPanel4Layout.createSequentialGroup()
                        .addGap(25, 25, 25)
                        .addComponent(jButton12)
                        .addGap(18, 18, 18)
                        .addComponent(jButton10, javax.swing.GroupLayout.PREFERRED_SIZE, 102, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(547, Short.MAX_VALUE))
        );
        jPanel4Layout.setVerticalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel7)
                .addGap(97, 97, 97)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jButton10)
                    .addComponent(jButton12))
                .addContainerGap(226, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout crearTurnoP4Layout = new javax.swing.GroupLayout(crearTurnoP4);
        crearTurnoP4.setLayout(crearTurnoP4Layout);
        crearTurnoP4Layout.setHorizontalGroup(
            crearTurnoP4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(crearTurnoP4Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel4, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );
        crearTurnoP4Layout.setVerticalGroup(
            crearTurnoP4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(crearTurnoP4Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel4, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );

        crearTurnoPasosJTabbed.addTab("Paso 4: Confirmación", crearTurnoP4);

        javax.swing.GroupLayout crearTurnoJpanelLayout = new javax.swing.GroupLayout(crearTurnoJpanel);
        crearTurnoJpanel.setLayout(crearTurnoJpanelLayout);
        crearTurnoJpanelLayout.setHorizontalGroup(
            crearTurnoJpanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(crearTurnoJpanelLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(crearTurnoJpanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, crearTurnoJpanelLayout.createSequentialGroup()
                        .addGap(0, 0, Short.MAX_VALUE)
                        .addComponent(jButton2))
                    .addComponent(crearTurnoPasosJTabbed))
                .addContainerGap())
        );
        crearTurnoJpanelLayout.setVerticalGroup(
            crearTurnoJpanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, crearTurnoJpanelLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(crearTurnoPasosJTabbed)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jButton2, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );

        backgroundJpanel.add(crearTurnoJpanel, "card3");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(backgroundJpanel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(backgroundJpanel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        mainJpanel.setVisible(false);
        crearTurnoJpanel.setVisible(true);
    }//GEN-LAST:event_jButton1ActionPerformed

    private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton2ActionPerformed
        mainJpanel.setVisible(true);
        crearTurnoJpanel.setVisible(false);
    }//GEN-LAST:event_jButton2ActionPerformed

    private void jButtonSiguienteAgregarPacienteP1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonSiguienteAgregarPacienteP1ActionPerformed
        crearTurnoPasosJTabbed.setSelectedIndex(1);
    }//GEN-LAST:event_jButtonSiguienteAgregarPacienteP1ActionPerformed

    private void jButton6ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton6ActionPerformed
        crearTurnoPasosJTabbed.setSelectedIndex(2);
    }//GEN-LAST:event_jButton6ActionPerformed

    private void jButton8ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton8ActionPerformed
        crearTurnoPasosJTabbed.setSelectedIndex(3);
        crearTurnoPasosJTabbed.setEnabled(true);
    }//GEN-LAST:event_jButton8ActionPerformed

    private void jButton9ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton9ActionPerformed
        crearTurnoPasosJTabbed.setSelectedIndex(0);
    }//GEN-LAST:event_jButton9ActionPerformed

    private void jButton11ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton11ActionPerformed
        crearTurnoPasosJTabbed.setSelectedIndex(1);
    }//GEN-LAST:event_jButton11ActionPerformed

    private void jButton12ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton12ActionPerformed
        crearTurnoPasosJTabbed.setSelectedIndex(2);
    }//GEN-LAST:event_jButton12ActionPerformed

    private void jButtonCrearPacienteActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonCrearPacienteActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jButtonCrearPacienteActionPerformed

    private void jTextFieldDniPacienteKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jTextFieldDniPacienteKeyPressed
        
    }//GEN-LAST:event_jTextFieldDniPacienteKeyPressed

    private void jTextFieldDniPacienteInputMethodTextChanged(java.awt.event.InputMethodEvent evt) {//GEN-FIRST:event_jTextFieldDniPacienteInputMethodTextChanged

    }//GEN-LAST:event_jTextFieldDniPacienteInputMethodTextChanged

    private void jTextFieldDniPacienteCaretUpdate(javax.swing.event.CaretEvent evt) {//GEN-FIRST:event_jTextFieldDniPacienteCaretUpdate
        
        
        String dniString = jTextFieldDniPaciente.getText();
        if (dniString.equals("")){
            dniString = "0";
        }
        
        int dni = Integer.valueOf(dniString);
        DAOPacienteSQL daoPaciente = new DAOPacienteSQL();
        if (daoPaciente.get(dni).isPresent()){
//            jLabelPacienteInexistente.setVisible(false);
            jButtonCrearPaciente.setEnabled(false);
            jComboBoxObraSocial.setEnabled(true);
            jCheckBoxParticular.setEnabled(true);
            
            
            
            System.out.println(daoPaciente.get(dni).get());
        }
        else{
//            jLabelPacienteInexistente.setVisible(true);
            jButtonCrearPaciente.setEnabled(true);
            
            jComboBoxObraSocial.setEnabled(false);
            jCheckBoxParticular.setEnabled(false);
        }
    }//GEN-LAST:event_jTextFieldDniPacienteCaretUpdate

    private void jTextFieldDniPacienteKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jTextFieldDniPacienteKeyTyped
        // TODO add your handling code here:
    }//GEN-LAST:event_jTextFieldDniPacienteKeyTyped

    private void jTextFieldDniPacienteActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jTextFieldDniPacienteActionPerformed

    }//GEN-LAST:event_jTextFieldDniPacienteActionPerformed

    private void jCheckBoxParticularActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jCheckBoxParticularActionPerformed
        if(jCheckBoxParticular.isSelected()){
            jComboBoxObraSocial.setEnabled(false);
        }
        else{
            jComboBoxObraSocial.setEnabled(true);
        }
    }//GEN-LAST:event_jCheckBoxParticularActionPerformed

    private void jTextFieldDniPacientePropertyChange(java.beans.PropertyChangeEvent evt) {//GEN-FIRST:event_jTextFieldDniPacientePropertyChange
        // TODO add your handling code here:
    }//GEN-LAST:event_jTextFieldDniPacientePropertyChange

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
            java.util.logging.Logger.getLogger(Main.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(Main.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(Main.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(Main.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new Main().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JPanel backgroundJpanel;
    private javax.swing.JPanel crearTurnoJpanel;
    private javax.swing.JPanel crearTurnoP1;
    private javax.swing.JPanel crearTurnoP2;
    private javax.swing.JPanel crearTurnoP3;
    private javax.swing.JPanel crearTurnoP4;
    private javax.swing.JTabbedPane crearTurnoPasosJTabbed;
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton10;
    private javax.swing.JButton jButton11;
    private javax.swing.JButton jButton12;
    private javax.swing.JButton jButton2;
    private javax.swing.JButton jButton6;
    private javax.swing.JButton jButton7;
    private javax.swing.JButton jButton8;
    private javax.swing.JButton jButton9;
    private javax.swing.JButton jButtonCrearPaciente;
    private javax.swing.JButton jButtonSiguienteAgregarPacienteP1;
    private com.toedter.calendar.JCalendar jCalendar1;
    private com.toedter.calendar.JCalendar jCalendar2;
    private javax.swing.JCheckBox jCheckBoxParticular;
    private javax.swing.JComboBox<String> jComboBox3;
    private javax.swing.JComboBox<String> jComboBoxObraSocial;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JTable jTable1;
    private javax.swing.JTable jTable2;
    private javax.swing.JTextField jTextFieldDniPaciente;
    private javax.swing.JList<String> listaProximasFechasLibres;
    private javax.swing.JPanel mainJpanel;
    // End of variables declaration//GEN-END:variables
}
