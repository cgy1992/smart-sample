$(function() {
    $('#upload_form').ajaxForm({
        type: 'post',
        url: BASE + '/upload.do',
        beforeSubmit: function() {
            var file = $('#file').val();
            if (file == '') {
                alert('Please choose a JPG file!');
                return false;
            }
            if (file.substring(file.lastIndexOf('.') + 1).toLowerCase() != 'jpg') {
                alert('This file is not JPG format!');
                return false;
            }
            return true;
        },
        success: function(result) {
            if (result.success) {
                var data = result.data;
                if (data) {
                    console.dir(data);
                    var html = '';
                    html += '<div>File Name: ' + data.file_name + '</div>';
                    html += '<div>File Type: ' + data.file_type + '</div>';
                    html += '<div>File Size: ' + data.file_size + '</div>';
                    html += '<img src="../upload/product/' + data.file_name + '" style="height: 400px;"/>';
                    $('#console').html(html);
                }
            }
        }
    });
});