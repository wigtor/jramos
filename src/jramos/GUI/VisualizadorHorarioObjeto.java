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
import jramos.tiposDatos.Carrera;
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
        this.labelNivel.setVisible(false);
        this.selectorSemestre.setVisible(false);
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
                this.setSize(800, 470);
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

                //muestro los cursos que tenga asignados el profesor en la tabla de cursos
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
        
        if (objetoQueVerHorario.getClass().equals(Carrera.class))
        {       this.labelNivel.setVisible(true);
                this.selectorSemestre.setVisible(true);
                this.selectorSemestre.removeAllItems();
                for (Semestre semestre : ((Carrera)objetoQueVerHorario).getListaSemestres())
                        this.selectorSemestre.addItem(semestre);
                this.labelClaseObjeto.setText("Carrera");
                this.labelNombreObjeto.setText(((Carrera)objetoQueVerHorario).getNombreCarrera());

                /*
                //horasAMostrar = ((Semestre)objetoQueVerHorario).getH;
                //muestro los cursos que tenga asignados el profesor en la tabla de cursos
                DefaultTableModel modelo = new DefaultTableModel(new Object [][] {},new String [] {"Curso", "Asignatura"});
                Vector vectorFila = new Vector(3);
                for (Curso curso :((Semestre)objetoQueVerHorario).getCursosArrayList())
                {       vectorFila.add(curso.getCodigoCurso()+" - " +curso.getSeccion());
                        vectorFila.add(curso.getNombreCurso());
                        modelo.addRow(vectorFila);
                        vectorFila = new Vector(3);
                }
                this.tablaCursos.setModel(modelo);
                */
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
                this.setSize(800, 300);
        }
        this.dibujaHhorario();
    }

    private void dibujaHhorario()
    {   this.horarioMostrado.removeAll();
        Hora horaTemp;
        int i, j;
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
                                    if (this.claseDelObjeto == Carrera.class)
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

        labelElegidas = new javax.swing.JLabel();
        labelHoras = new javax.swing.JLabel();
        scrollPanelHorasElegidas = new javax.swing.JScrollPane();
        JListHorasSeleccionadas = new javax.swing.JList();
        selectorSemestre = new javax.swing.JComboBox();
        labelNivel = new javax.swing.JLabel();
        panelHorario = new javax.swing.JScrollPane();
        horarioMostrado = new javax.swing.JTable();
        labelNombreObjeto = new javax.swing.JLabel();
        labelClaseObjeto = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        panelCursos = new javax.swing.JScrollPane();
        tablaCursos = new javax.swing.JTable();
        botonCerrarHorario = new javax.swing.JButton();
        jSeparator1 = new javax.swing.JSeparator();
        labelCursos = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setTitle("Horario");

        labelElegidas.setFont(new java.awt.Font("Dialog", 1, 10)); // NOI18N
        labelElegidas.setText("elegidas:");

        labelHoras.setFont(new java.awt.Font("Dialog", 1, 10)); // NOI18N
        labelHoras.setText("Horas");

        JListHorasSeleccionadas.setEnabled(false);
        JListHorasSeleccionadas.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                JListHorasSeleccionadasKeyPressed(evt);
            }
        });
        scrollPanelHorasElegidas.setViewportView(JListHorasSeleccionadas);

        selectorSemestre.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Semestre 1" }));
        selectorSemestre.setToolTipText("Seleccione el semestre del curso al que desea ver su horario");
        selectorSemestre.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                selectorSemestreItemStateChanged(evt);
            }
        });

        labelNivel.setText("Nivel:");

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

        labelNombreObjeto.setText("nombreObjeto");

        labelClaseObjeto.setText("tipoObjeto");

        jLabel3.setText(":");

        jLabel2.setText("Horario");

        tablaCursos.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Curso", "Asignatura"
            }
        ));
        panelCursos.setViewportView(tablaCursos);

        botonCerrarHorario.setText("Cerrar");
        botonCerrarHorario.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                botonCerrarHorarioActionPerformed(evt);
            }
        });

        jSeparator1.setOrientation(javax.swing.SwingConstants.VERTICAL);

        labelCursos.setText("Cursos:");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(labelCursos)
                .addContainerGap(761, Short.MAX_VALUE))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(panelCursos, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, 733, Short.MAX_VALUE)
                    .addGroup(javax.swing.GroupLayout.Alignment.LEADING, layout.createSequentialGroup()
                        .addComponent(labelClaseObjeto)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jLabel3)
                        .addGap(1, 1, 1)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                                .addComponent(jLabel2)
                                .addGap(349, 349, 349))
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                                .addGap(5, 5, 5)
                                .addComponent(labelNombreObjeto)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jSeparator1, javax.swing.GroupLayout.DEFAULT_SIZE, 355, Short.MAX_VALUE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(labelNivel)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(selectorSemestre, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))))
                    .addComponent(panelHorario, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, 733, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(labelElegidas)
                    .addComponent(scrollPanelHorasElegidas, javax.swing.GroupLayout.PREFERRED_SIZE, 52, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(labelHoras))
                .addGap(18, 18, 18))
            .addGroup(layout.createSequentialGroup()
                .addGap(343, 343, 343)
                .addComponent(botonCerrarHorario)
                .addContainerGap(405, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(labelHoras)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(labelElegidas)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(scrollPanelHorasElegidas, javax.swing.GroupLayout.PREFERRED_SIZE, 155, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addGroup(layout.createSequentialGroup()
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                    .addGroup(layout.createSequentialGroup()
                                        .addComponent(jLabel2)
                                        .addGap(18, 18, 18)
                                        .addComponent(jSeparator1))
                                    .addGroup(layout.createSequentialGroup()
                                        .addGap(28, 28, 28)
                                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                            .addComponent(labelClaseObjeto)
                                            .addComponent(jLabel3)
                                            .addComponent(labelNombreObjeto))))
                                .addGap(7, 7, 7))
                            .addGroup(layout.createSequentialGroup()
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(selectorSemestre, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(labelNivel))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(panelHorario, javax.swing.GroupLayout.PREFERRED_SIZE, 163, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(18, 18, 18)
                .addComponent(labelCursos)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(panelCursos, javax.swing.GroupLayout.PREFERRED_SIZE, 141, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(botonCerrarHorario)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
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
                if (!this.listaHorasSeleccionadas.contains(horaTemp))
                {       this.listaHorasSeleccionadas.add(horaTemp);
                        this.listModelHoras.addElement(horaTemp);
                }
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

    private void selectorSemestreItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_selectorSemestreItemStateChanged
        // Acción a realizar cuando se selecciona un semestre en el visualizador de horarios
        if (this.selectorSemestre.getSelectedItem() != null)
        {       //agrego las horas de ese semestre al ArrayList horasAMostrar
                this.horasAMostrar = new ArrayList();
                for (Curso curso : ((Semestre)this.selectorSemestre.getSelectedItem()).getCursosArrayList())
                {       this.horasAMostrar.addAll(curso.getHorasAsigArrayList());
                }
                //dibujo el horario
                this.dibujaHhorario();
                //dibujo la tabla con los cursos asignados y horarios
                DefaultTableModel modelo = new DefaultTableModel(new Object [][] {},new String [] {"Curso", "Asignatura"});
                Vector vectorFila = new Vector(3);
                for (Curso curso :((Semestre)this.selectorSemestre.getSelectedItem()).getCursosArrayList())
                {       vectorFila.add(curso.getCodigoCurso()+" - " +curso.getSeccion() + " - "+ curso.getHorario());
                        vectorFila.add(curso.getNombreCurso());
                        modelo.addRow(vectorFila);
                        vectorFila = new Vector(3);
                }
                this.tablaCursos.setModel(modelo);
        }

    }//GEN-LAST:event_selectorSemestreItemStateChanged


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JList JListHorasSeleccionadas;
    private javax.swing.JButton botonCerrarHorario;
    private javax.swing.JTable horarioMostrado;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JSeparator jSeparator1;
    private javax.swing.JLabel labelClaseObjeto;
    private javax.swing.JLabel labelCursos;
    private javax.swing.JLabel labelElegidas;
    private javax.swing.JLabel labelHoras;
    private javax.swing.JLabel labelNivel;
    private javax.swing.JLabel labelNombreObjeto;
    private javax.swing.JScrollPane panelCursos;
    private javax.swing.JScrollPane panelHorario;
    private javax.swing.JScrollPane scrollPanelHorasElegidas;
    private javax.swing.JComboBox selectorSemestre;
    private javax.swing.JTable tablaCursos;
    // End of variables declaration//GEN-END:variables

}
