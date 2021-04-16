package dashboard.fxml;

public class ListNode<D,H,A> {
    private D day;
    private H hours;
    private A activity;
    private ListNode link;

    public ListNode(D a, H b,A c,ListNode d){
        day = a;
        hours = b;
        activity = c;
        link = d;
    }

    public D getDay(){return day;}
    public H getHours(){return hours;}
    public A getActivity(){return activity;}

    public void setLink(ListNode a){link = a;}
    public ListNode getLink(){return link;}

    public String toString(){return "Day "+day+","+hours+" hours "+activity+"--> ";}
    public String toData(String a){return a+"."+day+"."+hours+"."+activity+"\n";}

}


