package iu;

import java.util.ArrayList;
import java.util.List;
import entidades.*;
import javax.swing.table.AbstractTableModel;



public class ModeloTablaPartidas extends AbstractTableModel {

	
	private static final long serialVersionUID = 1L;
	
	private List<Partida> partidas = new ArrayList<Partida>();
	private String[] columnas = {"Nro","Jugador 1","Jugador 2","Turno"};
	
	
	
	
	public ModeloTablaPartidas(List<Partida> part){
		partidas = part;
	}
	
	
	
	
	@Override
	public int getColumnCount() {
		// TODO Auto-generated method stub
		return columnas.length;
	}
	
	
	
	@Override
	public int getRowCount() {
		// TODO Auto-generated method stub
		if(partidas == null)
			return 0;
		return partidas.size();
	}
	
	
	
	
	public boolean isCellEditable (int row, int column)
	   {
	       
	       return false;
	}

	
	
	
	@Override
	public Object getValueAt(int fila, int columna) {
		Object result = null;
		switch(columna){
		case 0:
			 return result = partidas.get(fila).getNroPart();
		case 1:
			 return result = partidas.get(fila).getJugador1().getDni();
		case 2:
			 return result = partidas.get(fila).getJugador2().getDni();
		case 3:
			 return result = partidas.get(fila).getEstado();
		default:
			 return result;
		
		
		}
		
	}
	
	
	public String getColumnName(int column) {
	    return columnas[column];
	}

}
