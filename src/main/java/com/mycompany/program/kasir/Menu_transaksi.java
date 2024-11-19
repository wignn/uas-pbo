package com.mycompany.program.kasir;

/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
/**
 *
 * @author tigfi
 */
import com.mycompany.program.kasir.storage.session;
import com.mycompany.program.kasir.config.connect;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;

public class Menu_transaksi extends javax.swing.JFrame {

    private DefaultTableModel model = null;
    private PreparedStatement stat;
    private ResultSet rs;
    session session = new session();
    connect k = new connect();

    /**
     * Creates new form Menu_masakan
     */
    public Menu_transaksi() {
        k.db();
        initComponents();

        refreshCombo();
        refreshTable();

    }

    public boolean validateUser() {
        if (session.getIdLevel() < 4 && session.getIdLevel() > 0) {
            CetakLaporanBtn.setEnabled(true);
            return true;
        }
        CetakLaporanBtn.setEnabled(false);
        return false;
    }

    public class transaksi extends Menu_transaksi {

        int id_transaksi, id_masakan, jumlah_beli, harga, total_bayar;
        String nama_pelanggan, nama_masakan, tanggal;

        public transaksi() {
            this.nama_pelanggan = NamaPelangganTF.getText();
            String combo = IdMasakanComboBox.getSelectedItem().toString();
            String[] arr = combo.split(":");
            System.out.println(arr);
            this.id_masakan = Integer.parseInt(arr[0].trim());
            this.nama_masakan = arr[1];

            try {
                String hargaStr = arr[2].trim();
                Date date = DateTF.getDate();
                DateFormat df = new SimpleDateFormat("YYYY-MM-dd");
                this.tanggal = df.format(date);
                if (isNumeric(hargaStr)) {
                    this.harga = Integer.parseInt(hargaStr);
                } else {
                    throw new NumberFormatException("Harga is not a valid number.");
                }
            } catch (Exception e) {
                JOptionPane.showMessageDialog(null, e.getMessage());
            }
            this.jumlah_beli = Integer.parseInt(JumlahBeliTF.getText());
            this.total_bayar = this.harga * this.jumlah_beli;
        }

        private boolean isNumeric(String str) {
            try {
                Integer.parseInt(str);
                return true;
            } catch (NumberFormatException e) {
                return false;
            }
        }
    }

