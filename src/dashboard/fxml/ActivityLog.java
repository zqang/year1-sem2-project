package dashboard.fxml;

import java.util.Random;

/**
 *
 * @author User
 */
public class ActivityLog {
    Random r = new Random();


    public void generateDayActivity(HumanInfo h, int day, int hours, Places p, ContactTracer contactTracer) {


        if (contactTracer.incompleteSuspected.contains(h.getID())) {
            h.setActivity(day, hours, "In Quarantine with ID " + h.getID());
        } else if (contactTracer.infected.contains(h.getID())) {
            h.setActivity(day, hours, "In Hospital with ID " + h.getID());
        } else {
            if (Government.isIsStart()) {
                if (!Government.checkIfIsNaughtyCitizens(h.getID())) {
                    if(Government.checkIfIsJobNotAffected(h.getJob()))
                        setActivity(h,day,hours,p);
                    else{
                        int a = r.nextInt(10);
                        switch (a){
                            case 0 : h.setActivity(day,hours,p.go("Restaurant"));break;
                            case 1 : h.setActivity(day,hours,p.go("Market"));break;
                            case 2 : h.setActivity(day,hours,p.go("Grocery"));break;
                            case 3 : h.setActivity(day,hours,p.go("Bank"));break;
                            case 4: case 5: case 6: case 7: case 8: case 9: h.setActivity(day,hours,"In Quarantine due to MCO with ID " + h.getID());break;
                        }
                    }
                } else {
                    setActivity(h,day,hours,p);
                }
            }else{
                setActivity(h,day,hours,p);
            }
        }
    }

