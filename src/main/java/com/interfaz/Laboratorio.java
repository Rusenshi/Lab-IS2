/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package com.interfaz;

import com.formdev.flatlaf.FlatIntelliJLaf;
import com.logica.ManagerAnalisis;
import com.logica.ManagerInforme;
import com.logica.ManagerObraSocial;
import com.logica.ManagerPaciente;
import com.logica.ManagerReactivo;
import com.logica.ManagerTurno;
import com.objetos.Analisis;
import com.objetos.ObraSocial;
import com.objetos.Paciente;
import com.objetos.Reactivo;
import com.objetos.Turno;
import java.awt.Color;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.Vector;
import javax.swing.JButton;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author jerem
 */
public class Laboratorio extends javax.swing.JFrame {
    // Variables Panel: Dar Turno
    private static int crearTurnoActualStep = 0;
    private static final int MAX_TURNOS = 40;
    private static int turnosDisponibles = MAX_TURNOS; // Almacena los turnos disponibles para la fecha actual
    private static Set<String> analisisElegidos = new HashSet<>();
    private static DefaultTableModel dataModelanalisisElegidos = new DefaultTableModel();
//    private static List<Integer> listaClaves = ManagerTurno.armarListaClaves();
        
    // Variables Panel: Generar Resultado
    private static Set<String> analisisInforme = new HashSet<>();
    private static DefaultTableModel dataModelanalisisInforme = new DefaultTableModel(){
            // Sobreescritura del metodo isCellEditable para anular la edición de alguna columna
            boolean[] canEdit = new boolean[]{
                    false,false,true
            };

            @Override
            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit[columnIndex];
            }
    };
    
    // Variables Panel: Agregar Paciente
    private static Set<String> obrasSocialesElegidas = new HashSet<>();
    private static DefaultTableModel dataModelobrasSocialesElegidas = new DefaultTableModel();
    private static int crearPacienteActualStep = 0;
    
    // Variables Panel: Generar Resultado
    private static Set<String> reactivosAnalisis = new HashSet<>();
    private static DefaultTableModel dataModelReactivosAnalisis = new DefaultTableModel(){

            boolean[] canEdit = new boolean[]{
                    false,false,true
            };

            @Override
            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit[columnIndex];
            }
    };
    private static int crearAnalisisActualStep = 0;
    
    
    
    /**
     * Creates new form Laboratorio
     */
    public Laboratorio() {
        // Especificar el tema flatlaf (look and feel)
        try {
            UIManager.setLookAndFeel(new FlatIntelliJLaf());
        } catch (UnsupportedLookAndFeelException ex) {
            System.out.println("Error al cargar la interfaz");
        }
        
        
        
        initComponents();
        
        
        
        // Inicializacion de componentes de la interfaz
        // Panel: Dar Turno
        jButtonCrearTurnoAnterior.setVisible(false);
        jComboBoxObraSocialTurno.removeAllItems();
        jComboBoxObraSocialCrearPaciente.removeAllItems();
        for (ObraSocial os : ManagerObraSocial.obtenerObrasSocialesDisponibles()){
            jComboBoxObraSocialCrearPaciente.addItem(os.getNombre());
        }
        jComboBoxObraSocialCrearPaciente.removeItem("PARTICULAR");
        
        
        jComboCrearTurnoAnalisis.removeAllItems();
        for (Analisis analisis : ManagerTurno.obtenerAnalisis()){
            jComboCrearTurnoAnalisis.addItem(analisis.getNombre());
        }
        
        // Panel: Generar Resultado
        dataModelanalisisInforme.addColumn("Analisis");
        dataModelanalisisInforme.addColumn("Valor Referencia");
        dataModelanalisisInforme.addColumn("Valor Obtenido");
        jTableInformeAnalisis.setModel(dataModelanalisisInforme);
        
        
        // Panel: Crear Paciente
        dataModelobrasSocialesElegidas.addColumn("Obra Social");
        dataModelanalisisElegidos.addColumn("Analisis");
        
        jTableObrasSocialesAgregarPaciente.setModel(dataModelobrasSocialesElegidas);
        jTableAnalisisAgregarTurno.setModel(dataModelanalisisElegidos);
        
        // Panel: Consultar Analisis
        for(Analisis analisis : ManagerAnalisis.obtenerTodosLosAnalisis()){
            jComboBoxConsultarAnalisis.addItem(analisis.getNombre());
        }
        
        // Panel: Agregar Analisis
        dataModelReactivosAnalisis.addColumn("Nombre");
        dataModelReactivosAnalisis.addColumn("Capacidad");
        dataModelReactivosAnalisis.addColumn("Cantidad a usar");
        jTableAgregarPacienteReactivos.setModel(dataModelReactivosAnalisis);
        
        jComboBoxCrearAnalisisReactivos.removeAllItems();
        for(String nombre : ManagerReactivo.obtenerListaNombresReactivos()){
            jComboBoxCrearAnalisisReactivos.addItem(nombre);
        }
        jButtonCrearAnalisisAnterior.setVisible(false);
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jScrollBar1 = new javax.swing.JScrollBar();
        jPanelBarraLateral = new javax.swing.JPanel();
        jButtonLateralDarTurno = new javax.swing.JButton();
        jButtonLateralCancelarTurno = new javax.swing.JButton();
        jButtonLateralGenerarResultado = new javax.swing.JButton();
        jButtonLateralAgregarPaciente = new javax.swing.JButton();
        jButtonLateralAgregarAnalisis = new javax.swing.JButton();
        jButtonLateralBuscarAnalisis = new javax.swing.JButton();
        jButtonLateralConsultarStockReactivo = new javax.swing.JButton();
        jLabel7 = new javax.swing.JLabel();
        jPanelBackground = new javax.swing.JPanel();
        jPanelDarTurno = new javax.swing.JPanel();
        crearTurnoJpanel = new javax.swing.JPanel();
        jButtonCrearTurnoSiguiente = new javax.swing.JButton();
        jButtonCrearTurnoAnterior = new javax.swing.JButton();
        crearTurnoPasosJTabbed = new javax.swing.JTabbedPane();
        crearTurnoP1 = new javax.swing.JPanel();
        jPanel1 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jTextFieldDniPaciente = new javax.swing.JTextField();
        jButtonCrearPaciente = new javax.swing.JButton();
        jCheckBoxParticular = new javax.swing.JCheckBox();
        jComboBoxObraSocialTurno = new javax.swing.JComboBox<>();
        jLabel4 = new javax.swing.JLabel();
        jTextFieldDniPacienteMedico = new javax.swing.JTextField();
        jLabel5 = new javax.swing.JLabel();
        jTextFieldDniPacienteDiagnostico = new javax.swing.JTextField();
        errCrearTurnoDni = new javax.swing.JLabel();
        errCrearTurnoMedico = new javax.swing.JLabel();
        errCrearTurnoDiagnostico = new javax.swing.JLabel();
        crearTurnoP2 = new javax.swing.JPanel();
        jPanel2 = new javax.swing.JPanel();
        jCalendarPedirTurno = new com.toedter.calendar.JCalendar();
        jLabelPedirTurnoDisponibles = new javax.swing.JLabel();
        crearTurnoP3 = new javax.swing.JPanel();
        jPanel3 = new javax.swing.JPanel();
        jButtonAgregarAnalisisATurno = new javax.swing.JButton();
        jLabel6 = new javax.swing.JLabel();
        jComboCrearTurnoAnalisis = new javax.swing.JComboBox<>();
        jScrollPane1 = new javax.swing.JScrollPane();
        jTableAnalisisAgregarTurno = new javax.swing.JTable();
        jButtonEliminarAnalisisATurno = new javax.swing.JButton();
        crearTurnoP4 = new javax.swing.JPanel();
        jPanel4 = new javax.swing.JPanel();
        jPanel9 = new javax.swing.JPanel();
        jLabelCrearTurnoConfirmacion = new javax.swing.JLabel();
        jLabelConfirmacionDni = new javax.swing.JLabel();
        jLabelConfirmacionObraSocial = new javax.swing.JLabel();
        jLabelConfirmacionNombre = new javax.swing.JLabel();
        jLabelConfirmacionApellido = new javax.swing.JLabel();
        jLabelCrearTurnoConfirmacion1 = new javax.swing.JLabel();
        jSeparator1 = new javax.swing.JSeparator();
        jLabelConfirmacionFecha = new javax.swing.JLabel();
        jSeparator2 = new javax.swing.JSeparator();
        jLabelCrearTurnoConfirmacion2 = new javax.swing.JLabel();
        jScrollPane4 = new javax.swing.JScrollPane();
        jLabelConfirmacionAnalisisList = new javax.swing.JList<>();
        jLabelConfirmacionMedico = new javax.swing.JLabel();
        jLabelConfirmacionDiagnostico = new javax.swing.JLabel();
        jButton1 = new javax.swing.JButton();
        jPanelGenerarResultado = new javax.swing.JPanel();
        jPanel5 = new javax.swing.JPanel();
        jLabel3 = new javax.swing.JLabel();
        jButtonGuardarResultado = new javax.swing.JButton();
        jScrollPane2 = new javax.swing.JScrollPane();
        jTableInformeAnalisis = new javax.swing.JTable();
        jTextFieldBuscarTurno = new javax.swing.JTextField();
        jButtonBuscarTurno = new javax.swing.JButton();
        jComboBoxTurnosDNI = new javax.swing.JComboBox<>();
        jSeparator3 = new javax.swing.JSeparator();
        jLabelCargarResultadoTurnoCargado = new javax.swing.JLabel();
        jPanelAgregarPaciente = new javax.swing.JPanel();
        jPanel6 = new javax.swing.JPanel();
        jTabbedPanelCrearPaciente = new javax.swing.JTabbedPane();
        jPanelAgregarPacienteP1 = new javax.swing.JPanel();
        jPanel7 = new javax.swing.JPanel();
        jLabel9 = new javax.swing.JLabel();
        jTextFieldDni = new javax.swing.JTextField();
        jTextFieldNombre = new javax.swing.JTextField();
        jLabel10 = new javax.swing.JLabel();
        jLabel11 = new javax.swing.JLabel();
        jTextFieldApellido = new javax.swing.JTextField();
        jLabel12 = new javax.swing.JLabel();
        jTextFieldEdad = new javax.swing.JTextField();
        jLabel13 = new javax.swing.JLabel();
        jRadioButtonMujer = new javax.swing.JRadioButton();
        jRadioButtonHombre = new javax.swing.JRadioButton();
        jErrorCrearPacienteDni = new javax.swing.JLabel();
        jErrorCrearPacienteNombre = new javax.swing.JLabel();
        jErrorCrearPacienteApellido = new javax.swing.JLabel();
        jErrorCrearPacienteEdad = new javax.swing.JLabel();
        jErrorCrearPacienteSexo = new javax.swing.JLabel();
        jPanelAgregarPacienteP2 = new javax.swing.JPanel();
        jPanel8 = new javax.swing.JPanel();
        jLabel14 = new javax.swing.JLabel();
        jTextFieldDomicilio = new javax.swing.JTextField();
        jTextFieldTelefono = new javax.swing.JTextField();
        jLabel15 = new javax.swing.JLabel();
        jLabel16 = new javax.swing.JLabel();
        jTextFieldCorreo = new javax.swing.JTextField();
        jErrorCrearPacienteDomicilio = new javax.swing.JLabel();
        jErrorCrearPacienteTelefono = new javax.swing.JLabel();
        jErrorCrearPacienteCorreo = new javax.swing.JLabel();
        jPanelAgregarPacienteP3 = new javax.swing.JPanel();
        jLabel8 = new javax.swing.JLabel();
        jComboBoxObraSocialCrearPaciente = new javax.swing.JComboBox<>();
        jButton6 = new javax.swing.JButton();
        jScrollPane3 = new javax.swing.JScrollPane();
        jTableObrasSocialesAgregarPaciente = new javax.swing.JTable();
        jErrorCrearPacienteObraSocial = new javax.swing.JLabel();
        jButton2 = new javax.swing.JButton();
        jButtonCrearPacienteSiguiente = new javax.swing.JButton();
        jButtonCrearPacienteAnterior = new javax.swing.JButton();
        jPanelEnConstruccion = new javax.swing.JPanel();
        jLabel17 = new javax.swing.JLabel();
        jPanelAgregarAnalisis = new javax.swing.JPanel();
        jPanel10 = new javax.swing.JPanel();
        jTabbedPanelAgregarAnalisis = new javax.swing.JTabbedPane();
        jPanelAgregarAnalisisDatosBase = new javax.swing.JPanel();
        jPanel11 = new javax.swing.JPanel();
        jLabel21 = new javax.swing.JLabel();
        jTextFieldCrearAnalisisNombre = new javax.swing.JTextField();
        jTextFieldCrearAnalisisValorReferencia = new javax.swing.JTextField();
        jLabel22 = new javax.swing.JLabel();
        jLabel23 = new javax.swing.JLabel();
        jTextFieldCrearAnalisisMetodoUsado = new javax.swing.JTextField();
        jTextFieldCrearAnalisisMonto = new javax.swing.JTextField();
        jLabel24 = new javax.swing.JLabel();
        jPanelAgregarPacienteP4 = new javax.swing.JPanel();
        jLabel25 = new javax.swing.JLabel();
        jComboBoxCrearAnalisisReactivos = new javax.swing.JComboBox<>();
        jButtonAgregarAnalisisAgregarReactivo = new javax.swing.JButton();
        jScrollPane5 = new javax.swing.JScrollPane();
        jTableAgregarPacienteReactivos = new javax.swing.JTable();
        jErrorCrearPacienteObraSocial1 = new javax.swing.JLabel();
        jButtonAgregarAnalisisEliminarReactivo = new javax.swing.JButton();
        jButtonCrearAnalisisSiguiente = new javax.swing.JButton();
        jButtonCrearAnalisisAnterior = new javax.swing.JButton();
        jPanelConsultarAnalisis = new javax.swing.JPanel();
        crearTurnoP5 = new javax.swing.JPanel();
        jPanel13 = new javax.swing.JPanel();
        jButtonBuscarAnalisis = new javax.swing.JButton();
        jLabel26 = new javax.swing.JLabel();
        jScrollPane6 = new javax.swing.JScrollPane();
        jTableBuscarAnalisisReactivos = new javax.swing.JTable();
        jTextFieldNombreConsultarAnalisis = new javax.swing.JTextField();
        jPanel12 = new javax.swing.JPanel();
        jLabelBuscarAnalisisValorReferencia = new javax.swing.JLabel();
        jLabelBuscarAnalisisMonto = new javax.swing.JLabel();
        jLabelBuscarAnalisisMetodoUsado = new javax.swing.JLabel();
        jLabel35 = new javax.swing.JLabel();
        jComboBoxConsultarAnalisis = new javax.swing.JComboBox<>();
        jPanelCancelarTurno = new javax.swing.JPanel();
        jPanel14 = new javax.swing.JPanel();
        jCalendarCancelarTurno = new com.toedter.calendar.JCalendar();
        jComboBoxCancelarTurno = new javax.swing.JComboBox<>();
        jButton5 = new javax.swing.JButton();
        jTextFieldDniCancelarTurno = new javax.swing.JTextField();
        jLabel32 = new javax.swing.JLabel();
        jLabel33 = new javax.swing.JLabel();
        jLabel34 = new javax.swing.JLabel();
        jPanelConsultarStock = new javax.swing.JPanel();
        jPanel16 = new javax.swing.JPanel();
        jButton10 = new javax.swing.JButton();
        jPanelPedirReactivos = new javax.swing.JPanel();
        jPanel15 = new javax.swing.JPanel();
        jButtonAgregarAnalisisATurno2 = new javax.swing.JButton();
        jLabel30 = new javax.swing.JLabel();
        jComboCrearTurnoAnalisis1 = new javax.swing.JComboBox<>();
        jScrollPane7 = new javax.swing.JScrollPane();
        jTableAnalisisAgregarTurno2 = new javax.swing.JTable();
        jLabel31 = new javax.swing.JLabel();
        jComboCrearTurnoAnalisis2 = new javax.swing.JComboBox<>();
        jButton8 = new javax.swing.JButton();
        jButton9 = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setMaximumSize(new java.awt.Dimension(800, 480));
        setMinimumSize(new java.awt.Dimension(800, 480));
        setResizable(false);
        setSize(new java.awt.Dimension(800, 480));
        getContentPane().setLayout(new javax.swing.BoxLayout(getContentPane(), javax.swing.BoxLayout.LINE_AXIS));

        jPanelBarraLateral.setBackground(new java.awt.Color(102, 153, 255));
        jPanelBarraLateral.setMaximumSize(new java.awt.Dimension(200, 4096));
        jPanelBarraLateral.setMinimumSize(new java.awt.Dimension(200, 480));
        jPanelBarraLateral.setPreferredSize(new java.awt.Dimension(200, 480));
        jPanelBarraLateral.setLayout(new java.awt.FlowLayout(java.awt.FlowLayout.CENTER, 5, 0));

        jButtonLateralDarTurno.setBackground(new java.awt.Color(102, 153, 255));
        jButtonLateralDarTurno.setFont(new java.awt.Font("Segoe UI", 1, 16)); // NOI18N
        jButtonLateralDarTurno.setForeground(new java.awt.Color(255, 255, 255));
        jButtonLateralDarTurno.setIcon(new javax.swing.ImageIcon(getClass().getResource("/graficosIconos/iconTurno_miniatura.png"))); // NOI18N
        jButtonLateralDarTurno.setText("Dar Turno");
        jButtonLateralDarTurno.setBorderPainted(false);
        jButtonLateralDarTurno.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        jButtonLateralDarTurno.setPreferredSize(new java.awt.Dimension(200, 50));
        jButtonLateralDarTurno.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonLateralDarTurnoActionPerformed(evt);
            }
        });
        jPanelBarraLateral.add(jButtonLateralDarTurno);

        jButtonLateralCancelarTurno.setBackground(new java.awt.Color(102, 153, 255));
        jButtonLateralCancelarTurno.setFont(new java.awt.Font("Segoe UI", 1, 16)); // NOI18N
        jButtonLateralCancelarTurno.setForeground(new java.awt.Color(255, 255, 255));
        jButtonLateralCancelarTurno.setIcon(new javax.swing.ImageIcon(getClass().getResource("/graficosIconos/iconCancelarTurno.png"))); // NOI18N
        jButtonLateralCancelarTurno.setText("Cancelar Turno");
        jButtonLateralCancelarTurno.setBorderPainted(false);
        jButtonLateralCancelarTurno.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        jButtonLateralCancelarTurno.setPreferredSize(new java.awt.Dimension(200, 50));
        jButtonLateralCancelarTurno.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonLateralCancelarTurnoActionPerformed(evt);
            }
        });
        jPanelBarraLateral.add(jButtonLateralCancelarTurno);

        jButtonLateralGenerarResultado.setBackground(new java.awt.Color(102, 153, 255));
        jButtonLateralGenerarResultado.setFont(new java.awt.Font("Segoe UI", 1, 16)); // NOI18N
        jButtonLateralGenerarResultado.setForeground(new java.awt.Color(255, 255, 255));
        jButtonLateralGenerarResultado.setIcon(new javax.swing.ImageIcon(getClass().getResource("/graficosIconos/iconResultado_thumb.png"))); // NOI18N
        jButtonLateralGenerarResultado.setText("Cargar Resultado");
        jButtonLateralGenerarResultado.setBorderPainted(false);
        jButtonLateralGenerarResultado.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        jButtonLateralGenerarResultado.setPreferredSize(new java.awt.Dimension(200, 50));
        jButtonLateralGenerarResultado.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonLateralGenerarResultadoActionPerformed(evt);
            }
        });
        jPanelBarraLateral.add(jButtonLateralGenerarResultado);

        jButtonLateralAgregarPaciente.setBackground(new java.awt.Color(102, 153, 255));
        jButtonLateralAgregarPaciente.setFont(new java.awt.Font("Segoe UI", 1, 16)); // NOI18N
        jButtonLateralAgregarPaciente.setForeground(new java.awt.Color(255, 255, 255));
        jButtonLateralAgregarPaciente.setIcon(new javax.swing.ImageIcon(getClass().getResource("/graficosIconos/iconPaciente.png"))); // NOI18N
        jButtonLateralAgregarPaciente.setText("Agregar Paciente");
        jButtonLateralAgregarPaciente.setBorderPainted(false);
        jButtonLateralAgregarPaciente.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        jButtonLateralAgregarPaciente.setPreferredSize(new java.awt.Dimension(200, 50));
        jButtonLateralAgregarPaciente.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonLateralAgregarPacienteActionPerformed(evt);
            }
        });
        jPanelBarraLateral.add(jButtonLateralAgregarPaciente);

        jButtonLateralAgregarAnalisis.setBackground(new java.awt.Color(102, 153, 255));
        jButtonLateralAgregarAnalisis.setFont(new java.awt.Font("Segoe UI", 1, 16)); // NOI18N
        jButtonLateralAgregarAnalisis.setForeground(new java.awt.Color(255, 255, 255));
        jButtonLateralAgregarAnalisis.setIcon(new javax.swing.ImageIcon(getClass().getResource("/graficosIconos/iconAnalisis.png"))); // NOI18N
        jButtonLateralAgregarAnalisis.setText("Agregar Analisis");
        jButtonLateralAgregarAnalisis.setBorderPainted(false);
        jButtonLateralAgregarAnalisis.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        jButtonLateralAgregarAnalisis.setPreferredSize(new java.awt.Dimension(200, 50));
        jButtonLateralAgregarAnalisis.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonLateralAgregarAnalisisActionPerformed(evt);
            }
        });
        jPanelBarraLateral.add(jButtonLateralAgregarAnalisis);

        jButtonLateralBuscarAnalisis.setBackground(new java.awt.Color(102, 153, 255));
        jButtonLateralBuscarAnalisis.setFont(new java.awt.Font("Segoe UI", 1, 16)); // NOI18N
        jButtonLateralBuscarAnalisis.setForeground(new java.awt.Color(255, 255, 255));
        jButtonLateralBuscarAnalisis.setIcon(new javax.swing.ImageIcon(getClass().getResource("/graficosIconos/iconAnalisis.png"))); // NOI18N
        jButtonLateralBuscarAnalisis.setText("Buscar Analisis");
        jButtonLateralBuscarAnalisis.setBorderPainted(false);
        jButtonLateralBuscarAnalisis.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        jButtonLateralBuscarAnalisis.setPreferredSize(new java.awt.Dimension(200, 50));
        jButtonLateralBuscarAnalisis.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonLateralBuscarAnalisisActionPerformed(evt);
            }
        });
        jPanelBarraLateral.add(jButtonLateralBuscarAnalisis);

        jButtonLateralConsultarStockReactivo.setBackground(new java.awt.Color(102, 153, 255));
        jButtonLateralConsultarStockReactivo.setFont(new java.awt.Font("Segoe UI", 1, 16)); // NOI18N
        jButtonLateralConsultarStockReactivo.setForeground(new java.awt.Color(255, 255, 255));
        jButtonLateralConsultarStockReactivo.setIcon(new javax.swing.ImageIcon(getClass().getResource("/graficosIconos/iconStock.png"))); // NOI18N
        jButtonLateralConsultarStockReactivo.setText("Ver Stock");
        jButtonLateralConsultarStockReactivo.setBorderPainted(false);
        jButtonLateralConsultarStockReactivo.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        jButtonLateralConsultarStockReactivo.setPreferredSize(new java.awt.Dimension(200, 50));
        jButtonLateralConsultarStockReactivo.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonLateralConsultarStockReactivoActionPerformed(evt);
            }
        });
        jPanelBarraLateral.add(jButtonLateralConsultarStockReactivo);

        jLabel7.setIcon(new javax.swing.ImageIcon(getClass().getResource("/graficosIconos/logoLab.png"))); // NOI18N
        jPanelBarraLateral.add(jLabel7);

        getContentPane().add(jPanelBarraLateral);

        jPanelBackground.setMaximumSize(new java.awt.Dimension(600, 2147483647));
        jPanelBackground.setPreferredSize(new java.awt.Dimension(600, 480));
        jPanelBackground.setLayout(new java.awt.CardLayout());

        jPanelDarTurno.setBackground(new java.awt.Color(255, 255, 255));
        jPanelDarTurno.setLayout(new javax.swing.BoxLayout(jPanelDarTurno, javax.swing.BoxLayout.LINE_AXIS));

        crearTurnoJpanel.setBackground(new java.awt.Color(255, 255, 255));

        jButtonCrearTurnoSiguiente.setText("Siguiente");
        jButtonCrearTurnoSiguiente.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonCrearTurnoSiguienteActionPerformed(evt);
            }
        });

        jButtonCrearTurnoAnterior.setText("Anterior");
        jButtonCrearTurnoAnterior.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonCrearTurnoAnteriorActionPerformed(evt);
            }
        });

        crearTurnoPasosJTabbed.setBackground(new java.awt.Color(255, 255, 255));
        crearTurnoPasosJTabbed.setEnabled(false);
        crearTurnoPasosJTabbed.addChangeListener(new javax.swing.event.ChangeListener() {
            public void stateChanged(javax.swing.event.ChangeEvent evt) {
                crearTurnoPasosJTabbedStateChanged(evt);
            }
        });

        crearTurnoP1.setBackground(new java.awt.Color(255, 255, 255));

        jPanel1.setBackground(new java.awt.Color(255, 255, 255));

        jLabel1.setText("DNI:");

        jLabel2.setText("Obra Social: ");

        jTextFieldDniPaciente.setToolTipText("Ingrese solo numeros. Si el paciente no existe, se habilitará la pestaña \"Crear\"");
        jTextFieldDniPaciente.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusLost(java.awt.event.FocusEvent evt) {
                jTextFieldDniPacienteFocusLost(evt);
            }
        });
        jTextFieldDniPaciente.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jTextFieldDniPacienteActionPerformed(evt);
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

        jComboBoxObraSocialTurno.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        jComboBoxObraSocialTurno.setEnabled(false);

        jLabel4.setText("Nombre Medico:");

        jTextFieldDniPacienteMedico.setToolTipText("Solo texto");

        jLabel5.setText("Diagnostico:");

        jTextFieldDniPacienteDiagnostico.setToolTipText("Solo texto");

        errCrearTurnoDni.setFont(new java.awt.Font("Segoe UI", 2, 12)); // NOI18N
        errCrearTurnoDni.setForeground(new java.awt.Color(255, 51, 51));

        errCrearTurnoMedico.setFont(new java.awt.Font("Segoe UI", 2, 12)); // NOI18N
        errCrearTurnoMedico.setForeground(new java.awt.Color(255, 51, 51));

        errCrearTurnoDiagnostico.setFont(new java.awt.Font("Segoe UI", 2, 12)); // NOI18N
        errCrearTurnoDiagnostico.setForeground(new java.awt.Color(255, 51, 51));

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(jTextFieldDniPaciente, javax.swing.GroupLayout.DEFAULT_SIZE, 127, Short.MAX_VALUE)
                            .addComponent(jComboBoxObraSocialTurno, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addGap(18, 18, 18)
                                .addComponent(jButtonCrearPaciente, javax.swing.GroupLayout.PREFERRED_SIZE, 78, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                                .addGap(18, 18, 18)
                                .addComponent(jCheckBoxParticular, javax.swing.GroupLayout.PREFERRED_SIZE, 81, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addComponent(jLabel1)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(errCrearTurnoDni))
                            .addComponent(jTextFieldDniPacienteDiagnostico)
                            .addComponent(jTextFieldDniPacienteMedico, javax.swing.GroupLayout.Alignment.TRAILING)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addComponent(jLabel2)
                                .addGap(0, 0, Short.MAX_VALUE))
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addComponent(jLabel4)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(errCrearTurnoMedico))
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addComponent(jLabel5)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(errCrearTurnoDiagnostico)))
                        .addContainerGap())))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel1)
                    .addComponent(errCrearTurnoDni))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jButtonCrearPaciente, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jTextFieldDniPaciente, javax.swing.GroupLayout.PREFERRED_SIZE, 34, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jLabel2)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jComboBoxObraSocialTurno, javax.swing.GroupLayout.PREFERRED_SIZE, 36, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jCheckBoxParticular, javax.swing.GroupLayout.PREFERRED_SIZE, 36, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel4)
                    .addComponent(errCrearTurnoMedico))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jTextFieldDniPacienteMedico, javax.swing.GroupLayout.PREFERRED_SIZE, 36, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel5)
                    .addComponent(errCrearTurnoDiagnostico))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jTextFieldDniPacienteDiagnostico, javax.swing.GroupLayout.PREFERRED_SIZE, 80, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(23, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout crearTurnoP1Layout = new javax.swing.GroupLayout(crearTurnoP1);
        crearTurnoP1.setLayout(crearTurnoP1Layout);
        crearTurnoP1Layout.setHorizontalGroup(
            crearTurnoP1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(crearTurnoP1Layout.createSequentialGroup()
                .addGap(178, 178, 178)
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(178, Short.MAX_VALUE))
        );
        crearTurnoP1Layout.setVerticalGroup(
            crearTurnoP1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(crearTurnoP1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );

        crearTurnoPasosJTabbed.addTab("Paso 1: Cargar Paciente", crearTurnoP1);

        crearTurnoP2.setBackground(new java.awt.Color(255, 255, 255));

        jPanel2.setBackground(new java.awt.Color(255, 255, 255));

        jCalendarPedirTurno.addPropertyChangeListener(new java.beans.PropertyChangeListener() {
            public void propertyChange(java.beans.PropertyChangeEvent evt) {
                jCalendarPedirTurnoPropertyChange(evt);
            }
        });

        jLabelPedirTurnoDisponibles.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        jLabelPedirTurnoDisponibles.setText("Turnos Disponibles para la fecha <>: 12");

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jCalendarPedirTurno, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabelPedirTurnoDisponibles, javax.swing.GroupLayout.DEFAULT_SIZE, 544, Short.MAX_VALUE)
                .addContainerGap())
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addComponent(jCalendarPedirTurno, javax.swing.GroupLayout.PREFERRED_SIZE, 337, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabelPedirTurnoDisponibles)
                .addGap(0, 11, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout crearTurnoP2Layout = new javax.swing.GroupLayout(crearTurnoP2);
        crearTurnoP2.setLayout(crearTurnoP2Layout);
        crearTurnoP2Layout.setHorizontalGroup(
            crearTurnoP2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(crearTurnoP2Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(26, Short.MAX_VALUE))
        );
        crearTurnoP2Layout.setVerticalGroup(
            crearTurnoP2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(crearTurnoP2Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        crearTurnoPasosJTabbed.addTab("Paso 2: Elegir Fecha", crearTurnoP2);

        crearTurnoP3.setBackground(new java.awt.Color(255, 255, 255));

        jPanel3.setBackground(new java.awt.Color(255, 255, 255));

        jButtonAgregarAnalisisATurno.setText("Agregar");
        jButtonAgregarAnalisisATurno.setToolTipText("Solo puede agregar un Analisis");
        jButtonAgregarAnalisisATurno.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonAgregarAnalisisATurnoActionPerformed(evt);
            }
        });

        jLabel6.setText("Analisis:");

        jComboCrearTurnoAnalisis.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));

        jTableAnalisisAgregarTurno.setModel(new javax.swing.table.DefaultTableModel(
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
        jTableAnalisisAgregarTurno.setEnabled(false);
        jScrollPane1.setViewportView(jTableAnalisisAgregarTurno);

        jButtonEliminarAnalisisATurno.setText("Eliminar");
        jButtonEliminarAnalisisATurno.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonEliminarAnalisisATurnoActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 570, Short.MAX_VALUE)
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel6)
                            .addGroup(jPanel3Layout.createSequentialGroup()
                                .addComponent(jComboCrearTurnoAnalisis, javax.swing.GroupLayout.PREFERRED_SIZE, 164, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(18, 18, 18)
                                .addComponent(jButtonAgregarAnalisisATurno)
                                .addGap(18, 18, 18)
                                .addComponent(jButtonEliminarAnalisisATurno)))
                        .addContainerGap(224, Short.MAX_VALUE))))
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel6)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addComponent(jButtonAgregarAnalisisATurno, javax.swing.GroupLayout.PREFERRED_SIZE, 39, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addComponent(jComboCrearTurnoAnalisis)
                    .addComponent(jButtonEliminarAnalisisATurno, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addGap(18, 18, 18)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 284, Short.MAX_VALUE)
                .addContainerGap())
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

        crearTurnoP4.setBackground(new java.awt.Color(255, 255, 255));

        jPanel4.setBackground(new java.awt.Color(255, 255, 255));

        jLabelCrearTurnoConfirmacion.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        jLabelCrearTurnoConfirmacion.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabelCrearTurnoConfirmacion.setText("Paciente");

        jLabelConfirmacionDni.setText("DNI: ");

        jLabelConfirmacionObraSocial.setText("Obra Social:");

        jLabelConfirmacionNombre.setText("Nombre: ");

        jLabelConfirmacionApellido.setText("Apellido:");

        jLabelCrearTurnoConfirmacion1.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        jLabelCrearTurnoConfirmacion1.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabelCrearTurnoConfirmacion1.setText("Fecha");

        jLabelConfirmacionFecha.setText("Fecha: ");

        jLabelCrearTurnoConfirmacion2.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        jLabelCrearTurnoConfirmacion2.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabelCrearTurnoConfirmacion2.setText("Analisis");

        jScrollPane4.setEnabled(false);

        jLabelConfirmacionAnalisisList.setModel(new javax.swing.AbstractListModel<String>() {
            String[] strings = { "Item 1", "Item 2", "Item 3", "Item 4", "Item 5", "Item 5", "Item 1", "Item 2", "Item 3", "Item 4", "Item 5", "Item 5", "Item 1", "Item 2", "Item 3", "Item 4", "Item 5", "Item 5", "Item 1", "Item 2", "Item 3", "Item 4", "Item 5", "Item 5" };
            public int getSize() { return strings.length; }
            public String getElementAt(int i) { return strings[i]; }
        });
        jScrollPane4.setViewportView(jLabelConfirmacionAnalisisList);

        jLabelConfirmacionMedico.setText("Medico: ");

        jLabelConfirmacionDiagnostico.setText("Diagnostico: ");

        javax.swing.GroupLayout jPanel9Layout = new javax.swing.GroupLayout(jPanel9);
        jPanel9.setLayout(jPanel9Layout);
        jPanel9Layout.setHorizontalGroup(
            jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel9Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabelConfirmacionFecha, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jSeparator1)
                    .addComponent(jLabelCrearTurnoConfirmacion, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jLabelCrearTurnoConfirmacion1, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jSeparator2, javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jLabelCrearTurnoConfirmacion2, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jScrollPane4)
                    .addGroup(jPanel9Layout.createSequentialGroup()
                        .addComponent(jLabelConfirmacionDni, javax.swing.GroupLayout.PREFERRED_SIZE, 150, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 54, Short.MAX_VALUE)
                        .addComponent(jLabelConfirmacionObraSocial, javax.swing.GroupLayout.PREFERRED_SIZE, 150, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(jLabelConfirmacionNombre, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jLabelConfirmacionApellido, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jLabelConfirmacionMedico, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jLabelConfirmacionDiagnostico, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );
        jPanel9Layout.setVerticalGroup(
            jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel9Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabelCrearTurnoConfirmacion)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabelConfirmacionDni)
                    .addComponent(jLabelConfirmacionObraSocial))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabelConfirmacionNombre)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabelConfirmacionApellido)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jSeparator1, javax.swing.GroupLayout.PREFERRED_SIZE, 10, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabelCrearTurnoConfirmacion1)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabelConfirmacionFecha)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jSeparator2, javax.swing.GroupLayout.PREFERRED_SIZE, 10, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabelCrearTurnoConfirmacion2)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabelConfirmacionMedico)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabelConfirmacionDiagnostico)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane4, javax.swing.GroupLayout.DEFAULT_SIZE, 100, Short.MAX_VALUE)
                .addContainerGap())
        );

        javax.swing.GroupLayout jPanel4Layout = new javax.swing.GroupLayout(jPanel4);
        jPanel4.setLayout(jPanel4Layout);
        jPanel4Layout.setHorizontalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addGap(107, 107, 107)
                .addComponent(jPanel9, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(103, Short.MAX_VALUE))
        );
        jPanel4Layout.setVerticalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel9, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
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

        jButton1.setText("Cancelar");
        jButton1.setToolTipText("Permite vaciar todos los campos");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout crearTurnoJpanelLayout = new javax.swing.GroupLayout(crearTurnoJpanel);
        crearTurnoJpanel.setLayout(crearTurnoJpanelLayout);
        crearTurnoJpanelLayout.setHorizontalGroup(
            crearTurnoJpanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
            .addGroup(crearTurnoJpanelLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(crearTurnoJpanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addGroup(crearTurnoJpanelLayout.createSequentialGroup()
                        .addGap(6, 6, 6)
                        .addComponent(jButton1, javax.swing.GroupLayout.PREFERRED_SIZE, 80, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jButtonCrearTurnoAnterior, javax.swing.GroupLayout.PREFERRED_SIZE, 90, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(jButtonCrearTurnoSiguiente, javax.swing.GroupLayout.PREFERRED_SIZE, 90, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(crearTurnoPasosJTabbed, javax.swing.GroupLayout.PREFERRED_SIZE, 588, javax.swing.GroupLayout.PREFERRED_SIZE)))
        );
        crearTurnoJpanelLayout.setVerticalGroup(
            crearTurnoJpanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(crearTurnoJpanelLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(crearTurnoPasosJTabbed, javax.swing.GroupLayout.PREFERRED_SIZE, 422, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(crearTurnoJpanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jButtonCrearTurnoSiguiente, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jButtonCrearTurnoAnterior, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jButton1, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap())
        );

        jPanelDarTurno.add(crearTurnoJpanel);

        jPanelBackground.add(jPanelDarTurno, "card2");

        jPanelGenerarResultado.setBackground(new java.awt.Color(255, 255, 255));
        jPanelGenerarResultado.setLayout(new javax.swing.BoxLayout(jPanelGenerarResultado, javax.swing.BoxLayout.LINE_AXIS));

        jPanel5.setBackground(new java.awt.Color(255, 255, 255));

        jLabel3.setText("Buscar por DNI:");

        jButtonGuardarResultado.setText("Guardar");
        jButtonGuardarResultado.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonGuardarResultadoActionPerformed(evt);
            }
        });

        jTableInformeAnalisis.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null},
                {null, null, null},
                {null, null, null},
                {null, null, null}
            },
            new String [] {
                "Analisis", "Valor Referencia", "Valor Obtenido"
            }
        ));
        jScrollPane2.setViewportView(jTableInformeAnalisis);

        jTextFieldBuscarTurno.setToolTipText("Solo Numeros. Pulse Enter para cargar los turnos de este paciente.");
        jTextFieldBuscarTurno.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jTextFieldBuscarTurnoActionPerformed(evt);
            }
        });

        jButtonBuscarTurno.setText("Cargar");
        jButtonBuscarTurno.setEnabled(false);
        jButtonBuscarTurno.setMinimumSize(new java.awt.Dimension(72, 41));
        jButtonBuscarTurno.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonBuscarTurnoActionPerformed(evt);
            }
        });

        jComboBoxTurnosDNI.setEnabled(false);

        jLabelCargarResultadoTurnoCargado.setText("Turno Cargado: ");

        javax.swing.GroupLayout jPanel5Layout = new javax.swing.GroupLayout(jPanel5);
        jPanel5.setLayout(jPanel5Layout);
        jPanel5Layout.setHorizontalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel5Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jSeparator3)
                    .addComponent(jScrollPane2, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 588, Short.MAX_VALUE)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel5Layout.createSequentialGroup()
                        .addGap(0, 0, Short.MAX_VALUE)
                        .addComponent(jButtonGuardarResultado, javax.swing.GroupLayout.PREFERRED_SIZE, 80, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel5Layout.createSequentialGroup()
                        .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                            .addComponent(jLabel3, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, 206, Short.MAX_VALUE)
                            .addComponent(jTextFieldBuscarTurno, javax.swing.GroupLayout.Alignment.LEADING))
                        .addGap(18, 18, 18)
                        .addComponent(jComboBoxTurnosDNI, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addGap(18, 18, 18)
                        .addComponent(jButtonBuscarTurno, javax.swing.GroupLayout.PREFERRED_SIZE, 88, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel5Layout.createSequentialGroup()
                        .addComponent(jLabelCargarResultadoTurnoCargado, javax.swing.GroupLayout.PREFERRED_SIZE, 204, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 0, Short.MAX_VALUE)))
                .addContainerGap())
        );
        jPanel5Layout.setVerticalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel5Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel3, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jTextFieldBuscarTurno, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jButtonBuscarTurno, javax.swing.GroupLayout.DEFAULT_SIZE, 41, Short.MAX_VALUE)
                    .addComponent(jComboBoxTurnosDNI, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jSeparator3, javax.swing.GroupLayout.PREFERRED_SIZE, 10, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabelCargarResultadoTurnoCargado)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 306, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jButtonGuardarResultado, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );

        jPanelGenerarResultado.add(jPanel5);

        jPanelBackground.add(jPanelGenerarResultado, "card3");

        jPanelAgregarPaciente.setBackground(new java.awt.Color(255, 255, 255));
        jPanelAgregarPaciente.setLayout(new javax.swing.BoxLayout(jPanelAgregarPaciente, javax.swing.BoxLayout.LINE_AXIS));

        jPanel6.setBackground(new java.awt.Color(255, 255, 255));

        jTabbedPanelCrearPaciente.setBackground(new java.awt.Color(255, 255, 255));
        jTabbedPanelCrearPaciente.setEnabled(false);

        jPanelAgregarPacienteP1.setBackground(new java.awt.Color(255, 255, 255));
        jPanelAgregarPacienteP1.setMaximumSize(new java.awt.Dimension(588, 38));
        jPanelAgregarPacienteP1.setMinimumSize(new java.awt.Dimension(588, 38));

        jPanel7.setBackground(new java.awt.Color(255, 255, 255));

        jLabel9.setText("DNI:");
        jLabel9.setPreferredSize(new java.awt.Dimension(200, 16));

        jLabel10.setText("Nombre:");
        jLabel10.setPreferredSize(new java.awt.Dimension(200, 16));

        jLabel11.setText("Apellido:");
        jLabel11.setPreferredSize(new java.awt.Dimension(200, 16));

        jLabel12.setText("Edad:");
        jLabel12.setPreferredSize(new java.awt.Dimension(200, 16));

        jTextFieldEdad.setToolTipText("Solo números entre 1 y 120");

        jLabel13.setText("Sexo:");
        jLabel13.setPreferredSize(new java.awt.Dimension(200, 16));

        jRadioButtonMujer.setSelected(true);
        jRadioButtonMujer.setText("Mujer");
        jRadioButtonMujer.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jRadioButtonMujerActionPerformed(evt);
            }
        });

        jRadioButtonHombre.setText("Hombre");
        jRadioButtonHombre.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jRadioButtonHombreActionPerformed(evt);
            }
        });

        jErrorCrearPacienteDni.setFont(new java.awt.Font("Segoe UI", 2, 12)); // NOI18N
        jErrorCrearPacienteDni.setForeground(new java.awt.Color(255, 51, 51));

        jErrorCrearPacienteNombre.setFont(new java.awt.Font("Segoe UI", 2, 12)); // NOI18N
        jErrorCrearPacienteNombre.setForeground(new java.awt.Color(255, 51, 51));

        jErrorCrearPacienteApellido.setFont(new java.awt.Font("Segoe UI", 2, 12)); // NOI18N
        jErrorCrearPacienteApellido.setForeground(new java.awt.Color(255, 51, 51));

        jErrorCrearPacienteEdad.setFont(new java.awt.Font("Segoe UI", 2, 12)); // NOI18N
        jErrorCrearPacienteEdad.setForeground(new java.awt.Color(255, 51, 51));

        jErrorCrearPacienteSexo.setFont(new java.awt.Font("Segoe UI", 2, 12)); // NOI18N
        jErrorCrearPacienteSexo.setForeground(new java.awt.Color(255, 51, 51));

        javax.swing.GroupLayout jPanel7Layout = new javax.swing.GroupLayout(jPanel7);
        jPanel7.setLayout(jPanel7Layout);
        jPanel7Layout.setHorizontalGroup(
            jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel7Layout.createSequentialGroup()
                .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel7Layout.createSequentialGroup()
                        .addContainerGap()
                        .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jTextFieldNombre)
                            .addComponent(jTextFieldApellido)
                            .addComponent(jTextFieldEdad)
                            .addGroup(jPanel7Layout.createSequentialGroup()
                                .addComponent(jLabel10, javax.swing.GroupLayout.PREFERRED_SIZE, 60, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(jErrorCrearPacienteNombre))
                            .addGroup(jPanel7Layout.createSequentialGroup()
                                .addComponent(jLabel11, javax.swing.GroupLayout.PREFERRED_SIZE, 60, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(jErrorCrearPacienteApellido))
                            .addGroup(jPanel7Layout.createSequentialGroup()
                                .addComponent(jLabel12, javax.swing.GroupLayout.PREFERRED_SIZE, 60, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(jErrorCrearPacienteEdad))
                            .addGroup(jPanel7Layout.createSequentialGroup()
                                .addComponent(jTextFieldDni, javax.swing.GroupLayout.PREFERRED_SIZE, 200, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(0, 0, Short.MAX_VALUE))
                            .addGroup(jPanel7Layout.createSequentialGroup()
                                .addComponent(jLabel13, javax.swing.GroupLayout.PREFERRED_SIZE, 60, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(jErrorCrearPacienteSexo))
                            .addGroup(jPanel7Layout.createSequentialGroup()
                                .addComponent(jRadioButtonMujer)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(jRadioButtonHombre)
                                .addGap(23, 23, 23))))
                    .addGroup(jPanel7Layout.createSequentialGroup()
                        .addGap(5, 5, 5)
                        .addComponent(jLabel9, javax.swing.GroupLayout.PREFERRED_SIZE, 60, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jErrorCrearPacienteDni)))
                .addContainerGap())
        );
        jPanel7Layout.setVerticalGroup(
            jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel7Layout.createSequentialGroup()
                .addGap(5, 5, 5)
                .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel9, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jErrorCrearPacienteDni))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jTextFieldDni, javax.swing.GroupLayout.PREFERRED_SIZE, 38, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel10, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jErrorCrearPacienteNombre))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jTextFieldNombre, javax.swing.GroupLayout.PREFERRED_SIZE, 38, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel11, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jErrorCrearPacienteApellido))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jTextFieldApellido, javax.swing.GroupLayout.PREFERRED_SIZE, 38, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel12, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jErrorCrearPacienteEdad))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jTextFieldEdad, javax.swing.GroupLayout.PREFERRED_SIZE, 38, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel13, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jErrorCrearPacienteSexo))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jRadioButtonMujer)
                    .addComponent(jRadioButtonHombre))
                .addGap(51, 51, 51))
        );

        javax.swing.GroupLayout jPanelAgregarPacienteP1Layout = new javax.swing.GroupLayout(jPanelAgregarPacienteP1);
        jPanelAgregarPacienteP1.setLayout(jPanelAgregarPacienteP1Layout);
        jPanelAgregarPacienteP1Layout.setHorizontalGroup(
            jPanelAgregarPacienteP1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanelAgregarPacienteP1Layout.createSequentialGroup()
                .addGap(186, 186, 186)
                .addComponent(jPanel7, javax.swing.GroupLayout.PREFERRED_SIZE, 211, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(191, Short.MAX_VALUE))
        );
        jPanelAgregarPacienteP1Layout.setVerticalGroup(
            jPanelAgregarPacienteP1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanelAgregarPacienteP1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel7, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );

        jTabbedPanelCrearPaciente.addTab("Paso 1: Datos Personales", jPanelAgregarPacienteP1);

        jPanelAgregarPacienteP2.setBackground(new java.awt.Color(255, 255, 255));

        jPanel8.setBackground(new java.awt.Color(255, 255, 255));

        jLabel14.setText("Domicilio:");
        jLabel14.setPreferredSize(new java.awt.Dimension(200, 16));

        jTextFieldDomicilio.setToolTipText("Formato: <Nombre de Calle> <Número>");

        jLabel15.setText("Telefono:");
        jLabel15.setPreferredSize(new java.awt.Dimension(200, 16));

        jLabel16.setText("Correo Electronico:");
        jLabel16.setPreferredSize(new java.awt.Dimension(200, 16));

        jErrorCrearPacienteDomicilio.setFont(new java.awt.Font("Segoe UI", 2, 12)); // NOI18N
        jErrorCrearPacienteDomicilio.setForeground(new java.awt.Color(255, 51, 51));

        jErrorCrearPacienteTelefono.setFont(new java.awt.Font("Segoe UI", 2, 12)); // NOI18N
        jErrorCrearPacienteTelefono.setForeground(new java.awt.Color(255, 51, 51));

        jErrorCrearPacienteCorreo.setFont(new java.awt.Font("Segoe UI", 2, 12)); // NOI18N
        jErrorCrearPacienteCorreo.setForeground(new java.awt.Color(255, 51, 51));

        javax.swing.GroupLayout jPanel8Layout = new javax.swing.GroupLayout(jPanel8);
        jPanel8.setLayout(jPanel8Layout);
        jPanel8Layout.setHorizontalGroup(
            jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel8Layout.createSequentialGroup()
                .addGroup(jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel8Layout.createSequentialGroup()
                        .addGap(5, 5, 5)
                        .addComponent(jLabel14, javax.swing.GroupLayout.PREFERRED_SIZE, 105, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jErrorCrearPacienteDomicilio)
                        .addGap(11, 11, 11))
                    .addGroup(jPanel8Layout.createSequentialGroup()
                        .addContainerGap()
                        .addGroup(jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jTextFieldDomicilio)
                            .addComponent(jTextFieldTelefono)
                            .addComponent(jTextFieldCorreo)
                            .addGroup(jPanel8Layout.createSequentialGroup()
                                .addComponent(jLabel16, javax.swing.GroupLayout.PREFERRED_SIZE, 105, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(jErrorCrearPacienteCorreo)
                                .addGap(12, 12, 12))
                            .addGroup(jPanel8Layout.createSequentialGroup()
                                .addComponent(jLabel15, javax.swing.GroupLayout.PREFERRED_SIZE, 105, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 37, Short.MAX_VALUE)
                                .addComponent(jErrorCrearPacienteTelefono, javax.swing.GroupLayout.PREFERRED_SIZE, 111, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(25, 25, 25)))))
                .addContainerGap())
        );
        jPanel8Layout.setVerticalGroup(
            jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel8Layout.createSequentialGroup()
                .addGap(5, 5, 5)
                .addGroup(jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel14, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jErrorCrearPacienteDomicilio))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jTextFieldDomicilio, javax.swing.GroupLayout.PREFERRED_SIZE, 38, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel15, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jErrorCrearPacienteTelefono))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jTextFieldTelefono, javax.swing.GroupLayout.PREFERRED_SIZE, 38, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel16, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jErrorCrearPacienteCorreo))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jTextFieldCorreo, javax.swing.GroupLayout.PREFERRED_SIZE, 38, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(178, 178, 178))
        );

        javax.swing.GroupLayout jPanelAgregarPacienteP2Layout = new javax.swing.GroupLayout(jPanelAgregarPacienteP2);
        jPanelAgregarPacienteP2.setLayout(jPanelAgregarPacienteP2Layout);
        jPanelAgregarPacienteP2Layout.setHorizontalGroup(
            jPanelAgregarPacienteP2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanelAgregarPacienteP2Layout.createSequentialGroup()
                .addContainerGap(134, Short.MAX_VALUE)
                .addComponent(jPanel8, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(164, Short.MAX_VALUE))
        );
        jPanelAgregarPacienteP2Layout.setVerticalGroup(
            jPanelAgregarPacienteP2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanelAgregarPacienteP2Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel8, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );

        jTabbedPanelCrearPaciente.addTab("Paso 2: Datos de Contacto", jPanelAgregarPacienteP2);

        jPanelAgregarPacienteP3.setBackground(new java.awt.Color(255, 255, 255));

        jLabel8.setText("Obra Social:");

        jComboBoxObraSocialCrearPaciente.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));

        jButton6.setText("Agregar");
        jButton6.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton6ActionPerformed(evt);
            }
        });

        jTableObrasSocialesAgregarPaciente.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null},
                {null},
                {null},
                {null}
            },
            new String [] {
                "Obra Social"
            }
        ));
        jTableObrasSocialesAgregarPaciente.setEnabled(false);
        jScrollPane3.setViewportView(jTableObrasSocialesAgregarPaciente);

        jErrorCrearPacienteObraSocial.setFont(new java.awt.Font("Segoe UI", 2, 12)); // NOI18N
        jErrorCrearPacienteObraSocial.setForeground(new java.awt.Color(255, 51, 51));

        jButton2.setText("Eliminar");
        jButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton2ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanelAgregarPacienteP3Layout = new javax.swing.GroupLayout(jPanelAgregarPacienteP3);
        jPanelAgregarPacienteP3.setLayout(jPanelAgregarPacienteP3Layout);
        jPanelAgregarPacienteP3Layout.setHorizontalGroup(
            jPanelAgregarPacienteP3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanelAgregarPacienteP3Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanelAgregarPacienteP3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane3, javax.swing.GroupLayout.DEFAULT_SIZE, 576, Short.MAX_VALUE)
                    .addGroup(jPanelAgregarPacienteP3Layout.createSequentialGroup()
                        .addGroup(jPanelAgregarPacienteP3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addGroup(jPanelAgregarPacienteP3Layout.createSequentialGroup()
                                .addComponent(jLabel8)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(jErrorCrearPacienteObraSocial))
                            .addComponent(jComboBoxObraSocialCrearPaciente, javax.swing.GroupLayout.PREFERRED_SIZE, 170, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(18, 18, 18)
                        .addComponent(jButton6)
                        .addGap(18, 18, 18)
                        .addComponent(jButton2)
                        .addGap(0, 0, Short.MAX_VALUE)))
                .addContainerGap())
        );
        jPanelAgregarPacienteP3Layout.setVerticalGroup(
            jPanelAgregarPacienteP3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanelAgregarPacienteP3Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanelAgregarPacienteP3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel8)
                    .addComponent(jErrorCrearPacienteObraSocial))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanelAgregarPacienteP3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanelAgregarPacienteP3Layout.createSequentialGroup()
                        .addComponent(jButton6, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addComponent(jComboBoxObraSocialCrearPaciente)
                    .addComponent(jButton2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addGap(18, 18, 18)
                .addComponent(jScrollPane3, javax.swing.GroupLayout.DEFAULT_SIZE, 295, Short.MAX_VALUE)
                .addContainerGap())
        );

        jTabbedPanelCrearPaciente.addTab("Paso 3: Obra Social", jPanelAgregarPacienteP3);

        jButtonCrearPacienteSiguiente.setText("Siguiente");
        jButtonCrearPacienteSiguiente.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonCrearPacienteSiguienteActionPerformed(evt);
            }
        });

        jButtonCrearPacienteAnterior.setText("Anterior");
        jButtonCrearPacienteAnterior.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonCrearPacienteAnteriorActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel6Layout = new javax.swing.GroupLayout(jPanel6);
        jPanel6.setLayout(jPanel6Layout);
        jPanel6Layout.setHorizontalGroup(
            jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel6Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jTabbedPanelCrearPaciente)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel6Layout.createSequentialGroup()
                        .addGap(0, 0, Short.MAX_VALUE)
                        .addComponent(jButtonCrearPacienteAnterior, javax.swing.GroupLayout.PREFERRED_SIZE, 84, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(jButtonCrearPacienteSiguiente, javax.swing.GroupLayout.PREFERRED_SIZE, 84, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap())
        );
        jPanel6Layout.setVerticalGroup(
            jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel6Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jTabbedPanelCrearPaciente, javax.swing.GroupLayout.PREFERRED_SIZE, 422, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jButtonCrearPacienteAnterior, javax.swing.GroupLayout.DEFAULT_SIZE, 40, Short.MAX_VALUE)
                    .addComponent(jButtonCrearPacienteSiguiente, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );

        jPanelAgregarPaciente.add(jPanel6);

        jPanelBackground.add(jPanelAgregarPaciente, "card4");

        jLabel17.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        jLabel17.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel17.setText("Menu en Desarrollo");
        jLabel17.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);

        javax.swing.GroupLayout jPanelEnConstruccionLayout = new javax.swing.GroupLayout(jPanelEnConstruccion);
        jPanelEnConstruccion.setLayout(jPanelEnConstruccionLayout);
        jPanelEnConstruccionLayout.setHorizontalGroup(
            jPanelEnConstruccionLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanelEnConstruccionLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel17, javax.swing.GroupLayout.DEFAULT_SIZE, 588, Short.MAX_VALUE)
                .addContainerGap())
        );
        jPanelEnConstruccionLayout.setVerticalGroup(
            jPanelEnConstruccionLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanelEnConstruccionLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel17, javax.swing.GroupLayout.DEFAULT_SIZE, 468, Short.MAX_VALUE)
                .addContainerGap())
        );

        jPanelBackground.add(jPanelEnConstruccion, "card5");

        jPanelAgregarAnalisis.setBackground(new java.awt.Color(255, 255, 255));
        jPanelAgregarAnalisis.setLayout(new javax.swing.BoxLayout(jPanelAgregarAnalisis, javax.swing.BoxLayout.LINE_AXIS));

        jPanel10.setBackground(new java.awt.Color(255, 255, 255));

        jTabbedPanelAgregarAnalisis.setBackground(new java.awt.Color(255, 255, 255));
        jTabbedPanelAgregarAnalisis.setEnabled(false);

        jPanelAgregarAnalisisDatosBase.setBackground(new java.awt.Color(255, 255, 255));

        jPanel11.setBackground(new java.awt.Color(255, 255, 255));

        jLabel21.setText("Nombre:");

        jTextFieldCrearAnalisisValorReferencia.setToolTipText("Formato: [<numero> a <numero> <unidad/unidad>]");

        jLabel22.setText("Valor Referencia:");

        jLabel23.setText("Metodo Usado:");

        jLabel24.setText("Monto:");

        javax.swing.GroupLayout jPanel11Layout = new javax.swing.GroupLayout(jPanel11);
        jPanel11.setLayout(jPanel11Layout);
        jPanel11Layout.setHorizontalGroup(
            jPanel11Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel11Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel11Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel21)
                    .addComponent(jLabel22)
                    .addComponent(jTextFieldCrearAnalisisValorReferencia, javax.swing.GroupLayout.PREFERRED_SIZE, 200, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel24)
                    .addComponent(jTextFieldCrearAnalisisMonto, javax.swing.GroupLayout.PREFERRED_SIZE, 200, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel23)
                    .addComponent(jTextFieldCrearAnalisisMetodoUsado, javax.swing.GroupLayout.PREFERRED_SIZE, 200, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jTextFieldCrearAnalisisNombre, javax.swing.GroupLayout.PREFERRED_SIZE, 200, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel11Layout.setVerticalGroup(
            jPanel11Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel11Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel21)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jTextFieldCrearAnalisisNombre, javax.swing.GroupLayout.PREFERRED_SIZE, 34, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel22)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jTextFieldCrearAnalisisValorReferencia, javax.swing.GroupLayout.PREFERRED_SIZE, 34, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel24)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jTextFieldCrearAnalisisMonto, javax.swing.GroupLayout.PREFERRED_SIZE, 34, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel23)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jTextFieldCrearAnalisisMetodoUsado, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout jPanelAgregarAnalisisDatosBaseLayout = new javax.swing.GroupLayout(jPanelAgregarAnalisisDatosBase);
        jPanelAgregarAnalisisDatosBase.setLayout(jPanelAgregarAnalisisDatosBaseLayout);
        jPanelAgregarAnalisisDatosBaseLayout.setHorizontalGroup(
            jPanelAgregarAnalisisDatosBaseLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanelAgregarAnalisisDatosBaseLayout.createSequentialGroup()
                .addContainerGap(190, Short.MAX_VALUE)
                .addComponent(jPanel11, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(186, 186, 186))
        );
        jPanelAgregarAnalisisDatosBaseLayout.setVerticalGroup(
            jPanelAgregarAnalisisDatosBaseLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanelAgregarAnalisisDatosBaseLayout.createSequentialGroup()
                .addGap(32, 32, 32)
                .addComponent(jPanel11, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(33, Short.MAX_VALUE))
        );

        jTabbedPanelAgregarAnalisis.addTab("Paso 1: Datos", jPanelAgregarAnalisisDatosBase);

        jPanelAgregarPacienteP4.setBackground(new java.awt.Color(255, 255, 255));

        jLabel25.setText("Reactivos:");

        jButtonAgregarAnalisisAgregarReactivo.setText("Agregar");
        jButtonAgregarAnalisisAgregarReactivo.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonAgregarAnalisisAgregarReactivoActionPerformed(evt);
            }
        });

        jTableAgregarPacienteReactivos.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null},
                {null},
                {null},
                {null}
            },
            new String [] {
                "Reactivos"
            }
        ));
        jScrollPane5.setViewportView(jTableAgregarPacienteReactivos);

        jErrorCrearPacienteObraSocial1.setFont(new java.awt.Font("Segoe UI", 2, 12)); // NOI18N
        jErrorCrearPacienteObraSocial1.setForeground(new java.awt.Color(255, 51, 51));

        jButtonAgregarAnalisisEliminarReactivo.setText("Eliminar");
        jButtonAgregarAnalisisEliminarReactivo.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonAgregarAnalisisEliminarReactivoActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanelAgregarPacienteP4Layout = new javax.swing.GroupLayout(jPanelAgregarPacienteP4);
        jPanelAgregarPacienteP4.setLayout(jPanelAgregarPacienteP4Layout);
        jPanelAgregarPacienteP4Layout.setHorizontalGroup(
            jPanelAgregarPacienteP4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanelAgregarPacienteP4Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanelAgregarPacienteP4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane5, javax.swing.GroupLayout.DEFAULT_SIZE, 576, Short.MAX_VALUE)
                    .addGroup(jPanelAgregarPacienteP4Layout.createSequentialGroup()
                        .addGroup(jPanelAgregarPacienteP4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addGroup(jPanelAgregarPacienteP4Layout.createSequentialGroup()
                                .addComponent(jLabel25)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(jErrorCrearPacienteObraSocial1))
                            .addComponent(jComboBoxCrearAnalisisReactivos, javax.swing.GroupLayout.PREFERRED_SIZE, 170, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(18, 18, 18)
                        .addComponent(jButtonAgregarAnalisisAgregarReactivo)
                        .addGap(18, 18, 18)
                        .addComponent(jButtonAgregarAnalisisEliminarReactivo)
                        .addGap(0, 0, Short.MAX_VALUE)))
                .addContainerGap())
        );
        jPanelAgregarPacienteP4Layout.setVerticalGroup(
            jPanelAgregarPacienteP4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanelAgregarPacienteP4Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanelAgregarPacienteP4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel25)
                    .addComponent(jErrorCrearPacienteObraSocial1))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanelAgregarPacienteP4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jButtonAgregarAnalisisEliminarReactivo, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jComboBoxCrearAnalisisReactivos)
                    .addComponent(jButtonAgregarAnalisisAgregarReactivo, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addGap(18, 18, 18)
                .addComponent(jScrollPane5, javax.swing.GroupLayout.DEFAULT_SIZE, 293, Short.MAX_VALUE)
                .addContainerGap())
        );

        jTabbedPanelAgregarAnalisis.addTab("Paso 2: Reactivos", jPanelAgregarPacienteP4);

        jButtonCrearAnalisisSiguiente.setText("Siguiente");
        jButtonCrearAnalisisSiguiente.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonCrearAnalisisSiguienteActionPerformed(evt);
            }
        });

        jButtonCrearAnalisisAnterior.setText("Anterior");
        jButtonCrearAnalisisAnterior.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonCrearAnalisisAnteriorActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel10Layout = new javax.swing.GroupLayout(jPanel10);
        jPanel10.setLayout(jPanel10Layout);
        jPanel10Layout.setHorizontalGroup(
            jPanel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel10Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(jPanel10Layout.createSequentialGroup()
                        .addGap(0, 0, Short.MAX_VALUE)
                        .addComponent(jButtonCrearAnalisisAnterior, javax.swing.GroupLayout.PREFERRED_SIZE, 90, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(jButtonCrearAnalisisSiguiente, javax.swing.GroupLayout.PREFERRED_SIZE, 90, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(jTabbedPanelAgregarAnalisis))
                .addContainerGap())
        );
        jPanel10Layout.setVerticalGroup(
            jPanel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel10Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jTabbedPanelAgregarAnalisis, javax.swing.GroupLayout.PREFERRED_SIZE, 420, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jButtonCrearAnalisisAnterior, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jButtonCrearAnalisisSiguiente, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap())
        );

        jPanelAgregarAnalisis.add(jPanel10);

        jPanelBackground.add(jPanelAgregarAnalisis, "card6");

        jPanelConsultarAnalisis.setBackground(new java.awt.Color(255, 255, 255));
        jPanelConsultarAnalisis.setLayout(new javax.swing.BoxLayout(jPanelConsultarAnalisis, javax.swing.BoxLayout.LINE_AXIS));

        crearTurnoP5.setBackground(new java.awt.Color(255, 255, 255));

        jPanel13.setBackground(new java.awt.Color(255, 255, 255));

        jButtonBuscarAnalisis.setText("Buscar");
        jButtonBuscarAnalisis.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonBuscarAnalisisActionPerformed(evt);
            }
        });

        jLabel26.setText("Filtrar por Nombre de Analisis:");

        jScrollPane6.setEnabled(false);

        jTableBuscarAnalisisReactivos.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null},
                {null, null, null},
                {null, null, null},
                {null, null, null}
            },
            new String [] {
                "Nombre", "Capacidad", "Cantidad a usar"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.String.class, java.lang.Float.class, java.lang.Float.class
            };

            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }
        });
        jTableBuscarAnalisisReactivos.setEnabled(false);
        jScrollPane6.setViewportView(jTableBuscarAnalisisReactivos);

        jTextFieldNombreConsultarAnalisis.setToolTipText("Pulsa Enter para filtrar");
        jTextFieldNombreConsultarAnalisis.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusLost(java.awt.event.FocusEvent evt) {
                jTextFieldNombreConsultarAnalisisFocusLost(evt);
            }
        });
        jTextFieldNombreConsultarAnalisis.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jTextFieldNombreConsultarAnalisisActionPerformed(evt);
            }
        });

        jLabelBuscarAnalisisValorReferencia.setText("Valor Referencia:");

        jLabelBuscarAnalisisMonto.setText("Monto: ");

        jLabelBuscarAnalisisMetodoUsado.setText("Metodo Usado:");

        javax.swing.GroupLayout jPanel12Layout = new javax.swing.GroupLayout(jPanel12);
        jPanel12.setLayout(jPanel12Layout);
        jPanel12Layout.setHorizontalGroup(
            jPanel12Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel12Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel12Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabelBuscarAnalisisValorReferencia, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jLabelBuscarAnalisisMonto, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jLabelBuscarAnalisisMetodoUsado, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );
        jPanel12Layout.setVerticalGroup(
            jPanel12Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel12Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabelBuscarAnalisisValorReferencia)
                .addGap(18, 18, 18)
                .addComponent(jLabelBuscarAnalisisMonto)
                .addGap(18, 18, 18)
                .addComponent(jLabelBuscarAnalisisMetodoUsado)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jLabel35.setText("Reactivos Usados:");

        javax.swing.GroupLayout jPanel13Layout = new javax.swing.GroupLayout(jPanel13);
        jPanel13.setLayout(jPanel13Layout);
        jPanel13Layout.setHorizontalGroup(
            jPanel13Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel13Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel13Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jPanel12, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jScrollPane6, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 576, Short.MAX_VALUE)
                    .addComponent(jLabel35, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(jPanel13Layout.createSequentialGroup()
                        .addComponent(jLabel26)
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addGroup(jPanel13Layout.createSequentialGroup()
                        .addComponent(jTextFieldNombreConsultarAnalisis)
                        .addGap(18, 18, 18)
                        .addComponent(jComboBoxConsultarAnalisis, javax.swing.GroupLayout.PREFERRED_SIZE, 253, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(jButtonBuscarAnalisis)))
                .addContainerGap())
        );
        jPanel13Layout.setVerticalGroup(
            jPanel13Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel13Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel26)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel13Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jTextFieldNombreConsultarAnalisis, javax.swing.GroupLayout.PREFERRED_SIZE, 39, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jButtonBuscarAnalisis, javax.swing.GroupLayout.DEFAULT_SIZE, 40, Short.MAX_VALUE)
                    .addComponent(jComboBoxConsultarAnalisis, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addComponent(jPanel12, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(jLabel35)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane6, javax.swing.GroupLayout.DEFAULT_SIZE, 239, Short.MAX_VALUE)
                .addContainerGap())
        );

        javax.swing.GroupLayout crearTurnoP5Layout = new javax.swing.GroupLayout(crearTurnoP5);
        crearTurnoP5.setLayout(crearTurnoP5Layout);
        crearTurnoP5Layout.setHorizontalGroup(
            crearTurnoP5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(crearTurnoP5Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel13, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );
        crearTurnoP5Layout.setVerticalGroup(
            crearTurnoP5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(crearTurnoP5Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel13, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );

        jPanelConsultarAnalisis.add(crearTurnoP5);

        jPanelBackground.add(jPanelConsultarAnalisis, "card7");

        jPanelCancelarTurno.setBackground(new java.awt.Color(255, 255, 255));
        jPanelCancelarTurno.setLayout(new javax.swing.BoxLayout(jPanelCancelarTurno, javax.swing.BoxLayout.LINE_AXIS));

        jPanel14.setBackground(new java.awt.Color(255, 255, 255));

        jCalendarCancelarTurno.setBackground(new java.awt.Color(255, 255, 255));
        jCalendarCancelarTurno.addPropertyChangeListener(new java.beans.PropertyChangeListener() {
            public void propertyChange(java.beans.PropertyChangeEvent evt) {
                jCalendarCancelarTurnoPropertyChange(evt);
            }
        });

        jComboBoxCancelarTurno.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "DNI: 43123871 Nombre: Julian, Jeremias A.", "DNI: 43123871 Nombre: Julian, Jeremias A.", "DNI: 43123871 Nombre: Julian, Jeremias A.", "DNI: 43123871 Nombre: Julian, Jeremias A.", "DNI: 43123871 Nombre: Julian, Jeremias A." }));

        jButton5.setText("Cancelar");
        jButton5.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton5ActionPerformed(evt);
            }
        });

        jTextFieldDniCancelarTurno.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jTextFieldDniCancelarTurnoActionPerformed(evt);
            }
        });

        jLabel32.setText("Filtrar por DNI de Paciente:");

        jLabel33.setText("Turno a Eliminar:");

        jLabel34.setText("Buscar por Fecha:");

        javax.swing.GroupLayout jPanel14Layout = new javax.swing.GroupLayout(jPanel14);
        jPanel14.setLayout(jPanel14Layout);
        jPanel14Layout.setHorizontalGroup(
            jPanel14Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel14Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel14Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jCalendarCancelarTurno, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 588, Short.MAX_VALUE)
                    .addGroup(jPanel14Layout.createSequentialGroup()
                        .addComponent(jTextFieldDniCancelarTurno, javax.swing.GroupLayout.PREFERRED_SIZE, 140, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(jComboBoxCancelarTurno, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel14Layout.createSequentialGroup()
                        .addGap(0, 0, Short.MAX_VALUE)
                        .addComponent(jButton5, javax.swing.GroupLayout.PREFERRED_SIZE, 90, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel14Layout.createSequentialGroup()
                        .addGroup(jPanel14Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel14Layout.createSequentialGroup()
                                .addComponent(jLabel32)
                                .addGap(18, 18, 18)
                                .addComponent(jLabel33))
                            .addComponent(jLabel34, javax.swing.GroupLayout.PREFERRED_SIZE, 129, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(0, 0, Short.MAX_VALUE)))
                .addContainerGap())
        );
        jPanel14Layout.setVerticalGroup(
            jPanel14Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel14Layout.createSequentialGroup()
                .addGap(19, 19, 19)
                .addComponent(jLabel34)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jCalendarCancelarTurno, javax.swing.GroupLayout.PREFERRED_SIZE, 284, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel14Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel32)
                    .addComponent(jLabel33))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 10, Short.MAX_VALUE)
                .addGroup(jPanel14Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jTextFieldDniCancelarTurno, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jComboBoxCancelarTurno, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(31, 31, 31)
                .addComponent(jButton5, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );

        jPanelCancelarTurno.add(jPanel14);

        jPanelBackground.add(jPanelCancelarTurno, "card8");

        jPanelConsultarStock.setLayout(new javax.swing.BoxLayout(jPanelConsultarStock, javax.swing.BoxLayout.LINE_AXIS));

        jPanel16.setBackground(new java.awt.Color(255, 255, 255));

        jButton10.setText("Pedir Nuevos Reactivos");
        jButton10.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton10ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel16Layout = new javax.swing.GroupLayout(jPanel16);
        jPanel16.setLayout(jPanel16Layout);
        jPanel16Layout.setHorizontalGroup(
            jPanel16Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel16Layout.createSequentialGroup()
                .addContainerGap(441, Short.MAX_VALUE)
                .addComponent(jButton10)
                .addContainerGap())
        );
        jPanel16Layout.setVerticalGroup(
            jPanel16Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel16Layout.createSequentialGroup()
                .addContainerGap(434, Short.MAX_VALUE)
                .addComponent(jButton10, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );

        jPanelConsultarStock.add(jPanel16);

        jPanelBackground.add(jPanelConsultarStock, "card9");

        jPanelPedirReactivos.setLayout(new javax.swing.BoxLayout(jPanelPedirReactivos, javax.swing.BoxLayout.LINE_AXIS));

        jPanel15.setBackground(new java.awt.Color(255, 255, 255));

        jButtonAgregarAnalisisATurno2.setText("Agregar");
        jButtonAgregarAnalisisATurno2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonAgregarAnalisisATurno2ActionPerformed(evt);
            }
        });

        jLabel30.setText("Reactivo:");

        jComboCrearTurnoAnalisis1.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));

        jTableAnalisisAgregarTurno2.setModel(new javax.swing.table.DefaultTableModel(
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
        jTableAnalisisAgregarTurno2.setEnabled(false);
        jScrollPane7.setViewportView(jTableAnalisisAgregarTurno2);

        jLabel31.setText("Cantidad:");

        jComboCrearTurnoAnalisis2.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "10", "20", "50", "100", "500", "1000", "2500", "5000" }));

        jButton8.setText("Aceptar");

        jButton9.setText("Volver a Stock");
        jButton9.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton9ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel15Layout = new javax.swing.GroupLayout(jPanel15);
        jPanel15.setLayout(jPanel15Layout);
        jPanel15Layout.setHorizontalGroup(
            jPanel15Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel15Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel15Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane7, javax.swing.GroupLayout.DEFAULT_SIZE, 594, Short.MAX_VALUE)
                    .addGroup(jPanel15Layout.createSequentialGroup()
                        .addGroup(jPanel15Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel15Layout.createSequentialGroup()
                                .addGroup(jPanel15Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jComboCrearTurnoAnalisis1, javax.swing.GroupLayout.PREFERRED_SIZE, 164, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jLabel30))
                                .addGap(18, 18, 18)
                                .addGroup(jPanel15Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(jPanel15Layout.createSequentialGroup()
                                        .addComponent(jComboCrearTurnoAnalisis2, javax.swing.GroupLayout.PREFERRED_SIZE, 164, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addGap(18, 18, 18)
                                        .addComponent(jButtonAgregarAnalisisATurno2, javax.swing.GroupLayout.PREFERRED_SIZE, 81, javax.swing.GroupLayout.PREFERRED_SIZE))
                                    .addComponent(jLabel31))
                                .addGap(0, 0, Short.MAX_VALUE))
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel15Layout.createSequentialGroup()
                                .addComponent(jButton9)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(jButton8)))
                        .addContainerGap())))
        );
        jPanel15Layout.setVerticalGroup(
            jPanel15Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel15Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel15Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel30)
                    .addComponent(jLabel31))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel15Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jButtonAgregarAnalisisATurno2, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(jPanel15Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(jComboCrearTurnoAnalisis1, javax.swing.GroupLayout.PREFERRED_SIZE, 39, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(jComboCrearTurnoAnalisis2, javax.swing.GroupLayout.PREFERRED_SIZE, 39, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(18, 18, 18)
                .addComponent(jScrollPane7, javax.swing.GroupLayout.PREFERRED_SIZE, 331, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addGroup(jPanel15Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jButton8, javax.swing.GroupLayout.DEFAULT_SIZE, 40, Short.MAX_VALUE)
                    .addComponent(jButton9, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );

        jPanelPedirReactivos.add(jPanel15);

        jPanelBackground.add(jPanelPedirReactivos, "card10");

        getContentPane().add(jPanelBackground);

        pack();
    }// </editor-fold>//GEN-END:initComponents

    // Cambia entre las posibles ventanas del programa
    private void cambiarPanel(JPanel panel){
        JPanel[] panelesDisponibles = {jPanelGenerarResultado,jPanelAgregarPaciente,jPanelDarTurno,jPanelEnConstruccion,jPanelAgregarAnalisis,jPanelConsultarAnalisis,jPanelCancelarTurno,jPanelConsultarStock,jPanelPedirReactivos};
        for(JPanel p : panelesDisponibles){
            p.setVisible(false);
        }
        panel.setVisible(true);
        
    }
    
    // Cambia el color del boton seleccionado del panel lateral a uno mas oscuro
    private void setHighlighted(JButton button){
        JButton[] panelesDisponibles = {jButtonLateralDarTurno,jButtonLateralGenerarResultado,jButtonLateralAgregarPaciente,jButtonLateralAgregarAnalisis,jButtonLateralBuscarAnalisis,jButtonLateralCancelarTurno,jButtonLateralConsultarStockReactivo};
        for(JButton b : panelesDisponibles){
            b.setBackground(new Color(102,153,255)); // Color Default
        }
        button.setBackground(new Color(90,140,240));
        
    }
    
    
    private void jButtonLateralDarTurnoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonLateralDarTurnoActionPerformed
        setHighlighted(jButtonLateralDarTurno);
        cambiarPanel(jPanelDarTurno);
    }//GEN-LAST:event_jButtonLateralDarTurnoActionPerformed

    private void jButtonLateralGenerarResultadoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonLateralGenerarResultadoActionPerformed
        setHighlighted(jButtonLateralGenerarResultado);
        cambiarPanel(jPanelGenerarResultado);
    }//GEN-LAST:event_jButtonLateralGenerarResultadoActionPerformed

    private void jButtonLateralAgregarPacienteActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonLateralAgregarPacienteActionPerformed
        setHighlighted(jButtonLateralAgregarPaciente);
        cambiarPanel(jPanelAgregarPaciente);
    }//GEN-LAST:event_jButtonLateralAgregarPacienteActionPerformed

    private void jButtonLateralAgregarAnalisisActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonLateralAgregarAnalisisActionPerformed
        setHighlighted(jButtonLateralAgregarAnalisis);
        cambiarPanel(jPanelAgregarAnalisis);
    }//GEN-LAST:event_jButtonLateralAgregarAnalisisActionPerformed

    private void jButtonLateralBuscarAnalisisActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonLateralBuscarAnalisisActionPerformed
        setHighlighted(jButtonLateralBuscarAnalisis);
        cambiarPanel(jPanelConsultarAnalisis);
    }//GEN-LAST:event_jButtonLateralBuscarAnalisisActionPerformed

    private void jButtonCrearTurnoAnteriorActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonCrearTurnoAnteriorActionPerformed
        // Vuelve un paso en la etapa de crear turno
        if (crearTurnoActualStep > 0) {
            crearTurnoActualStep--;
            crearTurnoPasosJTabbed.setSelectedIndex(crearTurnoActualStep);
            jButtonCrearTurnoSiguiente.setText("Siguiente");
            if (crearTurnoActualStep == 0){
                jButtonCrearTurnoAnterior.setVisible(false);
            }
        }
        
