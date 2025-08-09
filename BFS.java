package dfs_bfs;

/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

/**
 *
 * @author thierno
 */

import java.util.*;

/**
 * Implémentation du BFS (Breadth-First Search) pour graphes orientés
 * Sans classe interne - Méthodes statiques directement dans la classe principale
 */
public class BFS {
    
    /**
     * BFS basique à partir d'un sommet donné
     */
    public static void bfs(List<List<Integer>> graph, int startVertex) {
        boolean[] visited = new boolean[graph.size()];
        Queue<Integer> queue = new LinkedList<>();
        
        visited[startVertex] = true;
        queue.offer(startVertex);
        
        System.out.print("BFS depuis " + startVertex + ": ");
        
        while (!queue.isEmpty()) {
            int vertex = queue.poll();
            System.out.print(vertex + " ");
            
            // Parcourir tous les voisins adjacents
            for (int neighbor : graph.get(vertex)) {
                if (!visited[neighbor]) {
                    visited[neighbor] = true;
                    queue.offer(neighbor);
                }
            }
        }
        System.out.println();
    }
    
    /**
     * BFS avec affichage niveau par niveau
     */
    public static void bfsLevelOrder(List<List<Integer>> graph, int startVertex) {
        boolean[] visited = new boolean[graph.size()];
        Queue<Integer> queue = new LinkedList<>();
        Queue<Integer> levelQueue = new LinkedList<>();
        
        visited[startVertex] = true;
        queue.offer(startVertex);
        levelQueue.offer(0); // Niveau 0
        
        System.out.println("BFS niveau par niveau depuis " + startVertex + ":");
        int currentLevel = -1;
        
        while (!queue.isEmpty()) {
            int vertex = queue.poll();
            int level = levelQueue.poll();
            
            if (level != currentLevel) {
                if (currentLevel != -1) System.out.println();
                System.out.print("  Niveau " + level + ": ");
                currentLevel = level;
            }
            System.out.print(vertex + " ");
            
            // Parcourir tous les voisins adjacents
            for (int neighbor : graph.get(vertex)) {
                if (!visited[neighbor]) {
                    visited[neighbor] = true;
                    queue.offer(neighbor);
                    levelQueue.offer(level + 1);
                }
            }
        }
        System.out.println();
    }
    
    /**
     * BFS avec calcul des distances depuis un sommet source
     */
    public static void bfsWithDistances(List<List<Integer>> graph, int startVertex) {
        boolean[] visited = new boolean[graph.size()];
        int[] distances = new int[graph.size()];
        Queue<Integer> queue = new LinkedList<>();
        
        // Initialisation
        Arrays.fill(distances, -1);
        visited[startVertex] = true;
        distances[startVertex] = 0;
        queue.offer(startVertex);
        
        while (!queue.isEmpty()) {
            int vertex = queue.poll();
            
            for (int neighbor : graph.get(vertex)) {
                if (!visited[neighbor]) {
                    visited[neighbor] = true;
                    distances[neighbor] = distances[vertex] + 1;
                    queue.offer(neighbor);
                }
            }
        }
        
        // Afficher les distances
        System.out.println("Distances depuis " + startVertex + ":");
        for (int i = 0; i < graph.size(); i++) {
            if (distances[i] != -1) {
                System.out.println("  " + startVertex + " → " + i + ": " + distances[i]);
            } else {
                System.out.println("  " + i + " non accessible");
            }
        }
    }
    
    /**
     * Trouver le plus court chemin entre deux sommets
     */
    public static List<Integer> shortestPath(List<List<Integer>> graph, int start, int end) {
        if (start == end) {
            return Arrays.asList(start);
        }
        
        boolean[] visited = new boolean[graph.size()];
        int[] parent = new int[graph.size()];
        Queue<Integer> queue = new LinkedList<>();
        
        Arrays.fill(parent, -1);
        visited[start] = true;
        queue.offer(start);
        
        while (!queue.isEmpty()) {
            int vertex = queue.poll();
            
            for (int neighbor : graph.get(vertex)) {
                if (!visited[neighbor]) {
                    visited[neighbor] = true;
                    parent[neighbor] = vertex;
                    queue.offer(neighbor);
                    
                    // Si on atteint la destination
                    if (neighbor == end) {
                        return reconstructPath(parent, start, end);
                    }
                }
            }
        }
        
        return new ArrayList<>(); // Aucun chemin trouvé
    }
    
    /**
     * Reconstruire le chemin à partir du tableau parent
     */
    private static List<Integer> reconstructPath(int[] parent, int start, int end) {
        List<Integer> path = new ArrayList<>();
        int current = end;
        
        while (current != -1) {
            path.add(current);
            current = parent[current];
        }
        
        Collections.reverse(path);
        return path;
    }
    
