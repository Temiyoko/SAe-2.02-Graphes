package main.java.graphe.algos;

import main.java.graphe.core.IGrapheConst;

import java.util.*;

public class Dijkstra {
    public static void dijkstra(IGrapheConst g, String source, Map<String, Integer> dist, Map<String, String> prev) {
        Set<String> visited = new HashSet<>();
        PriorityQueue<String> queue = new PriorityQueue<>(Comparator.comparingInt(dist::get));

        // Initialisation des distances avec une valeur infinie sauf pour la source (0)
        for (String sommet : g.getSommets()) {
            dist.put(sommet, Integer.MAX_VALUE);
        }
        dist.put(source, 0);

        queue.offer(source);

        while (!queue.isEmpty()) {
            String current = queue.poll();
            visited.add(current);

            List<String> successeurs = g.getSucc(current);

            for (String successeur : successeurs) {
                if (visited.contains(successeur)) {
                    continue;
                }

                int poids = g.getValuation(current, successeur);
                int alt = dist.get(current) + poids;

                if (alt < dist.get(successeur)) {
                    dist.put(successeur, alt);
                    prev.put(successeur, current);
                    queue.offer(successeur);
                }
            }
        }
        for (Map.Entry<String, Integer> entry : dist.entrySet()) {
            if (entry.getValue() == Integer.MAX_VALUE) {
                entry.setValue(-1);
            }
        }
    }
}