//        System.out.println(crearTurnoActualStep);
        
        
    }//GEN-LAST:event_jButtonCrearTurnoAnteriorActionPerformed
    
    // Reinicia el formulario de Crear Turno
    private void vaciarCamposCrearTurno(){
        // Limpiar Campos
        // Pag 1
        jTextFieldDniPaciente.setText("");
        jTextFieldDniPacienteMedico.setText("");
        jTextFieldDniPacienteDiagnostico.setText("");
        jComboBoxObraSocialTurno.setEnabled(false);
        jCheckBoxParticular.setSelected(false);
        jCheckBoxParticular.setEnabled(false);
        

        // Pag 3
        analisisElegidos.clear();
        dataModelanalisisElegidos.getDataVector().clear();

        // Resetear tabla
        crearTurnoActualStep = 0;
        crearTurnoPasosJTabbed.setSelectedIndex(crearTurnoActualStep);
    }
    
    
    private void jButtonCrearTurnoSiguienteActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonCrearTurnoSiguienteActionPerformed
        // Comportamiento boton siguiente Crear Turno
        boolean avance = false;
        System.out.println("Pagina actual: " + crearTurnoActualStep);
        
        switch(crearTurnoActualStep){
            case 0:
                avance = ManagerPaciente.validarDni(jTextFieldDniPaciente.getText()) && ManagerPaciente.comprobarExistencia(Integer.parseInt(jTextFieldDniPaciente.getText()))
                            && ManagerTurno.validarSoloTexto(jTextFieldDniPacienteMedico.getText())
                            && ManagerTurno.validarSoloTexto(jTextFieldDniPacienteDiagnostico.getText());
                if (!avance) {
                    // Configurar Mensaje de error
                    String msjError = "ERROR EN LOS SIGUIENTES CAMPOS:";
                    if (!ManagerPaciente.validarDni(jTextFieldDniPaciente.getText()) || !ManagerPaciente.comprobarExistencia(Integer.parseInt(jTextFieldDniPaciente.getText())))
                        msjError += "\n-DNI";
                    if (!ManagerTurno.validarSoloTexto(jTextFieldDniPacienteMedico.getText()))
                        msjError += "\n-Nombre Medico";
                    if (!ManagerTurno.validarSoloTexto(jTextFieldDniPacienteDiagnostico.getText()))
                        msjError += "\n-Diagnostico";
                    JOptionPane.showMessageDialog(null,msjError);

                    return;
                }

                crearTurnoActualStep++;
                jButtonCrearTurnoAnterior.setVisible(true);
                break;
            case 1:
                avance = (turnosDisponibles > 0);
                if (!avance) {
                    // Configurar Mensaje de error
                    String msjError = "ERROR: NO QUEDAN MAS TURNOS DISPONIBLES";
                    JOptionPane.showMessageDialog(null,msjError);
                    return;
                }

                crearTurnoActualStep++;
                break;
            case 2:
                avance = !analisisElegidos.isEmpty();
                System.out.println(analisisElegidos);
                if (!avance) {
                    // Configurar Mensaje de error
                    String msjError = "ERROR: INGRESE AL MENOS UN ANALISIS";
                    JOptionPane.showMessageDialog(null,msjError);
                    return;
                }

                crearTurnoActualStep++;
                
                // Preparar pestaña de confirmación
                // PACIENTE
                Paciente p = ManagerPaciente.obtenerPaciente(jTextFieldDniPaciente.getText());
                jLabelConfirmacionDni.setText("DNI: " + p.getDni());
                jLabelConfirmacionObraSocial.setText("Obra Social: " + (String) jComboBoxObraSocialTurno.getSelectedItem());
                if (jCheckBoxParticular.isSelected()){
                    jLabelConfirmacionObraSocial.setText("Obra Social: PARTICULAR");
                }
                
                jLabelConfirmacionNombre.setText("Nombre: " + p.getNombre());
                jLabelConfirmacionApellido.setText("Apellido: " + p.getApellido());
                
                // FECHA
                int dia = jCalendarPedirTurno.getDayChooser().getDay();
                int mes = jCalendarPedirTurno.getMonthChooser().getMonth()+1;
                int año = jCalendarPedirTurno.getYearChooser().getYear();
                String fecha = dia + "/"+mes +"/"+ año;
                jLabelConfirmacionFecha.setText("Fecha: " + fecha);
                
                // LISTA ANALISIS
                jLabelConfirmacionMedico.setText("Medico: " + jTextFieldDniPacienteMedico.getText());
                jLabelConfirmacionDiagnostico.setText("Diagnostico: " + jTextFieldDniPacienteDiagnostico.getText());
                
                Vector<String> listData = new Vector<>();
                for(String analisis : analisisElegidos){
                    listData.add(analisis);
                }
                
                jLabelConfirmacionAnalisisList.setListData(listData);
                
                jButtonCrearTurnoSiguiente.setText("Crear");
                break;
            case 3:
                // Crear turno y guardarlo en la base de datos
                String obraSocial = (String)jComboBoxObraSocialTurno.getSelectedItem();
                if (jCheckBoxParticular.isSelected()){
                    obraSocial = "PARTICULAR";
                }

                ManagerTurno.guardarTurno(jTextFieldDniPaciente.getText(),
                        obraSocial,
                        jTextFieldDniPacienteMedico.getText(),
                        jTextFieldDniPacienteDiagnostico.getText(),
                        jCalendarPedirTurno,
                        analisisElegidos);

                vaciarCamposCrearTurno();
                
                // Notificar al Usuario
                String msjError = "TURNO CREADO CON EXITO";
                JOptionPane.showMessageDialog(null,msjError);
                
                break;
            
        }
        System.out.println("Pagina siguiente: " + crearTurnoActualStep);
        crearTurnoPasosJTabbed.setSelectedIndex(crearTurnoActualStep);
    }//GEN-LAST:event_jButtonCrearTurnoSiguienteActionPerformed

    private void jButton6ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton6ActionPerformed
        // Agrega las obras sociales al Cargar un Paciente
        String nombreObraSocialElegida = (String)jComboBoxObraSocialCrearPaciente.getSelectedItem();
