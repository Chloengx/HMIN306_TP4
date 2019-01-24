package hmin306.tp4.dendrogram;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class DendrogramNode<T> 
{
    public final T contents;
    public final List<DendrogramNode<T>> children;

    DendrogramNode(T contents)
    {
        this.contents = contents;
        this.children = Collections.emptyList();
    }

    DendrogramNode(DendrogramNode<T> child0, DendrogramNode<T> child1)
    {
        this.contents = null;

        List<DendrogramNode<T>> list = new ArrayList<DendrogramNode<T>>();
        list.add(child0);
        list.add(child1);
        this.children = Collections.unmodifiableList(list);
    }

    public T getContents()
    {
        return contents;
    }

    public List<DendrogramNode<T>> getChildren()
    {
        return Collections.unmodifiableList(children);
    }
}