package criptografia

import grails.gorm.transactions.Transactional

@Transactional
class TransferService {

    CriptografiaService criptografiaService

    List<Transfer> list() {
        List<Transfer> transferList =  Transfer.list()
        transferList.forEach {transfer ->
            transfer.setCreditCardToken(criptografiaService.decrypt(transfer.getCreditCardToken()))
            transfer.setUserDocument(criptografiaService.decrypt(transfer.getUserDocument()))
        }
        return transferList
    }

    void create(Transfer transfer) {
        String encryptedUserDocument = criptografiaService.encode(transfer.userDocument)
        String encryptedCreditCardToken = criptografiaService.encode(transfer.creditCardToken)
        Transfer newTransfer = new Transfer
                (userDocument: encryptedUserDocument,
                 creditCardToken: encryptedCreditCardToken,
                 transferValue: transfer.transferValue)
        newTransfer.save()
    }

    void delete(Long id){
        Transfer tranfer = Transfer.findById(id)
        tranfer.delete()
    }
}
