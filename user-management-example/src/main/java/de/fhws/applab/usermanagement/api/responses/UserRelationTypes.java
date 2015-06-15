package de.fhws.applab.usermanagement.api.responses;

import de.fhws.applab.restserverspi.api.responses.RelationTypes;

/**
 * Created by braunpet on 14.06.15.
 */
public interface UserRelationTypes extends RelationTypes
{
	String REL_TYPE_UPDATE_USER = "updateUser";

	String REL_TYPE_DELETE_USER = "deleteUser";

	String REL_TYPE_GET_ALL_USERS = "getAllUsers";

	String REL_TYPE_CREATE_USER = "createUser";




}
