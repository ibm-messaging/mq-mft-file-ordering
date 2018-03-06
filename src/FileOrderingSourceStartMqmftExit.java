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
import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;
import java.util.ListIterator;
import java.util.Map;
import java.util.SortedSet;
import java.util.TreeSet;
import java.util.regex.Pattern;

import com.ibm.wmqfte.exitroutine.api.SourceFileExitFileSpecification;
import com.ibm.wmqfte.exitroutine.api.SourceTransferStartExit;
import com.ibm.wmqfte.exitroutine.api.TransferExitResult;
import com.ibm.wmqfte.exitroutine.api.TransferExitResultCode;

public class FileOrderingSourceStartMqmftExit implements SourceTransferStartExit {
	
	private SortedSet<File> alreadyAddedFiles = Collections.synchronizedSortedSet(new TreeSet<File>());

//	@Override
	public TransferExitResult onSourceTransferStart(String sourceAgentName, 
													String destinationAgentName,
													Map<String, String> environmentMetaData, 
													Map<String, String> transferMetaData,
													List<SourceFileExitFileSpecification> fileSpecs) {
		

		// flags for First and Last positioning
		boolean doFirstPositioning = false ;
		boolean doLastPositioning = false ;
		
		//will get sorting instructions from user metadata
		String sortBy1 = Constants.EMPTY_STRING;
		String sortBy2 = Constants.EMPTY_STRING;
		String sortBy1Order = Constants.EMPTY_STRING;
		String sortBy2Order = Constants.EMPTY_STRING;
		
		// sorted sets that will populate the fileSpecs in the desired order
		SortedSet<File> sortedFiles = null ; // for "middle" files
		SortedSet<File> sortedFirstFiles = null ;
		SortedSet<File> sortedLastFiles = null ;
		
		// bypass this exit if the fileSpecs is empty
		if (fileSpecs.size() == Constants.ZERO)
				return new TransferExitResult(TransferExitResultCode.PROCEED, Constants.NOTHING_TO_DO);
		
		// bypass this exit if the Toggle is not set to On
		if (!((transferMetaData.containsKey(Constants.TOGGLE)) && (transferMetaData.get(Constants.TOGGLE).equalsIgnoreCase(Constants.ON))))
				return new TransferExitResult(TransferExitResultCode.PROCEED, Constants.TOGGLE_NOT_ON);
		
		// For First positioning: test for valid instructions
		// if any of the First keys are there, they all should be there
		if(transferMetaData.containsKey(Constants.FIRST) || transferMetaData.containsKey(Constants.FIRST_SORTBY1) || transferMetaData.containsKey(Constants.FIRST_SORTBY1_ORDER)
				|| transferMetaData.containsKey(Constants.FIRST_SORTBY2) || transferMetaData.containsKey(Constants.FIRST_SORTBY2_ORDER))
			if(!(transferMetaData.containsKey(Constants.FIRST) && transferMetaData.containsKey(Constants.FIRST_SORTBY1) && transferMetaData.containsKey(Constants.FIRST_SORTBY1_ORDER)
					&& transferMetaData.containsKey(Constants.FIRST_SORTBY2) && transferMetaData.containsKey(Constants.FIRST_SORTBY2_ORDER)))
				return new TransferExitResult(TransferExitResultCode.CANCEL_TRANSFER, Constants.REQUIRED_KEYS_MISSING);
		// The characters * or ? by themselves are not valid values for First
		if(transferMetaData.containsKey(Constants.FIRST)){
			if( transferMetaData.get(Constants.FIRST).equals(Constants.ASTERISK) || transferMetaData.get(Constants.FIRST).equals(Constants.QUESTION_MARK) )
				return new TransferExitResult(TransferExitResultCode.CANCEL_TRANSFER, Constants.INVALID_POSITION_INSTRUCTION_VALUES);
			else
				doFirstPositioning = true ;
		}
		
		// For Last positioning: test for valid instructions
		// if any of the Last keys are there, they all should be there
		if(transferMetaData.containsKey(Constants.LAST) || transferMetaData.containsKey(Constants.LAST_SORTBY1) || transferMetaData.containsKey(Constants.LAST_SORTBY1_ORDER)
				|| transferMetaData.containsKey(Constants.LAST_SORTBY2) || transferMetaData.containsKey(Constants.LAST_SORTBY2_ORDER))
			if(!(transferMetaData.containsKey(Constants.LAST) && transferMetaData.containsKey(Constants.LAST_SORTBY1) && transferMetaData.containsKey(Constants.LAST_SORTBY1_ORDER)
					&& transferMetaData.containsKey(Constants.LAST_SORTBY2) && transferMetaData.containsKey(Constants.LAST_SORTBY2_ORDER)))
				return new TransferExitResult(TransferExitResultCode.CANCEL_TRANSFER, Constants.REQUIRED_KEYS_MISSING);
		// The characters * or ? by themselves are not valid values for Last
		if(transferMetaData.containsKey(Constants.LAST)) {
			if( transferMetaData.get(Constants.LAST).equals(Constants.ASTERISK) || transferMetaData.get(Constants.LAST).equals(Constants.QUESTION_MARK) )
				return new TransferExitResult(TransferExitResultCode.CANCEL_TRANSFER, Constants.INVALID_POSITION_INSTRUCTION_VALUES);
			else
				doLastPositioning = true ;
		}
		
		//If all of the required keys are not found in the user metadata, cancel the transfer.
		if (!(transferMetaData.containsKey(Constants.SORTBY1) && transferMetaData.containsKey(Constants.SORTBY2) && 
				transferMetaData.containsKey(Constants.SORTBY1_ORDER) && transferMetaData.containsKey(Constants.SORTBY2_ORDER)))
			return new TransferExitResult(TransferExitResultCode.CANCEL_TRANSFER, Constants.REQUIRED_KEYS_MISSING);
		//Else, prepare to implement the specified ordering and complete the transfer.
		else {
			sortBy1 = transferMetaData.get(Constants.SORTBY1);
			sortBy2 = transferMetaData.get(Constants.SORTBY2);
			sortBy1Order = transferMetaData.get(Constants.SORTBY1_ORDER);
			sortBy2Order = transferMetaData.get(Constants.SORTBY2_ORDER);
		}
		
		// For First positioning: set up sortedFirstFiles
		if(doFirstPositioning){
			sortedFirstFiles = setUpSortedSet(sortedFirstFiles, transferMetaData.get(Constants.FIRST_SORTBY1), 
																transferMetaData.get(Constants.FIRST_SORTBY2), 
																transferMetaData.get(Constants.FIRST_SORTBY1_ORDER), 
																transferMetaData.get(Constants.FIRST_SORTBY2_ORDER));
			if(sortedFirstFiles == null)
				return new TransferExitResult(TransferExitResultCode.CANCEL_TRANSFER, Constants.INVALID_POSITION_INSTRUCTIONS ) ;
		}
		// For Last positioning: set up sortedLastFiles
		if(doLastPositioning){
			sortedLastFiles = setUpSortedSet(sortedLastFiles, transferMetaData.get(Constants.LAST_SORTBY1), 
															  transferMetaData.get(Constants.LAST_SORTBY2), 
															  transferMetaData.get(Constants.LAST_SORTBY1_ORDER), 
															  transferMetaData.get(Constants.LAST_SORTBY2_ORDER));
			if(sortedLastFiles == null)
				return new TransferExitResult(TransferExitResultCode.CANCEL_TRANSFER, Constants.INVALID_POSITION_INSTRUCTIONS ) ;
		}
		// Always: set up sortedFiles (the "middle" files)
		sortedFiles = setUpSortedSet(sortedFiles, sortBy1, sortBy2, sortBy1Order, sortBy2Order);
		if(sortedFiles == null)
			return new TransferExitResult(TransferExitResultCode.CANCEL_TRANSFER, Constants.INVALID_SORTING_INSTRUCTIONS ) ;
		

		//Make a scratch copy of the fileSpecs and clear the original fileSpecs
		List<SourceFileExitFileSpecification> fileSpecsCopy = new ArrayList<SourceFileExitFileSpecification>(fileSpecs);
		Collections.copy(fileSpecsCopy, fileSpecs);
		fileSpecs.clear() ;
		
		//Ensure that the fileSpecsCopy does not contain transfer items that start from a queue: This exit will not apply sorting and positioning to message-to-file transfers. 
		//Handle the message-to-file items separately in queueSourceSpecs
		List<SourceFileExitFileSpecification> queueSourceSpecs = new ArrayList<SourceFileExitFileSpecification>() ;
		Iterator<SourceFileExitFileSpecification> copyIterator = fileSpecsCopy.iterator();
		while(copyIterator.hasNext()){
			SourceFileExitFileSpecification spec = copyIterator.next();
			Map<String,String> md = spec.getSourceFileMetaData() ;
			if( (md.get(Constants.FILETYPE)).equals(Constants.QUEUE) )
				queueSourceSpecs.add(spec);
		}
		Iterator<SourceFileExitFileSpecification> qsIterator = queueSourceSpecs.iterator();
		while(qsIterator.hasNext())
			fileSpecsCopy.remove(qsIterator.next());	
		
		
		//Exit without positioning or sorting if the transfer set contains only message-to-file transfers.
		if( (fileSpecsCopy.isEmpty()) & (! queueSourceSpecs.isEmpty()) ) {
			Iterator<SourceFileExitFileSpecification> queueIterator = queueSourceSpecs.iterator();
			while(queueIterator.hasNext())
				fileSpecs.add(queueIterator.next());
			return new TransferExitResult(TransferExitResultCode.PROCEED, Constants.MESSAGE_TO_FILE_ONLY);
		}
		
		/* Populate the sorted sets */
		// For First positioning
		if(doFirstPositioning)
			 sortedFirstFiles = populateSortedSet(transferMetaData.get(Constants.FIRST), sortedFirstFiles, fileSpecsCopy);
		// For Last positioning
		if(doLastPositioning)
			 sortedLastFiles = populateSortedSet(transferMetaData.get(Constants.LAST), sortedLastFiles, fileSpecsCopy);
		// Always: for "middle" files
		sortedFiles = populateSortedSet(Constants.EMPTY_STRING, sortedFiles, fileSpecsCopy);
		

		/*Update the original copy of fileSpecs to reflect the sorted Sets, putting the members of fileSpecs 
		in the desired order, to propagate at the end of this exit.  it is important to call updateFileSpecs in this order: 1) First; 2) Middle; and, 3) Last */
		if(doFirstPositioning)
			fileSpecs = updateFileSpecs(fileSpecs, sortedFirstFiles, fileSpecsCopy) ;
		// always: For "middle" files
		fileSpecs = updateFileSpecs(fileSpecs, sortedFiles, fileSpecsCopy) ;
		// add the message-to-file transfers, either last or penultimate
		if(! queueSourceSpecs.isEmpty()){
			Iterator<SourceFileExitFileSpecification> qIterator = queueSourceSpecs.iterator() ;
			while(qIterator.hasNext())
				fileSpecs.add(qIterator.next()) ;
		}
		if(doLastPositioning)
			fileSpecs = updateFileSpecs(fileSpecs, sortedLastFiles, fileSpecsCopy) ;
		
/*		//print the sorted list
	    Iterator iterator = sortedFiles.iterator();
	    while (iterator.hasNext()) {
	      File file = (File)iterator.next();
	      System.out.println("File name: " + file.getName() + " File last mod: " + file.lastModified() + " File length: " + file.length());
	    }*/
		
		
		// return with specified sorting and positioning applied and no explanation message
		return TransferExitResult.PROCEED_RESULT;
	}


