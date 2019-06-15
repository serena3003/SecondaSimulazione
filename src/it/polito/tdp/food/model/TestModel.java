package it.polito.tdp.food.model;

import java.util.List;

import org.jgrapht.Graphs;

import it.polito.tdp.food.db.Condiment;

public class TestModel {
	public static void main(String[] args) {
 
		Model model = new Model();
		model.creaGrafo(1000);
		
		System.out.println("Vertici: " + model.getGrafo().vertexSet().size() + " Archi : " + model.getGrafo().edgeSet().size());
		System.out.println(model.getGrafo());
		
		Condiment c = new Condiment(2, 11111000, "Whole milk", "2 Tablespoons",	18.3);
		//Condiment c = new Condiment(5, 11113000, "Fat free milk (skim)", "1/2  cup", 41.65);

		/*System.out.println("Stampo vicini: \n");
		for(Condiment co : Graphs.neighborListOf( model.getGrafo(), c)) {
			System.out.println(co.getDisplay_name() +"\n");
		}*/
		
		/*System.out.println("\n\n------------------- INIZIO RICORSIONE ---------------------");
		List<Condiment> soluzione = model.creaDieta(c);
		System.out.println("\n\n------------------- FINE RICORSIONE ---------------------");
		System.out.println("-------------SOLUZIONE---------\n" + soluzione.toString() + "\n--------------------");
		double calorie = 0.0;
		System.out.println("\nCALORIE: \n");
		for(Condiment con : soluzione) {
			System.out.println(con.getDisplay_name() +"\n");
			calorie = calorie + con.getCondiment_calories();
		}
		System.out.println("\n"+calorie);*/

	}
}
