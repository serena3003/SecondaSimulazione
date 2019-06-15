package it.polito.tdp.food.model;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.jgrapht.Graph;
import org.jgrapht.Graphs;
import org.jgrapht.graph.DefaultWeightedEdge;
import org.jgrapht.graph.SimpleWeightedGraph;

import it.polito.tdp.food.db.Condiment;
import it.polito.tdp.food.db.FoodDao;

public class Model {

	private FoodDao dao;
	private Graph<Condiment, DefaultWeightedEdge> grafo;
	private Map<Integer, Condiment> ingredienti;
	private List<Condiment> soluzioneRicorsiva;
	private double calorieMax;

	public Model() {
		this.dao = new FoodDao();
		grafo = new SimpleWeightedGraph<Condiment, DefaultWeightedEdge>(DefaultWeightedEdge.class);

		ingredienti = new HashMap<>();	
		soluzioneRicorsiva = new ArrayList<>();
	}

	public void creaGrafo(double calorie) {
		List<Condiment> cond = dao.getCondiment(calorie);
		for (Condiment c : cond)
			ingredienti.put(c.getCondiment_id(), c);
		
		Graphs.addAllVertices(this.grafo, cond);
		
		for(Condiment c1 : ingredienti.values()) {
			int nFood = 0;
			for(Condiment c2 : ingredienti.values()) {
				if(!c1.equals(c2)) {
					int peso = dao.getDifferentFood(c1.getFood_code(), c2.getFood_code());
					if(peso>0) {
						//System.out.println(c1.getDisplay_name() + " - " + c2.getDisplay_name()+" - " +peso+"\n");
						Graphs.addEdge(this.grafo, c1, c2, peso);	
						nFood = nFood + peso;
						//System.out.println(nFood+"\n");
						}
				}
			}
			c1.setNFood(nFood/2);
		}
		
	}

	public List<Condiment> getCondiment() {
		List<Condiment> result = new ArrayList<Condiment>(ingredienti.values());
		return result;
	}
	
	public Graph<Condiment, DefaultWeightedEdge> getGrafo(){
		return this.grafo;
	}

	public List<Condiment> creaDieta(Condiment condiment) {
		List<Condiment> parziale = new ArrayList<Condiment>();
		parziale.add(condiment);
		calorieMax = condiment.getCondiment_calories();
		cerca(condiment, parziale);		
		return soluzioneRicorsiva;
	}
	
	public void cerca(Condiment cond, List<Condiment> parziale) {
		if(contaCalorie(parziale) > calorieMax ) {
			calorieMax = contaCalorie(parziale);
			soluzioneRicorsiva = new ArrayList<Condiment>(parziale);
		}
		
		for(Condiment c : grafo.vertexSet()) {
			if(!parziale.contains(c) && nonContieneArco(parziale, c)) {
				parziale.add(c);
				cerca(c, parziale);
				parziale.remove(c);
			}
		}
	}
	
	public double contaCalorie(List<Condiment> condiment) {
		double somma = 0.0;
		for(Condiment c : condiment) {
			somma = somma + c.getCondiment_calories();
		}
		return somma;
	}
	
	private boolean nonContieneArco(List<Condiment> lista, Condiment con) {
		
		 for(Condiment c : lista) {
			if(grafo.containsEdge(c, con) || grafo.containsEdge(con, c)){
					return false;
				}
			}
		return true;
	}

}
