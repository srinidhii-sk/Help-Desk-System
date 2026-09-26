package dao;

import model.Agent;
import util.DBConnection;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class AgentDAO {

    // CREATE
    public boolean addAgent(Agent agent) {

        String sql = """
                INSERT INTO agents
                (name, email, department)
                VALUES (?, ?, ?)
                """;

        try (Connection connection = DBConnection.getConnection();
             PreparedStatement statement =
                     connection.prepareStatement(sql)) {

            statement.setString(1, agent.getName());
            statement.setString(2, agent.getEmail());
            statement.setString(3, agent.getDepartment());

            return statement.executeUpdate() > 0;

        } catch (SQLException e) {

            System.out.println(
                    "Error adding agent: " + e.getMessage()
            );

            return false;
        }
    }


    // READ - Get Agent By ID
    public Agent getAgentById(int agentId) {

        String sql =
                "SELECT * FROM agents WHERE agent_id = ?";

        try (Connection connection = DBConnection.getConnection();
             PreparedStatement statement =
                     connection.prepareStatement(sql)) {

            statement.setInt(1, agentId);

            try (ResultSet resultSet =
                         statement.executeQuery()) {

                if (resultSet.next()) {

                    Agent agent = new Agent();

                    agent.setAgentId(
                            resultSet.getInt("agent_id")
                    );

                    agent.setName(
                            resultSet.getString("name")
                    );

                    agent.setEmail(
                            resultSet.getString("email")
                    );

                    agent.setDepartment(
                            resultSet.getString("department")
                    );

                    if (resultSet.getTimestamp("created_at") != null) {

                        agent.setCreatedAt(
                                resultSet
                                        .getTimestamp("created_at")
                                        .toLocalDateTime()
                        );
                    }

                    return agent;
                }
            }

        } catch (SQLException e) {

            System.out.println(
                    "Error fetching agent: "
                            + e.getMessage()
            );
        }

        return null;
    }


    // READ - Get All Agents
    public List<Agent> getAllAgents() {

        List<Agent> agents = new ArrayList<>();

        String sql = "SELECT * FROM agents";

        try (Connection connection = DBConnection.getConnection();
             PreparedStatement statement =
                     connection.prepareStatement(sql);
             ResultSet resultSet =
                     statement.executeQuery()) {

            while (resultSet.next()) {

                Agent agent = new Agent();

                agent.setAgentId(
                        resultSet.getInt("agent_id")
                );

                agent.setName(
                        resultSet.getString("name")
                );

                agent.setEmail(
                        resultSet.getString("email")
                );

                agent.setDepartment(
                        resultSet.getString("department")
                );

                if (resultSet.getTimestamp("created_at") != null) {

                    agent.setCreatedAt(
                            resultSet
                                    .getTimestamp("created_at")
                                    .toLocalDateTime()
                    );
                }

                agents.add(agent);
            }

        } catch (SQLException e) {

            System.out.println(
                    "Error fetching agents: "
                            + e.getMessage()
            );
        }

        return agents;
    }


    // UPDATE
    public boolean updateAgent(Agent agent) {

        String sql = """
                UPDATE agents
                SET name = ?,
                    email = ?,
                    department = ?
                WHERE agent_id = ?
                """;

        try (Connection connection = DBConnection.getConnection();
             PreparedStatement statement =
                     connection.prepareStatement(sql)) {

            statement.setString(1, agent.getName());
            statement.setString(2, agent.getEmail());
            statement.setString(3, agent.getDepartment());
            statement.setInt(4, agent.getAgentId());

            return statement.executeUpdate() > 0;

        } catch (SQLException e) {

            System.out.println(
                    "Error updating agent: "
                            + e.getMessage()
            );

            return false;
        }
    }


    // DELETE
    public boolean deleteAgent(int agentId) {

        String sql =
                "DELETE FROM agents WHERE agent_id = ?";

        try (Connection connection = DBConnection.getConnection();
             PreparedStatement statement =
                     connection.prepareStatement(sql)) {

            statement.setInt(1, agentId);

            return statement.executeUpdate() > 0;

        } catch (SQLException e) {

            System.out.println(
                    "Error deleting agent: "
                            + e.getMessage()
            );

            return false;
        }
    }
}