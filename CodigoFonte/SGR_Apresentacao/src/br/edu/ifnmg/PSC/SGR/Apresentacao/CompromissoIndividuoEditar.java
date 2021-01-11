/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.edu.ifnmg.PSC.SGR.Apresentacao;

import br.edu.ifnmg.PSC.SGR.Aplicacao.Cliente;
import br.edu.ifnmg.PSC.SGR.Aplicacao.Compromisso;
import br.edu.ifnmg.PSC.SGR.Aplicacao.Fornecedor;
import br.edu.ifnmg.PSC.SGR.Aplicacao.Individuo;
import br.edu.ifnmg.PSC.SGR.Aplicacao.TipoCompromisso;
import br.edu.ifnmg.PSC.SGR.Aplicacao.ViolacaoRegraNegocioException;
import java.awt.Dimension;
import java.beans.PropertyVetoException;
import java.util.HashMap;
import java.util.List;
import java.util.Vector;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.ComboBoxModel;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JOptionPane;

/**
 *
 * @author DONO
 */
public class CompromissoIndividuoEditar extends javax.swing.JInternalFrame {

    private Integer individuo;
    private CompromissoIndividuoBuscar telaMae;
    
    public CompromissoIndividuoEditar(int individuo) {

        initComponents();

        this.individuo=individuo;
            
    }

    public CompromissoIndividuoEditar() {
        initComponents();
    }

    public CompromissoIndividuoBuscar getTelaMae() {
        return telaMae;
    }

    public void setTelaMae(CompromissoIndividuoBuscar telaMae) {
        this.telaMae = telaMae;
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        buttonGroup1 = new javax.swing.ButtonGroup();
        jPanel1 = new javax.swing.JPanel();
        rbtnCliente = new javax.swing.JRadioButton();
        rbtnFornecedor = new javax.swing.JRadioButton();
        cmbxIndividuos = new javax.swing.JComboBox<>();
        jLabel1 = new javax.swing.JLabel();
        btnSalvar = new javax.swing.JButton();

        buttonGroup1.add(rbtnCliente);
        buttonGroup1.add(rbtnFornecedor);

        setTitle("Envolvido");

        rbtnCliente.setSelected(true);
        rbtnCliente.setText("Cliente");
        rbtnCliente.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                rbtnClienteActionPerformed(evt);
            }
        });

        rbtnFornecedor.setText("Fornecedor");
        rbtnFornecedor.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                rbtnFornecedorActionPerformed(evt);
            }
        });

        cmbxIndividuos.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));

        jLabel1.setText("jLabel1");

        btnSalvar.setText("Salvar");
        btnSalvar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnSalvarActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(118, 118, 118)
                .addComponent(btnSalvar)
                .addContainerGap(161, Short.MAX_VALUE))
            .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(jPanel1Layout.createSequentialGroup()
                    .addContainerGap()
                    .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(jPanel1Layout.createSequentialGroup()
                            .addComponent(rbtnCliente)
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                            .addComponent(rbtnFornecedor))
                        .addGroup(jPanel1Layout.createSequentialGroup()
                            .addComponent(jLabel1)
                            .addGap(18, 18, 18)
                            .addComponent(cmbxIndividuos, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addContainerGap(172, Short.MAX_VALUE)))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addContainerGap(83, Short.MAX_VALUE)
                .addComponent(btnSalvar)
                .addContainerGap())
            .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(jPanel1Layout.createSequentialGroup()
                    .addContainerGap()
                    .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(rbtnCliente)
                        .addComponent(rbtnFornecedor))
                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                    .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(jLabel1)
                        .addComponent(cmbxIndividuos, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addContainerGap(56, Short.MAX_VALUE)))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(10, 10, 10)
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btnSalvarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnSalvarActionPerformed
        Individuo selecionado=(Individuo)cmbxIndividuos.getSelectedItem();
        if(!telaMae.compromisso.getListaIndividuos().contains(selecionado.getId()))
            telaMae.compromisso.addIndividuo(selecionado.getId());
        
        JOptionPane.showMessageDialog(rootPane, "Sucesso!");
        try {
            this.setClosed(true);
        } catch (PropertyVetoException ex) {
            Logger.getLogger(TelaEdicao.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_btnSalvarActionPerformed

    private void rbtnClienteActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_rbtnClienteActionPerformed
        try {
            Vector clientes=new Vector();
            for(Cliente s :Repositorios.getClienteRepositorio().Buscar(null))
                clientes.add(s);
            
            ComboBoxModel model = new DefaultComboBoxModel(clientes);
            cmbxIndividuos.setModel(model);
            if(individuo!=null)
                if(clientes.contains(Repositorios.getClienteRepositorio().Abrir(individuo)))
                    cmbxIndividuos.setSelectedItem(Repositorios.getClienteRepositorio().Abrir(individuo));
        } catch (ViolacaoRegraNegocioException ex) {
            Logger.getLogger(CompromissoIndividuoEditar.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_rbtnClienteActionPerformed

    private void rbtnFornecedorActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_rbtnFornecedorActionPerformed
        try {
            Vector fornecedores=new Vector();
            for(Fornecedor s :Repositorios.getFornecedorRepositorio().Buscar(null))
                fornecedores.add(s);
            
            ComboBoxModel model = new DefaultComboBoxModel(fornecedores);
            cmbxIndividuos.setModel(model);
            if(individuo!=null)
                if(fornecedores.contains(Repositorios.getFornecedorRepositorio().Abrir(individuo)))
                    cmbxIndividuos.setSelectedItem(Repositorios.getFornecedorRepositorio().Abrir(individuo));
        } catch (ViolacaoRegraNegocioException ex) {
            Logger.getLogger(CompromissoIndividuoEditar.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_rbtnFornecedorActionPerformed

    public void centraliza() {
        Dimension d = this.getParent().getSize();
        this.setLocation((d.width - this.getSize().width) / 2, (d.height - this.getSize().height) / 2); 
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnSalvar;
    private javax.swing.ButtonGroup buttonGroup1;
    private javax.swing.JComboBox<String> cmbxIndividuos;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JRadioButton rbtnCliente;
    private javax.swing.JRadioButton rbtnFornecedor;
    // End of variables declaration//GEN-END:variables
}
