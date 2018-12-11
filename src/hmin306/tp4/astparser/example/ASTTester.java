 package hmin306.tp4.astparser.example;

import java.io.IOException;
import java.lang.reflect.Modifier;
import java.util.Map;

import org.eclipse.jdt.core.JavaCore;
import org.eclipse.jdt.core.dom.AST;
import org.eclipse.jdt.core.dom.ASTParser;
import org.eclipse.jdt.core.dom.ASTVisitor;
import org.eclipse.jdt.core.dom.CompilationUnit;
import org.eclipse.jdt.core.dom.Expression;
import org.eclipse.jdt.core.dom.IMethodBinding;
import org.eclipse.jdt.core.dom.ITypeBinding;
import org.eclipse.jdt.core.dom.MethodInvocation;
 
public class ASTTester {
 
	public static void main(String[] args) throws IOException {
		char[] str = ASTParserExample.fileToString("/auto_home/ldaviaud/workspace/JapScanDownloader/src/fr/harkame/japscandownloader/downloader/JapscanDownloader.java");
 
		ASTParser parser = ASTParser.newParser(AST.JLS8);
		parser.setResolveBindings(true);
		parser.setKind(ASTParser.K_COMPILATION_UNIT);
 
		parser.setBindingsRecovery(true);
 
		Map options = JavaCore.getOptions();
		parser.setCompilerOptions(options);
 
		String unitName = "Apple.java";
		parser.setUnitName(unitName);
		
		String[] sources = { "/auto_home/ldaviaud/workspace/HMIN306_TP4" };
		String[] classPaths = { "/auto_home/ldaviaud/workspace/HMIN306_TP4" };
	
		parser.setEnvironment(classPaths, sources, new String[] { "UTF-8"}, true);
		parser.setSource(str);
 
		CompilationUnit cu = (CompilationUnit) parser.createAST(null);
 
		if (cu.getAST().hasBindingsRecovery()) {
			System.out.println("Binding activated.");
		}
 
		TypeFinderVisitor v = new TypeFinderVisitor();
		cu.accept(v);		
	}
}
 
class TypeFinderVisitor extends ASTVisitor
{	
	public void endVisit(MethodInvocation methodInvocation)
	{
		try
		{
			Expression expression = methodInvocation.getExpression();

			if(expression != null)
			{
				ITypeBinding typeBinding = expression.resolveTypeBinding();
				
				IMethodBinding methodBinding = methodInvocation.resolveMethodBinding();
				
				if(methodBinding != null && (methodBinding.getModifiers() & Modifier.STATIC) > 0)
				{
					System.out.println("STATIC : " + expression);
				}
				else if(typeBinding != null)
				{
					System.out.println("expression : " + expression);
					
					System.out.println("typeBinding : " + typeBinding.getQualifiedName());
				}
				else
				{
					System.out.println("NULL : " + expression);
				}
				
				//if(typeBinding == null)
					//System.out.println("UnresolvedTypeBinding : " + expression);
				// expression.resolveTypeBinding();

				// System.out.println("Expression : " +
				// methodInvocation.getExpression());
				// System.out.println("TypeBinding: " +
				// typeBinding);
				// System.out.println("Type: " +
				// typeBinding.toString());
			}

		}
		catch(NullPointerException nullPointerException)
		{
			nullPointerException.printStackTrace();
		}
	}
}