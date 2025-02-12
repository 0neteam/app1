$( () => {

    $("#searchZipcode").on("click", () => {
        // 다음 주소 API
        new daum.Postcode({
            oncomplete: function(data) {
                $("#zipCode").val(data.zonecode);
                $("#adr").val(data.roadAddress);
            }
        }).open();
        
    });

    $("form").on("submit", function(e) {
        e.preventDefault();

        // 우편번호 입력 체크
        const zipcode = $("#zipCode").val();
        if (!zipcode) {
            alert("우편번호 검색을 통해 우편번호와 주소를 입력해주세요.");
            return;
        }

        // 주소 입력 체크
        const address = $("#adr").val();
        if (!address) {
            alert("우편번호 검색을 통해 우편번호와 주소를 입력해주세요.");
            return;
        }
        $("#modalCorrectionConfirm").modal("show");
    });

    $("#bizCreate").on("click", () => {
        $("#modalCorrectionConfirm").modal("hide");
        
        $.ajax({
            url: '/biz/create',
            method: 'POST',
            data: $("form#createBiz").serialize(),  // 폼 데이터
        }).done(data => {
            if (data.status) {					
                alert("등록 완료");
                document.location.href = "/biz/list";
            } else {					
                alert("등록 오류");					
            }
        }).fail(error => {
            console.log(error);
        });

    });

});