//        ObraSocial os = ManagerObraSocial.recuperarObraSocial();
        boolean obraSocialAgregada = obrasSocialesElegidas.add(nombreObraSocialElegida);
        if (obraSocialAgregada){
            dataModelobrasSocialesElegidas.addRow(new Object[]{nombreObraSocialElegida});
            System.out.println(obrasSocialesElegidas);
        }
    }//GEN-LAST:event_jButton6ActionPerformed

    
    private void vaciarCamposCrearPaciente(){
        // Reinicia Formulatio de Crear Paciente
        jTextFieldDni.setText("");
        jTextFieldNombre.setText("");
        jTextFieldApellido.setText("");
        jTextFieldEdad.setText("");
        jTextFieldDomicilio.setText("");
        jTextFieldTelefono.setText("");
        jTextFieldCorreo.setText("");
        jRadioButtonMujer.setSelected(true);
        jRadioButtonHombre.setSelected(false);
        obrasSocialesElegidas.clear();
        dataModelobrasSocialesElegidas.getDataVector().clear();
        crearPacienteActualStep = 0;
        jTabbedPanelCrearPaciente.setSelectedIndex(crearPacienteActualStep);
    }
    
    private void jButtonCrearPacienteSiguienteActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonCrearPacienteSiguienteActionPerformed
        // Comportamiento boton siguiente Crear Paciente
        if (crearPacienteActualStep < 2) {
            // Realizar controles necesarios mientras avanza, y solo avanza si se cumple el requisito
            boolean avance = false;
            System.out.println(crearPacienteActualStep);
            switch(crearPacienteActualStep){
                case 0:
                    // Validar Paso 1
                    avance = ManagerPaciente.validarDni(jTextFieldDni.getText()) && !ManagerPaciente.comprobarExistencia(Integer.parseInt(jTextFieldDni.getText()))
                            && ManagerPaciente.validarNombre(jTextFieldNombre.getText())
                            && ManagerPaciente.validarNombre(jTextFieldApellido.getText())
                            && ManagerPaciente.validarEdad(jTextFieldEdad.getText());
                    
                    // Notificar Errores
                    if (!avance){
                        // Configurar Mensaje de error
                        String msjError = "ERROR EN LOS SIGUIENTES CAMPOS:";
                        
                        if (!ManagerPaciente.validarDni(jTextFieldDni.getText())){
                            msjError += "\n-DNI";
                        }
                        else if (ManagerPaciente.comprobarExistencia(Integer.parseInt(jTextFieldDni.getText())))
                                msjError += "\n-DNI: El Paciente ya existe";
                        
                        if (!ManagerPaciente.validarNombre(jTextFieldNombre.getText()))
                            msjError += "\n-Nombre";
                        if (!ManagerPaciente.validarNombre(jTextFieldApellido.getText()))
                            msjError += "\n-Apellido";
                        if (!ManagerPaciente.validarEdad(jTextFieldEdad.getText()))
                            msjError += "\n-Edad";
                        JOptionPane.showMessageDialog(null,msjError);
                    }

                    
                    break;
                case 1:
                    // Validar Paso 2
                    avance = ManagerPaciente.validarDomicilio(jTextFieldDomicilio.getText())
                            && ManagerPaciente.validarTelefono(jTextFieldTelefono.getText())
                            && ManagerPaciente.validarCorreoElectronico(jTextFieldCorreo.getText());
                    
                    // Notificar errores
                    if (!avance){
                        // Configurar Mensaje de error
                        String msjError = "ERROR EN LOS SIGUIENTES CAMPOS:";
                        
                        if (!ManagerPaciente.validarDomicilio(jTextFieldDomicilio.getText()))
                            msjError += "\n-Domicilio";
                        if (!ManagerPaciente.validarTelefono(jTextFieldTelefono.getText()))
                            msjError += "\n-Telefono";
                        if (!ManagerPaciente.validarCorreoElectronico(jTextFieldCorreo.getText()))
                            msjError += "\n-Correo Electronico";
                        JOptionPane.showMessageDialog(null,msjError);
                    }
                    break;
            }
            
            System.out.println(avance);
            
            
            if (!avance) return;
            
            crearPacienteActualStep++;
            jTabbedPanelCrearPaciente.setSelectedIndex(crearPacienteActualStep);
            jButtonCrearPacienteAnterior.setVisible(true);
            
            // Cambiar label para indicar que es el ultimo paso
            if (crearPacienteActualStep == 2){
                jButtonCrearPacienteSiguiente.setText("Guardar");
            }
            else{
                jButtonCrearPacienteSiguiente.setText("Siguiente");
            }
        }
        else if (crearPacienteActualStep == 2){
            // Guardar Paciente
            int sexo=0;
            if (jRadioButtonMujer.isSelected()){
                sexo=1;
            }
            if (jRadioButtonHombre.isSelected()){
                sexo=2;
            }
            
            ManagerPaciente.guardarPaciente(jTextFieldDni.getText(),
                    jTextFieldNombre.getText(),
                    jTextFieldApellido.getText(),
                    jTextFieldEdad.getText(), sexo,
                    jTextFieldDomicilio.getText(),
                    jTextFieldTelefono.getText(),
                    jTextFieldCorreo.getText(),
                    obrasSocialesElegidas);


            vaciarCamposCrearPaciente();
//            // Prepara para el siguiente
//            // Resetear campos
//            jTextFieldDni.setText("");
//            jTextFieldNombre.setText("");
//            jTextFieldApellido.setText("");
//            jTextFieldEdad.setText("");
//            jTextFieldDomicilio.setText("");
//            jTextFieldTelefono.setText("");
//            jTextFieldCorreo.setText("");
//            jRadioButtonMujer.setSelected(true);
//            jRadioButtonHombre.setSelected(false);
//            obrasSocialesElegidas.clear();
//            dataModelobrasSocialesElegidas.getDataVector().clear();
//            crearPacienteActualStep = 0;
//            jTabbedPanelCrearPaciente.setSelectedIndex(crearPacienteActualStep);
            // Notificar al usuario
            String msjError = "PACIENTE AGREGADO CON EXITO";
            JOptionPane.showMessageDialog(null,msjError);


        }
        
        
    }//GEN-LAST:event_jButtonCrearPacienteSiguienteActionPerformed

    private void jButtonCrearPacienteAnteriorActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonCrearPacienteAnteriorActionPerformed
        if (crearPacienteActualStep > 0) {
            crearPacienteActualStep--;
            jButtonCrearPacienteSiguiente.setText("Siguiente");
            jTabbedPanelCrearPaciente.setSelectedIndex(crearPacienteActualStep);
            if (crearPacienteActualStep == 0){
                jButtonCrearPacienteAnterior.setVisible(false);
            }
        }
    }//GEN-LAST:event_jButtonCrearPacienteAnteriorActionPerformed

    private void jCalendarPedirTurnoPropertyChange(java.beans.PropertyChangeEvent evt) {//GEN-FIRST:event_jCalendarPedirTurnoPropertyChange
        // Armar la vista previa de los turnos disponibles en el calendario
        int dia = jCalendarPedirTurno.getDayChooser().getDay();
        int mes = jCalendarPedirTurno.getMonthChooser().getMonth()+1; // Los meses empiezan desde cero en JCalendar
        int año = jCalendarPedirTurno.getYearChooser().getYear();
        String fecha = dia + "/"+mes +"/"+ año;     // Para mostrar en pantalla
        String fechaConsulta = año+"-"+mes+"-"+dia; // Para la consulta SQL
        
        int cantidadTurnosExistentes = ManagerTurno.obtenerCantidadTurnosPorFecha(fechaConsulta);
        if (cantidadTurnosExistentes <= MAX_TURNOS)
            turnosDisponibles = MAX_TURNOS-cantidadTurnosExistentes;
        else turnosDisponibles = 0;
        
        String turnosDisponiblesString = "TURNOS DISPONIBLES PARA LA FECHA " + fecha + ": " + turnosDisponibles;

        // Determina el color del label dependiendo de la cantidad de turnos disponibles
        if (turnosDisponibles == 0){
            jLabelPedirTurnoDisponibles.setForeground(Color.red);
            jButtonCrearTurnoSiguiente.setEnabled(false);
        }
        else{
            jLabelPedirTurnoDisponibles.setForeground(Color.black);
            jButtonCrearTurnoSiguiente.setEnabled(true);
        }
        
        jLabelPedirTurnoDisponibles.setText(turnosDisponiblesString);
        
    }//GEN-LAST:event_jCalendarPedirTurnoPropertyChange

    private void jCheckBoxParticularActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jCheckBoxParticularActionPerformed
        // Bloquea el combobox de obra social en caso de usar particular
        // Nota: aca podemos agregar que si un paciente no tiene obra social, esta casilla se active automaticamente
        if (jCheckBoxParticular.isSelected()){
            jComboBoxObraSocialTurno.setEnabled(false);
        }
        else{
            jComboBoxObraSocialTurno.setEnabled(true);
        }
    }//GEN-LAST:event_jCheckBoxParticularActionPerformed

    private void jButtonCrearPacienteActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonCrearPacienteActionPerformed
        cambiarPanel(jPanelAgregarPaciente);
        setHighlighted(jButtonLateralAgregarPaciente);
        jTextFieldDni.setText(jTextFieldDniPaciente.getText());
    }//GEN-LAST:event_jButtonCrearPacienteActionPerformed

    private void jTextFieldDniPacienteActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jTextFieldDniPacienteActionPerformed
        // Habilitar otros campos si existe el paciente
