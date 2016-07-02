package com.ibm.mqmft.exit.fileordering;

/**
 * © Copyright IBM Corporation 2010, 2016
 * 2010: Original release, IBM-internal
 * 2016: Version 3.0, external
 * author: Steve Parsons (sparsons@us.ibm.com, sparsons@sparsons.net)
 *
 */

import java.io.File;


public final class FileSorterFactory {
	
	//const
	private FileSorterFactory() {  throw   new UnsupportedOperationException(  "no instances could be created");  }  
	
	
	public static FileSorter getFileSorter(SortingChoice sc){
		FileSorter fs = null ;
		switch (sc) {
		
			case LASA:
				fs = new FileSorterLASA();
				break;
			case LASD:
				fs = new FileSorterLASD();
				break;
			case LAPA:
				fs = new FileSorterLAPA();
				break;	
			case LAPD:
				fs = new FileSorterLAPD();
				break;	
				
			case LDSA:
				fs = new FileSorterLDSA();
				break;
			case LDSD:
				fs = new FileSorterLDSD();
				break;
			case LDPA:
				fs = new FileSorterLDPA();
				break;	
			case LDPD:
				fs = new FileSorterLDPD();
				break;	
				
			case SALA:
				fs = new FileSorterSALA();
				break;
			case SALD:
				fs = new FileSorterSALD();
				break;
			case SAPA:
				fs = new FileSorterSAPA();
				break;	
			case SAPD:
				fs = new FileSorterSAPD() ;
				break;		
				
			case SDLA:
				fs = new FileSorterSDLA();
				break;
			case SDLD:
				fs = new FileSorterSDLD();
				break;
			case SDPA:
				fs = new FileSorterSDPA();
				break;	
			case SDPD:
				fs = new FileSorterSDPD() ;
				break;	
				
			case NALA:
				fs = new FileSorterNALA() ;
				break;
			case NALD:
				fs = new FileSorterNALD();
				break;
			case NAPA:
				fs = new FileSorterNAPA() ;
				break;
			case NAPD:
				fs = new FileSorterNAPD();
				break;
			case NASA:
				fs = new FileSorterNASA() ;
				break;
			case NASD:
				fs = new FileSorterNASD();
				break;
				
			case NDLA:
				fs = new FileSorterNDLA() ;
				break;
			case NDLD:
				fs = new FileSorterNDLD();
				break;	
			case NDPA:
				fs = new FileSorterNDPA() ;
				break;
			case NDPD:
				fs = new FileSorterNDPD();
				break;
			case NDSA:
				fs = new FileSorterNDSA() ;
				break;
			case NDSD:
				fs = new FileSorterNDSD();
				break;
			
			default:
				break;
		}
		return fs ;
	}

	
	
	// inner Comparator classes
	private static class FileSorterLASA extends FileSorter{
	/*		Compares its two arguments for order. Returns a negative integer, zero, or a positive integer 
		as the first argument is less than, equal to, or greater than the second.*/
		public int compare(File file1, File file2){
			int result = Constants.ZERO ;
			
			Long age1 = new Long(file1.lastModified()) ;
			Long age2 = new Long(file2.lastModified()) ;
			result = age1.compareTo(age2) ;
			
			if(result == Constants.ZERO){
				Long len1 = new Long(file1.length()) ;
				Long len2 = new Long(file2.length()) ;
				
				result = len1.compareTo(len2) ;	
			}
			
			if(result == Constants.ZERO)	
				result = file1.compareTo(file2);

			return result;
		}
		

	}
	

	private static class FileSorterLASD extends FileSorter{
		/*	Compares its two arguments for order. Returns a negative integer, zero, or a positive integer 
		as the first argument is less than, equal to, or greater than the second.*/
		public int compare(File file1, File file2){
			int result = Constants.ZERO ;
			
			Long age1 = new Long(file1.lastModified()) ;
			Long age2 = new Long(file2.lastModified()) ;
			result = age1.compareTo(age2) ;
			
			if(result == Constants.ZERO){
				Long len1 = new Long(file1.length()) ;
				Long len2 = new Long(file2.length()) ;
				
				result = len2.compareTo(len1) ;	
			}
			
			if(result == Constants.ZERO)	
				result = file1.compareTo(file2);

			return result;
		}
	}
	
	private static class FileSorterLAPA extends FileSorter{
		/*	Compares its two arguments for order. Returns a negative integer, zero, or a positive integer 
		as the first argument is less than, equal to, or greater than the second.*/
			
