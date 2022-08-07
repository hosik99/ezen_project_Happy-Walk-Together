<%@ page contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<!DOCTYPE html>
<html>
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
	<title>웹소켓 테스트 페이지</title>
	<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>
	<link href="https://cdn.jsdelivr.net/npm/bootstrap@5.2.0/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-gH2yIJqKdNHPEq0n4Mqa/HGKIhSkIHeL5AyhkYV8i59U5AR6csBvApHHNl/vI1Bx" crossorigin="anonymous">
	<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.2.0/dist/js/bootstrap.bundle.min.js" integrity="sha384-A3rJD856KowSb7dwlZdYEkO39Gagi7vIsF0jrRAoQmDKKtQBHUuLZ9AsSv4jD4Xa" crossorigin="anonymous"></script>
	<style type="text/css">
		body{
			margin-bottom:100%;
			background: linear-gradient(45deg, lightCyan, skyBlue, deepSkyBlue) 100%;
		}
		.gradient-custom{
			
		}
		.mask-custom {
			background: rgba(24, 24, 16, .2);
			border-radius: 2em;
			backdrop-filter: blur(15px);
			border: 2px solid rgba(255, 255, 255, 0.05);
			background-clip: padding-box;
			box-shadow: 10px 10px 10px rgba(46, 54, 68, 0.03);
		}
		section{
			width:100%;
		}
		#fir_member{
			width: 30%;
		}
		#fir_btn{
			padding: 5px 10px;
			margin: 3px 1px;
		}
	</style>
<script type="text/javascript">

var g_webSocket = null;
var uid = '${uid}';

window.onload = function()
{
       /* 배포시에 호스트 주소로 변경 */
	host = "localhost:58172";
    g_webSocket = new WebSocket("ws://"+host+"/websocket");
    
    
    /* 웹소켓 접속 성공시 실행 */
    g_webSocket.onopen = function(message) {
        //addLineToChatBox("Server is connected.");
    };
    
    
    /* 웹소켓 서버로부터 메시지 수신시 실행 */
    g_webSocket.onmessage = function(message) {
    	var obj = JSON.parse(message.data);
		var sender = obj.from;
		var contents = obj.contents;
		var channelCode = obj.channelCode;
		uid=sender;
		if(contents!=null & contents!=""){
			if(sender==uid){
				obj.side='right';
			}else{
				obj.side='left';
			}
			addLineToChatBox(obj);
		}
		document.getElementById("channelCode").value= channelCode;
		
    };

    /* 웹소켓 이용자가 연결을 해제하는 경우 실행 */
    g_webSocket.onclose = function(message) {
       // addLineToChatBox("Server is disconnected.");
    };

    /* 웹소켓 에러 발생시 실행 */
    g_webSocket.onerror = function(message) {
    	alert('재접속 해주시기 바랍니다.');
    };
}