//        if (jTextFieldDniPaciente.getText().equals("")) return;
//        int dniPaciente = Integer.parseInt(jTextFieldDniPaciente.getText());
        int dniPaciente = -1;
        try{
            dniPaciente = Integer.parseInt(jTextFieldDniPaciente.getText());
        }catch(NumberFormatException e){
            System.out.println("ERROR: NUMERO INVALIDO");
            return;
        }
        
        
        if (ManagerPaciente.comprobarExistencia(dniPaciente)){
            jButtonCrearPaciente.setEnabled(false);
            jComboBoxObraSocialTurno.setEnabled(true);
            jCheckBoxParticular.setEnabled(true);
            jCheckBoxParticular.setSelected(false);
            
            // Cargar obras sociales en combobox
            ArrayList<ObraSocial> obrasSociales = ManagerPaciente.obtenerObrasSociales(dniPaciente);
            jComboBoxObraSocialTurno.removeAllItems();
            for(ObraSocial os : obrasSociales){
                jComboBoxObraSocialTurno.addItem(os.getNombre());
            }
            
            // En caso de no tener obras sociales, marcar pestaña de particular
            if (obrasSociales.isEmpty()){
                jComboBoxObraSocialTurno.setEnabled(false);
                jCheckBoxParticular.setEnabled(false);
                jCheckBoxParticular.setSelected(true);
            }
        }
        else{
            jButtonCrearPaciente.setEnabled(true);
            jComboBoxObraSocialTurno.setEnabled(false);
            jCheckBoxParticular.setEnabled(false);
        }
    }//GEN-LAST:event_jTextFieldDniPacienteActionPerformed

    private void crearTurnoPasosJTabbedStateChanged(javax.swing.event.ChangeEvent evt) {//GEN-FIRST:event_crearTurnoPasosJTabbedStateChanged
        
    }//GEN-LAST:event_crearTurnoPasosJTabbedStateChanged

    private void jButtonAgregarAnalisisATurnoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonAgregarAnalisisATurnoActionPerformed
        // Comportamiento de Agregar analisis durante la creación de un turno
        String nombreAnalisisElegido = (String)jComboCrearTurnoAnalisis.getSelectedItem();
