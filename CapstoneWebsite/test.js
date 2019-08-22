var traverson = require('traverson')
var JsonHalAdapter = require('traverson-hal');

// register the traverson-hal plug-in for media type 'application/hal+json'
traverson.registerMediaType(JsonHalAdapter.mediaType, JsonHalAdapter);


traverson.newRequest()
.from('http://104.248.0.248/classes')
.preferEmbeddedResources()
  .withRequestOptions({
    headers: {
      'Accept': 'application/json',
      'Content-Type': 'application/json'
    }
  }).follow('classes', 'klass')                                                              
  //.withTemplateParameters({name: 'mike'})                                   
  .getResource(function(error, document) {
  	if(error) {
  		console.log(error)
     // document will represent the list of posts for user mike
  	} else {
  		console.log(JSON.stringify(document))
  	}
  })