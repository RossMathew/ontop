/***
 * Copyright (c) 2008, Mariano Rodriguez-Muro.
 * All rights reserved.
 *
 * The OBDA-API is licensed under the terms of the Lesser General Public
 * License v.3 (see OBDAAPI_LICENSE.txt for details). The components of this
 * work include:
 * 
 * a) The OBDA-API developed by the author and licensed under the LGPL; and, 
 * b) third-party components licensed under terms that may be different from 
 *   those of the LGPL.  Information about such licenses can be found in the 
 *   file named OBDAAPI_3DPARTY-LICENSES.txt.
 */

package inf.unibz.it.obda.gui.swing.datasource.panels;


import inf.unibz.it.obda.api.controller.DatasourcesController;
import inf.unibz.it.obda.api.datasource.JDBCConnectionManager;
import inf.unibz.it.obda.domain.DataSource;
import inf.unibz.it.obda.gui.swing.datasource.DatasourceCellRenderer;
import inf.unibz.it.obda.gui.swing.datasource.DatasourceComboBoxModel;

import java.awt.EventQueue;
import java.sql.ResultSet;

import javax.swing.JOptionPane;
import javax.swing.table.TableModel;


/**
 *
 * @author  mariano
 */
public class SQLQueryPanel extends javax.swing.JPanel {
    
	
	DatasourcesController dsc=null;
	String execute_Query;

  private DatasourceComboBoxModel dsComboModel;
	private DatasourceCellRenderer dsComboBoxRenderer;

    /** Creates new form SQLQueryPanel */
    public SQLQueryPanel(DatasourcesController dsc,String execute_Query) {
    	
    	this(dsc);
    	this.execute_Query=execute_Query;
    	show_Result_Query();    
    }
    
    
    public SQLQueryPanel(DatasourcesController dsc) {
    	this.dsc = dsc;

        DataSource[] datasources = dsc.getAllSources().values().toArray(new DataSource[0]);
        dsComboModel = new DatasourceComboBoxModel(datasources);
        dsComboBoxRenderer = new DatasourceCellRenderer();

        initComponents();
    }
    
    
    /** This method is called from within the constructor to
     * initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is
     * always regenerated by the Form Editor.
     */
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {
        java.awt.GridBagConstraints gridBagConstraints;

        splSqlQuery = new javax.swing.JSplitPane();
        pnlSqlQuery = new javax.swing.JPanel();
        lblSqlQuery = new javax.swing.JLabel();
        scrSqlQuery = new javax.swing.JScrollPane();
        txtSqlQuery = new javax.swing.JTextArea();
        cmdExecute = new javax.swing.JButton();
        pnlQueryResult = new javax.swing.JPanel();
        scrQueryResult = new javax.swing.JScrollPane();
        tblQueryResult = new javax.swing.JTable();
        cmbDatasource = new javax.swing.JComboBox();

        setFont(new java.awt.Font("Arial", 0, 18)); // NOI18N
        setLayout(new java.awt.BorderLayout());

        splSqlQuery.setOrientation(javax.swing.JSplitPane.VERTICAL_SPLIT);

        pnlSqlQuery.setMinimumSize(new java.awt.Dimension(156, 100));
        pnlSqlQuery.setPreferredSize(new java.awt.Dimension(156, 100));
        pnlSqlQuery.setLayout(new java.awt.GridBagLayout());

        lblSqlQuery.setText("SQL Query:");
        lblSqlQuery.setVerticalAlignment(javax.swing.SwingConstants.TOP);
        lblSqlQuery.setHorizontalTextPosition(javax.swing.SwingConstants.LEFT);
        lblSqlQuery.setRequestFocusEnabled(false);
        lblSqlQuery.setVerifyInputWhenFocusTarget(false);
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.anchor = java.awt.GridBagConstraints.WEST;
        pnlSqlQuery.add(lblSqlQuery, gridBagConstraints);

        txtSqlQuery.setColumns(20);
        txtSqlQuery.setRows(2);
        txtSqlQuery.setBorder(null);
        scrSqlQuery.setViewportView(txtSqlQuery);

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridheight = 2;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHWEST;
        gridBagConstraints.weightx = 2.0;
        gridBagConstraints.weighty = 2.0;
        pnlSqlQuery.add(scrSqlQuery, gridBagConstraints);

        cmdExecute.setText("Excecute");
        cmdExecute.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cmdExecuteActionPerformed(evt);
            }
        });
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridwidth = java.awt.GridBagConstraints.RELATIVE;
        gridBagConstraints.gridheight = java.awt.GridBagConstraints.RELATIVE;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.WEST;
        pnlSqlQuery.add(cmdExecute, gridBagConstraints);

        splSqlQuery.setLeftComponent(pnlSqlQuery);

        pnlQueryResult.setLayout(new java.awt.BorderLayout());

        tblQueryResult.setFont(new java.awt.Font("Arial", 0, 18)); // NOI18N
        tblQueryResult.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Results"
            }
        ));
        tblQueryResult.setRowHeight(21);
        scrQueryResult.setViewportView(tblQueryResult);

        pnlQueryResult.add(scrQueryResult, java.awt.BorderLayout.CENTER);

        cmbDatasource.setModel(dsComboModel);
        cmbDatasource.setRenderer(dsComboBoxRenderer);
        pnlQueryResult.add(cmbDatasource, java.awt.BorderLayout.PAGE_END);

        splSqlQuery.setRightComponent(pnlQueryResult);

        add(splSqlQuery, java.awt.BorderLayout.CENTER);
    }// </editor-fold>//GEN-END:initComponents

    private void cmdExecuteActionPerformed(java.awt.event.ActionEvent evt) {                                               

		EventQueue.invokeLater(new Runnable() {
			public void run() {
					// "com.mysql.jdbc.Driver",
					// "jdbc:mysql://localhost/sattest", "mastro", "mastro");
//					ResultSetTableModelFactory modelfactory = ResultSetTableModelFactory.getInstance(dsc.getCurrentDataSource());
//
//					/***********************************************************
//					 * get Previous model, if any and close it before
//					 * proceeding.
//					 */
					TableModel oldmodel = tblQueryResult.getModel();
					if ((oldmodel != null) && (oldmodel instanceof IncrementalResultSetTableModel)) {

						IncrementalResultSetTableModel rstm = (IncrementalResultSetTableModel) oldmodel;
						rstm.close();
					}
//
//					queryTable.setModel(modelfactory.getResultSetTableModel(queryField.getText()));

					DataSource current_ds = dsc.getCurrentDataSource();
					if(current_ds == null){ 				
						JOptionPane.showMessageDialog(null, "Pleas select a data source first");
					}else{
						JDBCConnectionManager man =JDBCConnectionManager.getJDBCConnectionManager();
						try {
							man.setProperty(JDBCConnectionManager.JDBC_AUTOCOMMIT, false);
							man.setProperty(JDBCConnectionManager.JDBC_RESULTSETTYPE, ResultSet.TYPE_FORWARD_ONLY);
							if(!man.isConnectionAlive(current_ds.getSourceID())){
								try {
									man.createConnection(current_ds);
								} catch (Exception e) {
									e.printStackTrace();
								}
							}
						
							java.sql.ResultSet set = man.executeQuery(current_ds.getSourceID(), txtSqlQuery.getText(),current_ds);
							//java.sql.ResultSet set = man.executeQuery(current_ds.getUri(), execute_query,current_ds); //EK
							IncrementalResultSetTableModel model = new IncrementalResultSetTableModel(set);
							tblQueryResult.setModel(model);
						} catch (Exception e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
							throw new RuntimeException(e);
							
						}
					}
			}
		});
    }                                             
