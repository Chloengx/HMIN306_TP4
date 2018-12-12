package hmin306.tp4.astparser.example;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collection;

import org.eclipse.jdt.core.dom.AST;
import org.eclipse.jdt.core.dom.ASTParser;
import org.eclipse.jdt.core.dom.CompilationUnit;

import hmin306.tp4.astparser.util.ParsingHelper;

public class ASTParserExample
{
	private final static String UNIT_NAME = "HMIN306_TP4";
	
	private final static String WINDOB_ENVIRONMENT_CLASS_PATH = "D:\\workspace\\HMIN306_TP4";
	private final static String WINDOB_ENVIRONMENT_SOURCES = "D:\\workspace\\HMIN306_TP4";
	
	private final static String LINUX_ENVIRONMENT_CLASS_PATH = "/home/harkame/workspace/HMIN306_TP4";
	private final static String LINUX_ENVIRONMENT_SOURCES = "/home/harkame/workspace/HMIN306_TP4";
	
	private final static String ENCODING = "UTF-8";
	
	// Attributes

	private ASTParser astParser;
	private Collection<String> sourceFiles = new ArrayList<String>();
	private String sourcePath;
	private CustomASTVisitor customASTVisitor;

	public ASTParserExample(String sourcePath) throws IOException
	{
		astParser = ASTParser.newParser(AST.JLS10);
		astParser.setResolveBindings(true);
		astParser.setKind(ASTParser.K_COMPILATION_UNIT);
 
		astParser.setBindingsRecovery(true);
 
		astParser.setUnitName(UNIT_NAME);

		String[] environmentClassPath = { WINDOB_ENVIRONMENT_CLASS_PATH };
		String[] environmentSources = { WINDOB_ENVIRONMENT_SOURCES };
	
		astParser.setEnvironment(environmentClassPath, environmentSources, new String[] { ENCODING }, true);

		this.sourcePath = sourcePath;
	}

	public void initialize() throws IOException
	{
		exploreProject(sourcePath);
		parseProject();

		CustomASTVisitor.percentOfClassWithManyMethods();
		CustomASTVisitor.percentOfClassWithManyAttributs();
		CustomASTVisitor.percentOfMethodsWithLargestCode();
		CustomASTVisitor.mergeBetweenClassWithManyAttributesAndMethods();
	}

	public void exploreProject(String directory) throws IOException
	{
		File root = new File(directory);
		
		for (File file : root.listFiles())
			if (file.isDirectory())
				exploreProject(file.getAbsolutePath());
			else
				sourceFiles.add(file.getAbsolutePath());
	}

	

	public void parseProject() throws IOException
	{
		for (String sourceFile : sourceFiles)
			parseFile(sourceFile);
	}

	private void parseFile(String sourceFile) throws IOException
	{
		astParser = ASTParser.newParser(AST.JLS10);
		astParser.setResolveBindings(true);
		astParser.setKind(ASTParser.K_COMPILATION_UNIT);
 
		astParser.setBindingsRecovery(true);
 
		astParser.setUnitName(UNIT_NAME);

		String[] environmentClassPath = { WINDOB_ENVIRONMENT_CLASS_PATH };
		String[] environmentSources = { WINDOB_ENVIRONMENT_SOURCES };
	
		astParser.setEnvironment(environmentClassPath, environmentSources, new String[] { ENCODING }, true);

		astParser.setSource(ParsingHelper.fileToString(sourceFile).toCharArray());

		CompilationUnit compilationUnit = (CompilationUnit) astParser.createAST(null);

		CustomASTVisitor customASTVisitor = new CustomASTVisitor();
		
		compilationUnit.accept(customASTVisitor);
	}
	
	public CustomASTVisitor getCustomASTVisitor()
	{
		return customASTVisitor;
	}
}