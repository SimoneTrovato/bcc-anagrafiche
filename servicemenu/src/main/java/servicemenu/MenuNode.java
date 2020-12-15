package servicemenu;

import java.util.List;

public class MenuNode {
	int nodeId;
	String nodeName;
	String nodeType;
	String groupType;
	String flowType;
	String status;
	int startValidityT;
	int sendValidityTs;
	String tag;
	List<MenuNode> nodes;
	Resource resource;
	public MenuNode(int nodeId, String nodeName, String nodeType, String groupType, String flowType, String status,
			int startValidityT, int sendValidityTs, String tag, List<MenuNode> nodes, Resource resource) {
		super();
		this.nodeId = nodeId;
		this.nodeName = nodeName;
		this.nodeType = nodeType;
		this.groupType = groupType;
		this.flowType = flowType;
		this.status = status;
		this.startValidityT = startValidityT;
		this.sendValidityTs = sendValidityTs;
		this.tag = tag;
		this.nodes = nodes;
		this.resource = resource;
	}
	public int getNodeId() {
		return nodeId;
	}
	public void setNodeId(int nodeId) {
		this.nodeId = nodeId;
	}
	public String getNodeName() {
		return nodeName;
	}
	public void setNodeName(String nodeName) {
		this.nodeName = nodeName;
	}
	public String getNodeType() {
		return nodeType;
	}
	public void setNodeType(String nodeType) {
		this.nodeType = nodeType;
	}
	public String getGroupType() {
		return groupType;
	}
	public void setGroupType(String groupType) {
		this.groupType = groupType;
	}
	public String getFlowType() {
		return flowType;
	}
	public void setFlowType(String flowType) {
		this.flowType = flowType;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	public int getStartValidityT() {
		return startValidityT;
	}
	public void setStartValidityT(int startValidityT) {
		this.startValidityT = startValidityT;
	}
	public int getSendValidityTs() {
		return sendValidityTs;
	}
	public void setSendValidityTs(int sendValidityTs) {
		this.sendValidityTs = sendValidityTs;
	}
	public String getTag() {
		return tag;
	}
	public void setTag(String tag) {
		this.tag = tag;
	}
	public List<MenuNode> getNodes() {
		return nodes;
	}
	public void setNodes(List<MenuNode> nodes) {
		this.nodes = nodes;
	}
	public Resource getResource() {
		return resource;
	}
	public void setResource(Resource resource) {
		this.resource = resource;
	}
	
	
	
	
	
}