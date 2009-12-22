package com.eastbanctech.gtest

import grails.converters.*;

class GenreController {

    def scaffold = Genre
	
	def getJson = {
		def genre = Genre.findByNameIlike("%"+params.name+"%")
		
		if (!genre) {
			genre = new Genre(name:"Not Found : " + params.name)
		}
		
		render genre as JSON
		
	}
}
