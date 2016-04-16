// start up script that creates some users for testing
// users have the username 'user1@test.com' .. 'user8@test.com'
// and the password test123 

Meteor.startup(function () {
  console.log(">>>>>entrant a Meteor.startup");
  if (!Meteor.users.findOne()){
    for (var i=1;i<9;i++){
      var email = "user"+i+"@test.com";
      var username = "user"+i;
      var avatar = "ava"+i+".png"
      console.log("creating a user with password 'test123' and username/ email: "+email);
      Meteor.users.insert({profile:{username:username, avatar:avatar}, emails:[{address:email}],services:{ password:{"bcrypt" : "$2a$10$I3erQ084OiyILTv8ybtQ4ON6wusgPbMZ6.P33zzSDei.BbDL.Q4EO"}}});
    }
  } 
  console.log("<<<<<sortint de Meteor.startup");
});

// return only user's login chats
Meteor.publish("chats", function(){
  console.log(">>>>>entrant a Meteor.publish('chats')");
  var filter = {$or:[
                 {$and: [ 
                    { user1Id:this.userId }, { user1Id: { $exists: true } } 
                  ], user2Id:{ $exists: true}}, 
                  {$and: [ 
                    { user2Id:this.userId }, { user2Id: { $exists: true } } 
                  ], user1Id:{ $exists: true}}
              ]};
  console.log("<<<<<sortint de Meteor.publish('chats')");
  return Chats.find(filter);
}); 

 // All users
Meteor.publish("users", function(){
  return Meteor.users.find();
});


Meteor.publish('emojis', function() {
  // Here you can choose to publish a subset of all emojis
  // if you'd like to.
  return Emojis.find();
});