		public int compare(File file1, File file2){
			int result = Constants.ZERO ;
			
			Long age1 = new Long(file1.lastModified()) ;
			Long age2 = new Long(file2.lastModified()) ;
			result = age1.compareTo(age2) ;
			
			if(result == Constants.ZERO){
				result = file1.getAbsolutePath().compareTo(file2.getAbsolutePath());
			}
			
			if(result == Constants.ZERO)	
				result = file1.compareTo(file2);

			return result;
		}
	}

	private static class FileSorterLAPD extends FileSorter{
		/*	Compares its two arguments for order. Returns a negative integer, zero, or a positive integer 
		as the first argument is less than, equal to, or greater than the second.*/
		public int compare(File file1, File file2){
			int result = Constants.ZERO ;
			
			Long age1 = new Long(file1.lastModified()) ;
			Long age2 = new Long(file2.lastModified()) ;
			result = age1.compareTo(age2) ;
			
			if(result == Constants.ZERO){
				result = file2.getAbsolutePath().compareTo(file1.getAbsolutePath());
			}
			
			if(result == Constants.ZERO)	
				result = file1.compareTo(file2);

			return result;
		}
	}

	private static class FileSorterLDSA extends FileSorter{
		/*	Compares its two arguments for order. Returns a negative integer, zero, or a positive integer 
		as the first argument is less than, equal to, or greater than the second.*/
		public int compare(File file1, File file2){
			int result = Constants.ZERO ;
			
			Long age1 = new Long(file1.lastModified()) ;
			Long age2 = new Long(file2.lastModified()) ;
			result = age2.compareTo(age1) ;
			
			if(result == Constants.ZERO){
				Long len1 = new Long(file1.length()) ;
				Long len2 = new Long(file2.length()) ;
				
				result = len1.compareTo(len2) ;	
			}
			
			if(result == Constants.ZERO)	
				result = file1.compareTo(file2);

			return result;
		}
	}

	private static class FileSorterLDSD extends FileSorter{
		/*	Compares its two arguments for order. Returns a negative integer, zero, or a positive integer 
		as the first argument is less than, equal to, or greater than the second.*/
		public int compare(File file1, File file2){
			int result = Constants.ZERO ;
			
			Long age1 = new Long(file1.lastModified()) ;
			Long age2 = new Long(file2.lastModified()) ;
			result = age2.compareTo(age1) ;
			
			if(result == Constants.ZERO){
				Long len1 = new Long(file1.length()) ;
				Long len2 = new Long(file2.length()) ;
				
				result = len2.compareTo(len1) ;	
			}
			
			if(result == Constants.ZERO)	
				result = file1.compareTo(file2);

			return result;
		}
	}
	
	private static class FileSorterLDPA extends FileSorter{
		/*	Compares its two arguments for order. Returns a negative integer, zero, or a positive integer 
		as the first argument is less than, equal to, or greater than the second.*/
		public int compare(File file1, File file2){
			int result = Constants.ZERO ;
			
			Long age1 = new Long(file1.lastModified()) ;
			Long age2 = new Long(file2.lastModified()) ;
			result = age2.compareTo(age1) ;
			
			if(result == Constants.ZERO){
				result = file1.getAbsolutePath().compareTo(file2.getAbsolutePath());
			}
			
			if(result == Constants.ZERO)	
				result = file1.compareTo(file2);

			return result;
		}
	}

	private static class FileSorterLDPD extends FileSorter{
		/*	Compares its two arguments for order. Returns a negative integer, zero, or a positive integer 
		as the first argument is less than, equal to, or greater than the second.*/
		public int compare(File file1, File file2){
			int result = Constants.ZERO ;
			
			Long age1 = new Long(file1.lastModified()) ;
			Long age2 = new Long(file2.lastModified()) ;
			result = age2.compareTo(age1) ;
			
			if(result == Constants.ZERO){
				result = file2.getAbsolutePath().compareTo(file1.getAbsolutePath());
			}
			
			if(result == Constants.ZERO)	
				result = file1.compareTo(file2);

			return result;
		}
	}


	private static class FileSorterSALA extends FileSorter{
		/*	Compares its two arguments for order. Returns a negative integer, zero, or a positive integer 
		as the first argument is less than, equal to, or greater than the second.*/
		public int compare(File file1, File file2){
			int result = Constants.ZERO ;
			
			Long len1 = new Long(file1.length()) ;
			Long len2 = new Long(file2.length()) ;
			
			result = len1.compareTo(len2) ;	
			
			if(result == Constants.ZERO){
				Long age1 = new Long(file1.lastModified()) ;
				Long age2 = new Long(file2.lastModified()) ;
				result = age1.compareTo(age2) ;
				
			}
			
			if(result == Constants.ZERO)	
				result = file1.compareTo(file2);

			return result;
		}
	}

