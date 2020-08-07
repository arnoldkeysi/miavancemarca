package igu.articles;

import data.ArticulosData;
import entities.Articulos;
import igu.util.Config;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;
import javax.swing.table.AbstractTableModel;


/**
 *
 * @author Asullom
 */
public class TareaTableModel extends AbstractTableModel {

    private List<Articulos> lis = new ArrayList();
    private String[] columns = {"#", "Asignatura", "Detalles", "Fecha Entrega", "Fecha Vencimiento"};
    private Class[] columnsType = {Integer.class, String.class, String.class, String.class, String.class};// Date.class
    
    SimpleDateFormat iguSDF = new SimpleDateFormat(Config.DEFAULT_DATE_STRING_FORMAT_PE);
    

    public TareaTableModel() {
        lis = ArticulosData.list("");
    }

    public TareaTableModel(String filter) {
        lis = ArticulosData.list(filter);
    }

    @Override
    public Object getValueAt(int row, int column) {
        Articulos d = (Articulos) lis.get(row);
        switch (column) {
            case 0:
                return row + 1;
            case 1:
                return d.getNombre();
            case 2:
                return d.getCodigo();
            case 3:
                return iguSDF.format(d.getFecha_ingreso());
            case 4:
                return iguSDF.format(d.getFecha_registro()); //return (d.getFecha_ven(); 
            default:
                return null;
        }
    }

    /*
    @Override
    public void setValueAt(Object valor, int row, int column) {
        Articulos d = (Articulos) lis.get(row);
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
                d.setAsignatura("" + valor);
                break;
            case 2:
                d.setDetalles("" + valor);
                break;

        }
        this.fireTableRowsUpdated(row, row);
        //fireTableCellUpdated(row, row);
    }
     */
    @Override
    public boolean isCellEditable(int row, int column) {
        //Articulos c = (Articulos) lis.get(row);
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

    public void addRow(Articulos d) { // con db no se usa
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
