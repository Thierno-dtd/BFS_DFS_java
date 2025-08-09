/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package dfs_bfs;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Scanner;
import java.util.Set;

/**
 *
 * @author thierno
 */
public class DFS_BFS {

    /**
     * @param args the command line arguments
     */
    private static Scanner scanner = new Scanner(System.in);
    private static List<List<Integer>> graph = null;
    
    public static void main(String[] args) {
        System.out.println("=== SIMULATEUR D'ALGORITHMES DE PARCOURS DE GRAPHE ===");
        
        while (true) {
            afficherMenuPrincipal();
            int choix = saisirEntier("Votre choix: ");
            
            switch (choix) {
                case 1:
                    menuDFS();
                    break;
                case 2:
                    menuBFS();
                    break;
                case 3:
                    System.out.println("Au revoir !");
                    return;
                default:
                    System.out.println("❌ Choix invalide, veuillez réessayer.");
            }
        }
    }
    
    private static void afficherMenuPrincipal() {
        System.out.println("\n" + "=".repeat(40));
        System.out.println("         MENU PRINCIPAL");
        System.out.println("=".repeat(40));
        System.out.println("1. 🔍 DFS (Depth-First Search)");
        System.out.println("2. 📊 BFS (Breadth-First Search)");
        System.out.println("3. 🚪 Quitter");
        System.out.println("=".repeat(40));
        
        if (graph != null) {
            System.out.println("Graphe actuel: " + graph.size() + " sommets");
        }
    }
    
    private static void menuDFS() {
        while (true) {
            afficherMenuDFS();
            int choix = saisirEntier("Votre choix: ");
            
            switch (choix) {
                case 1:
                    creerGraphe();
                    break;
                case 2:
                    if (graph == null) {
                        System.out.println("❌ Aucun graphe défini. Créez d'abord un graphe.");
                    } else {
                        executerDFSRecursif();
                    }
                    break;
                case 3:
                    if (graph == null) {
                        System.out.println("❌ Aucun graphe défini. Créez d'abord un graphe.");
                    } else {
                        executerDFSIteratif();
                    }
                    break;
                case 4:
                    if (graph == null) {
                        System.out.println("❌ Aucun graphe défini. Créez d'abord un graphe.");
                    } else {
                        executerDFSComplet();
                    }
                    break;
                case 5:
                    if (graph == null) {
                        System.out.println("❌ Aucun graphe défini. Créez d'abord un graphe.");
                    } else {
                        verifierCycle();
                    }
                    break;
                case 6:
                    if (graph == null) {
                        System.out.println("❌ Aucun graphe défini. Créez d'abord un graphe.");
                    } else {
                        executerTriTopologique();
                    }
                    break;
                case 7:
                    if (graph == null) {
                        System.out.println("❌ Aucun graphe défini. Créez d'abord un graphe.");
                    } else {
                        executerComposantesConnexes();
                    }
                    break;
                case 8:
                    afficherGraphe();
                    break;
                case 0:
                    return;
                default:
                    System.out.println("❌ Choix invalide.");
            }
        }
    }
    
