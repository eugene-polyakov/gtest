package com.eastbanctech.gtest

class Address {

    String street
    String street2
    String city
    String state
    String zipCode
	
	static hasMany = [authors:Author]
	
    static constraints = {
      street(blank:false, maxSize:200)
	  street2()
      city(blank:false)
	  state(inList:["VA","DC","MD","CA"])
	  zipCode(minSize:5, maxSize:5)
    }
	
	String toString() {
	  return street + (street2==null?"":" "+street2) + " " + city + ", " + state + " " + zipCode	
	}
} 
