/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

/*
 * VisualizadorHorarioObjeto.java
 *
 * Created on 30-05-2010, 04:50:43 PM
 */

package jramos.GUI;

import java.util.ArrayList;
import java.util.Vector;
import javax.swing.DefaultListModel;
import javax.swing.table.DefaultTableModel;
import jramos.excepciones.HourOutOfRangeException;
import jramos.tiposDatos.Curso;
import jramos.tiposDatos.Hora;
import jramos.tiposDatos.Profesor;
import jramos.tiposDatos.Semestre;

/**
 *
 * @author victor
 */
public class VisualizadorHorarioObjeto extends javax.swing.JDialog {
    public static final int EDICION = 1;
    public static final int VISUALIZACION = 2;
    private ArrayList<Hora> horasAMostrar;
    private ArrayList<Hora> horasOcupadasDelProfe;
    private ArrayList<Hora> horasDispDelProfe;
    private ArrayList<Hora> listaHorasSeleccionadas;
    private int modo;
    private DialogoEdicionCurso dialogoEdicionCursoPadre;
    private DefaultListModel listModelHoras;
    private Class claseDelObjeto;
    /** Creates new form VisualizadorHorarioObjeto */
    public VisualizadorHorarioObjeto(java.awt.Window parent, boolean modal, Object objetoQueVerHorario, int modo, Profesor profesorAAsignar) {
        super(parent);
        initComponents();
        this.modo = modo;
        int i, j;
        this.horasOcupadasDelProfe = new ArrayList();
        this.horasDispDelProfe = new ArrayList();
        this.horasAMostrar = new ArrayList();
        this.listaHorasSeleccionadas = new ArrayList();
        this.listModelHoras = new DefaultListModel();
        this.claseDelObjeto = objetoQueVerHorario.getClass();
        this.setLocationRelativeTo(parent);
        
        if (modo == VisualizadorHorarioObjeto.VISUALIZACION)
        {       this.horarioMostrado.setCellSelectionEnabled(false);
                this.scrollPanelHorasElegidas.setVisible(false);
                this.scrollPanelHorasElegidas.setEnabled(false);
                this.JListHorasSeleccionadas.setVisible(false);
                this.JListHorasSeleccionadas.setEnabled(false);
                this.labelElegidas.setVisible(false);
                this.labelHoras.setVisible(false);
                this.labelElegidas.setEnabled(false);
                this.labelHoras.setEnabled(false);
                this.setSize(782, 460);
        }
        if (modo == VisualizadorHorarioObjeto.EDICION)
        {       this.horarioMostrado.setCellSelectionEnabled(false);
                this.panelCursos.setVisible(false);
                this.labelCursos.setVisible(false);
                this.setSize(860, 300);
                this.botonCerrarHorario.setText("Aceptar");
                this.JListHorasSeleccionadas.setEnabled(true);
                this.JListHorasSeleccionadas.setVisible(true);
                this.JListHorasSeleccionadas.setModel(this.listModelHoras);
                if (profesorAAsignar != null)
                {       this.horasAMostrar = profesorAAsignar.getHorasDispArrayList();
                        this.horasOcupadasDelProfe = profesorAAsignar.getHorasAsigArrayList();
                        this.horasDispDelProfe = profesorAAsignar.getHorasDispArrayList();
                }

        }

        if (objetoQueVerHorario.getClass().equals(Curso.class))
        {       horasAMostrar = ((Curso)objetoQueVerHorario).getHorasAsigArrayList();
                this.labelClaseObjeto.setText("Curso");
                this.labelNombreObjeto.setText(((Curso)objetoQueVerHorario).getNombreCurso());
                this.panelCursos.setVisible(false);
                this.labelCursos.setVisible(false);
                this.botonCerrarHorario.setText("Aceptar");
        }
        if (objetoQueVerHorario.getClass().equals(Profesor.class))
        {       this.horasAMostrar = ((Profesor)objetoQueVerHorario).getHorasDispArrayList();
                this.horasOcupadasDelProfe = ((Profesor)objetoQueVerHorario).getHorasAsigArrayList();
                this.labelClaseObjeto.setText("Profesor");
                this.labelNombreObjeto.setText(((Profesor)objetoQueVerHorario).getNombreProfesor());

                //muestor los cursos que tenga asignados el profesor en la tabla de cursos
                DefaultTableModel modelo = new DefaultTableModel(new Object [][] {},new String [] {"Curso", "Asignatura"});
                Vector vectorFila = new Vector(3);
                for (Curso curso :((Profesor)objetoQueVerHorario).getCursosAsigArrayList())
                {       vectorFila.add(curso.getCodigoCurso()+" - " +curso.getSeccion() + " - "+ curso.getHorario());
                        vectorFila.add(curso.getNombreCurso());
                        modelo.addRow(vectorFila);
                        vectorFila = new Vector(3);
                }
                this.tablaCursos.setModel(modelo);
                
        }
        
        if (objetoQueVerHorario.getClass().equals(Semestre.class))
        {       horasAMostrar = ((Profesor)objetoQueVerHorario).getHorasAsigArrayList();
                this.labelClaseObjeto.setText("Semestre");
                this.labelNombreObjeto.setText("N° "+((Semestre)objetoQueVerHorario).getNumeroSemestre());
                //muestor los cursos que tenga asignados el profesor en la tabla de cursos
                DefaultTableModel modelo = new DefaultTableModel(new Object [][] {},new String [] {"Curso", "Asignatura"});
                Vector vectorFila = new Vector(3);
                for (Curso curso :((Semestre)objetoQueVerHorario).getCursosArrayList())
                {       vectorFila.add(curso.getCodigoCurso()+" - " +curso.getSeccion());
                        vectorFila.add(curso.getNombreCurso());
                        modelo.addRow(vectorFila);
                        vectorFila = new Vector(3);
                }
                this.tablaCursos.setModel(modelo);
        }
        
        if ((modo == VisualizadorHorarioObjeto.EDICION) && (objetoQueVerHorario.getClass() == Curso.class))
        {       this.dialogoEdicionCursoPadre = (DialogoEdicionCurso)parent;
                for (Hora hora : ((Curso)objetoQueVerHorario).getHorasAsigArrayList())
                {       this.listaHorasSeleccionadas.add(hora);
                        this.listModelHoras.addElement(hora);
                }
        }
        if ((modo == VisualizadorHorarioObjeto.VISUALIZACION) && (objetoQueVerHorario.getClass() == Curso.class))
        {       this.botonCerrarHorario.setAlignmentY(TOP_ALIGNMENT);
                this.setSize(782, 300);
        }

        Hora horaTemp;

        //dibujo las horas en las celdas de la tabla
        try
        {   for(i = 1; i < 7; i++)
            {       for (j = 0; j < 9; j++)
                    {       horaTemp = new Hora((i-1)*9 + j+1);
                            if (horasAMostrar.contains(horaTemp))
                            {       if (this.claseDelObjeto == Profesor.class)
                                        this.horarioMostrado.setValueAt("Disponible", j, i);
                                    if ((this.claseDelObjeto == Curso.class) && (this.modo == VisualizadorHorarioObjeto.VISUALIZACION))
                                        this.horarioMostrado.setValueAt("Asignado", j, i);
                                    if (this.claseDelObjeto == Semestre.class)
                                        this.horarioMostrado.setValueAt("Asignado", j, i);
                                    
                            }
                            else
                            {       this.horarioMostrado.setValueAt("", j, i);
                            }
                            if (this.horasDispDelProfe.contains(horaTemp))
                                    this.horarioMostrado.setValueAt("Disponible", j, i);
                            if (this.horasOcupadasDelProfe.contains(horaTemp))
                            {       this.horarioMostrado.setValueAt("Ocupada", j, i);

                            }
                    }
            }
        }
        catch (HourOutOfRangeException e)
        {
            System.out.println("Error al mostrar la hora en la tabla");
        }
        
    }

