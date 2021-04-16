package dashboard.fxml;

public class EdgeHuman<V,E> {
    private GraphNode verticeLink;
    private EdgeHuman edgeLink;
    private E weight;
    public V from;

    public EdgeHuman() {
        verticeLink = null;
        weight=null;
        edgeLink = null;
    }

    public EdgeHuman(GraphNode a, E b, EdgeHuman c,V d) {
        verticeLink = a;
        edgeLink = c;
        from=d;
        weight = b;
    }
    public String Weight() {
        switch((Integer)weight){
            case 0:return from+"'s Grandchild";
            case 1:return from+"'s Child";
            case 2:return from+"'s Sibling";
            case 3:return from+"'s Spouse";
            case 4:return from+"'s Parent";
            case 5:return from+"'s Grandparent";
            case 6:return from+"'s Friend";
        }
        return "";
    }
    //insert type of relationship betweem two human id
    public void setWeight(E a) { weight = a; }
    public E getWeight() {return weight;}
    public void setVerticeLink(GraphNode a) { verticeLink = a;}
    public GraphNode getVerticeLink() { return verticeLink; }
    public void setEdgeLink(EdgeHuman a) { edgeLink = a;}
    public EdgeHuman getEdgeLink() { return edgeLink; }
    public String toString() { return " -> " + verticeLink.getVertice() + " : " + Weight(); }
}
