<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
    <title>새 글 쓰기</title>
    <script type="text/javascript" src="${pageContext.request.contextPath}/resources/js/smarteditor/js/HuskyEZCreator.js" charset="utf-8"></script>
            <style>
            .se2_version_info { 
                display: none !important; 
                }
            </style>
</head>

<body>
    <h2>새 게시글 작성</h2>
    <form id="registerForm" action="/myweb/board/register" method="post">
        <input type="hidden" name="bgno" value="${param.bgno}">
       
        <table border="1" style="width: 900px; border-collapse: collapse;">
            <tr>
                <th style="width: 150px;">작성자</th>
                <td>
                    <input type="text" name="writer" value="${user.username}" readonly
                    style="background-color: lightgray; width: 95%;">
                </td>
            </tr>
            <tr>
                <th>제목</th>
                <td><input type="text" name="title" required style="width: 95%;"></td>
            </tr>
            <tr>
                <th>내용</th>
                <td style="width: 750px;">
                    <textarea name="content" id="content" rows="10" cols="100" style="width:100%; height:412px; display:none;"></textarea>
                </td>
            </tr>
        </table>
        <br>
        <button type="button" onclick="save()">등록하기</button>
    </form>
    
    <script type="text/javascript">
    var oEditors = [];

    nhn.husky.EZCreator.createInIFrame({
        oAppRef: oEditors,
        elPlaceHolder: "content", 
        sSkinURI: "${pageContext.request.contextPath}/resources/js/smarteditor/SmartEditor2Skin.html", 
        fCreator: "createInIFrame", 
        htParams : {
            bUseToolbar : true,
            bUseVerticalResizer : true,
            bUseModeChanger : true,
            nIFrameWidth: 850,
            I18N_LOCALE : "ko_KR" 
        },
        
        fOnAppLoad : function(){
            try {
                var iframe = document.getElementById('content' + "_iframe");
                if(!iframe) iframe = document.querySelector('iframe');
                var iframeDoc = iframe.contentDocument || iframe.contentWindow.document;

                var style = iframeDoc.createElement('style');
                style.innerHTML = '.se2_version_info { display: none !important; }';
                iframeDoc.head.appendChild(style);

                var toolbar = iframeDoc.querySelector('.se2_multy'); 
                if(toolbar) {
                    var li = iframeDoc.createElement('li');
                    li.innerHTML = '<button type="button" style="background:#ffcc00; color:#333; border:1px solid #999; padding:2px 5px; cursor:pointer; font-size:12px; font-weight:bold; margin-left:5px; height:22px; vertical-align:middle;">사진</button>';
                    li.onclick = function() {
                        var sUrl = "${pageContext.request.contextPath}/resources/js/smarteditor/sample/photo_uploader/photo_uploader.html";
                        window.open(sUrl, "photo_popup", "width=403,height=359,scrollbars=yes,resizable=no");
                    };
                    toolbar.appendChild(li);
                }
            } catch(e) {
                console.log("커스텀 중 오류 발생:", e);
            }
        }
    }); 

    function save() {
        oEditors.getById["content"].exec("UPDATE_CONTENTS_FIELD", []);
        var form = document.getElementById("registerForm");
        if(form) {
            form.submit();
        }
    }
    </script>
    
</body>
</html>