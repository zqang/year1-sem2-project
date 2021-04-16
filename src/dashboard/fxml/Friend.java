package dashboard.fxml;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Random;
import java.util.Scanner;

public class Friend {
    private ArrayList<String> all=new ArrayList<>();
    private ArrayList<String>friend= new ArrayList<>();
    private int[]max=new int[10000];

    public ArrayList<String> getFriend() {
        return friend;
    }

    public Friend() {
        store();
    }
    public void store(){
        for(int i=0;i<max.length;i++)
            max[i]=0;
        Random r=new Random();
        try {
            Scanner in =new Scanner(new FileInputStream("Name.txt"));
            in.nextLine();
            while(in.hasNextLine()){
                String []content=in.nextLine().split(", ");
                all.add(content[0]);
            }
            for(int i=0;i<all.size();i++){
                for(int j=0;j<all.size();j++){
                    int n=r.nextInt(5000)+1;
                        if(n>4399&&max[i]<10&&max[j]<10){
                            friend.add(all.get(i)+" "+all.get(j));
                            friend.add(all.get(j)+" "+all.get(i));
                            max[i]++;
                            max[j]++;
                        }
                }
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }
}