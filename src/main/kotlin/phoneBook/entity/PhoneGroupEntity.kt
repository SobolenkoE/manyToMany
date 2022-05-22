package phoneBook.entity

import javax.persistence.*
import javax.validation.constraints.NotEmpty


@Entity
@Table(name = "phonegroup")
open class PhoneGroupEntity {


    @Id
    @SequenceGenerator(
        name="phonegroup_id_seq",
        sequenceName ="phonegroup_id_seq",
        allocationSize = 1
    )
    @GeneratedValue(
        strategy = GenerationType.SEQUENCE,
        generator = "phonegroup_id_seq")
    @Column(name = "id", nullable = false)
    open var id: Int? = null
    @NotEmpty
    @Column(name = "name")
    open var name: String? = null

    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(
        name = "group_id_record_id",
        joinColumns = [JoinColumn(name = "group_id")],
        inverseJoinColumns = [JoinColumn(name = "record_id")]
    )
    open var records: MutableSet<PhoneRecordEntity>? = null




}