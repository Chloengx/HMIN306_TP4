package hmin306.tp4.spoon.main;

import java.io.IOException;

import javax.swing.JFrame;

import hmin306.tp4.graphe.call.CallGraph;
import hmin306.tp4.graphe.coupling.CouplingGraph;
import hmin306.tp4.spoon.example.SpoonExample;

import hmin306.tp4.dendrogram.*;

public class SpoonMain
{
	private final static String	WINDOB_PROJECT_JAPSCANDOWNLOADER_PROJECT	= "D:\\workspace\\JapScanDownloader\\src";
	private final static String	WINDOB_PROJECT_SELF_PROJECT	= ".\\src";

	public static void main(String[] Args) throws IOException
	{		
		SpoonExample<Void> spoonExample = new SpoonExample<Void>(WINDOB_PROJECT_JAPSCANDOWNLOADER_PROJECT);
		
		spoonExample.analyse();

		//showMetrics(spoonExample);
		
		//showCallGraph(spoonExample);
		
		//showCouplingGraph(spoonExample);
		
		//showDendrogram(spoonExample);
	}
	
	public static void showMetrics(SpoonExample spoonExample)
	{
		System.out.println("classCounter : " + spoonExample.classCounter);
		System.out.println("methodCounter : " + spoonExample.methodCounter);
		System.out.println("methodAverage : " + spoonExample.methodPerClassAverage);
	}
	
	public static void showCallGraph(SpoonExample spoonExample)
	{
		CallGraph graph = new CallGraph("Spoon", spoonExample.classTree);
		graph.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		graph.setSize(800, 740);
		graph.setVisible(true);
	}
	
	public static void showCouplingGraph(SpoonExample spoonExample)
	{
		CouplingGraph graph = new CouplingGraph("Spoon", spoonExample.classTree, spoonExample.couplingStructure);
		graph.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		graph.setSize(800, 740);
		graph.setVisible(true);
	}
	
	public static void showDendrogram(SpoonExample spoonExample)
	{

        JFrame f = new JFrame();
        f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        DendrogramPaintPanel panel = new DendrogramPaintPanel(spoonExample.couplingStructure);
        f.getContentPane().add(panel);

        f.setSize(1000,800);
        f.setLocationRelativeTo(null);
        f.setVisible(true);
    }
}
