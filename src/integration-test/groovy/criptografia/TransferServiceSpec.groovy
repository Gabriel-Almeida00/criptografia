package criptografia

import grails.testing.mixin.integration.Integration
import grails.gorm.transactions.Rollback
import spock.lang.Specification
import org.hibernate.SessionFactory

@Integration
@Rollback
class TransferServiceSpec extends Specification {

    TransferService transferService
    SessionFactory sessionFactory

    private Long setupData() {
        // TODO: Populate valid domain instances and return a valid ID
        //new Transfer(...).save(flush: true, failOnError: true)
        //new Transfer(...).save(flush: true, failOnError: true)
        //Transfer transfer = new Transfer(...).save(flush: true, failOnError: true)
        //new Transfer(...).save(flush: true, failOnError: true)
        //new Transfer(...).save(flush: true, failOnError: true)
        assert false, "TODO: Provide a setupData() implementation for this generated test suite"
        //transfer.id
    }

    void "test get"() {
        setupData()

        expect:
        transferService.get(1) != null
    }

    void "test list"() {
        setupData()

        when:
        List<Transfer> transferList = transferService.list(max: 2, offset: 2)

        then:
        transferList.size() == 2
        assert false, "TODO: Verify the correct instances are returned"
    }

    void "test count"() {
        setupData()

        expect:
        transferService.count() == 5
    }

    void "test delete"() {
        Long transferId = setupData()

        expect:
        transferService.count() == 5

        when:
        transferService.delete(transferId)
        sessionFactory.currentSession.flush()

        then:
        transferService.count() == 4
    }

    void "test save"() {
        when:
        assert false, "TODO: Provide a valid instance to save"
        Transfer transfer = new Transfer()
        transferService.save(transfer)

        then:
        transfer.id != null
    }
}
