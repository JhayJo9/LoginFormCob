/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package com.mycompany.login_form;

import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.util.*;




/**
 *
 * @author Karlene Mae
 */
public class Main2 extends javax.swing.JFrame {

    /**
     * Creates new form Main2
     */
    public Main2() {
        initComponents();
        
        txt_studno.setEnabled(false);
        txt_last.setEnabled(false);
        txt_first.setEnabled(false);
        txt_middle.setEnabled(false);
        txt_add.setEnabled(false);
        txt_bday.setEnabled(false);
        jc_dept.setEnabled(false);
        jc_course.setEnabled(false);
        
    }
    
    
   public void tableupdate(){
        
        Connection con = null;
        PreparedStatement pst = null;
        ResultSet rs= null;
        
        int c;
         try {
                Class.forName("net.ucanaccess.jdbc.UcanaccessDriver");
                con = DriverManager.getConnection("jdbc:ucanaccess://JavaLogin.accdb");


                String sql = "SELECT * FROM StudInfoTbl";
                pst = con.prepareStatement(sql);
                rs = pst.executeQuery();
                ResultSetMetaData rsd = rs.getMetaData();
                c = rsd.getColumnCount();
                DefaultTableModel dft = (DefaultTableModel) jTable1.getModel();
                dft.setRowCount(0);        
            while(rs.next()){
                Vector v2 = new Vector();
                for(int i =1; i<=c;i++){
                    v2.add(rs.getString("Stud-no"));
                    v2.add(rs.getString("Last-name"));
                    v2.add(rs.getString("First-name"));
                    v2.add(rs.getString("Middle-name"));
                    v2.add(rs.getString("Address"));
                    v2.add(rs.getString("Birthday"));
                    v2.add(rs.getString("Department"));
                    v2.add(rs.getString("Course"));

                }
                dft.addRow(v2);

            }


            txt_studno.setText("");
            txt_last.setText("");
            txt_first.setText("");
            txt_middle.setText("");
            txt_add.setText("");
            txt_bday.setText("");
            jc_dept.setSelectedItem("---Select Department---");
            jc_course.setSelectedItem("-------Select Course--------");
            txt_studno.requestFocus();

        } catch (Exception e) {
            e.printStackTrace();

        }
    }
   //
   public void savebtn(){
            Connection conmain = null;
             PreparedStatement pstmain = null;
       
            try {
              String urlmain = "jdbc:ucanaccess://JavaLogin.accdb";
              conmain = DriverManager.getConnection(urlmain);
              String sql = "INSERT into StudInfoTbl([STUD-NO], [LAST-NAME], [FIRST-NAME], [MIDDLE-NAME], Address, Birthday, Department, Course) VALUES (?, ?, ?, ?, ?, ?, ?, ?)";
              pstmain = conmain.prepareStatement(sql);

              String studentNumber, lastName, firstName, middleName, address, birthDate, department, course;

              studentNumber = txt_studno.getText();
              lastName = txt_last.getText();
              firstName = txt_first.getText();
              middleName = txt_middle.getText();
              address = txt_add.getText();
              birthDate = txt_bday.getText();
              department = (String) jc_dept.getSelectedItem();
              course = (String) jc_course.getSelectedItem();

              pstmain.setString(1, studentNumber);   // STUD-NO
              pstmain.setString(2, lastName);        // LAST-NAME
              pstmain.setString(3, firstName);       // FIRST-NAME
              pstmain.setString(4, middleName);      // MIDDLE-NAME
              pstmain.setString(5, address);         // Address
              pstmain.setString(6, birthDate);       // Birthday
              pstmain.setString(7, department);      // Department
              pstmain.setString(8, course);          // Course

              pstmain.executeUpdate();
              JOptionPane.showMessageDialog(rootPane, "Inserted Successfully!");
              tableupdate();



          } catch (Exception e) {
             System.out.println(e);
          }
   }
   //
   public void addnewrecord(){
            txt_studno.setEnabled(true);
            txt_last.setEnabled(true);
            txt_first.setEnabled(true);
            txt_middle.setEnabled(true);
            txt_add.setEnabled(true);
            txt_bday.setEnabled(true);
            jc_dept.setEnabled(true);
            jc_course.setEnabled(true);


            txt_studno.setText("");
            txt_last.setText("");
            txt_first.setText("");
            txt_middle.setText("");
            txt_add.setText("");
            txt_bday.setText("");
            jc_dept.setSelectedItem("------------Select Department----------");
            jc_course.setSelectedItem("------------Select Department----------");
            txt_studno.requestFocus();
   }
   //
   public void updaterecord(){
        txt_studno.setEnabled(true);
        txt_last.setEnabled(true);
        txt_first.setEnabled(true);
        txt_middle.setEnabled(true);
        txt_add.setEnabled(true);
        txt_bday.setEnabled(true);
        jc_dept.setEnabled(true);
        jc_course.setEnabled(true);
        
         Connection conmain = null;
         PreparedStatement pstmain = null;
       
          try
          {
           String urlmain = "jdbc:ucanaccess://JavaLogin.accdb";
           conmain = DriverManager.getConnection(urlmain);
           DefaultTableModel model = (DefaultTableModel) jTable1.getModel();
           int selectedRow = jTable1.getSelectedRow();
           String id = (model.getValueAt(selectedRow, 0).toString());
           
           
           String studentNumber, lastName, firstName, middleName, address, birthDate, department, course;
        
            studentNumber = txt_studno.getText();
            lastName = txt_last.getText();
            firstName = txt_first.getText();
            middleName = txt_middle.getText();
            address = txt_add.getText();
            birthDate = txt_bday.getText();
            department = (String) jc_dept.getSelectedItem().toString();
            course = (String) jc_course.getSelectedItem().toString();  
            String sql = "UPDATE StudInfoTbl SET `Stud-no`=?, `Last-name`=?, `First-name`=?, `Middle-name`=?, Address=?, Birthday=?, Department=?, Course=? WHERE `Stud-no`=?";
            pstmain = conmain.prepareStatement(sql);

            // Correct order for setting values in the prepared statement
            pstmain.setString(1, studentNumber);   // Stud-no
            pstmain.setString(2, lastName);        // Last-name
            pstmain.setString(3, firstName);       // First-name
            pstmain.setString(4, middleName);      // Middle-name
            pstmain.setString(5, address);         // Address
            pstmain.setString(6, birthDate);       // Birthday
            pstmain.setString(7, department);      // Department
            pstmain.setString(8, course);          // Course
            pstmain.setString(9, studentNumber);   // WHERE condition
  
            int k = JOptionPane.showConfirmDialog(rootPane, "Confirm to Update?", "Update", JOptionPane.YES_NO_OPTION);
            if (k == JOptionPane.YES_OPTION)
            {
                
                pstmain.executeUpdate();
                JOptionPane.showMessageDialog(rootPane, "Updated Successfully!");
                txt_studno.setText("");
                txt_last.setText("");
                txt_first.setText("");
                txt_middle.setText("");
                txt_add.setText("");
                txt_bday.setText("");
                jc_dept.setSelectedItem("---Select Department---");
                jc_course.setSelectedItem("-------Select Course--------");
                txt_studno.requestFocus();
                tableupdate();
            }
            else
            {
                JOptionPane.showMessageDialog(rootPane, "Data not Updated!");
            }
       
       }
       catch (Exception e)
            {
                System.out.println(e);
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

        jScrollPane1 = new javax.swing.JScrollPane();
        jTable1 = new javax.swing.JTable();
        jPanel3 = new javax.swing.JPanel();
        btn_save = new javax.swing.JButton();
        btn_delete = new javax.swing.JButton();
        btn_add = new javax.swing.JButton();
        btn_update = new javax.swing.JButton();
        btn_exit = new javax.swing.JButton();
        jPanel1 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        jPanel2 = new javax.swing.JPanel();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        txt_studno = new javax.swing.JTextField();
        txt_last = new javax.swing.JTextField();
        txt_first = new javax.swing.JTextField();
        txt_middle = new javax.swing.JTextField();
        jLabel6 = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        jLabel8 = new javax.swing.JLabel();
        jLabel9 = new javax.swing.JLabel();
        txt_add = new javax.swing.JTextField();
        txt_bday = new javax.swing.JTextField();
        jc_dept = new javax.swing.JComboBox<>();
        jc_course = new javax.swing.JComboBox<>();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        addWindowListener(new java.awt.event.WindowAdapter() {
            public void windowActivated(java.awt.event.WindowEvent evt) {
                formWindowActivated(evt);
            }
        });

        jTable1.setBorder(new javax.swing.border.SoftBevelBorder(javax.swing.border.BevelBorder.RAISED));
        jTable1.setFont(new java.awt.Font("Rockwell", 0, 12)); // NOI18N
        jTable1.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null}
            },
            new String [] {
                "Student no.", "Last Name", "First Name", "Middle Name", "Address", "Birthday", "Department", "Course"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false, false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        jTable1.setToolTipText("");
        jTable1.setGridColor(new java.awt.Color(255, 254, 255));
        jTable1.setShowGrid(true);
        jTable1.setSurrendersFocusOnKeystroke(true);
        jTable1.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jTable1MouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(jTable1);

        jPanel3.setBackground(new java.awt.Color(254, 255, 254));
        jPanel3.setBorder(new javax.swing.border.SoftBevelBorder(javax.swing.border.BevelBorder.RAISED));

        btn_save.setBackground(new java.awt.Color(25, 119, 243));
        btn_save.setFont(new java.awt.Font("Rockwell", 1, 12)); // NOI18N
        btn_save.setForeground(new java.awt.Color(255, 255, 255));
        btn_save.setText("Save Record");
        btn_save.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_saveActionPerformed(evt);
            }
        });

        btn_delete.setBackground(new java.awt.Color(25, 119, 243));
        btn_delete.setFont(new java.awt.Font("Rockwell", 1, 12)); // NOI18N
        btn_delete.setForeground(new java.awt.Color(255, 255, 255));
        btn_delete.setText("Delete Record");
        btn_delete.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_deleteActionPerformed(evt);
            }
        });

        btn_add.setBackground(new java.awt.Color(25, 119, 243));
        btn_add.setFont(new java.awt.Font("Rockwell", 1, 12)); // NOI18N
        btn_add.setForeground(new java.awt.Color(255, 255, 255));
        btn_add.setText("Add New Record");
        btn_add.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_addActionPerformed(evt);
            }
        });

        btn_update.setBackground(new java.awt.Color(25, 119, 243));
        btn_update.setFont(new java.awt.Font("Rockwell", 1, 12)); // NOI18N
        btn_update.setForeground(new java.awt.Color(255, 255, 255));
        btn_update.setText("Update Record");
        btn_update.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_updateActionPerformed(evt);
            }
        });

        btn_exit.setBackground(new java.awt.Color(25, 119, 243));
        btn_exit.setFont(new java.awt.Font("Rockwell", 1, 12)); // NOI18N
        btn_exit.setForeground(new java.awt.Color(255, 255, 255));
        btn_exit.setText("Exit");
        btn_exit.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_exitActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addGap(40, 40, 40)
                .addComponent(btn_save)
                .addGap(49, 49, 49)
                .addComponent(btn_delete)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(btn_add, javax.swing.GroupLayout.PREFERRED_SIZE, 140, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(52, 52, 52)
                .addComponent(btn_update)
                .addGap(50, 50, 50)
                .addComponent(btn_exit)
                .addGap(40, 40, 40))
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addGap(39, 39, 39)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btn_save)
                    .addComponent(btn_delete)
                    .addComponent(btn_add)
                    .addComponent(btn_update)
                    .addComponent(btn_exit))
                .addContainerGap(38, Short.MAX_VALUE))
        );

        jPanel1.setBackground(new java.awt.Color(254, 255, 254));
        jPanel1.setBorder(javax.swing.BorderFactory.createEtchedBorder(null, new java.awt.Color(102, 102, 102)));

        jLabel1.setFont(new java.awt.Font("Rockwell", 1, 24)); // NOI18N
        jLabel1.setText("MS ACCES DATABASE CONNECTION WITH JAVA NETBEANS");

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(52, 52, 52)
                .addComponent(jLabel1)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(35, 35, 35)
                .addComponent(jLabel1)
                .addContainerGap(36, Short.MAX_VALUE))
        );

        jPanel2.setBackground(new java.awt.Color(254, 255, 254));
        jPanel2.setBorder(new javax.swing.border.SoftBevelBorder(javax.swing.border.BevelBorder.RAISED));
        jPanel2.setForeground(new java.awt.Color(0, 204, 255));

        jLabel2.setFont(new java.awt.Font("Rockwell", 0, 12)); // NOI18N
        jLabel2.setText("Student no.:");

        jLabel3.setFont(new java.awt.Font("Rockwell", 0, 12)); // NOI18N
        jLabel3.setText("Last name:");

        jLabel4.setFont(new java.awt.Font("Rockwell", 0, 12)); // NOI18N
        jLabel4.setText("First name:");

        jLabel5.setFont(new java.awt.Font("Rockwell", 0, 12)); // NOI18N
        jLabel5.setText("Middle Name:");

        jLabel6.setFont(new java.awt.Font("Rockwell", 0, 12)); // NOI18N
        jLabel6.setText("Address");

        jLabel7.setFont(new java.awt.Font("Rockwell", 0, 12)); // NOI18N
        jLabel7.setText("Birthday:");

        jLabel8.setFont(new java.awt.Font("Rockwell", 0, 12)); // NOI18N
        jLabel8.setText("Department:");

        jLabel9.setFont(new java.awt.Font("Rockwell", 0, 12)); // NOI18N
        jLabel9.setText("Course:");

        jc_dept.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "School of Computer Studies", "School of Education", "School of Business Management", "School of Hospitality and Tourism Management", "------------Select Department----------" }));
        jc_dept.setSelectedIndex(4);
        jc_dept.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jc_deptActionPerformed(evt);
            }
        });

        jc_course.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { " " }));
        jc_course.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jc_courseActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap(42, Short.MAX_VALUE)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 70, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel3)
                    .addComponent(jLabel4)
                    .addComponent(jLabel5))
                .addGap(34, 34, 34)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(txt_studno, javax.swing.GroupLayout.DEFAULT_SIZE, 243, Short.MAX_VALUE)
                    .addComponent(txt_last)
                    .addComponent(txt_first)
                    .addComponent(txt_middle))
                .addGap(26, 26, 26)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel6)
                    .addComponent(jLabel7)
                    .addComponent(jLabel8)
                    .addComponent(jLabel9))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(txt_bday, javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(txt_add, javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jc_dept, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jc_course, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap(42, Short.MAX_VALUE))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel4, javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addContainerGap()
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(txt_studno, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel2)
                            .addComponent(jLabel6)
                            .addComponent(txt_add, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(txt_last, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel3)
                            .addComponent(jLabel7)
                            .addComponent(txt_bday, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(txt_first, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel8)
                            .addComponent(jc_dept, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(txt_middle, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(jLabel5))
                    .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(jLabel9)
                        .addComponent(jc_course, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jPanel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 198, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents
    
    private void formWindowActivated(java.awt.event.WindowEvent evt) {//GEN-FIRST:event_formWindowActivated

       // method called
       tableupdate();
       
    }//GEN-LAST:event_formWindowActivated

    private void btn_saveActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_saveActionPerformed
        // TODO add your handling code here:
        // method called
          savebtn();
    }//GEN-LAST:event_btn_saveActionPerformed

    private void btn_deleteActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_deleteActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_btn_deleteActionPerformed

    private void btn_exitActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_exitActionPerformed
        // TODO add your handling code here:
      LogIn frame5 = new LogIn();
      frame5.setVisible(true);
      this.setVisible(false);
      
    }//GEN-LAST:event_btn_exitActionPerformed

    private void jc_deptActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jc_deptActionPerformed
        // TODO add your handling code here:
        if(jc_dept.getSelectedItem().equals("School of Computer Studies")){
            jc_course.removeAllItems();
            jc_course.addItem("BS. Information Technology");
            jc_course.addItem("BS. Computer Science");
        }
        else if(jc_dept.getSelectedItem().equals("School of Business Management")){
            jc_course.removeAllItems();
            jc_course.addItem("BS. Marketing Management");
            jc_course.addItem("BS. Human Resources and Development");
            jc_course.addItem("BS. Office administration");
        }
        else if(jc_dept.getSelectedItem().equals("School of Hospitality and Tourism Management")){
            jc_course.removeAllItems();
            jc_course.addItem("BS. in Hotel Management");
            jc_course.addItem("BS. in Tourism Management");
        }
        else if(jc_dept.getSelectedItem().equals("School of Education")){
            jc_course.removeAllItems();
            jc_course.addItem("B.S.E Major in English");
            jc_course.addItem("B.S.E Major in Mathematics");
            jc_course.addItem("B.S.E Major in Social Studies");
            jc_course.addItem("B.S.E Major in Filipino");
        }
        else if(jc_dept.getSelectedItem().equals("------------Select Department----------")){
            jc_course.removeAllItems();
        }

    }//GEN-LAST:event_jc_deptActionPerformed

    private void jc_courseActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jc_courseActionPerformed
        // TODO add your handling code here:

    }//GEN-LAST:event_jc_courseActionPerformed

    private void jTable1MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jTable1MouseClicked
        // TODO add your handling code here:
        
        DefaultTableModel model = (DefaultTableModel)jTable1.getModel();
        int selectedIndex = jTable1.getSelectedRow();
        
        txt_studno.setText(model.getValueAt(selectedIndex, 0).toString());
        txt_last.setText(model.getValueAt(selectedIndex, 1).toString());
        txt_first.setText(model.getValueAt(selectedIndex, 2).toString());
        txt_middle.setText(model.getValueAt(selectedIndex, 3).toString());
        txt_add.setText(model.getValueAt(selectedIndex, 4).toString());
        txt_bday.setText(model.getValueAt(selectedIndex, 5).toString());
        jc_dept.setSelectedItem(model.getValueAt(selectedIndex, 6));
        jc_course.setSelectedItem(model.getValueAt(selectedIndex, 7));
    }//GEN-LAST:event_jTable1MouseClicked

    private void btn_updateActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_updateActionPerformed
        // TODO add your handling code here:
        
       updaterecord();
    }//GEN-LAST:event_btn_updateActionPerformed

    private void btn_addActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_addActionPerformed
        // TODO add your handling code here:
       //method called
       addnewrecord();
    }//GEN-LAST:event_btn_addActionPerformed

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
            java.util.logging.Logger.getLogger(Main2.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(Main2.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(Main2.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(Main2.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new Main2().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btn_add;
    private javax.swing.JButton btn_delete;
    private javax.swing.JButton btn_exit;
    private javax.swing.JButton btn_save;
    private javax.swing.JButton btn_update;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTable jTable1;
    private javax.swing.JComboBox<String> jc_course;
    private javax.swing.JComboBox<String> jc_dept;
    private javax.swing.JTextField txt_add;
    private javax.swing.JTextField txt_bday;
    private javax.swing.JTextField txt_first;
    private javax.swing.JTextField txt_last;
    private javax.swing.JTextField txt_middle;
    private javax.swing.JTextField txt_studno;
    // End of variables declaration//GEN-END:variables

    private DefaultTableModel setRowCount(int i) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }
}
