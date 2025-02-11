$(document).ready(function() {

    $("#stockEdit").on("click", () => {
        var arr = [];
        $("tbody tr").each((i, v) => {
            if($(v).find("th").find("input")[0].checked) {
                var no = $(v).find("th").find("input[name='no']").val();
                var name = $(v).find("td").eq(1).find("input").val();
                var qty = $(v).find("td").eq(2).find("input").val();;
                arr[arr.length] = {no, name, qty};
            }
        });
        console.log(arr);  // 선택된 대상 데이터 수정 목록
	    $.ajax({
	        url: '/stock/update',
	        method: 'POST',
	        contentType: 'application/json',
	        data: JSON.stringify(arr),
	        success: function(response) {
	            alert('수정 성공!');
	            location.reload(); // 새로고침하여 변경 반영
	        },
	        error: function(xhr) {
	            alert('수정 실패: ' + xhr.responseText);
	        }
	    });
	});
    });

    $("#stockDel").on("click", () => {
        var arr = [];
        $("tbody tr").each((i, v) => {
            if($(v).find("th").find("input")[0].checked) {
                var no = $(v).find("th").find("input[name='no']").val();
                var name = $(v).find("td").eq(1).find("input").val();
                var qty = $(v).find("td").eq(2).find("input").val();;
                arr[arr.length] = {no, name, qty};
            }
        });
        console.log(arr);  // 선택된 대상 데이터 삭제 목록 
		$.ajax({
		    url: '/stock/delete',
		    method: 'POST',
		    contentType: 'application/json',
		    data: JSON.stringify(arr),
		    success: function(response) {
		        alert('삭제 성공!');
		        location.reload();
		    },
		    error: function(xhr) {
		        alert('삭제 실패: ' + xhr.responseText);
		    }
		});
    });

    $("#cb_all").on("change", (e) => {
        if(e.target.checked) {
            $("tbody tr").each((i, v) => {
                $(v).find("th").find("input").attr("checked", true);
            });
        } else {
            $("tbody tr").each((i, v) => {
                $(v).find("th").find("input").attr("checked", false);
            });
        }
    });

    var loading = false;  // 데이터 로딩 중인지 여부
    var page = 1;         // 현재 페이지 번호

    // 무한 스크롤 구현
    $(window).on('scroll', function() {
        // 페이지 끝에 가까워지면
        if ($(window).scrollTop() + $(window).height() >= $(document).height() - 100) {
            if (!loading) {
                loading = true;
                page++;  // 페이지 번호 증가
                loadData(page);
            }
        }
    });

    // 데이터 불러오기
    function loadData(page) {
        $.ajax({
            url: '/searchStock', // 데이터 요청 URL
            type: 'GET',
            data: { page: page }, // 페이지 번호를 쿼리 파라미터로 전달
            success: function(response) {
                if (response.length > 0) {
                    // 데이터가 있으면 테이블에 추가
                    response.forEach(function(item) {
                        $('#data-container').append(`
                            <tr>
                                <td>${item.itemCode}</td>
                                <td>${item.itemName}</td>
                                <td>${item.qty}</td>
                            </tr>
                        `);
                    });
                    loading = false;  // 데이터 로딩 완료
                } else {
                    // 데이터가 없으면 더 이상 불러오지 않음
                    $(window).off('scroll');
                }
            },
            error: function() {
                loading = false;
                alert('데이터를 불러오는 중 오류가 발생했습니다.');
            }
        });
};
