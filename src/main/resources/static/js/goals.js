var sliderGoal = document.getElementById("milesRange");
var outputGoal = document.getElementById("milesBox");

var sliderProgress = document.getElementById("milesRangeProgress");
var outputProgress = document.getElementById("milesBoxProgress");

sliderGoal.value = outputGoal.value;
sliderProgress.value = outputProgress.value;

sliderGoal.onchange = function() {
    outputGoal.value = this.value;
}
outputGoal.onchange = function(){
    sliderGoal.value = outputGoal.value;
}

sliderProgress.onchange = function() {
    outputProgress.value = this.value;
}
outputProgress.onchange = function(){
    sliderProgress.value = outputProgress.value;
}

document.getElementById("swimmingChoice").setAttribute("checked","checked")