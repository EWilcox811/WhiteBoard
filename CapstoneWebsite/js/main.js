
var firstTable = true;


function getProfessorData() {
    headers = ["Username", "User ID", "First Name", "Last Name", "Email", "Date Joined", "Rating"]
    var userarray = new Array();
    
	jQuery.getJSON("http://104.248.0.248/persons", function(data) {
	}.bind(this)).fail(function(xhr, status) {
    console.log("The JSON chart failed to load.");
    console.log(status);
	}).done(function(data) {
		users = data["_embedded"]["professors"];
        var userinfo = new Array();
		for(i in users) {
			var uname = users[i]["username"];
			var uid = users[i]["_links"]["self"]["href"];
			uid = uid.split("http://104.248.0.248/professors/")[1];
            var fname = users[i]["firstName"];
            var lname = users[i]["lastName"];
            var email = users[i]["email"];
            var date = new Date(users[i]["dateJoined"]);
            var rating = users[i]["rating"];
			userinfo = [uname, uid, fname, lname, email, date, rating];
			
		}
        for(i in userinfo) {
            if(userinfo[i] == "" || userinfo[i] == undefined){
                userinfo[i] = "No Data Available";
            } 
        }

        userarray.push(userinfo);
        // User Headers
        
		drawTable(headers, userarray);
	});
    
};

function getStudentData() {
    headers = ["Username", "User ID", "First Name", "Last Name", "Email", "Date Joined", "GPA"]
    var userarray = new Array();
    
    jQuery.getJSON("http://104.248.0.248/persons", function(data) {
    }.bind(this)).fail(function(xhr, status) {
    console.log("The JSON chart failed to load.");
    console.log(status);
    }).done(function(data) {
        users = data["_embedded"]["students"];
        var userinfo = new Array();
        for(i in users) {
            var uname = users[i]["username"];
            var uid = users[i]["_links"]["self"]["href"];
            uid = uid.split("http://104.248.0.248/students/")[1];
            var fname = users[i]["firstName"];
            var lname = users[i]["lastName"];
            var email = users[i]["email"];
            var date = new Date(users[i]["dateJoined"]);
            var gpa = users[i]["gpa"];
            userinfo = [uname, uid, fname, lname, email, date, gpa];
            for(i in userinfo) {
                if(userinfo[i] == "" || userinfo[i] == undefined){
                    userinfo[i] = "No Data Available";
                } 
            }
            userarray.push(userinfo);
            
        }

        // User Headers
        
        drawTable(headers, userarray);
    });
    
};

function getClassData() {
    headers = ["Class Subject", "Class Number", "Class Description"]
    var classarray = new Array();
    
    jQuery.getJSON("http://104.248.0.248/classes", function(data) {
    }.bind(this)).fail(function(xhr, status) {
    console.log("The JSON chart failed to load.");
    console.log(status);
    }).done(function(data) {
        classes = data["_embedded"]["classes"];
        var classinfo = new Array();
        for(i in classes) {
            var subject = classes[i]["subject"];
            var classnumber = classes[i]["classNumber"];
            var desc = classes[i]["description"];
            classinfo = [subject, classnumber, desc];
            for(i in classinfo) {
                if(classinfo[i] == "" || classinfo[i] == undefined){
                    classinfo[i] = "No Data Available";
                } 
            }
            classarray.push(classinfo);
            
        }

        // User Headers
        
        drawTable(headers, classarray);
    });
    
};

function getSessionData() {
    headers = ["Session Name", "Start Date", "End Date", "Class Number", "Class Subject", "Class Description"]
    var sessionarray = new Array();
    
    jQuery.getJSON("http://104.248.0.248/sessions", function(data) {
    }.bind(this)).fail(function(xhr, status) {
    console.log("The JSON chart failed to load.");
    console.log(status);
    }).done(function(data) {
        sessions = data["_embedded"]["sessions"];
        var sessioninfo = new Array();
        for(i in sessions) {
            var sessionname = sessions[i]["sessionName"];
            var startdate = new Date(sessions[i]["startDate"]);
            var enddate = new Date(sessions[i]["endDate"]);
            var subject = sessions[i]["subject"];
            var sessionnumber = sessions[i]["classNumber"];
            var desc = sessions[i]["description"];
            sessioninfo = [sessionname, startdate, enddate, subject, sessionnumber, desc];
            for(i in sessioninfo) {
                if(sessioninfo[i] == "" || sessioninfo[i] == undefined){
                    sessioninfo[i] = "No Data Available";
                } 
            }
            sessionarray.push(sessioninfo);
            
        }

        // User Headers
        
        drawTable(headers, sessionarray);
    });
    
};

