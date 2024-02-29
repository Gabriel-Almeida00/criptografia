package criptografia.service

import DTO.RequestTransfer
import criptografia.Transfer
import criptografia.TransferService
import grails.testing.gorm.DataTest
import grails.testing.services.ServiceUnitTest
import spock.lang.Specification

class TransferServiceSpec extends Specification implements ServiceUnitTest<TransferService>, DataTest {

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

    void "teste create transfer"() {
        given:
        Transfer transfer = new Transfer()

        when:
        service.create(transfer)

        then:
        1 * service.create(transfer)
    }

    void "teste update transfer"(){
        given:
        Long id = 1
        RequestTransfer requestTransfer = new RequestTransfer()

        when:
        service.update(id, requestTransfer)

        then:
        1 * service.update(id, requestTransfer)
    }

    void "teste delete trnafer"(){
        given:
        Long id = 1

        when:
        service.delete(id)

        then:
        1 * service.delete(id)
    }
}
