package com.talkingjack.util.trees;

import java.util.Iterator;

public interface Node<V> {
//	public String getName();
	public Iterator<Node<V>> getChildrenIterator();
	public V getValue();
}
