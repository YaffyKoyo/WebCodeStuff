// subscribe to read data
Meteor.subscribe("chats",function(){
  console.log( "chats data ready." );
});
Meteor.subscribe("users",function(){
  console.log( "users ready." );
});
Meteor.subscribe('emojis',function(){
  console.log('emojis ready.')
});

// set up the main template the the router will use to build pages
Router.configure({
  layoutTemplate: 'ApplicationLayout'
});
// specify the top level route, the page users see when they arrive at the site
Router.route('/', function () {
  console.log("rendering root /");
  this.render("navbar", {to:"header"});
  this.render("lobby_page", {to:"main"});  
});

Router.route('/instruccions', function () {
  console.log("rendering  /instruccions ");
  this.render("navbar", {to:"header"});
  this.render("instruccions", {to:"main"});  
});

// specify a route that allows the current user to chat to another users
Router.route('/chat/:_id', function () {
  if (!Meteor.userId()) {
    console.log("   ...there is not user logg in");
    alert("you must be logged in to chat with this user!!");
    Router.go('/');
  } else {
    // the user they want to chat to has id equal to 
    // the id sent in after /chat/... 
    var otherUserId = this.params._id;
    console.log("   ...otherUserId: "+otherUserId);
    console.log("   ...Meteor.userId(): "+Meteor.userId());
    
    Meteor.call("addChat", otherUserId, function(error, result) {
    // 'result' is the method return value
    });
  };

    if (chatId){// looking good, save the id to the session
      Session.set("chatId",chatId);
      console.log("   ...establim Session.set('chatId',chatId): "+Session.get("chatId"));
    }
    this.render("navbar", {to:"header"});
    this.render("chat_page", {to:"main"});  
});

/////////////////////////////////////////////
///helper functions 
////////////////////////////////////////////////////
///
Template.available_user_list.helpers({
  users:function(){
    console.log('>>>>>>entrant a users de available_user_list.helpers');
    console.log('<<<<<<<sortint de users');
    return Meteor.users.find();
  }
});

Template.available_user.helpers({
  getUsername:function(userId){
    console.log('>>>>>>entrant a getUsername available_user.helpers');
    user = Meteor.users.findOne({_id:userId});
    console.log('<<<<<<<sortint de getUsername');
    return user.profile.username;
  }, 
  isMyUser:function(userId){
    console.log('>>>>>>entrant a isMyUser available_user.helpers');
    if (userId == Meteor.userId()){
      console.log('<<<<<<< TRUE sortint de isMyUser');
      return true;
    }
    else {
      console.log('<<<<<<< FALSE sortint de isMyUser');
      return false;
    }
  }
});


Template.chat_page.helpers({
  messages:function(){
    console.log('>>>>>>entrant a messages de chat_page.helpers');
    console.log("   ...Session.get('chatId'): "+Session.get("chatId"));
    var chat = Chats.findOne({_id:Session.get("chatId")});
    console.log("   ...chat.messages:");
    console.log (chat.messages);
    console.log('<<<<<<<sortint de  messages');
    return chat.messages;
  }, 
  other_user:function(){
    console.log('>>>>>>entrant a other_user de chat_page.helpers');
    console.log('<<<<<<<sortint de  other_user');
    return ""
  } 

});

Template.chat_message.helpers({
  user_msg_current:function(){
    console.log('>>>>>>entrant a user_msg_current de chat_message.helpers');
    var userlogged = Meteor.userId();
    console.log('userlogged : '+ userlogged);
    console.log("this:");
    console.log(this);
    var usermsg =this.user_msg;
    console.log('usermsg : '+this.user_msg);
    if (userlogged==usermsg) {
      console.log("return true");
      return true;
    } else {
      console.log("return false");
      return false;
    }
  }  
});

Template.img_msg.helpers({
  usermsg:function(){
    console.log('>>>>>>entrant a usermsg de img_msg.helpers');
    console.log('    ...this =');
    console.log(this);
    if (!this.user_msg) {
      console.log('    ...avatar = noavatar.png');
      console.log('<<<<<<<sortint de usermsg');
      return avatar="noavatar.png";
    } else {      
      var avatar= Meteor.users.findOne({_id:this.user_msg}).profile.avatar;
      console.log('    ...avatar =');
      console.log(avatar);
      console.log('<<<<<<<sortint de usermsg');
      return avatar;
    }
  },
  username:function(){
    console.log('>>>>>>entrant a username de img_msg.helpers');
    console.log('    ...this =');
    console.log(this);
    if (!this.user_msg) {
      console.log('    ...username = "anonimous"');
      console.log('<<<<<<<sortint de  username');
      var username ="anonimous";
      return username ;
    } else {      
      var username= Meteor.users.findOne({_id:this.user_msg}).profile.username;
      console.log('    ...username =');
      console.log(username);
      console.log('<<<<<<<sortint de  username');
      return username;
    }
  },
  datefromNow:function(){
    console.log('>>>>>>entrant a username de img_msg.helpers');
    console.log('    ...this =');
    console.log(this);

    if (!this.createdOn) {
      console.log('    ...date = "-"');
      console.log('<<<<<<<sortint de  date');
      
      return datefromNow ;
    } else {      
      var datefromNow = moment(this.createdOn).fromNow();
      console.log('    ...datefromNow = ');
      console.log(datefromNow);
      console.log('<<<<<<<sortint de  date');
      
      return datefromNow;
    }
  },
  textemoji:function(){
    console.log('>>>>>>entrant a texemoji de img_msg.helpers');
    console.log('    ...this =');
    console.log(this);

    if (!this.text) {
      console.log('    ...text = "-"');
      console.log('<<<<<<<sortint de textemoji');
      return
    } else {  
      console.log('    ...text = '+this.text);
      console.log('<<<<<<<sortint de textemoji');    
      var textemoji = Emoji.convert(this.text);  
      
      
      return textemoji;
    }
  }

});


