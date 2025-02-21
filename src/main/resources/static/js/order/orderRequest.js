$( () => {
    
    const nowDateEvent = () => {
      const d = new Date();

      // 년, 월, 일 가져오기
      const year = d.getFullYear(); // 4자리 년도
      const month = String(d.getMonth() + 1).padStart(2, '0'); // 월 (0부터 시작하므로 1을 더해줘야 실제 월이 나옴)
      const day = String(d.getDate()).padStart(2, '0'); // 일
      const now = `${year}-${month}-${day}`;

      // 년, 월, 일 형식으로 출력
      $("#deliDate").val(now);
      $("#deliDate").attr("min", now);
    }
    // 납기 날짜 초기화
    nowDateEvent();

    const checkBoxEvent = () => {
      $("#cb_all").off().on("change", (e) => {
        $(".cb").each((i,el) => {
          if(e.target.checked) {
            $(el).prop("checked", true);
          } else {
            $(el).prop("checked", false);
          }
        });
      });
      // $(".cb").off().on("change", (e) => {
      //   if(e.target.checked) {

      //   }
      // });
    }

    const itemCheckEvent = (itemCode) => {
      let checked = true;
      $("#data-container tr").each((i, e) => {
        if(itemCode === $(e).find("td").eq(0).text()) checked = false;
      });
      return checked;
    }

    const removeItemEvent = () => {
      $(".del_btn").off().on("click", (e) => {
        const index = $(".del_btn").index(e.target);
        $("#data-container tr").eq(index).remove();
      });
    }

    const addItemEvent = (arr) => {
      arr.forEach((v, i) => {
        if(itemCheckEvent(v.itemCode)){
          let html = `<tr>
                        <td style="width: 100px;">${v.itemCode}</td>
                        <td>${v.name}</td>
                        <td style="width: 100px;"><input type="number" name="qty" class="form-control form-control-plaintext text-center" value="0"/></td>
                        <td style="width: 100px;"><button type="button" class="btn btn-danger w-100 del_btn">삭제</button></td>
                      </tr>`;
          $("#data-container").append(html);
        }
      });
      $("#modalViewProduct").modal('hide');
      removeItemEvent();
    }

    $("#selectConfirmBtn").on("click", () => {
      if($("#data-container tr").find("td").length === 1) {
        $("#data-container").empty();
      }
      const arr = [];
      $(".cb").each((i,el) => {
        if(el.checked) {
          let itemCode = $("#items tr").eq(i).find("td").eq(0).text();
          let name = $("#items tr").eq(i).find("td").eq(1).text();
          arr[arr.length] = {itemCode, name};
        }
      });
      addItemEvent(arr);
    });

    $("#backBtn").on("click", (e) => {
      $("#modalViewProduct").modal('hide');
    });

    $("#viewProduct").on("click", ()=> {
      let bizNo = $("#bizNo").val();
      if(bizNo === "") return;

      var _csrf = $('input[name="_csrf"]').val();
      var params = {_csrf, bizNo}

      $.ajax({
          url: '/order/list',
          method: 'POST',
          data: params
      }).done(res => {
          if (res.status) {
            if(res.data) {
              $("#items").empty();
              res.data.forEach((v, i) => {
                const html = `<tr>
                                <th style="width: 100px;">
                                    <input type="checkbox" name="cb" class="cb" />
                                </th>
                                <th style="width: 100px;">${i + 1}</th>
                                <td style="width: 100px;">${v.itemCode}</td>
                                <td>${v.name}</td>
                              </tr>`;
                $("#items").append(html);
              });
              checkBoxEvent();
            }
            $("#modalViewProduct").modal('show');
          } else {
              alert("선택한 거래처에서 오류가 발생 했습니다.");
          }
      }).fail(error => {
          console.log(error);
      });
    });

    // DAUM API
    $("#addrSearch").on("click", () => {
      new daum.Postcode({
        oncomplete: function(data) {
            $("#dstn").val(data.roadAddress);
            $("#dstnD").prop("readonly", false);
        }
      }).open();
    });

    // 드디어 발주 하자!!
    $("form").on("submit", (e) => {
      e.preventDefault();
      
      if($("#dstn").val() === "") {
        alert("주소검색을 해주세요.");
        return;
      }
      if($("#dstnD").val() === "") {
        alert("상세 주소를 입력 해주세요.");
        return;
      }
      if($("#bizNo").val() === "") {
        alert("거래처를 선택 해주세요.");
        return;
      }
      if($("#data-container tr").find("td").length <= 1) {
        alert("품목을 선택 해주세요.");
        return;
      }

      var _csrf = $("meta[name='_csrf']").attr("content");
			var _csrf_header = $("meta[name='_csrf_header']").attr("content");
      let order = {
        bizNo: $("#bizNo").val(),
        deliDate : $("#deliDate").val(),
        orderStatus : 1,
        dstn: ($("#dstn").val() + " " + $("#dstnD").val())
      };
      let orderItems = [];
      $("#data-container tr").each((i, e) => {
        let itemCode = $(e).find("td").eq(0).text();
        let itemName = $(e).find("td").eq(1).text();
        let qty = $(e).find("td").eq(2).find("input[name='qty']").val();
        orderItems[orderItems.length] = {itemCode, itemName, qty};
      });
      const params = {order, orderItems};

      $.ajax({
        url: '/order/create',
        method: 'POST',
        beforeSend: x => x.setRequestHeader(_csrf_header, _csrf),
        dataType : 'json',
        contentType: 'application/json; charset=utf-8',
        data: JSON.stringify(params),
        cache : false
      })
      .done(function(data) {
          if (data.status) {					
              alert("발주요청 완료");
              window.location.href = data.result;
          } else {					
              alert("발주요청 오류");					
          }
      })
      .fail(function(error) {
          console.log(error);
      });

    });

} );