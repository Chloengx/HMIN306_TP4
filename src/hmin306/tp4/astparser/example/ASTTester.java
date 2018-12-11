package hmin306.tp4.astparser.example;

import java.io.IOException;
import java.lang.reflect.Modifier;

import org.eclipse.jdt.core.dom.AST;
import org.eclipse.jdt.core.dom.ASTParser;
import org.eclipse.jdt.core.dom.ASTVisitor;
import org.eclipse.jdt.core.dom.CompilationUnit;
import org.eclipse.jdt.core.dom.Expression;
import org.eclipse.jdt.core.dom.IMethodBinding;
import org.eclipse.jdt.core.dom.ITypeBinding;
import org.eclipse.jdt.core.dom.MethodInvocation;

import hmin306.tp4.astparser.util.ParsingHelper;

public class ASTTester
{

	public static void main(String[] args) throws IOException
	{
		ASTParser astParser = ASTParser.newParser(AST.JLS10);
		astParser.setResolveBindings(true);
		astParser.setKind(ASTParser.K_COMPILATION_UNIT);

		astParser.setBindingsRecovery(true);

		String unitName = "HMIN306_TP4";
		astParser.setUnitName(unitName);

		String[] sources =
		{ "/home/harkame/workspace/JapScanDownloader" };
		String[] classPaths =
		{ "/home/harkame/workspace/JapScanDownloader" };

		astParser.setEnvironment(classPaths, sources, new String[]
		{ "UTF-8" }, true);
		
		astParser.setSource(ParsingHelper.fileToString(
				"/home/harkame/workspace/JapScanDownloader/src/fr/harkame/japscandownloader/downloader/JapScanDownloader.java")
				.toCharArray());

		CompilationUnit compilationUnit = (CompilationUnit) astParser.createAST(null);

		CustomASTVisitor customASTVisitor = new CustomASTVisitor();
		
		compilationUnit.accept(customASTVisitor);
	}
}

class TypeFinderVisitor extends ASTVisitor
{
	public void endVisit(MethodInvocation methodInvocation)
	{
		try
		{
			Expression expression = methodInvocation.getExpression();

			if (expression != null)
			{
				ITypeBinding typeBinding = expression.resolveTypeBinding();

				IMethodBinding methodBinding = methodInvocation.resolveMethodBinding();

				if (methodBinding != null && (methodBinding.getModifiers() & Modifier.STATIC) > 0)

					System.out.println("STATIC : " + expression);
				else if (typeBinding != null)
				{
					System.out.println("expression : " + expression);

					System.out.println("typeBinding : " + typeBinding.getQualifiedName());
				}else
					System.out.println("NULL : " + expression);

				// if(typeBinding == null)
				// System.out.println("UnresolvedTypeBinding : " + expression);
				// expression.resolveTypeBinding();

				// System.out.println("Expression : " +
				// methodInvocation.getExpression());
				// System.out.println("TypeBinding: " +
				// typeBinding);
				// System.out.println("Type: " +
				// typeBinding.toString());
			}

		} catch (NullPointerException nullPointerException)
		{
			nullPointerException.printStackTrace();
		}
	}
}