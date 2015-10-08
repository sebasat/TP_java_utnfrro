package datos;


import java.sql.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import entidades.*;
import excepciones.AppExceptions;


public class DatosJuego {

	
	
	
	//guardar partida 
	public void guardarPartida(Partida p) throws AppExceptions{
		ResultSet rs=null;
		PreparedStatement statement=null;
        
		try {
        	statement = FactoryConexion.getInstancia().getConn().prepareStatement("insert into Partida (dni1, dni2) values (?,?)",
        			PreparedStatement.RETURN_GENERATED_KEYS);
            statement.setInt(1, p.getJugador1().getDni());
            statement.setInt(2, p.getJugador2().getDni());
            
            statement.execute();
            
            rs=statement.getGeneratedKeys();
			
            if(rs!=null && rs.next()){
				p.setNroPartida(rs.getInt(1));
			}
        } catch (SQLException ex) {
            
            throw new AppExceptions("Error al guardar partida", ex);
        }
        finally{
			
			try {
				if(rs!=null ) rs.close();
				if(statement != null) statement.close();
			} catch (SQLException e) {
				 
			}
			
			FactoryConexion.getInstancia().releaseConn();
		}
 
    }
	
	
	
	//buscar partida por nro
	public Partida getXnroPart(int nro) {
		ResultSet rs=null;
		PreparedStatement statement=null;
		Partida p=null;
		
		
		try {
        	statement = FactoryConexion.getInstancia().getConn().prepareStatement("SELECT * FROM Partida WHERE idPartida = ?");
        	statement.setInt(1, nro);
            rs = statement.executeQuery(); 
        	
            if(rs !=null && rs.next()){
        	      p = new Partida();
        	      Jugador j1 = new Jugador(); j1.setDni(rs.getInt("dni1"));
        	      Jugador j2 = new Jugador(); j2.setDni(rs.getInt("dni2"));
        	      
        	      p.setNroPartida(rs.getInt("idPartida"));
        	      p.setEstadoJuego(rs.getString("Turno"));
        	      p.setJugador1(j1);
        	      p.setJugador2(j2);
        	      
        	   
        	} 
        	
        }catch (SQLException ex) {
            ex.printStackTrace();
         }
		 finally{
			   try {
				   if(rs!=null)rs.close();
				   if(statement!=null) statement.close();
			   } catch (SQLException e) {
				    // TODO Auto-generated catch block
				     e.printStackTrace();
			     }
			   FactoryConexion.getInstancia().releaseConn();
		 }
		 return p;
	}
        
   


	
	//guarda las posiciones y piezas de una partida
	public void guardarPartidaPosicion(int nro, String pos, String pieza) {
		
		PreparedStatement statement=null;
		
		try {
        	statement = FactoryConexion.getInstancia().getConn().prepareStatement("insert into Partida_Posicion (idPartida, posicion, pieza) values (?,?,?)");
            statement.setInt(1, nro);
            statement.setString(2, pos);
            statement.setString(3, pieza);
            statement.execute();
        
		} catch (SQLException ex) {
            ex.printStackTrace();
        }
        finally{
			
			try {
				if(statement != null) statement.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			  }
			
			FactoryConexion.getInstancia().releaseConn();
		 }
 
    }
	
	
	
