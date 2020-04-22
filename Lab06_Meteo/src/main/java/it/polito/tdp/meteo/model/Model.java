package it.polito.tdp.meteo.model;

import java.util.ArrayList;
import java.util.List;
import java.util.*;

import it.polito.tdp.meteo.DAO.*;;

public class Model {
	private List<Rilevamento> rilevamenti = new ArrayList<Rilevamento>();
	private List<Rilevamento> rilevamenti2 = new ArrayList<Rilevamento>();
	
	private final static int COST = 100;
	private final static int NUMERO_GIORNI_CITTA_CONSECUTIVI_MIN = 3;
	private final static int NUMERO_GIORNI_CITTA_MAX = 6;
	private final static int NUMERO_GIORNI_TOTALI = 15;

	public Model() {
		MeteoDAO dao = new MeteoDAO();
		int mese = 1;
		String localita = "";
		this.rilevamenti = dao.getAllRilevamentiLocalitaMese(mese, localita);
		this.rilevamenti2 = dao.getAllRilevamentiLocalitaMese2(mese);
				//io farei una lista con tutti i rilevamenti 

	}

	// of course you can change the String output with what you think works best
	public String getUmiditaMedia(int mese) {
		return null;
	}
	
	public List<Rilevamento> ottieniSet(String mese) {
		List<Rilevamento> soluzioneParziale = new ArrayList<Rilevamento>();
		
		cerca(0,soluzioneParziale, rilevamenti2) ;
		
		return soluzioneParziale;
	}
	
	private void cerca(int livello, List<Rilevamento> parziale, List<Rilevamento> rilevamenti) {
		
		if(livello==14) {
			return ;
		}
		for(Rilevamento r : rilevamenti) {
			parziale.add(r);
			cerca(livello+1,parziale,rilevamenti);
			parziale.remove(r);
			System.out.println(parziale);
		}
		
		
	}
	
	// of course you can change the String output with what you think works best
	public String trovaSequenza(int mese) {
		return "TODO!";
	}
	

}
