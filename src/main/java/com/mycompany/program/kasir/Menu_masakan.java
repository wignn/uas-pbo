package com.mycompany.program.kasir;

import com.mycompany.program.kasir.config.connect;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;

/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
/**
 *
 * @author tigfi
 */
public class Menu_masakan extends javax.swing.JFrame {

    session session = new session();

    private DefaultTableModel model = null;
    private PreparedStatement stat;
    private ResultSet rs;
    connect k = new connect();

    /**
     * Creates new form Menu_masakan
     */
    public Menu_masakan() {
        k.db();
        initComponents();
        refreshTable();
        validateUser();
    }

    class masakan extends Menu_masakan {

        int id_masakan, harga;
        String nama_masakan, status;

        public masakan() {
            String idMasakanText = IdMasakanTF1.getText().trim();
            if (!idMasakanText.isEmpty()) {
                id_masakan = Integer.parseInt(idMasakanText);
            } else {
                id_masakan = 0;
            }
            nama_masakan = NamaMasakanTF.getText().trim();
            this.harga = Integer.parseInt(HargaMasakanTF.getText().trim());
            this.status = StatusComboBox.getSelectedItem().toString();
        }

    }

    public boolean validateUser() {
        if (session.getIdLevel() < 4 & session.getIdLevel() > 0) {
            InputBtn.setEnabled(true);
            DeleteBtn.setEnabled(true);
            MenuRegisterBtn.setEnabled(true);
            MenuTransaksiBtn.setEnabled(true);
            UpdateBtn.setEnabled(true);
            return true;
        }

        return false;
    }

