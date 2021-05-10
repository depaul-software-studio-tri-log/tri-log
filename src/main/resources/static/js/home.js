const birthdate = new Date(userBirthdate);

function calculateAge() {
    let currentDate = new Date();
    let age = currentDate.getUTCFullYear() - birthdate.getUTCFullYear();

    if (currentDate.getUTCMonth() < birthdate.getUTCMonth()) {
        age--;
    }
    else if ((currentDate.getUTCMonth() === birthdate.getUTCMonth())
        && (currentDate.getUTCDate() < birthdate.getUTCDate())) {
        age--;
    }
    document.getElementById("age").innerHTML = age;
}

function getOrdinalSuffix(date) {
    let dateUTC = date.getUTCDate();
    let ordinalSuffix = "th";

    if ((dateUTC % 10 === 1) && (dateUTC !== 11)) {
        ordinalSuffix = "st";
    }
    else if ((dateUTC % 10 === 2) && (dateUTC !== 12)) {
        ordinalSuffix = "nd";
    }
    else if ((dateUTC % 10 === 3) && (dateUTC !== 13)) {
        ordinalSuffix = "rd";
    }
    return ordinalSuffix;
}

function formatBirthday() {
    let birthday = birthdate.toLocaleString('default',{month: 'long', day: 'numeric', timeZone: 'UTC'});
    let ordinalSuffix = getOrdinalSuffix(birthdate);

    document.getElementById("birthday").innerHTML = birthday + ordinalSuffix;
}

calculateAge();
formatBirthday();