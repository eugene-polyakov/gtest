import grails.converters.JSON;

class TranslatorService {

    boolean transactional = true

    def translate(String text, String targetLanguage) {
    	def base = "http://ajax.googleapis.com/ajax/services/language/translate?v=1.0&"
		def q = []
		q << "q=" + text.encodeAsURL()
		q << "langpair=en|" + targetLanguage
		def url = new URL(base + q.join("&"))
		def conn = url.openConnection()
//		def result = [:]
		if (conn.responseCode == 200) {
			def res = JSON.parse(conn.content.text)
			return res.responseData.translatedText;
		} else {
			log.error("Shit happened : " + conn.responseCode)
			log.error("URL was : " + url)
			return null
		}
    }
}
