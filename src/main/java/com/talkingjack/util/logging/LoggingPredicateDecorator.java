package com.talkingjack.util.logging;

import java.util.function.Function;
import java.util.function.Predicate;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * A predicate Decorator that logs a user supplied message before the predicate is called.
 * @author shannonkohl
 *
 * @param <T>
 */
public class LoggingPredicateDecorator<T> implements Predicate<T> {
	private final String message;
	private final Function<T, Object[]> logArgsSupplier;
	private final Predicate<T> decorated;
	private final Logger log;
	
	public LoggingPredicateDecorator( String message, Function<T, Object[]> logArgsSupplier, Predicate<T> decorated ){
		this.message = message;
		this.logArgsSupplier = logArgsSupplier;
		this.decorated = decorated;
		this.log = LoggerFactory.getLogger( LoggingPredicateDecorator.class );//TODO: allow user to choose what logger to use
	}

	@Override
	public boolean test( T toTest ){
		log.info( message, logArgsSupplier.apply( toTest ));//TODO: allow user config of log level and whether to log before, after or both
		return decorated.test( toTest );
	}
	
	public LoggingPredicateDecorator<T> decorate( Predicate<T> newDecorated ){
		LoggingPredicateDecorator<T> clone = new LoggingPredicateDecorator<>( message, logArgsSupplier, newDecorated );
		return clone;
	}

}
