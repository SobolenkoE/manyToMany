package phoneBook.entity

import javax.persistence.*
import javax.validation.constraints.Email
import javax.validation.constraints.NotEmpty
import javax.validation.constraints.Size


@Entity
@Table(name = "phonerecord")
open class PhoneRecordEntity(




) {    @NotEmpty(message = "Имя не может быть пустым")
    @Size(min = 2, max = 100, message = "Размер строки должен быть от 2 до 100 символов")
    @Column
    open var name: String?=""
    @Size( max = 50, message = "Размер строки должен быть до 50 символов")
    @Column
    open var phone: String?=""

    @Email(message = "email должен быть валидным")
    @Column
    open var email: String?=""

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(nullable = false)
    open var id: Int = 0


    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(
        name = "group_id_record_id",
        joinColumns = [JoinColumn (name = "record_id") ],
        inverseJoinColumns = [JoinColumn (name = "group_id")]
    )
    open var group: MutableSet<PhoneGroupEntity>? = null





    override fun toString(): String {
        return "PhoneRecordEntity(" +
                "id=$id, name=$name, " +
                "phone=$phone, " +
                "group=${group?.map { it.name }})"
    }


}