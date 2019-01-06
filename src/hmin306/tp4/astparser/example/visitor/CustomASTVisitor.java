package hmin306.tp4.astparser.example.visitor;

import java.lang.reflect.Modifier;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;
import java.util.TreeSet;

import org.eclipse.jdt.core.dom.ASTNode;
import org.eclipse.jdt.core.dom.ASTVisitor;
import org.eclipse.jdt.core.dom.Expression;
import org.eclipse.jdt.core.dom.IMethodBinding;
import org.eclipse.jdt.core.dom.ITypeBinding;
import org.eclipse.jdt.core.dom.MethodDeclaration;
import org.eclipse.jdt.core.dom.MethodInvocation;
import org.eclipse.jdt.core.dom.PackageDeclaration;
import org.eclipse.jdt.core.dom.SimpleName;
import org.eclipse.jdt.core.dom.TypeDeclaration;

import hmin306.tp4.astparser.example.structure.SetType;
import hmin306.tp4.astparser.example.structure.TreeNode;
import hmin306.tp4.astparser.example.structure.TreeStructure;
import hmin306.tp4.astparser.example.structure.Triplet;

public class CustomASTVisitor extends ASTVisitor
{
	private final static int PERCENT = 20;

	private final static int X = 2;

	// DATA

	private static int classCounter = 0;
	private static int lineCounter = 0;
	private static int methodCounter = 0;
	private static int packageCounter = 0;

	private static List<String> percentClassWithManyMethods = new ArrayList<String>();
	private static List<String> percentClassWithManyAttributes = new ArrayList<String>();

	private static Collection<String> classWithManyMethodsAndAttributes = new ArrayList<String>();

	private static Collection<String> classWithMoreThanXMethods = new ArrayList<String>();
	private static Collection<String> percentMethodsWithLargestCode = new ArrayList<String>();

	private static int maximumMethodParameter = 0;

	private static Map<String, Collection<String>> classMethods = new TreeMap<String, Collection<String>>();
	private static Map<String, Collection<String>> methodMethods = new TreeMap<String, Collection<String>>();

	public static Map<String, TreeStructure> treeStructures = new TreeMap<String, TreeStructure>();

	private static TreeSet<SetType> classWithManyMethods = new TreeSet<SetType>();
	private static TreeSet<SetType> classWithManyAttributes = new TreeSet<SetType>();
	private static TreeSet<SetType> methodsWithLargestCode = new TreeSet<SetType>();

	private static int attributeCounter = 0;
	private static TreeSet<String> packages = new TreeSet<String>();
	private static int methodLineCounter = 0;

	private static List<Triplet> classesReferences = new ArrayList<Triplet>();

	private String currentPackageName;
	private String currentClassName;
	
	public boolean visit(PackageDeclaration node)
	{
		packages.add(node.getName().toString());
		packageCounter++;

		currentPackageName = node.getName().toString();

		return true;
	}

