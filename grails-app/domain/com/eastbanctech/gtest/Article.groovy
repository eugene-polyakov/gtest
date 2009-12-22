package com.eastbanctech.gtest

class Article {
	
	Genre genre
	String title
	String text
	Date created
	
	static hasMany = [involvement:Involvement, translation:Translation]

    static constraints = {
		genre(blank:false)
		title(blank:false)
		text(blank:false, widget:"textarea")
    }
	
	String toString() {
		return title
	}
	
}
