document.getElementById("mailForm").addEventListener("submit", function (event) {
    event.preventDefault();

    const formData = new FormData(event.target);
    const mail = formData.get("to");
    const subject = formData.get("subject");
    const message = formData.get("message");
    const attachment = formData.get("attachment");

    const body = new FormData();


    body.append("to", mail);
    body.append("subject", subject);
    body.append("message", message);


    if (attachment) {
        body.append("attachment", attachment);
    }

    fetch("/mail/send", {
        method: 'POST',
        body: body
    })
        .then(response => response.text())
        .then(data => {
            alert(data);
            document.getElementById("mailForm").reset();
        })
        .catch(error => console.error('Error:', error));
});
