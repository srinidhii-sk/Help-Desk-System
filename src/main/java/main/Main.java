package main;
import controller.AgentController;
import controller.TicketController;
import controller.UserController;
import model.Agent;
import model.Ticket;
import model.TicketPriority;
import model.TicketStatus;
import model.User;

import java.util.List;
import java.util.Scanner;

public class Main {

    private static final Scanner scanner = new Scanner(System.in);

    private static final UserController userController =
            new UserController();

    private static final AgentController agentController =
            new AgentController();

    private static final TicketController ticketController =
            new TicketController();

    public static void main(String[] args) {

        while (true) {

            System.out.println("\n=================================");
            System.out.println("       HELP DESK SYSTEM");
            System.out.println("=================================");
            System.out.println("1. User Management");
            System.out.println("2. Agent Management");
            System.out.println("3. Ticket Management");
            System.out.println("4. Exit");
            System.out.println("=================================");

            System.out.print("Enter your choice: ");

            int choice = readInt();

            switch (choice) {

                case 1:
                    userMenu();
                    break;

                case 2:
                    agentMenu();
                    break;

                case 3:
                    ticketMenu();
                    break;

                case 4:
                    System.out.println(
                            "Thank you for using Help Desk System!"
                    );
                    scanner.close();
                    return;

                default:
                    System.out.println(
                            "Invalid choice. Please try again."
                    );
            }
        }
    }

    // =====================================================
    // USER MENU
    // =====================================================

    private static void userMenu() {

        while (true) {

            System.out.println("\n=================================");
            System.out.println("        USER MANAGEMENT");
            System.out.println("=================================");
            System.out.println("1. Add User");
            System.out.println("2. View User");
            System.out.println("3. View All Users");
            System.out.println("4. Update User");
            System.out.println("5. Delete User");
            System.out.println("6. Back");
            System.out.println("=================================");

            System.out.print("Enter your choice: ");

            int choice = readInt();

            switch (choice) {

                case 1:
                    addUser();
                    break;

                case 2:
                    viewUser();
                    break;

                case 3:
                    viewAllUsers();
                    break;

                case 4:
                    updateUser();
                    break;

                case 5:
                    deleteUser();
                    break;

                case 6:
                    return;

                default:
                    System.out.println("Invalid choice.");
            }
        }
    }

    // =====================================================
    // USER OPERATIONS
    // =====================================================

    private static void addUser() {

        scanner.nextLine();

        System.out.println("\n--- ADD USER ---");

        System.out.print("Name: ");
        String name = scanner.nextLine();

        System.out.print("Email: ");
        String email = scanner.nextLine();

        System.out.print("Department: ");
        String department = scanner.nextLine();

        User user = new User();

        user.setName(name);
        user.setEmail(email);
        user.setDepartment(department);

        boolean result = userController.addUser(user);

        if (result) {
            System.out.println("User added successfully!");
        } else {
            System.out.println("Failed to add user.");
        }
    }

    private static void viewUser() {

        System.out.println("\n--- VIEW USER ---");

        System.out.print("Enter User ID: ");
        int userId = readInt();

        User user = userController.getUserById(userId);

        if (user == null) {
            System.out.println("User not found.");
        } else {
            displayUser(user);
        }
    }

    private static void viewAllUsers() {

        System.out.println("\n--- ALL USERS ---");

        List<User> users = userController.getAllUsers();

        if (users.isEmpty()) {
            System.out.println("No users found.");
            return;
        }

        for (User user : users) {
            displayUser(user);
            System.out.println("---------------------------------");
        }
    }

    private static void updateUser() {

        System.out.println("\n--- UPDATE USER ---");

        System.out.print("Enter User ID: ");
        int userId = readInt();

        User user = userController.getUserById(userId);

        if (user == null) {
            System.out.println("User not found.");
            return;
        }

        scanner.nextLine();

        System.out.print("New Name: ");
        user.setName(scanner.nextLine());

        System.out.print("New Email: ");
        user.setEmail(scanner.nextLine());

        System.out.print("New Department: ");
        user.setDepartment(scanner.nextLine());

        boolean result = userController.updateUser(user);

        if (result) {
            System.out.println("User updated successfully!");
        } else {
            System.out.println("Failed to update user.");
        }
    }

