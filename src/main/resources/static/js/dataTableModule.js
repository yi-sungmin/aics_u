/**
 * jquery dataTables module v1.0
 * 2023.12.03
 * 생성자 : 이성민
 */

const lang_kor = {
    lengthMenu: "_MENU_ 개씩 보기",
    zeroRecords: "등록된 데이터가 없습니다.",
    emptyTable: "등록된 데이터가 없습니다.",
    responsive: true,
    paginate: {
        first: "처음",
        previous: "이전",
        next: "다음",
        last: "마지막"
    },
    info: "현재 _START_ - _END_ / 총 _TOTAL_건",
    infoEmpty: "데이터 없음",
    // infoFiltered: "( _MAX_ 건의 데이터에서 필터링됨 )",
    infoFiltered: "",
    loadingRecords: "로딩중...",
    processing: "잠시만 기다려 주세요...",
    sSearch: '검색'
}

const defaultOptions = {
    // dom: 't<"margin-top-10"lp> ',
    deferRender: true,
    processing: true,
    serverSide: true,
    filter: false,
    paging: true,
    // pagingType: 'simple_numbers',
    lengthChange: true,
    pageLength: 10,
    lengthMenu: [10, 25, 50, 100],
    order: [],
    language: lang_kor
};

$.extend($.fn.dataTable.defaults, defaultOptions);

/**
 * DataTables를 초기화하고 Ajax 데이터를 가져오는 함수
 * @param selector
 * @param {string} url - Ajax 요청 URL
 * @param {Object} data - Ajax 데이터 파라미터
 * @param {Array} columns - DataTables 열 정의
 * @param customOptions
 */
const renderDataTable = (selector, url, data, columns, customOptions) => {
    const options = {
        ajax: initAjax(url, data),
        columns: columns,
        ...customOptions
    };
    return $(selector).DataTable(options);
};

const initAjax = (url, data) => {
    return {
        url: url,
        type: "GET",
        data: function (d) {
            let order = d.order[0];
            if (order !== undefined) {
                let sortMn = d.columns[order.column].data;
                if (sortMn != null) {
                    sortMn = sortMn !== '' ? convertCamelToSnake(sortMn) : '';
                    d.sortMn = sortMn;
                    d.sortDir = order.dir;
                } else {
                    d.sortMn = null;
                }
            } else {
                d.sortMn = '';
                d.sortDir = '';
            }
            d.startRow = d.start + 1;
            d.endRow = d.start + d.length;
            return $.extend({}, d, data);
        },
        // dataSrc: '',
        dataSrc: function (json) {
            $('#recordsFiltered').text(json.recordsFiltered);
            return json.data;
        },
        error: function (xhr, status, err) {
            defaultErrorCallback(xhr);
        }
    }
}
const defaultErrorCallback = function (xhr) {
    nobiz.alert('그리드 데이터 테이블 불러오기 실패!', 'error');
    console.log('통신 실패!!! Status: ' + xhr.responseJSON.message);
};

const convertCamelToSnake = (camelCase) => {
    return camelCase.replace(/([A-Z])/g, "_$1").toLowerCase();
}



