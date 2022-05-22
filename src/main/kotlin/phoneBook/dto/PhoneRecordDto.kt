package phoneBook.dto

data class PhoneRecordDto(
    val id: Int?=null,
    val name:String,
    val phone: String,
    val group:MutableList<PhoneGroupDto>,
)

