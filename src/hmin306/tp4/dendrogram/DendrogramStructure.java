package hmin306.tp4.dendrogram;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.Comparator;

import hmin306.tp4.structure.coupling.CouplingNode;
import hmin306.tp4.structure.coupling.CouplingStructure;

public class DendrogramStructure
{
	public ArrayList<CouplingNode> availableNodes = new ArrayList<CouplingNode>();
	
	public Collection<MyNode> myNodes = new ArrayList<MyNode>();
	
	public Collection<String> usedClassName = new ArrayList<String>();
	
	public DendrogramStructure(CouplingStructure couplingStructure)
	{
		Collections.sort(couplingStructure.couplingNodes, new Comparator<CouplingNode>() {
		        @Override
		        public int compare(CouplingNode couplingNode1, CouplingNode couplingNode2)
		        {
		     	   if(couplingNode1.counter == couplingNode2.counter)
		     	   {
		     		  if(couplingNode1.classNameA.equals(couplingNode2.classNameA))
		     		  {
		     			  return couplingNode2.classNameB.compareTo(couplingNode1.classNameB);
		     		  }
		     		  return couplingNode2.classNameA.compareTo(couplingNode1.classNameA);
		     	   }
		     	   return couplingNode2.counter - couplingNode1.counter;
		        }
		    });
		
		availableNodes.addAll(couplingStructure.couplingNodes);
		
		
		System.out.println(couplingStructure.couplingNodes);
	}
}
