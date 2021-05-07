var slider = document.getElementById("milesRange");
var output = document.getElementById("distantsValue");
output.innerHTML = slider.value;

slider.oninput = function() {
    output.innerHTML = this.value;
}