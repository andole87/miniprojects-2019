function onAddPostClick() {
    const content = document.getElementById('post-content').value;
    const url = document.location.href;
    const postRequest = {
        contents: content
    };

    fetch(url + "/posts", {
            method: "POST",
            body: JSON.stringify(postRequest),
            headers: {
                "Accept": "application/json",
                "Content-type": "application/json"
            }
        })
        .then(res => res.json())
        .then(body => {
            location.reload();
        })
        .catch(error => console.log(error));
}