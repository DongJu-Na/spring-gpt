
$(document).ready(function(e) {
	function favicon() {
		favicon = favicon == 1 ? 2 : 1;
		$('.favicon').attr('href','favicon' + favicon + ".png");
	}
   console.clear();
   var previouscommands = [];
   var currentcommand = 0;
  
   function init() {
      setInterval(time);
      console.clear();
      console.log(new Date().getTime());
	log("Website","██████╗&nbsp;&nbsp;██████╗&nbsp;███╗&nbsp;&nbsp;&nbsp;██╗&nbsp;██████╗&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;██╗██╗&nbsp;&nbsp;&nbsp;██╗&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;███╗&nbsp;&nbsp;&nbsp;██╗&nbsp;█████╗&nbsp;");
	log("Website","██╔══██╗██╔═══██╗████╗&nbsp;&nbsp;██║██╔════╝&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;██║██║&nbsp;&nbsp;&nbsp;██║&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;████╗&nbsp;&nbsp;██║██╔══██╗");
	log("Website","██║&nbsp;&nbsp;██║██║&nbsp;&nbsp;&nbsp;██║██╔██╗&nbsp;██║██║&nbsp;&nbsp;███╗&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;██║██║&nbsp;&nbsp;&nbsp;██║█████╗██╔██╗&nbsp;██║███████║");
	log("Website","██║&nbsp;&nbsp;██║██║&nbsp;&nbsp;&nbsp;██║██║╚██╗██║██║&nbsp;&nbsp;&nbsp;██║██&nbsp;&nbsp;&nbsp;██║██║&nbsp;&nbsp;&nbsp;██║╚════╝██║╚██╗██║██╔══██║");
	log("Website","██████╔╝╚██████╔╝██║&nbsp;╚████║╚██████╔╝╚█████╔╝╚██████╔╝&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;██║&nbsp;╚████║██║&nbsp;&nbsp;██║");
	log("Website","╚═════╝&nbsp;&nbsp;╚═════╝&nbsp;╚═╝&nbsp;&nbsp;╚═══╝&nbsp;╚═════╝&nbsp;&nbsp;╚════╝&nbsp;&nbsp;╚═════╝&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;╚═╝&nbsp;&nbsp;╚═══╝╚═╝&nbsp;&nbsp;╚═╝");
	log("Website","Github [https://github.com/DongJu-Na]");
	  setInterval(favicon,500);
   }
   
   function log(name, information) {
	  try{
		  var d = new Date();
	      var hours = ((d.getHours() < 10) ? "0" : "") + d.getHours();
	      var minutes = ((d.getMinutes() < 10) ? "0" : "") + d.getMinutes();
	      var seconds = ((d.getSeconds() < 10) ? "0" : "") + d.getSeconds();
	      var colour = "whitet";
	      var postcolour = "";
	
	      switch (name[0]) {
	         case "!":
	            postcolour = " important";
	            name = name.substr(1);
	            break;
	      }
	      switch (name) {
	         case "Website":
	            colour = "redt";
	            break;
	         case "Server":
	            colour = "aquat";
	            break;
	         case "Client":
	            colour = "bluet";
	            break;
	         case "User":
	            colour = "greent";
	            postcolour = " selft";
	            break;
	      }
	      if (information[0] == "A" && information[1] == "!") {
	         information = information.substr(2);
	         information = information.replace(/ /g, '\u00A0');
	      }
	      if (information[0] == "E" && information[1] == "!") {
	         information = information.substr(2);
	         postcolour = " important";
	      }
	
	      while (information.indexOf("](") >= 0) { //URL parser
	
	         var NAMEregExp = /\(([^)]+)\)/;
	         var uname = NAMEregExp.exec(information)[1];
	
	         var URLregExp = /\[([^)]+)\]/;
	         var url = URLregExp.exec(information)[1];
	         var newpage = false;
	         if (url[0] == "^") {
	            newpage = true;
	            url = url.substr(1);
	         }
	         var start = information.indexOf("[");
	         var end = information.indexOf(")");
	         if (newpage) {
	            information = information.replace(information.substring(start, end + 1), "").splice(start, 0, '<a href="' + url + '" target="_blank">' + uname + '</a>');
	         } else {
	            information = information.replace(information.substring(start, end + 1), "").splice(start, 0, '<a href="' + url + '">' + uname + '</a>');
	         }
	      }
	      var tobold = true;
	      var boldnumber = 0;
	      for (var i = 0; i < information.length; i++) {
	         if (information[i] == "*" && information[i - 1] != "*" && information[i + 1] != "*") {
	            boldnumber++;
	         }
	      }
	      while (information.indexOf("*") >= 0) { //Bold parser
	         var pos = information.indexOf("*");
	         information = information.replace("*", "");
	         if (tobold) {
	            information = information.splice(pos, 0, '<b>');
	         } else {
	            information = information.splice(pos, 0, '</b>');
	         }
	         tobold = !tobold;
	         if (tobold && boldnumber <= 1) {
	            break;
	         }
	         //information = '<a href="' + url + '">' + uname + '</a>'; //working
	      }
	      var tounderline = true;
	      var underlinenumber = 0;
	      for (var i = 0; i < information.length; i++) {
	         if (information[i] == "*" && information[i - 1] != "*" && information[i + 1] != "*") {
	            underlinenumber++;
	         }
	      }
	      while (information.indexOf("**") >= 0) { //Bold parser
	         var pos = information.indexOf("**");
	         information = information.replace("**", "");
	         if (tounderline) {
	            information = information.splice(pos, 0, '<u>');
	         } else {
	            information = information.splice(pos, 0, '</u>');
	         }
	         tounderline = !tounderline;
	         if (tounderline && underlinenumber <= 1) {
	            break;
	         }
	      }
	      
	      $(".stream").append('<div class="line">' +
	         '<p class="time">[' + hours + ":" + minutes + ":" + seconds + ']</p>' +
	         '<p class="name ' + colour + '">' + name + '</p>' +
	         '<p class="information' + postcolour + '">' + information + '</p>' +
	         '</div>');
	      $(document).scrollTop($(document).height() - $(window).height());
	  }catch(err){
		  
	  }
	  
    
   }
	var timestring = "";
   function time() {
      var d = new Date();
      var hours = d.getHours();
      var minutes = d.getMinutes();
      var seconds = d.getSeconds();
      if (hours < 10) {
         hours = "0" + hours;
      }
      if (minutes < 10) {
         minutes = "0" + minutes;
      }
      if (seconds < 10) {
         seconds = "0" + seconds;
      }
	  var temptimestring = "[" + hours + ":" + minutes + ":" + seconds + "]";
	  if (temptimestring != timestring) {
		  timestring = temptimestring;
      	$(".editline .time").text(timestring);
	  }
   }

   var ctrldown = false;
   $(".editline .edit").keydown(function(e) {
	  try{
		  var text = $(".editline .edit").text();
	      if (e.which == 13 && text !== "" && !ctrldown) {
			 e.preventDefault();
	         var commands = text.split(' ');
	         if (commands[0] == "help") {
	            text = "/" + text;
	         }
	         $(".editline .edit").text("");
	
	         previouscommands[currentcommand] = text;
	         currentcommand = previouscommands.length;
	         $(".editline .edit").keydown(35);
	         cmd(commands[0], text, commands);
	      }
	      if (e.which == 38) { //up
	         if (currentcommand > 0) {
	            currentcommand--;
	            $(".editline .edit").text(previouscommands[currentcommand]);
	         }
	      }
	      if (e.which == 40) { //down
	         if (currentcommand < previouscommands.length) {
	            currentcommand++;
	            $(".editline .edit").text(previouscommands[currentcommand]);
	         }
	      }  
	  }catch(err){
		  console.error(err);
		  $(".information.edit").empty();
	  }
   });

   function cmd(command, words, word) {
         output = word[0] + "";
		 log("Client", output);
		 $.ajax({
    		    type : 'post',
    		    url : '/api/answer',
    		    async :false,
    		    headers : { 
    		      "Content-Type" : "application/json",
    		    },
    		    dataType : 'json', 
    		    data : JSON.stringify({
					"text" : output
				}),
    		    success : function(result) {
    		        if(result.choices.length > 0){
    					log("Server", result.choices[0].message.content);	     	
    		        }
    		    },
    		    error : function(request, status, error) {
    		        console.error(error);
    		    }
    		})
   }


   String.prototype.splice = function(idx, rem, str) {
      return this.slice(0, idx) + str + this.slice(idx + Math.abs(rem));
   };
   init();
});