/**
 * Ajax custom module v1.0
 */

$.ajaxSetup({
    beforeSend: function (xhr) {
        let CSRF = getCSRFToken();
        if (CSRF) {
            xhr.setRequestHeader(CSRF.header, CSRF.token);
        }
    }
});

const getCSRFToken = () => {
    const header = document.querySelector('meta[name="_csrf_header"]').content;
    const token = document.querySelector('meta[name="_csrf"]').content;
    return {'header': header, 'token': token};
}

const AICS = {
    /**
     * @param {String} url
     * @param {Object=} data
     * @param {boolean=} loadModal
     * @returns {Promise<Object>}
     */
    get(url, data, loadModal) {
        return this._call(url, data, 'GET', loadModal ?? true)
    },
    post(url, data, loadModal) {
        return this._call(url, JSON.stringify(data), 'POST', loadModal ?? true)
    },
    put(url, data, loadModal) {
        return this._call(url, JSON.stringify(data), 'PUT', loadModal ?? true)
    },
    delete(url, data, loadModal) {
        return this._call(url, JSON.stringify(data), 'DELETE', loadModal ?? true)
    },
    /**
     * @param {String} url
     * @param {FormData} formData
     * @param {boolean=} loadModal
     * @returns {Promise<Object>}
     */
    fileUpload(url, formData, loadModal) {
        return this._callFileUpload(url, formData, loadModal ?? true)
    },
    /**
     * @private - 내부 호출 함수
     * @param {String} url
     * @param {Object=} data
     * @param {String} method
     * @param {boolean} loadModal
     * */
    _call(url, data, method, loadModal) {
        return AJAX.call({url, data, method, loadModal})
    },
    /**
     * @private - 내부 호출 함수
     * @param {String} url
     * @param {FormData} formData
     * @param {boolean=} loadModal
     * */
    _callFileUpload(url, formData, loadModal) {
        return AJAX.callFileUpload({url, formData, loadModal})
    },
    setCookie(name, value, days) {
        let expires = '';
        if (days) {
            let date = new Date();
            date.setTime(date.getTime() + (days * 24 * 60 * 60 * 1000));
            expires = '; expires=' + date.toUTCString();
        }
        document.cookie = name + '=' + value + expires + '; path=/';
    },
    getCookie(name) {
        let nameEQ = name + '=';
        let ca = document.cookie.split(';');
        for (let i = 0; i < ca.length; i++) {
            let c = ca[i];
            while (c.charAt(0) === ' ') c = c.substring(1, c.length);
            if (c.indexOf(nameEQ) === 0) return c.substring(nameEQ.length, c.length);
        }
        return null;
    },
    /**
     * 검색 조건 object 한번에 담기
     * return object {}
     */
    getSearchAreaValues() {
        let elements = document.querySelectorAll('.srch-container input, .srch-container select');
        let values = {};
        Array.from(elements).forEach((el) => {
            values[el.id] = (el.type === 'checkbox' || el.type === 'radio') ? el.checked : el.value;
        });
        return values;

    },
    clearSearchAreaValues() {
        let elements = document.querySelectorAll('.srch-container input, .srch-container select');
        Array.from(elements).forEach((el) => {
            if (el.tagName === 'SELECT') {
                el.selectedIndex = 0;
            }
            else if (el.type === 'checkbox' || el.type === 'radio') {
                el.checked = false;
            }
            else if (el.type === 'text' && el.id !== 'startDate' && el.id !== 'endDate') {
                el.value = '';
            }
        });
        this.initDatepicker();
    },
    initDatepicker() {
        let currentDate = new Date();
        let currentYear = currentDate.getFullYear();
        $("#startDate").datepicker("setDate", new Date(currentYear -1, currentDate.getMonth(), currentDate.getDate()));
        $("#endDate").datepicker("setDate", "0");
    },
    renderRangePicker(startEL, endEL) {
        $('#' + startEL).datepicker({
            format: 'yyyy-mm-dd',
            autoclose: true,
            todayBtn: true,
            todayHighlight: true
        }).on('changeDate', function(selected) {
            let startDate = new Date(selected.date.valueOf());
            $('#' + endEL).datepicker('setStartDate', startDate);
        });

        // 데이터피커 end
        $('#' + endEL).datepicker({
            format: 'yyyy-mm-dd',
            autoclose: true,
            todayBtn: true,
            todayHighlight: true
        }).on('changeDate', function(selected) {
            let endDate = new Date(selected.date.valueOf());
            $('#' + startEL).datepicker('setEndDate', endDate);
        });
    },
    excelDownload(url, data) {
        location.href = url + "?" + jsonToQueryString(data);
    },
    bytesToSize(bytes) {
        const sizes = ['Bytes', 'KB', 'MB', 'GB', 'TB'];
        if (bytes === 0) return '0 Byte';
        const i = parseInt(Math.floor(Math.log(bytes) / Math.log(1024)));
        return Math.round(100 * (bytes / Math.pow(1024, i))) / 100 + ' ' + sizes[i];
    }

}

/**
 * * ajax 함수
 * @author 송규광 <kyugwang@nobiz.co.kr>
 */
const AJAX = {
    /**
     * @param {Object} options - url, data, method
     * @returns {Promise<Object> | Undefined}
     */
    call: function (options) {
        const {url, data, method, loadModal} = options;

        return new Promise((resolve, reject) => {
            $.ajax({
                url: url,
                type: method,
                data: data,
                loadModal: loadModal || false,
                async: true,
                contentType: 'application/json',
                success: result => {
                    resolve(result);
                    result.msg && alert(result.msg);
                }, error: error => {
                    reject(error);
                    this._errorCallback(error);
                }
            });
        });
    },
    /**
     * @param {Object} options - url, formData
     * @returns {Promise<Object> | undefined}
     */
    callFileUpload: function (options) {
        const {url, formData, loadModal} = options;

        return new Promise((resolve, reject) => {
            $.ajax({
                url: url,
                type: 'POST',
                contentType: false,
                processData: false,
                data: formData,
                async: true,
                loadModal: loadModal || false,
                success: result => {
                    resolve(result);
                    // result.msg && alert(result.msg);
                }, error: error => {
                    reject(error);
                    this._errorCallback(error);
                }
            });
        });
    },
    /**
     * @private - 내부 호출 함수
     * @param {Object} error
     * */
    _errorCallback: function (error) {
        nobiz.alert(error.responseJSON.message, 'error');
    }
}

const jsonToQueryString = (json) =>{
    return Object.keys(json).map(function (key) {
        return encodeURIComponent(key) + '=' + encodeURIComponent(json[key]);
    }).join('&');
}

/* 전역 변수 저장소 */
class GlobalStorage {
    constructor() {
        this._data = {};
    }

    get(key) {
        return this._data[key];
    }

    set(key, value) {
        this._data[key] = value;
    }
}

const nobiz = {
    alert: (message = '요청이 완료되었습니다.', icon = 'success') => {
        Swal.fire({
            title: '',
            text: message,
            icon: icon,
            confirmButtonText: '확인',
            confirmButtonColor: '#3372DB'
        });
    },
    confirm: async (message, fnCallback = nobiz.alert) => {
        const result = await Swal.fire({
            title: '',
            text: message,
            icon: 'warning',
            showCancelButton: true,
            cancelButtonColor: '#808080',
            confirmButtonColor: '#3372DB',
            confirmButtonText: '확인',
            cancelButtonText: '취소',
            reverseButtons: true
        });

        if (result.isConfirmed) {
            fnCallback();
        }
    }

}