    private static void menuBFS() {
        while (true) {
            afficherMenuBFS();
            int choix = saisirEntier("Votre choix: ");
            
            switch (choix) {
                case 1:
                    creerGraphe();
                    break;
                case 2:
                    if (graph == null) {
                        System.out.println("❌ Aucun graphe défini. Créez d'abord un graphe.");
                    } else {
                        executerBFS();
                    }
                    break;
                case 3:
                    if (graph == null) {
                        System.out.println("❌ Aucun graphe défini. Créez d'abord un graphe.");
                    } else {
                        executerBFSNiveaux();
                    }
                    break;
                case 4:
                    if (graph == null) {
                        System.out.println("❌ Aucun graphe défini. Créez d'abord un graphe.");
                    } else {
                        executerBFSAvecDistances();
                    }
                    break;
                case 5:
                    if (graph == null) {
                        System.out.println("❌ Aucun graphe défini. Créez d'abord un graphe.");
                    } else {
                        trouverPlusCourtChemin();
                    }
                    break;
                case 6:
                    if (graph == null) {
                        System.out.println("❌ Aucun graphe défini. Créez d'abord un graphe.");
                    } else {
                        verifierAccessibilite();
                    }
                    break;
                case 7:
                    if (graph == null) {
                        System.out.println("❌ Aucun graphe défini. Créez d'abord un graphe.");
                    } else {
                        trouverSommetsAccessibles();
                    }
                    break;
                case 8:
                    if (graph == null) {
                        System.out.println("❌ Aucun graphe défini. Créez d'abord un graphe.");
                    } else {
                        executerBFSComplet();
                    }
                    break;
                case 9:
                    if (graph == null) {
                        System.out.println("❌ Aucun graphe défini. Créez d'abord un graphe.");
                    } else {
                        compterComposantes();
                    }
                    break;
                case 10:
                    if (graph == null) {
                        System.out.println("❌ Aucun graphe défini. Créez d'abord un graphe.");
                    } else {
                        trouverCheminsLongueurK();
                    }
                    break;
                case 11:
                    afficherGraphe();
                    break;
                case 0:
                    return;
                default:
                    System.out.println("❌ Choix invalide.");
            }
        }
    }
    
    private static void afficherMenuDFS() {
        System.out.println("\n" + "-".repeat(40));
        System.out.println("         MENU DFS");
        System.out.println("-".repeat(40));
        System.out.println("1. ➕ Créer un nouveau graphe");
        System.out.println("2. 🔄 DFS Récursif");
        System.out.println("3. 📚 DFS Itératif");
        System.out.println("4. 🌐 DFS Complet");
        System.out.println("5. 🔁 Détecter les cycles");
        System.out.println("6. 📋 Tri topologique");
        System.out.println("7. 🧩 Composantes fortement connexes");
        System.out.println("8. 👁️  Afficher le graphe");
        System.out.println("0. 🔙 Retour");
        System.out.println("-".repeat(40));
        
        if (graph != null) {
            System.out.println("Graphe actuel: " + graph.size() + " sommets");
        } else {
            System.out.println("⚠️  Aucun graphe défini");
        }
    }
    
    private static void afficherMenuBFS() {
        System.out.println("\n" + "-".repeat(40));
        System.out.println("         MENU BFS");
        System.out.println("-".repeat(40));
        System.out.println("1. ➕ Créer un nouveau graphe");
        System.out.println("2. 🔄 BFS basique");
        System.out.println("3. 📈 BFS niveau par niveau");
        System.out.println("4. 📏 BFS avec distances");
        System.out.println("5. 🛤️  Plus court chemin");
        System.out.println("6. ✅ Vérifier accessibilité");
        System.out.println("7. 🎯 Sommets accessibles");
        System.out.println("8. 🌐 BFS complet");
        System.out.println("9. 🔢 Compter composantes");
        System.out.println("10. 📐 Chemins de longueur K");
        System.out.println("11. 👁️  Afficher le graphe");
        System.out.println("0. 🔙 Retour");
        System.out.println("-".repeat(40));
        
        if (graph != null) {
            System.out.println("Graphe actuel: " + graph.size() + " sommets");
        } else {
            System.out.println("⚠️  Aucun graphe défini");
        }
    }
    
    private static void menuGraphe() {
        // Cette méthode n'est plus nécessaire avec la nouvelle structure
    }
    
    private static void creerGraphe() {
        System.out.println("\n=== CRÉATION DU GRAPHE ===");
        
        int nombreSommets = saisirEntier("Nombre de sommets: ");
        while (nombreSommets <= 0) {
            System.out.println("❌ Le nombre doit être positif.");
            nombreSommets = saisirEntier("Nombre de sommets: ");
        }
        
        graph = DFS.createGraph(nombreSommets);
        System.out.println("✅ Graphe créé avec " + nombreSommets + " sommets (0 à " + (nombreSommets-1) + ")");
        
        ajouterAretes();
    }
    
