package criptografia.controller

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


        when:


        then:

    }

    void "Test the show action with a null id"() {
        given:
        controller.transferService = Mock(TransferService) {
            1 * get(null) >> null
        }

        when:"The show action is executed with a null domain"
        controller.show()

        then:"A 404 error is returned"
        response.status == NOT_FOUND.value()
    }

    void "Test the show action with a valid id"() {
        given:
        controller.transferService = Mock(TransferService) {
            1 * get(2) >> new Transfer()
        }

        when:"A domain instance is passed to the show action"
        params.id = 2
        controller.show()

        then:"A model is populated containing the domain instance"
        response.status == OK.value()
        response.json == [:]
    }

    void "Test the update action with a null instance"() {
        when:
        request.contentType = JSON_CONTENT_TYPE
        request.method = 'PUT'
        controller.update()

        then:
        response.status == UNPROCESSABLE_ENTITY.value()
    }

    void "Test the update action correctly persists"() {
        given:
        controller.transferService = Mock(TransferService) {
            1 * save(_ as Transfer)
        }

        when:
        response.reset()
        request.contentType = JSON_CONTENT_TYPE
        request.method = 'PUT'
        populateValidParams(params)
        def instance = new Transfer(params)
        instance.id = 1
        instance.version = 0
        controller.update(instance)

        then:
        response.status == OK.value()
        response.json
    }

    void "Test the update action with an invalid instance"() {
        given:
        controller.transferService = Mock(TransferService) {
            1 * save(_ as Transfer) >> { Transfer transfer ->
                throw new ValidationException("Invalid instance", transfer.errors)
            }
        }

        when:
        request.contentType = JSON_CONTENT_TYPE
        request.method = 'PUT'
        def instance = new Transfer(params)
        instance.id = 1
        instance.version = 0
        controller.update(instance)

        then:
        response.status == UNPROCESSABLE_ENTITY.value()
        response.json
    }

    void "Test the delete action with a null instance"() {
        when:
        request.contentType = JSON_CONTENT_TYPE
        request.method = 'DELETE'
        controller.delete()

        then:
        response.status == NOT_FOUND.value()
    }

    void "Test the delete action with an instance"() {
        given:
        controller.transferService = Mock(TransferService) {
            1 * delete(2)
        }

        when:
        request.contentType = JSON_CONTENT_TYPE
        request.method = 'DELETE'
        params.id = 2
        controller.delete()

        then:
        response.status == NO_CONTENT.value()
    }
}