package graphe;

import java.util.List;

public class GrapheLArcs implements IGraphe {
	private List<Arc> arcs;

	public GrapheLArcs(String g31a){

	}

	@Override
	public void ajouterSommet(String noeud) {

	}

	@Override
	public void ajouterArc(String source, String destination, Integer valeur) {

	}

	@Override
	public void oterSommet(String noeud) {

	}

	@Override
	public void oterArc(String source, String destination) {

	}

	@Override
	public List<String> getSommets() {
		return null;
	}

	@Override
	public List<String> getSucc(String sommet) {
		return null;
	}

	@Override
	public int getValuation(String src, String dest) {
		return 0;
	}

	@Override
	public boolean contientSommet(String sommet) {
		return false;
	}

	@Override
	public boolean contientArc(String src, String dest) {
		return false;
	}
}