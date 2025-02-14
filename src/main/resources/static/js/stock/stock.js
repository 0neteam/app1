$(document).ready(function() {

	  $("#stockQty").on("change", function(e) {
	      if(e.target.value < 0) {
	          e.target.value = 0;
	      }
	  });

	  const editEvent = () => {
				
	      if (confirm("정말로 수정하시겠습니까?")) {
			  var _csrf = $("meta[name='_csrf']").attr("content");
			  var _csrf_header = $("meta[name='_csrf_header']").attr("content");
	          //var _csrf = document.querySelector('input[name="_csrf"]').value;
			  console.log("csrf : " + _csrf);
	          var stocks = []
	          $(".cb").each( (i, e) => {
	              if($(e).prop("checked")) {
	                  var row = $("#data-container tr").eq(i)[0];
	                  
					  var itemCode = row.querySelector("td:nth-child(2)").innerText; 
					  var name = row.querySelector("input[name='name']").value; 
					  var qty = row.querySelector("input[name='qty']").value; 
	                  
	                  var param = {itemCode,  name, qty}
	                  stocks[stocks.length] = param
	                  console.log(param);
	              }
	          });
	            
	            var params = { state: "edit", stocks };

	            console.log(params);

	            $.ajax({
	                url: '/stock/edit',
	                method: 'POST',
	                beforeSend: x => x.setRequestHeader(_csrf_header, _csrf),
	                dataType : 'json',
	                contentType: 'application/json; charset=utf-8',
	                data: JSON.stringify(params),
	                cache : false
	            })
	            .done(function(data) {
	                if (data.status) {					
	                    alert("업데이트 완료");
	                    location.reload();
	                } else {					
	                    alert("업데이트 오류");					
	                }
	            })
	            .fail(function(error) {
	                console.log(error);
	            });	 
	      } else {
	          location.reload();
	      }
	  }

	  // 수정 버튼 
	$("#stockEdit").on("click", editEvent);

	$("#stockDel").on("click", () => {

		if (confirm("정말로 삭제하시겠습니까?")) {

			  var _csrf = $("meta[name='_csrf']").attr("content");
			  var _csrf_header = $("meta[name='_csrf_header']").attr("content");
			  //var _csrf = document.querySelector('input[name="_csrf"]').value;
	          var stocks = []
	          $(".cb").each( (i, e) => {
	              if($(e).prop("checked")) {
	                  var row = $("#data-container tr").eq(i)[0];
	                  var itemCode = row.querySelector("td:nth-child(2)").innerText; 
	                  stocks[stocks.length] = { itemCode }
	              }
	          });
	            
	          var params = { state: "delete", stocks };

	          console.log(params);

	          $.ajax({
	              url: '/stock/edit',
	              method: 'POST',
	              beforeSend: x => x.setRequestHeader(_csrf_header, _csrf),
	              dataType : 'json',
	              contentType: 'application/json; charset=utf-8',
	              data: JSON.stringify(params),
	              cache : false
	          })
	          .done(function(data) {
	              if (data.status) {					
	                  alert("삭제 완료");
	                  location.reload();
	              } else {					
	                  alert("삭제 오류");					
	              }
	          })
	          .fail(function(error) {
	              console.log(error);
	          });	 
	      } else {
	          location.reload();
	      }
		
	});

	$("#cb_all").on("change", (e) => {
	    if(e.target.checked) {
	        $("tbody tr").each((i, v) => {
	            $(v).find("th").find("input").attr("checked", true);
				$(v).find("td").eq(1).find("input").prop("readonly", false);
				$(v).find("td").eq(2).find("input").prop("readonly", false);
	        });
	    } else {
	        $("tbody tr").each((i, v) => {
	            $(v).find("th").find("input").attr("checked", false);
				$(v).find("td").eq(1).find("input").prop("readonly", true);
				$(v).find("td").eq(2).find("input").prop("readonly", true);
	        });
	    }
	});

	$(".cb").on("change", function(e) {
	    if(e.target.checked) {
	        let index = $(".cb").index(this);
	        const $tr = $("#data-container tr").eq(index);
	        $tr.find("td").eq(1).find("input").prop("readonly", false);
	        $tr.find("td").eq(2).find("input").prop("readonly", false);
	    } else {
	        $(this).prop("checked", true);
	        editEvent();
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
            data: { page: page } // 페이지 번호를 쿼리 파라미터로 전달
        })
        .done(function(response) {
            if (response.length > 0) {
                // 데이터가 있으면 테이블에 추가
                response.forEach(function(item) {
                    $('#data-container').append(`
                        <tr>
                            <td>${item.itemCode}</td>
                            <td>${item.name}</td>
                            <td>${item.qty}</td>
                        </tr>
                    `);
                });
                loading = false;  // 데이터 로딩 완료
            } else {
                // 데이터가 없으면 더 이상 불러오지 않음
                $(window).off('scroll');
            }
        })
        .fail(function() {
            loading = false;
            alert('데이터를 불러오는 중 오류가 발생했습니다.');
        });
    }

});