	private static class FileSorterSALD extends FileSorter{
		/*	Compares its two arguments for order. Returns a negative integer, zero, or a positive integer 
		as the first argument is less than, equal to, or greater than the second.*/
		public int compare(File file1, File file2){
			int result = Constants.ZERO ;
			
			Long len1 = new Long(file1.length()) ;
			Long len2 = new Long(file2.length()) ;
			
			result = len1.compareTo(len2) ;	
			
			if(result == Constants.ZERO){
				Long age1 = new Long(file1.lastModified()) ;
				Long age2 = new Long(file2.lastModified()) ;
				result = age2.compareTo(age1) ;
				
			}
			
			if(result == Constants.ZERO)	
				result = file1.compareTo(file2);

			return result;
		}
	}
	
	private static class FileSorterSAPA extends FileSorter{
		/*	Compares its two arguments for order. Returns a negative integer, zero, or a positive integer 
		as the first argument is less than, equal to, or greater than the second.*/
		public int compare(File file1, File file2){
			int result = Constants.ZERO ;
			
			Long len1 = new Long(file1.length()) ;
			Long len2 = new Long(file2.length()) ;
			
			result = len1.compareTo(len2) ;	
			
			if(result == Constants.ZERO){
				result = file1.getAbsolutePath().compareTo(file2.getAbsolutePath());
			}
			
			if(result == Constants.ZERO)	
				result = file1.compareTo(file2);

			return result;
		}
	}

	private static class FileSorterSAPD extends FileSorter{
		/*	Compares its two arguments for order. Returns a negative integer, zero, or a positive integer 
		as the first argument is less than, equal to, or greater than the second.*/
		public int compare(File file1, File file2){
			int result = Constants.ZERO ;
			
			Long len1 = new Long(file1.length()) ;
			Long len2 = new Long(file2.length()) ;
			
			result = len1.compareTo(len2) ;	
			
			if(result == Constants.ZERO){
				result = file2.getAbsolutePath().compareTo(file1.getAbsolutePath());
			}
			
			if(result == Constants.ZERO)	
				result = file1.compareTo(file2);

			return result;
		}
	}

	
	private static class FileSorterSDLA extends FileSorter{
		/*	Compares its two arguments for order. Returns a negative integer, zero, or a positive integer 
		as the first argument is less than, equal to, or greater than the second.*/
		public int compare(File file1, File file2){
			int result = Constants.ZERO ;
			
			Long len1 = new Long(file1.length()) ;
			Long len2 = new Long(file2.length()) ;
			
			result = len2.compareTo(len1) ;	
			
			if(result == Constants.ZERO){
				Long age1 = new Long(file1.lastModified()) ;
				Long age2 = new Long(file2.lastModified()) ;
				result = age1.compareTo(age2) ;
				
			}
			
			if(result == Constants.ZERO)	
				result = file1.compareTo(file2);

			return result;
		}
	}

	private static class FileSorterSDLD extends FileSorter{
		/*	Compares its two arguments for order. Returns a negative integer, zero, or a positive integer 
		as the first argument is less than, equal to, or greater than the second.*/
		public int compare(File file1, File file2){
			int result = Constants.ZERO ;
			
			Long len1 = new Long(file1.length()) ;
			Long len2 = new Long(file2.length()) ;
			
			result = len2.compareTo(len1) ;	
			
			if(result == Constants.ZERO){
				Long age1 = new Long(file1.lastModified()) ;
				Long age2 = new Long(file2.lastModified()) ;
				result = age2.compareTo(age1) ;
				
			}
			
			if(result == Constants.ZERO)	
				result = file1.compareTo(file2);

			return result;
		}
	}

	private static class FileSorterSDPA extends FileSorter{
		/*	Compares its two arguments for order. Returns a negative integer, zero, or a positive integer 
		as the first argument is less than, equal to, or greater than the second.*/
		public int compare(File file1, File file2){
			int result = Constants.ZERO ;
			
			Long len1 = new Long(file1.length()) ;
			Long len2 = new Long(file2.length()) ;
			
			result = len2.compareTo(len1) ;	
			
			if(result == Constants.ZERO){
				
				result = file1.getAbsolutePath().compareTo(file2.getAbsolutePath()) ;
				
			}
			
			if(result == Constants.ZERO)	
				result = file1.compareTo(file2);

			return result;
		}
	}