    /**
     * Vérifier si un sommet est accessible depuis un autre
     */
    public static boolean isReachable(List<List<Integer>> graph, int start, int end) {
        if (start == end) return true;
        
        boolean[] visited = new boolean[graph.size()];
        Queue<Integer> queue = new LinkedList<>();
        
        visited[start] = true;
        queue.offer(start);
        
        while (!queue.isEmpty()) {
            int vertex = queue.poll();
            
            for (int neighbor : graph.get(vertex)) {
                if (neighbor == end) return true;
                
                if (!visited[neighbor]) {
                    visited[neighbor] = true;
                    queue.offer(neighbor);
                }
            }
        }
        
        return false;
    }
    
    /**
     * Trouver tous les sommets accessibles depuis un sommet donné
     */
    public static Set<Integer> findReachableVertices(List<List<Integer>> graph, int start) {
        Set<Integer> reachable = new HashSet<>();
        boolean[] visited = new boolean[graph.size()];
        Queue<Integer> queue = new LinkedList<>();
        
        visited[start] = true;
        queue.offer(start);
        reachable.add(start);
        
        while (!queue.isEmpty()) {
            int vertex = queue.poll();
            
            for (int neighbor : graph.get(vertex)) {
                if (!visited[neighbor]) {
                    visited[neighbor] = true;
                    queue.offer(neighbor);
                    reachable.add(neighbor);
                }
            }
        }
        
        return reachable;
    }
    
    /**
     * BFS complet - parcourt tous les sommets du graphe
     */
    public static void bfsComplete(List<List<Integer>> graph) {
        boolean[] visited = new boolean[graph.size()];
        
        System.out.print("BFS complet: ");
        for (int i = 0; i < graph.size(); i++) {
            if (!visited[i]) {
                bfsComponentUtil(graph, i, visited);
            }
        }
        System.out.println();
    }
    
    private static void bfsComponentUtil(List<List<Integer>> graph, int start, boolean[] visited) {
        Queue<Integer> queue = new LinkedList<>();
        visited[start] = true;
        queue.offer(start);
        
        while (!queue.isEmpty()) {
            int vertex = queue.poll();
            System.out.print(vertex + " ");
            
            for (int neighbor : graph.get(vertex)) {
                if (!visited[neighbor]) {
                    visited[neighbor] = true;
                    queue.offer(neighbor);
                }
            }
        }
    }
    
    /**
     * Compter le nombre de composantes connexes
     */
    public static int countConnectedComponents(List<List<Integer>> graph) {
        boolean[] visited = new boolean[graph.size()];
        int count = 0;
        
        for (int i = 0; i < graph.size(); i++) {
            if (!visited[i]) {
                bfsComponentUtil(graph, i, visited);
                count++;
            }
        }
        
        return count;
    }
    
    /**
     * Trouver tous les chemins de longueur k depuis un sommet (récursif)
     */
    public static List<List<Integer>> findPathsOfLengthK(List<List<Integer>> graph, int start, int k) {
        List<List<Integer>> result = new ArrayList<>();
        List<Integer> currentPath = new ArrayList<>();
        currentPath.add(start);
        
        findPathsOfLengthKUtil(graph, start, k, currentPath, result);
        return result;
    }
    
    private static void findPathsOfLengthKUtil(List<List<Integer>> graph, int vertex, int k, 
                                              List<Integer> currentPath, List<List<Integer>> result) {
        if (k == 0) {
            result.add(new ArrayList<>(currentPath));
            return;
        }
        
        for (int neighbor : graph.get(vertex)) {
            currentPath.add(neighbor);
            findPathsOfLengthKUtil(graph, neighbor, k - 1, currentPath, result);
            currentPath.remove(currentPath.size() - 1);
        }
    }
    
    /**
     * Créer un graphe orienté avec liste d'adjacence
     */
    public static List<List<Integer>> createGraph(int vertices) {
        List<List<Integer>> graph = new ArrayList<>();
        for (int i = 0; i < vertices; i++) {
            graph.add(new ArrayList<>());
        }
        return graph;
    }
    
    /**
     * Ajouter une arête orientée de u vers v
     */
    public static void addEdge(List<List<Integer>> graph, int u, int v) {
        graph.get(u).add(v);
    }
    
    /**
     * Afficher le graphe
     */
    public static void printGraph(List<List<Integer>> graph) {
        System.out.println("Graphe orienté:");
        for (int i = 0; i < graph.size(); i++) {
            System.out.print("  " + i + " → ");
            if (graph.get(i).isEmpty()) {
                System.out.println("∅");
            } else {
                System.out.println(graph.get(i));
            }
        }
    }
    
    
}