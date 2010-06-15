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
        this.profesorAEditar = profesorAEditar;
        this.listManager = listManager;
        this.ventanaPadre = ventanaPadre;
        this.setTitle(this.getTitle()+ profesorAEditar.getNombreProfesor());
        this.campoNombreProfesorNuevo.setText(profesorAEditar.getNombreProfesor());
        this.campoRutProfesor.setText((new Integer(profesorAEditar.getRutProfesor())).toString());
        this.campoRamosQueDicta.setText(profesorAEditar.getCodCursosQueImparte());
        this.campoHorasDisponibles.setText(profesorAEditar.getHorasDisponibles());
        
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

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setTitle("Dialogo de edición de un profesor - ");

        jLabel14.setFont(new java.awt.Font("Dialog", 1, 10)); // NOI18N
        jLabel14.setText("Editar la información de un profesor: ");

        jLabel5.setText("Nombre Profesor:");

        jLabel6.setText("RUT:");

        jLabel7.setText("Ramos que puede dictar:");

        jLabel8.setText("Horas disponibles:");

        campoHorasDisponibles.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                campoHorasDisponiblesActionPerformed(evt);
            }
        });

        botonAplicarCambiosProfesor.setText("Aplicar cambios");
        botonAplicarCambiosProfesor.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                botonAplicarCambiosProfesorActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap(108, Short.MAX_VALUE)
                .addComponent(botonAplicarCambiosProfesor, javax.swing.GroupLayout.PREFERRED_SIZE, 239, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(87, 87, 87))
            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(layout.createSequentialGroup()
                    .addContainerGap()
                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(layout.createSequentialGroup()
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
                                .addComponent(campoHorasDisponibles, javax.swing.GroupLayout.DEFAULT_SIZE, 206, Short.MAX_VALUE))
                            .addGap(27, 27, 27))
                        .addComponent(jLabel14, javax.swing.GroupLayout.PREFERRED_SIZE, 227, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addContainerGap()))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap(233, Short.MAX_VALUE)
                .addComponent(botonAplicarCambiosProfesor, javax.swing.GroupLayout.PREFERRED_SIZE, 38, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(29, 29, 29))
            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(layout.createSequentialGroup()
                    .addGap(72, 72, 72)
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
                    .addContainerGap(117, Short.MAX_VALUE)))
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
        } catch (nombreRepetidoException nombreRepetido) {       //abro nuevo dialogo de error.
            DialogoError dialogoError = new DialogoError(this.ventanaPadre, rootPaneCheckingEnabled, "El nombre del profesor ya existe.", "vuelva a escribir otro nombre para el profesor");
            dialogoError.setVisible(true);
            dialogoError = null;
            return ;
        } catch (StringVacioException nombreVacio) {       //abro nuevo dialogo de error.
            DialogoError dialogoError = new DialogoError(this.ventanaPadre, rootPaneCheckingEnabled, "No hay un nombre de profesor escrito", "Debe escribir un nombre de profesor");
            dialogoError.setVisible(true);
            dialogoError = null;
            return ;
        } catch (NumberFormatException NFE) {
            if (NFE.getMessage().equals("rut")) {       //Abro nuevo dialogo de error.
                DialogoError dialogoError = new DialogoError(this.ventanaPadre, rootPaneCheckingEnabled, "El rut introducido no es válido", "vuelva a escribir el rut del profesor");
                dialogoError.setVisible(true);
                dialogoError = null;
                return ;
            }
            if (NFE.getMessage().equals("codCurso")) {
                DialogoError dialogoError = new DialogoError(this.ventanaPadre, rootPaneCheckingEnabled, "Los codigos de curso introducidos no son válidos", "vuelva a escribir los cursos disponibles del profesor");
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
        
    }//GEN-LAST:event_botonAplicarCambiosProfesorActionPerformed

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton botonAplicarCambiosProfesor;
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
