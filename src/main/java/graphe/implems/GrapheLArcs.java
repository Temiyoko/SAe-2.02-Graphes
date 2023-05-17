package main.java.graphe.implems;

import main.java.graphe.core.*;

import java.util.ArrayList;
import java.util.List;

public class GrapheLArcs extends Graphe implements IGraphe {
	private List<Arc> arcs;

	public GrapheLArcs() {
		arcs = new ArrayList<>();
	}

	@Override
	public void ajouterSommet(String noeud) {
		if(!contientSommet(noeud)) {
			arcs.add(new Arc(noeud));
		}
	}

	@Override
	public void ajouterArc(String source, String destination, Integer valeur) throws IllegalArgumentException {
		if(contientArc(source, destination) || valeur < 0){
			throw new IllegalArgumentException();
		}
		arcs.add(new Arc(source, destination, valeur));
	}

	@Override
	public void oterSommet(String noeud) {
		if(contientSommet(noeud)){
			arcs.removeIf(arc -> arc.getSource().equals(noeud) || arc.getDestination().equals(noeud));
		}
	}

	@Override
	public void oterArc(String source, String destination) throws IllegalArgumentException {
		if (!contientArc(source, destination)) {
			throw new IllegalArgumentException();
		}
		arcs.removeIf(a -> a.getSource().equals(source) && a.getDestination().equals(destination));
	}

	@Override
	public List<String> getSommets() {
		List<String> sommets = new ArrayList<>();
		for (Arc a : arcs){
			if(!sommets.contains(a.getSource())){
				sommets.add(a.getSource());
			}
			if (!sommets.contains(a.getDestination())) {
				sommets.add(a.getDestination());
			}
		}
		return sommets;
	}

	@Override
	public List<String> getSucc(String sommet) {
		List<String> successeurs = new ArrayList<>();
		for (Arc a : arcs){
			if(a.getSource().equals(sommet) && !a.getDestination().equals("")){
				successeurs.add(a.getDestination());
			}
		}
		return successeurs;
	}

	@Override
	public int getValuation(String src, String dest) {
		for (Arc a : arcs){
			if(a.getSource().equals(src) && a.getDestination().equals(dest)) {
				return a.getValuation();
			}
		}
		return -1;
	}

	@Override
	public boolean contientSommet(String sommet) {
		for(Arc a : arcs){
			if(a.getSource().equals(sommet) || a.getDestination().equals(sommet)){
				return true;
			}
		}
		return false;
	}

	@Override
	public boolean contientArc(String src, String dest) {
		if(!contientSommet(src)){
			return false;
		}
		for (Arc a : arcs){
			if(a.getSource().equals(src) && a.getDestination().equals(dest)){
				return true;
			}
		}
		return false;
	}
}
