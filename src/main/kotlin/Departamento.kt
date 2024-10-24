package org.example

import jakarta.persistence.*

@Entity
@Table(name = "departamentos")
class Departamento(

    @Column(name = "nombre", nullable = false, length = 50)
    var nombre: String,

    @Column(name = "descripcion")
    var descripcion: String?,

    @ManyToOne(cascade = [CascadeType.ALL])
    @JoinColumn(name = "id_instituto")
    val instituto: String,

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    val id: Long?

) {
}