    /** This method is called from within the constructor to
     * initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is
     * always regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        PanelVisualizadorHorario = new javax.swing.JPanel();
        panelHorario = new javax.swing.JScrollPane();
        horarioMostrado = new javax.swing.JTable();
        labelCursos = new javax.swing.JLabel();
        panelCursos = new javax.swing.JScrollPane();
        tablaCursos = new javax.swing.JTable();
        jLabel2 = new javax.swing.JLabel();
        botonCerrarHorario = new javax.swing.JButton();
        labelClaseObjeto = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        labelNombreObjeto = new javax.swing.JLabel();
        scrollPanelHorasElegidas = new javax.swing.JScrollPane();
        JListHorasSeleccionadas = new javax.swing.JList();
        labelHoras = new javax.swing.JLabel();
        labelElegidas = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setTitle("Horario");

        horarioMostrado.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {"1.- 08:00-09:30", null, null, null, null, null, null},
                {"2.- 09:40-11:10", null, null, null, null, null, null},
                {"3.- 11:20-12:50", null, null, null, null, null, null},
                {"4.- 13:50-15:20", null, null, null, null, null, null},
                {"5.- 15:30-17:00", null, null, null, null, null, null},
                {"6.- 17:10-18:40", null, null, null, null, null, null},
                {"7.- 19:00-20:10", null, null, null, null, null, null},
                {"8.- 20:20-22:00", null, null, null, null, null, null},
                {"9.- 22:00-23:00", null, null, null, null, null, null}
            },
            new String [] {
                "Hora", "Lunes", "Martes", "Miercoles", "Jueves", "Viernes", "Sabado"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        horarioMostrado.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                horarioMostradoMouseClicked(evt);
            }
        });
        panelHorario.setViewportView(horarioMostrado);

        labelCursos.setFont(new java.awt.Font("Dialog", 1, 14));
        labelCursos.setText("Cursos");

        tablaCursos.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Curso", "Asignatura"
            }
        ));
        panelCursos.setViewportView(tablaCursos);

        jLabel2.setText("Horario");

        botonCerrarHorario.setText("Cerrar");
        botonCerrarHorario.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                botonCerrarHorarioActionPerformed(evt);
            }
        });

        labelClaseObjeto.setText("tipoObjeto");

        jLabel3.setText(":");

        labelNombreObjeto.setText("nombreObjeto");

        javax.swing.GroupLayout PanelVisualizadorHorarioLayout = new javax.swing.GroupLayout(PanelVisualizadorHorario);
        PanelVisualizadorHorario.setLayout(PanelVisualizadorHorarioLayout);
        PanelVisualizadorHorarioLayout.setHorizontalGroup(
            PanelVisualizadorHorarioLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(PanelVisualizadorHorarioLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(PanelVisualizadorHorarioLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(PanelVisualizadorHorarioLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(PanelVisualizadorHorarioLayout.createSequentialGroup()
                            .addGroup(PanelVisualizadorHorarioLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                .addComponent(labelCursos)
                                .addComponent(panelHorario, javax.swing.GroupLayout.DEFAULT_SIZE, 758, Short.MAX_VALUE)
                                .addComponent(panelCursos, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 758, Short.MAX_VALUE)
                                .addGroup(PanelVisualizadorHorarioLayout.createSequentialGroup()
                                    .addGap(27, 27, 27)
                                    .addComponent(labelClaseObjeto)
                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                    .addComponent(jLabel3)
                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                    .addComponent(labelNombreObjeto)))
                            .addContainerGap())
                        .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, PanelVisualizadorHorarioLayout.createSequentialGroup()
                            .addComponent(jLabel2)
                            .addGap(349, 349, 349)))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, PanelVisualizadorHorarioLayout.createSequentialGroup()
                        .addComponent(botonCerrarHorario)
                        .addGap(348, 348, 348))))
        );
        PanelVisualizadorHorarioLayout.setVerticalGroup(
            PanelVisualizadorHorarioLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(PanelVisualizadorHorarioLayout.createSequentialGroup()
                .addGroup(PanelVisualizadorHorarioLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(PanelVisualizadorHorarioLayout.createSequentialGroup()
                        .addGap(28, 28, 28)
                        .addGroup(PanelVisualizadorHorarioLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel3)
                            .addComponent(labelNombreObjeto)
                            .addComponent(labelClaseObjeto)))
                    .addGroup(PanelVisualizadorHorarioLayout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(jLabel2)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(panelHorario, javax.swing.GroupLayout.PREFERRED_SIZE, 163, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(labelCursos)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(panelCursos, javax.swing.GroupLayout.PREFERRED_SIZE, 147, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(botonCerrarHorario)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        JListHorasSeleccionadas.setEnabled(false);
        JListHorasSeleccionadas.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                JListHorasSeleccionadasKeyPressed(evt);
            }
        });
        scrollPanelHorasElegidas.setViewportView(JListHorasSeleccionadas);

        labelHoras.setFont(new java.awt.Font("Dialog", 1, 10)); // NOI18N
        labelHoras.setText("Horas");

        labelElegidas.setFont(new java.awt.Font("Dialog", 1, 10)); // NOI18N
        labelElegidas.setText("elegidas:");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(PanelVisualizadorHorario, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(labelElegidas)
                    .addComponent(labelHoras)
                    .addComponent(scrollPanelHorasElegidas, javax.swing.GroupLayout.PREFERRED_SIZE, 52, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(15, 15, 15)
                .addComponent(labelHoras)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(labelElegidas)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(scrollPanelHorasElegidas, javax.swing.GroupLayout.PREFERRED_SIZE, 155, javax.swing.GroupLayout.PREFERRED_SIZE))
            .addComponent(PanelVisualizadorHorario, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void botonCerrarHorarioActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_botonCerrarHorarioActionPerformed
        //Al presionar el boton "cerrar" hago invisible la ventana
        if (this.modo == VisualizadorHorarioObjeto.EDICION)
        {       dialogoEdicionCursoPadre.asignarHorasElegidas(this.listaHorasSeleccionadas);
        }
        this.setVisible(false);
    }//GEN-LAST:event_botonCerrarHorarioActionPerformed

    private void horarioMostradoMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_horarioMostradoMouseClicked
        //Acción a realizar cuando se hace click una celda del horario
        int fila = this.horarioMostrado.rowAtPoint(evt.getPoint());
        int columna = this.horarioMostrado.columnAtPoint(evt.getPoint());
        try
        {       Hora horaTemp = new Hora((columna-1)*9 + fila+1);
                this.listaHorasSeleccionadas.add(horaTemp);
                this.listModelHoras.addElement(horaTemp);
        }
        catch (HourOutOfRangeException HOORE)
        {       System.out.println("No se ha podido seleccionar una hora válida de la tabla");
        }
    }//GEN-LAST:event_horarioMostradoMouseClicked

    private void JListHorasSeleccionadasKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_JListHorasSeleccionadasKeyPressed
        //hago que cuando se presiona la tecla "suprimir" sobre un elemento seleccionado del jlist, este elemento sea eliminado
        Hora horaSeleccionada;
        horaSeleccionada = (Hora)this.JListHorasSeleccionadas.getSelectedValue();
        //Si existe una hora seleccionada en el jlist y la tecla presionada es la tecla "suprimir":
        if ((horaSeleccionada != null) && (evt.getKeyCode() == java.awt.event.KeyEvent.VK_DELETE))
        {       int indiceAborrar = this.JListHorasSeleccionadas.getSelectedIndex();
                //Elimino a esa hora de la lista de horas seleccionadas
                this.listModelHoras.remove(indiceAborrar);
                this.listaHorasSeleccionadas.remove(indiceAborrar);
        }
    }//GEN-LAST:event_JListHorasSeleccionadasKeyPressed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JList JListHorasSeleccionadas;
    private javax.swing.JPanel PanelVisualizadorHorario;
    private javax.swing.JButton botonCerrarHorario;
    private javax.swing.JTable horarioMostrado;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel labelClaseObjeto;
    private javax.swing.JLabel labelCursos;
    private javax.swing.JLabel labelElegidas;
    private javax.swing.JLabel labelHoras;
    private javax.swing.JLabel labelNombreObjeto;
    private javax.swing.JScrollPane panelCursos;
    private javax.swing.JScrollPane panelHorario;
    private javax.swing.JScrollPane scrollPanelHorasElegidas;
    private javax.swing.JTable tablaCursos;
    // End of variables declaration//GEN-END:variables

}
