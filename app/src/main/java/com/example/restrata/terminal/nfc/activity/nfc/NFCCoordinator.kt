package com.example.restrata.terminal.nfc.activity.nfc

import android.content.Context
import com.example.restrata.terminal.nfc.navigation.NFCNavigator
import dagger.hilt.android.qualifiers.ActivityContext
import dagger.hilt.android.scopes.ActivityScoped
import javax.inject.Inject

@ActivityScoped
class NFCCoordinator @Inject constructor(
    @ActivityContext private val context: Context
) {
    fun showCardList() = NFCNavigator.showCardList(context)
}