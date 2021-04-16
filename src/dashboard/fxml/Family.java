package dashboard.fxml;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Random;
import java.util.Scanner;

public class Family {
    private int adult=3900,grand=2500,child=3600;
    private ArrayList<String> fam=new ArrayList<>();
    private ArrayList<String> oldm = new ArrayList<>();
    private ArrayList<String> oldf = new ArrayList<>();
    private ArrayList<String> midm = new ArrayList<>();
    private ArrayList<String> midf = new ArrayList<>();
    private ArrayList<String> young = new ArrayList<>();
    private ArrayList<String> family=new ArrayList<>();

    public ArrayList<String> getFamily() {
        return family;
    }

    public void  addmember(){
        Random r=new Random();
        seperate();
        String member="";
        int i=0,j=0;
        while(grand+adult+child!=0){
                if (i < oldm.size()) {
                    member += oldm.get(i) + "0" + " ";
                    grand--;
                }
                if (i < oldf.size()) {
                    member += oldf.get(i) + "0" + " ";
                    grand--;
                }
                if (i < midm.size()) {
                    member += midm.get(i) + "1" + " ";
                    adult--;
                }
                if (i < midf.size()) {
                    member += midf.get(i) + "1" + " ";
                    adult--;
                }
            i++;
            int n=r.nextInt(2)+1;
            if(child-n<=0){
                n=child;
            }
            for(int k=0;k<n;k++){
                member+=young.get(j+k)+"2"+" ";
                child--;
            }
            j+=n;
            fam.add(member);
            member="";
        }
    }

    public void seperate(){
        try{
            Scanner in=new Scanner(new FileInputStream("Name.txt"));
            in.nextLine();
            while(in.hasNextLine()){
                String[]content=in.nextLine().split(", ");
                if(content[2].equals("Senior Residents")){
                    if(content[1].equals("Male")){
                        oldm.add(content[0]);
                    }
                    if(content[1].equals("Female")){
                        oldf.add(content[0]);
                    }
                }
                if(content[2].equals("Adult")){
                    if(content[1].equals("Male")){
                        midm.add(content[0]);
                    }
                    if(content[1].equals("Female")){
                        midf.add(content[0]);
                    }
                }
                if(content[2].equals("Children")){
                    young.add(content[0]);
                }
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }
//0 's grandchild 1's child 2 's sibling  3 's wife 4 's parent 5's grand parent
    public void print(){
            for(int i=0;i<fam.size();i++){
                String[]mem=fam.get(i).split(" ");
                for(int j=0;j<mem.length;j++){
                    for(int k=0;k<mem.length;k++){
                        if(j!=k){
                            //compare two value just now added
                            //for example a is senior citizen,b is adult the relation is B=A's child
                            //0-1=-1
                            //add all this relation to Family File
                            switch((mem[j].substring(4)).compareTo(mem[k].substring(4))){
                                case -2:{
                                    family.add(mem[j].substring(0,4)+" "+mem[k].substring(0,4)+" "+0);
                                    break;
                                }
                                case -1:{
                                    family.add(mem[j].substring(0,4)+" "+mem[k].substring(0,4)+" "+1);
                                    break;
                                }
                                case 0:{
                                    if(mem[j].charAt(4)=='2'){
                                        family.add(mem[j].substring(0,4)+" "+mem[k].substring(0,4)+" "+2);
                                    }
                                    else{
                                        family.add(mem[j].substring(0,4)+" "+mem[k].substring(0,4)+" "+3);
                                    }
                                    break;
                                }
                                case 1:{
                                    family.add(mem[j].substring(0,4)+" "+mem[k].substring(0,4)+" "+4);
                                    break;
                                }
                                case 2:{
                                    family.add(mem[j].substring(0,4)+" "+mem[k].substring(0,4)+" "+5);
                                    break;
                                }
                            }
                        }
                    }
                }
            }
    }

}
