package com.wajahatkarim3.activityresultsdemo

import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.invoke
import androidx.activity.result.contract.ActivityResultContract
import androidx.activity.result.contract.ActivityResultContracts
import com.wajahatkarim3.activityresultsdemo.databinding.ActivityMainBinding
import java.util.jar.Manifest

class MainActivity : AppCompatActivity() {

    lateinit var bi: ActivityMainBinding

    var imageUri: Uri? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        bi = ActivityMainBinding.inflate(layoutInflater)
        setContentView(bi.root)

        setupViews()
    }

    fun setupViews()
    {
        bi.btnPick.setOnClickListener {
            pickApi()
        }

        bi.btnPickImage.setOnClickListener {
            pickImages("image/*")
        }

        bi.btnRequestPermission.setOnClickListener {
            //askLocationPermission(android.Manifest.permission.ACCESS_FINE_LOCATION)
            askMultiplePermissions(arrayOf(android.Manifest.permission.ACCESS_FINE_LOCATION))
        }

        bi.btnTakePicture.setOnClickListener {
            takePicture.invoke(imageUri)
        }
    }

    private val takePicture = registerForActivityResult(ActivityResultContracts.TakePicture()) {bitmap ->
        bitmap?.let {
            bi.imgCameraPic.setImageBitmap(it)
            bi.imgCameraPic.visibility = View.VISIBLE
        }
    }

    private val askLocationPermission = registerForActivityResult(ActivityResultContracts.RequestPermission()) { result ->
        if(result){
            Log.e("TAG", "permnission granted")
        }else{
            Log.e("TAG", "No permnission")
        }
    }

    private val askMultiplePermissions = registerForActivityResult(ActivityResultContracts.RequestMultiplePermissions()) {map ->
        for (entry in map.entries)
        {
            Toast.makeText(this, "${entry.key} = ${entry.value}", Toast.LENGTH_SHORT).show()
        }
    }

    private val pickImages = registerForActivityResult(ActivityResultContracts.GetContent()) {uri ->
        uri?.let {uri ->
            bi.imgCameraPic.setImageURI(uri)
            bi.imgCameraPic.visibility = View.VISIBLE
        }
    }

    private val pickApi = registerForActivityResult(ApiPickerActivityContract()) {api ->
        if (api == null)
            Toast.makeText(this, "Nothing selected", Toast.LENGTH_SHORT).show()
        else
            Toast.makeText(this, api, Toast.LENGTH_SHORT).show()
    }
}