//        ObraSocial os = ManagerObraSocial.recuperarObraSocial();
        boolean analisisAgregado = analisisElegidos.add(nombreAnalisisElegido);
        if (analisisAgregado){
            dataModelanalisisElegidos.addRow(new Object[]{nombreAnalisisElegido});
            System.out.println(analisisElegidos);
        }
    }//GEN-LAST:event_jButtonAgregarAnalisisATurnoActionPerformed

    private void jRadioButtonMujerActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jRadioButtonMujerActionPerformed
        if (!jRadioButtonMujer.isSelected()) jRadioButtonMujer.setSelected(true);
        jRadioButtonHombre.setSelected(false);
    }//GEN-LAST:event_jRadioButtonMujerActionPerformed

    private void jRadioButtonHombreActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jRadioButtonHombreActionPerformed
        if (!jRadioButtonHombre.isSelected()) jRadioButtonHombre.setSelected(true);
        jRadioButtonMujer.setSelected(false);
    }//GEN-LAST:event_jRadioButtonHombreActionPerformed

    private void jTextFieldDniPacienteFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_jTextFieldDniPacienteFocusLost
        // Habilitar otros campos si existe el paciente
//        if (jTextFieldDniPaciente.getText().equals("")) return;

//        int dniPaciente = Integer.parseInt(jTextFieldDniPaciente.getText());

        int dniPaciente = -1;
        try{
            dniPaciente = Integer.parseInt(jTextFieldDniPaciente.getText());
        }catch(NumberFormatException e){
            System.out.println("ERROR: NUMERO INVALIDO");
            return;
        }

        if (ManagerPaciente.comprobarExistencia(dniPaciente)){
            jButtonCrearPaciente.setEnabled(false);
            jComboBoxObraSocialTurno.setEnabled(true);
            jCheckBoxParticular.setEnabled(true);
            jCheckBoxParticular.setSelected(false);
            ArrayList<ObraSocial> obrasSociales = ManagerPaciente.obtenerObrasSociales(dniPaciente);
            jComboBoxObraSocialTurno.removeAllItems();
            for(ObraSocial os : obrasSociales){
                jComboBoxObraSocialTurno.addItem(os.getNombre());
            }
            
            // En caso de no tener obras sociales, marcar pestaña de particular
            if (obrasSociales.isEmpty()){
                jComboBoxObraSocialTurno.setEnabled(false);
                jCheckBoxParticular.setEnabled(false);
                jCheckBoxParticular.setSelected(true);
            }
            
        }
        else{
            jButtonCrearPaciente.setEnabled(true);
            jComboBoxObraSocialTurno.setEnabled(false);
            jCheckBoxParticular.setEnabled(false);
        }
    }//GEN-LAST:event_jTextFieldDniPacienteFocusLost

    private void jButtonBuscarTurnoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonBuscarTurnoActionPerformed
        // Busca si hay un turno disponible
        dataModelanalisisInforme.getDataVector().clear();
