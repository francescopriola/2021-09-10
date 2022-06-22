package it.polito.tdp.yelp.model;

public class TestModel {

	public static void main(String[] args) {
		
		Model model = new Model();
		
		model.creaGrafo("Casa Grande");
		
		model.getLocaleAdiacentePiuLontano(model.getMap().get("7VLW-cIDaiO4Dx8gXYkZcg"));

	}

}
