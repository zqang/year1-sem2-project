package dashboard.fxml;



public class LinkedList <D extends Comparable<D>,H extends Comparable<H>,A extends Comparable<A>>{
    private ListNode head;
    public LinkedList(){
    head = null;
    }
    public int length(){
    int count = 0;
    ListNode currentNode = head;
    while(currentNode != null){
        currentNode = currentNode.getLink();
        count++;
    }
    return count;
    }

    
    public boolean isEmpty(){return head ==null;}

        public String showData(String a){
        String temp="";
        ListNode currentNode = head;
        while(currentNode !=null){
            temp+=currentNode.toData(a);
            currentNode = currentNode.getLink();
        }return temp;
    }
        
    public void addNode(D a,H b,A c){
    ListNode newNode = new ListNode(a,b,c,null);
    ListNode currentNode = head;
    if(head==null){
        head = newNode;
    }
    else{
        while (currentNode.getLink() !=null)
            currentNode = currentNode.getLink();
        currentNode.setLink(newNode);
        }
    }
    
    public void deleteFrontNode(){
        if(head !=null)
            head = head.getLink();
        else
            System.out.println("The list is empty.");
    }


    public void deleteDay(D a){
        ListNode current = head;
        ListNode previous = head;
        while(current != null){
            if(a.compareTo((D) current.getDay())==0){
                ListNode temp = current.getLink();
                previous.setLink(temp);
                current = temp;

            }else{
                previous = current;
                current = current.getLink();
            }
        }
    }
    
}
