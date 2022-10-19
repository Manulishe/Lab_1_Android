package com.example.myapplication

import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.ui.AppBarConfiguration
import android.widget.EditText
import android.widget.TextView
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.view.View
import android.widget.Button
import com.example.myapplication.R
import android.widget.Toast
import com.example.myapplication.databinding.ActivityMainBinding
import java.util.LinkedHashSet
import java.util.concurrent.ThreadLocalRandom

class MainActivity : AppCompatActivity() {
    private val appBarConfiguration: AppBarConfiguration? = null
    private var binding: ActivityMainBinding? = null
    private var Ot: EditText? = null
    private var Do: EditText? = null
    private var button: Button? = null
    private var result: TextView? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding!!.root)
        setSupportActionBar(binding!!.toolbar)
        Ot = findViewById(R.id.from)
        Do = findViewById(R.id.before)
        button = findViewById(R.id.button)
        result = findViewById(R.id.Result)
        button.setOnClickListener(View.OnClickListener {
            if (Ot.getText().toString().trim { it <= ' ' } == "" || Do.getText().toString()
                    .trim { it <= ' ' } == "") Toast.makeText(
                this@MainActivity,
                R.string.Error,
                Toast.LENGTH_LONG
            ).show() else {

                val fromS = Ot.getText().toString().trim { it <= ' ' }
                val beforeS = Do.getText().toString().trim { it <= ' ' }
                val from = fromS.toInt()
                val before = beforeS.toInt()
                val Size = 20
                val random: MutableSet<Int> = LinkedHashSet()
                for (i in 0 until Size) {
                    val x = ThreadLocalRandom.current().nextInt(from, before + 1)
                    random.add(x)
                }
                val resultS = random.toString()
                result.setText(resultS)
            }
        })
    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        // Inflate the menu; this adds items to the action bar if it is present.
        menuInflater.inflate(R.menu.menu_main, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        val id = item.itemId
        return if (id == R.id.action_settings) {
            true
        } else super.onOptionsItemSelected(item)
    }
}