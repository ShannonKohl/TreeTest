package com.talkingjack.util.trees;

import java.io.FileReader;
import java.util.concurrent.Callable;

import com.google.gson.Gson;

//NOTICE: this loader converts Integer to Double, use Jackson until we can figure out how to fix this
public class GsonBinaryTreeLoader<N> implements Callable<Tree<N>> {
	private String filepath;
	public GsonBinaryTreeLoader( String jsonFilepath ){
		this.filepath = jsonFilepath;
	}
	public Tree<N> call() throws Exception {
		Gson gson = new Gson();
		try( FileReader jsonReader = new FileReader( filepath )){
			SimpleBinaryNode<N> nodes = gson.fromJson( jsonReader, SimpleBinaryNode.class );
			Tree<N> tree = new Tree<>( nodes );
			return tree;
		}
	}

}
