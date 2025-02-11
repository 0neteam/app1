$(document).ready(function() {
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
                                <td>${item.orderCode}</td>
                                <td>${item.itemCode}</td>
                                <td>${item.itemName}</td>
                                <td>${item.orderQuantity}</td>
                                <td>${item.inboundQuantity}</td>
                                <td>${item.orderDate}</td>
                                <td>${item.inboundDate}</td>
                                <td>
                                    <button class="btn btn-info btn-sm view-details" data-item-id="${item.itemCode}">거래처</button>
                                </td>
								<td>${item.status}</td>
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
    }

	$("tbody tr").on("click", function() {
	    var quoNo = $(this).attr("data-quoNo");
	    var _csrf = document.querySelector('input[name="_csrf"]').value;
	    var params = {quoNo, _csrf}
	    console.log(params);

	    $.ajax({
	        url: '/quo',
	        method: 'POST',
	        data: params
	    }).done(data => {
	        console.log(data);
	        // MODAL 기능
	        // $("#supplierModal").modal("show");
	    }).fail(error => {
	        console.log(error);
	    });

	});

});
