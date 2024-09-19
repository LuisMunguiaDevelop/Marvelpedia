package org.plux.marvelpedia.network.marvel

import io.ktor.utils.io.core.toByteArray
import kotlinx.datetime.Clock
import org.kotlincrypto.hash.md.MD5
import org.plux.marvelpedia.BuildKonfig

object MarvelCredentialsBuilder {

    @OptIn(ExperimentalStdlibApi::class)
    fun getCredentials(): MarvelCredentials{
        val timeStamp = Clock.System.now().toString()

        //time stamp + private key + public key
        val rawHash = "${timeStamp}${BuildKonfig.MARVEL_PRIVATE_KEY}${BuildKonfig.MARVEL_PUBLIC_KEY}"

        val mD5 = MD5().digest(rawHash.toByteArray())

        val hash = mD5.toHexString(HexFormat.Default)

        return MarvelCredentials(
            hash = hash,
            timeStamp = timeStamp
        )
    }
}