package paquete.buscador;

import java.awt.Desktop;
import java.io.File;
import java.io.IOException;

public class Archivo {

	public static void abrirArchivo(String ruta) throws IOException{
		File objetofile = new File (ruta);
        Desktop.getDesktop().open(objetofile);
	}
	
}
