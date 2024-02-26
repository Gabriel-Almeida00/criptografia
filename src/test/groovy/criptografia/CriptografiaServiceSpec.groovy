package criptografia

import grails.testing.services.ServiceUnitTest
import spock.lang.Specification

class CriptografiaServiceSpec extends Specification implements ServiceUnitTest<CriptografiaService>{

    CriptografiaService service = Mock(CriptografiaService)

    void "teste para encode"(){
        given:
        String rawPassword = "123456"
        String criptografada = "7zXrzCNj8biN69IDnCLNfw=="
        service.encode(rawPassword) >> criptografada

        when:
        String encodedPassword = service.encode(rawPassword)

        then:
        encodedPassword == criptografada
    }

    void "teste para decrypt"(){
        given:
        String rawPassword = "123456"
        String criptografada = "7zXrzCNj8biN69IDnCLNfw=="
        service.decrypt(criptografada) >> rawPassword

        when:
        String encodedPassword = service.decrypt(criptografada)

        then:
        encodedPassword == rawPassword
    }
}
