package phoneBook.servise.impl

import org.springframework.beans.factory.annotation.Autowired
import org.springframework.data.crossstore.ChangeSetPersister.NotFoundException
import org.springframework.data.repository.findByIdOrNull
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional
import phoneBook.entity.PhoneGroupEntity
import phoneBook.repo.GroupRepo
import phoneBook.servise.GroupService

@Service
class GroupServiceImpl (
    @Autowired
    private val groupRepo: GroupRepo):GroupService {
    override fun getById(int: Int): PhoneGroupEntity {
        return groupRepo.getById(int)
    }

    override fun findByName(string: String): MutableSet<PhoneGroupEntity> {
        println(string)
        return groupRepo.getAllByNameStartsWithOrderByName(string)

    }

    override fun getAllByOrderByName(): MutableSet<PhoneGroupEntity> {
        return groupRepo.getAllByOrderByName()
    }

//    @Transactional
//    override fun create(GroupEntity: PhoneGroupEntity): Int {
//        GroupRepo.save(GroupEntity)
//        return GroupEntity.id
//    }
//
    @Transactional
    override fun update(id: Int, GroupEntity: PhoneGroupEntity) {
        val existingGroup = groupRepo.getById(id)
//            ?: throw NotFoundException
        existingGroup.name = GroupEntity.name

        groupRepo.save(existingGroup)
//        val Groups = dto.persons.map { it.toEntity(existingGroup) }
//        GroupRepository.deleteAllByGroup(existingGroup)
//        GroupRepository.saveAll(Groups)
    }
//
    @Transactional
    override fun delete(id: Int) {
//        val existingGroup = GroupRepo.findByIdOrNull(id)
//            ?: throw MyNotFoundException(id)
//        GroupRepository.deleteAllByGroup(existingGroup)
        groupRepo.deleteById(id)
    }
//
//    private fun PhoneGroupEntity.toStr(): String =
//        (
//                "id: " + this.id + "name: " + this.name + "phone:" + this.phone + "Состоит в группах:" + this.group?.map { it.name }
//                )
//
//    private fun PhoneGroupDto.toEntity(): PhoneGroupEntity =
//        PhoneGroupEntity(
//            id=0,
//            name = this.name,
//            phone = this.phone,
//        )
}