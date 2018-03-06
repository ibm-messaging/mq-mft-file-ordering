package com.ibm.mqmft.exit.fileordering;
/**
 * © Copyright IBM Corporation 2010, 2016
 * 2010: Original release, IBM-internal
 * 2016: Release 3.1, external
 * 2018: Release 3.3, external
 * author: Steve Parsons (sparsons@us.ibm.com, sparsons@sparsons.net)
 *
 */

// this enum contains the supported combinations of sorting options
public enum SortingChoice {
	

	
	LASA(Constants.LAST_MODIFIED + Constants.ASCENDING + Constants.SIZE + Constants.ASCENDING),
	LASD(Constants.LAST_MODIFIED + Constants.ASCENDING + Constants.SIZE + Constants.DESCENDING),
	LAPA(Constants.LAST_MODIFIED + Constants.ASCENDING + Constants.PATH + Constants.ASCENDING),
	LAPD(Constants.LAST_MODIFIED + Constants.ASCENDING + Constants.PATH + Constants.DESCENDING),
	
	LDSA(Constants.LAST_MODIFIED + Constants.DESCENDING + Constants.SIZE + Constants.ASCENDING),
	LDSD(Constants.LAST_MODIFIED + Constants.DESCENDING + Constants.SIZE + Constants.DESCENDING),
	LDPA(Constants.LAST_MODIFIED + Constants.DESCENDING + Constants.PATH + Constants.ASCENDING),
	LDPD(Constants.LAST_MODIFIED + Constants.DESCENDING + Constants.PATH + Constants.DESCENDING),
	
	SALA(Constants.SIZE + Constants.ASCENDING + Constants.LAST_MODIFIED + Constants.ASCENDING),
	SALD(Constants.SIZE + Constants.ASCENDING + Constants.LAST_MODIFIED + Constants.DESCENDING),
	SAPA(Constants.SIZE + Constants.ASCENDING + Constants.PATH + Constants.ASCENDING),
	SAPD(Constants.SIZE + Constants.ASCENDING + Constants.PATH + Constants.DESCENDING),
	
	SDLA(Constants.SIZE + Constants.DESCENDING + Constants.LAST_MODIFIED + Constants.ASCENDING),
	SDLD(Constants.SIZE + Constants.DESCENDING + Constants.LAST_MODIFIED + Constants.DESCENDING),
	SDPA(Constants.SIZE + Constants.DESCENDING + Constants.PATH + Constants.ASCENDING),
	SDPD(Constants.SIZE + Constants.DESCENDING + Constants.PATH + Constants.DESCENDING),
	
	NALA(Constants.NAME + Constants.ASCENDING + Constants.LAST_MODIFIED + Constants.ASCENDING),
	NALD(Constants.NAME + Constants.ASCENDING + Constants.LAST_MODIFIED + Constants.DESCENDING),
	NAPA(Constants.NAME + Constants.ASCENDING + Constants.PATH + Constants.ASCENDING),
	NAPD(Constants.NAME + Constants.ASCENDING + Constants.PATH + Constants.DESCENDING),
	NASA(Constants.NAME + Constants.ASCENDING + Constants.SIZE + Constants.ASCENDING),
	NASD(Constants.NAME + Constants.ASCENDING + Constants.SIZE + Constants.DESCENDING),
	
	NDLA(Constants.NAME + Constants.DESCENDING + Constants.LAST_MODIFIED + Constants.ASCENDING),
	NDLD(Constants.NAME + Constants.DESCENDING + Constants.LAST_MODIFIED + Constants.DESCENDING),
	NDPA(Constants.NAME + Constants.DESCENDING + Constants.PATH + Constants.ASCENDING),
	NDPD(Constants.NAME + Constants.DESCENDING + Constants.PATH + Constants.DESCENDING),
	NDSA(Constants.NAME + Constants.DESCENDING + Constants.SIZE + Constants.ASCENDING),
	NDSD(Constants.NAME + Constants.DESCENDING + Constants.SIZE + Constants.DESCENDING),
 
	;	
	
	
	private String choice ;
	private SortingChoice(String choice) {this.choice = choice ;}
	
	
	public String getChoiceValue(){return this.choice;}
	
	

}
