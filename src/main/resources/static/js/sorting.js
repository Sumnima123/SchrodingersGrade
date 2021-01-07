var timeslots = timeslotsfinal;
var date_start_time = [];
var final_timeslot = [];


for(var i = 0; i<timeslots.length; i++){
	var split_timeslot = timeslots[i].split("➔");
	var datetime = split_timeslot[0].split("━");
	var date = datetime[0];
	var start_time = datetime[1];
	date_start_time.push(new Date(date + " " + start_time));
}
date_start_time.sort(function(a, b){return a - b});
//console.log(date_start_time);
//console.log(date_start_time.toLocaleString());


for(var j = 0; j<date_start_time.length; j++){
	var date_time_string = date_start_time[j].toLocaleString();
	//console.log(date_time_string);
	var split_sorted = date_time_string.split(", ");
	var sorted_date = split_sorted[0];
	var sorted_start_time = split_sorted[1].substring(0, 5) + split_sorted[1].substring(8, 11);

	//console.log(sorted_date);
	//console.log(sorted_start_time);


	for(var k = 0; k<date_start_time.length; k++){
		if(timeslots[k].includes(sorted_date) && timeslots[k].includes(sorted_start_time)){
			final_timeslot.push(timeslots[k]);
			k = date_start_time.length;
		}
	}
}
//console.log(final_timeslot);
