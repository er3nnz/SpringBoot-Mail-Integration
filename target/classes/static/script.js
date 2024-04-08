document.getElementById("mailForm").addEventListener("submit", function (event) {
    event.preventDefault();

    const formData = new FormData(event.target);
    const mail = formData.get("to");
    const subject = formData.get("subject");
    const message = formData.get("message");
    const attachment = formData.get("attachment"); // Dosya ekini al

    const body = new FormData(); // FormData'nın yeni bir örneği

    // FormData'ya text alanları ekleniyor
    body.append("to", mail);
    body.append("subject", subject);
    body.append("message", message);

    // Dosya ekini FormData'ya ekleyin
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
