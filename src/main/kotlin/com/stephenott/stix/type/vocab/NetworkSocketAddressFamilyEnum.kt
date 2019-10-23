package com.stephenott.stix.type.vocab

class NetworkSocketAddressFamilyEnum(private val addressFamily: String) : ClosedVocab, CharSequence by addressFamily {

    companion object {
        val vocabName = "network-socket-address-family-enum"

        val vocab: LinkedHashSet<String> = linkedSetOf(
            "AF_UNSPEC", "AF_INET", "AF_IPX",
            "AF_APPLETALK", "AF_NETBIOS", "AF_INET6",
            "AF_IRDA", "AF_BTH"
        )
    }

    init {
        require(this.addressFamily in vocab)
    }
}