/**
 * 
 */

$(function() {
            $('#id_search').quicksearch('table tr.show-modal', {
                'stripeRows':['odd','even'],
                'loader':'span.loading',       // ローディング画像（オプション）
                'noResults': 'tr#noresults',   // 「結果なし」の表示（オプション）
                'bind':'keyup click change',   // どのタイミングでフィルタ処理するか
            });
});


