package phoneBook.repo

import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.data.repository.CrudRepository

import phoneBook.entity.PhoneRecordEntity


interface RecordRepo:CrudRepository<PhoneRecordEntity,Int> {
    fun getById(int: Int):PhoneRecordEntity
    fun getAllByNameStartsWithOrderByName(string: String):MutableSet<PhoneRecordEntity>
    fun getAllByOrderByName():MutableSet<PhoneRecordEntity>

    }