Template.msg_img.helpers({
  usermsg:function(){
    console.log('>>>>>>entrant a usermsg de msg_img.helpers');
    console.log('    ...this =');
    console.log(this);
    if (!this.user_msg) {
      console.log('    ...avatar = noavatar.png');
      console.log('<<<<<<<sortint de usermsg');
      return avatar="noavatar.png";
    } else {      
      var avatar= Meteor.users.findOne({_id:this.user_msg}).profile.avatar;
      console.log('    ...avatar =');
      console.log(avatar);
      console.log('<<<<<<<sortint de usermsg');
      return avatar;
    }
  },
  username:function(){
    console.log('>>>>>>entrant a username de msg_img.helpers');
    console.log('    ...this =');
    console.log(this);
    if (!this.user_msg) {
      console.log('    ...username = "anonimous"');
      console.log('<<<<<<<sortint de  username');
      var username ="anonimous";
      return username ;
    } else {      
      var username= Meteor.users.findOne({_id:this.user_msg}).profile.username;
      console.log('    ...username =');
      console.log(username);
      console.log('<<<<<<<sortint de  username');
      return username;
    }
  },
  datefromNow:function(){
    console.log('>>>>>>entrant a username de img_msg.helpers');
    console.log('    ...this =');
    console.log(this);

    if (!this.createdOn) {
      console.log('    ...date = "-"');
      console.log('<<<<<<<sortint de  date');
      
      return datefromNow ;
    } else {      
      var datefromNow = moment(this.createdOn).fromNow();
      console.log('    ...datefromNow = ');
      console.log(datefromNow);
      console.log('<<<<<<<sortint de  date');
      
      return datefromNow;
    }
  },
  textemoji:function(){
    console.log('>>>>>>entrant a texemoji de img_msg.helpers');
    console.log('    ...this =');
    console.log(this);

    if (!this.text) {
      console.log('    ...text = "-"');
      console.log('<<<<<<<sortint de textemoji');
      return
    } else {      
      var textemoji = Emoji.convert(this.text);  
      console.log('    ...text = '+text);
      console.log('<<<<<<<sortint de textemoji');
      
      return textemoji;
    }
  }
 
});

Template.form_message_send.helpers({
  usersending:function(){
    console.log('>>>>>>entrant a usersending de form_message_send.helpers');
    console.log('    ...Meteor.users.findOne({_id:Meteor.userId()}).profile.avatar');
    console.log("Meteor.userId() ="+Meteor.userId());
    console.log(Meteor.users.findOne({_id:Meteor.userId()}).profile.avatar);
    var usersending= Meteor.users.findOne({_id:Meteor.userId()}).profile.avatar;
    return usersending;
  },
});

Template.userlogged.helpers({
 getUserlogged:function(){
    console.log('>>>>>>entrant a getUserlogged:function(userId) d userlogged.helpers');
    var user = Meteor.users.findOne(Meteor.userId());
    if(user){      
      console.log('    ...user =');
      console.log(user);
      console.log("    ...retorna user.profile.username =");
      console.log(user.profile.username);
      console.log('<<<<<<<sortint de getUserlogged');
      return user.profile.username;
    } else {
      console.log("    ...retorna 'anonim'");
      console.log('<<<<<<<sortint de getUserlogged');
      return "anonimous";
    }
  }, 
});


///////////////////////////////////////////////////////////////////////////////
///////////////////////////////////////////////////////////////////////////////
// EVENTS FUNCTIONS
///

Template.chat_page.events({
// this event fires when the user sends a message on the chat page
  'submit .js-send-chat':function(event){
    console.log('>>>>>>entrant a submit .js-send-chat DE chat_page.events ');
    // stop the form from triggering a page reload
    event.preventDefault();
    // see if we can find a chat object in the database
    // to which we'll add the message
    var chat = Chats.findOne({_id:Session.get("chatId")});
    //div
    var text2=$( "div#tx-emj" ).text()
    console.log('text2 -$( "div#tx-emj" ).text()-: '+ text2);
    var text3= $( "div#tx-emj" ).html();
    console.log('text3 -$( "div#tx-emj" ).html();-: '+ text3);
    //textarea
    //console.log('   ...chat: '+chat);
    //var text= event.target.chat.value;
    //console.log('   ...text de textarea: '+text);
    //insert data trought a meteor method
    console.log('-------->>>>>>>>>>sortint a METEOR.CALL');
    Meteor.call("addMsg",text3,chat._id);
    console.log('--------<<<<<<<<<<TORNANT de METEOR.CALL');
    // reset the form
    // reset textares
    //event.target.chat.value = "";
    // reset div
    $( "div#tx-emj" ).html("");
  }
})


