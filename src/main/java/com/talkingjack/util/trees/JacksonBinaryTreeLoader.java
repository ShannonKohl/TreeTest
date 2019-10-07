package com.talkingjack.util.trees;

import java.io.FileReader;
import java.util.concurrent.Callable;

import com.fasterxml.jackson.databind.ObjectMapper;


public class JacksonBinaryTreeLoader<N> implements Callable<Tree<N>> {
	private String filepath;
	public JacksonBinaryTreeLoader( String jsonFilepath ){
		this.filepath = jsonFilepath;
	}
	public Tree<N> call() throws Exception {
		ObjectMapper mapper = new ObjectMapper();
		try( FileReader jsonReader = new FileReader( filepath )){
			
			@SuppressWarnings("unchecked")
			SimpleBinaryNode<N> nodes = mapper.readValue( jsonReader, SimpleBinaryNode.class );
			
			Tree<N> tree = new Tree<>( nodes );
			return tree;
		}
	}

}
