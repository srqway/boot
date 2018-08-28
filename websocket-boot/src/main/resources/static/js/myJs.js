var stompClient = null;

function setConnected(connected) {
    $("#connect").prop("disabled", connected);
    $("#disconnect").prop("disabled", !connected);
    $("#messages").html("");
}

function connect() {
    var socket = new SockJS('/endpoint_0');
    stompClient = Stomp.over(socket);
    stompClient.connect({}, function (frame) {
        setConnected(true);
        stompClient.subscribe('/topic_0/response', function (response) {
        	var json = JSON.parse(response.body);
            showConversation(json.messages);
        });
    });
}

function disconnect() {
    if (stompClient !== null) {
        stompClient.disconnect();
    }
    setConnected(false);
}

function send() {
	var message = $("#message").val()
    stompClient.send("/message/request", {}, JSON.stringify({'message': message}));
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