package com.test.cleando.ui.activity

import android.os.Bundle

import com.test.cleando.R
import com.test.cleando.contract.main.MainConfigurator
import com.test.cleando.contract.main.MainContract
import com.test.cleando.tool.Extra
import com.test.cleando.ui.fragment.BaseFragment
import com.test.cleando.ui.fragment.main.DetailFragment
import com.test.cleando.ui.fragment.main.TaskFragment

class MainActivity : BaseActivity(), MainContract.Controller {

    lateinit var output: MainContract.Interactor
    lateinit var router: MainContract.Router

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        MainConfigurator.configure(this)

        router.goToList()

    }

    override fun performFragmentTransaction(fragment: BaseFragment) {
        performFragmentTransaction(fragment, null)
    }

    override fun performFragmentTransaction(fragment: BaseFragment, extra: Map<String, Any>?) {

        if (fragment is TaskFragment) {

            fragment.parentRouter = router
            supportActionBar?.setDisplayHomeAsUpEnabled(false)

            showFragment(R.id.container, fragment)

        } else if (fragment is DetailFragment && extra != null) {

            fragment.parentRouter = router
            supportActionBar?.setDisplayHomeAsUpEnabled(true)

            var bundle = Bundle()


            extra.entries.forEach { entry ->

                when (entry.key) {

                    Extra.TASK_ID -> bundle.putInt(entry.key, entry.value as Int)

                }

            }

            fragment.arguments = bundle

            showFragment(R.id.container, fragment)

        }

    }

    override fun onBackPressed() {

        when (getCurrentFragment(R.id.container)) {
            TaskFragment() -> super.onBackPressed()
            DetailFragment() -> router.goToList()
        }

    }
}
