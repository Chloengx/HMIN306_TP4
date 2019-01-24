package hmin306.tp4.dendrogram;

import java.util.ArrayList;
import java.util.Collection;

public class DendogramNode
{
	public String className;
	public int value;
	
	public Collection<DendogramNode> childs = new ArrayList<DendogramNode>();

	public DendogramNode(String className, int value)
	{
		super();
		this.className = className;
		this.value = value;
	}
	
	
}
