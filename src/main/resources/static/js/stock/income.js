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

    // 팝업창을 띄우는 함수
/*    function showPopup(itemId) {
        // 여기서 itemId를 사용해 추가 데이터를 요청할 수 있습니다
        // 예시로 팝업에 기본 메시지를 넣겠습니다.
        var popupContent = `<p>아이템 ID: ${itemId}</p><p>아이템에 대한 추가 정보를 여기에 표시합니다.</p>`;
        
        // 팝업창 표시
        $('#popup').html(popupContent).show();
    }

    // 팝업창 닫기
    $('#close-popup').on('click', function() {
        $('#popup').hide();
    });*/
});
