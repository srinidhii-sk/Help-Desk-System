package controller;

import model.Ticket;
import model.TicketPriority;
import model.TicketStatus;
import service.TicketService;

import java.util.List;

public class TicketController {

    private final TicketService ticketService = new TicketService();

    // ADD TICKET
    public boolean addTicket(Ticket ticket) {

        return ticketService.addTicket(ticket);
    }

    // GET TICKET BY ID
    public Ticket getTicketById(int ticketId) {

        return ticketService.getTicketById(ticketId);
    }

    // GET ALL TICKETS
    public List<Ticket> getAllTickets() {

        return ticketService.getAllTickets();
    }

    // UPDATE TICKET
    public boolean updateTicket(Ticket ticket) {

        return ticketService.updateTicket(ticket);
    }

    // DELETE TICKET
    public boolean deleteTicket(int ticketId) {

        return ticketService.deleteTicket(ticketId);
    }

    // ASSIGN AGENT
    public boolean assignAgent(int ticketId, int agentId) {

        return ticketService.assignAgent(ticketId, agentId);
    }

    // UPDATE STATUS
    public boolean updateStatus(int ticketId, TicketStatus status) {

        return ticketService.updateStatus(ticketId, status);
    }

    // UPDATE PRIORITY
    public boolean updatePriority(int ticketId, TicketPriority priority) {

        return ticketService.updatePriority(ticketId, priority);
    }

    // GET TICKETS BY AGENT
    public List<Ticket> getTicketsByAgent(int agentId) {

        return ticketService.getTicketsByAgent(agentId);
    }

    // GET UNASSIGNED TICKETS
    public List<Ticket> getUnassignedTickets() {

        return ticketService.getUnassignedTickets();
    }
}