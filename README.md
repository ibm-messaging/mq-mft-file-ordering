<p><h1><b>File Ordering MQMFT Exit: <a href="https://github.com/ibm-messaging/mq-mft-file-ordering/releases/download/v3.0/FileOrderingMqmftExit_v3.0.zip">Download here</a></b></h1></p>
<p><form action="https://github.com/ibm-messaging/mq-mft-file-ordering/releases/download/v3.0/FileOrderingMqmftExit_v3.0.zip">
    <button type="submit">Click here</button>
</form></p>
====================
This repository contains an exit for MQ Managed File Transfer (MQMFT): File Ordering MQMFT Exit.  In a transfer involving multiple files, MQMFT by default will transfer each of the files in ascending order by path name.  This exit allows the user to dynamically specify sorting instructions for MQMFT to apply when transferring files, based on the source files’ names, paths, sizes, and last modified timestamps; for example, a user might want MQMFT to move files in ascending order by size, with the smallest file arriving at the destination first and the biggest file last.  Optionally, the user can also use a positioning capability to specify input files that should be transferred first and/or last, regardless of the sorting instructions; for example, a user might wish a summary file with an extension of “.sum” to arrive before companion files that contain the details.

Thanks for checking it out! 

-- Steve Parsons

 
Pull requests                                                                                           
=============                                                                                           
Contributions to this package can be accepted under the terms of the                                    
IBM Contributor License Agreement, found in the file CLA.md of this repository.                         
                                                                                                        
When submitting a pull request, you must include a statement stating you accept the terms in CLA.md.    

