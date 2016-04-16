//main.js ---SHARED
////////////////////////////////////////////////////////////////////
// code that is shared between client and server, i.e. sent to both
// method definitions
Meteor.methods({
// adding new messages
  addMsg: function (text,chatId) {
      console.log(">>>>>>entrant a addMsg method running!");
      if (!this.userId){// we have a user
        throw new Meteor.Error("not-user authorized");
      };
      console.log("    ...this.userId:");
      console.log(this.userId);console.log(">>>>>>entrant a addMsg method running!");
      console.log("   ...text:");
      console.log(text);
      console.log("   ...chatId :");
      console.log(chatId)
      chat= Chats.findOne({_id:chatId});
      console.log("   ...chat =");
      console.log(chat);
      if (chat){// ok - we have a chat to use
        var msgs = chat.messages; // pull the messages property
        if (!msgs){// no messages yet, create a new array
          msgs = [];
        }
    };
      msgs.push({
        text: text,
        user_msg:this.userId,
        createdOn: new Date()
      });
      // put the messages array onto the chat object
      chat.messages = msgs;
      console.log("   ...chat =");
      console.log(chat);
      console.log("<<<<<sortint de addMsg method running!");
      // update the chat object in the database.
      return Chats.update(chat._id, chat);
  },
  addChat: function(otherUserId){
    console.log("************>>>>>>entrant a addChat method running!");
    console.log("   ...otherUserId: "+otherUserId);
    // Check user authenticated
    if (!this.userId) {
      throw new Meteor.Error(401, 'Unauthorized access');
    }
    console.log("   ...this.userId: "+this.userId);
    var filter = {$or:[
                {user1Id:this.userId, user2Id:otherUserId}, 
                {user2Id:this.userId, user1Id:otherUserId}
                ]};
    // find a chat that has two users that match current user id
    // and the requested user id
    var chat = Chats.findOne(filter);
    console.log("chat: ");
    console.log(chat);
    if (!chat){// no chat matching the filter - need to insert a new one
      console.log('   ...no  hi ha xat');
      chatId = Chats.insert({
        user1Id:Meteor.userId(), 
        user2Id:otherUserId
      });
      console.log("   ...chat._id del chat despres de crear-lo: "+chatId);
    }
    else {// there is a chat going already - use that. 
      console.log("   ...tenim un chat existent");
      chatId = chat._id;
      console.log("   ...chatId del chat: "+chatId);
    }
        
    console.log("************<<<<<<sortint de addChat method running!");
    return chatId;
  }
});
