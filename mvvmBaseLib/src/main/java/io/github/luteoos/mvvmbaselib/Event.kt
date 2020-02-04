package io.github.luteoos.mvvmbaselib

/**
 * Base class for single use Events
 *
 * Created by Luteoos on 24.01.2020
 */
open class Event<out T>(private val payload: T) {

    /**
     * lock external write
     */
    var isUsed = false
        private set

    /**
     * return payload and marks as used
     */
    fun get(): T? =
            when(isUsed){
                true -> null
                false -> {isUsed = true; payload}
            }

    /**
     * return payload ignoring isUsed
     */
    fun peek() = payload
}