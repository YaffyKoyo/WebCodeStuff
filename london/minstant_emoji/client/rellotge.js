Meteor.setInterval(function() {
	Session.set("time", new Date());
    //console.log("time"+ new Date());
  }, 1000);


Template.clock.helpers({
	clock:function(){
		//console.log('>>>>>>entrant a clock.helpers');
		Session.get('time');//only for reactive updating
		updateClock();
	return;
	}
});

function updateClock(){
		//console.log('>>>>>>entrant a updateClock()');
        var now = moment(),
            second = now.seconds() * 6,
            minute = now.minutes() * 6 + second / 60,
            hour = ((now.hours() % 12) / 12) * 360 + 90 + minute / 12;

        $('#hour').css("transform", "rotate(" + hour + "deg)");
        $('#minute').css("transform", "rotate(" + minute + "deg)");
        $('#second').css("transform", "rotate(" + second + "deg)");
    }