// Display the stats
function showStats(data) {

    var err = false;
    var errMessage = '';

    if(data.status == 404) {
        err = true;
        errMessage = "The project does not exist!";
    }

//    if(data.length == 0) {
        err = true;
        errMessage = "There are no releases for this project";
//    }

    var html = '';

    if(err) {
        html = "<div class='col-md-6 col-md-offset-3 error output'>" + errMessage + "</div>";
    } else {
        html += "<div class='col-md-6 col-md-offset-3 output'>";
        var latest = true;
        var totalDownloadCount = 0;

        $.each(data, function(index, item) {
            var releaseTag = item.tag_name;
            var releaseURL = item.html_url;
            var releaseAssets = item.assets;
            var hasAssets = releaseAssets.length != 0;
            var releaseAuthor = item.author;
            var publishDate = item.published_at.split("T")[0];

            if(latest) {
                html += "<div class='row release latest-release'>" +
                    "<h2><a href='" + releaseURL + "' target='_blank'>" +
                    "<span class='glyphicon glyphicon-tag'></span>&nbsp&nbsp" +
                    "Latest Release: " + releaseTag +
                    "</a></h2><hr class='latest-release-hr'>";
                latest = false;
            } else {
                html += "<div class='row release'>" +
                    "<h4><a href='" + releaseURL + "' target='_blank'>" +
                    "<span class='glyphicon glyphicon-tag'></span>&nbsp&nbsp" +
                    releaseTag +
                    "</a></h4><hr class='release-hr'>";
            }

            html += "<h4><span class='glyphicon glyphicon-info-sign'></span>&nbsp&nbsp" +
                "Release Info:</h4>";

            html += "<ul>";

            html += "<li><span class='glyphicon glyphicon-user'></span>&nbsp&nbspRelease Author: " +
                "<a href='" + releaseAuthor.html_url + "'>" + releaseAuthor.login  +"</a><br></li>";

            html += "<li><span class='glyphicon glyphicon-calendar'></span>&nbsp&nbspPublished on: " +
                publishDate + "</li>";

            html += "</ul>";
        
            if(hasAssets) {
                html += "<h4><span class='glyphicon glyphicon-download'></span>" +
                    "&nbsp&nbspDownload Info: </h4>";

                html += "<ul>";
                $.each(releaseAssets, function(index, asset) {
                    var assetSize = (asset.size / 1048576.0).toFixed(2);
                    var lastUpdate = asset.updated_at.split("T")[0];
                    html += "<li>" + asset.name + " (" + assetSize + " MiB) - Downloaded " +
                        asset.download_count + " times.<br><i>Last updated on " + lastUpdate + "</i></li>";
                    totalDownloadCount += asset.download_count;
                });
                html += "</ul>";
            }
            html += "</div>";
        });

        if(totalDownloadCount > 0) {
            totalDownloadCount = totalDownloadCount.toString().replace(/(\d)(?=(\d{3})+$)/g, '$1,');
            var totalHTML = "<div class='row total-downloads'>";
            totalHTML += "<h2><span class='glyphicon glyphicon-download'></span>" +
                "&nbsp&nbspTotal Downloads</h2> ";
            totalHTML += "<span>" + totalDownloadCount + "</span>";
            totalHTML += "</div>";
            html = totalHTML + html;
        }

        html += "</div>";
    }

    var resultDiv = $("#stats-result");
    resultDiv.hide();
    resultDiv.html(html);
  //  $("#loader-gif").hide();
  //  resultDiv.slideDown();
}

// Callback function for getting release stats
function getStats() {
   // var url = "https://api.github.com/repos/ibm-messaging/mq-mft-file-ordering/releases";
	var url = "https://api.github.com/repos/ibm-messaging/mq-mft-file-ordering/releases/latest";
    $.getJSON(url, showStats).fail(showStats);
}

// The main function
$(function() {
	getStats();
});