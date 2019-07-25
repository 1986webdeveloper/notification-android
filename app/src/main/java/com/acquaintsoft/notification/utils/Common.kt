package com.acquaintsoft.notification.utils

object Common {
    fun getDigits(digit: Int): String {
        if (digit > 9) {
            return "" + digit
        } else {
            return "0" + digit
        }

    }
}