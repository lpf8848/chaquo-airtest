package cn.com.longene.yundong

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.Toast
import com.chaquo.python.PyException
import com.chaquo.python.Python;
import com.chaquo.python.android.AndroidPlatform

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        if (! Python.isStarted()) {
            Python.start(AndroidPlatform(this))
        }

        findViewById<Button>(R.id.button).setOnClickListener {
            val py = Python.getInstance()
            try {
                val airtest = py.getModule("airtest")
                val version = airtest.get("__version__").toString();
                Toast.makeText(this, version, Toast.LENGTH_LONG).show()
            } catch (e: PyException) {
                Toast.makeText(this, e.message, Toast.LENGTH_LONG).show()
            }
        }
    }
}
