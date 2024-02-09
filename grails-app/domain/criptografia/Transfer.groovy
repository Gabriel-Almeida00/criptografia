package criptografia

import grails.gorm.annotation.Entity

@Entity
class Transfer {

    String userDocument
    String creditCardToken
    double transferValue

    static constraints = {
        userDocument(nullable: true)
        creditCardToken(nullable: true)
        transferValue(nullable: true)
    }
}
