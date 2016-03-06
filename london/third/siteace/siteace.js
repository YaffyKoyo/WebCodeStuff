Websites = new Mongo.Collection("websites");
Comments = new Mongo.Collection("comments");


if (Meteor.isClient) {

	/////
	// template helpers
	/////
	Router.configure({
		layoutTemplate: 'ApplicationLayout'
	});

	Router.route('/', function () {
		this.render('welcome', {
			to:"navbar"
		});
	});

	Router.route('/content', function () {
		this.render('navbar', {
			to:"navbar"
		});
		this.render('website_form',{
			to:"form"
		});
		this.render('website_list',{
			to:"main"
		})
	});

	Router.route('/content/:_id', function () {
		this.render('navbar', {
			to:"navbar"
		});
		// this.render('comment_form',{
		// 	to:"form"
		// });
		this.render('website_item_ind',{
			to:"main",
			data:function(){
				return Websites.findOne({_id:this.params._id});
			}
		})

	});


Accounts.ui.config({
	passwordSignupFields: "USERNAME_AND_EMAIL"
});


	// helper function that returns all available websites
	Template.website_list.helpers({
		websites:function(){
			return Websites.find({},{sort:{voting:-1}});
		}
	});

	Template.comment_list.helpers({
		comments:function(){
			return Comments.find({websiteID:this._id},{sort:{createdOn:-1}});
		},
		getUser:function(user_id){
			var user = Meteor.users.findOne({_id:user_id});
			if(user){
				return user.username;
			}else{
				return "anon";
			}
		}
	});






	/////
	// template events
	/////




	Template.website_item.events({
		"click .js-upvote":function(event){
			// example of how you can access the id for the website in the database
			// (this is the data context for the template)
			var website_id = this._id;
			var website_upvote = this.upVote;
			var voting = this.voting;
			console.log("Up voting website with id "+website_id);
			// put the code in here to add a vote to a website!
			var rating =0; //(event.currentTarget).data("userrating");
			//console.log(rating);
			Websites.update({_id:website_id}, {$set:{upVote:website_upvote+1}});
			Websites.update({_id:website_id}, {$set:{voting:voting+1}});

			console.log("voting of "+website_id+": "+website_upvote);

			return false;// prevent the button from reloading the page
		},
		"click .js-downvote":function(event){

			// example of how you can access the id for the website in the database
			// (this is the data context for the template)
			var website_id = this._id;
			var website_downvote = this.downVote;
			var voting = this.voting;
			console.log("Down voting website with id "+website_id);
			// put the code in here to remove a vote from a website!
			var rating = 0;// $(event.currentTarget).data("userrating");
			//console.log(rating);
			Websites.update({_id:website_id}, {$set:{downVote:website_downvote+1}});
			Websites.update({_id:website_id}, {$set:{voting:voting-1}});

			console.log("voting of "+website_id+": "+website_downvote);

			return false;// prevent the button from reloading the page
		}
	})

	Template.comment_form.events({
		"click .js-toggle-comment-form":function(event){
			$("#comment_form").toggle('slow');
			console.log("should trigger something")
		},

		"submit .js-save-comment-form":function(event){
			var comment_content = event.target.content.value;
			console.log("comment_content: "+comment_content);
			var user_id = Meteor.user()._id;
			console.log("user ID: "+user_id);
			var website_id = this._id;
			console.log("website_id: "+website_id);

			if(Meteor.user()){
				Comments.insert({
					websiteID:website_id,
					comment_content:comment_content,
					user: user_id,
					createdOn:new Date()
				})
			}
			return false;
		}
	});

	Template.website_form.events({
		"click .js-toggle-website-form":function(event){
			$("#website_form").toggle('slow');
		},
		"submit .js-save-website-form":function(event){

			// here is an example of how to get the url out of the form:
			var url = event.target.url.value;
			console.log("The url they entered is: "+url);
			var title = event.target.title.value;
			console.log("The title they entered is: "+title);
			var description = event.target.description.value;
			console.log("The title they entered is: "+description);
			var simple_des = description.replace(/(([^\s]+\s\s*){5})(.*)/,"$1…");


			if(Meteor.user()){
				Websites.insert({
					url:url,
					title:title,
					description:description,
					simple_des:simple_des,
					createdOn:new Date(),
					upVote:0,
					downVote:0,
					voting:0
					//createdBy:Meteor.user()._id
				})
			}
			//  put your website saving code in here!

			return false;// stop the form submit from reloading the page

		}
	});
}


if (Meteor.isServer) {
	// start up function that creates entries in the Websites databases.
	Meteor.startup(function () {
		// code to run on server at startup
		if(!Comments.findOne()){
			console.log("No comments yet. Creating start data.");
			Comments.insert({
				comment_content:"aaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaa",
				createdOn: new Date(),
			});
			Comments.insert({
				comment_content:"bbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbb",
				createdOn: new Date(),
			});
			Comments.insert({
				comment_content:"ccccccccccccccccccccccccccccccccccc",
				createdOn: new Date(),
			});

		}

		if (!Websites.findOne()){
			console.log("No websites yet. Creating starter data.");
			Websites.insert({
				title:"Goldsmiths Computing Department",
				url:"http://www.gold.ac.uk/computing/",
				description:"This is where this course was developed.",
				simple_des:"This is where this course...",
				createdOn:new Date(),
				upVote:0,
				downVote:0,
				voting:0
			});
			Websites.insert({
				title:"University of London",
				url:"http://www.londoninternational.ac.uk/courses/undergraduate/goldsmiths/bsc-creative-computing-bsc-diploma-work-entry-route",
				description:"University of London International Programme.",
				simple_des:"University of London...",
				upVote:0,
				downVote:0,
				voting:0,
				createdOn:new Date()
			});
			Websites.insert({
				title:"Coursera",
				url:"http://www.coursera.org",
				description:"Universal access to the world’s best education.",
				simple_des:"Universal access to the...",
				upVote:0,
				downVote:0,
				voting:0,
				createdOn:new Date()
			});
			Websites.insert({
				title:"Google",
				url:"http://www.google.com",
				description:"Popular search engine.",
				simple_des:"Popular search engine...",
				upVote:0,
				downVote:0,
				voting:0,
				createdOn:new Date()
			});
		}
	});
}
