const dateAfter = document.getElementById('dateAfter');
const dateBefore = document.getElementById('dateBefore');

const today = new Date();
const aweekback = new Date();
aweekback.setDate(aweekback.getDate() - 7);


dateAfter.valueAsDate = aweekback;
dateBefore.valueAsDate = today;