    public void refreshTable() {
        model = new DefaultTableModel() {
            @Override
            public boolean isCellEditable(int row, int column) {
                return false;
            }
        };
        model.addColumn("Id Transaksi");
        model.addColumn("Nama Pelanggan");
        model.addColumn("Id Masakan");
        model.addColumn("Nama Masakan");
        model.addColumn("Harga");
        model.addColumn("Jumlah Beli");
        model.addColumn("Total Beli");
        model.addColumn("Tanggal");
        TableTransaksi.setModel(model);
        try {
            this.stat = k.getCon().prepareStatement("select * from transaksi");
            this.rs = this.stat.executeQuery();
            while (rs.next()) {
                Object[] data = {
                    rs.getString(1),
                    rs.getString(2),
                    rs.getString(3),
                    rs.getString(4),
                    rs.getString(5),
                    rs.getString(6),
                    rs.getString(7),
                    rs.getString(8),};
                model.addRow(data);
            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(rootPane, e.getMessage());
        }

        IdTransaksiTF.setText("");
        NamaPelangganTF.setText("");
        JumlahBeliTF.setText("");
        TotalBayarTF.setText("");
        DateTF.setDate(null);
    }

    public void refreshCombo() {
        try {
            this.stat = k.getCon().prepareStatement("SELECT * FROM masakan WHERE status='Tersedia'");
            this.rs = this.stat.executeQuery();
            while (rs.next()) {
                IdMasakanComboBox.addItem(
                        rs.getInt("id_masakan") + ": "
                        + rs.getString("nama_masakan") + ": "
                        + rs.getString("harga") + ": "
                        + rs.getString("status")
                );

            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e.getMessage());
        }
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
        NamaPelangganTF = new javax.swing.JTextField();
        IdTransaksiTF = new javax.swing.JTextField();
        jLabel4 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        IdMasakanComboBox = new javax.swing.JComboBox<>();
        jScrollPane1 = new javax.swing.JScrollPane();
        TableTransaksi = new javax.swing.JTable();
        jPanel1 = new javax.swing.JPanel();
        UpdateBtn = new javax.swing.JButton();
        InputBtn = new javax.swing.JButton();
        CetakLaporanBtn = new javax.swing.JButton();
        DeleteBtn = new javax.swing.JButton();
        LogoutBtn = new javax.swing.JButton();
        jLabel6 = new javax.swing.JLabel();
        LihatMenuBtn = new javax.swing.JButton();
        jLabel7 = new javax.swing.JLabel();
        jLabel8 = new javax.swing.JLabel();
        TotalBayarTF = new javax.swing.JTextField();
        jLabel9 = new javax.swing.JLabel();
        JumlahBeliTF = new javax.swing.JTextField();
        DateTF = new com.toedter.calendar.JDateChooser();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setFocusTraversalPolicyProvider(true);
        setLocation(new java.awt.Point(0, 0));

        jLabel2.setFont(new java.awt.Font("Consolas", 0, 18)); // NOI18N
        jLabel2.setText("ID Transaksi");

        NamaPelangganTF.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                NamaPelangganTFActionPerformed(evt);
            }
        });

        IdTransaksiTF.setEnabled(false);
        IdTransaksiTF.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                IdTransaksiTFActionPerformed(evt);
            }
        });

        jLabel4.setFont(new java.awt.Font("Consolas", 0, 18)); // NOI18N
        jLabel4.setText("Nama Pelanggan");

        jLabel5.setFont(new java.awt.Font("Consolas", 0, 18)); // NOI18N
        jLabel5.setText("Id Masakan");

        IdMasakanComboBox.setFont(new java.awt.Font("Consolas", 0, 14)); // NOI18N
        IdMasakanComboBox.setMinimumSize(new java.awt.Dimension(97, 22));
        IdMasakanComboBox.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                IdMasakanComboBoxActionPerformed(evt);
            }
        });

        TableTransaksi.setModel(new javax.swing.table.DefaultTableModel(
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
        TableTransaksi.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                TableTransaksiMouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(TableTransaksi);

        UpdateBtn.setFont(new java.awt.Font("Consolas", 0, 12)); // NOI18N
        UpdateBtn.setText("Update");
        UpdateBtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                UpdateBtnActionPerformed(evt);
            }
        });

        InputBtn.setFont(new java.awt.Font("Consolas", 0, 12)); // NOI18N
        InputBtn.setText("Input");
        InputBtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                InputBtnActionPerformed(evt);
            }
        });

        CetakLaporanBtn.setFont(new java.awt.Font("Consolas", 0, 12)); // NOI18N
        CetakLaporanBtn.setText("Cetak Laporan");
        CetakLaporanBtn.setEnabled(false);
        CetakLaporanBtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                CetakLaporanBtnActionPerformed(evt);
            }
        });

        DeleteBtn.setFont(new java.awt.Font("Consolas", 0, 12)); // NOI18N
        DeleteBtn.setText("Delete");
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
                .addComponent(CetakLaporanBtn)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(21, 21, 21)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(InputBtn, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(UpdateBtn, javax.swing.GroupLayout.PREFERRED_SIZE, 39, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(CetakLaporanBtn, javax.swing.GroupLayout.PREFERRED_SIZE, 38, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(DeleteBtn, javax.swing.GroupLayout.PREFERRED_SIZE, 38, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(24, Short.MAX_VALUE))
        );

        LogoutBtn.setFont(new java.awt.Font("Consolas", 0, 14)); // NOI18N
        LogoutBtn.setText("Logout");
        LogoutBtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                LogoutBtnActionPerformed(evt);
            }
        });

        jLabel6.setFont(new java.awt.Font("Consolas", 0, 24)); // NOI18N
        jLabel6.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel6.setText("Transaksi");
        jLabel6.setToolTipText("");
        jLabel6.setFocusable(false);
        jLabel6.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);

        LihatMenuBtn.setFont(new java.awt.Font("Consolas", 0, 12)); // NOI18N
        LihatMenuBtn.setText("Lihat Menu");
        LihatMenuBtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                LihatMenuBtnActionPerformed(evt);
            }
        });

        jLabel7.setFont(new java.awt.Font("Consolas", 0, 18)); // NOI18N
        jLabel7.setText("Total Bayar");

        jLabel8.setFont(new java.awt.Font("Consolas", 0, 18)); // NOI18N
        jLabel8.setText("Jumlah Beli");

        TotalBayarTF.setEnabled(false);
        TotalBayarTF.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                TotalBayarTFActionPerformed(evt);
            }
        });

        jLabel9.setFont(new java.awt.Font("Consolas", 0, 18)); // NOI18N
        jLabel9.setText("Tanggal");

        JumlahBeliTF.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                JumlahBeliTFActionPerformed(evt);
            }
        });

        DateTF.setMinimumSize(new java.awt.Dimension(64, 22));

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel6, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(0, 436, Short.MAX_VALUE)
                        .addComponent(LogoutBtn))
                    .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel2)
                            .addComponent(jLabel4)
                            .addComponent(jLabel5)
                            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                                .addComponent(jLabel9, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(jLabel7, javax.swing.GroupLayout.DEFAULT_SIZE, 140, Short.MAX_VALUE)
                                .addComponent(jLabel8, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                        .addGap(26, 26, 26)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(TotalBayarTF)
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(IdMasakanComboBox, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addGap(18, 18, 18)
                                .addComponent(LihatMenuBtn))
                            .addComponent(IdTransaksiTF)
                            .addComponent(NamaPelangganTF)
                            .addComponent(JumlahBeliTF)
                            .addComponent(DateTF, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(6, 6, 6)
                        .addComponent(jScrollPane1)))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(LogoutBtn)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel6, javax.swing.GroupLayout.PREFERRED_SIZE, 52, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(39, 39, 39)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(IdTransaksiTF, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(NamaPelangganTF, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel4, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel5, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(IdMasakanComboBox, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(LihatMenuBtn, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jLabel9, javax.swing.GroupLayout.DEFAULT_SIZE, 35, Short.MAX_VALUE)
                    .addComponent(DateTF, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel8, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(JumlahBeliTF, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel7, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(TotalBayarTF, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 146, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void NamaPelangganTFActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_NamaPelangganTFActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_NamaPelangganTFActionPerformed

    private void IdTransaksiTFActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_IdTransaksiTFActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_IdTransaksiTFActionPerformed

    private void UpdateBtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_UpdateBtnActionPerformed
        try {
            if (!validateUser()) {
                JOptionPane.showMessageDialog(null, "tidak punya hak akses");
                return;
            }
            InputBtn.setEnabled(true);
            transaksi t = new transaksi();
            t.id_transaksi = Integer.parseInt(IdTransaksiTF.getText());
            t.nama_pelanggan = NamaPelangganTF.getText();

            Date date = DateTF.getDate();
            DateFormat df = new SimpleDateFormat("yyyy-MM-dd");
            t.tanggal = df.format(date);

            String sql = "UPDATE transaksi SET nama_pelanggan = ?,id_masakan=?, tanggal = ?, nama_masakan = ?, harga = ?, jumlah_beli = ?, total_bayar = ? WHERE id_transaksi = ?";
            this.stat = k.getCon().prepareStatement(sql);

            this.stat.setString(1, t.nama_pelanggan);
            this.stat.setInt(2, t.id_masakan);
            this.stat.setString(3, t.tanggal);
            this.stat.setString(4, t.nama_masakan);
            this.stat.setInt(5, t.harga);
            this.stat.setInt(6, t.jumlah_beli);
            this.stat.setInt(7, t.total_bayar);
            this.stat.setInt(8, t.id_transaksi);

            int rowsUpdated = stat.executeUpdate();
            if (rowsUpdated > 0) {
                JOptionPane.showMessageDialog(null, "Data berhasil diperbarui");
                refreshTable();

            }
        } catch (Exception e) {

            JOptionPane.showMessageDialog(null, e.getMessage());
            System.out.println("Error: " + e.getMessage());
        }

    }//GEN-LAST:event_UpdateBtnActionPerformed

    private void InputBtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_InputBtnActionPerformed
        try {
            if (!validateUser()) {
                JOptionPane.showMessageDialog(null, "tidak punya hak akses");
                return;
            }
            transaksi t = new transaksi();
            String sql = "INSERT INTO transaksi (id_transaksi, nama_pelanggan, id_masakan, tanggal, nama_masakan, harga, jumlah_beli, total_bayar) VALUES (?, ?, ?, ?, ?, ?, ?, ?)";
            this.stat = k.getCon().prepareStatement(sql);
            stat.setInt(1, 0);
            stat.setString(2, t.nama_pelanggan);
            stat.setInt(3, t.id_masakan);
            stat.setString(4, t.tanggal);
            stat.setString(5, t.nama_masakan);
            stat.setInt(6, t.harga);
            stat.setInt(7, t.jumlah_beli);
            stat.setInt(8, t.total_bayar);
            int p = JOptionPane.showConfirmDialog(null,
                    "Tanggal: " + t.tanggal
                    + "\nNama Pelanggan: " + t.nama_pelanggan
                    + "\nData telah disimpan. Apakah Anda ingin melanjutkan?",
                    "Konfirmasi", JOptionPane.YES_NO_OPTION);

            if (p == JOptionPane.YES_OPTION) {
                this.stat.executeUpdate();
                System.out.println("Data berhasil disimpan!");
                refreshTable();
            } else {
                System.out.println("Data tidak disimpan.");
                refreshTable();
            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e.getMessage());
            System.out.print(e);
        }
    }//GEN-LAST:event_InputBtnActionPerformed


    private void CetakLaporanBtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_CetakLaporanBtnActionPerformed
        try (
                FileWriter writer = new FileWriter("src/result.txt")) {
            if (!validateUser()) {
                JOptionPane.showMessageDialog(null, "tidak punya hak akses");
                return;
            }
            this.stat = k.getCon().prepareStatement("select * from transaksi");
            this.rs = this.stat.executeQuery();
            String header = String.format(
                    "%-15s %-20s %-15s %-20s %-15s %-15s %-15s%n",
                    "ID_TRANSAKSI", "NAMA_PELANGGAN", "ID_MASAKAN", "NAMA_MASAKAN", "JUMLAH_BELI", "TOTAL_BAYAR", "TANGGAL"
            );
            writer.write(header);
            writer.write("===========================================================================================================\n");
            int totalUangMasuk = 0;
            while (rs.next()) {
                String row = String.format(
                        "%-15s %-20s %-15s %-20s %-15s %-15s %-15s%n",
                        rs.getString("id_transaksi"),
                        rs.getString("nama_pelanggan"),
                        rs.getString("id_masakan"),
                        rs.getString("nama_masakan"),
                        rs.getString("jumlah_beli"),
                        rs.getString("total_bayar"),
                        rs.getString("tanggal")
                );
                writer.write(row);
                totalUangMasuk += rs.getInt("total_bayar");
            }
            writer.write("=============================================================================================================\n");
            writer.write(String.format("%100s: %,d%n", "TOTAL UANG MASUK", totalUangMasuk));

            JOptionPane.showMessageDialog(this, "Hasil telah disimpan di src/result.txt");
        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, "Error: " + e.getMessage());
        }

    }//GEN-LAST:event_CetakLaporanBtnActionPerformed

    private void DeleteBtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_DeleteBtnActionPerformed
        try {
            if (!validateUser()) {
                JOptionPane.showMessageDialog(null, "tidak punya hak akses");
                return;
            }
            InputBtn.setEnabled(true);
            transaksi t = new transaksi();
            int confirm = JOptionPane.showConfirmDialog(
                    null,
                    "Apakah Anda yakin ingin menghapus data ini?",
                    "Konfirmasi Hapus",
                    JOptionPane.YES_NO_OPTION
            );

            if (confirm == JOptionPane.YES_OPTION) {
                this.stat = k.getCon().prepareStatement("delete from transaksi where id_transaksi=?");
                stat.setInt(1, Integer.parseInt(IdTransaksiTF.getText()));
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

    private void TotalBayarTFActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_TotalBayarTFActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_TotalBayarTFActionPerformed

    private void JumlahBeliTFActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_JumlahBeliTFActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_JumlahBeliTFActionPerformed

    private void LogoutBtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_LogoutBtnActionPerformed
        Login l = new Login();
        l.setVisible(true);
        this.setVisible(false);
    }//GEN-LAST:event_LogoutBtnActionPerformed

    private void IdMasakanComboBoxActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_IdMasakanComboBoxActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_IdMasakanComboBoxActionPerformed

    private void TableTransaksiMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_TableTransaksiMouseClicked
        try {
            if (!validateUser()) {
                return;
            }
            InputBtn.setEnabled(false);
            String dateString = model.getValueAt(TableTransaksi.getSelectedRow(), 7).toString();
            DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
            Date date = dateFormat.parse(dateString);
            DateTF.setDate(date);
            IdTransaksiTF.setText(model.getValueAt(TableTransaksi.getSelectedRow(), 0).toString());
            NamaPelangganTF.setText(model.getValueAt(TableTransaksi.getSelectedRow(), 1).toString());
            JumlahBeliTF.setText(model.getValueAt(TableTransaksi.getSelectedRow(), 5).toString());
            TotalBayarTF.setText(model.getValueAt(TableTransaksi.getSelectedRow(), 6).toString());

        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e.getMessage());
        }
    }//GEN-LAST:event_TableTransaksiMouseClicked

    private void LihatMenuBtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_LihatMenuBtnActionPerformed
        Menu_masakan m = new Menu_masakan();
        m.setVisible(true);
        this.setVisible(false);
    }//GEN-LAST:event_LihatMenuBtnActionPerformed

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
            java.util.logging.Logger.getLogger(Menu_transaksi.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(Menu_transaksi.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(Menu_transaksi.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(Menu_transaksi.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new Menu_transaksi().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    public javax.swing.JButton CetakLaporanBtn;
    private com.toedter.calendar.JDateChooser DateTF;
    public javax.swing.JButton DeleteBtn;
    private javax.swing.JComboBox<String> IdMasakanComboBox;
    private javax.swing.JTextField IdTransaksiTF;
    public javax.swing.JButton InputBtn;
    private javax.swing.JTextField JumlahBeliTF;
    private javax.swing.JButton LihatMenuBtn;
    public javax.swing.JButton LogoutBtn;
    private javax.swing.JTextField NamaPelangganTF;
    private javax.swing.JTable TableTransaksi;
    private javax.swing.JTextField TotalBayarTF;
    private javax.swing.JButton UpdateBtn;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JScrollPane jScrollPane1;
    // End of variables declaration//GEN-END:variables
}
