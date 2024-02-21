package criptografia

import DTO.RequestTransfer
import grails.gorm.transactions.Transactional

@Transactional
class TransferService {

    CriptografiaService criptografiaService

    Transfer findById(Long id){
        Transfer transfer = Transfer.findById(id)
        return transfer
    }

    List<Transfer> list() {
        List<Transfer> transferList = Transfer.getAll()
        transferList.forEach { transfer ->
            transfer.setCreditCardToken(criptografiaService.decrypt(transfer.getCreditCardToken()))
            transfer.setUserDocument(criptografiaService.decrypt(transfer.getUserDocument()))
            transfer.discard()
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

    void update(Long id, RequestTransfer requestTransfer) {
        Transfer updateTranfer = Transfer.findById(id)
        updateTranfer.setUserDocument(criptografiaService.encode(requestTransfer.userDocument))
        updateTranfer.setCreditCardToken(criptografiaService.encode(requestTransfer.creditCardToken))
        updateTranfer.setTransferValue(requestTransfer.transferValue)
        updateTranfer.save()
    }

    void delete(Long id) {
        Transfer tranfer = Transfer.findById(id)
        tranfer.delete()
    }
}
