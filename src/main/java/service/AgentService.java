package service;

import dao.AgentDAO;
import model.Agent;

import java.util.List;

public class AgentService {

    private final AgentDAO agentDAO = new AgentDAO();

    // ADD AGENT
    public boolean addAgent(Agent agent) {

        if (agent == null) {
            return false;
        }

        if (agent.getName() == null || agent.getName().isBlank()) {
            return false;
        }

        if (agent.getEmail() == null || agent.getEmail().isBlank()) {
            return false;
        }

        if (agent.getDepartment() == null || agent.getDepartment().isBlank()) {
            return false;
        }

        return agentDAO.addAgent(agent);
    }

    // GET AGENT BY ID
    public Agent getAgentById(int agentId) {

        if (agentId <= 0) {
            return null;
        }

        return agentDAO.getAgentById(agentId);
    }

    // GET ALL AGENTS
    public List<Agent> getAllAgents() {

        return agentDAO.getAllAgents();
    }

    // UPDATE AGENT
    public boolean updateAgent(Agent agent) {

        if (agent == null || agent.getAgentId() <= 0) {
            return false;
        }

        if (agent.getName() == null || agent.getName().isBlank()) {
            return false;
        }

        if (agent.getEmail() == null || agent.getEmail().isBlank()) {
            return false;
        }

        if (agent.getDepartment() == null || agent.getDepartment().isBlank()) {
            return false;
        }

        return agentDAO.updateAgent(agent);
    }

    // DELETE AGENT
    public boolean deleteAgent(int agentId) {

        if (agentId <= 0) {
            return false;
        }

        return agentDAO.deleteAgent(agentId);
    }
}