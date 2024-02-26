package criptografia

import grails.testing.gorm.DomainUnitTest
import spock.lang.Specification

class TransferSpec extends Specification implements DomainUnitTest<Transfer> {

    void 'test name cannot be null'() {
        when:
        domain.userDocument = null

        then:
        !domain.validate(['userDocument'])
        domain.errors['userDocument'].code == 'nullable'
    }
}
