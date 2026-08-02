package com.junior.roadmap;

import java.util.Scanner;

public class Main {

    static void menu(){
        System.out.println("""

                ***************************
                   STUDY SESSION PLANNER
                ***************************

                1. Create study session
                2. List study sessions
                3. Search by subject
                4. Exit

                """);
    }

    public static Session createSesh(Scanner sc){
        Session sesh = null;
        String subject = "", goal = "";
        Integer sessionMin = 0;

        do{
            try {
                System.out.print("\n\nSubject: ");
                subject = sc.nextLine();

                if (subject.isBlank() || subject == null) {
                    throw new InvalidSessionException();
                }

            } catch (InvalidSessionException e) {
                System.out.println("Subject cannot be empty. Try again.");
            }
            
        } while (subject == null || subject.isBlank());
        
        do{

            try {
                System.out.print("\n\nGoal: ");
                goal = sc.nextLine();

                if (goal.isBlank() || goal == null) {
                    throw new InvalidSessionException();
                }
            } catch (Exception e) {
                System.out.println("Goal cannot be empty. Try again.");
            }
            

        } while (goal == null || goal.isBlank());

        do {
            
            try {
                System.out.print("\n\nPlanned Minutes: ");
                sessionMin = Integer.parseInt(sc.nextLine());
            
                if (sessionMin == null || sessionMin <= 0) {
                    throw new InvalidSessionException("The session's minutes cannot be equal or less than 0. Try again.");
                } 

                if (sessionMin > 480) {
                    throw new InvalidSessionException("Session exceeded the maximum length for a session 480.");
                }

            } catch (InvalidSessionException e) {
               System.out.println(e.getMessage());
            } catch (NumberFormatException e) {
                    System.out.println("Planned minutes must be a number.");
            }
        
        } while (sessionMin <= 0 || sessionMin == 0 || sessionMin > 480);


        try {
            sesh = new Session(subject, goal, sessionMin);
        } catch (InvalidSessionException e) {
            e.printStackTrace();
        }

        return sesh;
            
    }

    public static List<Session> findBySubject(List<Session> sessions, String input){
        List <Session> matches = new ArrayList<>();

        for (Session session : sessions) {
            if (session.getSubject().equalsIgnoreCase(input)) {
                matches.add(session);
            }
        }

        return matches;
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        SessionRepository repository = new InMemorySessionRepository();
        Integer option = -1;

        try {
            repository.save(new Session("Java", "Review constructors", 45));
        } catch (InvalidSessionException e) {

            e.printStackTrace();
        }
        try {
            repository.save(new Session("DSA", "Practice arrays", 30));
        } catch (InvalidSessionException e) {
            e.printStackTrace();
        }

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
                        repository.save(sesh);
                        System.out.println("\nSession: " + sesh.toString());
                        System.out.println("\nSession created\n");
                    } else {
                        System.out.println("Session could not be created.\n");
                    }
                    
                    sc.reset();
                    break;
            
                case 2: 
                    System.out.println("Showing past study sessions...\n");
                    if (repository.findAll().size() >=1) {
                        int i = 0;
                        for (Session session : repository.findAll()) {
                            i++;
                            System.out.println("\nSession " + i + ": " + session);
                        }

                        System.out.println("\nThat is all.\n");
                    }else{
                        System.out.println("There are no saved sessions. Add at least 1 and come back.\n");
                    }

                    sc.reset();
                    break;

                case 3: 
                    String search;
                    System.out.print("Search for subject: ");
                    search = sc.nextLine();
                    System.out.println(findBySubject(sessionList, search).toString());
                    break;

                case 4: 
                    System.out.println("Have a good day!!!");
                    break;

                default:
                    System.out.println("Option must be a number from 1-4. Please try again.\n");

                    sc.reset();
                    break;
            }
        } while (option != 4);

        sc.close();
    }
}
