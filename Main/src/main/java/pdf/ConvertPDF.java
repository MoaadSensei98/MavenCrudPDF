package pdf;
import java.io.IOException;
import java.util.List;

import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.pdmodel.PDPage;
import org.apache.pdfbox.pdmodel.PDPageContentStream;
import org.apache.pdfbox.pdmodel.font.PDType1Font;

import modelo.entidad.Coche;
 
 
public class ConvertPDF {
	public void generarFichero(List<Coche> lista) throws Exception{
				
		try (PDDocument doc = new PDDocument()) {

			PDPage myPage = new PDPage();
			doc.addPage(myPage);

			try (PDPageContentStream cont = new PDPageContentStream(doc, myPage)) {

				printTituloPdf(cont);
				
				printCochesPdf(cont,lista);

				cont.endText();		
				
			} catch (IOException ioe) {
				ioe.printStackTrace();
				throw ioe;
			}
			
			doc.save("src/main/coches.pdf");
			System.out.println("Fichero pdf creado en src/main/coches.pdf");
			System.out.println("Refresque el proyecto en caso de que no aparezca");
			
		}catch (IOException ioe) {
			ioe.printStackTrace();
		}
	}

	/**
	 * Printa los coches de la base de datos en el pdf
	 * @param cont
	 * @throws IOException	 */
	private void printCochesPdf(PDPageContentStream cont, List<Coche> coches) 
			throws IOException {
				for (int i = 0; i < coches.size(); i++) {
			String carLine = coches.get(i).getMarca() + " " + coches.get(i).getModelo() + " " +
							coches.get(i).getKm() + " " + coches.get(i).getMatricula();
			cont.showText(carLine);
			cont.newLine();
		}
	}

	/**
	 * Printa el nombre de los atributos del titulo en el pdf
	 * @param cont
	 * @throws IOException
	 */
	private void printTituloPdf(PDPageContentStream cont) throws IOException {
		
		String[] headers = {"Marca   ", "Modelo   ", "Km   ", "Matricula"};
		
		cont.beginText();

		cont.setFont(PDType1Font.TIMES_ROMAN, 12);
		cont.setLeading(14.5f);

		cont.newLineAtOffset(25, 700);
		String headersLine = "";
		for (String h: headers) headersLine += h + " ";
		cont.showText(headersLine);

		cont.newLine();
	}
}
