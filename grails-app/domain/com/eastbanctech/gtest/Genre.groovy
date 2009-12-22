package com.eastbanctech.gtest

class Genre {
	
	String name
	
	static hasMany = [articles:Article]
	
    static constraints = {
		articles(display:false)
    }
	
	String toString() {
		return name;
	}
}
