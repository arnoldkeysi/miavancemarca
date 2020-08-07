package igu.familias;


import data.familiaData;
import entities.familiaArticulos;
import java.util.ArrayList;
import java.util.List;
import javax.swing.table.AbstractTableModel;

/**
 *
 * @author Asullom
 */
public class FamiliasTableModel extends AbstractTableModel {

    private List<familiaArticulos> lis = new ArrayList();
    private String[] columns = {"#", "Nombres", "Descripción"};
    private Class[] columnsType = {Integer.class, String.class, String.class};

    public FamiliasTableModel() {
        lis = familiaData.list("");
    }

    public FamiliasTableModel(String filter) {
        lis = familiaData.list(filter);
    }
    

    @Override
    public Object getValueAt(int row, int column) {
        familiaArticulos d = (familiaArticulos) lis.get(row);
        switch (column) {
            case 0:
                return row + 1;
            case 1:
                return d.getNombreFamilia();
            case 2:
                return d.getDescripcion();
            
            default:
                return null;
        }
    }
/*
    @Override
    public void setValueAt(Object valor, int row, int column) {
        familiaArticulos d = (familiaArticulos) lis.get(row);
        switch (column) {
            
           // case 0:
           //     int id = 0;
           //     try {
            //        if (valor instanceof Number) {
           //             id = Integer.parseInt("" + valor);
           //         }
           //     } catch (NumberFormatException nfe) {
            //        System.err.println("" + nfe);
             //   }
            //    d.setId(id);
             //   break;
             
            case 1:
                d.setNombres("" + valor);
                break;
            case 2:
                d.setInfoadic("" + valor);
                break;

        }
        this.fireTableRowsUpdated(row, row);
        //fireTableCellUpdated(row, row);
    }
*/
    @Override
    public boolean isCellEditable(int row, int column) {
        //familiaArticulos c = (familiaArticulos) lis.get(row);
        if (column >= 0 && column != 0) {
            //return true;
        }
        return false;//bloquear edicion
    }

    @Override
    public String getColumnName(int column) {
        return columns[column];
    }

    @Override
    public Class getColumnClass(int column) {
        return columnsType[column];
    }

    @Override
    public int getRowCount() {
        return lis.size();
    }

    @Override
    public int getColumnCount() {
        return columns.length;
    }

    public void addRow(familiaArticulos d) { // con db no se usa
        this.lis.add(d);
        //this.fireTableDataChanged();
        this.fireTableRowsInserted(lis.size(), lis.size());
    }

    public void removeRow(int linha) { // con db no se usa
        this.lis.remove(linha);
        this.fireTableRowsDeleted(linha, linha);
    }

    public Object getRow(int row) { // usado para paintForm
        this.fireTableRowsUpdated(row, row);
        return lis.get(row);
    }

}
