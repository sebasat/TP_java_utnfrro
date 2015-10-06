package iu;

import java.util.ArrayList;


import javax.swing.AbstractListModel;

public class ModeloListaPiezas extends AbstractListModel<String> {

	
	private static final long serialVersionUID = 1L;
	
	private ArrayList<String> lista = new ArrayList<String>();
	
	
	public ModeloListaPiezas(){
	
	}
	
	
	public void borrarLista(){
		lista.clear();
	}
	
	
	
	public void addItemLista(String s){
		lista.add(s);
		
	}
	
	
	@Override
	public String getElementAt(int arg0) {
		
		return lista.get(arg0);
	}

	
	@Override
	public int getSize() {
		// TODO Auto-generated method stub
		return lista.size();
	}
	
	public void update() {
        this.fireContentsChanged(this, 0, lista.size() - 1);
    }

}
