package criptografia

import grails.gorm.annotation.Entity

@Entity
class Transfer {

    String userDocument
    String creditCardToken
    Double transferValue

    static constraints = {
        userDocument(nullable: false)
        creditCardToken(nullable: false)
        transferValue(nullable: false)
    }
}
