package criptografia.domain

import criptografia.Transfer
import grails.testing.gorm.DomainUnitTest
import spock.lang.Specification

class TransferSpec extends Specification implements DomainUnitTest<Transfer> {

    void 'teste para userDocument null '() {
        when:
        domain.userDocument = null

        then:
        !domain.validate(['userDocument'])
        domain.errors['userDocument'].code == 'nullable'
    }

    void 'teste para creditCardToken null '() {
        when:
        domain.creditCardToken = null

        then:
        !domain.validate(['creditCardToken'])
        domain.errors['creditCardToken'].code == 'nullable'
    }

    void 'teste para creditCardToken null '() {
        when:
        domain.transferValue = null

        then:
        !domain.validate(['transferValue'])
        domain.errors['transferValue'].code == 'nullable'
    }
}