    private static void deleteUser() {

        System.out.println("\n--- DELETE USER ---");

        System.out.print("Enter User ID: ");
        int userId = readInt();

        boolean result = userController.deleteUser(userId);

        if (result) {
            System.out.println("User deleted successfully!");
        } else {
            System.out.println("Failed to delete user.");
        }
    }

    private static void displayUser(User user) {

        System.out.println("User ID    : " + user.getUserId());
        System.out.println("Name       : " + user.getName());
        System.out.println("Email      : " + user.getEmail());
        System.out.println("Department : " + user.getDepartment());
        System.out.println("Created At : " + user.getCreatedAt());
    }

    // =====================================================
    // AGENT MENU
    // =====================================================

    private static void agentMenu() {

        while (true) {

            System.out.println("\n=================================");
            System.out.println("        AGENT MANAGEMENT");
            System.out.println("=================================");
            System.out.println("1. Add Agent");
            System.out.println("2. View Agent");
            System.out.println("3. View All Agents");
            System.out.println("4. Update Agent");
            System.out.println("5. Delete Agent");
            System.out.println("6. Back");
            System.out.println("=================================");

            System.out.print("Enter your choice: ");

            int choice = readInt();

            switch (choice) {

                case 1:
                    addAgent();
                    break;

                case 2:
                    viewAgent();
                    break;

                case 3:
                    viewAllAgents();
                    break;

                case 4:
                    updateAgent();
                    break;

                case 5:
                    deleteAgent();
                    break;

                case 6:
                    return;

                default:
                    System.out.println("Invalid choice.");
            }
        }
    }

    // =====================================================
    // AGENT OPERATIONS
    // =====================================================

    private static void addAgent() {

        scanner.nextLine();

        System.out.println("\n--- ADD AGENT ---");

        System.out.print("Name: ");
        String name = scanner.nextLine();

        System.out.print("Email: ");
        String email = scanner.nextLine();

        System.out.print("Department: ");
        String department = scanner.nextLine();

        Agent agent = new Agent();

        agent.setName(name);
        agent.setEmail(email);
        agent.setDepartment(department);

        boolean result = agentController.addAgent(agent);

        if (result) {
            System.out.println("Agent added successfully!");
        } else {
            System.out.println("Failed to add agent.");
        }
    }

    private static void viewAgent() {

        System.out.println("\n--- VIEW AGENT ---");

        System.out.print("Enter Agent ID: ");
        int agentId = readInt();

        Agent agent = agentController.getAgentById(agentId);

        if (agent == null) {
            System.out.println("Agent not found.");
        } else {
            displayAgent(agent);
        }
    }

    private static void viewAllAgents() {

        System.out.println("\n--- ALL AGENTS ---");

        List<Agent> agents = agentController.getAllAgents();

        if (agents.isEmpty()) {
            System.out.println("No agents found.");
            return;
        }

        for (Agent agent : agents) {
            displayAgent(agent);
            System.out.println("---------------------------------");
        }
    }

    private static void updateAgent() {

        System.out.println("\n--- UPDATE AGENT ---");

        System.out.print("Enter Agent ID: ");
        int agentId = readInt();

        Agent agent = agentController.getAgentById(agentId);

        if (agent == null) {
            System.out.println("Agent not found.");
            return;
        }

        scanner.nextLine();

        System.out.print("New Name: ");
        agent.setName(scanner.nextLine());

        System.out.print("New Email: ");
        agent.setEmail(scanner.nextLine());

        System.out.print("New Department: ");
        agent.setDepartment(scanner.nextLine());

        boolean result = agentController.updateAgent(agent);

        if (result) {
            System.out.println("Agent updated successfully!");
        } else {
            System.out.println("Failed to update agent.");
        }
    }

    private static void deleteAgent() {

        System.out.println("\n--- DELETE AGENT ---");

        System.out.print("Enter Agent ID: ");
        int agentId = readInt();

        boolean result = agentController.deleteAgent(agentId);

        if (result) {
            System.out.println("Agent deleted successfully!");
        } else {
            System.out.println("Failed to delete agent.");
        }
    }

    private static void displayAgent(Agent agent) {

        System.out.println("Agent ID   : " + agent.getAgentId());
        System.out.println("Name       : " + agent.getName());
        System.out.println("Email      : " + agent.getEmail());
        System.out.println("Department : " + agent.getDepartment());
        System.out.println("Created At : " + agent.getCreatedAt());
    }

    // =====================================================
    // TICKET MENU
    // =====================================================