function getCommentData() {
    headers = ["Comment ID", "Parent Comment", "Comment Date", "Commenter User Name", "Comment", "Number of Replies", "Anonymous", "Session Name"]
    var commentarray = new Array();
    
    jQuery.getJSON("http://104.248.0.248/comments", function(data) {
    }.bind(this)).fail(function(xhr, status) {
    console.log("The JSON chart failed to load.");
    console.log(status);
    }).done(function(data) {
        comments = data["_embedded"]["comments"];
        var commentinfo = new Array();
        for(i in comments) {
            var commentid = comments[i]["id"];
            var parentcomment = comments[i]["parentId"];
            if(commentid == parentcomment) {
                parentcomment = "Is Parent";
            }
            var commentdate = new Date(comments[i]["dateCreated"]);
            var comment = comments[i]["message"];
            var username = comments[i]["userName"];
            var numofreplies;
            if(0 == comments[i]["numOfRelies"]) {
                numofreplies = "0";
            }
            else {
                numofreplies = comments[i]["numOfRelies"];
            }
            var isAnonymous;
            if("0" == comments[i]["isAnonymous"] ) {
                isanonymous = "No";
            }
            else {
                isanonymous = "Yes";
            }
            var sessionname = comments[i]["sessionName"];
            commentinfo = [commentid, commentdate, parentcomment, username, comment, numofreplies, isanonymous, sessionname];
            for(i in commentinfo) {
                if(commentinfo[i] == "" || commentinfo[i] == undefined){
                    commentinfo[i] = "No Data Available";
                } 
            }
            commentarray.push(commentinfo);
            
        }

        // User Headers
        
        drawTable(headers, commentarray);
    });
    
};


function drawTable(headers, tabledata) {

    

    // get the reference for the body
    var div1 = document.getElementById('tablediv');



    // creates a <table> element
    //var tbl = document.createElement("table");
    var tbl = document.getElementById('tabledata');
    var tablebody = document.getElementById('tablebody');

    if(!firstTable) {
        removeTable();
    }

    // Create Columns
    var tr = document.createElement('TR');
    tablebody.appendChild(tr);
    for (i = 0; i < headers.length; i++) {
    	var th = document.createElement('TH');
    	th.width = '75';
    	th.appendChild(document.createTextNode(headers[i]));
    	tr.appendChild(th);
    }

    // Table Rows
    
    tablebody.appendChild(tr);
    for(i = 0; i < tabledata.length;i++) {
    	var tr = document.createElement('TR');
    	for(j = 0; j < tabledata[i].length;j++)
    	{
    		var td = document.createElement('TD');
    		td.appendChild(document.createTextNode(tabledata[i][j]));
    		tr.appendChild(td);
    	}
    	tablebody.appendChild(tr);
    }

    firstTable = false;
}

function removeTable() {
    jQuery("#tablebody tr").remove(); 
}


window.onload=getProfessorData; 


/* Set the width of the side navigation to 250px and the left margin of the page content to 250px */
function openNav() {
  document.getElementById("mySidenav").style.width = "250px";
  document.getElementById("main").style.marginLeft = "250px";
}

openNav();

/* Set the width of the side navigation to 0 and the left margin of the page content to 0 */
function closeNav() {
  document.getElementById("mySidenav").style.width = "0";
  document.getElementById("main").style.marginLeft = "0";
}

/*

            var myTableDiv = document.getElementById("metric_results")
            var table = document.createElement('TABLE')
            var tableBody = document.createElement('TBODY')

            table.border = '1'
            table.appendChild(tableBody);

            var heading = new Array();
            heading[0] = "Request Type"
            heading[1] = "Group A"
            heading[2] = "Groub B"
            heading[3] = "Group C"
            heading[4] = "Total"


          	//TABLE ROWS
            var tr = document.createElement('TR');
            tableBody.appendChild(tr);

            for (i = 0; i < stock.length; i++) {
                for (j = 0; j < stock[i].length; j++) {
                    var td = document.createElement('TD')
                    td.appendChild(document.createTextNode(stock[i][j]));
                    td.appendChild(td)
                }
            }

            myTableDiv.appendChild(table)
*/