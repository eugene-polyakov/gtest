
<%@ page import="com.eastbanctech.gtest.Translation" %>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8"/>
        <meta name="layout" content="main" />
        <title>Create Translation</title>         
    </head>
    <body>
        <div class="nav">
            <span class="menuButton"><a class="home" href="${resource(dir:'')}">Home</a></span>
            <span class="menuButton"><g:link class="list" action="list">Translation List</g:link></span>
        </div>
        <div class="body">
            <h1>Create Translation</h1>
            <g:if test="${flash.message}">
            <div class="message">${flash.message}</div>
            </g:if>
            <g:hasErrors bean="${translationInstance}">
            <div class="errors">
                <g:renderErrors bean="${translationInstance}" as="list" />
            </div>
            </g:hasErrors>
            <g:form action="save" method="post" >
                <div class="dialog">
                    <table>
                        <tbody>
                        
                            <tr class="prop">
                                <td valign="top" class="name">
                                    <label for="language">Language:</label>
                                </td>
                                <td valign="top" class="value ${hasErrors(bean:translationInstance,field:'language','errors')}">
                                    <g:select id="language" name="language" from="${translationInstance.constraints.language.inList}" value="${translationInstance.language}" ></g:select>
                                </td>
                            </tr> 
                            
                            <input type="hidden" name="text"/>
                        
                            <tr class="prop">
                                <td valign="top" class="name">
                                    <label for="article">Article:</label>
                                </td>
                                <td valign="top" class="value ${hasErrors(bean:translationInstance,field:'article','errors')}">
                                    <g:select optionKey="id" from="${com.eastbanctech.gtest.Article.list()}" name="article.id" value="${translationInstance?.article?.id}" ></g:select>
                                </td>
                            </tr> 
                        
                        </tbody>
                    </table>
                </div>
                <div class="buttons">
                    <span class="button"><input class="save" type="submit" value="create" /></span>
                </div>
            </g:form>
        </div>
    </body>
</html>
