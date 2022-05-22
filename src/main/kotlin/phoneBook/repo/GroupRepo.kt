package phoneBook.repo

import org.springframework.data.repository.CrudRepository
import phoneBook.entity.PhoneGroupEntity

interface GroupRepo: CrudRepository<PhoneGroupEntity,Int> {
    fun getById(int: Int): PhoneGroupEntity
    fun getAllByNameStartsWithOrderByName(string: String):MutableSet<PhoneGroupEntity>
    fun getAllByOrderByName():MutableSet<PhoneGroupEntity>
    fun save(phoneGroupEntity: PhoneGroupEntity)
}