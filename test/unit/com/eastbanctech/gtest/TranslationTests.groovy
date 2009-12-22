package com.eastbanctech.gtest

import grails.test.*

class TranslationTests extends GrailsUnitTestCase {
    protected void setUp() {
        super.setUp()
    }

    protected void tearDown() {
        super.tearDown()
    }

    void testSomething() {
		def t = new Translation(language:"fr", text:"aaa", article:Article.findById(1))
		assert t.validate()
    }
}
