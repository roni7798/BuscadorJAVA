package paquete.buscador;

import java.io.File;
import java.util.ArrayList;
import java.util.Arrays;

import javax.swing.JCheckBox;

public class Buscador {
	static ArrayList<String> archivosEncontrados = new ArrayList<>();
	static ArrayList<String> rutasEncontradas = new ArrayList<>();
	public static ArrayList<String> buscar(String busqueda, boolean chckbxImagen, boolean chckbxVideo, boolean chckbxTxt, boolean chckbxArchivosOffice, boolean chckbxBuscarTodo){
		//String busqueda = "contar";//"chromedriver";
		String ruta = System.getProperty("user.home");
		String sCarpAct = System.getProperty("user.home");
		File carpeta = new File(sCarpAct);
		String[] listado = carpeta.list();
		Arrays.sort(listado);
		if (listado == null || listado.length == 0) {
			System.out.println("No hay elementos dentro de la carpeta actual");
		} else {
			archivosEncontrados=encontrarBusqueda(ruta,carpeta, busqueda);
		}
		return archivosEncontrados;
	}

	public static ArrayList<String>  encontrarBusqueda(String ruta, File c, String busqueda) {	
		String[] list = c.list();
		c = new File(ruta);
		if(list!=null){
			Arrays.sort(list);
			if(validarRutasSistema(c)){
		for (int i = 0; i < list.length; i++) {
			c= new File(ruta+"/"+list[i]);
			if(c.isDirectory() && c.canRead()){
				String seg_ruta=ruta+"/"+list[i];
				File ca= new File(seg_ruta);
				encontrarBusqueda(seg_ruta,ca,busqueda);
			}else{			
				String[] palabras = busqueda.split("\\s+");
				for (String palabra : palabras) {
				    if (list[i].toLowerCase().trim().contains(palabra.toLowerCase().trim())) {
				        //System.out.println("Encontré un archivo con esa palabra!!! Está en la siguiente ruta: "+c.getPath());
				        rutasEncontradas.add(c.getPath());
				    }
				}
			}
			
		}
		}
	}
		return rutasEncontradas;
	}
	
	public static boolean validarRutasSistema(File f){
		boolean result = true;
		ArrayList<String> rutasSistema = new ArrayList<>();
		rutasSistema.add("/Users/ronijordanlopez/Library");
		rutasSistema.add("/Users/ronijordanlopez/.gradle");
		rutasSistema.add("/Users/ronijordanlopez/.rvm");
		for(int i=0;i<rutasSistema.size();i++){
			String rutaSis= rutasSistema.get(i);
			String ruta = f.getPath();
			if(rutaSis.equals(ruta)){
				result=false;
			}
		}
		
		return result;
	}
	

}
