/*
 * モーダルウィンドウ：それを閉じない限り他の操作を受け付けなくするウィンドウ
 */
$(function(){
	/* ---------------------------------------------------------------------
	 * モーダルの実行処理
	 ---------------------------------------------------------------------*/
	
	// Ajax通信テスト ボタンクリック
	$(".show-modal").click(function() {
    	
        // ajax_output_dataを空に初期化
        $("#ajax_output_data2").text("");

		var ppp = $(this).attr('id');
        
        var userno = $("#userno",this).text();
        
        $.ajax({
			// 送信方法
            type        : "GET",
			// 送信先
            url         : "./api/Top/" + userno,
			// 取得データ
            dataType    : "json",
			// 成功
            success     : function(data) {
            				showModal(data);
                        },
			// 失敗
            error       : function(XMLHttpRequest, textStatus, errorThrown) {
                            error(XMLHttpRequest, textStatus, errorThrown);
                        }
        });
    });
	
	// $('.show-modal').on('click', showModal);
	
});

/* ---------------------------------------------------------------------
 * hideModal
 ---------------------------------------------------------------------*/
// モーダルウィンドウを閉じる
function hideModal() {
	// バックグラウンドの背景色を削除
	$('#shade').remove();
	// モーダルウィンドウの状態変更
	$('#modalwin').removeClass('show').addClass('hide');
}

/* ---------------------------------------------------------------------
 * showModal
 ---------------------------------------------------------------------*/
// モーダルウィンドウを開く
function showModal(userno, data) {
	// シェードの生成
	var $shade = $('<div></div>');

	// 閉じる用①
	$shade
	.attr('id', 'shade') //id　属性にshadeを設定(#shadeの領域を押下した場合に備える為)
	.on('click', hideModal);

	var $modalWin = $('#modalwin');
	// モーダルウィンドウを中央に配置する
	// .width()、.height()：要素の幅と高さを返す。
	// outerWidth(),.outerHeight()：paddingとborderを含めた要素の幅または高さをピクセル数で取得
	// outerWidth(true),.outerHeight(true)：marginも含める
	var posX = ($(window).width() - $modalWin.outerWidth()) / 2;
	var posY = ($(window).height() - $modalWin.outerHeight()) / 6;
	
	// 【値の設定】-------------------------------------
	$("#Userno").text("").append(userno);
	$("#Name").text("").append(data.name);
	$("#Age").text("").append(data.age);
	$("#Department").text("").append(data.department);
	$("#Club").text("").append(data.club);
	$("#Dispatchlocation").text("").append(data.dispatchlocation);
	$("#Freetext").text("").append(data.freetext);

	$modalWin.before($shade).css({left: posX, top: posY}).removeClass('hide').addClass('show');

	// 閉じる用②
	$modalWin
	.on('click', '.modal-close', function() {
		hideModal();
	})
	.on('click', 'button', function() {
		/* some function */
		hideModal();
	});
}