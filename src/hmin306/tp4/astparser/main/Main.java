package hmin306.tp4.astparser.main;

import java.io.IOException;
import java.util.Map;

import javax.swing.JFrame;

import hmin306.tp4.astparser.example.ASTParserExample;
import hmin306.tp4.astparser.example.structure.TreeStructure;
import hmin306.tp4.astparser.example.visitor.ClassVisitor;
import hmin306.tp4.astparser.example.visitor.CustomASTVisitor;
import hmin306.tp4.astparser.util.GraphAST;

public class Main
{
	private static final String	LINE_SEPARATOR				= System.getProperty("line.separator");
	private final static String	WINDOB_PROJECT_SOURCE_FOLDER	= "D:\\workspace\\JapScanDownloader\\src";
	private final static String	WINDOB_ENVIRONMENT_CLASS_PATH	= "D:\\workspace\\HMIN306_TP4";
	private final static String	WINDOB_ENVIRONMENT_SOURCES	= "D:\\workspace\\HMIN306_TP4";

	private final static String	LINUX_PROJECT_SOURCE_FOLDER	= "/home/harkame/workspace/JapScanDownloader/src";
	private final static String	LINUX_ENVIRONMENT_CLASS_PATH	= "/home/harkame/workspace/HMIN306_TP4";
	private final static String	LINUX_ENVIRONMENT_SOURCES	= "/home/harkame/workspace/HMIN306_TP4";

	private final static int PERCENT = 20;

	private final static int X = 2;

	public static void main(String[] args) throws IOException
	{
		ASTParserExample astParserExample = new ASTParserExample(WINDOB_PROJECT_SOURCE_FOLDER,
			WINDOB_ENVIRONMENT_CLASS_PATH, WINDOB_ENVIRONMENT_SOURCES);

		astParserExample.initialize();

		System.out.println(LINE_SEPARATOR);
		System.out.println("--- Result --- ");
		System.out.println(LINE_SEPARATOR);

		System.out.println("classCounter : " + CustomASTVisitor.getClassCounter());
		System.out.println("lineCounter : " + CustomASTVisitor.getLineCounter());
		System.out.println("methodCounter : " + CustomASTVisitor.getMethodCounter());
		System.out.println("packageCounter : " + CustomASTVisitor.getPackageCounter());

		System.out.println("methodAverage : " + CustomASTVisitor.getMethodsAverage());
		System.out.println("codeLineMethodAverage : " + CustomASTVisitor.getCodeLineMethodAverage());
		System.out.println("attributeAverage : " + CustomASTVisitor.getAttributeAverage());

		System.out.println(PERCENT + "% of class with many Methods : "
			+ CustomASTVisitor.getPercentClassWithManyMethods().toString());
		System.out.println(PERCENT + "% of class with many Attributes : "
			+ CustomASTVisitor.getPercentClassWithManyAttributes().toString());

		System.out.println("classWithManyMethodsAndAttributes : "
			+ CustomASTVisitor.getClassWithManyMethodsAndAttributes().toString());

		System.out.println("class With More Than " + X + " Methods : "
			+ CustomASTVisitor.getClassWithMoreThanXMethods().toString());
		System.out.println(PERCENT + "% of Methods with largest code (by number of line) : "
			+ CustomASTVisitor.getPercentMethodsWithLargestCode().toString());

		System.out.println("maximumMethodParameter : " + CustomASTVisitor.getMaximumMethodParameter());

		System.out.println("classesReferences : " + CustomASTVisitor.getClassesReferences());

		System.out.println("projectClass : " + ClassVisitor.getProjectClass());

		GraphAST frame = new GraphAST(astParserExample.getCustomASTVisitor().getTreeStructures());
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setSize(800, 740);
		frame.setVisible(true);

	}
}