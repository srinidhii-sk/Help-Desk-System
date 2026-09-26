package controller;

import model.Agent;
import service.AgentService;

import java.util.List;

public class AgentController {

    private final AgentService agentService = new AgentService();

    // ADD AGENT
    public boolean addAgent(Agent agent) {

        return agentService.addAgent(agent);
    }

    // GET AGENT BY ID
    public Agent getAgentById(int agentId) {

        return agentService.getAgentById(agentId);
    }

    // GET ALL AGENTS
    public List<Agent> getAllAgents() {

        return agentService.getAllAgents();
    }

    // UPDATE AGENT
    public boolean updateAgent(Agent agent) {

        return agentService.updateAgent(agent);
    }

    // DELETE AGENT
    public boolean deleteAgent(int agentId) {

        return agentService.deleteAgent(agentId);
    }
}