package phoneBook.servise


import phoneBook.entity.PhoneRecordEntity


interface RecordService {
    fun getById(int: Int):PhoneRecordEntity
    fun findByName(string: String):MutableSet<PhoneRecordEntity>
    fun getAllByOrderByName():MutableSet<PhoneRecordEntity>
//    fun create(recordEntity: PhoneRecordEntity):Int
    fun update(id: Int,recordEntity: PhoneRecordEntity)
    fun delete(id: Int)
}
