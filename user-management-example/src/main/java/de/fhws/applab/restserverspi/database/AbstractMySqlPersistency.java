/*
 * Copyright (c) peter.braun@fhws.de
 *
 *  Licensed under the Apache License, Version 2.0 (the "License");
 *  you may not use this file except in compliance with the License.
 *  You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 *  Unless required by applicable law or agreed to in writing, software
 *  distributed under the License is distributed on an "AS IS" BASIS,
 *  WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 *  See the License for the specific language governing permissions and
 *  limitations under the License.
 */

package de.fhws.applab.restserverspi.database;

import com.mchange.v2.c3p0.ComboPooledDataSource;
import de.fhws.applab.restserverspi.database.config.MySqlConfig;
import de.fhws.applab.restserverspi.database.tables.AbstractTable;
import de.fhws.applab.usermanagement.database.config.DefaultMySqlConfig;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.LinkedList;
import java.util.List;
import java.util.Set;

public abstract class AbstractMySqlPersistency implements IPersistency
{
	private static final String COM_MYSQL_JDBC_DRIVER = "com.mysql.jdbc.Driver";
	private static String DATABASE_PORT = "3306";

	protected static AbstractMySqlPersistency instance;

	private MySqlConfig configuration;

	private ComboPooledDataSource cpds;

	public AbstractMySqlPersistency( )
	{
		this( new DefaultMySqlConfig( ) );
	}

	public AbstractMySqlPersistency( boolean deleteDatabase )
	{
		this( new DefaultMySqlConfig( ), deleteDatabase );
	}

	public AbstractMySqlPersistency( MySqlConfig configuration )
	{
		this( configuration, false );
	}

	public AbstractMySqlPersistency( MySqlConfig configuration, final boolean deleteDatabase )
	{
		this.configuration = configuration;
		createConnectionPool( );
		createAllTables( deleteDatabase );
	}

	@Override public final void shutdown( )
	{
		this.close( );
	}

	private void close( )
	{
		this.cpds.close( );
	}

	private void createAllTables( boolean deleteDatabase )
	{
		Connection con = null;
		final List<AbstractTable> tables = new LinkedList<AbstractTable>( );
		tables.addAll( getAllTables( ) );

		try
		{
			con = getConnection( );

			for ( AbstractTable table : tables )
			{
				table.prepareTable( deleteDatabase, con );
			}
		}
		catch ( Exception e )
		{
			e.printStackTrace( );
		}
		finally
		{
			if ( con != null )
			{
				try
				{
					con.close( );
				}
				catch ( Exception e )
				{
					e.printStackTrace( );
				}
			}
		}
	}

	protected abstract Set<AbstractTable> getAllTables( );

	protected abstract String getDatabaseName( );

	@Override public final Connection getConnection( ) throws SQLException
	{
		return this.cpds.getConnection( );
	}

	private void createConnectionPool( )
	{
		try
		{
			Class.forName( COM_MYSQL_JDBC_DRIVER );
			this.cpds = new ComboPooledDataSource( );
			cpds.setDriverClass( COM_MYSQL_JDBC_DRIVER );
			cpds.setJdbcUrl(
				"jdbc:mysql://" + this.configuration.getDbHost( ) + ":" + DATABASE_PORT + "/" + getDatabaseName( ) );
			cpds.setUser( this.configuration.getDbUser( ) );
			cpds.setPassword( this.configuration.getDbPassword( ) );
			cpds.setTestConnectionOnCheckout( true );
			cpds.setMinPoolSize( 5 );
			cpds.setAcquireIncrement( 5 );
			cpds.setMaxPoolSize( 50 );
		}
		catch ( Exception e )
		{
			System.out.println( "MySQL Database Driver not found" );
			this.cpds = null;
		}
	}
}