    private static void modifierGraphe() {
        // Cette méthode n'est plus nécessaire avec la nouvelle structure
    }
    
    private static void ajouterAretes() {
        System.out.println("\n=== AJOUT DES ARÊTES ===");
        System.out.println("Sommets disponibles: 0 à " + (graph.size()-1));
        
        while (true) {
            System.out.println("\nVoulez-vous ajouter une arête ?");
            System.out.println("1. ✅ Oui");
            System.out.println("2. ❌ Non, terminer");
            
            int continuer = saisirEntier("Votre choix: ");
            if (continuer != 1) break;
            
            int source = saisirSommetValide("Sommet source: ");
            int destination = saisirSommetValide("Sommet destination: ");
            
            if (graph.get(source).contains(destination)) {
                System.out.println("⚠️  L'arête " + source + "→" + destination + " existe déjà.");
            } else {
                DFS.addEdge(graph, source, destination);
                System.out.println("✅ Arête ajoutée: " + source + " → " + destination);
            }
        }
        
        System.out.println("✅ Configuration des arêtes terminée.");
    }
    
    private static void afficherGraphe() {
        if (graph == null) {
            System.out.println("❌ Aucun graphe défini.");
            return;
        }
        
        System.out.println("\n=== GRAPHE ACTUEL ===");
        DFS.printGraph(graph);
        
        int totalAretes = 0;
        for (List<Integer> adjacents : graph) {
            totalAretes += adjacents.size();
        }
        System.out.println("Résumé: " + graph.size() + " sommets, " + totalAretes + " arêtes");
    }
    
    // Méthodes d'exécution DFS
    private static void executerDFSRecursif() {
        int sommet = saisirSommetValide("Sommet de départ: ");
        System.out.println("\n🔄 DFS Récursif:");
        DFS.dfsRecursive(graph, sommet);
        pauseClavier();
    }
    
    private static void executerDFSIteratif() {
        int sommet = saisirSommetValide("Sommet de départ: ");
        System.out.println("\n📚 DFS Itératif:");
        DFS.dfsIterative(graph, sommet);
        pauseClavier();
    }
    
    private static void executerDFSComplet() {
        System.out.println("\n🌐 DFS Complet:");
        DFS.dfsComplete(graph);
        pauseClavier();
    }
    
    private static void verifierCycle() {
        System.out.println("\n🔁 Détection de cycles:");
        boolean cycle = DFS.hasCycle(graph);
        if (cycle) {
            System.out.println("🔴 Le graphe contient un cycle.");
        } else {
            System.out.println("🟢 Le graphe ne contient pas de cycle.");
        }
        pauseClavier();
    }
    
    private static void executerTriTopologique() {
        System.out.println("\n📋 Tri topologique:");
        if (DFS.hasCycle(graph)) {
            System.out.println("❌ Impossible: le graphe contient un cycle.");
        } else {
            List<Integer> result = DFS.topologicalSort(graph);
            System.out.println("✅ Ordre topologique: " + result);
        }
        pauseClavier();
    }
    
    private static void executerComposantesConnexes() {
        System.out.println("\n🧩 Composantes fortement connexes:");
        DFS.stronglyConnectedComponents(graph);
        pauseClavier();
    }
    
    // Méthodes d'exécution BFS
    private static void executerBFS() {
        int sommet = saisirSommetValide("Sommet de départ: ");
        System.out.println("\n🔄 BFS:");
        BFS.bfs(graph, sommet);
        pauseClavier();
    }
    
    private static void executerBFSNiveaux() {
        int sommet = saisirSommetValide("Sommet de départ: ");
        System.out.println("\n📈 BFS niveau par niveau:");
        BFS.bfsLevelOrder(graph, sommet);
        pauseClavier();
    }
    
    private static void executerBFSAvecDistances() {
        int sommet = saisirSommetValide("Sommet de départ: ");
        System.out.println("\n📏 Calcul des distances:");
        BFS.bfsWithDistances(graph, sommet);
        pauseClavier();
    }
    
