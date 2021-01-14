package com.sandiplus.calltracker.ui.activity

import android.Manifest
import android.content.Context
import android.content.pm.PackageManager
import android.database.Cursor
import android.os.Bundle
import android.provider.CallLog
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import com.sandiplus.calltracker.R
import com.sandiplus.calltracker.core.BaseActivity
import com.sandiplus.calltracker.databinding.ActivityMainBinding
import com.sandiplus.calltracker.viewmodel.MainViewModel
import dagger.hilt.android.AndroidEntryPoint
import java.lang.Long
import java.util.*

@AndroidEntryPoint
class MainActivity : BaseActivity() {

    private val CALL_LOG_REQUEST_CODE = 1

    private lateinit var binding: ActivityMainBinding
    private lateinit var viewModel: MainViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        viewModel = getViewModel()
        binding = bindContentView(R.layout.activity_main)
        // or
        //setContentView(R.layout.activity_main)

        if (checkCallLogPermission()) {
            binding.callList.text = getCallDetails(this)
        } else {
            requestCallLogPermission()
        }
    }

    override fun onRequestPermissionsResult(requestCode: Int, permissions: Array<String>, grantResults: IntArray) {
        when (requestCode) {
            CALL_LOG_REQUEST_CODE -> {
                if ((grantResults.isNotEmpty() && grantResults[0] == PackageManager.PERMISSION_GRANTED)) {
                    binding.callList.text = getCallDetails(this)
                } else {
                    checkCallLogPermission()
                }
                return
            }

            // Add other 'when' lines to check for other
            // permissions this app might request.
            else -> {
                // Ignore all other requests.
            }
        }
    }

    private fun getCallDetails(context: Context): String? {
        val stringBuffer = StringBuffer()

        val cursor: Cursor? = context.contentResolver.query(
            CallLog.Calls.CONTENT_URI,
            null, null, null, CallLog.Calls.DATE.toString() + " DESC"
        )

        if (cursor != null) {
            val number: Int = cursor.getColumnIndex(CallLog.Calls.NUMBER)
            val type: Int = cursor.getColumnIndex(CallLog.Calls.TYPE)
            val date: Int = cursor.getColumnIndex(CallLog.Calls.DATE)
            val duration: Int = cursor.getColumnIndex(CallLog.Calls.DURATION)

            var index = 1

            while (cursor.moveToNext()) {
                val phNumber: String = cursor.getString(number)
                val callType: String = cursor.getString(type)
                val callDate: String = cursor.getString(date)
                val callDayTime = Date(Long.valueOf(callDate))
                val callDuration: String = cursor.getString(duration)
                var dir: String? = null
                val dircode = callType.toInt()

                when (dircode) {
                    CallLog.Calls.OUTGOING_TYPE -> dir = "OUTGOING"
                    CallLog.Calls.INCOMING_TYPE -> dir = "INCOMING"
                    CallLog.Calls.MISSED_TYPE -> dir = "MISSED"
                }

                stringBuffer.append("""
                        |$index. 
                        |   Phone Number: --- $phNumber 
                        |   Call Type: --- $dir 
                        |   Call Date: --- $callDayTime 
                        |   Call duration in sec: --- $callDuration
                        |""".trimMargin()
                )
                stringBuffer.append("\n")

                index++
            }

            cursor.close()
            return stringBuffer.toString()
        } else {
            return "ERROR"
        }
    }

    private fun checkCallLogPermission(): Boolean {
        return ContextCompat.checkSelfPermission(this, Manifest.permission.READ_CONTACTS) == PackageManager.PERMISSION_GRANTED
    }

    private fun requestCallLogPermission() {
        ActivityCompat.requestPermissions(this, arrayOf(Manifest.permission.READ_CALL_LOG), CALL_LOG_REQUEST_CODE)
    }
}
