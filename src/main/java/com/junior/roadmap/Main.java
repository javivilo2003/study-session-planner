package com.junior.roadmap;

import java.util.Scanner;
import java.util.UUID;

public class Main {

    static void menu(){
        System.out.println("""

                ***************************
                   STUDY SESSION PLANNER
                ***************************

                1. Create study session
                2. List study sessions
                3. Search
                4. Update session
                5. Show total amount of minutes remaining in Planned sessions
                6. Set session status to COMPLETE
                7. Delete a session
                8. Exit

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

                if (subject == null || subject.isBlank()) {
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

                if (goal == null || goal.isBlank() ) {
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

    public static void updateSesh(Scanner sc, SessionService service, SessionRepository repository){
        UUID id;
        System.out.println("\nEnter the id of the Session you would like to update." + repository.findAll().toString());
        System.out.print("\nEnter the ID here: "); 
        try{
            id = UUID.fromString(sc.nextLine());
        } catch (Exception e) {
            System.out.println("That is not a valid UUID format. Going back to the main menu.");
            return;
        }

        Session seshSearch = repository.findById(id);
        
        if (seshSearch == null) {
            System.out.println("This session ID doesn't exist. Going back to the main menu.");
            return;
        }
        System.out.println("""
                What would you like to update?
                1. Subject
                2. Goal
                3. Session Minutes
                4. Status
                """);
        System.out.print("Enter your choice here: ");
        Integer choice;
        try {
            choice = Integer.parseInt(sc.nextLine());
        } catch (Exception e) {
            System.out.println("That is not a valid choice format. Going back to the main menu.");
            return;
        }

        switch (choice) {
            case 1:
                System.out.print("New Subject: ");
                String newSubject = sc.nextLine();
                try {
                    service.updateSubject(id, newSubject);
                } catch (SessionNotFoundException | InvalidSessionException e) {
                    e.printStackTrace();
                    break;
                }
                System.out.println("Session subject updated.");

                break;

            case 2:
                System.out.print("New Goal: ");
                String newGoal = sc.nextLine();
                try {
                    service.updateGoal(id, newGoal);
                } catch (SessionNotFoundException | InvalidSessionException e) {
                    e.printStackTrace();
                    break;
                }
                System.out.println("Session goal updated.");

                break;

            case 3:
                Integer newMin;
                System.out.print("New Session Minutes: ");
                try {
                    newMin = Integer.parseInt(sc.nextLine());
                } catch (Exception e) {
                    System.out.println("That is not a valid number format. Going back to the main menu.");
                    break;
                }
                
                try {
                    service.updateSessionMin(id, newMin);
                } catch (SessionNotFoundException | InvalidSessionException e) {
                    e.printStackTrace();
                    break;
                }
                System.out.println("Session minutes updated.");

                break;

            case 4:
                Integer newStatus;
                System.out.println("""
                        New Status options:
                        1. PLANNED
                        2. COMPLETED
                        3. CANCELLED
                        """);
                System.out.print("Enter you choice from 1-3: ");
                try {
                    newStatus = Integer.parseInt(sc.nextLine());
                } catch (Exception e) {
                    System.out.println("That is not a valid number format. Going back to the main menu.");
                    break;
                }
                

                switch (newStatus) {
                    case 1:
                        try {
                            service.updateStatus(id, Status.PLANNED);
                        } catch (SessionNotFoundException | InvalidSessionException e) {
                            e.printStackTrace();
                            break;
                        }
                        System.out.println("Session status updated.");

                        break;
                
                    case 2:
                        try {
                            service.updateStatus(id, Status.COMPLETED);
                        } catch (SessionNotFoundException | InvalidSessionException e) {
                            e.printStackTrace();
                            break;
                        }
                        System.out.println("Session status updated.");

                        break;

                    case 3: 
                        try {
                            service.updateStatus(id, Status.CANCELLED);
                        } catch (SessionNotFoundException | InvalidSessionException e) {
                            e.printStackTrace();
                            break;
                        }
                        System.out.println("Session status updated.");

                        break;

                    default:
                        System.out.println("That is not an option. Going back to the main menu.");
                        break;
                }
                break;
            
            default:
                System.out.println("That is not an option. Going back to the main menu.");
                break;
        }
    }

    public static void completeSession(Scanner sc, SessionRepository repository, SessionService service){
        UUID id;
        System.out.println("Select the session ID to mark complete: \n" + repository.findAll().toString());
        System.out.print("Enter the ID: ");
        try{
            id = UUID.fromString(sc.nextLine());
        } catch (Exception e) {
            System.out.println("That is not a valid UUID format. Going back to the main menu.");
            return;
        }
        try {
            service.completeSession(id);
            System.out.println("Session completed successfully.");
        } catch (SessionNotFoundException e) {
            e.printStackTrace();
        }
        
    }

    public static void deleteSession(Scanner sc, SessionRepository repository, SessionService service){
        UUID id;
        System.out.println("Select the session ID to delete: \n" + repository.findAll().toString());
        System.out.print("Enter the ID: ");
        try{
            id = UUID.fromString(sc.nextLine());
        } catch (Exception e) {
            System.out.println("That is not a valid UUID format. Going back to the main menu.");
            return;
        }
        try {
            service.deleteSession(id);
            System.out.println("Session deleted successfully.");
        } catch (SessionNotFoundException e) {
            e.printStackTrace();
        }
        
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        SessionRepository repository = new InMemorySessionRepository();
        SessionService service = new SessionService(repository);
        Integer option = -1;

        try {
            repository.save(new Session("Java", "Review constructors", 45));
        } catch (InvalidSessionException e) {
            e.printStackTrace();
        }
        try {
            repository.save(new Session("DSA", "Practice arrays", 30, Status.COMPLETED));
        } catch (InvalidSessionException e) {
            e.printStackTrace();
        }

        do{
            menu();
            try {
                System.out.print("Choose an option 1-8 -> ");
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
                    break;
            
                case 2: 
                    System.out.println("Showing all study sessions...\n");
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
                    break;

                case 3: 
                    Integer searchBy = -1;
                    System.out.println("""
                            
                            What would you like to search for?
                            1. Subject
                            2. Status

                            """);
                            try {
                                System.out.print("Enter your choice: ");
                                searchBy = Integer.parseInt(sc.nextLine());
                            } catch (Exception e) {
                                System.out.println("That is not a valid option.\n");
                                break;
                            }

                    switch (searchBy) {
                        case 1:
                            String searchSubject;
                            System.out.print("\nSearch for subject: ");
                            searchSubject = sc.nextLine();
                            String result = repository.findBySubject(searchSubject).toString();
                            if (result == null || result.equals("[]")) {
                                System.out.println("No study sessions created with subject -> \"" + searchSubject + "\". Create a new session by going back to the main menu and pressing 1.");
                            } else {
                                System.out.println(result);
                            }
                            break;
                    
                        case 2:
                            Integer filterStatus = -1;
                            System.out.println("""

                                    Filter by status:
                                    1. PLANNED
                                    2. COMPLETED
                                    3. CANCELLED

                                    """);
                            try{
                            System.out.print("Enter your choice: ");
                            filterStatus = Integer.parseInt(sc.nextLine());
                            } catch (Exception e) {
                                System.out.println("That is not a valid option.\n");
                                break;
                            }
                            switch (filterStatus) {
                                case 1:
                                    String resultPlanned = repository.findByStatus(Status.PLANNED).toString();
                                    if (resultPlanned == null || resultPlanned.equals("[]")) {
                                        System.out.println("No study sessions created with status -> \"" + resultPlanned + "\". Create a new session by going back to the main menu and pressing 1.");
                                    } else {
                                        System.out.println(resultPlanned);
                                    }
                                    break;
                            
                                case 2:
                                    String resultCompleted = repository.findByStatus(Status.COMPLETED).toString();
                                    if (resultCompleted == null || resultCompleted.equals("[]")) {
                                        System.out.println("No study sessions created with status -> \"" + resultCompleted + "\". Create a new session by going back to the main menu and pressing 1.");
                                    } else {
                                        System.out.println(resultCompleted);
                                    }
                                    break;


                                case 3:
                                    String resultCancelled = repository.findByStatus(Status.CANCELLED).toString();
                                    if (resultCancelled == null || resultCancelled.equals("[]")) {
                                        System.out.println("No study sessions created with status -> \"" + resultCancelled + "\". Create a new session by going back to the main menu and pressing 1.");
                                    } else {
                                        System.out.println(resultCancelled);
                                    }
                                    break;

                                default:
                                    System.out.println("That is not a valid option. Going back to the main menu.");
                                    break;
                            }
                            break;

                        default:
                            System.out.println("That is not a valid option. Going back to the main menu.");
                            break;
                    }
                    
                    
                    break;

                case 4:                 
                    updateSesh(sc, service, repository);
                    break;

                case 5: 
                    System.out.println("Total minutes remaining in PLANNED sessions: " + repository.showTotalMin());
                    break;

                case 6:
                    completeSession(sc, repository, service);
                    break;

                case 7:
                    deleteSession(sc, repository, service);
                    break;

                case 8: 
                    System.out.println("Have a good day!!!");
                    break;

                default:
                    System.out.println("Option must be a number from 1-8. Please try again.\n");
                    break;
            }
        } while (option != 8);

        sc.close();
    }
}