		// update the fileSpecs
		private List<SourceFileExitFileSpecification> updateFileSpecs (
				List<SourceFileExitFileSpecification> fs,
				SortedSet<File> sortedSet,
				List<SourceFileExitFileSpecification> fsCopy)  {
			// for each file in the sorted order...
			String sep = System.getProperty(Constants.SEPARATOR);
			Iterator<File> sortedFileList = sortedSet.iterator();
			while (sortedFileList.hasNext()) {
				File fileToOrder = sortedFileList.next();
				// fix on 22 Sep 2014 v2.1)
				String fileToOrderName = fileToOrder.getAbsolutePath().replaceAll(Pattern.quote(File.separator), Constants.FORWARD_SLASH) ;
				ListIterator<SourceFileExitFileSpecification> fileListCopy = fsCopy.listIterator();
				while (fileListCopy.hasNext()) {
					SourceFileExitFileSpecification fileSpec = fileListCopy.next();
					// ... find it in the scratch copy of fileSpecs and add it back to the original fileSpecs
					if (fileSpec.getSource().replaceAll(Pattern.quote(sep),Constants.FORWARD_SLASH).equals(fileToOrderName)) {
						fs.add(fileSpec);
						break;
					}
				}
			}
			
			return fs;
		}

		

		private SortedSet<File> populateSortedSet(String glob,
				SortedSet<File> sortedSet,
				List<SourceFileExitFileSpecification> fileSpecsCopy) {	
			ListIterator<SourceFileExitFileSpecification> fileList = fileSpecsCopy.listIterator();
			while (fileList.hasNext()) {
				SourceFileExitFileSpecification fileSpec = fileList.next();
				// notice: the fileName value will include the whole path (e.g. /dir1/dir2/dir3/filename.ext), so the glob must accommodate the whole path (e.g. *filena*.ext)
				String fileName = fileSpec.getSource();
				File newFile = new File(fileName);
				// if the file has not already been added to one of the three sorted sets
				if(! alreadyAddedFiles.contains(newFile)){
					// if we are doing first and/or last positioning
					if(! glob.equals(Constants.EMPTY_STRING)){
						if (fileName.matches(convertGlobToRegEx(glob))){
							sortedSet.add(newFile);
							alreadyAddedFiles.add(newFile) ;
						}
					}
					// for "middle" files
					else {
						sortedSet.add(newFile);
						alreadyAddedFiles.add(newFile) ;
					}
				}
			}		
			return sortedSet;
		}
	
	

