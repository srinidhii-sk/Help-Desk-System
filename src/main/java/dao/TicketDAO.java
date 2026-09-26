package dao;

import model.Ticket;
import model.TicketPriority;
import model.TicketStatus;
import util.DBConnection;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class TicketDAO {

    // CREATE - Add Ticket
    public boolean addTicket(Ticket ticket) {

        String sql = """
                INSERT INTO tickets
                (title, description, priority, status, user_id, agent_id)
                VALUES (?, ?, ?, ?, ?, ?)
                """;

        try (Connection connection = DBConnection.getConnection();
             PreparedStatement statement =
                     connection.prepareStatement(sql)) {

            statement.setString(1, ticket.getTitle());
            statement.setString(2, ticket.getDescription());
            statement.setString(3, ticket.getPriority().name());
            statement.setString(4, ticket.getStatus().name());
            statement.setInt(5, ticket.getUserId());

            // agentId can be NULL
            if (ticket.getAgentId() == null) {
                statement.setNull(6, Types.INTEGER);
            } else {
                statement.setInt(6, ticket.getAgentId());
            }

            return statement.executeUpdate() > 0;

        } catch (SQLException e) {

            System.out.println(
                    "Error adding ticket: " + e.getMessage()
            );

            return false;
        }
    }


    // READ - Get Ticket By ID
    public Ticket getTicketById(int ticketId) {

        String sql =
                "SELECT * FROM tickets WHERE ticket_id = ?";

        try (Connection connection = DBConnection.getConnection();
             PreparedStatement statement =
                     connection.prepareStatement(sql)) {

            statement.setInt(1, ticketId);

            try (ResultSet resultSet =
                         statement.executeQuery()) {

                if (resultSet.next()) {

                    return mapResultSetToTicket(resultSet);
                }
            }

        } catch (SQLException e) {

            System.out.println(
                    "Error fetching ticket: " + e.getMessage()
            );
        }

        return null;
    }


    // READ - Get All Tickets
    public List<Ticket> getAllTickets() {

        List<Ticket> tickets = new ArrayList<>();

        String sql = "SELECT * FROM tickets";

        try (Connection connection = DBConnection.getConnection();
             PreparedStatement statement =
                     connection.prepareStatement(sql);
             ResultSet resultSet =
                     statement.executeQuery()) {

            while (resultSet.next()) {

                tickets.add(
                        mapResultSetToTicket(resultSet)
                );
            }

        } catch (SQLException e) {

            System.out.println(
                    "Error fetching tickets: " + e.getMessage()
            );
        }

        return tickets;
    }


    // UPDATE - Update Ticket
    public boolean updateTicket(Ticket ticket) {

        String sql = """
                UPDATE tickets
                SET title = ?,
                    description = ?,
                    priority = ?,
                    status = ?,
                    agent_id = ?
                WHERE ticket_id = ?
                """;

        try (Connection connection = DBConnection.getConnection();
             PreparedStatement statement =
                     connection.prepareStatement(sql)) {

            statement.setString(1, ticket.getTitle());
            statement.setString(2, ticket.getDescription());
            statement.setString(3, ticket.getPriority().name());
            statement.setString(4, ticket.getStatus().name());

            // agentId can be NULL
            if (ticket.getAgentId() == null) {

                statement.setNull(5, Types.INTEGER);

            } else {

                statement.setInt(
                        5,
                        ticket.getAgentId()
                );
            }

            statement.setInt(
                    6,
                    ticket.getTicketId()
            );

            return statement.executeUpdate() > 0;

        } catch (SQLException e) {

            System.out.println(
                    "Error updating ticket: " + e.getMessage()
            );

            return false;
        }
    }


    // DELETE - Delete Ticket
    public boolean deleteTicket(int ticketId) {

        String sql =
                "DELETE FROM tickets WHERE ticket_id = ?";

        try (Connection connection = DBConnection.getConnection();
             PreparedStatement statement =
                     connection.prepareStatement(sql)) {

            statement.setInt(1, ticketId);

            return statement.executeUpdate() > 0;

        } catch (SQLException e) {

            System.out.println(
                    "Error deleting ticket: " + e.getMessage()
            );

            return false;
        }
    }


    // ASSIGN AGENT
    public boolean assignAgent(int ticketId, int agentId) {

        String sql = """
                UPDATE tickets
                SET agent_id = ?
                WHERE ticket_id = ?
                """;

        try (Connection connection = DBConnection.getConnection();
             PreparedStatement statement =
                     connection.prepareStatement(sql)) {

            statement.setInt(1, agentId);
            statement.setInt(2, ticketId);

            return statement.executeUpdate() > 0;

        } catch (SQLException e) {

            System.out.println(
                    "Error assigning agent: " + e.getMessage()
            );

            return false;
        }
    }


    // UPDATE STATUS
    public boolean updateStatus(
            int ticketId,
            TicketStatus status) {

        String sql = """
                UPDATE tickets
                SET status = ?
                WHERE ticket_id = ?
                """;

        try (Connection connection = DBConnection.getConnection();
             PreparedStatement statement =
                     connection.prepareStatement(sql)) {

            statement.setString(1, status.name());
            statement.setInt(2, ticketId);

            return statement.executeUpdate() > 0;

        } catch (SQLException e) {

            System.out.println(
                    "Error updating status: "
                            + e.getMessage()
            );

            return false;
        }
    }


    // UPDATE PRIORITY
    public boolean updatePriority(
            int ticketId,
            TicketPriority priority) {

        String sql = """
                UPDATE tickets
                SET priority = ?
                WHERE ticket_id = ?
                """;

        try (Connection connection = DBConnection.getConnection();
             PreparedStatement statement =
                     connection.prepareStatement(sql)) {

            statement.setString(1, priority.name());
            statement.setInt(2, ticketId);

            return statement.executeUpdate() > 0;

        } catch (SQLException e) {

            System.out.println(
                    "Error updating priority: "
                            + e.getMessage()
            );

            return false;
        }
    }


    // GET TICKETS BY AGENT
    public List<Ticket> getTicketsByAgent(int agentId) {

        List<Ticket> tickets = new ArrayList<>();

        String sql =
                "SELECT * FROM tickets WHERE agent_id = ?";

        try (Connection connection = DBConnection.getConnection();
             PreparedStatement statement =
                     connection.prepareStatement(sql)) {

            statement.setInt(1, agentId);

            try (ResultSet resultSet =
                         statement.executeQuery()) {

                while (resultSet.next()) {

                    tickets.add(
                            mapResultSetToTicket(resultSet)
                    );
                }
            }

        } catch (SQLException e) {

            System.out.println(
                    "Error fetching tickets by agent: "
                            + e.getMessage()
            );
        }

        return tickets;
    }


    // GET UNASSIGNED TICKETS
    public List<Ticket> getUnassignedTickets() {

        List<Ticket> tickets = new ArrayList<>();

        String sql =
                "SELECT * FROM tickets WHERE agent_id IS NULL";

        try (Connection connection = DBConnection.getConnection();
             PreparedStatement statement =
                     connection.prepareStatement(sql);
             ResultSet resultSet =
                     statement.executeQuery()) {

            while (resultSet.next()) {

                tickets.add(
                        mapResultSetToTicket(resultSet)
                );
            }

        } catch (SQLException e) {

            System.out.println(
                    "Error fetching unassigned tickets: "
                            + e.getMessage()
            );
        }

        return tickets;
    }


    // HELPER METHOD
    // Converts one database row into a Ticket object
    private Ticket mapResultSetToTicket(
            ResultSet resultSet) throws SQLException {

        Ticket ticket = new Ticket();

        ticket.setTicketId(
                resultSet.getInt("ticket_id")
        );

        ticket.setTitle(
                resultSet.getString("title")
        );

        ticket.setDescription(
                resultSet.getString("description")
        );

        ticket.setPriority(
                TicketPriority.valueOf(
                        resultSet.getString("priority")
                )
        );

        ticket.setStatus(
                TicketStatus.valueOf(
                        resultSet.getString("status")
                )
        );

        ticket.setUserId(
                resultSet.getInt("user_id")
        );


        // agent_id can be NULL
        int agentId =
                resultSet.getInt("agent_id");

        if (resultSet.wasNull()) {

            ticket.setAgentId(null);

        } else {

            ticket.setAgentId(agentId);
        }


        // created_at
        if (resultSet.getTimestamp("created_at") != null) {

            ticket.setCreatedAt(
                    resultSet
                            .getTimestamp("created_at")
                            .toLocalDateTime()
            );
        }


        // updated_at
        if (resultSet.getTimestamp("updated_at") != null) {

            ticket.setUpdatedAt(
                    resultSet
                            .getTimestamp("updated_at")
                            .toLocalDateTime()
            );
        }

        return ticket;
    }
}