/* 채팅 메시지를 화면에 표시 */
function addLineToChatBox(obj) 
{
    if (obj.contents == null) {
    	obj.contents = "";
    }
    var msgHtml = [];
	
    if(obj.side=='right'){
		msgHtml.push('<li style="float: right; margin-left: 50%;" class="d-flex justify-content-between mb-4">');
		msgHtml.push('    <div class="card mask-custom">');
		msgHtml.push('      <div class="card-header d-flex justify-content-between p-3"');
		msgHtml.push('        style="border-bottom: 1px solid rgba(255,255,255,.3);">');
		msgHtml.push('        <p class="fw-bold mb-0">'+obj.from+'</p>');
		msgHtml.push('        <p class="text-light small mb-0"><i class="far fa-clock"></i> 12 mins ago</p>');
		msgHtml.push('      </div>');
		msgHtml.push('     <div class="card-body">');
		msgHtml.push('       <p class="mb-0">');
		msgHtml.push('       	'+obj.contents);
		msgHtml.push('       </p>');
		msgHtml.push('     </div>');
		msgHtml.push('   </div>');
		msgHtml.push(' </li>');
    }else{
    	msgHtml.push('<li style="float: left; margin-left: 50%;" class="d-flex justify-content-between mb-4">');
		msgHtml.push('    <div class="card mask-custom">');
		msgHtml.push('      <div class="card-header d-flex justify-content-between p-3"');
		msgHtml.push('        style="border-bottom: 1px solid rgba(255,255,255,.3);">');
		msgHtml.push('        <p class="fw-bold mb-0">'+obj.from+'</p>');
		msgHtml.push('        <p class="text-light small mb-0"><i class="far fa-clock"></i> 12 mins ago</p>');
		msgHtml.push('      </div>');
		msgHtml.push('     <div class="card-body">');
		msgHtml.push('       <p class="mb-0">');
		msgHtml.push('       	'+obj.contents);
		msgHtml.push('       </p>');
		msgHtml.push('     </div>');
		msgHtml.push('   </div>');
		msgHtml.push(' </li>');
    }
	
	$('ul').append(msgHtml.join(""));
	
    var inputMsgBox = document.getElementById("inputMsgBox");
    //chatBoxArea.value += _line + "\n";
    inputMsgBox.scrollTop = inputMsgBox.scrollHeight;    
}

/* Send 버튼 클릭하면 서버로 메시지 전송 */
function sendButton_onclick() {
	var inputMsgBox = document.getElementById("inputMsgBox");
    if (inputMsgBox == null || inputMsgBox.value == null || inputMsgBox.value.length == 0) {
        return false;
    }
    
    var chatBoxArea = document.getElementById("chatBoxArea");
    
    if (g_webSocket == null || g_webSocket.readyState == 3) {
        //chatBoxArea.value += "Server is disconnected.\n";
        return false;
    }
    
    //채팅방 Code
    var channelCodeVal = document.getElementById("channelCode").value;
    
    // 서버로 메시지 전송
    var msg = {};
    msg.from = uid;
    msg.contents = inputMsgBox.value;
    msg.channelCode = channelCodeVal;
    
    g_webSocket.send(JSON.stringify(msg));  // '{\"key":\"value\"}'
    inputMsgBox.value = "";
    inputMsgBox.focus();
    
    return true;
}

/* Disconnect 버튼 클릭하는 경우 호출 */
function disconnectButton_onclick() {
    if (g_webSocket != null) {
        g_webSocket.close();    
    }
    location.href='/ws/chat/list';
}

/* inputMsgBox 키 입력하는 경우 호출 */
function inputMsgBox_onkeypress() {
    if (event == null) {
        return false;
    }
    
    // 엔터키 누를 경우 서버로 메시지 전송
    var keyCode = event.keyCode || event.which;
    if (keyCode == 13) {
        sendButton_onclick();
    }
}
</script>
</head>
<body>
	<section class="gradient-custom">
	  	<div class="container py-5">
			<div class="row">
				<!-- 
				<div  id="fir_member" class="col-md-6 col-lg-5 col-xl-5 mb-4 mb-md-0">
					<h5 class="font-weight-bold mb-3 text-center text-white">Member</h5>
					<div class="card mask-custom">
			         	<div class="card-body">
			         	멤버
						</div>
			        </div>
				</div>
				 -->
		    	<div id="fir_box" class="col-md-6 col-lg-7 col-xl-7">
					<ul class="list-unstyled text-white">
						  
		       		</ul>
		        	<div class="form-outline form-white">
		        		<textarea id="inputMsgBox"  class="form-control"  rows="2" onkeypress="inputMsgBox_onkeypress();"></textarea>
		       		</div>
		       		<button id="fir_btn" type="button" id="disconnectButton" class="btn btn-light btn-lg btn-rounded float-end" onclick="disconnectButton_onclick();">Out</button>
		        	<button id="fir_btn" type="button" id="sendButton" class="btn btn-light btn-lg btn-rounded float-end" onclick="sendButton_onclick();">Send</button>
		        </div>
			</div>
		</div>
	</section>
	<input type="hidden" id="channelCode" value="">
</body>
</html>