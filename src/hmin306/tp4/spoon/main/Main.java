package hmin306.tp4.spoon.main;

import java.io.IOException;

import javax.swing.JFrame;

import hmin306.tp4.graphe.GraphAST;
import hmin306.tp4.spoon.example.SpoonExample;

public class Main
{
	private final static String	WINDOB_PROJECT_JAPSCANDOWNLOADER_PROJECT	= "D:\\workspace\\JapScanDownloader\\src";
	private final static String	WINDOB_PROJECT_SELF_PROJECT	= ".\\src";

	public static void main(String[] Args) throws IOException
	{		
		SpoonExample<Void> spoonExample = new SpoonExample<Void>(WINDOB_PROJECT_JAPSCANDOWNLOADER_PROJECT);
		
		spoonExample.analyse();
		
		//System.out.println(spoonExample.getClassTree().toString());
		
		GraphAST frame = new GraphAST(spoonExample.getClassTree());
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setSize(800, 740);
		frame.setVisible(true);
		
	}
}
