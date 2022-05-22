package phoneBook.dto

data class PhoneGroupDto(
    val id: Int?=null,
    val name:String,
    val group:MutableList<PhoneRecordDto>,
)
