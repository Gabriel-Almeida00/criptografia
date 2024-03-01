package criptografia.controller

import DTO.RequestTransfer
import criptografia.Transfer
import criptografia.TransferController
import criptografia.TransferService
import spock.lang.*
import static org.springframework.http.HttpStatus.OK
import static org.springframework.http.HttpStatus.NOT_FOUND
import static org.springframework.http.HttpStatus.NO_CONTENT
import static org.springframework.http.HttpStatus.CREATED
import static org.springframework.http.HttpStatus.UNPROCESSABLE_ENTITY
import grails.validation.ValidationException
import grails.testing.web.controllers.ControllerUnitTest
import grails.testing.gorm.DomainUnitTest

class TransferControllerSpec extends Specification implements ControllerUnitTest<TransferController>, DomainUnitTest<Transfer> {

   TransferController controller = Mock(TransferController)

    void "Test findById"() {
        given:
        Long id = 1
        Transfer transferEsperado = new Transfer()
        controller.findById(id) >> transferEsperado

        when:
        Transfer transfer = controller.findById(id)

        then:
        transfer == transferEsperado
    }


    void "Test list"() {
        given:
        List<Transfer> transfers = [new Transfer()]
        controller.list() >> transfers

        when:
        List<Transfer> transfersEsperadas = controller.list()

        then:
        transfersEsperadas == transfers
    }

    void "Test create"() {
        given:
        Transfer transfer = new Transfer()
        controller.create(transfer)

        when:
        controller.create(transfer)

        then:
        1 * controller.create(transfer)
    }

    void "Test update"() {
        given:
        Long id = 1
        RequestTransfer requestTransfer = new RequestTransfer()

        when:
        controller.update(id, requestTransfer)

        then:
        1 * controller.update(id, requestTransfer)

    }

    void "teste delete"(){
        given:
        Long id = 1

        when:
        controller.delete(id)

        then:
        1 * controller.delete(id)
    }
}