package criptografia

import grails.validation.ValidationException
import static org.springframework.http.HttpStatus.CREATED
import static org.springframework.http.HttpStatus.NOT_FOUND
import static org.springframework.http.HttpStatus.NO_CONTENT
import static org.springframework.http.HttpStatus.OK
import static org.springframework.http.HttpStatus.UNPROCESSABLE_ENTITY

import grails.gorm.transactions.ReadOnly
import grails.gorm.transactions.Transactional

class TransferController {

    TransferService transferService

    static responseFormats = ['json', 'xml']
    static allowedMethods = [save: "POST", update: "PUT", delete: "DELETE"]

    def index(Integer max) {
        params.max = Math.min(max ?: 10, 100)
        respond transferService.list(params), model:[transferCount: transferService.count()]
    }

    def show(Long id) {
        respond transferService.get(id)
    }

    @Transactional
    def save(Transfer transfer) {
        if (transfer == null) {
            render status: NOT_FOUND
            return
        }
        if (transfer.hasErrors()) {
            transactionStatus.setRollbackOnly()
            respond transfer.errors
            return
        }

        try {
            transferService.save(transfer)
        } catch (ValidationException e) {
            respond transfer.errors
            return
        }

        respond transfer, [status: CREATED, view:"show"]
    }

    @Transactional
    def update(Transfer transfer) {
        if (transfer == null) {
            render status: NOT_FOUND
            return
        }
        if (transfer.hasErrors()) {
            transactionStatus.setRollbackOnly()
            respond transfer.errors
            return
        }

        try {
            transferService.save(transfer)
        } catch (ValidationException e) {
            respond transfer.errors
            return
        }

        respond transfer, [status: OK, view:"show"]
    }

    @Transactional
    def delete(Long id) {
        if (id == null) {
            render status: NOT_FOUND
            return
        }

        transferService.delete(id)

        render status: NO_CONTENT
    }
}
