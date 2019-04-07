function checkText(domObj) {
    domObj.value = domObj.value.toUpperCase();
    lastChar = parseInt(domObj.value.charAt(domObj.value.length - 1));
    if (!isNaN(lastChar)) {
        alert("No escribas números");
        domObj.value = domObj.value.substr(0, domObj.value.length - 1);
    }
}

function checkEmail(domObj) {
    email = domObj.value;
    hasArroba = email.indexOf("@") != -1;
    hasDot = email.indexOf(".") != -1;
    if (!hasArroba || !hasDot) {
        alert("Checa el correo");
    }
}