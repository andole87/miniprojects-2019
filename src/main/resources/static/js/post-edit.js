function onModifyClick(id) {
    const content = document.getElementById('post-modify-content').value;
    const url = document.location.origin;
    const postRequest = {
        contents: content
    };

    fetch(url + "/posts/" + id, {
            method: "PUT",
            body: JSON.stringify(postRequest),
            headers: {
                "Accept": "application/json",
                "Content-type": "application/json"
            }
        })
        .then(document.location.href = url)
        .catch(error => console.log(error));
}