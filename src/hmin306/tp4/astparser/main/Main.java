package hmin306.tp4.astparser.main;

import java.io.IOException;

import hmin306.tp4.astparser.example.ASTParserExample;
import hmin306.tp4.astparser.example.CustomASTVisitor;

public class Main
{
	private final static String PROJECT_SOURCE_FOLDER = "/home/harkame/workspace/JapScanDownloader/src";

	private final static int PERCENT = 20;

	private final static int X = 2;

	public static void main(String[] args) throws IOException
	{
		ASTParserExample astParserExample = new ASTParserExample(PROJECT_SOURCE_FOLDER);

		astParserExample.initialize();
		
		System.out.println(System.getProperty("line.separator"));
		System.out.println("--- Result --- ");
		System.out.println(System.getProperty("line.separator"));

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
		

		/*
		String className = "Main";

		for(Map.Entry<String, TreeStructure> entry : astParserExample.getTreeStructures().entrySet())
			if(entry.getKey().equals(className))
			{
				GraphAST frame = new GraphAST(entry.getValue());
				frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
				frame.setSize(800, 740);
				frame.setVisible(true);
			}
			*/
	}
}