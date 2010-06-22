/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

/*
 * DialogoFacultadNueva.java
 *
 * Created on 28-05-2010, 03:04:09 PM
 */

package jramos.GUI;
import jramos.tiposDatos.Facultad;
import jramos.ManipuladorListas;
import jramos.excepciones.StringVacioException;
import jramos.excepciones.nombreRepetidoException;

/**
 *
 * @author victor
 */
public class DialogoFacultadNueva extends javax.swing.JDialog {

    private java.awt.Frame ventanaPadre;
    private ManipuladorListas listManager;
    private int nuevaOEdita;
    Facultad facultadAEditar;
    /** Creates new form DialogoFacultadNueva */
    public DialogoFacultadNueva(java.awt.Frame ventanaPadre, boolean modal, ManipuladorListas listManager, Facultad facultadAEditar,int nuevaOEdita) {
        super(ventanaPadre, modal);
        initComponents();
        this.setLocationRelativeTo(ventanaPadre);
        this.ventanaPadre = ventanaPadre;
        this.listManager = listManager;
        this.nuevaOEdita = nuevaOEdita;
        if (nuevaOEdita == VentanaPrincipal.EDITA)
        {       //Si el dialogo se abre en modo edicion, cambio el texto de un boton y el titulo del dialogo.
                this.setTitle("Dialogo de edición de facultad");
                this.botonAceptaAgregarFacultad.setText("aplicar cambios");
                this.facultadAEditar = facultadAEditar;
                this.campoNombreFacultadNueva.setText(facultadAEditar.getNombreFacultad());
                this.textoDescripcionFacultadNueva.setText(facultadAEditar.getDescripcion());
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

        botonAceptaAgregarFacultad = new javax.swing.JButton();
        botonCancelaAgregarFacultad = new javax.swing.JButton();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        campoNombreFacultadNueva = new javax.swing.JTextField();
        jScrollPane1 = new javax.swing.JScrollPane();
        textoDescripcionFacultadNueva = new javax.swing.JTextArea();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);

        botonAceptaAgregarFacultad.setText("Agregar facultad");
        botonAceptaAgregarFacultad.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                botonAceptaAgregarFacultadActionPerformed(evt);
            }
        });

        botonCancelaAgregarFacultad.setText("Cancelar");
        botonCancelaAgregarFacultad.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                botonCancelaAgregarFacultadActionPerformed(evt);
            }
        });

        jLabel1.setText("Nombre facultad: ");

        jLabel2.setFont(new java.awt.Font("Dialog", 1, 10)); // NOI18N
        jLabel2.setText("Breve descripción: (opcional)");

        campoNombreFacultadNueva.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                campoNombreFacultadNuevaActionPerformed(evt);
            }
        });

        textoDescripcionFacultadNueva.setColumns(20);
        textoDescripcionFacultadNueva.setRows(5);
        jScrollPane1.setViewportView(textoDescripcionFacultadNueva);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel2)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addComponent(botonAceptaAgregarFacultad)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(botonCancelaAgregarFacultad, javax.swing.GroupLayout.PREFERRED_SIZE, 130, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 479, Short.MAX_VALUE)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jLabel1)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(campoNombreFacultadNueva, javax.swing.GroupLayout.DEFAULT_SIZE, 341, Short.MAX_VALUE)))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel1)
                    .addComponent(campoNombreFacultadNueva, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addComponent(jLabel2)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 138, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(botonCancelaAgregarFacultad)
                    .addComponent(botonAceptaAgregarFacultad))
                .addContainerGap())
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void campoNombreFacultadNuevaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_campoNombreFacultadNuevaActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_campoNombreFacultadNuevaActionPerformed

    private void botonAceptaAgregarFacultadActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_botonAceptaAgregarFacultadActionPerformed
        // Accion a realizar cuando se aprieta el boton "agregar facultad"
        //Si lo que se desea es crear una facultad Nueva:
        if (this.nuevaOEdita == VentanaPrincipal.NUEVA)
        {       try
                {       //Creo una facultad nueva
                        listManager.agregaFacultad(campoNombreFacultadNueva.getText(), textoDescripcionFacultadNueva.getText());
                }
                catch (nombreRepetidoException nombreFacultadRepetido)
                {       DialogoError dialogoError = new DialogoError(ventanaPadre, rootPaneCheckingEnabled, "El nombre de facultad ya existe.", "vuelva a escribir otro nombre para la facultad");
                        dialogoError.setVisible(true);
                        dialogoError = null;
                        return ;
                }
                catch (StringVacioException nombreFacultadVacio)
                {       DialogoError dialogoError = new DialogoError(ventanaPadre, rootPaneCheckingEnabled, "No hay un nombre de facultad escrito", "Debe escribir un nombre de facultad");
                        dialogoError.setVisible(true);
                        dialogoError = null;
                        return ;
                }
                ((VentanaPrincipal)this.ventanaPadre).actualizaJListListaFacultades();
        }
        //Si lo que se desea es editar una facultad:
        else
        {       try
                {       this.listManager.editarFacultad(facultadAEditar, this.campoNombreFacultadNueva.getText(), this.textoDescripcionFacultadNueva.getText());
                }
                catch (StringVacioException nombreFacultadVacio)
                {       DialogoError dialogoError = new DialogoError(ventanaPadre, rootPaneCheckingEnabled, "No hay un nombre de facultad escrito", "Debe escribir un nombre de facultad");
                        dialogoError.setVisible(true);
                        dialogoError = null;
                        return ;
                }
                catch (nombreRepetidoException nombreFacultadVacio)
                {       DialogoError dialogoError = new DialogoError(ventanaPadre, rootPaneCheckingEnabled, "El nuevo nombre de facultad introducido ya existe.", "vuelva a escribir otro nombre para la facultad");
                        dialogoError.setVisible(true);
                        dialogoError = null;
                        return ;
                }
                //Actualizo las listas que se muestran en la GUI
                ((VentanaPrincipal)this.ventanaPadre).actualizaJListListaFacultades();
                ((VentanaPrincipal)this.ventanaPadre).actualizaJListListaCarreras();
        }
        this.setVisible(false);

    }//GEN-LAST:event_botonAceptaAgregarFacultadActionPerformed

    private void botonCancelaAgregarFacultadActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_botonCancelaAgregarFacultadActionPerformed
        // TODO add your handling code here:
        this.setVisible(false);
    }//GEN-LAST:event_botonCancelaAgregarFacultadActionPerformed

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton botonAceptaAgregarFacultad;
    private javax.swing.JButton botonCancelaAgregarFacultad;
    private javax.swing.JTextField campoNombreFacultadNueva;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTextArea textoDescripcionFacultadNueva;
    // End of variables declaration//GEN-END:variables

}
