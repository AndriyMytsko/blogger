$(() => {
    let show = function(show) {
        $.ajax({
            url: '/show',
            type: 'get',
            success: function (response) {
                $('#showDIV').empty();
                console.log(response);
                for(let i = 0; i<response.length; i++){
                    $('<p/>', {text:response[i].comment}).appendTo("#showDIV");
                }
            },
            error: function (err) {
                alert(err);
            }
        });
    };

    $('#save').click(function () {
        let comment =$('#add-post').val();
        let comment2 = JSON.stringify({comment:comment});
        console.log(comment);

        $.ajax({
            url : '/saveComment',
            type : 'POST',
            data : comment2,
            contentType: 'application/json',
            success: function (result) {
                show();
            },
            error : function (err) {
                alert(err);
            }
        })
    });
});