package com.talkingjack.test.GraphTest;

import com.talkingjack.util.logging.LoggingPredicateDecorator;
import com.talkingjack.util.trees.*;


/**
 * Hello world!
 *
 */
public class App 
{
    public static void main( String[] args ) throws Throwable
    {
        Tree<Integer> btree = new JacksonBinaryTreeLoader<Integer>( "src/main/resources/btree.json").call();
        
        final int value = 6;
        final int value2 = 2;
        
        LoggingPredicateDecorator<Node<Integer>> logger = new LoggingPredicateDecorator<>( "visiting node: {}", App::logArgExtractor, node -> node.getValue() == value );
        
        System.out.println( "dfs called directly searching for "+value );
        btree.depthFirstSearch( logger );

        System.out.println();
        System.out.println( "dfs passed a function searching for "+value2 );
        final Node<Integer> node2 = btree.traverse( btree::depthFirstSearch, logger.decorate( node -> node.getValue() == value2 ));
        System.out.println( "foundNode: "+node2 );
        
        System.out.println();
        final int five = 5;
        System.out.println( "dfs passed a funtion with starting node searching for "+five );
        btree.traverse( node2, btree::depthFirstSearch, logger.decorate( node -> node.getValue() == five ));

        System.out.println();
        System.out.println( "bfs called directly searching for "+value );
        btree.breadthFirstSearch( logger.decorate( node -> node.getValue() == value ));
        
        System.out.println();
        System.out.println( "bfs passed a lambda searching for "+value2 );
        btree.traverse( btree::breadthFirstSearch, logger.decorate( node -> node.getValue() == value2 ));
    }
    public static Object[] logArgExtractor( Node<?> node ){
    	return new Object[]{ node.getValue()};
    }
}
