package dashboard.fxml;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.PrintWriter;
import java.util.ArrayList;

public class GraphHuman<V extends Comparable<V>, E> {
    private GraphNode head;

    public GraphHuman() {
        head = null;
    }

    public boolean isEmpty() {
        return (head==null);
    }

    public int getSize() {
        int count=0;
        GraphNode currentNode = head;
        while (currentNode != null) {
            currentNode = currentNode.getVerticeLink();
            count++;
        }
        return count;
    }

    public void showGraph() {
        try {
            PrintWriter graph = new PrintWriter(new FileOutputStream("Relationship.txt"));
            GraphNode currentNode = head;
            while (currentNode != null) {
                graph.println(currentNode.toString());
                EdgeHuman edgeNode = (EdgeHuman) currentNode.getEdgeLink();
                while (edgeNode != null) {
                    graph.print(edgeNode.toString());
                    edgeNode = edgeNode.getEdgeLink();
                }
                graph.println();
                currentNode = currentNode.getVerticeLink();
            }
            graph.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }

    }

    public GraphNode hasVertice(V a) {
        GraphNode currentNode = head;
        if (isEmpty()) {
            return null;
        }
        else {
            while (currentNode!= null) {
                if (a.compareTo( (V) currentNode.getVertice())==0) {
                    return currentNode;
                }
                currentNode = currentNode.getVerticeLink();
            }
        }
        return null;
    }

    public void addVertice(V a) {
        GraphNode newNode =new GraphNode(a, null);
        GraphNode currentNode = head;
        if (head==null) {
            head = newNode;
        }
        else {
            while (currentNode.getVerticeLink()!= null)
                currentNode = currentNode.getVerticeLink();
            currentNode.setVerticeLink(newNode);
        }
    }

    //add a from to Edge class constructor to identify whose relationship
    public boolean addEdge(V from, V to, E weight) {
        if (hasVertice(from)==null || hasVertice(to)==null)
            return false;
        else {
            V previous=from;
            GraphNode currentNode = head;
            while (currentNode!= null) {
                if (from.compareTo( (V) currentNode.getVertice())==0) {
                    GraphNode temp = hasVertice(to);
                    EdgeHuman<V,E> newNode = new EdgeHuman<>(temp, weight, null,from);
                    EdgeHuman edgeNode = (EdgeHuman) currentNode.getEdgeLink();
                    if (edgeNode==null)
                        currentNode.setEdgeLink(newNode);
                    else {
                        while(edgeNode.getEdgeLink()!=null)
                            edgeNode = edgeNode.getEdgeLink();
                        edgeNode.setEdgeLink(newNode);
                    }
                    return true;
                }
                else
                    currentNode = currentNode.getVerticeLink();
            }
        }
        return false;
    }


    public boolean isEdge(V from, V to) {
        if (hasVertice(from)==null || hasVertice(to)==null)
            return false;
        else {
            GraphNode currentNode = head;
            while (currentNode!= null) {
                if (from.compareTo( (V) currentNode.getVertice())==0) {
                    GraphNode temp = hasVertice(to);
                    EdgeHuman edgeNode = (EdgeHuman) currentNode.getEdgeLink();
                    if (edgeNode==null)
                        return false;
                    else {
                        while(edgeNode!=null) {
                            if (edgeNode.getVerticeLink()==temp)
                                return true;
                            edgeNode = edgeNode.getEdgeLink();
                        }
                    }
                }
                else
                    currentNode = currentNode.getVerticeLink();
            }
        }
        return false;
    }

    public E getWeight(V from, V to) {
        if (isEdge(from, to)) {
            GraphNode currentNode = head;
            while (currentNode!= null) {
                if (from.compareTo( (V) currentNode.getVertice())==0) {
                    GraphNode temp = hasVertice(to);
                    EdgeHuman edgeNode = (EdgeHuman) currentNode.getEdgeLink();
                    while(edgeNode!=null) {
                        if (edgeNode.getVerticeLink()==temp)
                            return (E) edgeNode.getWeight();
                        edgeNode = edgeNode.getEdgeLink();
                    }
                }
                else
                    currentNode = currentNode.getVerticeLink();
            }
        }
        return null;
    }

    public ArrayList getAdjacent(V a) {
        ArrayList<V> array = new ArrayList();
        if (hasVertice(a)!=null) {
            GraphNode currentNode = head;
            while (currentNode!= null) {
                if (a.compareTo( (V) currentNode.getVertice())==0) {
                    EdgeHuman edgeNode = (EdgeHuman) currentNode.getEdgeLink();
                    while (edgeNode!=null) {
                        array.add((V)edgeNode.getVerticeLink().getVertice());
                        edgeNode = edgeNode.getEdgeLink();
                    }
                    break;
                }
                else
                    currentNode = currentNode.getVerticeLink();
            }
        }
        return array;
    }


}

