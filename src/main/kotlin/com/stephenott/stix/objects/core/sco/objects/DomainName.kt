package com.stephenott.stix.objects.core.sco.objects

import com.stephenott.stix.common.BusinessRulesValidator
import com.stephenott.stix.common.CompanionAllowedRelationships
import com.stephenott.stix.common.CompanionIdContributingProperties
import com.stephenott.stix.common.CompanionStixType
import com.stephenott.stix.objects.core.sco.StixCyberObservableObject
import com.stephenott.stix.objects.core.sro.objects.AllowedRelationship
import com.stephenott.stix.type.*
import com.stephenott.stix.type.StixSpecVersion.Companion.StixVersions
import kotlin.reflect.KProperty1

interface DomainNameSco : StixCyberObservableObject {

    val value: String //@TODO add validation
    //@TODO deprecated resolves_to_refs, add elevator code to create relationships from the 2.1 objects

    companion object:
        CompanionStixType,
        BusinessRulesValidator<DomainNameSco>,
        CompanionIdContributingProperties<DomainNameSco>,
        CompanionAllowedRelationships {

        override val stixType = StixType("domain-name")

        override val idContributingProperties: List<KProperty1<DomainNameSco, Any?>> = listOf(
            DomainNameSco::value
        )

        override val allowedRelationships: List<AllowedRelationship> = listOf(
            AllowedRelationship(
                DomainNameSco::class,
                RelationshipType("resolves-to"),
                DomainNameSco::class
            ),
            AllowedRelationship(
                DomainNameSco::class,
                RelationshipType("resolves-to"),
                DomainNameSco::class // @TODO IPV4-addr
            ),
            AllowedRelationship(
                DomainNameSco::class,
                RelationshipType("resolves-to"),
                DomainNameSco::class // @TODO IPV6-addr
            )
        )

        override fun objectValidationRules(obj: DomainNameSco) {
        }

    }
}

data class DomainName(
    override val value: String,
    override val type: StixType = StixType(DomainNameSco.stixType),
    override val id: StixIdentifier = StixIdentifier(type),
    override val objectMarkingsRefs: String? = null,
    override val granularMarkings: String? = null,
    override val specVersion: StixSpecVersion = StixSpecVersion(StixVersions.TWO_DOT_ONE, false),
    override val extensions: Extensions? = null,
    override val defanged: StixBoolean = StixBoolean()
) : DomainNameSco {

    init {
        DomainNameSco.objectValidationRules(this)
    }

    override fun allowedRelationships(): List<AllowedRelationship> {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

}