var slider = document.getElementById("milesRange");
var output = document.getElementById("milesBox");
output.innerHTML = slider.value;

slider.onchange = function() {
    output.value = this.value;
}
output.onchange = function(){
    slider.value = output.value;
}
