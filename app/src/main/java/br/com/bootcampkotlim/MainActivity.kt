package br.com.bootcampkotlim

import android.content.Context
import android.content.Intent
import android.content.SharedPreferences
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.provider.Settings.Global.putString
import android.view.Menu
import android.view.MenuInflater
import android.view.MenuItem
import android.widget.Toast
import androidx.core.content.edit
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import br.com.bootcampkotlim.ContactDetail.Companion.EXTRA_CONTACT
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken

class MainActivity : AppCompatActivity(), ClickItemListener {
    private val rvList: RecyclerView by lazy{
        findViewById<RecyclerView>(R.id.rv_list)
    }

    private val adapter = ContactAdapter(this)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        //buscaListaContatos()
        bindViews()

        //updateList()
    }

    /*private fun buscaListaContatos(){
        val lista = arrayListOf(
            Contact(
                name = "Jose Santos",
                phone = "(15)98249-8203",
                photograph = "img.png"
            ),
            Contact(
                name = "Jose Cardoso",
                phone = "(15)98249-5241",
                photograph = "img.png"
            )
        )
        getInstanceSharedPreferences().edit {
            putString("contacts", Gson().toJson(lista))
        }
    }*/

    private fun getInstanceSharedPreferences(): SharedPreferences{
        return getSharedPreferences("br.com.bootcampkotlim.PREFENCES", Context.MODE_PRIVATE)
    }

    private fun bindViews(){
        rvList.adapter = adapter
        rvList.layoutManager = LinearLayoutManager(this)
        updateList()
    }

    private fun getListContacts(): List<Contact> {
        val list = getInstanceSharedPreferences().getString("contacts","[]")
        val turnTypes = object : TypeToken<List<Contact>>() {}.type
        return Gson().fromJson(list, turnTypes)
    }

    private fun updateList(){
        adapter.updateList(getListContacts())

            /*arrayListOf(
                Contact(
                    name = "Junior Santos",
                    phone = "(15)98249-8203",
                    photograph = "img.png"
                ),
                Contact(
                    name = "Juliana Cardoso",
                    phone = "(15)98249-5241",
                    photograph = "img.png"
                )
            )
        )*/
    }

    private fun showToast(message: String){
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show()
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        val inflater: MenuInflater = menuInflater
        inflater.inflate(R.menu.menu, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when(item.itemId){
            R.id.item_menu1 -> {
                showToast("Exibindo item menu 1")
                return true
            }
            R.id.item_menu2 -> {
                showToast("Exibindo item menu 2")
                return true
            }
            else -> super.onOptionsItemSelected(item)
        }
    }

    override fun clickItemContact(contact: Contact) {
        val intent = Intent(this, ContactDetail::class.java)
        intent.putExtra(EXTRA_CONTACT, contact)
        startActivity(intent)
    }
}