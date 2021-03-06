/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

/*
 * DialogoEdicionProfesor.java
 *
 * Created on 08-06-2010, 08:01:48 PM
 */

package jramos.GUI;

import jramos.ManipuladorListas;
import jramos.excepciones.HourOutOfRangeException;
import jramos.excepciones.StringVacioException;
import jramos.excepciones.nombreRepetidoException;
import jramos.tiposDatos.Hora;
import jramos.tiposDatos.Profesor;

/**
 *
 * @author victor
 */
public class DialogoEdicionProfesor extends javax.swing.JDialog {
    private java.awt.Frame ventanaPadre;
    private Profesor profesorAEditar;
    private ManipuladorListas listManager;
    /** Creates new form DialogoEdicionProfesor */
    public DialogoEdicionProfesor(java.awt.Frame ventanaPadre, boolean modal, ManipuladorListas listManager, Profesor profesorAEditar) {
        super(ventanaPadre, modal);
        initComponents();
        this.setLocationRelativeTo(ventanaPadre);
        this.profesorAEditar = profesorAEditar;
        this.listManager = listManager;
        this.ventanaPadre = ventanaPadre;
        this.setTitle(this.getTitle()+ profesorAEditar.getNombreProfesor());
        this.campoNombreProfesorNuevo.setText(profesorAEditar.getNombreProfesor());
        this.campoRutProfesor.setText((new Integer(profesorAEditar.getRutProfesor())).toString());
        for (Integer codCurso : profesorAEditar.getCodCursosQueImparteArrayList())
            this.campoRamosQueDicta.setText(this.campoRamosQueDicta.getText() + codCurso.intValue() + " ");
        for (Hora hora : profesorAEditar.getHorasDispArrayList())
            this.campoHorasDisponibles.setText(this.campoHorasDisponibles.getText() + hora + " ");
        
    }