//throw new RuntimeException(e);
    
 
//    /***************************************************************************
//	 * Verifies that there is a connected result set factory for the current
//	 * data sources. If there is no factory it creates it, if there is, but the
//	 * connection data for the current source and the data used to create the
//	 * factory is different it closes the previous one and creates a new one.
//	 * 
//	 * If there exists a factory and its data is ok it checks if it is
//	 * connected, if it is not it tries to connect it.
//	 * 
//	 * @throws NoDatasourceSelectedException
//	 * @throws NoConnectionException
//	 */
//	private ResultSetTableModelFactory getResultSetModelFactory() throws NoDatasourceSelectedException, NoConnectionException,
//			ClassNotFoundException, SQLException {
//
//	}
    private void show_Result_Query(){
    	txtSqlQuery.setText(execute_Query);
    	TableModel oldmodel = tblQueryResult.getModel();
    	
		if ((oldmodel != null) && (oldmodel instanceof IncrementalResultSetTableModel)) {

			IncrementalResultSetTableModel rstm = (IncrementalResultSetTableModel) oldmodel;
			rstm.close();
		}
		
		DataSource current_ds = dsc.getCurrentDataSource();
		if(current_ds == null){ 
		
			JOptionPane.showMessageDialog(null, "Pleas select a data source first");
		}else{
			JDBCConnectionManager man =JDBCConnectionManager.getJDBCConnectionManager();
			try {
				man.setProperty(JDBCConnectionManager.JDBC_AUTOCOMMIT, false);
				man.setProperty(JDBCConnectionManager.JDBC_RESULTSETTYPE, ResultSet.TYPE_FORWARD_ONLY);
				if(!man.isConnectionAlive(current_ds.getSourceID())){
					try {
						man.createConnection(current_ds);
					} catch (Exception e) {
						e.printStackTrace();
					}
				}
			
				//java.sql.ResultSet set = man.executeQuery(current_ds.getUri(), queryField.getText(),current_ds); original
				java.sql.ResultSet set = man.executeQuery(current_ds.getSourceID(), execute_Query,current_ds); //EK
				IncrementalResultSetTableModel model = new IncrementalResultSetTableModel(set);
				tblQueryResult.setModel(model);
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
				
				
			}
		}
}
    	
    

    
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JComboBox cmbDatasource;
    private javax.swing.JButton cmdExecute;
    private javax.swing.JLabel lblSqlQuery;
    private javax.swing.JPanel pnlQueryResult;
    private javax.swing.JPanel pnlSqlQuery;
    private javax.swing.JScrollPane scrQueryResult;
    private javax.swing.JScrollPane scrSqlQuery;
    private javax.swing.JSplitPane splSqlQuery;
    private javax.swing.JTable tblQueryResult;
    private javax.swing.JTextArea txtSqlQuery;
    // End of variables declaration//GEN-END:variables
    
}