    private static void ticketMenu() {

        while (true) {

            System.out.println("\n=================================");
            System.out.println("        TICKET MANAGEMENT");
            System.out.println("=================================");
            System.out.println("1. Create Ticket");
            System.out.println("2. View Ticket");
            System.out.println("3. View All Tickets");
            System.out.println("4. Update Ticket");
            System.out.println("5. Delete Ticket");
            System.out.println("6. Assign Agent");
            System.out.println("7. Update Status");
            System.out.println("8. Update Priority");
            System.out.println("9. View Agent Tickets");
            System.out.println("10. View Unassigned Tickets");
            System.out.println("11. Back");
            System.out.println("=================================");

            System.out.print("Enter your choice: ");

            int choice = readInt();

            switch (choice) {

                case 1:
                    addTicket();
                    break;

                case 2:
                    viewTicket();
                    break;

                case 3:
                    viewAllTickets();
                    break;

                case 4:
                    updateTicket();
                    break;

                case 5:
                    deleteTicket();
                    break;

                case 6:
                    assignAgent();
                    break;

                case 7:
                    updateStatus();
                    break;

                case 8:
                    updatePriority();
                    break;

                case 9:
                    viewAgentTickets();
                    break;

                case 10:
                    viewUnassignedTickets();
                    break;

                case 11:
                    return;

                default:
                    System.out.println("Invalid choice.");
            }
        }
    }

    // =====================================================
    // TICKET OPERATIONS
    // =====================================================

    private static void addTicket() {

        scanner.nextLine();

        System.out.println("\n--- CREATE TICKET ---");

        System.out.print("Title: ");
        String title = scanner.nextLine();

        System.out.print("Description: ");
        String description = scanner.nextLine();

        TicketPriority priority = readPriority();

        System.out.print("User ID: ");
        int userId = readInt();

        Ticket ticket = new Ticket();

        ticket.setTitle(title);
        ticket.setDescription(description);
        ticket.setPriority(priority);
        ticket.setUserId(userId);

        // Status will automatically become OPEN
        // inside TicketService.

        boolean result = ticketController.addTicket(ticket);

        if (result) {
            System.out.println("Ticket created successfully!");
        } else {
            System.out.println("Failed to create ticket.");
        }
    }

    private static void viewTicket() {

        System.out.println("\n--- VIEW TICKET ---");

        System.out.print("Enter Ticket ID: ");
        int ticketId = readInt();

        Ticket ticket = ticketController.getTicketById(ticketId);

        if (ticket == null) {
            System.out.println("Ticket not found.");
        } else {
            displayTicket(ticket);
        }
    }

    private static void viewAllTickets() {

        System.out.println("\n--- ALL TICKETS ---");

        List<Ticket> tickets = ticketController.getAllTickets();

        if (tickets.isEmpty()) {
            System.out.println("No tickets found.");
            return;
        }

        for (Ticket ticket : tickets) {
            displayTicket(ticket);
            System.out.println("---------------------------------");
        }
    }

    private static void updateTicket() {

        System.out.println("\n--- UPDATE TICKET ---");

        System.out.print("Enter Ticket ID: ");
        int ticketId = readInt();

        Ticket ticket = ticketController.getTicketById(ticketId);

        if (ticket == null) {
            System.out.println("Ticket not found.");
            return;
        }

        scanner.nextLine();

        System.out.print("New Title: ");
        ticket.setTitle(scanner.nextLine());

        System.out.print("New Description: ");
        ticket.setDescription(scanner.nextLine());

        ticket.setPriority(readPriority());
        ticket.setStatus(readStatus());

        System.out.print("Agent ID (0 for unassigned): ");
        int agentId = readInt();

        if (agentId == 0) {
            ticket.setAgentId(null);
        } else {
            ticket.setAgentId(agentId);
        }

        boolean result = ticketController.updateTicket(ticket);

        if (result) {
            System.out.println("Ticket updated successfully!");
        } else {
            System.out.println("Failed to update ticket.");
        }
    }

    private static void deleteTicket() {

        System.out.println("\n--- DELETE TICKET ---");

        System.out.print("Enter Ticket ID: ");
        int ticketId = readInt();

        boolean result = ticketController.deleteTicket(ticketId);

        if (result) {
            System.out.println("Ticket deleted successfully!");
        } else {
            System.out.println("Failed to delete ticket.");
        }
    }

