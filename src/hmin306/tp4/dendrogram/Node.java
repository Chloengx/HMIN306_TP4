package hmin306.tp4.dendrogram;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

class Node<T> 
{
    private final T contents;
    private final List<Node<T>> children;

    Node(T contents)
    {
        this.contents = contents;
        this.children = Collections.emptyList();
    }

    Node(Node<T> child0, Node<T> child1)
    {
        this.contents = null;

        List<Node<T>> list = new ArrayList<Node<T>>();
        list.add(child0);
        list.add(child1);
        this.children = Collections.unmodifiableList(list);
    }

    public T getContents()
    {
        return contents;
    }

    public List<Node<T>> getChildren()
    {
        return Collections.unmodifiableList(children);
    }
}