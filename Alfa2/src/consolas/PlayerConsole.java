/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package consolas;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JOptionPane;
import master.Monster;
import player.Player;
import javax.swing.*;

/**
 *
 * @author pmeji
 */
public class PlayerConsole extends javax.swing.JFrame implements ActionListener {

    private Player player;
    private Monster enteringMonster;
    private Monster leavingMonster;
    private int x;
    private int y;
    private String name;

    /**
     * Creates new form GameConsole
     */
    public PlayerConsole() {
        final JFrame parent = new JFrame();
        this.name = JOptionPane.showInputDialog(parent,"¿Tu nombre?", null);
//        String password = JOptionPane.showInputDialog(parent,"¿Contraseña?", null);
        JPanel panel = new JPanel();
        JLabel label = new JLabel("Enter a password:");
        JPasswordField pass = new JPasswordField(10);
        panel.add(label);
        panel.add(pass);
        String[] options = new String[]{"Aceptar", "Cancelar"};
        int option = JOptionPane.showOptionDialog(null, panel, "Password",
                                 JOptionPane.NO_OPTION, JOptionPane.PLAIN_MESSAGE,
                                 null, options, options[0]);
        String password = null;
        if(option == 0) // pressing OK button
        {
            password = new String(pass.getPassword());
            System.out.println("Your password is: " + password);
        } else {
            System.exit(0);
        }
        initComponents();
        jInternalFrame1.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
        System.out.println("Player Console: " + name);
        jInternalFrame1.setTitle("Player: " + name);
        player = new Player(name, password);
        player.logIn();
        leavingMonster = new Monster(-1, -1, 1);
        enteringMonster = new Monster(-1, -1, 1);
        jTextField.setVisible(true);
    }

    private void showMonster() {
        switch (y) {
            case 1:
                switch (x) {
                    case 1:
                        jCheckBox1.setForeground(Color.red);
                        break;
                    case 2:
                        jCheckBox2.setForeground(Color.red);
                        break;
                    case 3:
                        jCheckBox3.setForeground(Color.red);
                        break;
                }
                break;
            case 2:
                switch (x) {
                    case 1:
                        jCheckBox4.setForeground(Color.red);
                        break;
                    case 2:
                        jCheckBox5.setForeground(Color.red);
                        break;
                    case 3:
                        jCheckBox6.setForeground(Color.red);
                        break;
                }
                break;
            case 3:
                switch (x) {
                    case 1:
                        jCheckBox7.setForeground(Color.red);
                        break;
                    case 2:
                        jCheckBox8.setForeground(Color.red);
                        break;
                    case 3:
                        jCheckBox9.setForeground(Color.red);
                        break;
                }
        }
    }

    public void cleanMonster() {
        switch (y) {
            case 1:
                switch (x) {

                    case 1:
                        jCheckBox1.setForeground(Color.BLACK);
                        break;
                    case 2:
                        jCheckBox2.setForeground(Color.BLACK);
                        break;
                    case 3:
                        jCheckBox3.setForeground(Color.BLACK);
                        break;
                }
            case 2:
                switch (x) {
                    case 1:
                        jCheckBox4.setForeground(Color.BLACK);
                        break;
                    case 2:
                        jCheckBox5.setForeground(Color.BLACK);
                        break;
                    case 3:
                        jCheckBox6.setForeground(Color.BLACK);
                        break;
                }
            case 3:
                switch (x) {
                    case 1:
                        jCheckBox7.setForeground(Color.BLACK);
                        break;
                    case 2:
                        jCheckBox8.setForeground(Color.BLACK);
                        break;
                    case 3:
                        jCheckBox9.setForeground(Color.BLACK);
                        break;
                }
        }
    }

