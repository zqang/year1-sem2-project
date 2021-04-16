package dashboard.fxml;

import java.util.ArrayList;

public class GraphPlace<V extends Comparable<V>, E> {
    private GraphNode head;
    
    public GraphPlace() {
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
        
    public boolean addEdge(V from, V to, E weight) {
        if (hasVertice(from)==null || hasVertice(to)==null)
            return false;
        else {
            GraphNode currentNode = head;       
            while (currentNode!= null) {
               if (from.compareTo( (V) currentNode.getVertice())==0) {                
                    GraphNode temp = hasVertice(to);                    
                    EdgePlace newNode = new EdgePlace(temp, weight, null);
                    EdgePlace edgeNode = (EdgePlace) currentNode.getEdgeLink();
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
                    EdgePlace edgeNode = (EdgePlace) currentNode.getEdgeLink();
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
}

