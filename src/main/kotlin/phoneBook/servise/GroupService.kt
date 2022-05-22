package phoneBook.servise


import phoneBook.entity.PhoneGroupEntity



interface GroupService {
    fun getById(int: Int):PhoneGroupEntity
    fun findByName(string: String):MutableSet<PhoneGroupEntity>
    fun getAllByOrderByName():MutableSet<PhoneGroupEntity>
//    fun create(GroupEntity: PhoneGroupEntity):Int
    fun update(id: Int,GroupEntity: PhoneGroupEntity)
    fun delete(id: Int)
}
