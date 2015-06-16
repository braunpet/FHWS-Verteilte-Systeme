package de.fhws.applab.restserverspi.database;

import java.sql.Connection;
import java.sql.SQLException;

/**
 * Created by braunpet on 16.06.15.
 */
public interface IPersistency
{
	Connection getConnection( ) throws SQLException;
}
