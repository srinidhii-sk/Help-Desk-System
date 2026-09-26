package service;

import dao.TicketDAO;
import model.Ticket;
import model.TicketPriority;
import model.TicketStatus;

import java.util.List;

public class TicketService {

    private final TicketDAO ticketDAO = new TicketDAO();

    // ADD TICKET
    public boolean addTicket(Ticket ticket) {

        if (ticket == null) {
            return false;
        }

        if (ticket.getTitle() == null || ticket.getTitle().isBlank()) {
            return false;
        }

        if (ticket.getDescription() == null
                || ticket.getDescription().isBlank()) {
            return false;
        }

        if (ticket.getPriority() == null) {
            return false;
        }

        if (ticket.getUserId() <= 0) {
            return false;
        }

        // New tickets should start as OPEN
        if (ticket.getStatus() == null) {
            ticket.setStatus(TicketStatus.OPEN);
        }

        return ticketDAO.addTicket(ticket);
    }

    // GET TICKET BY ID
    public Ticket getTicketById(int ticketId) {

        if (ticketId <= 0) {
            return null;
        }

        return ticketDAO.getTicketById(ticketId);
    }

    // GET ALL TICKETS
    public List<Ticket> getAllTickets() {

        return ticketDAO.getAllTickets();
    }

    // UPDATE TICKET
    public boolean updateTicket(Ticket ticket) {

        if (ticket == null || ticket.getTicketId() <= 0) {
            return false;
        }

        if (ticket.getTitle() == null || ticket.getTitle().isBlank()) {
            return false;
        }

        if (ticket.getDescription() == null
                || ticket.getDescription().isBlank()) {
            return false;
        }

        if (ticket.getPriority() == null) {
            return false;
        }

        if (ticket.getStatus() == null) {
            return false;
        }

        if (ticket.getUserId() <= 0) {
            return false;
        }

        return ticketDAO.updateTicket(ticket);
    }

    // DELETE TICKET
    public boolean deleteTicket(int ticketId) {

        if (ticketId <= 0) {
            return false;
        }

        return ticketDAO.deleteTicket(ticketId);
    }

    // ASSIGN AGENT
    public boolean assignAgent(int ticketId, int agentId) {

        if (ticketId <= 0 || agentId <= 0) {
            return false;
        }

        return ticketDAO.assignAgent(ticketId, agentId);
    }

    // UPDATE STATUS
    public boolean updateStatus(int ticketId, TicketStatus status) {

        if (ticketId <= 0 || status == null) {
            return false;
        }

        return ticketDAO.updateStatus(ticketId, status);
    }

    // UPDATE PRIORITY
    public boolean updatePriority(int ticketId, TicketPriority priority) {

        if (ticketId <= 0 || priority == null) {
            return false;
        }

        return ticketDAO.updatePriority(ticketId, priority);
    }

    // GET TICKETS BY AGENT
    public List<Ticket> getTicketsByAgent(int agentId) {

        if (agentId <= 0) {
            return List.of();
        }

        return ticketDAO.getTicketsByAgent(agentId);
    }

    // GET UNASSIGNED TICKETS
    public List<Ticket> getUnassignedTickets() {

        return ticketDAO.getUnassignedTickets();
    }
}