//        boolean avance = ManagerInforme.existeTurno(jTextFieldBuscarTurno.getText());
//        if (!avance) return;
//        ManagerInforme.llenarTablaAnalisis(Integer.parseInt(jTextFieldBuscarTurno.getText()), dataModelanalisisInforme);
//        ManagerInforme.validarResultado(dataModelanalisisInforme);
        if (jComboBoxTurnosDNI.isEnabled()){
            int nroTurno = ManagerInforme.llenarTablaAnalisis(jComboBoxTurnosDNI.getSelectedIndex(), dataModelanalisisInforme);
            jLabelCargarResultadoTurnoCargado.setText("Turno Cargado: " + nroTurno);
        }
        
        
    }//GEN-LAST:event_jButtonBuscarTurnoActionPerformed

    private void jButtonGuardarResultadoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonGuardarResultadoActionPerformed
        // Valida que todos los campos ingresados por el usuario sean validos (que sean en formato flotante)
        boolean exito = ManagerInforme.validarResultado(dataModelanalisisInforme);
        if (!exito) {
            String msjError = "ERROR: EL FORMATO DE LOS RESULTADOS NO ES CORRECTO\nSOLO SE ADMITEN NUMEROS";
            JOptionPane.showMessageDialog(null,msjError);
            return;
        }
        
