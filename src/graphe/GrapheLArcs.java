package graphe;

import java.util.ArrayList;
import java.util.List;

public class GrapheLArcs extends Graphe implements IGraphe {
	private List<Arc> arcs;

	public GrapheLArcs() {
		arcs = new ArrayList<>();
	}

	@Override
	public void ajouterSommet(String noeud) {
		ajouterArc(noeud, "", 0);
	}

	@Override
	public void ajouterArc(String source, String destination, Integer valeur) {
		assert(valeur >= 0);
		arcs.add(new Arc(source, destination, valeur));
	}

	@Override
	public void oterSommet(String noeud) {
		for(Arc a : arcs){
			if(a.getSource().equals(noeud)){
				oterArc(noeud, a.getDestination());
			} else if (a.getDestination().equals(noeud)) {
				oterArc(a.getSource(), noeud);
			}
		}
	}

	@Override
	public void oterArc(String source, String destination) {
		for (Arc a : arcs){
			if (a.getSource().equals(source) && a.getDestination().equals(destination)){
				arcs.remove(a);
				break;
			}
		}
	}

	@Override
	public List<String> getSommets() {
		List<String> sommets = new ArrayList<>();
		for (Arc a : arcs){
			if(!sommets.contains(a.getSource())){
				sommets.add(a.getSource());
			}
			if (!sommets.contains(a.getDestination())){
				sommets.add(a.getDestination());
			}
		}
		return sommets;
	}

	@Override
	public List<String> getSucc(String sommet) {
		List<String> successeurs = new ArrayList<>();
		for (Arc a : arcs){
			if(a.getSource().equals(sommet)){
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
