mq-mft-file-ordering
====================
This repository contains an exit for MQ Managed File Transfer (MQMFT): File Ordering MQMFT Exit.  In a transfer involving multiple files, MQMFT by default will transfer each of the files in ascending order by path name.  This exit allows the user to dynamically specify the order in which MQMFT transfers the files, based on the source files’ names, paths, sizes, and last modified timestamps.  Optionally, the user can also use a positioning capability to specify input files that should be transferred first and/or last, regardless of the sorting instructions; for example, a user might wish a summary file with an extension of “.sum” to arrive before companion files that contain the details.



 
Pull requests                                                                                           
=============                                                                                           
Contributions to this package can be accepted under the terms of the                                    
IBM Contributor License Agreement, found in the file CLA.md of this repository.                         
                                                                                                        
When submitting a pull request, you must include a statement stating you accept the terms in CLA.md.    

