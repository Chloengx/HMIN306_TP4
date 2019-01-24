package hmin306.tp4.dendrogram;

import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Point;
import java.util.List;

import javax.swing.JPanel;

import hmin306.tp4.structure.coupling.CouplingStructure;

public class DendrogramPaintPanel extends JPanel
{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private static <T> DendrogramNode<T> create(T contents)
	{
		return new DendrogramNode<T>(contents);
	}

	private static <T> DendrogramNode<T> create(DendrogramNode<T> child0, DendrogramNode<T> child1)
	{
		return new DendrogramNode<T>(child0, child1);
	}

	private DendrogramNode<String>	root;
	private int			leaves;
	private int			levels;
	private int			heightPerLeaf;
	private int			widthPerLevel;
	private int			currentY;
	private final int		margin	= 25;

	public DendrogramPaintPanel(CouplingStructure couplingStructure)
	{
		DendrogramStructure dendrogramStructure = new DendrogramStructure(couplingStructure);

		root = create(create(create("5"), create(create("9"), create(create("8"), create("7")))),
			create(create(create("6"), create("5")),
				create(create("4"), create(create("3"), create(create("2"), create("1"))))));
	}

	private static <T> int countLeaves(DendrogramNode<T> node)
	{
		List<DendrogramNode<T>> children = node.getChildren();
		if(children.size() == 0)
		{
			return 1;
		}
		DendrogramNode<T> child0 = children.get(0);
		DendrogramNode<T> child1 = children.get(1);
		return countLeaves(child0) + countLeaves(child1);
	}

	private static <T> int countLevels(DendrogramNode<T> node)
	{
		List<DendrogramNode<T>> children = node.getChildren();
		if(children.size() == 0)
		{
			return 1;
		}
		DendrogramNode<T> child0 = children.get(0);
		DendrogramNode<T> child1 = children.get(1);
		return 1 + Math.max(countLevels(child0), countLevels(child1));
	}

	@Override
	protected void paintComponent(Graphics gr)
	{
		super.paintComponent(gr);
		Graphics2D g = (Graphics2D) gr;

		leaves = countLeaves(root);
		levels = countLevels(root);
		heightPerLeaf = (getHeight() - margin - margin) / leaves;
		widthPerLevel = (getWidth() - margin - margin) / levels;
		currentY = 0;

		g.translate(margin, margin);
		draw(g, root, 0);
	}

	private <T> Point draw(Graphics g, DendrogramNode<T> node, int y)
	{
		List<DendrogramNode<T>> children = node.getChildren();
		if(children.size() == 0)
		{
			int x = getWidth() - widthPerLevel - 2 * margin;
			g.drawString(String.valueOf(node.getContents()), x + 8, currentY + 8);
			int resultX = x;
			int resultY = currentY;
			currentY += heightPerLeaf;
			return new Point(resultX, resultY);
		}
		if(children.size() >= 2)
		{
			DendrogramNode<T> child0 = children.get(0);
			DendrogramNode<T> child1 = children.get(1);
			Point p0 = draw(g, child0, y);
			Point p1 = draw(g, child1, y + heightPerLeaf);

			g.fillRect(p0.x - 2, p0.y - 2, 4, 4);
			g.fillRect(p1.x - 2, p1.y - 2, 4, 4);
			int dx = widthPerLevel;
			int vx = Math.min(p0.x - dx, p1.x - dx);
			g.drawLine(vx, p0.y, p0.x, p0.y);
			g.drawLine(vx, p1.y, p1.x, p1.y);
			g.drawLine(vx, p0.y, vx, p1.y);
			Point p = new Point(vx, p0.y + (p1.y - p0.y) / 2);
			return p;
		}
		// Should never happen
		return new Point();
	}
}