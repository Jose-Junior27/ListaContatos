package br.com.bootcampkotlim

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.TextView

class ContactDetail : AppCompatActivity() {
    private var contact: Contact? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_contact_detail)

        getExtras()
        bindViews()
    }

    private fun getExtras(){
        contact = intent.getParcelableExtra(EXTRA_CONTACT)
    }

    private fun bindViews(){
        findViewById<TextView>(R.id.tv_name).text = contact?.name
        findViewById<TextView>(R.id.tv_phone).text = contact?.phone

    }

    companion object{
        const val EXTRA_CONTACT: String = "EXTRA_CONTACT"
    }

}