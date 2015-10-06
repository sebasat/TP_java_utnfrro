package entidades;

public class Jugador {
      private int dni, id;
      private String nombre, apellido;
	
      
      
    public Jugador(){
    	
    }
      
     
    
    public int getId() {
		return id;
	}
    
    
    public void setId(int id){
    	this.id = id;
    }
    
    
    
    public int getDni() {
		return dni;
	}
	
    public void setDni(int dni) {
		this.dni = dni;
	}
	
    public String getNombre() {
		return nombre;
	}
	
    public void setNombre(String nombre) {
		this.nombre = nombre;
	}
	
    public String getApellido() {
		return apellido;
	}
	
    public void setApellido(String apellido) {
		this.apellido = apellido;
	}
      
      
}
