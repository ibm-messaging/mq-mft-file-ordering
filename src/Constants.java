/**
 * 
 */
package com.ibm.mqmft.exit.fileordering;

/**
 * © Copyright IBM Corporation 2010, 2016
 * 2010: Original release, IBM-internal
 * 2016: Release 3.1, external
 * 2018: Release 3.3, external
 * author: Steve Parsons (sparsons@us.ibm.com, sparsons@sparsons.net)
 *
 */

public final class Constants {
	//Misc
	public static final String COLON = ": " ;
	public static final String COMMA = ", " ;
	public static final String EMPTY_STRING = "";
	public static final String ESCAPED_PERIOD = "\\." ;
	public static final String FILETYPE = "com.ibm.wmqfte.FileType" ;
	public static final String FORWARD_SLASH = "/" ;
	public static final String PERIOD = ". " ;
	public static final String PERIOD_ASTERISK = ".*" ;
	public static final String QUEUE = "queue" ;
	public static final String SEPARATOR = "file.separator" ;
	//Comparator return values
	public static final int ZERO = 0;
	public static final int ONENEG = -1;
	public static final int ONEPOS = 1;
	//FTE user metadata keys
	public static final String FIRST = "mqmft.fileordering.First";
	public static final String FIRST_SORTBY1 = "mqmft.fileordering.FirstSortBy1";
	public static final String FIRST_SORTBY1_ORDER = "mqmft.fileordering.FirstSortBy1Order";
	public static final String FIRST_SORTBY2 = "mqmft.fileordering.FirstSortBy2";
	public static final String FIRST_SORTBY2_ORDER = "mqmft.fileordering.FirstSortBy2Order";
	public static final String LAST = "mqmft.fileordering.Last";
	public static final String LAST_SORTBY1 = "mqmft.fileordering.LastSortBy1";
	public static final String LAST_SORTBY1_ORDER = "mqmft.fileordering.LastSortBy1Order";
	public static final String LAST_SORTBY2 = "mqmft.fileordering.LastSortBy2";
	public static final String LAST_SORTBY2_ORDER = "mqmft.fileordering.LastSortBy2Order";
	public static final String SORTBY1 = "mqmft.fileordering.SortBy1";
	public static final String SORTBY2 = "mqmft.fileordering.SortBy2";
	public static final String SORTBY1_ORDER = "mqmft.fileordering.SortBy1Order";
	public static final String SORTBY2_ORDER = "mqmft.fileordering.SortBy2Order";
	public static final String TOGGLE = "mqmft.fileordering.Toggle";
	//FTE user metadata values
	public static final String LAST_MODIFIED = "LastModified";
	public static final String SIZE = "Size";
	public static final String NAME = "Name";
	public static final String PATH = "Path";
	public static final String ASCENDING = "Ascending";
	public static final String DESCENDING = "Descending";
	public static final String ON = "On" ;
	public static final String QUESTION_MARK = "?" ;
	public static final String ASTERISK = "*" ;
	//Error messages
	public static final String INVALID_POSITION_INSTRUCTIONS = "com.ibm.mqmft.exit.fileordering.FileOrderingSourceStartMqmftExit: Transfer cancelled.  Invalid positioning instructions detected in user metadata.  Refer to the exit documentation for an explanation of valid positioning instructions.   Prior log publications for this transfer will show the metadata keys and values in use." ;
	public static final String INVALID_POSITION_INSTRUCTION_VALUES = "com.ibm.mqmft.exit.fileordering.FileOrderingSourceStartMqmftExit: Transfer cancelled.  Invalid positioning instructions detected in user metadata.  The characters * or ? alone are not valid values for First and Last.  Refer to the exit documentation.  Prior log publications for this transfer will show the metadata keys and values in use." ; 
	public static final String INVALID_SORTING_INSTRUCTIONS = "com.ibm.mqmft.exit.fileordering.FileOrderingSourceStartMqmftExit: Transfer cancelled.  Invalid sorting instructions detected in user metadata.  Refer to the exit documentation for an explanation of valid sorting instructions.  Prior log publications for this transfer will show the metadata keys and values in use." ;  
	public static final String NOTHING_TO_DO = "com.ibm.mqmft.exit.fileordering.FileOrderingSourceStartMqmftExit: Bypassing this exit because the transfer set is empty."   ;
	public static final String MESSAGE_TO_FILE_ONLY = "com.ibm.mqmft.exit.fileordering.FileOrderingSourceStartMqmftExit: Bypassing this exit because only message-to-file transfers were found in the transfer set.  This exit has no effect on message-to-file transfers." ;  
	public static final String REQUIRED_KEYS_MISSING = "com.ibm.mqmft.exit.fileordering.FileOrderingSourceStartMqmftExit: Transfer cancelled.  The required keys were not found in the user metadata.  Refer to the exit documentation.  Prior log publications for this transfer will show the metadata keys and values in use." ;
	public static final String TOGGLE_NOT_ON = "com.ibm.mqmft.exit.fileordering.FileOrderingSourceStartMqmftExit: Bypassing this exit because Toggle was not set to On in the user metadata." ;
	
	// noninstantiable
	  private Constants(){
		    throw new AssertionError();
		  }
}
