package hmin306.tp4.spoon.example;

import java.util.Collection;
import java.util.List;

import hmin306.tp4.structure.coupling.CouplingStructure;
import hmin306.tp4.structure.tree.ClassTree;
import spoon.Launcher;
import spoon.reflect.code.CtInvocation;
import spoon.reflect.declaration.CtMethod;
import spoon.reflect.declaration.CtType;
import spoon.reflect.visitor.Query;
import spoon.reflect.visitor.filter.TypeFilter;

public class SpoonExample <T>
{
	public ClassTree classTree = new ClassTree();
	
	public CouplingStructure couplingStructure;
	
	public int classCounter = 0;
	public int methodCounter = 0;
	public int methodPerClassAverage;
	
	private Launcher launcher = new Launcher();
	
	public SpoonExample(String sourcePath)
	{			
		launcher.getEnvironment().setNoClasspath(true);

		launcher.addInputResource(sourcePath);
		launcher.buildModel();
	}
	
	public void analyse()
	{
		for(CtType<?> ctType : launcher.getFactory().Type().getAll())
			analyse(ctType);
		
		couplingStructure = new CouplingStructure(classTree);
		 
		methodPerClassAverage = methodCounter/classCounter;
	}
	
	private void analyse(CtType<?> ctType)
	{
		Collection<CtMethod<?>> methods = ctType.getMethods();
		
		classTree.addClassDeclaration(ctType.getQualifiedName());
		
		classCounter++;
		
		for(CtMethod<?> methodDeclaration : methods)
		{
			methodCounter++;
			
			classTree.addMethodDeclaration(ctType.getQualifiedName(), methodDeclaration.getSimpleName());
			
			for(CtInvocation<?> methodInvocation : (List<CtInvocation>) Query.getElements(methodDeclaration, new TypeFilter<CtInvocation>(CtInvocation.class)))
			{
				if(methodInvocation.getTarget() != null && 
					methodInvocation.getTarget().getType() != null)
					if(isProjectClass(methodInvocation.getTarget().getType().toString()))
						classTree.addMethodInvocation(ctType.getQualifiedName(), methodDeclaration.getSimpleName(), methodInvocation.getTarget().getType().toString(), methodInvocation.getExecutable().getSimpleName());
			}
		}
	}
	
	public boolean isProjectClass(String className)
	{
		for(CtType<?> ctType : launcher.getFactory().Type().getAll())
			if(ctType.getQualifiedName().equals(className))
				return true;
		
		return false;
	}
}
