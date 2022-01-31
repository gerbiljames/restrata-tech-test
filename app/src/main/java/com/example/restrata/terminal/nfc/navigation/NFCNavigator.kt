package com.example.restrata.terminal.nfc.navigation

import android.content.Context
import android.content.Intent
import com.example.restrata.terminal.nfc.activity.cardlist.CardListActivity

object NFCNavigator {

    fun showCardList(context: Context) =
        context.startActivity(
            Intent(
                context,
                CardListActivity::class.java
            )
        )
}