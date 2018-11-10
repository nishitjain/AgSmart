/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package logic;

import gui.CropSequencingMethodWindow;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author nishi
 */
public class CropSequencer 
{
    public void populateTable(JTable table, int days)
    {
        try {
            Class.forName("oracle.jdbc.driver.OracleDriver");
            Connection connect = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe","system","nishhit.777");
            Statement stmt = connect.createStatement();
            String query = "select distinct(crop_name),plant_start_date,plant_end_date,harvest_start_date,harvest_end_date,total_days,harvested_area,temp_at_planting,precip_at_planting from agsmart.SEQUENCE_DATA where plant_start_day between "+days+" and "+(days+60);
            ResultSet rs = stmt.executeQuery(query);
            while(table.getRowCount()>0)
                {
                    ((DefaultTableModel)table.getModel()).removeRow(0);
                }
                int columns = rs.getMetaData().getColumnCount();
                while(rs.next())
                {
                    Object[] row = new Object[columns];
                    for(int i=1;i<=columns;i++)
                    {
                        row[i-1] = rs.getObject(i);
                    }
                    ((DefaultTableModel)table.getModel()).insertRow(rs.getRow()-1, row);
                }
            rs.close();
            stmt.close();
            connect.close();
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(CropSequencer.class.getName()).log(Level.SEVERE, null, ex);
        } catch (SQLException ex) {
            Logger.getLogger(CropSequencer.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
