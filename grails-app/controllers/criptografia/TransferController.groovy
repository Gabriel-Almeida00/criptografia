package criptografia

import DTO.RequestTransfer

class TransferController {

    TransferService transferService

    static responseFormats = ['json', 'xml']
    static allowedMethods = [list: "GET", create: "POST", update: "PUT", delete: "DELETE"]

    List<Transfer> list() {
        respond(transferService.list()) as List<Transfer>
    }

    def create(Transfer transfer) {
        transferService.create(transfer)
        render(contentType: responseFormats, text: 'mensagemDeErro')
    }

    def update(Long id, RequestTransfer requestTransfer) {
        transferService.update(id, requestTransfer)
        render(contentType: responseFormats, text: 'deu bom')
    }

    def delete(Long id) {
        transferService.delete(id)
        render(contentType: responseFormats, text: 'mensagemDeErro')
    }
}
