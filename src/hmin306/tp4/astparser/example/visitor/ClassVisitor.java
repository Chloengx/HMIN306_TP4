package hmin306.tp4.astparser.example.visitor;

import java.util.ArrayList;
import java.util.Collection;

import org.eclipse.jdt.core.dom.ASTVisitor;
import org.eclipse.jdt.core.dom.PackageDeclaration;
import org.eclipse.jdt.core.dom.SimpleName;
import org.eclipse.jdt.core.dom.TypeDeclaration;

public class ClassVisitor extends ASTVisitor
{
	private static Collection<String> projectClass = new ArrayList<String>();

	private String currentPackageName;
	
	public boolean visit(PackageDeclaration node)
	{
		currentPackageName = node.getName().toString();

		return true;
	}
	
	public boolean visit(TypeDeclaration node)
	{
		SimpleName className = node.getName();
		
		projectClass.add(currentPackageName + "." + className.toString());

		return true;
	}

	public static Collection<String> getProjectClass()
	{
		return projectClass;
	}
}
