function customTask() {
	// Show graph link
	jQuery("a[onclick='showGraph(\'\');return false;']").parent().attr("style", "display:none");
	
	// Users links
	$userLink = jQuery("div.UserSuggestionInput > a");
	$userLink.attr("style","pointer-events:none; cursor:default;");
	$userLink.attr("href","javascript:void(0)");

	$creatorLink = jQuery("span.creator > a");
	$creatorLink.attr("style","pointer-events:none; cursor:default;");
	$creatorLink.attr("href","javascript:void(0)");
	
}

window.addEventListener("load", customTask);