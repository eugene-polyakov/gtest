package com.eastbanctech.gtest

class TranslationController {

    def scaffold = Translation
	
	def translatorService
	
	def save = {
		def translationInstance = new Translation(params)
		translationInstance.text = translatorService.translate(translationInstance.article.text, translationInstance.language)
		if(!translationInstance.hasErrors() && translationInstance.save()) {
			flash.message = "Translation ${translationInstance.id} created"
			redirect(action:show,id:translationInstance.id)
		}
		else {
			render(view:'create',model:[translationInstance:translationInstance])
		}
	}
	
	
	def createWithLang = {
	   def targetLang = params.targetLang
	   def article = Article.get(params.article.id)
	   def translatedText = translatorService.translate(article.text, targetLang)
	   def newTranslation = new Translation(language:targetLang, text:translatedText, article:article)
	   newTranslation.save()
	   redirect(action:edit, params:["id":newTranslation.id])
	}
	
/*	def create = {
    	def articleId = params.article.id
		render(view:"createTranslation", )
	}
	*/
	
}
