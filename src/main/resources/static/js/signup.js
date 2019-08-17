const password = document.getElementById("password");
const passwordConfirm = document.getElementById("passwordConfirm");

const validatePassword = function() {
    if (password.value !== passwordConfirm.value) {
        passwordConfirm.setCustomValidity("패스워드가 서로 맞지 않습니다.");
    } else {
        passwordConfirm.setCustomValidity("");
    }
};

password.oninput = validatePassword;
passwordConfirm.oninput = validatePassword;