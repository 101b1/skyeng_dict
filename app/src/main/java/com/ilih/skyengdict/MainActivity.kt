package com.ilih.skyengdict

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.ilih.skyengdict.di.MainComponent
import com.ilih.skyengdict.ui.router.RouterImpl
import com.ilih.skyengdict.ui.search.SearchFragment

const val SAVED_FRAGMENT = "fragment"
const val FRAGMENT_MEANINGS = "meanings"

class MainActivity : AppCompatActivity() {

    companion object{
        private var mainComponent: MainComponent? = null
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
//        savedInstanceState?.let {
//            if (it.containsKey(SAVED_FRAGMENT)){
//                if (it.getString(SAVED_FRAGMENT) == FRAGMENT_MEANINGS) mainComponent.getRouter().navToMeaning()
//            }
//        }
//        getMainComponent().getRouter()
        setContentView(R.layout.activity_main)
        if (savedInstanceState == null){
            supportFragmentManager.beginTransaction()
                .add(R.id.fragment_container, SearchFragment::class.java, null)
                .commit()
        }
    }

    fun getMainComponent(): MainComponent{
        return mainComponent ?: MainComponent.create(
            (application as App).getAppComponent(),
            this,
            RouterImpl(supportFragmentManager))
    }

}