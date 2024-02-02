$(function() {
    $('.mobile-menu-btn').click(function(){
        $('body').toggleClass('active');
    })
    
    $('.header .menu > li > a').click(function(){
        $('.header .menu > li').removeClass('active');
        $(this).parent().addClass('active');
    });

    // datepicker basic
    $('#datepicker01').datepicker({
        format: 'yyyy-mm-dd',
        autoclose: true,
        todayBtn: true,
        todayHighlight: true,
        language: "ko"
    });

    // month picker
    $('.form-control.mnpk').datepicker({
        format: "yyyy-mm",
        autoclose: true,
        viewMode: "months",
        minViewMode: "months",
        language: "ko"
    });
    
    // 데이터피커 start
    $('#startDate').datepicker({
        format: 'yyyy-mm-dd',
        autoclose: true,
        todayBtn: true,
        todayHighlight: true,
        language: "ko"
    }).on('changeDate', function(selected) {
        let startDate = new Date(selected.date.valueOf());
        $('#endDate').datepicker('setStartDate', startDate);
    });
    
    // 데이터피커 end
    $('#endDate').datepicker({
        format: 'yyyy-mm-dd',
        autoclose: true,
        todayBtn: true,
        todayHighlight: true,
        language: "ko"
    }).on('changeDate', function(selected) {
        let endDate = new Date(selected.date.valueOf());
        $('#startDate').datepicker('setEndDate', endDate);
    });

    // 체크박스 전체 선택/해제
    $('.check-all').change(function () {
        // 전체 체크박스의 상태에 따라 나머지 체크박스의 상태를 변경
        $('.chk-box .form-check-input:not(".check-all")').prop('checked', this.checked);
    });
    
    // 나머지 체크박스들의 상태 변화에 따라 "전체" 체크박스의 상태 변경
    $('.chk-box .form-check-input:not(".check-all")').change(function () {
        if ($('.chk-box .form-check-input:not(".check-all"):checked').length === $('.chk-box .form-check-input:not(".check-all")').length) {
            // 모든 체크박스가 선택되었을 경우 "전체" 체크박스도 선택 상태로 변경
            $('.check-all').prop('checked', true);
        } else {
            // 그 외의 경우 "전체" 체크박스는 선택 해제 상태로 변경
            $('.check-all').prop('checked', false);
        }
    });


    //util 버튼 레이어 열기
    $(document).on('click', '.utill > li.alarm > a', function() {
        $('.utill > li > ul').hide();
        if (!$(this).siblings('ul').is(':visible')) {
            $(this).siblings('ul').show();
        } else {
            $(this).siblings('ul').fadeOut();
        }
        return false;
    });

    //재생/다운로드 버튼 레이어 열기
    $(document).on('click', '.grid-top-comp-area .btn-wrap button', function() {
        $('.grid-top-comp-area .btn-wrap ul').hide();
        var rPos = $(this).parent().outerWidth() - ($(this).position().left + $(this).outerWidth());
        if (!$(this).nextAll('ul').eq(0).is(':visible')) {
            $(this).nextAll('ul').eq(0).show();
            $(this).nextAll('ul').eq(0).css({'right':rPos});
        } else {
            $(this).nextAll('ul').eq(0).hide();
        }
        return false;
    });

    // 아무데나 클릭하면 util 버튼 레이어 닫기
    $(document).click(function(){
        $('.utill > li > ul, .grid-top-comp-area .btn-wrap ul').fadeOut();
    });

    /* 상단 날짜 셋팅 */
    AICS.initDatepicker();
    
});

// 팝업 창열기
function popOpen(elem) {
    $(elem).show();
    $('.modal-bg').show();
}

// 팝업 창닫기
function popClose(elem) {
    $(elem).hide();
    $('.modal-bg').hide();
}

const moveFocus = (currentInput, nextInputId) => {
    if (currentInput.value.length === currentInput.maxLength) {
        const nextInput = document.getElementById(nextInputId);
        if (nextInput) {
            nextInput.focus();
        }
    }
}
const formatPhoneNumber = (phoneNumber) => {
    // 공백 및 하이픈 제거
    phoneNumber = phoneNumber.replace(/\s|-/g, '');
    // 전화번호를 3-4-4 자리로 나누어 포맷팅
    if (phoneNumber.length === 10) {
        return phoneNumber.substring(0, 3) + '-' + phoneNumber.substring(3, 6) + '-' + phoneNumber.substring(6);
    } else if (phoneNumber.length === 11) {
        return phoneNumber.substring(0, 3) + '-' + phoneNumber.substring(3, 7) + '-' + phoneNumber.substring(7);
    } else if (phoneNumber.length === 12) {
        return phoneNumber.substring(0, 4) + '-' + phoneNumber.substring(4, 8) + '-' + phoneNumber.substring(8);
    } else {
        // 적절한 길이가 아닌 경우 원본 번호 반환
        return phoneNumber;
    }
}

const modifyPasswd = (el) => {
    let params = {};
    let nodeEL = '.' + el + ' input';
    let nodes = document.querySelectorAll(nodeEL);
    nodes.forEach(node => {
        if (node.name !== '') {
            params[node.name] = node.value;
        }
    });

    console.log(params);
    if (params.passwd === '') {
        nobiz.alert('현재 비밀번호를 입력 해 주세요.');
        return false;
    }
    if (params.newPasswd === '') {
        nobiz.alert('신규 비밀번호를 입력 해 주세요.');
        return false;
    }
    if (params.newPasswd !== params.confPasswd) {
        nobiz.alert('신규 비밀번호와 비밀번호 확인이 일치하지 않습니다.');
        return false;
    }

    let response = AICS.put("/user/passwd", params, false);
    response.then(result => {
        nobiz.alert(result.message);
        popClose('.' + el);
    })
}

/**
 * 그리드에서 Y, N 값을 ○, X 로 변경하는 함수
 * @param c
 * @returns {string}
 */
const changeToChar = (c) => {
    if (c === 'Y')
        return '○';

    if (c === 'N')
        return 'X';
}
