Template.emoticons.helpers({
  // rerrieve a list of emoticons
  emojis:function(){
  	return Emojis.find();
  }
});

Template.emoticons.events({
   // load doc button
  "click .js-putEmoji":function(event){
    console.log(this.toHTML());
    console.log(this.toHex());
    var text = $( "div#tx-emj" ).text();
    console.log($( "div#tx-emj" ).text());
    //console.log(document.getElementById("tx-emj").value);
    //console.log(document.getElementById("tx-emj2").value);
    console.log(this.emoji);
    console.log(this.alias);
    console.log(this);
    console.log()
    $( "div#tx-emj" ).append(this.toHTML());
    //$("#tx-emj2").insertAtCaret(this.emoji);
   }
});


//insert emji at cursor position in textarea
jQuery.fn.extend({
	insertAtCaret: function(myValue){
		console.log(">>>>>>>>>>> entering insertAtCaret");
 		return this.each(function(i) {
 			console.log(">>>>>>>>>>> entering  this.each(function(i) insertAtCaret");
    		if (document.selection) {
    			console.log(">>>>>>>>>>> entering insertAtCaret");
			    //For browsers like Internet Explorer
			    this.focus();
			    var sel = document.selection.createRange();
			    sel.text = myValue;
			    console.log("sel.text: "+ sel.text);
			    this.focus();
		    }
		    else if (this.selectionStart || this.selectionStart == '0') {
		      //For browsers like Firefox and Webkit based
		      var startPos = this.selectionStart;
		      var endPos = this.selectionEnd;
		      var scrollTop = this.scrollTop;
		      console.log("this.value: "+ this.value);
		      this.value = this.value.substring(0, startPos)+myValue+this.value.substring(endPos,this.value.length);
		      this.focus();
		      this.selectionStart = startPos + myValue.length;
		      this.selectionEnd = startPos + myValue.length;
		      this.scrollTop = scrollTop;
		    } else {
		      this.value += myValue;
		      console.log("this.value2: "+ this.value);
		      this.focus();
		    }
		});
	}
	
});
