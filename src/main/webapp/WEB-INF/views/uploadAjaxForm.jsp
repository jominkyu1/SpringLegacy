<%@ page language="java" contentType="text/html; charset=UTF-8"
pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
  <head>
    <meta charset="UTF-8" />
    <title>Insert title here</title>
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>
    <script>
        $(document).ready(function(){

            $('#uloadBtn').on('click', function(e){
                var formData = new FormData(); //폼데이터 객체 생성
                /*  첨부파일을 업로드 하는 또 다른 방법은 비동기식 AJAX를 이용하여 폼 데이터 객체를 이용하는것이다.
                */
                var inputFile = $('input[name="uploadFile"]');
                //ID선택자가아닌 Name속성값으로 jQuery에서 input type="file"에 접근한다

                var files = intpuFile[0].files;
                //input type="file"로 첨부한것을 배열로 구함

                for(var i=0; i<files.length<i++){
                    formData.append("uploadFile", files[i]);
                    //append() 메소드로 폼데이터 객체에 파일을 추가한다.
                }

                $.ajax({
                    url: 'uploadAjaxAction',
                    processData: false, //데이터를 컨텐트 타입에 맞게 변환 여부
                    contentType: false, //요청 컨텐트 타입
                    data: formData, //폼데이터 객체
                    type: 'POST',
                    dataType: 'json',
                    success: function(result){
                        alert(result);
                    }
                })
                })
      })
    </script>
  </head>
  <body>
    <h2>파일 업로드</h2>
    <form method="post" enctype="multipart/form-data" action="uploadFormAction">
      <input type="file" name="uploadFile" multiple > <hr>
      <input type="submit" value="파일 업로드">
    </form>
    <hr>
    <h2>비동기식 파일 업로드</h2>
    <input type="file" name="uploadFile" multiple > <hr>
    <input type="button" id="uloadBtn" value="비동기식 파일 업로드">
  </body>
</html>
