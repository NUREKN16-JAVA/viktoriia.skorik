package ua.nure.kn.skorik.usermanagement.agent;

import ua.nure.kn.skorik.usermanagement.db.DatabaseException;

public class SearchException extends Exception {
	
	public SearchException(DatabaseException e) {
        super(e);
    }
}