	public boolean visit(TypeDeclaration node)
	{
		SimpleName className = node.getName();

		currentClassName = className.toString();

		if (treeStructures.get(node.getName().toString()) == null)
			treeStructures.put(node.getName().toString(), new TreeStructure(node.getName().toString()));

		classMethods.put(className.toString(), new ArrayList<String>());

		int localLineCounter = node.toString().length()
				- node.toString().replace(System.getProperty("line.separator"), "").length();

		if (localLineCounter == 0)
			localLineCounter += node.toString().length() - node.toString().replace("\n", "").length();

		lineCounter += localLineCounter;
		classCounter++;

		/*
		 * for(Object object : node.superInterfaceTypes()) System.out.println(object);
		 */

		/*
		 * for(FieldDeclaration fieldDeclaration : node.getFields()) System.out.println(
		 * fieldDeclaration.fragments().get(0) + " - " +
		 * fieldDeclaration.modifiers().toString());
		 */
		attributeCounter += node.getFields().length;

		classWithManyAttributes.add(new SetType(className.toString(), node.getFields().length));

		for (MethodDeclaration methodDeclaration : node.getMethods())
		{
			/*
			 * System.out.println(methodDeclaration.getName() + " - " +
			 * methodDeclaration.getReturnType2() + " - " + methodDeclaration.parameters());
			 */
			if (methodDeclaration.parameters().size() > maximumMethodParameter)
				maximumMethodParameter = methodDeclaration.parameters().size();

			localLineCounter = methodDeclaration.getBody().toString().length()
					- methodDeclaration.getBody().toString().replace(System.getProperty("line.separator"), "").length();

			if (localLineCounter == 0)
				localLineCounter += methodDeclaration.getBody().toString().length()
						- methodDeclaration.getBody().toString().replace("\n", "").length();

			methodLineCounter += localLineCounter;

			classMethods.get(className.toString()).add(methodDeclaration.getName().toString());

			if (treeStructures.get(node.getName().toString()).declarationInvocations
					.get(methodDeclaration.getName().toString()) == null)
				treeStructures.get(node.getName().toString()).declarationInvocations
						.put(methodDeclaration.getName().toString(), new TreeSet<TreeNode>());

			methodsWithLargestCode.add(new SetType(
					(methodDeclaration.getName() + " - " + methodDeclaration.getReturnType2() + " - "
							+ methodDeclaration.parameters()),
					localLineCounter, methodDeclaration.getName().toString()));
		}

		if (node.getMethods().length > X)
			classWithMoreThanXMethods.add(className.toString());

		classWithManyMethods.add(new SetType(className.toString(), node.getMethods().length));

		methodCounter += node.getMethods().length;

		return true;
	}
//
//	public void endVisit(MethodInvocation methodInvocation)
//	{
//		try
//		{
//			ASTNode parent = methodInvocation.getParent();
//
//			if (parent == null)
//				return;
//
//			while (parent.getNodeType() != 31)
//			{
//				parent = parent.getParent();
//
//				if (parent == null)
//					return;
//			}
//
//			MethodDeclaration methodDeclaration = (MethodDeclaration) parent;
//
//			parent = methodInvocation.getParent();
//
//			if (parent == null)
//				return;
//
//			while (parent.getNodeType() != 55)
//				parent = parent.getParent();
//
//			TypeDeclaration typeDeclaration = (TypeDeclaration) parent;
//
////			if(treeStructures.get(typeDeclaration.getName().toString()).declarationInvocations
////				.get(methodDeclaration.getName().toString()) == null)
////				treeStructures.get(typeDeclaration.getName().toString()).declarationInvocations
////					.put(methodDeclaration.getName().toString(), new TreeSet<TreeNode>());
//
//			// System.out.println("METHODINVOCATION : " +
//			// methodInvocation.getName().toString());
////
////			treeStructures.get(typeDeclaration.getName().toString()).declarationInvocations
////				.get(methodDeclaration.getName().toString())
////				.add(new TreeNode("", methodInvocation.getName().toString()));
//
//			// methodMethods.get(methodDeclaration.getName().toString()).add(methodInvocation.getName().toString());
//
//			Expression expression = methodInvocation.getExpression();
//
//			if (expression != null)
//			{
//				ITypeBinding typeBinding = expression.resolveTypeBinding();
//
//				System.out.println("expression : " + expression);
//				System.out.println("typeBinding : " + typeBinding);
//
//				// expression.resolveTypeBinding();
//
//				// System.out.println("Expression : " +
//				// methodInvocation.getExpression());
//				// System.out.println("TypeBinding: " +
//				// typeBinding);
//				// System.out.println("Type: " +
//				// typeBinding.toString());
//			}
//
//		} catch (NullPointerException nullPointerException)
//		{
//			nullPointerException.printStackTrace();
//		}
//	}

	public boolean visit(MethodInvocation methodInvocation)
	{
		Expression expression = methodInvocation.getExpression();

		if (expression == null)
			return true;

		ITypeBinding typeBinding = expression.resolveTypeBinding();

		IMethodBinding methodBinding = methodInvocation.resolveMethodBinding();

		//Static call
		if (methodBinding != null && (methodBinding.getModifiers() & Modifier.STATIC) > 0)
		{
			boolean exist = false;
			int index;
			String completeName = currentPackageName + "." + currentClassName;
		
			for (index = 0; index < classesReferences.size(); index++)
				if (classesReferences.get(index).getClassFromName().equals(completeName)
						&& classesReferences.get(index).getClassToName().equals(expression.toString()))
				{
					exist = true;
					break;
				}

			if (exist)
				classesReferences.get(index).incrementReferences();
			else
			{
				if(ClassVisitor.getProjectClass().contains(currentPackageName + "." + currentClassName) && ClassVisitor.getProjectClass().contains(expression.toString()))
					classesReferences.add(new Triplet(currentPackageName + "." + currentClassName, expression.toString()));
			}
		}
		else if (typeBinding != null) //Call object
		{
			boolean exist = false;
			int index;
			String completeName = currentPackageName + "." + currentClassName;

			for (index = 0; index < classesReferences.size(); index++)
			{
				if (classesReferences.get(index).getClassFromName().equals(completeName)
						&& classesReferences.get(index).getClassToName().equals(typeBinding.getQualifiedName()))
				{
					exist = true;
					break;
				}
			}	

			if (exist)
				classesReferences.get(index).incrementReferences();
			else
				if(ClassVisitor.getProjectClass().contains(currentPackageName + "." + currentClassName) && ClassVisitor.getProjectClass().contains(typeBinding.getQualifiedName()))
					classesReferences.add(new Triplet(currentPackageName + "." + currentClassName, typeBinding.getQualifiedName()));
		}
		
		if (typeBinding != null)
		{
			ASTNode parent = methodInvocation.getParent();
	
			if (parent == null)
				return true;
	
			while (parent.getNodeType() != 31)
			{
				parent = parent.getParent();
	
				if (parent == null)
					return true;
			}
	
			MethodDeclaration methodDeclaration = (MethodDeclaration) parent;
	
			parent = methodInvocation.getParent();
	
			if (parent == null)
				return true;
	
			while (parent.getNodeType() != 55)
				parent = parent.getParent();
	
			TypeDeclaration typeDeclaration = (TypeDeclaration) parent;
	
			if(treeStructures.get(typeDeclaration.getName().toString()).declarationInvocations
				.get(methodDeclaration.getName().toString()) == null)
				treeStructures.get(typeDeclaration.getName().toString()).declarationInvocations
					.put(methodDeclaration.getName().toString(), new TreeSet<TreeNode>());
	
			treeStructures.get(typeDeclaration.getName().toString()).declarationInvocations
				.get(methodDeclaration.getName().toString())
				.add(new TreeNode(typeBinding.getName(), methodInvocation.getName().toString()));
	
			//methodMethods.get(methodDeclaration.getName().toString()).add(methodInvocation.getName().toString());
		}		
		
		
		/////////////////////////////

		return true;
	}

