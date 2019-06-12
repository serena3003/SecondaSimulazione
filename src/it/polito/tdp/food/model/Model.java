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

	public Model() {
		this.dao = new FoodDao();
		grafo = new SimpleWeightedGraph<Condiment, DefaultWeightedEdge>(DefaultWeightedEdge.class);

		ingredienti = new HashMap<>();		
	}

	public void creaGrafo(int calorie) {
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
						System.out.println(c1.getDisplay_name() + " - " + c2.getDisplay_name()+" - " +peso+"\n");
						Graphs.addEdge(this.grafo, c1, c2, peso);	
						nFood = nFood + peso;
						System.out.println(nFood+"\n");
						}
				}
			}
			c1.setNFood(nFood);
		}
		
	}

	public List<Condiment> getCondiment() {
		List<Condiment> result = new ArrayList<Condiment>(ingredienti.values());
		return result;
	}
	
	public Graph<Condiment, DefaultWeightedEdge> getGrafo(){
		return this.grafo;
	}

}
