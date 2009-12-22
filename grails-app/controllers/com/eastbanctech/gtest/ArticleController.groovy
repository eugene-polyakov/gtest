package com.eastbanctech.gtest

import grails.converters.*;

class ArticleController {
	
	def controllerName = "edit Articles"
	
	def translatorService
	
    def scaffold = Article

    def editCustomized = {
        def articleInstance = Article.get( params.id )

        if(!articleInstance) {
            flash.message = "Article not found with id ${params.id}"
            redirect(action:list)
        }
        else {
            return [ articleInstance : articleInstance ]
        }
    }
	
	def translate = {
		def articleInstance = Article.get( params.id )
		def result = translatorService.translate(articleInstance.text, params.lang)
		if (!result) { render "Error" } else { render result }
	}
}
