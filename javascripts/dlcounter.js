function showJSON(data){
	return data ;
}
$(function(){
	$getJSON("https://api.github.com/repos/ibm-messaging/mq-mft-file-ordering/releases/latest", showJSON) ;
});