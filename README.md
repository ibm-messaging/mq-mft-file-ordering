<table><tr><td><h1><b>File Ordering MQMFT Exit</b></h1></td><td><h1><b> <a href="https://github.com/ibm-messaging/mq-mft-file-ordering/releases/download/v3.1/FileOrderingMqmftExit_v3.1.zip"><img src="DownloadButton.png"/></b></h1></td></tr></table>
====================
This repository contains an exit for MQ Managed File Transfer (MQMFT): File Ordering MQMFT Exit.  In a transfer involving multiple files, MQMFT by default will transfer each of the files in ascending order by path name.  This exit enables the user to dynamically specify sorting instructions for MQMFT to apply when transferring files, based on the source files’ names, paths, sizes, and last modified timestamps; for example, a user might want MQMFT to move files in ascending order by size, with the smallest file arriving at the destination first and the biggest file last.  Optionally, the user can also use a positioning capability to specify input files that should be transferred first and/or last, regardless of the sorting instructions; for example, a user might wish a summary file with an extension of “.sum” to arrive before accompanying files that contain the details.

See also the GitHub Pages site for this repository: https://ibm-messaging.github.io/mq-mft-file-ordering

Thanks for checking it out! 

-- Steve Parsons

 

