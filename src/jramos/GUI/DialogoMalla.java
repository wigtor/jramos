/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

/*
 * DialogoMalla.java
 *
 * Created on 12-06-2010, 11:20:01 AM
 */

package jramos.GUI;

import java.util.ArrayList;
import java.util.Vector;
import javax.swing.table.TableColumn;
import jramos.tiposDatos.Carrera;
import jramos.tiposDatos.Curso;
import jramos.tiposDatos.Semestre;

/**
 *
 * @author victor
 */
public class DialogoMalla extends javax.swing.JDialog {
    private Carrera carreraAVerMalla;

    /** Creates new form DialogoMalla */
    public DialogoMalla(java.awt.Frame parent, boolean modal, Carrera carreraAVerMalla) {
        super(parent, modal);
        initComponents();
        this.setLocationRelativeTo(parent);
        this.carreraAVerMalla = carreraAVerMalla;
        this.labelTitulo.setText(this.labelTitulo.getText()+carreraAVerMalla.getNombreCarrera());
        javax.swing.table.DefaultTableModel modeloTabla = new javax.swing.table.DefaultTableModel();
        Vector vectorCursosEnSemestre;
        for (Semestre semestreEnCarrera : carreraAVerMalla.getListaSemestres())
        {       vectorCursosEnSemestre = new Vector(3, 1);
                //Agrego los cursos al vector, excepto los cursos iguales de diferente sección
                ArrayList<Integer> listaCodCursosDelSemestre = semestreEnCarrera.getCodigoCursos();
                for (Integer integer : listaCodCursosDelSemestre)
                {   for (Curso curso : semestreEnCarrera.getCursosArrayList())
                    {       if (curso.getCodigoCurso() == integer.intValue())
                            {       vectorCursosEnSemestre.add(curso);
                                    break;
                            }
                    }
                }
                modeloTabla.addColumn("Semestre N°"+semestreEnCarrera.getNumeroSemestre(), vectorCursosEnSemestre);
        }
        this.tablaMalla.setModel(modeloTabla);
        TableColumn columna;
        for (int i = 0; i < modeloTabla.getColumnCount(); i++) {
                columna = tablaMalla.getColumnModel().getColumn(i);
                columna.setPreferredWidth(140);
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

        labelTitulo = new javax.swing.JLabel();
        botonCerrarMalla = new javax.swing.JButton();
        jScrollPane1 = new javax.swing.JScrollPane();
        tablaMalla = new javax.swing.JTable();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setTitle("Malla Curricular");

        labelTitulo.setFont(new java.awt.Font("Dialog", 1, 16)); // NOI18N
        labelTitulo.setText("Malla Curricular: ");

        botonCerrarMalla.setFont(new java.awt.Font("Dialog", 1, 14)); // NOI18N
        botonCerrarMalla.setText("Cerrar");
        botonCerrarMalla.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                botonCerrarMallaActionPerformed(evt);
            }
        });

        tablaMalla.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {

            }
        ));
        tablaMalla.setAutoResizeMode(javax.swing.JTable.AUTO_RESIZE_OFF);
        tablaMalla.setRowHeight(40);
        jScrollPane1.setViewportView(tablaMalla);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addContainerGap()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 725, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(labelTitulo, javax.swing.GroupLayout.PREFERRED_SIZE, 632, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(92, 92, 92))))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(321, 321, 321)
                        .addComponent(botonCerrarMalla, javax.swing.GroupLayout.PREFERRED_SIZE, 96, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(labelTitulo, javax.swing.GroupLayout.DEFAULT_SIZE, 28, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 264, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(botonCerrarMalla, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void botonCerrarMallaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_botonCerrarMallaActionPerformed
        // Acción a realizar cuando se presiona el boton "Cerrar"
        this.setVisible(false);
    }//GEN-LAST:event_botonCerrarMallaActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton botonCerrarMalla;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JLabel labelTitulo;
    private javax.swing.JTable tablaMalla;
    // End of variables declaration//GEN-END:variables

}
