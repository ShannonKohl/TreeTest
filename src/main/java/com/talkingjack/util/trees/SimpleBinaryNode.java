package com.talkingjack.util.trees;

import java.util.Iterator;

public class SimpleBinaryNode<V> implements Node<V> {
//	private String name;
	private V value;
	private SimpleBinaryNode<V> left;
	private SimpleBinaryNode<V> right;
	public V getValue() {
		return value;
	}
	public void setValue(V value) {
		this.value = value;
	}
	public SimpleBinaryNode<V> getLeft() {
		return left;
	}
	public void setLeft(SimpleBinaryNode<V> left) {
		this.left = left;
	}
	public SimpleBinaryNode<V> getRight() {
		return right;
	}
	public void setRight(SimpleBinaryNode<V> right) {
		this.right = right;
	}
	@SuppressWarnings("unchecked")
	public SimpleBinaryNode<V>[] getChildren(){
		return new SimpleBinaryNode[] { getLeft(), getRight()};
	}
	@Override
	public Iterator<Node<V>> getChildrenIterator() {
		return new SimpleBinaryNodeIterator();
	}
	
	private class SimpleBinaryNodeIterator implements Iterator<Node<V>> {
		private int node = 0;

		@Override
		public boolean hasNext() {
			return node < 1;
		}

		@Override
		public Node<V> next() {
			++node;
			if( node == 1 ){
				return getLeft();
			}else if( node == 2 ){
				return getRight();
			}
			return null;//TODO: check Iterator javadoc to see if this is correct
		}
		
	}
	
	@Override
	public String toString(){ return "Node{ value: "+getValue()+" }"; }
}