    /** This method is called from within the constructor to
     * initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is
     * always regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jLabel14 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        jLabel8 = new javax.swing.JLabel();
        campoHorasDisponibles = new javax.swing.JTextField();
        botonAplicarCambiosProfesor = new javax.swing.JButton();
        campoRamosQueDicta = new javax.swing.JTextField();
        campoRutProfesor = new javax.swing.JTextField();
        campoNombreProfesorNuevo = new javax.swing.JTextField();
        botonCancelar = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setTitle("Dialogo de edición de un profesor - ");
        setResizable(false);

        jLabel14.setFont(new java.awt.Font("Dialog", 1, 10));
        jLabel14.setText("Editar la información de un profesor: ");

        jLabel5.setText("Nombre profesor:");

        jLabel6.setText("RUT:");

        jLabel7.setText("Ramos que puede dictar:");

        jLabel8.setText("Horas disponibles:");

        campoHorasDisponibles.setToolTipText("Escriba aquí las horas disponibles del profesor separadas por un espacio");
        campoHorasDisponibles.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                campoHorasDisponiblesActionPerformed(evt);
            }
        });

        botonAplicarCambiosProfesor.setText("Aplicar cambios");
        botonAplicarCambiosProfesor.setToolTipText("Haga click aquí para aplicar los cambios al profesor");
        botonAplicarCambiosProfesor.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                botonAplicarCambiosProfesorActionPerformed(evt);
            }
        });

        campoRamosQueDicta.setToolTipText("Escriba aquí los códigos de curso que puede dictar el profesor separados por un espacio");

        campoRutProfesor.setEditable(false);

        campoNombreProfesorNuevo.setToolTipText("Escriba aquí el nombre del profesor");

        botonCancelar.setText("Cancelar");
        botonCancelar.setToolTipText("Haga click aquí para descartar los cambios y cerrar este dialogo");
        botonCancelar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                botonCancelarActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(botonAplicarCambiosProfesor)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(botonCancelar))
                    .addGroup(javax.swing.GroupLayout.Alignment.LEADING, layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel5)
                            .addComponent(jLabel6)
                            .addComponent(jLabel7)
                            .addComponent(jLabel8))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(campoRutProfesor, javax.swing.GroupLayout.DEFAULT_SIZE, 206, Short.MAX_VALUE)
                            .addComponent(campoNombreProfesorNuevo, javax.swing.GroupLayout.DEFAULT_SIZE, 206, Short.MAX_VALUE)
                            .addComponent(campoRamosQueDicta, javax.swing.GroupLayout.DEFAULT_SIZE, 206, Short.MAX_VALUE)
                            .addComponent(campoHorasDisponibles, javax.swing.GroupLayout.DEFAULT_SIZE, 206, Short.MAX_VALUE)))
                    .addComponent(jLabel14, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.PREFERRED_SIZE, 227, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(27, 27, 27))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel14)
                .addGap(9, 9, 9)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel5)
                    .addComponent(campoNombreProfesorNuevo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel6)
                    .addComponent(campoRutProfesor, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel7)
                    .addComponent(campoRamosQueDicta, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel8)
                    .addComponent(campoHorasDisponibles, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(botonCancelar)
                    .addComponent(botonAplicarCambiosProfesor))
                .addContainerGap())
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void campoHorasDisponiblesActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_campoHorasDisponiblesActionPerformed
        // TODO add your handling code here:
}//GEN-LAST:event_campoHorasDisponiblesActionPerformed

    private void botonAplicarCambiosProfesorActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_botonAplicarCambiosProfesorActionPerformed
        // Acción a realizar cuando se presiona el boton "agregar profesor"
        int i, tamLista;
        //Intento agregar un profesor nuevo.
        try {
            this.listManager.editaProfesor(this.profesorAEditar, this.campoNombreProfesorNuevo.getText(), this.campoRamosQueDicta.getText(), this.campoHorasDisponibles.getText());
        } catch (nombreRepetidoException nombreRepetido) {
            //abro nuevo dialogo de error.
            DialogoError dialogoError = new DialogoError(this.ventanaPadre, rootPaneCheckingEnabled, "El nombre del profesor ya existe.", "vuelva a escribir otro nombre para el profesor");
            dialogoError.setVisible(true);
            dialogoError = null;
            return ;
        } catch (StringVacioException nombreVacio) {
            //abro nuevo dialogo de error.
            DialogoError dialogoError = new DialogoError(this.ventanaPadre, rootPaneCheckingEnabled, "No hay un nombre de profesor escrito", "Debe escribir un nombre de profesor");
            dialogoError.setVisible(true);
            dialogoError = null;
            return ;
        } catch (NumberFormatException NFE) {
            if (NFE.getMessage().equals("codCurso")) {
                DialogoError dialogoError = new DialogoError(this.ventanaPadre, rootPaneCheckingEnabled, "Los codigos de curso no son válidos o no existen", "vuelva a escribir los cursos disponibles del profesor");
                dialogoError.setVisible(true);
                dialogoError = null;
                return ;
            }
        } catch (HourOutOfRangeException HOORE) {       //Abro dialogo de error.
            DialogoError dialogoError = new DialogoError(this.ventanaPadre, rootPaneCheckingEnabled, "Las horas introducidas no son válidas.", "Revise las horas escritas y vuelva a escribirlas");
            dialogoError.setVisible(true);
            dialogoError = null;
            return ;
        }

        //Actualizo la GUI
        ((VentanaPrincipal)this.ventanaPadre).actualizaJListListaProfes();
        this.setVisible(false);
        
    }//GEN-LAST:event_botonAplicarCambiosProfesorActionPerformed

    private void botonCancelarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_botonCancelarActionPerformed
        // Acción a realizar cuando se presiona el boton "cancelar": solo cierro la ventana
        this.setVisible(false);
    }//GEN-LAST:event_botonCancelarActionPerformed

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton botonAplicarCambiosProfesor;
    private javax.swing.JButton botonCancelar;
    private javax.swing.JTextField campoHorasDisponibles;
    private javax.swing.JTextField campoNombreProfesorNuevo;
    private javax.swing.JTextField campoRamosQueDicta;
    private javax.swing.JTextField campoRutProfesor;
    private javax.swing.JLabel jLabel14;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    // End of variables declaration//GEN-END:variables

}