	//borrar las posiciones ocupadas de una partida re-iniciada
	public void delete(Partida p) {
		PreparedStatement statement=null;
        
		try {
        	statement = FactoryConexion.getInstancia().getConn().prepareStatement("delete from Partida_Posicion where idPartida = ?");
            statement.setInt(1, p.getNroPart());
            statement.execute();
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        finally{
			
			try {
				if(statement != null) statement.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			  }
			
			FactoryConexion.getInstancia().releaseConn();
		 }
 
    }
	
	
	
	
	//busca partidas de jugadores
	public ArrayList<Partida> getPartidasJugadores(Jugador j1, Jugador j2) throws AppExceptions{
		ResultSet rs=null;
		PreparedStatement statement=null;
		Partida p=null;
		ArrayList<Partida> partidas = new ArrayList<Partida>();
		
		try {
        	statement = FactoryConexion.getInstancia().getConn().prepareStatement("SELECT * FROM Partida WHERE (dni1 = ? AND dni2 = ?) OR (dni2 = ? AND dni1 = ?)");
        	statement.setInt(1, j1.getDni());
        	statement.setInt(2, j2.getDni());
        	statement.setInt(3, j1.getDni());
        	statement.setInt(4, j2.getDni());
            
        	rs = statement.executeQuery(); 
        	
            while(rs !=null && rs.next()){
        	      p = new Partida();
        	      Jugador jug1 = new Jugador(); jug1.setDni(rs.getInt("dni1"));
        	      Jugador jug2 = new Jugador(); jug2.setDni(rs.getInt("dni2"));
        	      
        	      p.setNroPartida(rs.getInt("idPartida"));
        	      p.setEstadoJuego(rs.getString("Turno"));
        	      p.setJugador1(jug1);
        	      p.setJugador2(jug2);
        	      partidas.add(p);
        	      
        	   
        	} 
        	
        } catch (SQLException ex) {
        	    throw new AppExceptions("Error al buscar partidas guardadas", ex);
          }
		  finally{
			   try {
				   if(rs!=null)rs.close();
				   if(statement!=null) statement.close();
			   } catch (SQLException e) {
				    // TODO Auto-generated catch block
				     e.printStackTrace();
			     }
			   FactoryConexion.getInstancia().releaseConn();
		  }
		 return partidas;
	}
	
	
	
	
	//obtiene las posiciones ocupadas de una determinada partida
	public Map<Posicion, Pieza> getPosicionesPartida(int nroPart) {
		ResultSet rs=null;
		PreparedStatement statement=null;
		
		Map<Posicion, Pieza> posiciones = new HashMap<Posicion, Pieza>();
		
		try {
        	statement = FactoryConexion.getInstancia().getConn().prepareStatement("SELECT * FROM Partida_Posicion WHERE idPartida = ?");
        	statement.setInt(1, nroPart);
        	
        	
        	rs = statement.executeQuery(); 
        	
            while(rs !=null && rs.next()){
            	Pieza pi;
            	
            	char colOrig = rs.getString("posicion").charAt(0);
            	int filaOrig = Integer.parseInt(String.valueOf(rs.getString("posicion").charAt(1))); 
            	Posicion pos = new Posicion(colOrig, filaOrig);
            	
        	    switch (rs.getString("pieza").charAt(0)) {
			       case 'P':
				       pi = new Peon(rs.getString("pieza").charAt(1));
				       break;
			       case 'C':
			    	   pi = new Caballo(rs.getString("pieza").charAt(1));
				       break;
			       case 'D':
			    	   pi = new Reina(rs.getString("pieza").charAt(1));
				       break;
			       case 'A':
			    	   pi = new Alfil(rs.getString("pieza").charAt(1));
				       break;
			       case 'R':
			    	   pi = new Rey(rs.getString("pieza").charAt(1));
				       break;
			       case 'T':
			    	   pi = new Torre(rs.getString("pieza").charAt(1));
				       break;
			       default:
			    	   pi = null;
				       break;
		        }
        	    posiciones.put(pos, pi);
        	} 
        	
         }catch (SQLException ex) {
            ex.printStackTrace();
          }
		  finally{
			   try {
				   if(rs!=null)rs.close();
				   if(statement!=null) statement.close();
			   } catch (SQLException e) {
				    // TODO Auto-generated catch block
				     e.printStackTrace();
			     }
			   FactoryConexion.getInstancia().releaseConn();
		  }
		  return posiciones;
	}
	
	
	
	
	//actualizar el estado de una partida
	public void actualizarEstadoPartida(Partida p){
		PreparedStatement stmt=null;
		
		try {
			stmt = FactoryConexion.getInstancia().getConn().prepareStatement(
					 "update Partida set Turno=? where idPartida=?"
					);
			
			stmt.setString(1, p.getEstado());
			stmt.setInt(2, p.getNroPart());
			
			stmt.execute();
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			
		} finally{
			
			try {
				if(stmt != null) stmt.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
			FactoryConexion.getInstancia().releaseConn();
		}
	}
	
	
	
	
	//buscar Jugador por dni
		public Jugador buscarJugador(int dni) {
			ResultSet rs=null;
			PreparedStatement statement=null;
			Jugador j=null;
			
			
			try {
	        	statement = FactoryConexion.getInstancia().getConn().prepareStatement("SELECT * FROM Jugador WHERE DNI = ?");
	        	statement.setInt(1, dni);
	            rs = statement.executeQuery(); 
	        	
	            if(rs !=null && rs.next()){
	        	      j = new Jugador();
	        	      j.setDni(rs.getInt("DNI"));
	        	      
	        	} 
	        	
	        } catch (SQLException ex) {
	            ex.printStackTrace();
	          }
			  finally{
				   try {
					   if(rs!=null)rs.close();
					   if(statement!=null) statement.close();
				   } catch (SQLException e) {
					    // TODO Auto-generated catch block
					     e.printStackTrace();
				     }
				   FactoryConexion.getInstancia().releaseConn();
			  }
			 return j;
		}
		
		
		
		
		//guardar jugador 
		public void guardarJugador(Jugador j) throws AppExceptions{
					ResultSet rs=null;
					PreparedStatement statement=null;
			        
					try {
			        	statement = FactoryConexion.getInstancia().getConn().prepareStatement("insert into Jugador (DNI, Apellido, Nombre) values (?,?,?)");
			            statement.setInt(1, j.getDni());
			            statement.setString(2, j.getApellido());
			            statement.setString(3, j.getNombre());
			            
			            statement.execute();
			            
			            
			        } catch (SQLException ex) {
			            ex.printStackTrace();
			            throw new AppExceptions("Error al guardar Jugador", ex);
			        }
			        finally{
						
						try {
							if(rs!=null ) rs.close();
							if(statement != null) statement.close();
						} catch (SQLException e) {
							 
						}
						
						FactoryConexion.getInstancia().releaseConn();
					}
			 
		}
}