		private SortedSet<File> setUpSortedSet(SortedSet<File> sortedSet,
				String sortOn1, String sortOn2, String sortOn1Order,
				String sortOn2Order) {
			// check whether the combination of sorting options is valid
			String sortingRequest = sortOn1 + sortOn1Order + sortOn2 + sortOn2Order;
			SortingChoice sortingChoice = isSupportedSortingRequest(sortingRequest);
			// if a SortingChoice was not returned, exit with invalid options
			// message
			if (sortingChoice == null)
				return null;
			else {
				// else, call factory to get the right FileSorter subclass object,
				// based on the sorting choice, and feed it into the sorted set
				FileSorter fs = FileSorterFactory.getFileSorter(sortingChoice);
				sortedSet = Collections.synchronizedSortedSet(new TreeSet<File>(fs));
			}
			return sortedSet;
		}
		

		// is the input string found in the enum of supported options?  If so, return the corresponding enum SortingChoice
		private SortingChoice isSupportedSortingRequest(String sortingRequest) {
			SortingChoice result = null ;
			for(SortingChoice sc : SortingChoice.values()){
				if (sc.getChoiceValue().equals(sortingRequest)) {
			        result = sc;
			        break ;
				}
			}
			return result ;
		}
		
		// converts a glob to a reg ex
		private String convertGlobToRegEx(String globString){
			// deal with the . in file mask
			String escapedString = globString.replace(Constants.PERIOD, Constants.ESCAPED_PERIOD) ;
			return escapedString.replace(Constants.QUESTION_MARK, Constants.PERIOD).replace(Constants.ASTERISK, Constants.PERIOD_ASTERISK);	
		}
				

}
