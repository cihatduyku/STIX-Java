package com.stephenott.stix.`object`.sdo.`object`

import com.stephenott.stix.`object`.sdo.StixDomainObject
import com.stephenott.stix.`object`.sro.`object`.AllowedRelationship
import com.stephenott.stix.`object`.sro.`object`.RelationshipSro
import com.stephenott.stix.type.*

interface ObservedDataSdo : StixDomainObject {
    val firstObserved: StixInstant
    val lastObserved: StixInstant
    val numberObserved: StixInteger
    val objectRefs: StixIdentifiers

    companion object{
        val stixType = StixType("observed-data")

        val allowedRelationships: List<AllowedRelationship> = listOf()
    }
}

data class ObservedData(
    override val firstObserved: StixInstant,
    override val lastObserved: StixInstant,
    override val numberObserved: StixInteger,
    override val objectRefs: StixIdentifiers,
    override val type: StixType = ObservedDataSdo.stixType,
    override val id: StixIdentifier = StixIdentifier(type),
    override val createdByRef: String? = null,
    override val created: StixInstant = StixInstant(),
    override val externalReferences: ExternalReferences? = null,
    override val objectMarkingsRefs: String? = null,
    override val granularMarkings: String? = null,
    override val specVersion: StixSpecVersion = StixSpecVersion(),
    override val labels: StixLabels? = null,
    override val modified: StixInstant = StixInstant(created),
    override val revoked: StixBoolean = StixBoolean()
) :
    ObservedDataSdo {
    override fun allowedRelationships(): List<AllowedRelationship> {
        return ObservedDataSdo.allowedRelationships + RelationshipSro.allowedCommonRelationships
    }

}