package com.junior.roadmap;

import java.util.ArrayList;
import java.util.Scanner;

public class Main {

    static void menu(){
        System.out.println("""

                ***************************
                   STUDY SESSION PLANNER
                ***************************

                1. Create study session
                2. List study sessions
                3. Exit

                """);
    }

    public static Session createSesh(Scanner sc){
        Session sesh;
        String subject, goal;
        Integer sessionMin;

        System.out.print("\n\nSubject: ");
        subject = sc.nextLine();

        System.out.print("\n\nGoal: ");
        goal = sc.nextLine();

        System.out.print("\n\nPlanned Minutes: ");
        sessionMin = Integer.parseInt(sc.nextLine());

        sesh = new Session(subject, goal, sessionMin);

        return sesh;
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        ArrayList<Session> sessionList = new ArrayList<>(); 
        Integer option = -1;

        do{
            menu();
            try {
                System.out.print("Choose an option 1-3 -> ");
                option = Integer.parseInt(sc.nextLine());
            } catch (Exception e) {
                System.out.println("That is not a valid option. Please try again.\n");
            }

            switch (option) {
                case 1:
                    Session sesh = createSesh(sc);
                    if (sesh.getClass() == Session.class) {
                        sessionList.add(sesh);
                        System.out.println("\nSession: " + sesh.toString());
                        System.out.println("\nSession created\n");
                    } else {
                        System.out.println("Session could not be created.\n");
                    }
                    
                    sc.reset();
                    break;
            
                case 2: 
                    System.out.println("Showing past study sessions...\n");
                    if (sessionList.size() >=1) {
                        int i = 0;
                        for (Session session : sessionList) {
                            i++;
                            System.out.println("\nSession " + i + ": " + session);
                        }

                        System.out.println("That is all.\n");
                    }else{
                        System.out.println("There are no saved sessions. Add at least 1 and come back.\n");
                    }

                    sc.reset();
                    break;

                case 3: 
                System.out.println("Have a good day!!!");
                    break;

                default:
                    System.out.println("Option must be a number from 1-3. Please try again.\n");

                    sc.reset();
                    break;
            }
        } while (option != 3);

        sc.close();
    }
}
