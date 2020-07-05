
function addArticle() {

    var title = $("#title").val()
    var describe = $("#describe").val()
    var art_pic = $("#imgurl").val()
    var content = CKEDITOR.instances.editor1.getData();//文章内容
    var istop = $("#istop").is(':checked');
    var artclass = $("#artclass").val();


    if(title==""){
        alert("文章标题不能为空")
        return false
    }
    if(describe==""){
        alert("文章描述不能为空")
        return false
    }
    if(art_pic==""||art_pic==null){
        alert("文章配图不能为空")
        return false
    }
    if(content==""){
        alert("文章内容不能为空")
        return false
    }
    if(artclass==""){
        alert("分类信息不能为空")
        return false
    }



    $.ajax({
        type: "POST",
        url: "/admin/article",
        contentType: 'application/json',
        data: JSON.stringify({
            "title":title,
            "content": content,
            "describe":describe,
            "art_pic":art_pic,
            "istop":istop,
            "artclass":artclass
        }),
        success: function (response) {

            alert("发表成功")
            location.reload();
        },
        error: function (data) {
            alert("发表出现异常")
        },
        dataType: "json"
    });
}

//上传图片
function upPic() {

    var formData = new FormData();

    //判断是否为空
    if($("#file")[0].files[0]==null){
        alert("请选择图片")
        return false
    }

    //判断是否为图片
    var path = $("#file").val();
    var extStart = path.lastIndexOf('.'),
        ext = path.substring(extStart, path.length).toUpperCase();
    if (ext !== '.PNG' && ext !== '.JPG' && ext !== '.JPEG' && ext !== '.GIF') {
        alert("请上传图片");
        return false;
    }


    //进行上传
    formData.append("mFile",$("#file")[0].files[0]);
    $.ajax({
        url: "/fileUpload",
        type: 'POST',
        data: formData,
        processData: false,
        contentType:false,
        success : function(data) {
            alert("上传完成")

            $(".urls").html(data);

        }

    });
}

//更新文章
function  upArticle() {


    var title = $("#title").val()
    var describe = $("#describe").val()
    var art_pic = $("#imgurl").val()
    var content = CKEDITOR.instances.editor1.getData();//文章内容
    var istop = $("#istop").val();
    var artclass = $("#artclass").val();
    var artid = $("#id").val()

    if(title==""){
        alert("文章标题不能为空")
        return false
    }
    if(describe==""){
        alert("文章描述不能为空")
        return false
    }
    if(art_pic==""||art_pic==null){
        alert("文章配图不能为空")
        return false
    }
    if(content==""){
        alert("文章内容不能为空")
        return false
    }
    if(artclass==""){
        alert("分类信息不能为空")
        return false
    }
    if(istop!="true"&&istop!="false"){
        alert("请按格式输入置顶设置")
        return false
    }



    $.ajax({
        type: "PUT",
        url: "/admin/article/"+artid,
        contentType: 'application/json',
        data: JSON.stringify({
            "title":title,
            "content": content,
            "describe":describe,
            "art_pic":art_pic,
            "istop":istop,
            "artclass":artclass,

        }),
        success: function (response) {

            if (response.code==200){
                alert("更新成功")
                location.reload();
            }


        },
        error: function (data) {
            alert("发表出现异常")
        },
        dataType: "json"
    });



}

function upComment() {


    var comment = $("#comment").val()

    if (comment==""){
        alert("评论内容不能为空")
        return false
    }
    if (comment.length>30){
        alert("评论不能超过30个字符")
        return false
    }
    var name=prompt("请输入昵称")

    if (name==""||name==null){
        alert("昵称不能为空")
        return false
    }

    var email=prompt("请输入邮箱")

    if(isValidEmailAddress(email)==false){
        alert("请输入正确的邮箱地址");
        return false;
    }



    $.ajax({
        type: "POST",
        url: "/admin/Comment",
        contentType: 'application/json',
        data: JSON.stringify({
            "id":id,
            "comment":comment,
            "name":name,
            "email":email,
        }),
        success: function (response) {

            alert("评论成功")
            location.reload();
        },
        error: function (data) {
            alert("评论出现异常")
        },
        dataType: "json"
    });



}

//检查邮箱地址格式是否正确
function isValidEmailAddress(emailAddress) {
    var pattern = /^([a-z\d!#$%&'*+\-\/=?^_`{|}~\u00A0-\uD7FF\uF900-\uFDCF\uFDF0-\uFFEF]+(\.[a-z\d!#$%&'*+\-\/=?^_`{|}~\u00A0-\uD7FF\uF900-\uFDCF\uFDF0-\uFFEF]+)*|"((([ \t]*\r\n)?[ \t]+)?([\x01-\x08\x0b\x0c\x0e-\x1f\x7f\x21\x23-\x5b\x5d-\x7e\u00A0-\uD7FF\uF900-\uFDCF\uFDF0-\uFFEF]|\\[\x01-\x09\x0b\x0c\x0d-\x7f\u00A0-\uD7FF\uF900-\uFDCF\uFDF0-\uFFEF]))*(([ \t]*\r\n)?[ \t]+)?")@(([a-z\d\u00A0-\uD7FF\uF900-\uFDCF\uFDF0-\uFFEF]|[a-z\d\u00A0-\uD7FF\uF900-\uFDCF\uFDF0-\uFFEF][a-z\d\-._~\u00A0-\uD7FF\uF900-\uFDCF\uFDF0-\uFFEF]*[a-z\d\u00A0-\uD7FF\uF900-\uFDCF\uFDF0-\uFFEF])\.)+([a-z\u00A0-\uD7FF\uF900-\uFDCF\uFDF0-\uFFEF]|[a-z\u00A0-\uD7FF\uF900-\uFDCF\uFDF0-\uFFEF][a-z\d\-._~\u00A0-\uD7FF\uF900-\uFDCF\uFDF0-\uFFEF]*[a-z\u00A0-\uD7FF\uF900-\uFDCF\uFDF0-\uFFEF])\.?$/i;
    return pattern.test(emailAddress);
};


function f() {
    alert("功能正着完善中。。。")
}