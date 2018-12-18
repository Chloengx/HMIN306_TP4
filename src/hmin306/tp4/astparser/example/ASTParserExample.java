package hmin306.tp4.astparser.example;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collection;

import org.eclipse.jdt.core.dom.AST;
import org.eclipse.jdt.core.dom.ASTParser;
import org.eclipse.jdt.core.dom.CompilationUnit;

import hmin306.tp4.astparser.example.visitor.ClassVisitor;
import hmin306.tp4.astparser.example.visitor.CustomASTVisitor;
import hmin306.tp4.astparser.util.ParsingHelper;

public class ASTParserExample
{
	private final static String UNIT_NAME = "HMIN306_TP4";

	private final static String ENCODING = "UTF-8";

	// Attributes

	private ASTParser astParser;
	private Collection<String> sourceFiles = new ArrayList<String>();
	private String sourcePath;
	private CustomASTVisitor customASTVisitor;

	private String environmentClassPath;
	private String environmentSourcePath;

	public ASTParserExample(String sourcePath, String environmentClassPath, String environmentSourcePath)
			throws IOException
	{
		this.sourcePath = sourcePath;
		this.environmentClassPath = environmentClassPath;
		this.environmentSourcePath = environmentSourcePath;

		astParser = ASTParser.newParser(AST.JLS10);
		astParser.setResolveBindings(true);
		astParser.setKind(ASTParser.K_COMPILATION_UNIT);

		astParser.setBindingsRecovery(true);

		astParser.setUnitName(UNIT_NAME);

		String[] environmentClassPathFormated =
		{ this.environmentClassPath };
		String[] environmentSourcesFormated =
		{ this.environmentSourcePath };

		astParser.setEnvironment(environmentClassPathFormated, environmentSourcesFormated, new String[]
		{ ENCODING }, true);
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
		String[] environmentClassPathFormated =
		{ this.environmentClassPath };
		String[] environmentSourcesFormated =
		{ this.environmentSourcePath };
		
		astParser = ASTParser.newParser(AST.JLS10);
		astParser.setResolveBindings(true);
		astParser.setKind(ASTParser.K_COMPILATION_UNIT);

		astParser.setBindingsRecovery(true);

		astParser.setUnitName(UNIT_NAME);

		astParser.setEnvironment(environmentClassPathFormated, environmentSourcesFormated, new String[]
		{ ENCODING }, true);

		astParser.setSource(ParsingHelper.fileToString(sourceFile).toCharArray());

		CompilationUnit compilationUnit = (CompilationUnit) astParser.createAST(null);

		ClassVisitor classVisitor = new ClassVisitor();

		compilationUnit.accept(classVisitor);
		
		astParser = ASTParser.newParser(AST.JLS10);
		astParser.setResolveBindings(true);
		astParser.setKind(ASTParser.K_COMPILATION_UNIT);

		astParser.setBindingsRecovery(true);

		astParser.setUnitName(UNIT_NAME);

		astParser.setEnvironment(environmentClassPathFormated, environmentSourcesFormated, new String[]
		{ ENCODING }, true);

		astParser.setSource(ParsingHelper.fileToString(sourceFile).toCharArray());

		compilationUnit = (CompilationUnit) astParser.createAST(null);

		CustomASTVisitor customASTVisitor = new CustomASTVisitor();

		compilationUnit.accept(customASTVisitor);
	}

	public CustomASTVisitor getCustomASTVisitor()
	{
		return customASTVisitor;
	}
}