	public static void percentOfClassWithManyMethods()
	{
		int numberToSelect = (classCounter * PERCENT) / 100;
		int counter = 0;

		for (SetType it : classWithManyMethods)
			if (counter != numberToSelect)
			{
				percentClassWithManyMethods.add(it.toString());
				counter++;
			} else
				return;
	}

	public static void percentOfClassWithManyAttributs()
	{
		int numberToSelect = (classCounter * PERCENT) / 100;
		int counter = 0;

		for (SetType setType : classWithManyAttributes)
			if (counter != numberToSelect)
			{
				percentClassWithManyAttributes.add(setType.toString());
				counter++;
			} else
				return;
	}

	public static void percentOfMethodsWithLargestCode()
	{
		int numberToSelect = (methodCounter * PERCENT) / 100;
		int counter = 0;

		for (SetType setType : methodsWithLargestCode)
			if (counter != numberToSelect)
			{
				percentMethodsWithLargestCode.add(setType.toString());
				counter++;
			} else
				return;
	}

	public static void mergeBetweenClassWithManyAttributesAndMethods()
	{
		for (String cMethods : percentClassWithManyMethods)
			for (String cAttributes : percentClassWithManyAttributes)
				if (cMethods.equals(cAttributes))
					classWithManyMethodsAndAttributes.add(cMethods.toString());
	}

	public static int getMethodsAverage()
	{
		return methodCounter / classCounter;
	}

	public static int getCodeLineMethodAverage()
	{
		return methodLineCounter / methodCounter;
	}

	public static int getAttributeAverage()
	{
		return attributeCounter / classCounter;
	}

	public static int getClassCounter()
	{
		return classCounter;
	}

	public static int getLineCounter()
	{
		return lineCounter;
	}

	public static int getMethodCounter()
	{
		return methodCounter;
	}

	public static int getPackageCounter()
	{
		return packageCounter;
	}

	public static List<String> getPercentClassWithManyMethods()
	{
		return percentClassWithManyMethods;
	}

	public static List<String> getPercentClassWithManyAttributes()
	{
		return percentClassWithManyAttributes;
	}

	public static Collection<String> getClassWithManyMethodsAndAttributes()
	{
		return classWithManyMethodsAndAttributes;
	}

	public static Collection<String> getClassWithMoreThanXMethods()
	{
		return classWithMoreThanXMethods;
	}

	public static Collection<String> getPercentMethodsWithLargestCode()
	{
		return percentMethodsWithLargestCode;
	}

	public static int getMaximumMethodParameter()
	{
		return maximumMethodParameter;
	}

	public static Map<String, Collection<String>> getClassMethods()
	{
		return classMethods;
	}

	public static Map<String, Collection<String>> getMethodMethods()
	{
		return methodMethods;
	}

	public static Map<String, TreeStructure> getTreeStructures()
	{
		return treeStructures;
	}

	public static TreeSet<SetType> getClassWithManyMethods()
	{
		return classWithManyMethods;
	}

	public static TreeSet<SetType> getClassWithManyAttributes()
	{
		return classWithManyAttributes;
	}

	public static TreeSet<SetType> getMethodsWithLargestCode()
	{
		return methodsWithLargestCode;
	}

	public static int getAttributeCounter()
	{
		return attributeCounter;
	}

	public static TreeSet<String> getPackages()
	{
		return packages;
	}

	public static int getMethodLineCounter()
	{
		return methodLineCounter;
	}

	public static List<Triplet> getClassesReferences()
	{
		return classesReferences;
	}
}
