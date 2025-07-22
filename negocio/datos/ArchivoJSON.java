package datos;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;

import java.lang.reflect.Type;
import java.util.HashMap;
import java.util.Set;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;

import grafo.Grafo;

public class ArchivoJSON{
	static HashMap<String, Grafo> grafosEnJSON;
	static String archivoJSON = "grafos.json";
	
	public static void generarJSON(String nombreGrafo,Grafo grafo){
		Gson gson = new GsonBuilder().setPrettyPrinting().create();
		agregarEnElHashMap(nombreGrafo, grafo);
		String json = gson.toJson(grafosEnJSON);
		
		try{
			FileWriter writer = new FileWriter(archivoJSON);
			writer.write(json);
			writer.close();
		}
		catch(Exception e) {
		    e.printStackTrace();
		}
	}
	
	private static void agregarEnElHashMap(String nombreGrafo, Grafo grafo){
		if(grafosEnJSON == null) {
			incializarHashMap();
		}
		grafosEnJSON.put(nombreGrafo, grafo);
	}
	
	private static void incializarHashMap() {
		grafosEnJSON = new HashMap<String, Grafo>();
		leerJSON();
	}

	private static void leerJSON(){
		Gson gson = new Gson();
		try{
			BufferedReader br = new BufferedReader(new FileReader(archivoJSON));
            Type listType = new TypeToken<HashMap<String, Grafo>>() {}.getType();
            grafosEnJSON = gson.fromJson(br, listType);
		}
		catch (Exception e) {
		    e.printStackTrace();
		}
	}
	
	public static Set<String> obtenerConjuntoNombresGrafos() {
		if(grafosEnJSON == null) {
			incializarHashMap();
		}
		return grafosEnJSON.keySet();
	}
	
	public static String[] obtenerArrayStringNombresGrafos(){
		if(grafosEnJSON == null) {
			incializarHashMap();
		}
		String[] nombresGrafos = new String[grafosEnJSON.size()];
		int cont = 0;
		for (String nombre : grafosEnJSON.keySet()) {
			nombresGrafos[cont] = nombre; 
			cont++;
		}
		return nombresGrafos;
	}
	
	public static Grafo obtenerGrafo(String nombreGrafo){
		if(!grafosEnJSON.containsKey(nombreGrafo))
			throw new RuntimeException("No se encontro el grafo solicitado");
		return grafosEnJSON.get(nombreGrafo);
	}
}

