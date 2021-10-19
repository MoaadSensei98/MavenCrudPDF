package modelo.persistencia.interfaces;

import java.util.List;

import modelo.entidad.Coche;

public interface DaoCoche {
	boolean alta(Coche p);
	boolean baja(int id);
	boolean modificar(Coche p);
	Coche obtener(int id);
	List<Coche> listar();
}
