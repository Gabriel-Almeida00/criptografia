package criptografia.service

import criptografia.CriptografiaService
import criptografia.Transfer
import criptografia.TransferService
import grails.testing.services.ServiceUnitTest
import spock.lang.Specification

class TransferServiceSpec extends Specification implements ServiceUnitTest<TransferService>{

    TransferService service = Mock(TransferService)

    void "test findById"() {
        given:
        Transfer transfer = new Transfer()
        service.findById(1) >> transfer

        when:
        Transfer transferEsperada = service.findById(1)

        then:
        transfer == transferEsperada
    }
    void "test list all"() {
        given:
        List<Transfer> transfers = [new Transfer()]
        service.list() >> transfers

        when:
        List<Transfer> transfersList = service.list()

        then:
        transfers == transfersList
    }


}
