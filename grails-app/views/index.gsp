<html>
    <head>
        <title>Welcome to Grails</title>
		<meta name="layout" content="main" />
    </head>
    <body>
    	<div  style="margin-left:20px;">
        <h1>Test project</h1>
		From here, you can :<br/><br/>
		<ul>
		<li><g:link controller="article"><b>Edit Articles</b></g:link><br/><br/></li>
		<li><g:link controller="author">Edit Authors</g:link></li>
		<li><g:link controller="address">Edit Addresses</g:link></li>
		<li><g:link controller="genre">Edit Genres</g:link></li>
		</ul>
<!--         <div class="dialog" style="margin-left:20px;width:60%;">
            <ul>
              <g:each var="c" in="${grailsApplication.controllerClasses}">
                    <li class="controller"><g:link controller="${c.logicalPropertyName}">${c.fullName}</g:link></li>
              </g:each>
            </ul> -->
        </div>
    </body>
</html>