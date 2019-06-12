package it.polito.tdp.food.model;

public class TestModel {
	public static void main(String[] args) {
 
		Model model = new Model();
		model.creaGrafo(1000);
		
		System.out.println("Vertici: " + model.getGrafo().vertexSet().size() + " Archi : " + model.getGrafo().edgeSet().size());
	}
}
