package com.denniverse.heapk

import com.denniverse.heapk.models.AppContext
import com.denniverse.heapk.models.HeapKConfig
import kotlinx.coroutines.InternalCoroutinesApi
import kotlinx.coroutines.internal.SynchronizedObject
import kotlinx.coroutines.internal.synchronized

internal expect fun startHeap(context: AppContext, config: HeapKConfig)
internal expect fun addHeapUserProperties(userProperties: Map<String, String?>)
internal expect fun trackHeap(action: String, vararg data:Pair<String, String>)

@OptIn(InternalCoroutinesApi::class)
class HeapK {
    internal var isInit = false
    internal var context = AppContext
    private val lock = SynchronizedObject()
    private val preInitQueue = mutableListOf<Pair<String, Array<out Pair<String, String>>>>()

    fun setContext(context: AppContext) {
        this.context = context
    }

    fun initialize(config: HeapKConfig) {
        if (!isInit) {
            isInit = true
            startHeap(context, config)
            dumpQueue()
        }
    }

    fun track(action: String, vararg data:Pair<String, String>) {
        if (!isInit) {
            synchronized(lock) {
                preInitQueue.add(Pair(action, data))
            }
        } else {
            trackHeap(action, *data)
        }
    }

    private fun dumpQueue() {
        synchronized(lock) {
            preInitQueue.forEach {
                track(it.first, *it.second)
            }
            preInitQueue.clear()
        }
    }

}

fun <K, V> Map<K, V?>.removeNullValuePairs(): Map<K, V> {
    return this.filterValues { it != null }
        .mapNotNull { (key, value) -> value?.let { key to it } }
        .toMap()
}