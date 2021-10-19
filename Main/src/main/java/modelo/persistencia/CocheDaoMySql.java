package modelo.persistencia;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import modelo.entidad.Coche;
import modelo.persistencia.interfaces.DaoCoche;

public class CocheDaoMySql implements DaoCoche{

	private Connection conexion;
	
	public boolean abrirConexion(){
		String url = "jdbc:mysql://localhost:3306/bbdd";
		String usuario = "root";
		String password = "";
		try {
			conexion = DriverManager.getConnection(url,usuario,password);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return false;
		}
		return true;
	}
	
	public boolean cerrarConexion(){
		try {
			conexion.close();
		} catch (SQLException e) {
			e.printStackTrace();
			return false;
		}
		return true;
	}
	
	
	
	@Override
	public boolean alta(Coche p) {
		if(!abrirConexion()){
			return false;
		}
		boolean alta = true;
		
		String query = "insert into coche (MARCA,MODELO,KM,MATRICULA) "
				+ " values(?,?,?,?)";
		try {
			//preparamos la query con valores parametrizables(?)
			PreparedStatement coche = conexion.prepareStatement(query);
			coche.setString(1, p.getMarca());
			coche.setString(2, p.getModelo());
			coche.setInt(3, p.getKm());
			coche.setString(4, p.getMatricula()); 
			
			int numeroFilasAfectadas = coche.executeUpdate();
			if(numeroFilasAfectadas == 0) {
				alta = false;
			}
		} catch (SQLException e ) {
			System.out.println("alta -> Error al insertar: " + p);
			alta = false;
			 e.printStackTrace();
			 
 		}  
		finally{
			cerrarConexion();
		}
		
		return alta;
	}

	@Override
	public boolean baja(int id) {
		if(!abrirConexion()){
			return false;
		}
		
		boolean borrado = true;
		String query = "delete from coche where id = ?";
		try {
			PreparedStatement coche = conexion.prepareStatement(query);
			//sustituimos la primera interrgante por la id
			coche.setInt(1, id);
			
			int numeroFilasAfectadas = coche.executeUpdate();
			if(numeroFilasAfectadas == 0)
				borrado = false;
		} catch (SQLException e) {
			System.out.println("baja -> No se ha podido dar de baja"
					+ " el id " + id);
			e.printStackTrace();
		} finally {
			cerrarConexion();
		}
		return borrado; 
	}

	@Override
	public boolean modificar(Coche p) {
		if(!abrirConexion()){
			return false;
		}
		boolean modificado = true;
		String query = "update coche set MARCA=?, MODELO=?, "
				+ "KM=?, MATRICULA=? WHERE ID=?";
		try {
			PreparedStatement coche = conexion.prepareStatement(query);
			coche.setString(1, p.getMarca());
			coche.setString(2, p.getModelo());
			coche.setInt(3, p.getKm());
			coche.setString(4, p.getMatricula()); 
			int numeroFilasAfectadas = coche.executeUpdate();
			if(numeroFilasAfectadas == 0)
				modificado = false;
			else
				modificado = true;
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			System.out.println("modificar -> error al modificar el "
					+ " coche " + p);
			modificado = false;
			e.printStackTrace();
		} finally{
			cerrarConexion();
		}
		
		return modificado;
	}

	@Override
	public Coche obtener(int id) {
		if(!abrirConexion()){
			return null;
		}		
		Coche co = null;
		
		String query = "select ID,MARCA,MODELO,KM,MATRICULA from coche "
				+ "where id = ?";
		try {
			PreparedStatement coche = conexion.prepareStatement(query);
			coche.setInt(1, id);
			
			ResultSet rs = coche.executeQuery();
			while(rs.next()){
				co = new Coche();
				co.setId(rs.getInt(1));
				co.setMarca(rs.getString(2));
				co.setModelo(rs.getString(3));
				co.setKm(rs.getInt(4));
				co.setMatricula(rs.getString(5));
			}
		} catch (SQLException e) {
			System.out.println("obtener -> error al obtener el "
					+ "coche con id " + id);
			e.printStackTrace();
		} finally {
			cerrarConexion();
		}
		
		
		return co;
	}

	@Override
	public List<Coche> listar() {
		if(!abrirConexion()){
			return null;
		}		
		List<Coche> listaCoches = new ArrayList<>();
		
		String query = "select ID,MARCA,MODELO,KM,MATRICULA from coche";
		try {
			PreparedStatement ps = conexion.prepareStatement(query);
			
			ResultSet rs = ps.executeQuery();
			
			while(rs.next()){
				Coche co = new Coche();
				co.setId(rs.getInt(1));
				co.setMarca(rs.getString(2));
				co.setModelo(rs.getString(3));
				co.setKm(rs.getInt(4));
				co.setMatricula(rs.getString(5));
		 
				listaCoches.add(co);
			}
		} catch (SQLException e) {
			System.out.println("listar -> error al obtener las "
					+ "personas");
			e.printStackTrace();
		} finally {
			cerrarConexion();
		}
		
		
		return listaCoches;
	}
	
	//Bloque estatico, los bloques estaticos son ejecutados
	//por java JUSTO ANTES de ejecutar el metodo main()
	//Java busca todos los metodos staticos que haya en el programa
	//y los ejecuta
	/*
	static{
		try {
			//Esta sentencia carga del jar que hemos importado
			//una clase que se llama Driver que esta en el paqueta
			//com.mysql.jdbc. Esta clase se carga previamente en
			//java para más adelante ser llamada
			//Esto SOLO es necesario si utilizamos una versión java anterior
			//a la 1.7 ya que desde esta versión java busca automaticamente 
			//los drivers
			Class.forName("com.mysql.jdbc.Driver");
			System.out.println("Driver cargado");
		} catch (ClassNotFoundException e) {
			System.out.println("Driver NO cargado");
			e.printStackTrace();
		}
	}*/

}
