const stompClient = new StompJs.Client({
    brokerURL: 'ws://localhost:8002/gs-guide-websocket'
});

stompClient.onConnect = (frame) => {
    setConnected(true);
    console.log('Connected: ' + frame);
    stompClient.subscribe('/topic/scores', (scoresMessage) => {
        const scores = JSON.parse(scoresMessage.body);

        $("#greetings").empty();

        scores.sort((a, b) => b.score - a.score);

        scores.forEach(score => {
            showScore(score.username, score.score, score.time);
        });
    });
    sendName();
};

stompClient.onWebSocketError = (error) => {
    console.error('Error with websocket', error);
};

stompClient.onStompError = (frame) => {
    console.error('Broker reported error: ' + frame.headers['message']);
    console.error('Additional details: ' + frame.body);
};

function setConnected(connected) {
    $("#connect").prop("disabled", connected);
    $("#disconnect").prop("disabled", !connected);
    if (connected) {
        $("#conversation").show();
    }
    else {
        $("#conversation").hide();
    }
}

function connect() {
    stompClient.activate();
}

function disconnect() {
    stompClient.deactivate();
    setConnected(false);
    console.log("Disconnected");
    $("#greetings").empty();
}

function sendName() {

    stompClient.publish({
        destination: "/app/hello",
    });
}

function showScore(username, score, time) {
    $("#greetings").append("<tr><td>" + username + " - " + score + " points at " + time + "</td></tr>");
}

$(function () {
    $("form").on('submit', (e) => e.preventDefault());
    $( "#connect" ).click(() => connect());
    $( "#disconnect" ).click(() => disconnect());
    $( "#send" ).click(() => sendName());
});