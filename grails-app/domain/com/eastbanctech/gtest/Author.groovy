package com.eastbanctech.gtest

class Author {
	String name
	Address address
	String phone
	
	static hasMany = [involvement:Involvement]

    static constraints = {
		name(blank:false)
		address(blank:false)
		phone()
    }
	
	String toString() {
		return name
	}
}
