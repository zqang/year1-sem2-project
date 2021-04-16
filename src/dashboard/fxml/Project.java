package dashboard.fxml;

import java.io.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Scanner;

public class Project {
    private static int count=0;
    public static GraphHuman<String, Integer> human = new GraphHuman<>();

    public static void human(Places a){
        //generate human list testing
//        generate grp from textfile
//        enter use for insert non-duplicate vertice
//        in use to add relationship between two vertice (Edge)
        Family b=new Family();
        b.addmember();
        b.print();
        Friend c=new Friend();

        System.out.println("Generating family");
        try{
            Scanner enter=new Scanner(new FileInputStream("Name.txt"));
            enter.nextLine();
            while(enter.hasNextLine()){
                String[]content=enter.nextLine().split(", ");
                human.addVertice(content[0]);
            }
            enter.close();
            System.out.println("Next");
            ArrayList<String> family=b.getFamily();
            for(int i=0;i<family.size();i++){
                String[]content=family.get(i).split(" ");
                String from=content[0];
                String to=content[1];
                int relation=Integer.parseInt(content[2]);
                human.addEdge(from,to,relation);
            }
            ArrayList<String> friend=c.getFriend();
            System.out.println("Next1");
            for(String s : friend){
                String[] arr = s.split(" ");
                human.addEdge(arr[0],arr[1],6);
                count++;
            }
            System.out.println("Next2");
            human.showGraph();
            System.out.println("Done!!!");
        }catch(FileNotFoundException e){
            System.out.println("File was not found");
        }
    }
    public static void place(Places a){
        a.place();
        a.storeArray();
        GraphPlace<String,Integer> rural=new GraphPlace<>();
        String[][] map=a.getLocation();
        //add vertice to graph
        for(int i=0;i<a.getrow();i++){
            for(int j=0;j<a.getcolumn();j++){
                if(!map[i][j].equals("*")&&!map[i][j].equals("Beach")){
                    rural.addVertice(map[i][j]);
                }
            }
        }
        //add an edge link from A to B
        for(int i=0;i<a.getrow();i++){
            for(int j=0;j<a.getcolumn();j++){
                int got=checkleft(map,i,j);
                if(got!=0){
                    rural.addEdge(map[i][j],map[i][j-got],got*50);
                }
                got=checkright(map,i,j);
                if(got!=0){
                    rural.addEdge(map[i][j],map[i][j+got],got*50);
                }
                got=checkup(map,i,j);
                if(got!=0){
                    rural.addEdge(map[i][j],map[i-got][j],got*50);
                }
                got=checkdown(map,i,j);
                if(got!=0){
                    rural.addEdge(map[i][j],map[i+got][j],got*50);
                }
            }
        }
//        rural.showGraph();
    }
    //seperate the human info according age range
    public static void create3file(Places b){
        HumanInfoGenerator a=new HumanInfoGenerator();
        a.generateInfo(b);
//        a.printDaily(b,1);
        try{
            Scanner in=new Scanner(new FileInputStream("Name.txt"));
            try{
                PrintWriter out = new PrintWriter(new FileOutputStream("grand.txt"));
                PrintWriter ou = new PrintWriter(new FileOutputStream("parent.txt"));
                PrintWriter o = new PrintWriter(new FileOutputStream("child.txt"));
                in.nextLine();
                while(in.hasNextLine()){
                    String[] content=in.nextLine().split(", ");
                    switch(content[2]){
                        case "Senior Residents":{
                            for(int i=0;i<content.length;i++){
                                out.print(content[i]+", ");
                            }
                            out.println("");
                            break;
                        }
                        case "Adult":{
                            for(int i=0;i<content.length;i++){
                                ou.print(content[i]+", ");
                            }
                            ou.println("");
                            break;
                        }
                        case "Children":{
                            for(int i=0;i<content.length;i++){
                                o.print(content[i]+", ");
                            }
                            o.println("");
                            break;
                        }
                    }
                }
                out.close();
                ou.close();
                o.close();
            } catch (FileNotFoundException e) {
                e.printStackTrace();
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }System.out.println("Age Range is saparated.");
    }
    //check duplicate vertice
    public static boolean pass(ArrayList<String> from,int a){
        for(int i=0;i<from.size();i++){
            if(i!=a){
                if(from.get(i).equals(from.get(a))){
                    return false;
                }
            }
        }
        return true;
    }
    //check the building at the left.right,uo,down
    //return  distance between two building
    public static int checkleft(String[][] map,int i,int j){
        for(int k=j-1;k>=0;k--){
            if(map[i][k]!="*"){
                return j-k;
            }
        }
        return 0;
    }
    public static int checkright(String[][] map,int i,int j){
        for(int k=j+1;k<map.length;k++){
            if(map[i][k]!="*"){
                return k-j;
            }
        }
        return 0;
    }
    public static int checkup(String[][] map,int i,int j){
        for(int k=i-1;k>=0;k--){
            if(map[k][j]!="*"){
                return i-k;
            }
        }
        return 0;
    }
    public static int checkdown(String[][] map,int i,int j){
        for(int k=i+1;k<map.length;k++){
            if(map[k][j]!="*"){
                return k-i;
            }
        }
        return 0;
    }
}
