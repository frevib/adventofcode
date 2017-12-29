import java.util.ArrayList;
import java.util.List;

public class Node {

	private String name;
	private List<Node> children = new ArrayList<Node>();
	private Node parent;
	private int weight;
	private int totalWeight = 0;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public List<Node> getChildren() {
		return children;
	}

	public void setChildren(List<Node> children) {
		this.children = children;
	}

	public void setChild(int index, Node node) {
		children.set(index, node);
	}

	public void addChild(Node child) {
		children.add(child);
	}

	public Node getParent() {
		return parent;
	}

	public void setParent(Node parent) {
		this.parent = parent;
	}

	public int getWeight() {
		return weight;
	}

	public void setWeight(int weight) {
		this.weight = weight;
		this.totalWeight += weight;
	}

	public void addWeight(int weight) {
		this.totalWeight += weight;
	}

	public int getTotalWeight() {
		return totalWeight;
	}
}