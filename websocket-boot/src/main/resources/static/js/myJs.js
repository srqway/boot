var stompClient = null;

function setConnected(connected) {
    $("#connect").prop("disabled", connected);
    $("#disconnect").prop("disabled", !connected);
    $("#messages").html("");
}

function connectCallback(frame) {
    setConnected(true);
    stompClient.subscribe('/topic_0/response', function (response) {
    	var json = JSON.parse(response.body);
        showConversation(json.messages);
    });
}

function errorCallback(any) {
    console.log('STOMP: ' + any);
    setTimeout(connect, 10000);
    console.log('STOMP: Reconecting in 10 seconds');
}

function connect() {
    console.log('STOMP: Attempting connection');
    var socket = new SockJS('/endpoint_0');
    stompClient = Stomp.over(socket);
    stompClient.connect({}, connectCallback, errorCallback);
}

function disconnect() {
    if (stompClient !== null) {
        stompClient.disconnect();
    }
    setConnected(false);
}

function send() {
	var message = $("#message").val()
    stompClient.send("/app/message/request", {}, JSON.stringify({'message': message}));
}

function showConversation(messages) {
    $("#messages").append("<tr><td>" + messages + "</td></tr>");
}

$(function () {
    $("form").on('submit', function (e) {
        e.preventDefault();
    });
    $( "#connect" ).click(function() { connect(); });
    $( "#disconnect" ).click(function() { disconnect(); });
    $( "#send" ).click(function() { send(); });
});