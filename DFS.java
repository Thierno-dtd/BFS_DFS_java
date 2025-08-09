/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dfs_bfs;

/**
 *
 * @author thierno
 */
import java.util.*;

public class DFS {
    
    /**
     * DFS récursif à partir d'un sommet donné
     */
    public static void dfsRecursive(List<List<Integer>> graph, int startVertex) {
        boolean[] visited = new boolean[graph.size()];
        System.out.print("DFS récursif depuis " + startVertex + ": ");
        dfsRecursiveUtil(graph, startVertex, visited);
        System.out.println();
    }
    
    /**
     * Fonction utilitaire récursive pour DFS
     */
    private static void dfsRecursiveUtil(List<List<Integer>> graph, int vertex, boolean[] visited) {
        visited[vertex] = true;
        System.out.print(vertex + " ");
        
        // Parcourir tous les voisins adjacents
        for (int neighbor : graph.get(vertex)) {
            if (!visited[neighbor]) {
                dfsRecursiveUtil(graph, neighbor, visited);
            }
        }
    }
    
    /**
     * DFS itératif utilisant une pile
     */
    public static void dfsIterative(List<List<Integer>> graph, int startVertex) {
        boolean[] visited = new boolean[graph.size()];
        Stack<Integer> stack = new Stack<>();
        
        stack.push(startVertex);
        System.out.print("DFS itératif depuis " + startVertex + ": ");
        
        while (!stack.isEmpty()) {
            int vertex = stack.pop();
            
            if (!visited[vertex]) {
                visited[vertex] = true;
                System.out.print(vertex + " ");
                
                // Ajouter tous les voisins non visités à la pile
                // En ordre inverse pour maintenir l'ordre lexicographique
                List<Integer> neighbors = new ArrayList<>(graph.get(vertex));
                Collections.reverse(neighbors);
                
                for (int neighbor : neighbors) {
                    if (!visited[neighbor]) {
                        stack.push(neighbor);
                    }
                }
            }
        }
        System.out.println();
    }
    
    /**
     * DFS complet - parcourt tous les sommets du graphe
     */
    public static void dfsComplete(List<List<Integer>> graph) {
        boolean[] visited = new boolean[graph.size()];
        System.out.print("DFS complet: ");
        
        for (int i = 0; i < graph.size(); i++) {
            if (!visited[i]) {
                dfsRecursiveUtil(graph, i, visited);
            }
        }
        System.out.println();
    }
    
    /**
     * Détection de cycle dans le graphe orienté
     */
    public static boolean hasCycle(List<List<Integer>> graph) {
        boolean[] visited = new boolean[graph.size()];
        boolean[] recStack = new boolean[graph.size()]; // Pile de récursion
        
        for (int i = 0; i < graph.size(); i++) {
            if (hasCycleUtil(graph, i, visited, recStack)) {
                return true;
            }
        }
        return false;
    }
    
    /**
     * Fonction utilitaire pour la détection de cycle
     */
    private static boolean hasCycleUtil(List<List<Integer>> graph, int vertex, 
                                       boolean[] visited, boolean[] recStack) {
        if (recStack[vertex]) {
            return true; // Cycle détecté
        }
        
        if (visited[vertex]) {
            return false;
        }
        
        visited[vertex] = true;
        recStack[vertex] = true;
        
        for (int neighbor : graph.get(vertex)) {
            if (hasCycleUtil(graph, neighbor, visited, recStack)) {
                return true;
            }
        }
        
        recStack[vertex] = false; // Retirer de la pile de récursion
        return false;
    }
    
    /**
     * Tri topologique utilisant DFS
     */
    public static List<Integer> topologicalSort(List<List<Integer>> graph) {
        Stack<Integer> stack = new Stack<>();
        boolean[] visited = new boolean[graph.size()];
        
        // Appeler la fonction récursive pour tous les sommets
        for (int i = 0; i < graph.size(); i++) {
            if (!visited[i]) {
                topologicalSortUtil(graph, i, visited, stack);
            }
        }
        
        // Créer la liste résultat à partir de la pile
        List<Integer> result = new ArrayList<>();
        while (!stack.isEmpty()) {
            result.add(stack.pop());
        }
        
        return result;
    }
    
    /**
     * Fonction utilitaire pour le tri topologique
     */
    private static void topologicalSortUtil(List<List<Integer>> graph, int vertex, 
                                           boolean[] visited, Stack<Integer> stack) {
        visited[vertex] = true;
        
        for (int neighbor : graph.get(vertex)) {
            if (!visited[neighbor]) {
                topologicalSortUtil(graph, neighbor, visited, stack);
            }
        }
        
        stack.push(vertex);
    }
    
    /**
     * Trouver toutes les composantes fortement connexes (Kosaraju)
     */
    public static void stronglyConnectedComponents(List<List<Integer>> graph) {
        Stack<Integer> stack = new Stack<>();
        boolean[] visited = new boolean[graph.size()];
        
        // Étape 1: Remplir la pile selon les temps de fin (DFS)
        for (int i = 0; i < graph.size(); i++) {
            if (!visited[i]) {
                fillOrder(graph, i, visited, stack);
            }
        }
        
        // Étape 2: Créer le graphe transposé
        List<List<Integer>> transpose = getTranspose(graph);
        
        // Étape 3: DFS sur le graphe transposé dans l'ordre de la pile
        Arrays.fill(visited, false);
        int componentCount = 0;
        
        System.out.println("Composantes fortement connexes:");
        while (!stack.isEmpty()) {
            int vertex = stack.pop();
            if (!visited[vertex]) {
                System.out.print("Composante " + (++componentCount) + ": ");
                dfsRecursiveUtil(transpose, vertex, visited);
                System.out.println();
            }
        }
    }
    
    private static void fillOrder(List<List<Integer>> graph, int vertex, 
                                 boolean[] visited, Stack<Integer> stack) {
        visited[vertex] = true;
        
        for (int neighbor : graph.get(vertex)) {
            if (!visited[neighbor]) {
                fillOrder(graph, neighbor, visited, stack);
            }
        }
        
        stack.push(vertex);
    }
    
    private static List<List<Integer>> getTranspose(List<List<Integer>> graph) {
        List<List<Integer>> transpose = new ArrayList<>();
        
        // Initialiser le graphe transposé
        for (int i = 0; i < graph.size(); i++) {
            transpose.add(new ArrayList<>());
        }
        
        // Inverser toutes les arêtes
        for (int i = 0; i < graph.size(); i++) {
            for (int neighbor : graph.get(i)) {
                transpose.get(neighbor).add(i);
            }
        }
        
        return transpose;
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
