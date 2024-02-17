package DTO

import criptografia.Transfer

class TransferDTO {

    private long id

    private String userDocument

    private String creditCardToken

    private double transferValue

     TransferDTO(Transfer data){
        this.id = data.getId()
        this.userDocument = data.getUserDocument()
        this.creditCardToken = data.getCreditCardToken()
        this.transferValue = data.getTransferValue()
    }

     TransferDTO(long id,RequestTransfer data){
        this.id = id
        this.userDocument = data.userDocument
        this.creditCardToken = data.creditCardToken
        this.transferValue = data.transferValue
    }

}
