let currentDate = new Date().toLocaleDateString('en-CA')
document.getElementById("workout-date").value = currentDate;

function editWorkout() {
    window.location.replace("/plan/" + document.getElementById("workout-date").value);
}