//        ManagerInforme.guardarInforme(jTextFieldBuscarTurno.getText(),dataModelanalisisInforme);
        ManagerInforme.guardarInforme(dataModelanalisisInforme);
        
        // Limpiar Campos
        jLabelCargarResultadoTurnoCargado.setText("Turno Cargado: ");
        jTextFieldBuscarTurno.setText("");
        jComboBoxTurnosDNI.removeAllItems();
        jComboBoxTurnosDNI.setEnabled(false);
        jButtonBuscarTurno.setEnabled(false);
        dataModelanalisisInforme.fireTableDataChanged();// Actualiza la tabla tras su vaciado
        
        String msjError = "INFORME GUARDADO CON EXITO";
        JOptionPane.showMessageDialog(null,msjError);
        
    }//GEN-LAST:event_jButtonGuardarResultadoActionPerformed

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        vaciarCamposCrearTurno();
    }//GEN-LAST:event_jButton1ActionPerformed

    private void jTextFieldBuscarTurnoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jTextFieldBuscarTurnoActionPerformed
        try{
            int dni = Integer.parseInt(jTextFieldBuscarTurno.getText());
            List<String> turnosPorDNI = ManagerTurno.obtenerTurnosPorDNI(dni);
            jComboBoxTurnosDNI.setEnabled(true);
            jButtonBuscarTurno.setEnabled(true);
            if (turnosPorDNI.isEmpty()){
                jComboBoxTurnosDNI.setEnabled(false);
                jButtonBuscarTurno.setEnabled(false);
                return;
            }
            jComboBoxTurnosDNI.removeAllItems();
            for (String s : turnosPorDNI){
                jComboBoxTurnosDNI.addItem(s);
            }
            
        }
        catch(NumberFormatException e){
            jComboBoxTurnosDNI.setEnabled(false);
            jButtonBuscarTurno.setEnabled(false);
            System.out.println("ERROR: DNI INVALIDO");
        }
    }//GEN-LAST:event_jTextFieldBuscarTurnoActionPerformed

    private void jButtonLateralConsultarStockReactivoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonLateralConsultarStockReactivoActionPerformed
        setHighlighted(jButtonLateralConsultarStockReactivo);
        cambiarPanel(jPanelEnConstruccion);
    }//GEN-LAST:event_jButtonLateralConsultarStockReactivoActionPerformed

    private void jButtonLateralCancelarTurnoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonLateralCancelarTurnoActionPerformed
        setHighlighted(jButtonLateralCancelarTurno);
        cambiarPanel(jPanelCancelarTurno);
        
    }//GEN-LAST:event_jButtonLateralCancelarTurnoActionPerformed

    private void jButtonAgregarAnalisisAgregarReactivoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonAgregarAnalisisAgregarReactivoActionPerformed
        String nombreReactivoElegido = (String)jComboBoxCrearAnalisisReactivos.getSelectedItem();
        
        boolean reactivoAgregado = reactivosAnalisis.add(nombreReactivoElegido);
        if (reactivoAgregado){
            Reactivo r = ManagerReactivo.obtenerPorNombre(nombreReactivoElegido);
            dataModelReactivosAnalisis.addRow(new Object[]{r.getNombre(),r.getCantidadRecipiente(),0.0f});
            System.out.println(reactivosAnalisis);
        }      
        
        
        
        
    }//GEN-LAST:event_jButtonAgregarAnalisisAgregarReactivoActionPerformed

    private void jButtonBuscarAnalisisActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonBuscarAnalisisActionPerformed
        Analisis analisisElegido = ManagerAnalisis.buscarPorNombre(jComboBoxConsultarAnalisis.getSelectedItem().toString());
        if (analisisElegido == null){
            System.out.println("ERROR INTERNO: ANALISIS NO ENCONTRADO");
            return;
        }
        System.out.println(analisisElegido); // DEBUG
        
        // Actualizar etiquetas
        jLabelBuscarAnalisisValorReferencia.setText("Valor Referencia: " + analisisElegido.getValorReferencia());
        jLabelBuscarAnalisisMonto.setText("Monto: "+ String.format("$%.2f", analisisElegido.getMonto()));
        jLabelBuscarAnalisisMetodoUsado.setText("Metodo Usado: " + analisisElegido.getMetodoUsado());
        
        // Rellenar tabla de reactivos
        DefaultTableModel model = (DefaultTableModel) jTableBuscarAnalisisReactivos.getModel();
        model.getDataVector().clear();
        for(Reactivo r : analisisElegido.getReactivosUsados().keySet()){
            Object[] fila = {r.getNombre(),r.getCantidadRecipiente(),analisisElegido.getReactivosUsados().get(r)};
            model.addRow(fila);
        }
        
    }//GEN-LAST:event_jButtonBuscarAnalisisActionPerformed

    private void jButtonAgregarAnalisisATurno2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonAgregarAnalisisATurno2ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jButtonAgregarAnalisisATurno2ActionPerformed

    private void jButton10ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton10ActionPerformed
        cambiarPanel(jPanelPedirReactivos);
    }//GEN-LAST:event_jButton10ActionPerformed

    private void jButton9ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton9ActionPerformed
        cambiarPanel(jPanelConsultarStock);
    }//GEN-LAST:event_jButton9ActionPerformed

    private void jCalendarCancelarTurnoPropertyChange(java.beans.PropertyChangeEvent evt) {//GEN-FIRST:event_jCalendarCancelarTurnoPropertyChange
//        // Rellenar ComboBox de Turnos a cancelar
//        jComboBoxCancelarTurno.removeAllItems();
//        
//        
//        // Armar la vista previa de los turnos disponibles en el calendario
//        int dia = jCalendarCancelarTurno.getDayChooser().getDay();
//        int mes = jCalendarCancelarTurno.getMonthChooser().getMonth()+1; // Los meses empiezan desde cero en JCalendar
//        int año = jCalendarCancelarTurno.getYearChooser().getYear();
////        String fecha = dia + "/"+mes +"/"+ año;     // Para mostrar en pantalla
//        String fechaConsulta = año+"-"+mes+"-"+dia; // Para la consulta SQL
        
        int dni = -1;
        try{
            dni = Integer.parseInt(jTextFieldDniCancelarTurno.getText());
        }catch (NumberFormatException e){
//            System.out.println("ERROR: NUMERO INVALIDO O CAMPO VACIO");
        }
        
        ManagerTurno.filtrarPorDniOFechaCancelarTurno(jComboBoxCancelarTurno, jCalendarCancelarTurno, dni);
    }//GEN-LAST:event_jCalendarCancelarTurnoPropertyChange

    private void jTextFieldDniCancelarTurnoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jTextFieldDniCancelarTurnoActionPerformed
        // Rellenar ComboBox de Turnos a cancelar
//        jComboBoxCancelarTurno.removeAllItems();

        // Armar la vista previa de los turnos disponibles en el calendario
