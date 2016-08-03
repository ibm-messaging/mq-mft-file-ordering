// see https://github.com/Somsubhra/github-release-stats

// Display the stats
function showStats(data) {

    var err = false;
    var errMessage = '';

    if(data.status == 404) {
        err = true;
        errMessage = "The project does not exist!";
    }

    if(data.length == 0) {
        err = true;
        errMessage = "There are no releases for this project";
    }

    var html = '';

    if(err) {
        html = "<div class='col-md-6 col-md-offset-3 error output'>" + errMessage + "</div>";
    } else {
        html += "<div class='col-md-6 col-md-offset-3 output'>";
        var latest = true;
        var totalDownloadCount = 0;

        $.each(data, function(index, item) {
            var releaseAssets = item.assets;
            var hasAssets = releaseAssets.length != 0;
        
            if(hasAssets) {
 
                $.each(releaseAssets, function(index, asset) {
                    totalDownloadCount += asset.download_count;
                });
         
            }
         
        });

        if(totalDownloadCount > 0) {
            totalDownloadCount = totalDownloadCount.toString().replace(/(\d)(?=(\d{3})+$)/g, '$1,');
            var totalHTML = "<div class='row total-downloads'>";
			totalHTML += "&nbsp&nbspTotal downloads: " + totalDownloadCount + "</div>";
            html = totalHTML + html;
        }

        html += "</div>";
    }

    var resultDiv = $("#stats-result");
    resultDiv.hide();
    resultDiv.html(html);
    resultDiv.slideDown();
}

// function for getting release stats
function getStats() {
    var url = "https://api.github.com/repos/ibm-messaging/mq-mft-file-ordering/releases";
    $.getJSON(url, showStats).fail(showStats);
}

// The main function
$(function() {
	getStats();
});