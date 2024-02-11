package criptografia

class TransferController {

    TransferService transferService

    static responseFormats = ['json', 'xml']
    static allowedMethods = [list: "GET", create: "POST"]

    List<Transfer> list() {
        respond(transferService.list()) as List<Transfer>
    }

    def create(Transfer transfer) {
        transferService.create(transfer)
        render(contentType: responseFormats, text: 'mensagemDeErro')
    }
}
