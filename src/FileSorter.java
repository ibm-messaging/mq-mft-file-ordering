package com.ibm.mqmft.exit.fileordering;

/**
 * © Copyright IBM Corporation 2010, 2016
 * 2010: Original release, IBM-internal
 * 2016: Release 3.1, external
 * 2018: Release 3.3, external
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
