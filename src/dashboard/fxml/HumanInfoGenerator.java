package dashboard.fxml;





import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Random;
import java.util.Scanner;

public class HumanInfoGenerator {
        Random r = new Random();
        int count = 1;
        public final int population = 10000, male = 4500, female = 5500, children = 3600, adult = 3900, seniorResidents = 2500;
        public ArrayList<HumanInfo> humanList = new ArrayList<>(population);
        ActivityLog a=new ActivityLog();

        public void generateInfo(Places b){
            int [] n_Gender = {male, female} ;
            String[] gender = {"Male", "Female"};
            int [] n_ageRange = {children, adult, seniorResidents};
            String[] ageRange = {"Children", "Adult", "Senior Residents"};
            String[] firstThreeNumber = {"010","011","012","013","014","016","017","018","019"};

            String[] occupations = {"Student", "Retired", "Farmer", "Fisherman", "Teacher", "Grocer", "Postman", "Doctor", "Nurse", "Construction worker", "Veterinarian", "Cook", "Police",
                    "Barber", "Electrical Technician", "Baker", "Craftman", "Carpenter", "Grab Driver", "Vendor", "Hotel worker", "Bank officer", "Security Guard", "Waiter", "Engineer", "Factory worker"};
            int[] job = {3600, 2500, 100, 100, 140, 100, 20, 250, 300, 300, 10, 150, 200, 30, 200, 50, 50, 100, 50, 300, 150, 150, 50, 300, 300, 500};

            for (int i = 0; i < population; i++) {
                humanList.add(new HumanInfo(String.format("%04d", i)));
            }


            // GENDER
            int countM = 0, countF = 0;
            while ((countM + countF) < population) {
                int temp = r.nextInt(gender.length);
                if (temp == 0 && countM < n_Gender[0]) {
                    humanList.get(countM + countF).setGender(gender[temp]);
                    countM++;
                }

                if (temp == 1 && countF < n_Gender[1]) {
                    humanList.get(countM + countF).setGender(gender[temp]);
                    countF++;
                }
            }

            // CHILD, ADULT & SENIOR
            int countC = 0, countA = 0, countS = 0;

            while ((countC + countA + countS) < population) {
                int temp = r.nextInt(ageRange.length);
                // CHILD: 0 - 30%
                if (temp == 0 && countC < n_ageRange[0]) {
                    humanList.get(countC + countA + countS).setAgeRange(ageRange[temp]);
                    humanList.get(countC + countA + countS).setForgetfulness(r.nextInt(31));
                    humanList.get(countC + countA + countS).setContactNumber("+6"+firstThreeNumber[r.nextInt(9)] + " - " + String.format("%07d",r.nextInt(9999999)));
                    countC++;

                    // ADULT: 5 - 50%
                } else if (temp == 1 && countA < n_ageRange[1]) {
                    humanList.get(countC + countA + countS).setAgeRange(ageRange[temp]);
                    humanList.get(countC + countA + countS).setForgetfulness(r.nextInt(46) +5);
                    humanList.get(countC + countA + countS).setContactNumber("+6"+firstThreeNumber[r.nextInt(9)] + " - " + String.format("%07d",r.nextInt(9999999)));
                    countA++;

                    // SENIOR: 10 - 70%
                } else if (temp == 2 && countS < n_ageRange[2]) {
                    humanList.get(countC + countA + countS).setAgeRange(ageRange[temp]);
                    humanList.get(countC + countA + countS).setForgetfulness(r.nextInt(60) + 10);
                    humanList.get(countC + countA + countS).setContactNumber("+6"+firstThreeNumber[r.nextInt(9)] + " - " + String.format("%07d",r.nextInt(9999999)));
                    countS++;
                }
            }


            // OCCUPATION
            // ALL CHILD = STUDENT
            int index = 0;
            int[] countO = new int[job.length];
            while (index < population) {
                if (humanList.get(index).getAgeRange().equals("Children") && countO[0] < job[0]) {
                    int r1=r.nextInt(5)+1;
                    humanList.get(index).setJob(occupations[0]);
                    humanList.get(index).setWorkPlace(b.find("School", r1));
                    humanList.get(index).setWorkNature(2);
                    countO[0]++;

                } else if (humanList.get(index).getAgeRange().equals("Senior Residents") && countO[1] < job[1]) {
                    humanList.get(index).setJob(occupations[1]);
                    humanList.get(index).setWorkNature(2);
                    countO[1]++;

                } else {
                    boolean flag = false;
                    do {
                        int temp = r.nextInt(occupations.length - 2) + 2; // -2 and +2 to skip student and retired

                        if (countO[temp] < job[temp]) {
                            flag = true;
                            humanList.get(index).setJob(occupations[temp]);
                            countO[temp]++;

                            switch(temp){
                                // Farmer
                                case 2:
                                    humanList.get(index).setImmunity(2);
                                    humanList.get(index).setWorkNature(1);
                                    humanList.get(index).setWorkPlace(b.go("Farm area"));
                                    break;

//                            Fisherman
                                case 3:
                                    humanList.get(index).setImmunity(2);
                                    humanList.get(index).setWorkNature(1);
                                    humanList.get(index).setWorkPlace(b.go("Beach"));
                                    break;

                                // Teacher
                                case 4:
                                    int r1=r.nextInt(5)+1;
                                    humanList.get(index).setImmunity(1);
                                    humanList.get(index).setWorkNature(2);
                                    humanList.get(index).setWorkPlace(b.find("School", r1));
                                    break;

                                // grocer
                                case 5:
                                    r1=r.nextInt(25)+1;
                                    humanList.get(index).setImmunity(2);
                                    humanList.get(index).setWorkNature(2);
                                    humanList.get(index).setWorkPlace(b.find("Grocery", r1));
                                    break;

                                // Postman
                                case 6:
                                    humanList.get(index).setImmunity(2);
                                    humanList.get(index).setWorkNature(2);
                                    break;

                                // Doctor
                                case 7:
                                    humanList.get(index).setImmunity(2);
                                    humanList.get(index).setWorkNature(2);

                                    int tempDoc = r.nextInt(10) + 1;

                                    if(tempDoc <= 3) {
                                        r1=r.nextInt(10)+1;
                                        humanList.get(index).setWorkPlace(b.find("Clinic", r1));}
                                    else {
                                        r1=r.nextInt(3)+1;
                                        humanList.get(index).setWorkPlace(b.find("Hospital", r1));}

                                    break;

                                // Nurse
                                case 8:
                                    humanList.get(index).setImmunity(2);
                                    humanList.get(index).setWorkNature(2);
                                    int tempNurse = r.nextInt(10) + 1;

                                    if(tempNurse <= 3) {
                                        r1=r.nextInt(10)+1;
                                        humanList.get(index).setWorkPlace(b.find("Clinic", r1));}
                                    else {
                                        r1=r.nextInt(3)+1;
                                        humanList.get(index).setWorkPlace(b.find("Hospital", r1));}

                                    break;

                                // Construction worker
                                case 9:
                                    humanList.get(index).setImmunity(2);
                                    humanList.get(index).setWorkNature(1);
                                    humanList.get(index).setWorkPlace(b.go("Development zone"));
                                    break;

                                // Veterinarian
                                case 10:
                                    humanList.get(index).setImmunity(2);
                                    humanList.get(index).setWorkNature(2);
                                    humanList.get(index).setWorkPlace(b.find("Veterinary", countO[temp]));
                                    break;

                                // cook
                                case 11:
                                    humanList.get(index).setImmunity(2);
                                    humanList.get(index).setWorkNature(1);
                                    humanList.get(index).setWorkPlace(b.go("Restaurant"));
                                 break;

                                // Police
                                case 12:
                                    humanList.get(index).setImmunity(2);
                                    humanList.get(index).setWorkNature(2);
                                    humanList.get(index).setWorkPlace(b.go("Restaurant"));
                                 break;

                                // barber
                                case 13:
                                    humanList.get(index).setImmunity(1);
                                    humanList.get(index).setWorkNature(1);
                                    humanList.get(index).setWorkPlace(b.find("Barbershop", countO[temp]));
                                 break;

                                // Electical technician
                                case 14:
                                    humanList.get(index).setImmunity(2);
                                    humanList.get(index).setWorkNature(1);
                                    humanList.get(index).setWorkPlace(b.go("Electrical shop"));
                                 break;

                                // Baker
                                case 15:
                                    r1=r.nextInt(20)+1;
                                    humanList.get(index).setImmunity(2);
                                    humanList.get(index).setWorkNature(1);
                                    humanList.get(index).setWorkPlace(b.find("Bakery", r1));
                                 break;

                                // craftman
                                case 16:
                                    humanList.get(index).setImmunity(1);
                                    humanList.get(index).setWorkNature(1);
                                    humanList.get(index).setWorkPlace(b.go("Workshop"));
                                 break;

                                // carpenter
                                case 17:
                                    humanList.get(index).setImmunity(2);
                                    humanList.get(index).setWorkNature(1);
                                    humanList.get(index).setWorkPlace(b.go("Workshop"));
                                  break;

                                // grab driver
                                case 18:
                                    humanList.get(index).setImmunity(1);
                                    humanList.get(index).setWorkNature(2);
                                  break;

                                // vendor
                                case 19:
                                    humanList.get(index).setImmunity(1);
                                    humanList.get(index).setWorkNature(2);
                                    humanList.get(index).setWorkPlace(b.go("Market"));
                                  break;

                                // Hotel worker
                                case 20:
                                    humanList.get(index).setImmunity(2);
                                    humanList.get(index).setWorkNature(1);
                                    humanList.get(index).setWorkPlace(b.go("Hotel"));
                                  break;

                                // Bank officer
                                case 21:
                                    humanList.get(index).setImmunity(1);
                                    humanList.get(index).setWorkNature(2);
                                    humanList.get(index).setWorkPlace(b.go("Bank"));
                                 break;

                                // security guard
                                case 22:
                                    humanList.get(index).setImmunity(2);
                                    humanList.get(index).setWorkNature(1);
                                    humanList.get(index).setWorkPlace(b.go("House"));
                                break;

                                // waiter
                                case 23:
                                    humanList.get(index).setImmunity(2);
                                    humanList.get(index).setWorkNature(2);
                                    humanList.get(index).setWorkPlace(b.go("Restaurant"));
                                 break;

                                // engineer
                                case 24:
                                    humanList.get(index).setImmunity(2);
                                    humanList.get(index).setWorkNature(2);
                                    humanList.get(index).setWorkPlace(b.go("Factory"));
                                break;

                                // Factory worker
                                case 25:
                                    humanList.get(index).setImmunity(2);
                                    humanList.get(index).setWorkNature(2);
                                    humanList.get(index).setWorkPlace(b.go("Factory"));
                                 break;



                                default:
                            }

                        }
                    } while (flag == false);
                }
                index++;
            }

            try {
                PrintWriter p = new PrintWriter(new FileOutputStream("Name.txt"));
                System.out.println("Generating sprites...");
                p.write("ID: Gender, Age Category, Forgetfulness(%), Occupation, WorkPlace\n");

                for (int i = 0; i < 10000; i++) {
                    p.write(humanList.get(i).getID() + ", " + humanList.get(i).getGender() + ", " + humanList.get(i).getAgeRange() + ", " + humanList.get(i).getForgetfulness() + "%, " + humanList.get(i).getJob() + ", " + humanList.get(i).getWorkPlace() + "\n");
                }
                p.close();
            } catch (FileNotFoundException e) {
                System.out.println("Problem with file.");
            }

            System.out.println("GENERATING STATUS: [COMPLETED]");
            System.out.println("TOTAL CITIZENS: " + population);


        }
        public void printDaily(Places b, int day,ContactTracer contactTracer){

            try {

                if(day>3){
                    count++;
                    for(HumanInfo i : humanList){
                        i.deleteActivity(day-3);
                    }
                }

                PrintWriter p = new PrintWriter(new FileOutputStream("Data"+count+".txt"));


                for (int i = 0; i < humanList.size(); i++) {

                    int hours=6;
                    while(hours<=20){
                        a.generateDayActivity(humanList.get(i), day, hours, b,contactTracer);
                        hours++;
                    }
                    p.write(humanList.get(i).showData(humanList.get(i).getID()));

                }
                p.close();
            } catch (FileNotFoundException e) {
                System.out.println("Problem with file.");
            }




        }

//        private void getHumanID(){
//            System.out.println("Retrieving data...");
//            int citizenCount = 0;
//            try{
//                Scanner s = new Scanner(new FileInputStream("Name.txt"));
//                // Skip the first table row
//                s.nextLine();
//
//                while(s.hasNextLine()){
//                    // ID, Gender, Age Range, Job, forgetfulness , work nature , immunity,  WorkPlace
//                    String info = s.nextLine();
//                    String[] split = info.split(", ");
//                    humanList.add(new HumanInfo(split[0], split[1], split[2], split[3], split[4], split[5] , split[6], split[7]));
////                System.out.println(info);
//                    citizenCount++;
//                }
//
//                s.close();
//            } catch (FileNotFoundException fnf){
//                System.err.println("File not found!");
//            }
//
//            System.out.println("RETRIVE HUMAN ID: [COMPLETED]");
//            System.out.println("TOTAL CITIZENS: " + citizenCount);
//        }


        public HumanInfo getHumanInfo(String id){
            for(HumanInfo h : humanList){
                if(h.getID().compareTo(id)==0){
                    return h;
                }
            }
            return null;
        }
    }


