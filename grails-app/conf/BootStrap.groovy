import com.eastbanctech.gtest.Address
import com.eastbanctech.gtest.Author
import com.eastbanctech.gtest.Article
import com.eastbanctech.gtest.Involvement
import com.eastbanctech.gtest.Genre

import org.springframework.orm.hibernate3.HibernateTemplate
import org.springframework.web.context.support.WebApplicationContextUtils

import org.springframework.beans.SimpleTypeConverter

class BootStrap {


private static convertToType(value, targetType) {
        SimpleTypeConverter typeConverter = new SimpleTypeConverter()

        if (value != null && !targetType.isAssignableFrom(value.class)) {
            if (value instanceof Number && Long.class.equals(targetType)) {
                value = value.toLong()
            }
            else {
                try {
                    value = typeConverter.convertIfNecessary(value, targetType)
                } catch (org.springframework.beans.TypeMismatchException e) {
                    // ignore
                };
            }
        }
        return value
    }


     def init = { servletContext ->
		def address1 = new Address(street:"3312 M Street NW", street2:"Second Floor", city:"Washington", state:"DC", zipCode:"20005").save()
		def address2 = new Address(street:"2003 Columbia Pike", street2:"#722", city:"Arlington", state:"VA", zipCode:"22204").save()

		def genre1 = new Genre(name:"Parallel Universes").save()
		def genre2 = new Genre(name:"Space Warfare").save()
		new Genre(name:"Alien Invasion").save()
		new Genre(name:"Mind Reading").save()
		new Genre(name:"Bioimplants").save()
		
		def auth1 = new Author(name:"Vasya", address:address1, phone:"(202) 132-4567").save()
		def auth2 = new Author(name:"Petya", address:address2, phone:"(987) 665-2343").save()
		def auth3 = new Author(name:"Onotole", address:address2, phone:"(123) 434-3234").save()
		
		def art1 = new Article(genre:genre2, title:"Use Constraints to customize DB Schema Definition", text:"The fact that the state attribute has a min length of 2 and a max length of 2 provides enough information for Grails to define the column as a char(2) instead of varchar(255). Since varchar needs one more byte compared to char to store its length, the developer could have saved one byte of memory. But what about the description? It's an optional property that length is specified between 2 and 1000, but as default it's mapped to a varchar(255). If the user enters more than 255 characters, he will get in touch with a hibernate exception message and his instance of domain will not persist! That could be prevented by using TEXT for String properties with a max-length > 255! Technically speaking: In grails, java.lang.String objects seem to be mapped as default using hibernate's \"string\"-mapping type and are stored as Standard-SQL build-in type \"VARCHAR\" with a length of 255. For the description property, either varchar's length has to be adapted to varchar(1000), or the hibernate mapping type \"text\" has to be used, that is stored as Standard-SQL build-in type \"CLOB\".", created:new Date()).save()
		def inv1 = new Involvement(role:"Creator", article:art1, author:auth3, joinDate:new Date()).save();
		def inv2 = new Involvement(role:"Reviewer", article:art1, author:auth1, joinDate:new Date()).save();

    def ctx = WebApplicationContextUtils.getWebApplicationContext(servletContext)
        
        if(ctx.containsBean("sessionFactory")) {
            def sf = ctx.getBean("sessionFactory")
            def template = new HibernateTemplate(sf)
            for(d in ctx.getBean("grailsApplication").domainClasses) {
                def domain = d
                def identityType = domain.identifier.type
                domain.clazz.metaClass.static.get = { Serializable id ->
                    id = convertToType(id, identityType)
                    def obj = id ? template.get(domain.clazz,id) : null
                    if(obj && obj instanceof org.hibernate.proxy.HibernateProxy) {
                        obj = obj.getHibernateLazyInitializer().implementation
                    }
                    return obj
                }
            }
        }

		     }
     def destroy = {
     }
} 