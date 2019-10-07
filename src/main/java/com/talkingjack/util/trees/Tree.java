package com.talkingjack.util.trees;

import java.util.Iterator;
import java.util.Queue;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.function.BiFunction;
import java.util.function.Function;
import java.util.function.Predicate;

/**
 * A simple generic tree with depth first and breadth first searches.
 * @author shannonkohl
 *
 * @param <N>
 */
public class Tree<N> {
	private Node<N> root;
	
	public Tree( Node<N> root ){
		this.root = root;
	}
	public Node<N> traverse( Function<Predicate<Node<N>>, Node<N>> algorithm, Predicate<Node<N>> predicate ){
		return algorithm.apply( predicate );
	}
	public Node<N> traverse( Node<N> startingNode, BiFunction<Node<N>, Predicate<Node<N>>, Node<N>> algorithm, Predicate<Node<N>> predicate ){
		return algorithm.apply( startingNode, predicate );
	}
	public Node<N> depthFirstSearch( Predicate<Node<N>> query ){
		return depthFirstSearch( root, query );
	}
	public Node<N> depthFirstSearch( Node<N> node, Predicate<Node<N>> query ){
		if( node == null ){
			return null;
		}
		if( query.test( node )){
			return node;
		}
		Iterator<Node<N>> children = node.getChildrenIterator();
		while( children.hasNext()){
			Node<N> child = children.next();
			Node<N> found = depthFirstSearch( child, query );
			if( found != null ){
				return found;
		}}
		return null;
	}
	public Node<N> breadthFirstSearch( Predicate<Node<N>> query ){
		LinkedBlockingQueue<Node<N>> queue = new LinkedBlockingQueue<>();//TODO: can abstract this?
		queue.add( root );
		return breadthFirstSearch( queue, query );
	}
	public Node<N> breadthFirstSearch( Queue<Node<N>> queue, Predicate<Node<N>> query ){
		while( !queue.isEmpty()){
			Node<N> node = queue.poll();
			if( query.test( node )){
				return node;
			}
			Iterator<Node<N>> children = node.getChildrenIterator();
			while( children.hasNext()){
				Node<N> child = children.next();
				if( child != null ){
					queue.add( child );
				}
			}
		}
		return null;
	}
}
