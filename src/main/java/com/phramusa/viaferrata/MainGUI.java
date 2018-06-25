/*
 * Copyright (C) 2018 phramusca ( https://github.com/phramusca/JaMuz/ )
 *
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with this program.  If not, see <http://www.gnu.org/licenses/>.
 */
package com.phramusa.viaferrata;

import com.phramusa.viaferrata.utils.Popup;
import com.phramusa.viaferrata.utils.ProgressBar;
import java.io.IOException;

/**
 *
 * @author phramusca ( https://github.com/phramusca/JaMuz/ )
 */
public class MainGUI extends javax.swing.JDialog {

	protected static ProgressBar progressBar;
	
	/**
	 * Creates new form MainGUI
	 * @param parent
	 * @param modal
	 */
	public MainGUI(java.awt.Frame parent, boolean modal) {
		super(parent, modal);
		initComponents();
		
		progressBar = (ProgressBar)jProgressBar1;
	}

	/**
	 * This method is called from within the constructor to initialize the form.
	 * WARNING: Do NOT modify this code. The content of this method is always
	 * regenerated by the Form Editor.
	 */
	@SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jButton1 = new javax.swing.JButton();
        jScrollPane1 = new javax.swing.JScrollPane();
        jTextArea1 = new javax.swing.JTextArea();
        jProgressBar1 = new com.phramusa.viaferrata.utils.ProgressBar();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setMaximumSize(new java.awt.Dimension(0, 0));

        jButton1.setText("Lister");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        jTextArea1.setColumns(20);
        jTextArea1.setRows(5);
        jScrollPane1.setViewportView(jTextArea1);

        jProgressBar1.setStringPainted(true);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 745, Short.MAX_VALUE)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jButton1)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jProgressBar1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jButton1)
                    .addComponent(jProgressBar1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 444, Short.MAX_VALUE)
                .addContainerGap())
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents
	
	String sep = "**************************************************";
	String end = "\n";
		
    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
		jButton1.setEnabled(false);
		progressBar.setIndeterminate("Starting ...");
//		ViaFerrataNet via = new ViaFerrataNet(new CallBack(), 
//				"https://www.viaferrata-fr.net/via-ferrata-10-La-Roche-%E0-l%27Agathe-Th%F4nes-Haute-Savoie.html");
		ViaFerrataNet via = new ViaFerrataNet(new CallBack());
		via.start();
    }//GEN-LAST:event_jButton1ActionPerformed

	class CallBack implements ICallBack {

		@Override
		public void setup(int size) {
			progressBar.setup(size);
		}
		
		@Override
		public void read(ViaFerrata via) {
			jTextArea1.append(sep+end+via.toString()+end);
			progressBar.progress(via.nom);
		}
		
		@Override
		public void completed() {
			jButton1.setEnabled(true);
		}

		@Override
		public void interrupted() {
			Popup.info("Canceled");
		}

		@Override
		public void error(IOException ex) {
			jTextArea1.append(ex.toString()+end);
		}
	}
	
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
		} catch (ClassNotFoundException 
				| InstantiationException 
				| IllegalAccessException 
				| javax.swing.UnsupportedLookAndFeelException ex) {
			java.util.logging.Logger.getLogger(MainGUI.class.getName())
					.log(java.util.logging.Level.SEVERE, null, ex);
		}
		//</editor-fold>
		
		/* Create and display the dialog */
		java.awt.EventQueue.invokeLater(() -> {
			MainGUI dialog = new MainGUI(new javax.swing.JFrame(), true);
			dialog.addWindowListener(new java.awt.event.WindowAdapter() {
				@Override
				public void windowClosing(java.awt.event.WindowEvent e) {
					System.exit(0);
				}
			});
			dialog.setVisible(true);
		});
	}

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jButton1;
    private javax.swing.JProgressBar jProgressBar1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTextArea jTextArea1;
    // End of variables declaration//GEN-END:variables
}