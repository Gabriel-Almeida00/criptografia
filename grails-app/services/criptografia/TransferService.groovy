package criptografia

import grails.gorm.transactions.Transactional

@Transactional
class TransferService {

    List<Transfer> list() {
        Transfer.list()
    }

    void create(Transfer transfer) {
        Transfer newTransfer = new Transfer
                (userDocument: transfer.userDocument,
                 creditCardToken: transfer.creditCardToken,
                 transferValue: transfer.transferValue)
        newTransfer.save()
    }

}
