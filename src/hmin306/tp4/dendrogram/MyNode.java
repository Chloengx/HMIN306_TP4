package hmin306.tp4.dendrogram;

public class MyNode
{
	public int level;
	public DendrogramNode dendrogramNode;
	
	public MyNode(int level, DendrogramNode dendrogramNode)
	{
		super();
		this.level = level;
		this.dendrogramNode = dendrogramNode;
	}
}
