package de.fhws.applab.mapreduce;

import com.hazelcast.core.Hazelcast;
import com.hazelcast.core.HazelcastInstance;
import com.hazelcast.core.ICompletableFuture;
import com.hazelcast.core.IMap;
import com.hazelcast.mapreduce.*;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.Map;
import java.util.StringTokenizer;

public class Main
{
	private static long startTime, stopTime;

	public static void main( String[] args )
	{
		System.out.println( "Map-Reduce Main node" );

		HazelcastInstance hazelcastInstance = Hazelcast.newHazelcastInstance( );

		initializeMap( hazelcastInstance );

		ICompletableFuture<Map<String, Long>> future = startMapReduce( hazelcastInstance );
		startTime = System.currentTimeMillis( );

		waitForResult( future );

		stopTime = System.currentTimeMillis( );

		System.out.println( "Duration: " + ( stopTime - startTime ) );
	}

	private static void waitForResult( ICompletableFuture<Map<String, Long>> future )
	{
		try
		{
			Map<String, Long> result = future.get( );

			//System.out.println( "Result: " + result );
		}
		catch ( Exception e )
		{
			e.printStackTrace( );
		}
	}

	private static ICompletableFuture<Map<String, Long>> startMapReduce( HazelcastInstance hazelcastInstance )
	{
		IMap<String, String> map = hazelcastInstance.getMap( "input" );
		KeyValueSource<String, String> source = KeyValueSource.fromMap( map );

		JobTracker jobTracker = hazelcastInstance.getJobTracker( "mapreduce" );
		Job<String, String> job = jobTracker.newJob( source );

		return job
			.mapper( new TokenizerMapper( ) )
			.combiner( new WordCountCombinerFactory( ) )
			.reducer( new WordCountReducerFactory( ) )
			.submit( );
	}

	private static void initializeMap( HazelcastInstance hazelcastInstance )
	{
		IMap<String, String> data = hazelcastInstance.getMap( "input" );
		int indexOfKey = 0;

		InputStream inputStream = Main.class.getResourceAsStream( "/kant-krv-utf8.txt" );
		String line;

		try
		{
			BufferedReader br = new BufferedReader( new InputStreamReader( inputStream ) );

			while ( ( line = br.readLine( ) ) != null )
			{
				String theKey = "KEY" + indexOfKey++;
				if ( line.trim( ).isEmpty( ) == false )
				{
					//System.out.println( theKey + " -> " + line );
					data.put( theKey, line );
				}
			}
		}
		catch ( Exception e )
		{
			e.printStackTrace( );
		}
		finally
		{
			try
			{
				inputStream.close( );
			}
			catch ( Exception e )
			{
				e.printStackTrace( );
			}
		}
	}

	static class TokenizerMapper implements Mapper<String, String, String, Long>
	{
		private static final Long ONE = Long.valueOf( 1L );

		@Override
		public void map( String key, String document, Context<String, Long> context )
		{
			StringTokenizer tokenizer = new StringTokenizer( document.toLowerCase( ) );
			while ( tokenizer.hasMoreTokens( ) )
			{
				String nextToken = tokenizer.nextToken( );
				String trimmed = nextToken.replaceAll( "[\\.,()\\*]", "" );
				context.emit( trimmed, ONE );
			}
		}
	}

	static class WordCountCombinerFactory
		implements CombinerFactory<String, Long, Long>
	{

		@Override
		public Combiner<Long, Long> newCombiner( String key )
		{
			return new WordCountCombiner( );
		}

		private class WordCountCombiner extends Combiner<Long, Long>
		{
			private long sum = 0;

			@Override
			public void combine( Long value )
			{
				sum++;
			}

			@Override
			public Long finalizeChunk( )
			{
				return sum;
			}

			@Override
			public void reset( )
			{
				sum = 0;
			}
		}
	}

	static class WordCountReducerFactory implements ReducerFactory<String, Long, Long>
	{

		@Override
		public Reducer<Long, Long> newReducer( String key )
		{
			return new WordCountReducer( );
		}

		private class WordCountReducer extends Reducer<Long, Long>
		{
			private volatile long sum = 0;

			@Override
			public void reduce( Long value )
			{
				sum += value.longValue( );
			}

			@Override
			public Long finalizeReduce( )
			{
				return sum;
			}
		}
	}

}
