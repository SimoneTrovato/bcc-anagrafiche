package content;

import java.util.List;


public class MenuContent {
	
    private String version;
    private List<MenuNode> nodes;

    public MenuContent(String version, List<MenuNode> nodes) {
		super();
		this.version = version;
		this.nodes = nodes;
	}

	public MenuContent() {
        super();
    }

    public String getVersion() {
        return version;
    }

    public void setVersion(String version) {
        this.version = version;
    }

	public List<MenuNode> getNodes() {
		return nodes;
	}

	public void setNodes(List<MenuNode> nodes) {
		this.nodes = nodes;
	}
  
}
