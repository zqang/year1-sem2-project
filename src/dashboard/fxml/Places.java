package dashboard.fxml;
import java.util.Arrays;
import java.util.Random;
public class Places {
private final int x=30,y=30;
private String [][] location=new String[x][y];
private String []arrange=new String[x*y];
Random r =new Random();
public int getrow() {
        return x;
    }
public int getcolumn() {
        return y;
    }
public String[][] getLocation() {
        return location;
    }
public String randomPlace(){
int r1=r.nextInt(20);
String str="";
switch(r1){
    case 0:
    case 1:
    case 2:
    case 3:
        str="Market";
        break;
    case 4:
        str="Beach";
        break;    
    case 5:
        str="Bank";
        break;
    case 6:
        str="Workshop";
        break;
    case 7:
        str="Barbershop";
        break;
    case 8:
    case 9:
        str="Bakery";
        break;
    case 10:
    case 11:
    case 12:
        str="Restaurant";
    case 13:
    case 14:
    case 15:
    case 16:
        str="Grocery";
        break;
    case 17:
        str="Electrical shop";
        break;
    case 18:
        str="Clinic";
        break;
    case 19:
        str="Veterinary";
        break;
}
return str;
}
public String grab(){
int r1=r.nextInt(12);
String str="";
switch(r1){
    case 0:
    case 1:
    case 2:
    case 3:
    case 4:
        str="House";
        break;
    case 5:
        str="Development zone";
        break;
    case 6:
        str="Farm area";
        break;
    case 7:
        str="Hotel";
        break;
    case 8:
        str="Factory";
        break;
    case 9:
        str="Playground";
        break;
    case 10:
        str="Hospital";
        break;
    case 11:
        str="Police Station";
        break;
}
return str;

}
public void storeArray(){
    int cnt=0;
for(int i=0;i<x;i++){
    for(int j=0;j<y;j++){
    if(!location[i][j].equals("*")){
      arrange[cnt]=location[i][j];
        cnt++;
    }
    }}
Arrays.sort(arrange,0,cnt);
}
public String find(String a,int b){
    int i=0;
    int cnt=0;
    String str="";
while(true){
if(arrange[i]!=null){
    if(arrange[i].contains(a)){
        cnt++;
    if(cnt==b){
        str=arrange[i];
        break;
    }
    else 
        i++;}
    else
        i++;
}
}
return str;
}
public void place(){
for(int i=0;i<x;i++){
    for(int j=0;j<y;j++){
    if((i<=5&&j<=5)||(i>=x-6&&j>=y-6))
    location[i][j]="Beach"+"("+i+","+j+")";
    else
    location[i][j]="*";
    }
}
add("School",5);
add("Veterinary",10);
add("Farm area",10);
add("Market",50);
add("House",100);
add("Hospital",3);
add("Restaurant",30);
add("Police Station",4);
add("Bank",5);
add("Grocery",25);
add("Barbershop",30);
add("Development zone",25);
add("Bakery",20);
add("Workshop",30);
add("Electrical shop",30);
add("Clinic",10);
add("Hotel",30);
add("Playground",20);
add("Factory",50);




}
public void showPlace(){
    int cnt=0 ;
for(int i=0;i<x;i++){
    for(int j=0;j<y;j++){
        if(!location[i][j].equals("*")){
            
            switch(location[i][j].replaceAll("[^a-zA-Z]", "")){
                case "Barbershop":
                    System.out.print("BS ");
                    break;
                case "Hospital":
                    System.out.print("HP ");
                    break;
                case "Hotel":
                    System.out.print("HT ");
                    break;
                case "Bank":
                    System.out.print("BK ");
                    break;
                case "Bakery":
                    System.out.print("BY ");
                    break;
                case "Grocery":
                    System.out.print("GR ");
                    break;
                case "Farm area":
                    System.out.print("FA ");
                    break;
                case "Police Station":
                    System.out.print("PS ");
                    break;
                default:    
                    System.out.print(location[i][j].charAt(0)+"  ");
                    break;
            }
        }
        else 
            System.out.print(location[i][j].charAt(0)+"  ");

    }
    System.out.println("");
}


}
public String go(String name){
    String temp="";
int a=r.nextInt(x*y);
while(true){
    a=r.nextInt(x*y);
    if(arrange[a]!=null){
        if(arrange[a].contains(name)){
        temp=arrange[a];
        break;}
    }
}return temp;
}
public void add(String a,int b){
for(int i=0;i<b;i++){
int x1=r.nextInt(x-1);
int y1=r.nextInt(y-1);
if(location[x1][y1].equals("*"))
        location[x1][y1]=a+"("+x1+","+y1+")";
else
    i-=1;}
}
}