	private static class FileSorterSDPD extends FileSorter{
		/*	Compares its two arguments for order. Returns a negative integer, zero, or a positive integer 
		as the first argument is less than, equal to, or greater than the second.*/
		public int compare(File file1, File file2){
			int result = Constants.ZERO ;
			
			Long len1 = new Long(file1.length()) ;
			Long len2 = new Long(file2.length()) ;
			
			result = len2.compareTo(len1) ;	
			
			if(result == Constants.ZERO){
				
				result = file2.getAbsolutePath().compareTo(file1.getAbsolutePath()) ;
				
			}
			
			if(result == Constants.ZERO)	
				result = file1.compareTo(file2);

			return result;
		}
	}
	
	
	private static class FileSorterNALA extends FileSorter{
		/*	Compares its two arguments for order. Returns a negative integer, zero, or a positive integer 
		as the first argument is less than, equal to, or greater than the second.*/
		public int compare(File file1, File file2){
			int result = Constants.ZERO ;
			
			String name1 = file1.getName() ;
			String name2 = file2.getName() ;
			
			result = name1.compareTo(name2) ;	
			
			if(result == Constants.ZERO){
				
				Long age1 = new Long(file1.lastModified()) ;
				Long age2 = new Long(file2.lastModified()) ;
				result = age1.compareTo(age2) ;
				
			}
			
			if(result == Constants.ZERO)	
				result = file1.compareTo(file2);

			return result;
		}
	}
	
	private static class FileSorterNALD extends FileSorter{
		/*	Compares its two arguments for order. Returns a negative integer, zero, or a positive integer 
		as the first argument is less than, equal to, or greater than the second.*/
		public int compare(File file1, File file2){
			int result = Constants.ZERO ;
			
			String name1 = file1.getName() ;
			String name2 = file2.getName() ;
			
			result = name1.compareTo(name2) ;	
			
			if(result == Constants.ZERO){
				
				Long age1 = new Long(file1.lastModified()) ;
				Long age2 = new Long(file2.lastModified()) ;
				result = age2.compareTo(age1) ;
				
			}
			
			if(result == Constants.ZERO)	
				result = file1.compareTo(file2);

			return result;
		}
	}
	
	private static class FileSorterNAPA extends FileSorter{
		/*	Compares its two arguments for order. Returns a negative integer, zero, or a positive integer 
		as the first argument is less than, equal to, or greater than the second.*/
		public int compare(File file1, File file2){
			int result = Constants.ZERO ;
			
			String name1 = file1.getName() ;
			String name2 = file2.getName() ;
			
			result = name1.compareTo(name2) ;	
			
			if(result == Constants.ZERO){
				
				result = file1.getAbsolutePath().compareTo(file2.getAbsolutePath()) ;
				
			}
			
			if(result == Constants.ZERO)	
				result = file1.compareTo(file2);

			return result;
		}
	}

	private static class FileSorterNAPD extends FileSorter{
		/*	Compares its two arguments for order. Returns a negative integer, zero, or a positive integer 
		as the first argument is less than, equal to, or greater than the second.*/
		public int compare(File file1, File file2){
			int result = Constants.ZERO ;
			
			String name1 = file1.getName() ;
			String name2 = file2.getName() ;
			
			result = name1.compareTo(name2) ;	
			
			if(result == Constants.ZERO){
				
				result = file2.getAbsolutePath().compareTo(file1.getAbsolutePath()) ;
				
			}
			
			if(result == Constants.ZERO)	
				result = file1.compareTo(file2);

			return result;
		}
	}
	
	
	
	private static class FileSorterNASA extends FileSorter{
		/*	Compares its two arguments for order. Returns a negative integer, zero, or a positive integer 
		as the first argument is less than, equal to, or greater than the second.*/
		public int compare(File file1, File file2){
			int result = Constants.ZERO ;
			
			String name1 = file1.getName() ;
			String name2 = file2.getName() ;
			
			result = name1.compareTo(name2) ;	
			
			if(result == Constants.ZERO){
				
				Long len1 = new Long(file1.length()) ;
				Long len2 = new Long(file2.length()) ;
				
				result = len1.compareTo(len2) ;	
				
			}
			
			if(result == Constants.ZERO)	
				result = file1.compareTo(file2);

			return result;
		}
	}

	private static class FileSorterNASD extends FileSorter{
		/*	Compares its two arguments for order. Returns a negative integer, zero, or a positive integer 
		as the first argument is less than, equal to, or greater than the second.*/
		public int compare(File file1, File file2){
			int result = Constants.ZERO ;
			
			String name1 = file1.getName() ;
			String name2 = file2.getName() ;
			
			result = name1.compareTo(name2) ;	
			
			if(result == Constants.ZERO){
				
				Long len1 = new Long(file1.length()) ;
				Long len2 = new Long(file2.length()) ;
				
				result = len2.compareTo(len1) ;	
				
			}
			
			if(result == Constants.ZERO)	
				result = file1.compareTo(file2);

			return result;
		}
	}
	
	
	

