package phoneBook.servise.impl

import org.springframework.beans.factory.annotation.Autowired
import org.springframework.data.crossstore.ChangeSetPersister.NotFoundException
import org.springframework.data.repository.findByIdOrNull
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional
import phoneBook.dto.PhoneRecordDto
import phoneBook.entity.PhoneRecordEntity
import phoneBook.repo.RecordRepo
import phoneBook.servise.RecordService

@Service
class RecordServiceImpl (
    @Autowired
    private val recordRepo: RecordRepo):RecordService {
    override fun getById(int: Int): PhoneRecordEntity {
        return recordRepo.getById(int)
    }

    override fun findByName(string: String): MutableSet<PhoneRecordEntity> {
        println(string)
        return recordRepo.getAllByNameStartsWithOrderByName(string)

    }

    override fun getAllByOrderByName(): MutableSet<PhoneRecordEntity> {
        return recordRepo.getAllByOrderByName()
    }

//    @Transactional
//    override fun create(recordEntity: PhoneRecordEntity): Int {
//        recordRepo.save(recordEntity)
//        return recordEntity.id
//    }
//
    @Transactional
    override fun update(id: Int, recordEntity: PhoneRecordEntity) {
        val existingRecord = recordRepo.getById(id)
//            ?: throw NotFoundException
        existingRecord.name = recordEntity.name
        existingRecord.phone = recordEntity.phone
        existingRecord.email=recordEntity.email
//
        recordRepo.save(existingRecord)
//        val records = dto.persons.map { it.toEntity(existingGroup) }
//        recordRepository.deleteAllByGroup(existingGroup)
//        recordRepository.saveAll(records)
    }
//
    @Transactional
    override fun delete(id: Int) {
//        val existingRecord = recordRepo.findByIdOrNull(id)
//            ?: throw MyNotFoundException(id)
//        recordRepository.deleteAllByGroup(existingGroup)
        recordRepo.deleteById(id)
    }
//
//    private fun PhoneRecordEntity.toStr(): String =
//        (
//                "id: " + this.id + "name: " + this.name + "phone:" + this.phone + "Состоит в группах:" + this.group?.map { it.name }
//                )
//
//    private fun PhoneRecordDto.toEntity(): PhoneRecordEntity =
//        PhoneRecordEntity(
//            id=0,
//            name = this.name,
//            phone = this.phone,
//        )
}