    public void catchMonster() {
        if (player.isIsWinner() == true) {
            player.setIsWinner(false);
            final JFrame parent = new JFrame();
            JOptionPane.showMessageDialog(parent, "¡Felicidades " + this.name + ". Ganaste la ronda :) ");
        }
        player.receiveMonster();
        enteringMonster = player.getCurrentMonster();
        this.cleanMonster();
        x = enteringMonster.getX();
        y = enteringMonster.getY();
        this.showMonster();
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        buttonGroup1 = new javax.swing.ButtonGroup();
        jInternalFrame1 = new javax.swing.JInternalFrame();
        jCheckBox1 = new javax.swing.JCheckBox();
        jCheckBox2 = new javax.swing.JCheckBox();
        jCheckBox3 = new javax.swing.JCheckBox();
        jCheckBox4 = new javax.swing.JCheckBox();
        jCheckBox5 = new javax.swing.JCheckBox();
        jCheckBox6 = new javax.swing.JCheckBox();
        jCheckBox7 = new javax.swing.JCheckBox();
        jCheckBox8 = new javax.swing.JCheckBox();
        jCheckBox9 = new javax.swing.JCheckBox();
        jButton1 = new javax.swing.JButton();
        jTextField = new javax.swing.JTextField();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jInternalFrame1.setVisible(true);

        buttonGroup1.add(jCheckBox1);
        jCheckBox1.setText("Hole 1");
        jCheckBox1.setActionCommand("h1");
        jCheckBox1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jCheckBox1ActionPerformed(evt);
            }
        });

        buttonGroup1.add(jCheckBox2);
        jCheckBox2.setText("Hole 2");
        jCheckBox2.setActionCommand("h2");
        jCheckBox2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jCheckBox2ActionPerformed(evt);
            }
        });

        buttonGroup1.add(jCheckBox3);
        jCheckBox3.setText("Hole 3");
        jCheckBox3.setActionCommand("h3");
        jCheckBox3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jCheckBox3ActionPerformed(evt);
            }
        });

        buttonGroup1.add(jCheckBox4);
        jCheckBox4.setText("Hole 4");
        jCheckBox4.setActionCommand("h4");
        jCheckBox4.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jCheckBox4ActionPerformed(evt);
            }
        });

        buttonGroup1.add(jCheckBox5);
        jCheckBox5.setText("Hole 5");
        jCheckBox5.setActionCommand("h5");
        jCheckBox5.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jCheckBox5ActionPerformed(evt);
            }
        });

        buttonGroup1.add(jCheckBox6);
        jCheckBox6.setText("Hole 6");
        jCheckBox6.setActionCommand("h6");
        jCheckBox6.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jCheckBox6ActionPerformed(evt);
            }
        });

        buttonGroup1.add(jCheckBox7);
        jCheckBox7.setText("Hole 7");
        jCheckBox7.setActionCommand("h7");
        jCheckBox7.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jCheckBox7ActionPerformed(evt);
            }
        });

        buttonGroup1.add(jCheckBox8);
        jCheckBox8.setText("Hole 8");
        jCheckBox8.setActionCommand("h8");
        jCheckBox8.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jCheckBox8ActionPerformed(evt);
            }
        });

        buttonGroup1.add(jCheckBox9);
        jCheckBox9.setText("Hole 9");
        jCheckBox9.setActionCommand("h9");
        jCheckBox9.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jCheckBox9ActionPerformed(evt);
            }
        });

        jButton1.setText("START");
        jButton1.setActionCommand("update");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        jTextField.setEditable(false);
        jTextField.setText("Puntaje: 0");
        jTextField.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jTextFieldActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jInternalFrame1Layout = new javax.swing.GroupLayout(jInternalFrame1.getContentPane());
        jInternalFrame1.getContentPane().setLayout(jInternalFrame1Layout);
        jInternalFrame1Layout.setHorizontalGroup(
            jInternalFrame1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jInternalFrame1Layout.createSequentialGroup()
                .addGroup(jInternalFrame1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jInternalFrame1Layout.createSequentialGroup()
                        .addGap(115, 115, 115)
                        .addComponent(jButton1, javax.swing.GroupLayout.PREFERRED_SIZE, 114, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jInternalFrame1Layout.createSequentialGroup()
                        .addGap(70, 70, 70)
                        .addGroup(jInternalFrame1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(jTextField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(jInternalFrame1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                .addGroup(jInternalFrame1Layout.createSequentialGroup()
                                    .addComponent(jCheckBox7)
                                    .addGap(18, 18, 18)
                                    .addComponent(jCheckBox8)
                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                    .addComponent(jCheckBox9))
                                .addGroup(jInternalFrame1Layout.createSequentialGroup()
                                    .addGroup(jInternalFrame1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                                        .addComponent(jCheckBox1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                        .addComponent(jCheckBox4, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                                    .addGroup(jInternalFrame1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                        .addGroup(jInternalFrame1Layout.createSequentialGroup()
                                            .addGap(18, 18, 18)
                                            .addComponent(jCheckBox5)
                                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                            .addComponent(jCheckBox6))
                                        .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jInternalFrame1Layout.createSequentialGroup()
                                            .addGap(18, 18, 18)
                                            .addComponent(jCheckBox2)
                                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                            .addComponent(jCheckBox3))))))))
                .addContainerGap(77, Short.MAX_VALUE))
        );
        jInternalFrame1Layout.setVerticalGroup(
            jInternalFrame1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jInternalFrame1Layout.createSequentialGroup()
                .addGap(43, 43, 43)
                .addGroup(jInternalFrame1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jInternalFrame1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(jCheckBox2)
                        .addComponent(jCheckBox3))
                    .addComponent(jCheckBox1, javax.swing.GroupLayout.PREFERRED_SIZE, 23, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jInternalFrame1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jCheckBox4)
                    .addComponent(jCheckBox5)
                    .addComponent(jCheckBox6))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jInternalFrame1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jCheckBox7)
                    .addComponent(jCheckBox8)
                    .addComponent(jCheckBox9))
                .addGap(13, 13, 13)
                .addComponent(jButton1)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jTextField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(12, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jInternalFrame1)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jInternalFrame1)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jCheckBox1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jCheckBox1ActionPerformed
        // TODO add your handling code here:
        leavingMonster.setX(1);
        leavingMonster.setY(1);
        player.setCurrentMonster(leavingMonster);
        player.sendAnswer();
        if (player.getScore() >= 0) {
            jTextField.setText("Puntaje: " + player.getScore());
        }
    }//GEN-LAST:event_jCheckBox1ActionPerformed

    private void jCheckBox2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jCheckBox2ActionPerformed
        leavingMonster.setX(2);
        leavingMonster.setY(1);
        player.setCurrentMonster(leavingMonster);
        player.sendAnswer();
        if (player.getScore() >= 0) {
            jTextField.setText("Puntaje: " + player.getScore());
        }
    }//GEN-LAST:event_jCheckBox2ActionPerformed

    private void jCheckBox3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jCheckBox3ActionPerformed
        // TODO add your handling code here:
        leavingMonster.setX(3);
        leavingMonster.setY(1);
        player.setCurrentMonster(leavingMonster);
        player.sendAnswer();
        if (player.getScore() >= 0) {
            jTextField.setText("Puntaje: " + player.getScore());
        }
    }//GEN-LAST:event_jCheckBox3ActionPerformed

    private void jCheckBox4ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jCheckBox4ActionPerformed
        // TODO add your handling code here:
        leavingMonster.setX(1);
        leavingMonster.setY(2);
        player.setCurrentMonster(leavingMonster);
        player.sendAnswer();
        if (player.getScore() >= 0) {
            jTextField.setText("Puntaje: " + player.getScore());
        }
    }//GEN-LAST:event_jCheckBox4ActionPerformed

    private void jCheckBox5ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jCheckBox5ActionPerformed
        // TODO add your handling code here:
        leavingMonster.setX(2);
        leavingMonster.setY(2);
        player.setCurrentMonster(leavingMonster);
        player.sendAnswer();
        if (player.getScore() >= 0) {
            jTextField.setText("Puntaje: " + player.getScore());
        }
    }//GEN-LAST:event_jCheckBox5ActionPerformed

    private void jCheckBox6ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jCheckBox6ActionPerformed
        // TODO add your handling code here:
        leavingMonster.setX(3);
        leavingMonster.setY(2);
        player.setCurrentMonster(leavingMonster);
        player.sendAnswer();
        if (player.getScore() >= 0) {
            jTextField.setText("Puntaje: " + player.getScore());
        }
    }//GEN-LAST:event_jCheckBox6ActionPerformed

    private void jCheckBox7ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jCheckBox7ActionPerformed
        // TODO add your handling code here:
        leavingMonster.setX(1);
        leavingMonster.setY(3);
        player.setCurrentMonster(leavingMonster);
        player.sendAnswer();
        if (player.getScore() >= 0) {
            jTextField.setText("Puntaje: " + player.getScore());
        }
    }//GEN-LAST:event_jCheckBox7ActionPerformed

    private void jCheckBox8ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jCheckBox8ActionPerformed
        leavingMonster.setX(2);
        leavingMonster.setY(3);
        player.setCurrentMonster(leavingMonster);
        player.sendAnswer();
        if (player.getScore() >= 0) {
            jTextField.setText("Puntaje: " + player.getScore());
        }
    }//GEN-LAST:event_jCheckBox8ActionPerformed

    private void jCheckBox9ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jCheckBox9ActionPerformed
        // TODO add your handling code here:
        leavingMonster.setX(3);
        leavingMonster.setY(3);
        player.setCurrentMonster(leavingMonster);
        player.sendAnswer();
        if (player.getScore() >= 0) {
            jTextField.setText("Puntaje: " + player.getScore());
        }
    }//GEN-LAST:event_jCheckBox9ActionPerformed

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        Receiver re = new Receiver(this);
        re.start();

    }//GEN-LAST:event_jButton1ActionPerformed

    private void jTextFieldActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jTextFieldActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jTextFieldActionPerformed

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
            java.util.logging.Logger.getLogger(PlayerConsole.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(PlayerConsole.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(PlayerConsole.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(PlayerConsole.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                System.setProperty("java.net.preferIPv4Stack", "true");
                PlayerConsole pc = new PlayerConsole();
                pc.setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.ButtonGroup buttonGroup1;
    private javax.swing.JButton jButton1;
    private javax.swing.JCheckBox jCheckBox1;
    private javax.swing.JCheckBox jCheckBox2;
    private javax.swing.JCheckBox jCheckBox3;
    private javax.swing.JCheckBox jCheckBox4;
    private javax.swing.JCheckBox jCheckBox5;
    private javax.swing.JCheckBox jCheckBox6;
    private javax.swing.JCheckBox jCheckBox7;
    private javax.swing.JCheckBox jCheckBox8;
    private javax.swing.JCheckBox jCheckBox9;
    private javax.swing.JInternalFrame jInternalFrame1;
    private javax.swing.JTextField jTextField;
    // End of variables declaration//GEN-END:variables

    @Override
    public void actionPerformed(ActionEvent e) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
}
