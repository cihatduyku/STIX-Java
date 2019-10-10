package com.stephenott.stix.`object`.sdo.`object`

import com.stephenott.stix.`object`.sdo.StixDomainObject
import com.stephenott.stix.`object`.sro.`object`.AllowedRelationship
import com.stephenott.stix.`object`.sro.`object`.RelationshipSro
import com.stephenott.stix.type.*
import com.stephenott.stix.type.vocab.*

interface ThreatActorSdo : StixDomainObject {
    val name: String
    val description: String?
    val threatActorTypes: ThreatActorTypes
    val aliases: StixStringList?
    val firstSeen: StixInstant?
    val lastSeen: StixInstant?
    val roles: ThreatActorRoles?
    val goals: StixStringList?
    val sophistication: ThreatActorSophisticationOv?
    val resourceLevel: AttackResourceLevelOv?
    val primaryMotivation: AttackMotivationOv?
    val secondaryMotivation: AttackMotivationOv?
    val personalMotivations: AttackMotivations?

    companion object{
        val stixType = StixType("threat-actor")

        val allowedRelationships: List<AllowedRelationship> = listOf(
            AllowedRelationship(
                ThreatActorSdo::class,
                RelationshipType("attributed-to"),
                IdentitySdo::class
            ),

            AllowedRelationship(
                ThreatActorSdo::class,
                RelationshipType("compromises"),
                InfrastructureSdo::class
            ),

            AllowedRelationship(
                ThreatActorSdo::class,
                RelationshipType("hosts"),
                InfrastructureSdo::class
            ),

            AllowedRelationship(
                ThreatActorSdo::class,
                RelationshipType("owns"),
                InfrastructureSdo::class
            ),

            AllowedRelationship(
                ThreatActorSdo::class,
                RelationshipType("impersonates"),
                IdentitySdo::class
            ),

            AllowedRelationship(
                ThreatActorSdo::class,
                RelationshipType("located-at"),
                LocationSdo::class
            ),

            AllowedRelationship(
                ThreatActorSdo::class,
                RelationshipType("targets"),
                IdentitySdo::class
            ),
            AllowedRelationship(
                ThreatActorSdo::class,
                RelationshipType("targets"),
                LocationSdo::class
            ),
            AllowedRelationship(
                ThreatActorSdo::class,
                RelationshipType("targets"),
                VulnerabilitySdo::class
            ),

            AllowedRelationship(
                ThreatActorSdo::class,
                RelationshipType("uses"),
                AttackPatternSdo::class
            ),
            AllowedRelationship(
                ThreatActorSdo::class,
                RelationshipType("uses"),
                InfrastructureSdo::class
            ),
            AllowedRelationship(
                ThreatActorSdo::class,
                RelationshipType("uses"),
                MalwareSdo::class
            ),
            AllowedRelationship(
                ThreatActorSdo::class,
                RelationshipType("uses"),
                ToolSdo::class
            )
        )
    }
}

data class ThreatActor(
    override val name: String,
    override val description: String? = null,
    override val threatActorTypes: ThreatActorTypes,
    override val aliases: StixStringList? = null,
    override val firstSeen: StixInstant? = null,
    override val lastSeen: StixInstant? = null,
    override val roles: ThreatActorRoles? = null,
    override val goals: StixStringList? = null,
    override val sophistication: ThreatActorSophisticationOv? = null,
    override val resourceLevel: AttackResourceLevelOv? = null,
    override val primaryMotivation: AttackMotivationOv? = null,
    override val secondaryMotivation: AttackMotivationOv? = null,
    override val personalMotivations: AttackMotivations? = null,
    override val type: StixType = ThreatActorSdo.stixType,
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
    ThreatActorSdo {

    override fun allowedRelationships(): List<AllowedRelationship> {
        return ThreatActorSdo.allowedRelationships + RelationshipSro.allowedCommonRelationships
    }

}