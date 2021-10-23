
$("#public").click(function() {
	if ($("#public-dropdown").hasClass('disable')) {
		$(".dropdown").addClass('disable');
		$("#public-dropdown").slideDown();
		$("#public-dropdown").removeClass('disable');
	} else {
		$("#public-dropdown").slideUp();
		$("#public-dropdown").addClass('disable');
	}
});

$("#protected").click(function() {
	if ($("#protected-dropdown").hasClass('disable')) {
		$(".dropdown").addClass('disable');
		$("#protected-dropdown").slideDown();
		$("#protected-dropdown").removeClass('disable');
	} else {
		$("#protected-dropdown").slideUp();
		$("#protected-dropdown").addClass('disable');
	}
});

$("#customized-list").click(function() {
	if ($("#customized-list-dropdown").hasClass('disable')) {
		$(".dropdown").addClass('disable');
		$("#customized-list-dropdown").slideDown();
		$("#customized-list-dropdown").removeClass('disable');
	} else {
		$("#customized-list-dropdown").slideUp();
		$("#customized-list-dropdown").addClass('disable');
	}
});