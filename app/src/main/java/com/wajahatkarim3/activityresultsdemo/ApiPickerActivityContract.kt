package com.wajahatkarim3.activityresultsdemo

import android.app.Activity
import android.app.Instrumentation
import android.content.Context
import android.content.Intent
import androidx.activity.result.contract.ActivityResultContract

class ApiPickerActivityContract : ActivityResultContract<Unit, String?>() {

    override fun createIntent(context: Context, input: Unit?): Intent {
        return Intent(context, ChooseOptionActivity::class.java)
    }

    override fun parseResult(resultCode: Int, intent: Intent?): String? = when
    {
        resultCode != Activity.RESULT_OK -> null
        else -> intent?.getStringExtra("data")
    }
}