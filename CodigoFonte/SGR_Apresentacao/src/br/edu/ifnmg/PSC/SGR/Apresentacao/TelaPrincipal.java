/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.edu.ifnmg.PSC.SGR.Apresentacao;

import br.edu.ifnmg.PSC.SGR.Aplicacao.Sessao;
import br.edu.ifnmg.PSC.SGR.Aplicacao.ViolacaoRegraNegocioException;
import java.beans.PropertyVetoException;
import java.net.URL;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;
import net.sf.jasperreports.engine.JRDataSource;
import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;
import net.sf.jasperreports.engine.util.JRLoader;
import net.sf.jasperreports.view.JasperViewer;

/**
 *
 * @author DONO
 */
public class TelaPrincipal extends javax.swing.JFrame {

    /**
     * Creates new form TelaPrincipal
     */
    public TelaPrincipal() {
        if(!Sessao.isLogged()){        
            System.exit(0);
        }

        initComponents();
        this.setLocationRelativeTo(null);
    }
    
    private void exibeRelatorioJasper(String caminho_relatorio, List dados, Map parametros) {

        try {
            // Parâmetros
            if(parametros == null)
                parametros = new HashMap();

            // Pega o caminho do arquivo do relatório
            URL arquivo = getClass().getResource(caminho_relatorio);
            
            // Carrega o relatório na memória
            JasperReport relatorio = (JasperReport) JRLoader.loadObject(arquivo);
            
            // Data Source - Fonte de Dados do Relatório
            JRDataSource fontededados = new JRBeanCollectionDataSource(dados, true);
            
            // Preenche o relatório com os dados fornecidos pelo Data Source
            JasperPrint jasperPrint = JasperFillManager.fillReport(relatorio, parametros, fontededados);
            
            // Visualiza o relatório, isto é, abre uma janela que mostra o relatório
            JasperViewer jrviewer = new JasperViewer(jasperPrint, false);
            
            jrviewer.setVisible(true);
        
        } catch (JRException ex) {
            Logger.getLogger(JasperReport.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

@SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jDesktopPane = new javax.swing.JDesktopPane();
        barMenu = new javax.swing.JMenuBar();
        mnuFornecedor = new javax.swing.JMenu();
        itmFornecedorGerenciar = new javax.swing.JMenuItem();
        mnuCliente = new javax.swing.JMenu();
        itmClienteGerenciar = new javax.swing.JMenuItem();
        itmClienteGerenciar1 = new javax.swing.JMenuItem();
        mnuProduto = new javax.swing.JMenu();
        itmProdutoGerenciar = new javax.swing.JMenuItem();
        mnuCompromisso = new javax.swing.JMenu();
        itmCompromissoGerenciar = new javax.swing.JMenuItem();
        mnuCompromisso1 = new javax.swing.JMenu();
        itmCompromissoGerenciar1 = new javax.swing.JMenuItem();
        mnuProduto1 = new javax.swing.JMenu();
        itmProdutoGerenciar1 = new javax.swing.JMenuItem();
        itmProdutoGerenciar2 = new javax.swing.JMenuItem();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("SGR");
        setMinimumSize(new java.awt.Dimension(691, 449));

        jDesktopPane.setBackground(new java.awt.Color(204, 204, 204));

        javax.swing.GroupLayout jDesktopPaneLayout = new javax.swing.GroupLayout(jDesktopPane);
        jDesktopPane.setLayout(jDesktopPaneLayout);
        jDesktopPaneLayout.setHorizontalGroup(
            jDesktopPaneLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 983, Short.MAX_VALUE)
        );
        jDesktopPaneLayout.setVerticalGroup(
            jDesktopPaneLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 549, Short.MAX_VALUE)
        );

        barMenu.setFont(new java.awt.Font("Segoe UI", 0, 24)); // NOI18N

        mnuFornecedor.setBackground(new java.awt.Color(153, 153, 153));
        mnuFornecedor.setBorderPainted(true);
        mnuFornecedor.setFont(new java.awt.Font("Segoe UI", 0, 16)); // NOI18N
        mnuFornecedor.setLabel(" Fornecedores");
        mnuFornecedor.setPreferredSize(new java.awt.Dimension(110, 24));

        itmFornecedorGerenciar.setFont(new java.awt.Font("Segoe UI", 0, 16)); // NOI18N
        itmFornecedorGerenciar.setText("Gerenciar");
        itmFornecedorGerenciar.setPreferredSize(new java.awt.Dimension(110, 24));
        itmFornecedorGerenciar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                itmFornecedorGerenciarActionPerformed(evt);
            }
        });
        mnuFornecedor.add(itmFornecedorGerenciar);

        barMenu.add(mnuFornecedor);

        mnuCliente.setBackground(new java.awt.Color(153, 153, 153));
        mnuCliente.setBorder(null);
        mnuCliente.setText("    Clientes");
        mnuCliente.setBorderPainted(true);
        mnuCliente.setFont(new java.awt.Font("Segoe UI", 0, 16)); // NOI18N
        mnuCliente.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        mnuCliente.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        mnuCliente.setInheritsPopupMenu(true);
        mnuCliente.setPreferredSize(new java.awt.Dimension(95, 24));

        itmClienteGerenciar.setFont(new java.awt.Font("Segoe UI", 0, 16)); // NOI18N
        itmClienteGerenciar.setText("Gerenciar");
        itmClienteGerenciar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                itmClienteGerenciarActionPerformed(evt);
            }
        });
        mnuCliente.add(itmClienteGerenciar);

        itmClienteGerenciar1.setFont(new java.awt.Font("Segoe UI", 0, 16)); // NOI18N
        itmClienteGerenciar1.setText("Relatorio");
        itmClienteGerenciar1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                itmClienteGerenciar1ActionPerformed(evt);
            }
        });
        mnuCliente.add(itmClienteGerenciar1);

        barMenu.add(mnuCliente);

        mnuProduto.setBackground(new java.awt.Color(153, 153, 153));
        mnuProduto.setText("   Produtos");
        mnuProduto.setBorderPainted(true);
        mnuProduto.setFont(new java.awt.Font("Segoe UI", 0, 16)); // NOI18N
        mnuProduto.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        mnuProduto.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        mnuProduto.setInheritsPopupMenu(true);
        mnuProduto.setPreferredSize(new java.awt.Dimension(95, 24));

        itmProdutoGerenciar.setFont(new java.awt.Font("Segoe UI", 0, 16)); // NOI18N
        itmProdutoGerenciar.setText("Gerenciar");
        itmProdutoGerenciar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                itmProdutoGerenciarActionPerformed(evt);
            }
        });
        mnuProduto.add(itmProdutoGerenciar);

        barMenu.add(mnuProduto);

        mnuCompromisso.setBackground(new java.awt.Color(153, 153, 153));
        mnuCompromisso.setText("   Compromissos");
        mnuCompromisso.setBorderPainted(true);
        mnuCompromisso.setFont(new java.awt.Font("Segoe UI", 0, 16)); // NOI18N
        mnuCompromisso.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        mnuCompromisso.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        mnuCompromisso.setInheritsPopupMenu(true);
        mnuCompromisso.setPreferredSize(new java.awt.Dimension(130, 24));

        itmCompromissoGerenciar.setFont(new java.awt.Font("Segoe UI", 0, 16)); // NOI18N
        itmCompromissoGerenciar.setText("Gerenciar");
        itmCompromissoGerenciar.setPreferredSize(new java.awt.Dimension(130, 24));
        itmCompromissoGerenciar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                itmCompromissoGerenciarActionPerformed(evt);
            }
        });
        mnuCompromisso.add(itmCompromissoGerenciar);

        barMenu.add(mnuCompromisso);

        mnuCompromisso1.setBackground(new java.awt.Color(153, 153, 153));
        mnuCompromisso1.setText("   Pedido Recebido");
        mnuCompromisso1.setBorderPainted(true);
        mnuCompromisso1.setFont(new java.awt.Font("Segoe UI", 0, 16)); // NOI18N
        mnuCompromisso1.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        mnuCompromisso1.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        mnuCompromisso1.setInheritsPopupMenu(true);
        mnuCompromisso1.setPreferredSize(new java.awt.Dimension(140, 24));

        itmCompromissoGerenciar1.setFont(new java.awt.Font("Segoe UI", 0, 16)); // NOI18N
        itmCompromissoGerenciar1.setText("Gerenciar");
        itmCompromissoGerenciar1.setPreferredSize(new java.awt.Dimension(130, 24));
        itmCompromissoGerenciar1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                itmCompromissoGerenciar1ActionPerformed(evt);
            }
        });
        mnuCompromisso1.add(itmCompromissoGerenciar1);

        barMenu.add(mnuCompromisso1);

        mnuProduto1.setBackground(new java.awt.Color(153, 153, 153));
        mnuProduto1.setText("   Usuario");
        mnuProduto1.setBorderPainted(true);
        mnuProduto1.setFont(new java.awt.Font("Segoe UI", 0, 16)); // NOI18N
        mnuProduto1.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        mnuProduto1.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        mnuProduto1.setInheritsPopupMenu(true);
        mnuProduto1.setPreferredSize(new java.awt.Dimension(95, 24));

        itmProdutoGerenciar1.setFont(new java.awt.Font("Segoe UI", 0, 16)); // NOI18N
        itmProdutoGerenciar1.setText("Editar");
        itmProdutoGerenciar1.setPreferredSize(new java.awt.Dimension(95, 24));
        itmProdutoGerenciar1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                itmProdutoGerenciar1ActionPerformed(evt);
            }
        });
        mnuProduto1.add(itmProdutoGerenciar1);

        itmProdutoGerenciar2.setFont(new java.awt.Font("Segoe UI", 0, 16)); // NOI18N
        itmProdutoGerenciar2.setText("Sair");
        itmProdutoGerenciar2.setPreferredSize(new java.awt.Dimension(95, 24));
        itmProdutoGerenciar2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                itmProdutoGerenciar2ActionPerformed(evt);
            }
        });
        mnuProduto1.add(itmProdutoGerenciar2);

        barMenu.add(mnuProduto1);

        setJMenuBar(barMenu);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addGap(0, 0, 0)
                .addComponent(jDesktopPane)
                .addGap(0, 0, 0))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jDesktopPane)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void itmFornecedorGerenciarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_itmFornecedorGerenciarActionPerformed
        FornecedorBuscar tela = new FornecedorBuscar(Repositorios.getFornecedorRepositorio(), FornecedorEditar.class);
        
        this.jDesktopPane.removeAll();
        
        this.jDesktopPane.add(tela);

        try {
            tela.setMaximum(true);
        } catch (PropertyVetoException ex) {
            Logger.getLogger(TelaEdicao.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        tela.buscar();
        
        tela.setVisible(true);
    }//GEN-LAST:event_itmFornecedorGerenciarActionPerformed

    private void itmClienteGerenciarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_itmClienteGerenciarActionPerformed
        ClienteBuscar tela = new ClienteBuscar(Repositorios.getClienteRepositorio(), ClienteEditar.class);
        
        this.jDesktopPane.removeAll();
        
        this.jDesktopPane.add(tela);

        try {
            tela.setMaximum(true);
        } catch (PropertyVetoException ex) {
            Logger.getLogger(TelaEdicao.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        tela.buscar();
        
        tela.setVisible(true);
    }//GEN-LAST:event_itmClienteGerenciarActionPerformed

    private void itmProdutoGerenciarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_itmProdutoGerenciarActionPerformed
        ProdutoBuscar tela = new ProdutoBuscar(Repositorios.getProdutoRepositorio(), ProdutoEditar.class);
        
        this.jDesktopPane.removeAll();
        
        this.jDesktopPane.add(tela);

        try {
            tela.setMaximum(true);
        } catch (PropertyVetoException ex) {
            Logger.getLogger(TelaEdicao.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        tela.buscar();
        
        tela.setVisible(true);
    }//GEN-LAST:event_itmProdutoGerenciarActionPerformed

    private void itmCompromissoGerenciarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_itmCompromissoGerenciarActionPerformed
        CompromissoBuscar tela = new CompromissoBuscar(Repositorios.getCompromissoRepositorio(), CompromissoEditar.class);
        
        this.jDesktopPane.removeAll();
        
        this.jDesktopPane.add(tela);

        try {
            tela.setMaximum(true);
        } catch (PropertyVetoException ex) {
            Logger.getLogger(TelaEdicao.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        tela.buscar();
        
        tela.setVisible(true);
    }//GEN-LAST:event_itmCompromissoGerenciarActionPerformed

    private void itmProdutoGerenciar1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_itmProdutoGerenciar1ActionPerformed
        UsuarioEditar telaUsuarioEditar=new UsuarioEditar();

        telaUsuarioEditar.setRepositorio(Repositorios.getUsuarioRepositorio());

        this.jDesktopPane.add(telaUsuarioEditar);

        telaUsuarioEditar.centraliza();

        telaUsuarioEditar.setCampos(Sessao.getUsuario());

        telaUsuarioEditar.setVisible(true);

    }//GEN-LAST:event_itmProdutoGerenciar1ActionPerformed

    private void itmProdutoGerenciar2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_itmProdutoGerenciar2ActionPerformed
        Sessao.setUsuario(null);
        
        TelaLogin telaLogin = new TelaLogin();

        telaLogin.setVisible(true);

        this.dispose();        
    }//GEN-LAST:event_itmProdutoGerenciar2ActionPerformed

    private void itmCompromissoGerenciar1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_itmCompromissoGerenciar1ActionPerformed
        PedidoRecebidoBuscar tela = new PedidoRecebidoBuscar(Repositorios.getPedidoRecebidoRepositorio(), PedidoRecebidoEditar.class);
        
        this.jDesktopPane.removeAll();
        
        this.jDesktopPane.add(tela);

        try {
            tela.setMaximum(true);
        } catch (PropertyVetoException ex) {
            Logger.getLogger(TelaEdicao.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        tela.buscar();
        
        tela.setVisible(true);
    }//GEN-LAST:event_itmCompromissoGerenciar1ActionPerformed

    private void itmClienteGerenciar1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_itmClienteGerenciar1ActionPerformed
        try {
            Map parametros = new HashMap();
            
            parametros.put("usuario", Sessao.getUsuario().getNome());
            
            exibeRelatorioJasper("Clientes.jasper", Repositorios.getClienteRepositorio().Buscar(null), parametros);
        } catch (ViolacaoRegraNegocioException ex) {
            Logger.getLogger(TelaPrincipal.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_itmClienteGerenciar1ActionPerformed

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
            java.util.logging.Logger.getLogger(TelaPrincipal.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(TelaPrincipal.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(TelaPrincipal.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(TelaPrincipal.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new TelaPrincipal().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JMenuBar barMenu;
    private javax.swing.JMenuItem itmClienteGerenciar;
    private javax.swing.JMenuItem itmClienteGerenciar1;
    private javax.swing.JMenuItem itmCompromissoGerenciar;
    private javax.swing.JMenuItem itmCompromissoGerenciar1;
    private javax.swing.JMenuItem itmFornecedorGerenciar;
    private javax.swing.JMenuItem itmProdutoGerenciar;
    private javax.swing.JMenuItem itmProdutoGerenciar1;
    private javax.swing.JMenuItem itmProdutoGerenciar2;
    private javax.swing.JDesktopPane jDesktopPane;
    private javax.swing.JMenu mnuCliente;
    private javax.swing.JMenu mnuCompromisso;
    private javax.swing.JMenu mnuCompromisso1;
    private javax.swing.JMenu mnuFornecedor;
    private javax.swing.JMenu mnuProduto;
    private javax.swing.JMenu mnuProduto1;
    // End of variables declaration//GEN-END:variables
}
