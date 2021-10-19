  package modelo.negocio;

import java.util.List;

import modelo.entidad.Coche;
//import modelo.persistencia.PersonaDaoDerby;
import modelo.persistencia.CocheDaoMySql;
import modelo.persistencia.interfaces.DaoCoche;
 import pdf.ConvertPDF;

//Aqu� irian todas las reglas de negocio de nuestra aplicacion, se aplicarian
//antes de llamar a la BBDD. Por ejemplo si la persona tiene que tener al
//menos un nombre con 3 caracteres 

public class GestorCoche {
	
	//Aqui podemos jugar con cambiar el dao que queremos usar
	//podemos usar PersonaDaoDerby o PersonaDaoMySql
	//Gracias a las interfaces solo tenemos que cambiar el objeto
	DaoCoche personaDao = new CocheDaoMySql();
	
	public boolean alta(Coche p){
		//aplicamos la regla de negocio
		if(p.getMatricula().length() == 7 && !p.getMarca().isEmpty() && !p.getModelo().isEmpty() && !p.getMatricula().isEmpty() ) {
			boolean alta = personaDao.alta(p);
			return alta; 
		 	
		}
		return false;
	}
	
	public boolean baja(int id){
		boolean baja = personaDao.baja(id);
		return baja;
	}
	
	public boolean modificar(Coche p){
		//aplicamos la regla de negocio
		if(p.getMatricula().length() == 7 && !p.getMarca().isEmpty() && !p.getModelo().isEmpty() && !p.getMatricula().isEmpty() ) {
			boolean modificada = personaDao.modificar(p);
			return modificada;
		}
		return false;
	}
	
	public Coche obtener(int id){
		Coche persona = personaDao.obtener(id);
		return persona;
	}
	
	public List<Coche> listar(){
		List<Coche> listaPersonas = personaDao.listar();
		return listaPersonas;
	}
	 public void crearFicheroPDF() throws Exception{
		ConvertPDF ctPDF = new ConvertPDF();
		ctPDF.generarFichero(listar());
	 }
	
}
