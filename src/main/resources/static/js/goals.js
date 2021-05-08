var slider = document.getElementById("milesRange");
var output = document.getElementById("milesBox");
output.innerHTML = slider.value;

slider.onchange = function() {
    output.value = this.value;
}
output.onkeyup = function(){
    slider.value = output.value;
}