    public void setActivity(HumanInfo h, int day, int hours, Places p ){
            switch (h.getJob()) {
                case "Student": {
                    switch (day % 7) {
                        case 1:
                        case 2:
                        case 3:
                        case 4:
                        case 5:
                            if (hours >= 7 && hours <= 14) {
                                h.setActivity(day, hours, h.getWorkPlace());
                                break;
                            } else if (hours >= 15 && hours <= 18) {
                                int random = r.nextInt(5);
                                if (random <= 1)
                                    h.setActivity(day, hours, p.go("Playground"));
                                else if (random == 2)
                                    h.setActivity(day, hours, p.go(p.randomPlace()));
                            }
                            break;
                        case 6:
                        case 0:
                            if (hours >= 8 && hours <= 18) {
                                int random = r.nextInt(6);
                                if (random <= 1)
                                    h.setActivity(day, hours, p.go("Playground"));
                                else if (random == 2)
                                    h.setActivity(day, hours, p.go(p.randomPlace()));
                            }
                            break;
                    }
                    break;
                }
                case "Retired":
                    switch (day % 7) {
                        case 1:
                        case 2:
                        case 3:
                        case 4:
                        case 5:
                            if (hours >= 9 && hours <= 17) {
                                int random = r.nextInt(10);
                                if (random <= 4)
                                    h.setActivity(day, hours, p.go(p.randomPlace()));
                                else if (random == 5)
                                    h.setActivity(day, hours, p.go("Hospital"));
                            }
                            break;
                        case 6:
                        case 0:
                            if (hours >= 9 && hours <= 19) {
                                int random = r.nextInt(10);
                                if (random <= 6)
                                    h.setActivity(day, hours, p.go(p.randomPlace()));
                                else
                                    h.setActivity(day, hours, h.getWorkPlace());

                            }
                            break;
                    }
                    break;
                case "Fisherman":
                case "Farmer":
                    switch (day % 7) {
                        case 1:
                        case 2:
                        case 3:
                        case 4:
                        case 5:
                            if (hours >= 5 && hours <= 10) {
                                h.setActivity(day, hours, h.getWorkPlace());
                            } else if (hours >= 12 && hours <= 18) {
                                int r1 = r.nextInt(20);
                                if (r1 <= 15)
                                    h.setActivity(day, hours, p.go(p.randomPlace()));
                            }
                            break;
                        case 6:
                        case 0:
                            if (hours >= 8 && hours <= 19) {
                                int r1 = r.nextInt(20);
                                if (r1 <= 15)
                                    h.setActivity(day, hours, p.go(p.randomPlace()));
                            }
                            break;
                    }
                    break;
                case "Teacher":
                    switch (day % 7) {
                        case 1:
                        case 2:
                        case 3:
                        case 4:
                        case 5:
                            if (hours >= 7 && hours <= 14)
                                h.setActivity(day, hours, h.getWorkPlace());
                            else if (hours >= 15 && hours <= 19) {
                                int r1 = r.nextInt(15);
                                if (r1 <= 9)
                                    h.setActivity(day, hours, p.go(p.randomPlace()));
                            }
                            break;
                        case 6:
                        case 0:
                            if (hours >= 8 && hours <= 18) {
                                int r1 = r.nextInt(20);
                                if (r1 <= 9)
                                    h.setActivity(day, hours, p.go(p.randomPlace()));
                            }
                            break;
                    }
                    break;
                case "Construction worker":
                case "Veterinarian":
                case "Electrical Technician":
                case "Baker":
                case "Craftman":
                case "Carpenter":
                case "Bank officer":
                case "Engineer":
                case "Factory worker":
                    switch (day % 7) {
                        case 1:
                        case 2:
                        case 3:
                        case 4:
                        case 5:
                            if (hours >= 8 && hours < 18) {
                                if (hours >= 12 && hours < 14) {
                                    int r1 = r.nextInt(10);
                                    if (r1 <= 6) {
                                        h.setActivity(day, hours, p.go("Restaurant"));
                                    }
                                } else
                                    h.setActivity(day, hours, h.getWorkPlace());
                            }
                            break;
                        case 6:
                        case 0:
                            if (hours >= 8 && hours <= 19) {
                                int r1 = r.nextInt(30);
                                if (r1 <= 19)
                                    h.setActivity(day, hours, p.go(p.randomPlace()));
                                else if (r1 == 20)
                                    h.setActivity(day, hours, p.go("Hospital"));
                            }
                            break;
                    }
                    break;
                case "Doctor":
                case "Nurse":
                    if (h.getWorkPlace().contains("Hospital")) {
                        switch (day % 7) {
                            case 1:
                            case 2:
                            case 3:
                            case 4:
                            case 5:
                            case 6:
                            case 0:
                                if (hours >= 6 && hours <= 19) {
                                    if (hours >= 12 && hours < 14) {
                                        int r1 = r.nextInt(10);
                                        if (r1 <= 4) {
                                            h.setActivity(day, hours, p.go("Restaurant"));
                                        }
                                    } else {
                                        int r1 = r.nextInt(50);
                                        if (r1 <= 44)
                                            h.setActivity(day, hours, h.getWorkPlace());
                                        else
                                            h.setActivity(day, hours, p.go(p.randomPlace()));
                                    }
                                }
                                break;
                        }
                    } else {
                        switch (day % 7) {
                            case 1:
                            case 2:
                            case 3:
                            case 4:
                            case 5:
                                if (hours >= 8 && hours <= 17) {
                                    if (hours >= 12 && hours < 14) {
                                        int r1 = r.nextInt(10);
                                        if (r1 <= 6) {
                                            h.setActivity(day, hours, p.go("Restaurant"));
                                        }
                                    } else {
                                        h.setActivity(day, hours, h.getWorkPlace());
                                    }
                                }
                                break;
                            case 6:
                            case 0:
                                if (hours >= 8 && hours <= 15)
                                    h.setActivity(day, hours, h.getWorkPlace());
                                else if (hours > 15 && hours <= 18)
                                    h.setActivity(day, hours, p.go(p.randomPlace()));
                                break;
                        }
                    }
                    break;
                case "Hotel worker":
                case "Security Guard":
                case "Police":
                    switch (day % 7) {
                        case 1:
                        case 2:
                        case 3:
                        case 4:
                        case 5:
                        case 6:
                        case 0:
                            if (hours >= 7 && hours <= 19) {
                                if (hours >= 1 && hours < 15) {
                                    int r1 = r.nextInt(10);
                                    if (r1 <= 6) {
                                        h.setActivity(day, hours, p.go("Restaurant"));
                                    }
                                } else {
                                    int r1 = r.nextInt(50);
                                    if (r1 <= 39)
                                        h.setActivity(day, hours, h.getWorkPlace());
                                    else
                                        h.setActivity(day, hours, p.go(p.randomPlace()));
                                }

                            }
                            break;
                    }
                    break;
                case "Grocer":
                case "Vendor":
                case "Barber":
                    switch (day % 7) {
                        case 1:
                        case 2:
                        case 3:
                        case 4:
                        case 5:
                        case 6:
                        case 0:
                            if (hours >= 7 && hours <= 19) {
                                if (hours >= 13 && hours < 15) {
                                    int r1 = r.nextInt(10);
                                    if (r1 <= 8) {
                                        h.setActivity(day, hours, p.go("Restaurant"));
                                    }
                                } else {
                                    int r1 = r.nextInt(50);
                                    if (r1 <= 34)
                                        h.setActivity(day, hours, h.getWorkPlace());
                                    else
                                        h.setActivity(day, hours, p.go(p.randomPlace()));
                                }
                            }
                            break;
                    }
                    break;
                case "Grab Driver":
                case "Postman":
                    switch (day % 7) {
                        case 1:
                        case 2:
                        case 3:
                        case 4:
                        case 5:
                        case 6:
                        case 0:
                            if (hours >= 7 && hours <= 19) {
                                if (hours == 13)
                                    h.setActivity(day, hours, p.go("Restaurant"));
                                else {
                                    int r1 = r.nextInt(3);
                                    if (r1 == 0 || r1 == 1)
                                        h.setActivity(day, hours, p.go(p.randomPlace()));
                                    else if (r1 == 2)
                                        h.setActivity(day, hours, p.go(p.grab()));
                                }
                            }
                            break;
                    }
                    break;
                case "Cook":
                case "Waiter":
                    switch (day % 7) {
                        case 1:
                        case 2:
                        case 3:
                        case 4:
                        case 5:
                        case 6:
                        case 0:
                            if (hours >= 8 && hours <= 19) {
                                int r1 = r.nextInt(7);
                                if (r1 <= 5) {
                                    h.setActivity(day, hours, h.getWorkPlace());
                                } else if (r1 == 6) {
                                    h.setActivity(day, hours, p.go(p.randomPlace()));
                                }
                            }
                            break;
                    }
                    break;
            }



    }
}
    
    

