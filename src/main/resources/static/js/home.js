function calculateAge(birthdate) {
    let date = new Date(birthdate);
    let currentDate = new Date();
    let age = currentDate.getUTCFullYear() - date.getUTCFullYear();
    if (currentDate.getMonth() < date.getMonth()) {
        age--;
    }
    else if ((currentDate.getMonth() === date.getMonth()) && (currentDate.getUTCDate() < date.getUTCDate())) {
        age--;
    }
    document.getElementById("age").innerHTML = age;
}