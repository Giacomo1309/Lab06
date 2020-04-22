package it.polito.tdp.meteo.DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.*;

import it.polito.tdp.meteo.model.Rilevamento;

public class MeteoDAO {

	public List<Rilevamento> getAllRilevamenti() {

		final String sql = "SELECT Localita, Data, Umidita FROM situazione ORDER BY data ASC";

		List<Rilevamento> rilevamenti = new ArrayList<Rilevamento>();

		try {
			Connection conn = ConnectDB.getConnection();
			PreparedStatement st = conn.prepareStatement(sql);

			ResultSet rs = st.executeQuery();

			while (rs.next()) {

				Rilevamento r = new Rilevamento(rs.getString("Localita"), rs.getDate("Data"), rs.getInt("Umidita"));
				rilevamenti.add(r);
			}

			conn.close();
			return rilevamenti;

		} catch (SQLException e) {

			e.printStackTrace();
			throw new RuntimeException(e);
		}
	}

	public List<Rilevamento> getAllRilevamentiLocalitaMese(int mese,String localita) {
		List<Rilevamento> allRilevamenti = new ArrayList<Rilevamento>();
		final String sql =  " SELECT Localita,Umidita,Data" 
							+ " FROM situazione"
							+ " WHERE DATA BETWEEN ? AND ? AND Localita = ?"
							+ " GROUP BY Localita , DATA, umidita " ;
		
		try {
			Connection conn = ConnectDB.getConnection();
			PreparedStatement st = conn.prepareStatement(sql);
			st.setString(1, "2013-" +mese+ "-01");
			st.setString(2, "2013-" + mese+"-15");
			st.setString(3, localita);
			ResultSet rs = st.executeQuery();

			while (rs.next()) {

				Rilevamento r = new Rilevamento(rs.getString("Localita"),rs.getDate("Data"),rs.getInt("Umidita"));
				allRilevamenti.add(r);
			}

			conn.close();
			return allRilevamenti;

		} catch (SQLException e) {

			e.printStackTrace();
			throw new RuntimeException(e);
		}

	}
	public List<Rilevamento> getAllRilevamentiLocalitaMese2(int mese) {
		List<Rilevamento> allRilevamenti = new ArrayList<Rilevamento>();
		final String sql =  " SELECT Localita,Umidita,Data" 
							+ " FROM situazione"
							+ " WHERE DATA BETWEEN ? AND ?"
							+ " GROUP BY DATA, Localita , umidita " ;
		
		try {
			Connection conn = ConnectDB.getConnection();
			PreparedStatement st = conn.prepareStatement(sql);
			st.setString(1, "2013-" +mese+ "-01");
			st.setString(2, "2013-" + mese+"-15");
			ResultSet rs = st.executeQuery();

			while (rs.next()) {

				Rilevamento r = new Rilevamento(rs.getString("Localita"),rs.getDate("Data"),rs.getInt("Umidita"));
				allRilevamenti.add(r);
			}

			conn.close();
			return allRilevamenti;

		} catch (SQLException e) {

			e.printStackTrace();
			throw new RuntimeException(e);
		}

	}

	public List<String> getUmiditaMediaMensile(String mese,String mesedopo) {
		List<String> umiditaMediaMensile= new ArrayList<String>();
		
		final String sql =  " SELECT Localita , AVG(umidita) " + 
				"FROM situazione " + 
				"WHERE DATA BETWEEN ? AND  ? " + 
				"GROUP BY Localita" ;

		try {
			Connection conn = ConnectDB.getConnection();
			PreparedStatement st = conn.prepareStatement(sql);
			st.setString(1, mese);
			st.setString(2, mesedopo);
			ResultSet rs = st.executeQuery();

			while (rs.next()) {

				String s = new String(""+rs.getString("Localita")+" "+rs.getFloat("AVG(Umidita)"));
				umiditaMediaMensile.add(s);
			}

			conn.close();
			return umiditaMediaMensile;

		} catch (SQLException e) {

			e.printStackTrace();
			throw new RuntimeException(e);
		}
		
	}

}
