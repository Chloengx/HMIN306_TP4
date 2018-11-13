package TP3.astparser.example;

public class TreeNode implements Comparable
{
	public String className;
	public String methodName;
	
	public TreeNode(String className, String methodName)
	{
		this.className = className;
		
		this.methodName = methodName;
	}

	@Override
	public int compareTo(Object arg0)
	{
		TreeNode treeNode = (TreeNode) arg0;
		
		return className.compareTo(treeNode.className) + methodName.compareTo(treeNode.methodName);
	}
}
