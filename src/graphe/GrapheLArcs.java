package graphe;

import java.util.ArrayList;
import java.util.List;

public class GrapheLArcs implements IGraphe {
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
		arcs.add(new Arc(source, destination, valeur));
	}

	@Override
	public void oterSommet(String noeud) {
		oterArc(noeud, "");
	}

	@Override
	public void oterArc(String source, String destination) {
		for (Arc a : arcs){
			if (a.getSource() == source && a.getDestination() == destination){
				arcs.remove(a);
				break;
			}
		}
	}

	@Override
	public List<String> getSommets() {
		List<String> sommets = new ArrayList<>();
		for (Arc a : arcs){
			if(a.getDestination() == ""){
				sommets.add(a.getSource());
			}
		}
		return sommets;
	}

	@Override
	public List<String> getSucc(String sommet) {
		List<String> successeurs = new ArrayList<>();
		for (Arc a : arcs){
			if(a.getSource() == sommet){
				successeurs.add(a.getDestination());
			}
		}
		return successeurs;
	}

	@Override
	public int getValuation(String src, String dest) {
		for (Arc a : arcs){
			if(a.getSource() == src && a.getDestination() == dest) {
				return a.getValuation();
			}
		}
		return -1;
	}

	@Override
	public boolean contientSommet(String sommet) {
		return contientArc(sommet, "");
	}

	@Override
	public boolean contientArc(String src, String dest) {
		for (Arc a : arcs){
			if(a.getSource() == src && a.getDestination() == dest){
				return true;
			}
		}
		return false;
	}
}