//        int dia = jCalendarCancelarTurno.getDayChooser().getDay();
//        int mes = jCalendarCancelarTurno.getMonthChooser().getMonth()+1; // Los meses empiezan desde cero en JCalendar
//        int año = jCalendarCancelarTurno.getYearChooser().getYear();
////        String fecha = dia + "/"+mes +"/"+ año;     // Para mostrar en pantalla
//        String fechaConsulta = año+"-"+mes+"-"+dia; // Para la consulta SQL
//        
        int dni = -1;
        try{
            dni = Integer.parseInt(jTextFieldDniCancelarTurno.getText());
        }catch (NumberFormatException e){
            System.out.println("ERROR: NUMERO INVALIDO");
        }
        
        ManagerTurno.filtrarPorDniOFechaCancelarTurno(jComboBoxCancelarTurno, jCalendarCancelarTurno, dni);
    }//GEN-LAST:event_jTextFieldDniCancelarTurnoActionPerformed

    private void jButton5ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton5ActionPerformed
        Turno turnoEliminar = ManagerTurno.obtenerTurnoDesdeComboboxCancelarTurno(jComboBoxCancelarTurno.getSelectedIndex());
        if (turnoEliminar == null) {    
            System.out.println("ERROR: NO HAY TURNO DISPONIBLE");
            return;
        }
        ManagerTurno.eliminarTurno(turnoEliminar);
        jComboBoxCancelarTurno.removeAllItems();
        jTextFieldDniCancelarTurno.setText("");
    }//GEN-LAST:event_jButton5ActionPerformed

    private void jTextFieldNombreConsultarAnalisisActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jTextFieldNombreConsultarAnalisisActionPerformed
        // Refrescar elementos
        jComboBoxConsultarAnalisis.removeAllItems();
        
        // Obtener nombre del analisis a buscar
        String nombreAnalisis = jTextFieldNombreConsultarAnalisis.getText();
        if (nombreAnalisis == null) nombreAnalisis = "";
        
        // Agregar elementos filtrados a Combobox
        for(Analisis analisis : ManagerAnalisis.filtrarPorNombre(nombreAnalisis)){
            jComboBoxConsultarAnalisis.addItem(analisis.getNombre());
        }
        
        // Comprobar si el conjunto de analisis a elegir está vacio
        boolean permitirCampo = jComboBoxConsultarAnalisis.getItemCount() > 0;
        jButtonBuscarAnalisis.setEnabled(permitirCampo);
        jComboBoxConsultarAnalisis.setEnabled(permitirCampo);
    }//GEN-LAST:event_jTextFieldNombreConsultarAnalisisActionPerformed

    private void jButtonAgregarAnalisisEliminarReactivoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonAgregarAnalisisEliminarReactivoActionPerformed
        String nombreReactivoElegido = (String)jComboBoxCrearAnalisisReactivos.getSelectedItem();
        boolean reactivoAgregado = reactivosAnalisis.remove(nombreReactivoElegido);
        if (reactivoAgregado){
            for(int i = 0; i < dataModelReactivosAnalisis.getRowCount(); i++){
                if(dataModelReactivosAnalisis.getValueAt(i, 0).equals(nombreReactivoElegido)){
                    dataModelReactivosAnalisis.removeRow(i);
                    break;
                }
            }
            System.out.println(reactivosAnalisis);
        }
        
    }//GEN-LAST:event_jButtonAgregarAnalisisEliminarReactivoActionPerformed

    private void jButtonCrearAnalisisSiguienteActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonCrearAnalisisSiguienteActionPerformed
        // Comportamiento boton siguiente Crear Analisis
        System.out.println("Pagina actual: " + crearAnalisisActualStep);
        boolean avance = false;
        switch(crearAnalisisActualStep){
            case 0:
                avance = ManagerAnalisis.validarNombreAnalisis(jTextFieldCrearAnalisisNombre.getText()) && 
                        ManagerAnalisis.validarValorReferencia(jTextFieldCrearAnalisisValorReferencia.getText()) &&
                        ManagerAnalisis.validarFlotante(jTextFieldCrearAnalisisMonto.getText()) &&
                                ManagerAnalisis.validarSoloTexto(jTextFieldCrearAnalisisMetodoUsado.getText());
                System.out.println("Campos validados: "+avance);
                if (!avance) {
                    // Configurar Mensaje de error
                    String msjError = "ERROR EN LOS SIGUIENTES CAMPOS:";
                    if (ManagerAnalisis.comprobarExistenciaAnalisis(jTextFieldCrearAnalisisNombre.getText()))
                        msjError += "\n-Nombre: El Analisis ya existe";
                    else if (!ManagerAnalisis.validarNombreAnalisis(jTextFieldCrearAnalisisNombre.getText()))
                        msjError += "\n-Nombre";
                    
                    if (!ManagerAnalisis.validarValorReferencia(jTextFieldCrearAnalisisValorReferencia.getText()))
                        msjError += "\n-Valor Referencia";
                    if (!ManagerAnalisis.validarFlotante(jTextFieldCrearAnalisisMonto.getText()))
                        msjError += "\n-Monto";
                    if (!ManagerAnalisis.validarSoloTexto(jTextFieldCrearAnalisisMetodoUsado.getText()))
                        msjError += "\n-Metodo Usado";
                    JOptionPane.showMessageDialog(null,msjError);
                    
                    return;
                }
                crearAnalisisActualStep++;
                jTabbedPanelAgregarAnalisis.setSelectedIndex(crearAnalisisActualStep);
                jButtonCrearAnalisisAnterior.setVisible(true);
                
                break;
            case 1:
                avance = !reactivosAnalisis.isEmpty() && ManagerAnalisis.validarTablaReactivos((DefaultTableModel)jTableAgregarPacienteReactivos.getModel());
                System.out.println("Campos validados: "+avance);
                if (!avance) {
                    // Configurar Mensaje de error
                    String msjError = "ERROR: INGRESE AL MENOS UN REACTIVO";
                    if (!ManagerAnalisis.validarTablaReactivos((DefaultTableModel)jTableAgregarPacienteReactivos.getModel()))
                        msjError = "ERROR: EL FORMATO DE LOS NUMEROS NO ES VALIDO";
                    if (reactivosAnalisis.isEmpty())
                        msjError = "ERROR: INGRESE AL MENOS UN REACTIVO";
                    
                    JOptionPane.showMessageDialog(null,msjError);
                    return;
                }
                
                // Guardar
                // Obtener campos de textfields y tabla
                String nombre = jTextFieldCrearAnalisisNombre.getText();
                String valor = jTextFieldCrearAnalisisValorReferencia.getText();
                String monto = jTextFieldCrearAnalisisMonto.getText();
                String metodo = jTextFieldCrearAnalisisMetodoUsado.getText();
                DefaultTableModel tabla = (DefaultTableModel)jTableAgregarPacienteReactivos.getModel();
                ManagerAnalisis.guardarAnalisis(nombre,valor,monto,metodo,tabla);
                
                // Actualizar Combobox de analisis
                jComboBoxConsultarAnalisis.removeAllItems();
                for(Analisis analisis : ManagerAnalisis.obtenerTodosLosAnalisis()){
                    jComboBoxConsultarAnalisis.addItem(analisis.getNombre());
                }
                
                // Vaciar campos
                crearAnalisisActualStep=0;
                jTabbedPanelAgregarAnalisis.setSelectedIndex(crearAnalisisActualStep);
                jButtonCrearAnalisisAnterior.setVisible(false);
                jTextFieldCrearAnalisisNombre.setText("");
                jTextFieldCrearAnalisisValorReferencia.setText("");
                jTextFieldCrearAnalisisMonto.setText("");
                jTextFieldCrearAnalisisMetodoUsado.setText("");
                dataModelReactivosAnalisis.getDataVector().clear();
                reactivosAnalisis.clear();
                
                // Notificar al Usuario
                String msjError = "ANALISIS AGREGADO CON EXITO";
                JOptionPane.showMessageDialog(null,msjError);
                
                break;
        }
    }//GEN-LAST:event_jButtonCrearAnalisisSiguienteActionPerformed

    private void jTextFieldNombreConsultarAnalisisFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_jTextFieldNombreConsultarAnalisisFocusLost
        // Refrescar elementos
        jComboBoxConsultarAnalisis.removeAllItems();
        
        // Obtener nombre del analisis a buscar
        String nombreAnalisis = jTextFieldNombreConsultarAnalisis.getText();
        if (nombreAnalisis == null) nombreAnalisis = "";
        
        // Agregar elementos filtrados a Combobox
        for(Analisis analisis : ManagerAnalisis.filtrarPorNombre(nombreAnalisis)){
            jComboBoxConsultarAnalisis.addItem(analisis.getNombre());
        }
        
        // Comprobar si el conjunto de analisis a elegir está vacio
        boolean permitirCampo = jComboBoxConsultarAnalisis.getItemCount() > 0;
        jButtonBuscarAnalisis.setEnabled(permitirCampo);
        jComboBoxConsultarAnalisis.setEnabled(permitirCampo);
    }//GEN-LAST:event_jTextFieldNombreConsultarAnalisisFocusLost

    private void jButtonCrearAnalisisAnteriorActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonCrearAnalisisAnteriorActionPerformed
        System.out.println("Pagina actual: " + crearAnalisisActualStep);
        switch(crearAnalisisActualStep){
            case 1:
                crearAnalisisActualStep--;
                jTabbedPanelAgregarAnalisis.setSelectedIndex(crearAnalisisActualStep);
                jButtonCrearAnalisisAnterior.setVisible(false);
                break;
        }
    }//GEN-LAST:event_jButtonCrearAnalisisAnteriorActionPerformed

    private void jButtonEliminarAnalisisATurnoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonEliminarAnalisisATurnoActionPerformed
        String nombreAnalisisElegido = (String)jComboCrearTurnoAnalisis.getSelectedItem();
        boolean analisisAgregado = analisisElegidos.remove(nombreAnalisisElegido);
        if (analisisAgregado){
            for(int i = 0; i < dataModelanalisisElegidos.getRowCount(); i++){
                if(dataModelanalisisElegidos.getValueAt(i, 0).equals(nombreAnalisisElegido)){
                    dataModelanalisisElegidos.removeRow(i);
                    break;
                }
            }
            System.out.println(analisisElegidos);
        }
    }//GEN-LAST:event_jButtonEliminarAnalisisATurnoActionPerformed

    private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton2ActionPerformed
        String nombreAnalisisElegido = (String)jComboBoxObraSocialCrearPaciente.getSelectedItem();
        boolean analisisAgregado = obrasSocialesElegidas.remove(nombreAnalisisElegido);
        if (analisisAgregado){
            for(int i = 0; i < dataModelobrasSocialesElegidas.getRowCount(); i++){
                if(dataModelobrasSocialesElegidas.getValueAt(i, 0).equals(nombreAnalisisElegido)){
                    dataModelobrasSocialesElegidas.removeRow(i);
                    break;
                }
            }
            System.out.println(obrasSocialesElegidas);
        }
    }//GEN-LAST:event_jButton2ActionPerformed

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
            java.util.logging.Logger.getLogger(Laboratorio.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(Laboratorio.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(Laboratorio.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(Laboratorio.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new Laboratorio().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JPanel crearTurnoJpanel;
    private javax.swing.JPanel crearTurnoP1;
    private javax.swing.JPanel crearTurnoP2;
    private javax.swing.JPanel crearTurnoP3;
    private javax.swing.JPanel crearTurnoP4;
    private javax.swing.JPanel crearTurnoP5;
    private javax.swing.JTabbedPane crearTurnoPasosJTabbed;
    private javax.swing.JLabel errCrearTurnoDiagnostico;
    private javax.swing.JLabel errCrearTurnoDni;
    private javax.swing.JLabel errCrearTurnoMedico;
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton10;
    private javax.swing.JButton jButton2;
    private javax.swing.JButton jButton5;
    private javax.swing.JButton jButton6;
    private javax.swing.JButton jButton8;
    private javax.swing.JButton jButton9;
    private javax.swing.JButton jButtonAgregarAnalisisATurno;
    private javax.swing.JButton jButtonAgregarAnalisisATurno2;
    private javax.swing.JButton jButtonAgregarAnalisisAgregarReactivo;
    private javax.swing.JButton jButtonAgregarAnalisisEliminarReactivo;
    private javax.swing.JButton jButtonBuscarAnalisis;
    private javax.swing.JButton jButtonBuscarTurno;
    private javax.swing.JButton jButtonCrearAnalisisAnterior;
    private javax.swing.JButton jButtonCrearAnalisisSiguiente;
    private javax.swing.JButton jButtonCrearPaciente;
    private javax.swing.JButton jButtonCrearPacienteAnterior;
    private javax.swing.JButton jButtonCrearPacienteSiguiente;
    private javax.swing.JButton jButtonCrearTurnoAnterior;
    private javax.swing.JButton jButtonCrearTurnoSiguiente;
    private javax.swing.JButton jButtonEliminarAnalisisATurno;
    private javax.swing.JButton jButtonGuardarResultado;
    private javax.swing.JButton jButtonLateralAgregarAnalisis;
    private javax.swing.JButton jButtonLateralAgregarPaciente;
    private javax.swing.JButton jButtonLateralBuscarAnalisis;
    private javax.swing.JButton jButtonLateralCancelarTurno;
    private javax.swing.JButton jButtonLateralConsultarStockReactivo;
    private javax.swing.JButton jButtonLateralDarTurno;
    private javax.swing.JButton jButtonLateralGenerarResultado;
    private com.toedter.calendar.JCalendar jCalendarCancelarTurno;
    private com.toedter.calendar.JCalendar jCalendarPedirTurno;
    private javax.swing.JCheckBox jCheckBoxParticular;
    private javax.swing.JComboBox<String> jComboBoxCancelarTurno;
    private javax.swing.JComboBox<String> jComboBoxConsultarAnalisis;
    private javax.swing.JComboBox<String> jComboBoxCrearAnalisisReactivos;
    private javax.swing.JComboBox<String> jComboBoxObraSocialCrearPaciente;
    private javax.swing.JComboBox<String> jComboBoxObraSocialTurno;
    private javax.swing.JComboBox<String> jComboBoxTurnosDNI;
    private javax.swing.JComboBox<String> jComboCrearTurnoAnalisis;
    private javax.swing.JComboBox<String> jComboCrearTurnoAnalisis1;
    private javax.swing.JComboBox<String> jComboCrearTurnoAnalisis2;
    private javax.swing.JLabel jErrorCrearPacienteApellido;
    private javax.swing.JLabel jErrorCrearPacienteCorreo;
    private javax.swing.JLabel jErrorCrearPacienteDni;
    private javax.swing.JLabel jErrorCrearPacienteDomicilio;
    private javax.swing.JLabel jErrorCrearPacienteEdad;
    private javax.swing.JLabel jErrorCrearPacienteNombre;
    private javax.swing.JLabel jErrorCrearPacienteObraSocial;
    private javax.swing.JLabel jErrorCrearPacienteObraSocial1;
    private javax.swing.JLabel jErrorCrearPacienteSexo;
    private javax.swing.JLabel jErrorCrearPacienteTelefono;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel13;
    private javax.swing.JLabel jLabel14;
    private javax.swing.JLabel jLabel15;
    private javax.swing.JLabel jLabel16;
    private javax.swing.JLabel jLabel17;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel21;
    private javax.swing.JLabel jLabel22;
    private javax.swing.JLabel jLabel23;
    private javax.swing.JLabel jLabel24;
    private javax.swing.JLabel jLabel25;
    private javax.swing.JLabel jLabel26;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel30;
    private javax.swing.JLabel jLabel31;
    private javax.swing.JLabel jLabel32;
    private javax.swing.JLabel jLabel33;
    private javax.swing.JLabel jLabel34;
    private javax.swing.JLabel jLabel35;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JLabel jLabelBuscarAnalisisMetodoUsado;
    private javax.swing.JLabel jLabelBuscarAnalisisMonto;
    private javax.swing.JLabel jLabelBuscarAnalisisValorReferencia;
    private javax.swing.JLabel jLabelCargarResultadoTurnoCargado;
    private javax.swing.JList<String> jLabelConfirmacionAnalisisList;
    private javax.swing.JLabel jLabelConfirmacionApellido;
    private javax.swing.JLabel jLabelConfirmacionDiagnostico;
    private javax.swing.JLabel jLabelConfirmacionDni;
    private javax.swing.JLabel jLabelConfirmacionFecha;
    private javax.swing.JLabel jLabelConfirmacionMedico;
    private javax.swing.JLabel jLabelConfirmacionNombre;
    private javax.swing.JLabel jLabelConfirmacionObraSocial;
    private javax.swing.JLabel jLabelCrearTurnoConfirmacion;
    private javax.swing.JLabel jLabelCrearTurnoConfirmacion1;
    private javax.swing.JLabel jLabelCrearTurnoConfirmacion2;
    private javax.swing.JLabel jLabelPedirTurnoDisponibles;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel10;
    private javax.swing.JPanel jPanel11;
    private javax.swing.JPanel jPanel12;
    private javax.swing.JPanel jPanel13;
    private javax.swing.JPanel jPanel14;
    private javax.swing.JPanel jPanel15;
    private javax.swing.JPanel jPanel16;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JPanel jPanel5;
    private javax.swing.JPanel jPanel6;
    private javax.swing.JPanel jPanel7;
    private javax.swing.JPanel jPanel8;
    private javax.swing.JPanel jPanel9;
    private javax.swing.JPanel jPanelAgregarAnalisis;
    private javax.swing.JPanel jPanelAgregarAnalisisDatosBase;
    private javax.swing.JPanel jPanelAgregarPaciente;
    private javax.swing.JPanel jPanelAgregarPacienteP1;
    private javax.swing.JPanel jPanelAgregarPacienteP2;
    private javax.swing.JPanel jPanelAgregarPacienteP3;
    private javax.swing.JPanel jPanelAgregarPacienteP4;
    private javax.swing.JPanel jPanelBackground;
    private javax.swing.JPanel jPanelBarraLateral;
    private javax.swing.JPanel jPanelCancelarTurno;
    private javax.swing.JPanel jPanelConsultarAnalisis;
    private javax.swing.JPanel jPanelConsultarStock;
    private javax.swing.JPanel jPanelDarTurno;
    private javax.swing.JPanel jPanelEnConstruccion;
    private javax.swing.JPanel jPanelGenerarResultado;
    private javax.swing.JPanel jPanelPedirReactivos;
    private javax.swing.JRadioButton jRadioButtonHombre;
    private javax.swing.JRadioButton jRadioButtonMujer;
    private javax.swing.JScrollBar jScrollBar1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JScrollPane jScrollPane4;
    private javax.swing.JScrollPane jScrollPane5;
    private javax.swing.JScrollPane jScrollPane6;
    private javax.swing.JScrollPane jScrollPane7;
    private javax.swing.JSeparator jSeparator1;
    private javax.swing.JSeparator jSeparator2;
    private javax.swing.JSeparator jSeparator3;
    private javax.swing.JTabbedPane jTabbedPanelAgregarAnalisis;
    private javax.swing.JTabbedPane jTabbedPanelCrearPaciente;
    private javax.swing.JTable jTableAgregarPacienteReactivos;
    private javax.swing.JTable jTableAnalisisAgregarTurno;
    private javax.swing.JTable jTableAnalisisAgregarTurno2;
    private javax.swing.JTable jTableBuscarAnalisisReactivos;
    private javax.swing.JTable jTableInformeAnalisis;
    private javax.swing.JTable jTableObrasSocialesAgregarPaciente;
    private javax.swing.JTextField jTextFieldApellido;
    private javax.swing.JTextField jTextFieldBuscarTurno;
    private javax.swing.JTextField jTextFieldCorreo;
    private javax.swing.JTextField jTextFieldCrearAnalisisMetodoUsado;
    private javax.swing.JTextField jTextFieldCrearAnalisisMonto;
    private javax.swing.JTextField jTextFieldCrearAnalisisNombre;
    private javax.swing.JTextField jTextFieldCrearAnalisisValorReferencia;
    private javax.swing.JTextField jTextFieldDni;
    private javax.swing.JTextField jTextFieldDniCancelarTurno;
    private javax.swing.JTextField jTextFieldDniPaciente;
    private javax.swing.JTextField jTextFieldDniPacienteDiagnostico;
    private javax.swing.JTextField jTextFieldDniPacienteMedico;
    private javax.swing.JTextField jTextFieldDomicilio;
    private javax.swing.JTextField jTextFieldEdad;
    private javax.swing.JTextField jTextFieldNombre;
    private javax.swing.JTextField jTextFieldNombreConsultarAnalisis;
    private javax.swing.JTextField jTextFieldTelefono;
    // End of variables declaration//GEN-END:variables
}
