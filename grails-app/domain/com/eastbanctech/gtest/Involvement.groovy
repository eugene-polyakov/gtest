package com.eastbanctech.gtest

class Involvement {
	
	Date joinDate
	String role
	
	static belongsTo = [author:Author, article:Article]

    static constraints = {
		role(inList:["Creator","Collaborator","Reviewer"])
    }
	
	String toString() {
		return author.name + " : " + role + " of \"" + article.title + "\""
	}
}
