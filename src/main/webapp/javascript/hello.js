$(document).ready(function() {
    $.ajax({
        url: "restservices/reserveringen"
    }).then(function(data) {
       $('.greeting-id').append(data.Reserveringnummer);
       $('.greeting-content').append(data.content);
    });
});