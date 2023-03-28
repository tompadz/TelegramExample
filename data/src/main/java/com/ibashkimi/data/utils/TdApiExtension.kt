package com.ibashkimi.data.utils

import org.drinkless.td.libcore.telegram.TdApi

fun TdApi.Object.getName(): String {
    val regex = Regex("[{}\\s]+", RegexOption.MULTILINE)
    return regex.replace(this.toString(), "")
}