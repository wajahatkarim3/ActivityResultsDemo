package com.wajahatkarim3.activityresultsdemo

import android.app.Activity
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.MenuItem
import com.wajahatkarim3.activityresultsdemo.databinding.ActivityChooseOptionBinding

class ChooseOptionActivity : AppCompatActivity() {

    lateinit var bi: ActivityChooseOptionBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        bi = ActivityChooseOptionBinding.inflate(layoutInflater)
        setContentView(bi.root)

        setupViews()
    }

    fun setupViews()
    {
        supportActionBar?.setDisplayShowHomeEnabled(true)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)

        bi.btnAsyncTask.setOnClickListener {
            apiSelected(bi.btnAsyncTask.text.toString())
        }

        bi.btnCursosrs.setOnClickListener {
            apiSelected(bi.btnCursosrs.text.toString())

        }

        bi.btnEclipse.setOnClickListener {
            apiSelected(bi.btnEclipse.text.toString())

        }

        bi.btnEverything.setOnClickListener {
            apiSelected(bi.btnEverything.text.toString())

        }

        bi.btnFrgTransactions.setOnClickListener {
            apiSelected(bi.btnFrgTransactions.text.toString())

        }
    }

    fun apiSelected(api: String)
    {
        var intent = Intent()
        intent.putExtra("data", api)
        setResult(Activity.RESULT_OK, intent)
        finish()
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        if (item.itemId == android.R.id.home)
        {
            setResult(Activity.RESULT_CANCELED)
            finish()
        }
        return super.onOptionsItemSelected(item)
    }
}
