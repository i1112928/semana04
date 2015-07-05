package edu.continental.dao;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;

import org.codehaus.jettison.json.JSONArray;

import edu.continental.util.ToJSON;
import edu.continental.util.conexion;

public class CiudadesDAO {
	public JSONArray listaCiudades(){
		//obtener la coneccion a la DB
		conexion cn = new conexion();
		Connection con = cn.getConnection();
		
		//sentencia sql
		String sql = "select id, nombre, altitud, estado from ciudades where estado = 'A'";
			
			Statement st = null;
			ResultSet rs = null;
			
			//para convertir a JSON
			ToJSON convertidor = new ToJSON();
			JSONArray arreglo = new JSONArray();
			
			try{
				//creo la sentencia
				st = con.createStatement();
				//se junta la sentencia
				rs = st.executeQuery(sql);
				
				arreglo = convertidor.toJSONArray(rs);
				st.close();
			}
			catch (Exception ex){
				System.out.print("error: " + ex.getMessage());
				ex.printStackTrace();
				return null;
			}
			return arreglo;
	}

}
