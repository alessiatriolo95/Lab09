package it.polito.tdp.metrodeparis.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.javadocmd.simplelatlng.LatLng;
import com.javadocmd.simplelatlng.LatLngTool;
import com.javadocmd.simplelatlng.util.LengthUnit;

import it.polito.tdp.metrodeparis.model.Connessione;
import it.polito.tdp.metrodeparis.model.Fermata;

public class MetroDAO {

	public List<Fermata> getAllFermate() {

		final String sql = "SELECT id_fermata, nome, coordx, coordy FROM fermata ORDER BY nome ASC";
		List<Fermata> fermate = new ArrayList<Fermata>();

		try {
			Connection conn = DBConnect.getInstance().getConnection();
			PreparedStatement st = conn.prepareStatement(sql);
			ResultSet rs = st.executeQuery();

			while (rs.next()) {
				Fermata f = new Fermata(rs.getInt("id_Fermata"), rs.getString("nome"), new LatLng(rs.getDouble("coordx"), rs.getDouble("coordy")));
				fermate.add(f);
			}

			st.close();
			conn.close();

		} catch (SQLException e) {
			e.printStackTrace();
			throw new RuntimeException("Errore di connessione al Database.");
		}

		return fermate;
	}
	public List<Connessione> getConnessioni(int idf1){

		String sql= "SELECT id_connessione AS idconn, id_linea AS idlinea, connessione.id_stazP AS idf1, connessione.id_stazA AS idf2,F1.nome,F1.coordX,F1.coordY,F2.nome,F2.coordX,F2.coordY "+
"FROM connessione,fermata AS F1, fermata AS F2 "+
"WHERE id_stazP= ? AND id_stazP=F1.id_fermata AND id_stazA= F2.id_fermata";
		
		List<Connessione> connessioni = new ArrayList<Connessione>();


		try {
			Connection conn = DBConnect.getInstance().getConnection();
			PreparedStatement st = conn.prepareStatement(sql);
			st.setInt(1,idf1);
			ResultSet rs = st.executeQuery();
			
			while (rs.next()) {
				Fermata f1=new Fermata(rs.getInt("idf1"),rs.getString("F1.nome"),new LatLng(rs.getDouble("F1.coordX"),rs.getDouble("F1.coordY")));
				Fermata f2=	new Fermata(rs.getInt("idf2"),rs.getString("F2.nome"),new LatLng(rs.getDouble("F2.coordX"),(rs.getDouble("F2.coordY"))));
				Connessione c = new Connessione(rs.getInt("idconn"), rs.getInt("idlinea"), f1,f2);
				
				connessioni.add(c);
				
			}
			st.close();
			conn.close();

		} catch (SQLException e) {
			e.printStackTrace();
			throw new RuntimeException("Errore di connessione al Database.");
		}

		return connessioni;
		
	}
	public Double getTempo (Fermata f1, Fermata f2){
		String sql="SELECT velocita,id_connessione AS idconn, connessione.id_linea AS idlinea, connessione.id_stazP AS idf1, connessione.id_stazA AS idf2,F1.nome,F1.coordX,F1.coordY,F2.nome,F2.coordX,F2.coordY "+
				"FROM connessione,fermata AS F1, fermata AS F2,linea "+
				"WHERE id_stazP= ? AND id_stazP=F1.id_fermata AND id_stazA= F2.id_fermata AND id_stazA= ? AND linea.id_linea=connessione.id_linea ";
		double velocita=0.0;
		double spazio = LatLngTool.distance(f1.getCoords(),f2.getCoords(), LengthUnit.KILOMETER);
		try {
			Connection conn = DBConnect.getInstance().getConnection();
			PreparedStatement st = conn.prepareStatement(sql);
			st.setInt(1,f1.getIdFermata());
			st.setInt(2, f2.getIdFermata());
			ResultSet rs = st.executeQuery();
			
			if (rs.next()) {
				velocita= rs.getDouble("velocita");
				
			}
			st.close();
			conn.close();

		} catch (SQLException e) {
			e.printStackTrace();
			throw new RuntimeException("Errore di connessione al Database.");
		}

		return spazio/velocita;
		
	}
}

