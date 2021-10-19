package main;

import java.util.List;
import java.util.Scanner;

import modelo.entidad.Coche;
import modelo.negocio.GestorCoche;

public class Main {
	public static void main(String[] args) {

		Scanner sc = new Scanner(System.in);
		System.out.println("Escriba el numero de lo que desa hacer ");
		System.out.print("(1.Añadir, 2.Eliminar, 3.Modificar, 4.Consultar/Listar o 5.Exportar a PDF):");

		String casos = sc.nextLine();

		switch (casos) {
		case "1":

			Coche cocheA = new Coche();
			System.out.print("Escribe la marca: ");
			String marcaA = sc.nextLine();
			cocheA.setMarca(marcaA);
			System.out.print("Escribe el modelo: ");
			String modeloA = sc.nextLine();
			cocheA.setModelo(modeloA);
			System.out.print("Escribe los kilometros: ");
			String kmA = sc.nextLine();
			cocheA.setKm(Integer.parseInt(kmA));
			System.out.print("Escribe la matricula: ");
			String matriculaA = sc.nextLine();
			cocheA.setMatricula(matriculaA);

			GestorCoche gcA = new GestorCoche();
			boolean alta = gcA.alta(cocheA);
			if (alta) {
				System.out.println("El coche se ha incluido");
			} else {
				if (marcaA.isEmpty() || modeloA.isEmpty() || matriculaA.isEmpty()) {
					System.out.println("LAS CASILLAS MODELO MATRICULA Y MARCA SON OBLIGATORIAS");
				} else {
					System.out.println("LA LONGITUD DE CARACTERES DE UNA MATRICULA ES DE 7 CARACTERES");

				}
			}
			break;
		case "2":
			GestorCoche gcE = new GestorCoche();
			System.out.print("Escribe la id del coche: ");
			String idE = sc.nextLine();
			boolean baja = gcE.baja(Integer.parseInt(idE));
			if (baja) {
				System.out.println("El coche se ha eliminado");
			} else {
				System.out.println("El coche NO se ha eliminado");
			}
			break;
		case "3":
			Coche cocheM = new Coche();
			System.out.print("Escribe la marca: ");
			String marcaM = sc.nextLine();
			cocheM.setMarca(marcaM);
			System.out.print("Escribe el modelo: ");
			String modeloM = sc.nextLine();
			cocheM.setModelo(modeloM);
			System.out.print("Escribe los kilometros: ");
			String kmM = sc.nextLine();
			cocheM.setKm(Integer.parseInt(kmM));
			System.out.print("Escribe la matricula: ");
			String matriculaM = sc.nextLine();
			cocheM.setMatricula(matriculaM);

			GestorCoche gcM = new GestorCoche();

			boolean modificar = gcM.modificar(cocheM);
			if (modificar) {
				System.out.println("El coche se ha modificado");
			} else {

				System.out.println("El coche NO se ha modificado");
			}
			break;
		case "4":
			GestorCoche gcL = new GestorCoche();
			System.out.print("Escribe la matricula: ");
			String idL = sc.nextLine();
			Coche coche = gcL.obtener(Integer.parseInt(idL));
			System.out.println(coche);

			System.out.println("*******************");

			List<Coche> listaCoches = gcL.listar();
			for (Coche c : listaCoches) {
				System.out.println(c);
			}
			break;
		case "5":
			GestorCoche gGPDF = new GestorCoche();
			try {
				gGPDF.crearFicheroPDF();
				System.out.println("Se ha creado el fichero satisfactoriamente");
			} catch (Exception e) {
				System.out.println("Ha ocurrido un error generando el fichero");
			}
			break; 
		}
		}

	}
 
