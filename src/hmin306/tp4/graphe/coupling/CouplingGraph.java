package hmin306.tp4.graphe.coupling;

import java.util.Map;

import javax.swing.JFrame;

import com.mxgraph.swing.mxGraphComponent;
import com.mxgraph.view.mxGraph;

import hmin306.tp4.structure.coupling.CouplingNode;
import hmin306.tp4.structure.coupling.CouplingStructure;
import hmin306.tp4.structure.tree.ClassTree;
import hmin306.tp4.structure.tree.MethodTree;

public class CouplingGraph extends JFrame
{
	private static final long serialVersionUID = -2707712944901661771L;

	private static final int	ROOT_X	= 200;
	private static final int	ROOT_Y	= 20;

	private static final int DEFAULT_SPACE = 100;

	private static final int	CLASS_WIDTH	= 350;
	private static final int	CLASS_HEIGHT	= 100;


	private mxGraph	graph;
	private Object		parent;

	public CouplingGraph(String className, ClassTree treeStructure, CouplingStructure couplingStructure)
	{
		super("CouplingGraphe : " + className);

		graph = new mxGraph();
		parent = graph.getDefaultParent();

		graph.getModel().beginUpdate();
		int xCounter = 0;
		int yCounter = 0;

		for(Map.Entry<String, MethodTree> classEntry : treeStructure.classTree.entrySet())
		{

			Object classFigure = graph.insertVertex(parent, null, classEntry.getKey(), ROOT_X + xCounter,
				ROOT_Y + yCounter, CLASS_WIDTH, CLASS_HEIGHT, "fillColor=#b3e6ff");
		
			couplingStructure.addFigure(classEntry.getKey(), classFigure);
			
			xCounter += DEFAULT_SPACE;
			yCounter += DEFAULT_SPACE;
		}

		connect(couplingStructure);

		graph.setAllowDanglingEdges(false);
		graph.setEdgeLabelsMovable(true);
		graph.setConnectableEdges(false);

		graph.setCellsDeletable(false);
		graph.setCellsCloneable(false);
		graph.setCellsDisconnectable(false);
		graph.setDropEnabled(false);
		graph.setSplitEnabled(false);
		graph.setDisconnectOnMove(false);

		graph.setCellsBendable(true);

		graph.getModel().endUpdate();

		mxGraphComponent graphComponent = new mxGraphComponent(graph);
		getContentPane().add(graphComponent);
	}

	public void connect(CouplingStructure couplingStructure)
	{
		for(CouplingNode couplingNode : couplingStructure.couplingNodes)
			graph.insertEdge(parent, null, couplingNode.counter + " / " + couplingStructure.totalReferences, couplingNode.classFigureA,
				couplingNode.classFigureB);

	}
}
