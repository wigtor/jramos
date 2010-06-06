/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

/*
 * DialogoError.java
 *
 * Created on 05-06-2010, 05:11:07 PM
 */

package jramos;

import javax.swing.SwingConstants;

/**
 *
 * @author victor
 */
public class DialogoError extends javax.swing.JDialog {

    /** Creates new form DialogoError */
    public DialogoError(java.awt.Frame parent, boolean modal, String detallesError, String solucionError) {
        super(parent, modal);
        initComponents();
        this.labelSolucionError.setHorizontalAlignment(SwingConstants.CENTER);
        this.labelSolucionError.setText(solucionError);
        this.labelDetallesError.setHorizontalAlignment(SwingConstants.CENTER);
        this.labelDetallesError.setText(detallesError);
    }

    /** This method is called from within the constructor to
     * initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is
     * always regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jLabel1 = new javax.swing.JLabel();
        botonAceptarDialogoError = new javax.swing.JButton();
        labelDetallesError = new javax.swing.JLabel();
        labelSolucionError = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setTitle("Error");

        jLabel1.setText("ERROR:");

        botonAceptarDialogoError.setText("Aceptar");
        botonAceptarDialogoError.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                botonAceptarDialogoErrorActionPerformed(evt);
            }
        });

        labelDetallesError.setText("descripción del error");

        labelSolucionError.setText("solucion del error");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(labelDetallesError, javax.swing.GroupLayout.DEFAULT_SIZE, 451, Short.MAX_VALUE))
                    .addGroup(layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(labelSolucionError, javax.swing.GroupLayout.DEFAULT_SIZE, 451, Short.MAX_VALUE))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(193, 193, 193)
                        .addComponent(botonAceptarDialogoError))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(203, 203, 203)
                        .addComponent(jLabel1)))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 14, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(2, 2, 2)
                .addComponent(labelDetallesError, javax.swing.GroupLayout.PREFERRED_SIZE, 26, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(labelSolucionError, javax.swing.GroupLayout.DEFAULT_SIZE, 22, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(botonAceptarDialogoError)
                .addGap(27, 27, 27))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void botonAceptarDialogoErrorActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_botonAceptarDialogoErrorActionPerformed
        //cierro la ventana cuando se presiona el boton "aceptar"
        this.setVisible(false);
    }//GEN-LAST:event_botonAceptarDialogoErrorActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton botonAceptarDialogoError;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel labelDetallesError;
    private javax.swing.JLabel labelSolucionError;
    // End of variables declaration//GEN-END:variables

}
