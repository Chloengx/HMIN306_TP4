package hmin306.tp4.spoon.example;

import java.util.Collection;
import java.util.List;

import org.eclipse.jdt.core.dom.IMethodBinding;

import hmin306.tp4.structure.tree.ClassTree;
import spoon.Launcher;
import spoon.reflect.code.CtInvocation;
import spoon.reflect.declaration.CtMethod;
import spoon.reflect.declaration.CtType;
import spoon.reflect.visitor.Query;
import spoon.reflect.visitor.filter.TypeFilter;

public class SpoonExample <T>
{
	private ClassTree classTree = new ClassTree();
	
	private Launcher launcher = new Launcher();
	
	/**
	 * Constructor
	 * @param classFilePath Path where found .java file
	 * @param className Name of the search class into classFilePath
	 */
	public SpoonExample(String sourcePath)
	{			
		launcher.getEnvironment().setNoClasspath(true);

		launcher.addInputResource(sourcePath);
		launcher.buildModel();

		//ctClass = (CtClass<T>) launcher.getFactory().Type().get(className);
	}
	
	public void analyse()
	{
		for(CtType<?> ctType : launcher.getFactory().Type().getAll())
			analyse(ctType);
	}
	
	private void analyse(CtType<?> ctType)
	{
		Collection<CtMethod<?>> methods = ctType.getMethods();
		
		for(CtMethod<?> method : methods)
			for(CtInvocation<?> methodInvocation : (List<CtInvocation>) Query.getElements(method, new TypeFilter<CtInvocation>(CtInvocation.class)))
				if(methodInvocation.getTarget().getType() != null)
					if(isProjectClass(methodInvocation.getTarget().getType().toString()))
						classTree.addMethodInvocation(ctType.getQualifiedName(), method.getSimpleName(), methodInvocation.getTarget().getType().toString(), methodInvocation.getExecutable().getSimpleName());
	}
	
	public boolean isProjectClass(String className)
	{
		for(CtType<?> ctType : launcher.getFactory().Type().getAll())
			if(ctType.getQualifiedName().equals(className))
				return true;
		
		return false;
	}
	
	public ClassTree getClassTree()
	{
		return classTree;
	}
}
