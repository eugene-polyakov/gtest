package com.eastbanctech.gtest

class Translation {
	
	String language
	String text
	Article article
	
	static belongsTo = Article

    static constraints = {
		language(inList:["fr","it","de"])
		text(blank:true, widget:"textarea")
    }
	
	String toString() {
		return "Translation to [" + language + "]"
	}
}
