package dashboard.fxml;

public class EdgePlace<E> {
    private GraphNode verticeLink;
    private EdgePlace edgeLink;
    private E weight;
    
    public EdgePlace() {
        verticeLink = null;
        weight=null;
        edgeLink = null;
    }
    
    public EdgePlace(GraphNode a, E b, EdgePlace c) {
        verticeLink = a; 
        weight = b;
        edgeLink = c;
    }
    
    public void setWeight(E a) {weight = a;}
    public E getWeight() {return weight;}    
    public void setVerticeLink(GraphNode a) { verticeLink = a;}
    public GraphNode getVerticeLink() { return verticeLink; }
    public void setEdgeLink(EdgePlace a) { edgeLink = a;}
    public EdgePlace getEdgeLink() { return edgeLink; }
    public String toString() { return " -> " + verticeLink.getVertice() + " : " + weight; }    
}


