package com.ibm.mqmft.exit.fileordering;

/**
 * © Copyright IBM Corporation 2010, 2016
 * 2010: Original release, IBM-internal
 * 2016: Version 3.0, external
 * author: Steve Parsons (sparsons@us.ibm.com, sparsons@sparsons.net)
 *
 */

import java.io.File;
import java.util.Comparator;

public class FileSorter implements Comparator<File> {
	protected int compareResult ;
	
	public int compare(File file1, File file2){
		return compareResult ;
	}
	
		
}