    private static void assignAgent() {

        System.out.println("\n--- ASSIGN AGENT ---");

        System.out.print("Ticket ID: ");
        int ticketId = readInt();

        System.out.print("Agent ID: ");
        int agentId = readInt();

        boolean result =
                ticketController.assignAgent(ticketId, agentId);

        if (result) {
            System.out.println("Agent assigned successfully!");
        } else {
            System.out.println("Failed to assign agent.");
        }
    }

    private static void updateStatus() {

        System.out.println("\n--- UPDATE STATUS ---");

        System.out.print("Ticket ID: ");
        int ticketId = readInt();

        TicketStatus status = readStatus();

        boolean result =
                ticketController.updateStatus(ticketId, status);

        if (result) {
            System.out.println("Status updated successfully!");
        } else {
            System.out.println("Failed to update status.");
        }
    }

    private static void updatePriority() {

        System.out.println("\n--- UPDATE PRIORITY ---");

        System.out.print("Ticket ID: ");
        int ticketId = readInt();

        TicketPriority priority = readPriority();

        boolean result =
                ticketController.updatePriority(ticketId, priority);

        if (result) {
            System.out.println("Priority updated successfully!");
        } else {
            System.out.println("Failed to update priority.");
        }
    }

    private static void viewAgentTickets() {

        System.out.println("\n--- AGENT TICKETS ---");

        System.out.print("Agent ID: ");
        int agentId = readInt();

        List<Ticket> tickets =
                ticketController.getTicketsByAgent(agentId);

        if (tickets.isEmpty()) {
            System.out.println("No tickets assigned to this agent.");
            return;
        }

        for (Ticket ticket : tickets) {
            displayTicket(ticket);
            System.out.println("---------------------------------");
        }
    }

    private static void viewUnassignedTickets() {

        System.out.println("\n--- UNASSIGNED TICKETS ---");

        List<Ticket> tickets =
                ticketController.getUnassignedTickets();

        if (tickets.isEmpty()) {
            System.out.println("No unassigned tickets.");
            return;
        }

        for (Ticket ticket : tickets) {
            displayTicket(ticket);
            System.out.println("---------------------------------");
        }
    }

    private static void displayTicket(Ticket ticket) {

        System.out.println("Ticket ID   : " + ticket.getTicketId());
        System.out.println("Title       : " + ticket.getTitle());
        System.out.println("Description : " + ticket.getDescription());
        System.out.println("Priority    : " + ticket.getPriority());
        System.out.println("Status      : " + ticket.getStatus());
        System.out.println("User ID     : " + ticket.getUserId());
        System.out.println("Agent ID    : " + ticket.getAgentId());
        System.out.println("Created At  : " + ticket.getCreatedAt());
        System.out.println("Updated At  : " + ticket.getUpdatedAt());
    }

    // =====================================================
    // INPUT HELPERS
    // =====================================================

    private static TicketPriority readPriority() {

        while (true) {

            System.out.println("\nSelect Priority:");
            System.out.println("1. LOW");
            System.out.println("2. MEDIUM");
            System.out.println("3. HIGH");
            System.out.println("4. CRITICAL");

            System.out.print("Enter choice: ");

            int choice = readInt();

            switch (choice) {

                case 1:
                    return TicketPriority.LOW;

                case 2:
                    return TicketPriority.MEDIUM;

                case 3:
                    return TicketPriority.HIGH;

                case 4:
                    return TicketPriority.CRITICAL;

                default:
                    System.out.println(
                            "Invalid priority. Try again."
                    );
            }
        }
    }

    private static TicketStatus readStatus() {

        while (true) {

            System.out.println("\nSelect Status:");
            System.out.println("1. OPEN");
            System.out.println("2. IN_PROGRESS");
            System.out.println("3. RESOLVED");
            System.out.println("4. CLOSED");

            System.out.print("Enter choice: ");

            int choice = readInt();

            switch (choice) {

                case 1:
                    return TicketStatus.OPEN;

                case 2:
                    return TicketStatus.IN_PROGRESS;

                case 3:
                    return TicketStatus.RESOLVED;

                case 4:
                    return TicketStatus.CLOSED;

                default:
                    System.out.println(
                            "Invalid status. Try again."
                    );
            }
        }
    }

    private static int readInt() {

        while (!scanner.hasNextInt()) {

            System.out.print("Please enter a valid number: ");
            scanner.next();
        }

        return scanner.nextInt();
    }
}