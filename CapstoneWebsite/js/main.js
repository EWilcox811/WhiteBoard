
var firstTable = true;
var currentDataSelected = "Professor";
var currentRowSelected = { };
var textfielddivs;
var professordiv;
var studentdiv;
var classesdiv;
var sessionsdiv;
var commentsdiv;

function getProfessorData() {
    professordiv = document.getElementById("professorTextFields");
    currentDataSelected = "Professor";
    headers = ["Username", "User ID", "First Name", "Last Name", "Email", "Date Joined"]
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
            userinfo = [uname, uid, fname, lname, email, date];
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

function getStudentData() {
    studentdiv = document.getElementById("studentTextFields")
    currentDataSelected = "Student";
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
    classesdiv = document.getElementById("classTextFields");
    currentDataSelected = "Class";
    headers = ["Class ID", "Class Subject", "Class Number", "Class Description"]
    var classarray = new Array();
    
    jQuery.getJSON("http://104.248.0.248/classes", function(data) {
    }.bind(this)).fail(function(xhr, status) {
    console.log("The JSON chart failed to load.");
    console.log(status);
    }).done(function(data) {
        classes = data["_embedded"]["classes"];
        var classinfo = new Array();
        for(i in classes) {
            var cid = classes[i]["_links"]["self"]["href"];
            cid = cid.split("http://104.248.0.248/classes/")[1];
            var subject = classes[i]["subject"];
            var classnumber = classes[i]["classNumber"];
            var desc = classes[i]["description"];
            classinfo = [cid, subject, classnumber, desc];
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
    sessionsdiv = document.getElementById("sessionTextFields");
    currentDataSelected = "Session";
    headers = ["Session ID", "Session Name", "Start Date", "End Date", "Class Subject", "Class Number", "Class Description"]
    var sessionarray = new Array();
    
    jQuery.getJSON("http://104.248.0.248/sessions", function(data) {
    }.bind(this)).fail(function(xhr, status) {
    console.log("The JSON chart failed to load.");
    console.log(status);
    }).done(function(data) {
        sessions = data["_embedded"]["sessions"];
        var sessioninfo = new Array();
        for(i in sessions) {
            var sid = sessions[i]["_links"]["self"]["href"];
            sid = sid.split("http://104.248.0.248/sessions/")[1];
            var sessionname = sessions[i]["sessionName"];
            var startdate = new Date(sessions[i]["startDate"]);
            var enddate = new Date(sessions[i]["endDate"]);
            var subject = sessions[i]["classSubject"];
            var sessionnumber = sessions[i]["classNumber"];
            var desc = sessions[i]["classDescription"];
            sessioninfo = [sid, sessionname, startdate, enddate, subject, sessionnumber, desc];
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
    commentsdiv = document.getElementById("commentTextFields");
    currentDataSelected = "Comments";
    headers = ["Comment ID", "Comment Date", "Parent Comment", "Commenter User Name", "Comment", "Number of Replies", "Anonymous", "Session Name"]
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
    textfielddivs = document.getElementsByClassName("textfieldsclass");
    hideTextInputs("");

    

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

/* Setup Table Highlighting and Selecting. */

function highlight(e) {
    if (selected[0]) selected[0].className = '';
    e.target.parentNode.className = 'selected';
    
}

var table = document.getElementById('tablebody'),
    selected = table.getElementsByClassName('selected');
table.onclick = highlight;

function EditBtnOnClick(){
    value = $(".selected").html();
    value = value || "No row Selected";
    //console.log(value);

    $('.selected').children().each(function(_, node) {
        currentRowSelected[ _ ] = { };
        currentRowSelected[ _ ] = node.textContent;
    });



    switch(currentDataSelected) {
        case "Professor":
            console.log("Professor Selected");
            hideTextInputs(professordiv);
            fillProfessorEditTables();
            professordiv.style.display = "block";
            break;
        case "Student":
            console.log("Student Selected");
            hideTextInputs(studentdiv);
            fillStudentEditTables();
            studentdiv.style.display = "block";
            break;
        case "Class":
            console.log("Class Selected");
            hideTextInputs(classesdiv);
            fillClassEditTables();
            classesdiv.style.display = "block";
            break;
        case "Session":
            console.log("Session Selected");
            hideTextInputs(sessionsdiv);
            fillSessionEditTables();
            sessionsdiv.style.display = "block";
            break;
        case "Comments":
            console.log("Comments Selected");
            hideTextInputs(commentsdiv);
            fillCommentEditTables();
            commentsdiv.style.display = "block";
            break;
    }
}

function DeleteBtnOnClick() {
    value = $(".selected").html();
    value = value || "No row Selected";
    //console.log(value);

    $('.selected').children().each(function(_, node) {
        currentRowSelected[ _ ] = { };
        currentRowSelected[ _ ] = node.textContent;
    });


    if(promptForDelete()) {

        switch(currentDataSelected) {
            case "Professor":
                console.log("Professor Selected");
                deleteProfessor();
                break;
            case "Student":
                console.log("Student Selected");
                deleteStudent();
                studentdiv.style.display = "block";
                break;
            case "Class":
                console.log("Class Selected");
                deleteClass();
                classesdiv.style.display = "block";
                break;
            case "Session":
                console.log("Session Selected");
                deleteSession();
                sessionsdiv.style.display = "block";
                break;
            case "Comments":
                console.log("Comments Selected");
                deleteComment();
                break;
        }
    }
}

/* Text Input Functions */

function hideTextInputs(selected) {
    for(var i = 0; i < textfielddivs.length;i++) {
        if(textfielddivs[i].id != selected)
            textfielddivs[i].style.display = "none";
    }
}

function fillProfessorEditTables() {

    var fnameInput = document.getElementById("proffnameInput");
    var lnameInput = document.getElementById("proflnameInput");
    var emailInput = document.getElementById("profemailInput");

    fnameInput.value = currentRowSelected[2];
    lnameInput.value = currentRowSelected[3];
    emailInput.value = currentRowSelected[4];
}

function fillStudentEditTables() {

    var fnameInput = document.getElementById("stufnameInput");
    var lnameInput = document.getElementById("stulnameInput");
    var emailInput = document.getElementById("stuemailInput");
    var gpaInput = document.getElementById("stugpaInput");

    fnameInput.value = currentRowSelected[2];
    lnameInput.value = currentRowSelected[3];
    emailInput.value = currentRowSelected[4];
    gpaInput.value = currentRowSelected[6];
}

function fillClassEditTables() {

    var classsubjectInput = document.getElementById("classsubjectInput");
    var classnumberInput = document.getElementById("classnumberInput");
    var classdescInput = document.getElementById("classdescInput");

    classsubjectInput.value = currentRowSelected[1];
    classnumberInput.value = currentRowSelected[2];
    classdescInput.value = currentRowSelected[3];
}

function fillSessionEditTables() {
    var sessionnameInput = document.getElementById("sessionnameInput");
    var sessionstartdateInput = document.getElementById("sessionstartdateInput");
    var sessionenddateInput = document.getElementById("sessionenddateInput");
    var sessclasssubInput = document.getElementById("sessclasssubInput");
    var sessclassnumInput = document.getElementById("sessclassnumInput");
    var sessclassdescInput = document.getElementById("sessclassdescInput");

    sessionnameInput.value = currentRowSelected[1];
    startDate = new Date(currentRowSelected[2]).toISOString();
    startDate = startDate.substring(0,startDate.length-1);
    endDate = new Date(currentRowSelected[3]).toISOString();
    endDate = endDate.substring(0,endDate.length-1);
    sessionstartdateInput.value = startDate;
    sessionenddateInput.value = endDate;
    sessclasssubInput.value = currentRowSelected[4];
    sessclassnumInput.value = currentRowSelected[5];
    sessclassdescInput.value = currentRowSelected[6];
}


function fillCommentEditTables() {
    var commentInput = document.getElementById("commentInput");

    commentInput.value = currentRowSelected[4]; 
}

/* PUT Functions */


/*

{
  "firstName" : "Jean",
  "lastName"  : "Desire",
  "password"  : "password",
  "email"     : "jean.desire@natuniv.edu",
  "rating"       : "3.95"
}


*/
function editProfessor() {
    console.log("Editing professor");

    apiUrl = "http://104.248.0.248/professors"



    var data = new Object();
    data.firstName = document.getElementById("proffnameInput").value;
    data.lastName = document.getElementById("proflnameInput").value;
    data.email = document.getElementById("profemailInput").value;


  fetch(apiUrl + "/" + currentRowSelected[1], {
    method: 'PATCH',
    headers: {
      'Content-type': 'application/json; charset=UTF-8' // Indicates the content 
     },
    body: JSON.stringify(data)   
      }).then((response) => {
        response.json().then((response) => {
          getProfessorData();
          //console.log(response);
        })
      }).catch(err => {
        console.error(err)
  })
}

function editStudent() {
    console.log("Editing student");

    apiUrl = "http://104.248.0.248/students"



    var data = new Object();
    data.firstName = document.getElementById("stufnameInput").value;
    data.lastName = document.getElementById("stulnameInput").value;
    data.email = document.getElementById("stuemailInput").value;
    data.gpa = document.getElementById("stugpaInput").value;


  fetch(apiUrl + "/" + currentRowSelected[1], {
    method: 'PATCH',
    headers: {
      'Content-type': 'application/json; charset=UTF-8' // Indicates the content 
     },
    body: JSON.stringify(data)   
      }).then((response) => {
        response.json().then((response) => {
          getStudentData();
          //console.log(response);
        })
      }).catch(err => {
        console.error(err)
  })
}


function editClass() {
    console.log("Editing classes");

    apiUrl = "http://104.248.0.248/classes"



    var data = new Object();
    data.subject = document.getElementById("classsubjectInput").value;
    data.classNumber = document.getElementById("classnumberInput").value;
    data.description = document.getElementById("classdescInput").value;


  fetch(apiUrl + "/" + currentRowSelected[0], {
    method: 'PATCH',
    headers: {
      'Content-type': 'application/json; charset=UTF-8' // Indicates the content 
     },
    body: JSON.stringify(data)   
      }).then((response) => {
        response.json().then((response) => {
          getClassData();
          //console.log(response);
        })
      }).catch(err => {
        console.error(err)
  })
}

function editSession() {
    console.log("Editing session");

    apiUrl = "http://104.248.0.248/sessions"



    var data = new Object();
    data.sessionName = document.getElementById("sessionnameInput").value;
    data.startDate = document.getElementById("sessionstartdateInput").value;
    data.endDate = document.getElementById("sessionenddateInput").value;
    data.classSubject = document.getElementById("sessclasssubInput").value;
    data.classNumber = document.getElementById("sessclassnumInput").value;
    data.classDescription = document.getElementById("sessclassdescInput").value;


  fetch(apiUrl + "/" + currentRowSelected[0], {
    method: 'PATCH',
    headers: {
      'Content-type': 'application/json; charset=UTF-8' // Indicates the content 
     }
      }).then((response) => {
        response.json().then((response) => {
          getSessionData();
          //console.log(response);
        })
      }).catch(err => {
        console.error(err)
  })
}

function editComment() {
    console.log("Editing comment");

    apiUrl = "http://104.248.0.248/comments"



    var data = new Object();
    data.message = document.getElementById("commentInput").value;


  fetch(apiUrl + "/" + currentRowSelected[0], {
    method: 'PATCH',
    headers: {
      'Content-type': 'application/json; charset=UTF-8' // Indicates the content 
     },
    body: JSON.stringify(data)   
      }).then((response) => {
        response.json().then((response) => {
          getCommentData();
          //console.log(response);
        })
      }).catch(err => {
        console.error(err)
  })
}

/* DELETE Functions */
function promptForDelete() {
    return confirm("Are you sure you want to delete the selected item?");
}

function deleteProfessor() {
    console.log("Deleting professor");

    apiUrl = "http://104.248.0.248/users"


  fetch(apiUrl + "/" + currentRowSelected[1], {
    method: 'DELETE'
      }).then((response) => {
          getProfessorData();
          //console.log(response);
      }).catch(err => {
        console.error(err)
  })
    
}

function deleteStudent() {
    console.log("Deleting student");

    apiUrl = "http://104.248.0.248/users"


  fetch(apiUrl + "/" + currentRowSelected[1], {
    method: 'DELETE'
      }).then((response) => {
          getProfessorData();
          //console.log(response);
      }).catch(err => {
        console.error(err)
  })
}

function deleteClass() {
    console.log("Deleting class");

    apiUrl = "http://104.248.0.248/classes"


  fetch(apiUrl + "/" + currentRowSelected[0], {
    method: 'DELETE'
      }).then((response) => {
          getClassData();
          //console.log(response);
      }).catch(err => {
        console.error(err)
  })
}

function deleteSession() {
    console.log("Deleting session");

    apiUrl = "http://104.248.0.248/sessions"


  fetch(apiUrl + "/" + currentRowSelected[0], {
    method: 'DELETE'
      }).then((response) => {
          getSessionData();
          //console.log(response);
      }).catch(err => {
        console.error(err)
  })
}

function deleteComment() {
    console.log("Deleting comment");
    console.log(currentRowSelected);
    apiUrl = "http://104.248.0.248/comments"


  fetch(apiUrl + "/" + currentRowSelected[1], {
    method: 'DELETE'
      }).then((response) => {
          getCommentData();
          //console.log(response);
      }).catch(err => {
        console.error(err)
  })
    
}