    public void refreshTable() {
        model = new DefaultTableModel() {
            @Override
            public boolean isCellEditable(int row, int column) {
                return false;
            }
        };
        model.addColumn("Id Masakan");
        model.addColumn("Nama Masakan");
        model.addColumn("Harga");
        model.addColumn("Status Masakan");
        Table_Masakan.setModel(model);
        try {
            this.stat = k.getCon().prepareStatement("select * from masakan");
            this.rs = this.stat.executeQuery();
            while (rs.next()) {
                Object[] data = {
                    rs.getInt("id_masakan"),
                    rs.getString("nama_masakan"),
                    rs.getInt("harga"),
                    rs.getString("status"),};
                model.addRow(data);
            }
        } catch (Exception e) {
            System.out.print(e);
            JOptionPane.showMessageDialog(null, e.getMessage());
        }
InputBtn.setEnabled(true);
        NamaMasakanTF.setText("");
        HargaMasakanTF.setText("");
        IdMasakanTF1.setText("");

    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jLabel2 = new javax.swing.JLabel();
        NamaMasakanTF = new javax.swing.JTextField();
        jLabel3 = new javax.swing.JLabel();
        IdMasakanTF1 = new javax.swing.JTextField();
        jLabel4 = new javax.swing.JLabel();
        HargaMasakanTF = new javax.swing.JTextField();
        jLabel5 = new javax.swing.JLabel();
        StatusComboBox = new javax.swing.JComboBox<>();
        jScrollPane1 = new javax.swing.JScrollPane();
        Table_Masakan = new javax.swing.JTable();
        jPanel1 = new javax.swing.JPanel();
        UpdateBtn = new javax.swing.JButton();
        InputBtn = new javax.swing.JButton();
        MenuRegisterBtn = new javax.swing.JButton();
        DeleteBtn = new javax.swing.JButton();
        LogoutBtn = new javax.swing.JButton();
        MenuTransaksiBtn = new javax.swing.JButton();
        jLabel6 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setFocusTraversalPolicyProvider(true);

        jLabel2.setFont(new java.awt.Font("Consolas", 0, 18)); // NOI18N
        jLabel2.setText("ID Masakan");

        NamaMasakanTF.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                NamaMasakanTFActionPerformed(evt);
            }
        });

        jLabel3.setFont(new java.awt.Font("Consolas", 0, 18)); // NOI18N
        jLabel3.setText("Harga");

        IdMasakanTF1.setEnabled(false);
        IdMasakanTF1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                IdMasakanTF1ActionPerformed(evt);
            }
        });

        jLabel4.setFont(new java.awt.Font("Consolas", 0, 18)); // NOI18N
        jLabel4.setText("Nama Masakan");

        HargaMasakanTF.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                HargaMasakanTFActionPerformed(evt);
            }
        });

        jLabel5.setFont(new java.awt.Font("Consolas", 0, 18)); // NOI18N
        jLabel5.setText("Status Masakan");

        StatusComboBox.setFont(new java.awt.Font("Consolas", 0, 14)); // NOI18N
        StatusComboBox.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Tersedia", "Habis" }));
        StatusComboBox.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                StatusComboBoxActionPerformed(evt);
            }
        });

        Table_Masakan.setModel(new javax.swing.table.DefaultTableModel(
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
        Table_Masakan.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                Table_MasakanMouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(Table_Masakan);

        UpdateBtn.setFont(new java.awt.Font("Consolas", 0, 12)); // NOI18N
        UpdateBtn.setText("Update");
        UpdateBtn.setEnabled(false);
        UpdateBtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                UpdateBtnActionPerformed(evt);
            }
        });

        InputBtn.setFont(new java.awt.Font("Consolas", 0, 12)); // NOI18N
        InputBtn.setText("Input");
        InputBtn.setEnabled(false);
        InputBtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                InputBtnActionPerformed(evt);
            }
        });

        MenuRegisterBtn.setFont(new java.awt.Font("Consolas", 0, 12)); // NOI18N
        MenuRegisterBtn.setText("Menu Registrasi");
        MenuRegisterBtn.setEnabled(false);
        MenuRegisterBtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                MenuRegisterBtnActionPerformed(evt);
            }
        });

        DeleteBtn.setFont(new java.awt.Font("Consolas", 0, 12)); // NOI18N
        DeleteBtn.setText("Delete");
        DeleteBtn.setEnabled(false);
        DeleteBtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                DeleteBtnActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(InputBtn, javax.swing.GroupLayout.PREFERRED_SIZE, 101, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(UpdateBtn, javax.swing.GroupLayout.PREFERRED_SIZE, 101, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(DeleteBtn, javax.swing.GroupLayout.PREFERRED_SIZE, 101, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(MenuRegisterBtn)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(24, 24, 24)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(InputBtn, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(UpdateBtn, javax.swing.GroupLayout.PREFERRED_SIZE, 39, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(MenuRegisterBtn, javax.swing.GroupLayout.PREFERRED_SIZE, 38, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(DeleteBtn, javax.swing.GroupLayout.PREFERRED_SIZE, 38, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(16, Short.MAX_VALUE))
        );

        LogoutBtn.setFont(new java.awt.Font("Consolas", 0, 14)); // NOI18N
        LogoutBtn.setText("Logout");
        LogoutBtn.setEnabled(false);
        LogoutBtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                LogoutBtnActionPerformed(evt);
            }
        });

        MenuTransaksiBtn.setFont(new java.awt.Font("Consolas", 0, 14)); // NOI18N
        MenuTransaksiBtn.setText("Menu Transaksi");
        MenuTransaksiBtn.setEnabled(false);
        MenuTransaksiBtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                MenuTransaksiBtnActionPerformed(evt);
            }
        });

        jLabel6.setFont(new java.awt.Font("Consolas", 0, 24)); // NOI18N
        jLabel6.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel6.setText("Menu Masakan");
        jLabel6.setToolTipText("");
        jLabel6.setFocusable(false);
        jLabel6.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel6, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(MenuTransaksiBtn)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(LogoutBtn))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addGroup(javax.swing.GroupLayout.Alignment.LEADING, layout.createSequentialGroup()
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabel2)
                                    .addComponent(jLabel4))
                                .addGap(26, 26, 26)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(IdMasakanTF1)
                                    .addComponent(NamaMasakanTF)))
                            .addGroup(javax.swing.GroupLayout.Alignment.LEADING, layout.createSequentialGroup()
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabel5)
                                    .addComponent(jLabel3))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(HargaMasakanTF)
                                    .addComponent(StatusComboBox, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                            .addComponent(jPanel1, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addGroup(layout.createSequentialGroup()
                                .addGap(6, 6, 6)
                                .addComponent(jScrollPane1)))
                        .addGap(38, 38, 38)))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(LogoutBtn)
                    .addComponent(MenuTransaksiBtn))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jLabel6, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(IdMasakanTF1, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(NamaMasakanTF, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(17, 17, 17)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel3, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(HargaMasakanTF, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addComponent(jLabel4, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jLabel5, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(StatusComboBox, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 149, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(28, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void NamaMasakanTFActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_NamaMasakanTFActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_NamaMasakanTFActionPerformed

    private void IdMasakanTF1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_IdMasakanTF1ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_IdMasakanTF1ActionPerformed

    private void HargaMasakanTFActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_HargaMasakanTFActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_HargaMasakanTFActionPerformed

    private void UpdateBtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_UpdateBtnActionPerformed
        try {
            if (!validateUser()) {
                JOptionPane.showMessageDialog(null, "Tidak punya hak akses");
                return;
            }
            masakan m = new masakan();
            System.out.print(m.harga + m.id_masakan + m.nama_masakan + m.status);
            String sql = "UPDATE masakan SET nama_masakan = ?, harga = ?, status = ? WHERE id_masakan = ?";
            this.stat = k.getCon().prepareStatement(sql);
            stat.setString(1, m.nama_masakan);
            stat.setInt(2, m.harga);
            stat.setString(3, m.status);
            stat.setInt(4, m.id_masakan);
            int rowsUpdated = stat.executeUpdate();
            if (rowsUpdated > 0) {
                JOptionPane.showMessageDialog(null, "Data berhasil diperbarui");
                refreshTable();
            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Error: " + e.getMessage());
            e.printStackTrace();
        }
    }//GEN-LAST:event_UpdateBtnActionPerformed

    private void InputBtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_InputBtnActionPerformed
        try {
            if (!validateUser()) {
                JOptionPane.showMessageDialog(null, "Tidak punya hak akses");
                return;
            }
            masakan m = new masakan();
            if (m.nama_masakan == null || m.status == null || m.harga == 0) {
                JOptionPane.showMessageDialog(null, "Semua field harus diisi!");
                return;
            }
            this.stat = k.getCon().prepareStatement("insert into masakan values(?,?,?,?)");
            stat.setInt(1, m.id_masakan);
            stat.setString(2, m.nama_masakan);
            stat.setInt(3, m.harga);
            stat.setString(4, m.status);
            stat.executeUpdate();
            System.out.print(stat);
            refreshTable();
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e.getMessage());
        }

    }//GEN-LAST:event_InputBtnActionPerformed

    private void MenuRegisterBtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_MenuRegisterBtnActionPerformed
        Menu_register r = new Menu_register();
        r.setVisible(true);
        this.setVisible(false);
    }//GEN-LAST:event_MenuRegisterBtnActionPerformed

    private void DeleteBtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_DeleteBtnActionPerformed
        try {
            if (!validateUser()) {
                JOptionPane.showMessageDialog(null, "Tidak punya hak akses");
                return;
            }
            masakan m = new masakan();
            int confirm = JOptionPane.showConfirmDialog(
                    null,
                    "Apakah Anda yakin ingin menghapus data ini?",
                    "Konfirmasi Hapus",
                    JOptionPane.YES_NO_OPTION
            );

            if (confirm == JOptionPane.YES_OPTION) {
                this.stat = k.getCon().prepareStatement("delete from masakan where id_masakan=?");
                stat.setInt(1, m.id_masakan);
                int rowsAffected = stat.executeUpdate();
                if (rowsAffected > 0) {
                    JOptionPane.showMessageDialog(null, "Data berhasil dihapus!");
                } else {
                    JOptionPane.showMessageDialog(null, "Data tidak ditemukan!");
                }
            }
            refreshTable();
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e.getMessage());
        }
    }//GEN-LAST:event_DeleteBtnActionPerformed

    private void LogoutBtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_LogoutBtnActionPerformed
        Login l = new Login();
        l.setVisible(true);
        this.setVisible(false);
    }//GEN-LAST:event_LogoutBtnActionPerformed

    private void Table_MasakanMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_Table_MasakanMouseClicked
        if (!validateUser()) {
            return;
        }
        InputBtn.setEnabled(false);
        int selectedRow = Table_Masakan.getSelectedRow();
        IdMasakanTF1.setText(model.getValueAt(selectedRow, 0).toString());
        NamaMasakanTF.setText(model.getValueAt(selectedRow, 1).toString());
        HargaMasakanTF.setText(model.getValueAt(selectedRow, 2).toString());
        StatusComboBox.setSelectedItem(model.getValueAt(selectedRow, 3));
    }//GEN-LAST:event_Table_MasakanMouseClicked

    private void MenuTransaksiBtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_MenuTransaksiBtnActionPerformed
        Menu_transaksi t = new Menu_transaksi();
        t.setVisible(true);
        this.setVisible(false);
    }//GEN-LAST:event_MenuTransaksiBtnActionPerformed

    private void StatusComboBoxActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_StatusComboBoxActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_StatusComboBoxActionPerformed

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
            java.util.logging.Logger.getLogger(Menu_masakan.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(Menu_masakan.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(Menu_masakan.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(Menu_masakan.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new Menu_masakan().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    public javax.swing.JButton DeleteBtn;
    private javax.swing.JTextField HargaMasakanTF;
    private javax.swing.JTextField IdMasakanTF1;
    public javax.swing.JButton InputBtn;
    public javax.swing.JButton LogoutBtn;
    public javax.swing.JButton MenuRegisterBtn;
    public javax.swing.JButton MenuTransaksiBtn;
    private javax.swing.JTextField NamaMasakanTF;
    private javax.swing.JComboBox<String> StatusComboBox;
    private javax.swing.JTable Table_Masakan;
    public javax.swing.JButton UpdateBtn;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JScrollPane jScrollPane1;
    // End of variables declaration//GEN-END:variables
}