    private static void trouverPlusCourtChemin() {
        int source = saisirSommetValide("Sommet source: ");
        int destination = saisirSommetValide("Sommet destination: ");
        
        System.out.println("\n🛤️  Plus court chemin:");
        List<Integer> path = BFS.shortestPath(graph, source, destination);
        
        if (path.isEmpty()) {
            System.out.println("❌ Aucun chemin trouvé entre " + source + " et " + destination);
        } else {
            System.out.println("✅ Chemin: " + path);
            System.out.println("📏 Longueur: " + (path.size() - 1) + " arête(s)");
        }
        pauseClavier();
    }
    
    private static void verifierAccessibilite() {
        int source = saisirSommetValide("Sommet source: ");
        int destination = saisirSommetValide("Sommet destination: ");
        
        System.out.println("\n✅ Vérification d'accessibilité:");
        boolean accessible = BFS.isReachable(graph, source, destination);
        
        if (accessible) {
            System.out.println("🟢 " + destination + " est accessible depuis " + source);
        } else {
            System.out.println("🔴 " + destination + " n'est pas accessible depuis " + source);
        }
        pauseClavier();
    }
    
    private static void trouverSommetsAccessibles() {
        int sommet = saisirSommetValide("Sommet de départ: ");
        
        System.out.println("\n🎯 Sommets accessibles:");
        Set<Integer> accessibles = BFS.findReachableVertices(graph, sommet);
        
        List<Integer> sorted = new ArrayList<>(accessibles);
        Collections.sort(sorted);
        System.out.println("✅ Accessibles: " + sorted);
        System.out.println("📊 Total: " + accessibles.size() + " sommet(s)");
        pauseClavier();
    }
    
    private static void executerBFSComplet() {
        System.out.println("\n🌐 BFS Complet:");
        BFS.bfsComplete(graph);
        pauseClavier();
    }
    
    private static void compterComposantes() {
        System.out.println("\n🔢 Composantes connexes:");
        int count = BFS.countConnectedComponents(graph);
        System.out.println("✅ Nombre de composantes: " + count);
        pauseClavier();
    }
    
    private static void trouverCheminsLongueurK() {
        int sommet = saisirSommetValide("Sommet de départ: ");
        int k = saisirEntierPositif("Longueur K: ");
        
        System.out.println("\n📐 Chemins de longueur " + k + ":");
        List<List<Integer>> paths = BFS.findPathsOfLengthK(graph, sommet, k);
        
        if (paths.isEmpty()) {
            System.out.println("❌ Aucun chemin de longueur " + k + " trouvé.");
        } else {
            System.out.println("✅ " + paths.size() + " chemin(s) trouvé(s):");
            int maxAffichage = 10;
            for (int i = 0; i < Math.min(paths.size(), maxAffichage); i++) {
                System.out.println("  " + (i + 1) + ". " + paths.get(i));
            }
            if (paths.size() > maxAffichage) {
                System.out.println("  ... et " + (paths.size() - maxAffichage) + " autres");
            }
        }
        pauseClavier();
    }
    
    // Méthodes utilitaires
    private static int saisirEntier(String message) {
        while (true) {
            System.out.print(message);
            try {
                return Integer.parseInt(scanner.nextLine().trim());
            } catch (NumberFormatException e) {
                System.out.println("❌ Nombre entier requis.");
            }
        }
    }
    
    private static int saisirEntierPositif(String message) {
        while (true) {
            int valeur = saisirEntier(message);
            if (valeur >= 0) {
                return valeur;
            }
            System.out.println("❌ Valeur positive requise.");
        }
    }
    
    private static int saisirSommetValide(String message) {
        while (true) {
            int sommet = saisirEntier(message);
            if (sommet >= 0 && sommet < graph.size()) {
                return sommet;
            }
            System.out.println("❌ Sommet invalide. Utilisez 0 à " + (graph.size() - 1));
        }
    }
    
    private static void pauseClavier() {
        System.out.println("\nAppuyez sur ENTRÉE pour continuer...");
        scanner.nextLine();
    }
    
}
