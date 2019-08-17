const userId = window.location.href.split("/")[4]

onUserUpdateClick = function() {
    const nameInput = document.getElementById('user-edit-name');
    const nameValue = nameInput.value;
    const emailValue = document.getElementById('user-edit-email').value;
    fetch('/users/' + userId, {
        method: 'PUT',
        headers: {
            'Content-Type': 'application/json; charset=UTF-8'
        },
        body: JSON.stringify({
            name: nameValue,
            email: emailValue
        })
    }).then(response => {
        console.log(response);
        const errorDiv = document.getElementById("error-message");
        errorDiv.innerHTML = "";
        if (response.status === 200) {
            response.json().then(function(json) {
                const p = document.createElement('p');
                p.innerHTML = json.message;
                errorDiv.insertAdjacentElement('afterbegin', p);
                nameInput.value = json.object.name;
            });
            return;
        }
        response.json().then(function(json) {
            const p = document.createElement('p');
            p.innerHTML = json.message;
            errorDiv.insertAdjacentElement('afterbegin', p);
            nameInput.value = json.object.name;
        });
    });
}