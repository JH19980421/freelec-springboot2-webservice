
var main = { // var main안에 모든 함수들을 정의한 이유는 브라우저의 스코프는 공용 공간으로 쓰이기 때문에
             // 나중에 불려진 js의 기능들이 먼저 불려진 js를 덮어버리기 때문에 여러 사람이 참가하는 프로젝트에서 중복된 함수 기능이 꼬이는
             // 것을 피하기 위함이다.
    init : function () {
        var _this = this;
        $('#btn-save').on('click', function () {
            _this.save();
        });

        $('#btn-update').on('click', function () { // btn-update란 id를 가진 HTML 엘리먼트에 click 이벤트가 발생할 때 update function을 실행한다.
            _this.update();
        });

        $('#btn-delete').on('click', function () {
            _this.delete();
        });
    },
    save : function () {
        var data = {
            title: $('#title').val(),
            author: $('#author').val(),
            content: $('#content').val()
        };

        $.ajax({
            type: 'POST',
            url: '/api/v1/posts',
            dataType: 'json',
            contentType:'application/json; charset=utf-8',
            data: JSON.stringify(data)
        }).done(function() {
            alert('글이 등록되었습니다.');
            window.location.href = '/'; // 글 등록이 성공하면 메인페이지(/)로 이동한다.
        }).fail(function (error) {
            alert(JSON.stringify(error));
        });
    },
    update : function () {
        var data = {
            title: $('#title').val(),
            content: $('#content').val()
        };

        var id = $('#id').val();

        $.ajax({
            type: 'PUT', // PostsApiController에서 @PutMapping으로 선언했기 때문에 PUT을 사용해 수정한다.
            url: '/api/v1/posts/'+id, // 어느 게시글을 수정할 지 URL Path로 구분하기 위해 Path에 id를 추가한다.
            dataType: 'json',
            contentType:'application/json; charset=utf-8',
            data: JSON.stringify(data)
        }).done(function() {
            alert('글이 수정되었습니다.');
            window.location.href = '/';
        }).fail(function (error) {
            alert(JSON.stringify(error));
        });
    },
    delete : function () {
        var id = $('#id').val();

        $.ajax({
            type: 'DELETE',
            url: '/api/v1/posts/'+id,
            dataType: 'json',
            contentType:'application/json; charset=utf-8'
        }).done(function() {
            alert('글이 삭제되었습니다.');
            window.location.href = '/';
        }).fail(function (error) {
            alert(JSON.stringify(error));
        });
    }

};

main.init();
Y