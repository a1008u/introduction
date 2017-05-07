/**
 * @classdesc header
 * @author A
 * @todo change menu size
 * 
 */
$(function () {

	// window幅によって、メニューリストの表示形式の変更
	$(window).on('load resize', checkWindowSize);

	$('.menu > a').on('click', function () {
		$('.nav-global').slideToggle(400);
	});

});

function checkWindowSize() {
	var $menubar = $('.menubar');
	var $navglobal = $menubar.find('.nav-global');
	var $menu = $menubar.find('#menu');
	var $windoWidth = parseInt($menubar.css('width'));

	if ($windoWidth < 576) {
		showMenu_hideNavglobal($menu, $navglobal);
	} else {
		showNavglobal_hideMenu($menu, $navglobal);
	}
}

/**
 * 
 * @param  {} $menu
 * @param  {} $navglobal
 */
function showMenu_hideNavglobal($menu, $navglobal) {
	$navglobal.addClass('hide');
	$menu.removeClass('hide').addClass('show');
}

/**
 * @param  {} $menu
 * @param  {} $navglobal
 */
function showNavglobal_hideMenu($menu, $navglobal) {
	$navglobal.removeClass('hide');
	$menu.removeClass('show').addClass('hide');
}