	private static class FileSorterNDLA extends FileSorter{
		/*	Compares its two arguments for order. Returns a negative integer, zero, or a positive integer 
		as the first argument is less than, equal to, or greater than the second.*/
		public int compare(File file1, File file2){
			int result = Constants.ZERO ;
			
			String name1 = file1.getName() ;
			String name2 = file2.getName() ;
			
			result = name2.compareTo(name1) ;	
			
			if(result == Constants.ZERO){
				
				Long age1 = new Long(file1.lastModified()) ;
				Long age2 = new Long(file2.lastModified()) ;
				result = age1.compareTo(age2) ;
				
			}
			
			if(result == Constants.ZERO)	
				result = file1.compareTo(file2);

			return result;
		}
	}
	
	private static class FileSorterNDLD extends FileSorter{
		/*	Compares its two arguments for order. Returns a negative integer, zero, or a positive integer 
		as the first argument is less than, equal to, or greater than the second.*/
		public int compare(File file1, File file2){
			int result = Constants.ZERO ;
			
			String name1 = file1.getName() ;
			String name2 = file2.getName() ;
			
			result = name2.compareTo(name1) ;	
			
			if(result == Constants.ZERO){
				
				Long age1 = new Long(file1.lastModified()) ;
				Long age2 = new Long(file2.lastModified()) ;
				result = age2.compareTo(age1) ;
				
			}
			
			if(result == Constants.ZERO)	
				result = file1.compareTo(file2);

			return result;
		}
	}
	
	private static class FileSorterNDPA extends FileSorter{
		/*	Compares its two arguments for order. Returns a negative integer, zero, or a positive integer 
		as the first argument is less than, equal to, or greater than the second.*/
		public int compare(File file1, File file2){
			int result = Constants.ZERO ;
			
			String name1 = file1.getName() ;
			String name2 = file2.getName() ;
			
			result = name2.compareTo(name1) ;	
			
			if(result == Constants.ZERO){
				
				result = file1.getAbsolutePath().compareTo(file2.getAbsolutePath()) ;
				
			}
			
			if(result == Constants.ZERO)	
				result = file1.compareTo(file2);

			return result;
		}
	}

	private static class FileSorterNDPD extends FileSorter{
		/*	Compares its two arguments for order. Returns a negative integer, zero, or a positive integer 
		as the first argument is less than, equal to, or greater than the second.*/
		public int compare(File file1, File file2){
			int result = Constants.ZERO ;
			
			String name1 = file1.getName() ;
			String name2 = file2.getName() ;
			
			result = name2.compareTo(name1) ;	
			
			if(result == Constants.ZERO){
				
				result = file2.getAbsolutePath().compareTo(file1.getAbsolutePath()) ;
				
			}
			
			if(result == Constants.ZERO)	
				result = file1.compareTo(file2);

			return result;
		}
	}
	
	private static class FileSorterNDSA extends FileSorter{
		/*	Compares its two arguments for order. Returns a negative integer, zero, or a positive integer 
		as the first argument is less than, equal to, or greater than the second.*/
		public int compare(File file1, File file2){
			int result = Constants.ZERO ;
			
			String name1 = file1.getName() ;
			String name2 = file2.getName() ;
			
			result = name2.compareTo(name1) ;	
			
			if(result == Constants.ZERO){
				
				Long len1 = new Long(file1.length()) ;
				Long len2 = new Long(file2.length()) ;
				
				result = len1.compareTo(len2) ;	
				
			}
			
			if(result == Constants.ZERO)	
				result = file1.compareTo(file2);

			return result;
		}
	}

	private static class FileSorterNDSD extends FileSorter{
		/*	Compares its two arguments for order. Returns a negative integer, zero, or a positive integer 
		as the first argument is less than, equal to, or greater than the second.*/
		public int compare(File file1, File file2){
			int result = Constants.ZERO ;
			
			String name1 = file1.getName() ;
			String name2 = file2.getName() ;
			
			result = name2.compareTo(name1) ;	
			
			if(result == Constants.ZERO){
				
				Long len1 = new Long(file1.length()) ;
				Long len2 = new Long(file2.length()) ;
				
				result = len2.compareTo(len1) ;	
				
			}
			
			if(result == Constants.ZERO)	
				result = file1.compareTo(file2);

			return result;
		}
	}




}
