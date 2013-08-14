$(document).ready(function() {
	
	$("#denyBtn").click(function(event) {
		window.location.replace("/oauth2/denied");
	});

	$("#approveBtn").click(function(event) {
		$("#approveForm").submit();
	});

	
});