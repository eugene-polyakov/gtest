
<%@ page import="com.eastbanctech.gtest.Article" %>
<%@ page import="com.eastbanctech.gtest.Translation" %>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8"/>
        <meta name="layout" content="main" />
        <title>Edit Article</title>
        <script type="text/javascript">

		function update(res, field) {
			var genre = eval("(" + res + ")")
			var textDiv = $(field + ".name")
			textDiv.innerHTML = "<B>" + genre.id + " - " + genre.name + "</b>"
			var hiddenField = $(field + ".id")
			hiddenField.value = (genre.id == null)?-1:genre.id
		}

		function get(field) {
    		var baseUrl="${createLink(controller:'genre', action:'getJson')}"
    		var url=baseUrl + "?name=" + $F(field + ".input")
    		new Ajax.Request(url, { method:'get', asynchronous:true, onSuccess:function(req) {
    			update(req.responseText, field)
    		},
    		onFailure:function(req) { alert('failure : ' + req.status) }
    		})
        }
        

        </script>

        <g:javascript library="prototype"/>
    </head>
    <body>
        <div class="nav">
            <span class="menuButton"><a class="home" href="${resource(dir:'')}">Home</a></span>
            <span class="menuButton"><g:link class="list" action="list">Article List</g:link></span>
            <span class="menuButton"><g:link class="create" action="create">New Article</g:link></span>
        </div>
        <div class="body">
            <h1>Edit Article</h1>
            <g:if test="${flash.message}">
            <div class="message">${flash.message}</div>
            </g:if>
            <g:hasErrors bean="${articleInstance}">
            <div class="errors">
                <g:renderErrors bean="${articleInstance}" as="list" />
            </div>
            </g:hasErrors>
            <g:form method="post" >
                <input type="hidden" name="id" value="${articleInstance?.id}" />
                <input type="hidden" name="version" value="${articleInstance?.version}" />
                <div class="dialog">
                    <table>
                        <tbody>
                        
                            <tr class="prop">
                                <td valign="top" class="name">
                                    <label for="genre">Genre:</label>
                                </td>
                                <td valign="top" class="value ${hasErrors(bean:articleInstance,field:'genre','errors')}">
                                    <div id="genre.name">[ Type in genre code ]</div>
                                    <input type="hidden" name="genre.id" value="${articleInstance.genre.id}" id="genre.id"/>
                                    <input type="text" name="genre.input" id="genre.input"/>
                                    <input type="button" value="Find" onClick="get('genre')"/>
		                         </td>
                            </tr> 
                        
                            <tr class="prop">
                                <td valign="top" class="name">
                                    <label for="title">Title:</label>
                                </td>
                                <td valign="top" class="value ${hasErrors(bean:articleInstance,field:'title','errors')}">
                                    <input type="text" id="title" name="title" value="${fieldValue(bean:articleInstance,field:'title')}"/>
                                </td>
                            </tr> 
                        
                            <tr class="prop">
                                <td valign="top" class="name">
                                    <label for="text">Text:</label>
                                </td>
                                <td valign="top" class="value ${hasErrors(bean:articleInstance,field:'text','errors')}">
                                    <textarea rows="5" cols="40" name="text">${fieldValue(bean:articleInstance, field:'text')}</textarea>
                                </td>
                            </tr> 
                        
                            <tr class="prop">
                                <td valign="top" class="name">
                                    <label for="created">Created:</label>
                                </td>
                                <td valign="top" class="value ${hasErrors(bean:articleInstance,field:'created','errors')}">
                                    <g:datePicker name="created" value="${articleInstance?.created}" precision="minute" ></g:datePicker>
                                </td>
                            </tr> 
                        
                            <tr class="prop">
                                <td valign="top" class="name">
                                    <label for="involvement">Involvement:</label>
                                </td>
                                <td valign="top" class="value ${hasErrors(bean:articleInstance,field:'involvement','errors')}">
                                    
<ul>
<g:each var="i" in="${articleInstance?.involvement?}">
    <li><g:link controller="involvement" action="show" id="${i.id}">${i?.encodeAsHTML()}</g:link></li>
</g:each>
</ul>
<g:link controller="involvement" params="['article.id':articleInstance?.id]" action="create">Add Involvement</g:link>

                                </td>
                            </tr> 

                            <tr class="prop">
                                <td valign="top" class="name">
                                    <label for="translations">Translations:</label>
                                </td>
                                <td valign="top" class="value ${hasErrors(bean:articleInstance,field:'translation','errors')}">
                                    
							<ul>
								<g:each var="i" in="${articleInstance?.translation?}">
							    <li><g:link controller="translation" action="show" id="${i.id}">${i?.encodeAsHTML()}</g:link></li>
								</g:each>
							</ul>
							Add translation in
							
							<g:each var="i" in="${new Translation().constraints.language.inList}">
								 | <g:link controller="translation" params="['article.id':articleInstance?.id, 'targetLang':i]" action="createWithLang">${i}</g:link>
							</g:each> 

                             </td>
                            </tr> 
                        
                        </tbody>
                    </table>
                </div>
                <div class="buttons">
                    <span class="button"><g:actionSubmit class="save" value="Update" /></span>
                    <span class="button"><g:actionSubmit class="delete" onclick="return confirm('Are you sure?');" value="Delete" /></span>
                </div>
            </g:form>
        </div>
    </body>
</html>
